package de.simone.backend;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import io.quarkus.logging.Log;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class MessagingProvidersService extends TAService<MessagingProvider> {

  private final ObjectMapper objectMapper = new ObjectMapper();
  private final OkHttpClient okHttpClient = new OkHttpClient();
  private final MediaType jsonMediaType = MediaType.parse("application/json");
  public static record ProviderEndPoint(String baseUrl, String authorization) {}

  public MessagingProvidersService() {
    super(MessagingProvider.class);
  }

  @Override
  public List<MessagingProvider> listAll() {
    return MessagingProvider.listAll(Sort.ascending("provider"));
  }

  @Override
  public MessagingProvider get(Long id) throws WebApplicationException {
    return getImpl(id);
  }

  @Override
  @Transactional
  public Response delete(Long id) {
    return deleteImpl(id);
  }

  @Override
  @Transactional
  public Response save(MessagingProvider entity) {
    Response response;
    if (!entity.isNewEntity()) {
      response = super.saveImpl(entity);
    } else {
      // check if is there a provider with the same "provider"
      Long count = MessagingProvider.find("provider = ?1", entity.provider).count();
      System.out.println("MessagingProvidersService.save() " + count);
      if (count > 0)
        return getBadRequestResponse(
            "Response.messagingProvider.error", "provider", entity.provider);

      response = super.saveImpl(entity);
    }
    return response;
  }

  @Override
  public Response duplicate(Long id) {
    return duplicateImpl(id);
  }

  @SuppressWarnings("unchecked")
  public Response sendWhatsappMessage(Adult adult, String templateId) {
    try {
      User user = User.findById(adult.carerId);
      String auth = "Bearer " + user.messagingProvider.clientId;
      String url =
          "https://graph.facebook.com/v22.0/" + user.messagingProvider.accountName + "/messages";

      Language language = new Language();
      language.code = "en_US";
      Template template = new Template();
      template.language = language;

      Component component = new Component();
      component.parameter_name = "User.email";
      component.text = user.email;

      template.name = templateId;
      WhatsappMessage message = new WhatsappMessage();
      message.template = template;
      message.to = user.phone;

      System.out.println(objectMapper.writeValueAsString(message));

      RequestBody body =
          RequestBody.create(jsonMediaType, objectMapper.writeValueAsString(message));
      Request request =
          new Request.Builder()
              .url(url)
              .method("POST", body)
              .addHeader("Authorization", auth)
              .addHeader("Content-Type", jsonMediaType.toString())
              .build();
      com.squareup.okhttp.Response response = okHttpClient.newCall(request).execute();
      String responseBody = response.body().string();
      // System.out.println("MessagingProvidersService.sendWhatsappMessage() " + responseBody);

      // diferent template according to the operationsresult
      if (responseBody.startsWith("{\"error\":{")) {
        HashMap<String, Object> hashMap = objectMapper.readValue(responseBody, HashMap.class);
        Map<String, Object> errorMap = (Map<String, Object>) hashMap.get("error");
        String errMessage = errorMap.get("message").toString();
        Log.error("Error sending whatsapp message: " + errMessage);
        return getBadRequestResponse("Response.whatsapp.error", "errMessage", errMessage);
      }
    } catch (Exception e) {
      Log.error("", e);
    }
    return getOkResponse("Response.whatsapp.ok");
  }

  public static class Language {
    public String code;
  }

  public static class Component {
    public String type = "text";
    public String parameter_name;
    public String text;
  }

  public static class Template {
    public String name;
    public Language language;
    public List<Component> components = new ArrayList<>();
  }

  public static class WhatsappMessage {
    public String messaging_product = "whatsapp";
    public String to;
    public String type = "template";
    public Template template;
  }

  public static class Error {
    public String message;
    public String type;
    public int code;
    public int error_subcode;
    public String fbtrace_id;
  }

  public static class WhatsappResponse {
    public Error error;
  }
}
