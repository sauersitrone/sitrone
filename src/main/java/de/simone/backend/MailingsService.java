package de.simone.backend;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.exception.ExceptionUtils;

import de.simone.TranslationProvider;
import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import io.quarkus.qute.Qute;
import io.quarkus.qute.TemplateException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class MailingsService extends TAService<Mailing> {

        @Inject
    Mailer mailer;

    public MailingsService() {
        super(Mailing.class);
    }

    public Map<String, String> getSubjectVariables(String audience) {
            return getEntitiesVariables(new User());
    }

    public Map<String, String> getBodyVariables(String audience) {
            return getEntitiesVariables(new User());
    }

    @Override
    public Mailing get(Long id) throws WebApplicationException {
        return getImpl(id);
    }

    @Override
    @Transactional
    public Response delete(Long id) {
        return deleteImpl(id);
    }

    public Response checkQuteTemplate(String template) {
        Response response = getOkResponse("Response.qute", "msg", "Qute template Ok");
        // silent ignore null template
        if (template == null)
            return response;
        try {
            Qute.fmt(template).render();
        } catch (TemplateException e) {
            // apparently the message is only available whe the error is a sintaxis error,
            // but with no variables, (template is ok but qute cna.t render without
            // entities) there is no message that means: no message -> template is ok
            if (e.getMessage() != null)
                response = getBadRequestResponse("Response.qute", "msg", e.getMessage());
        }
        return response;
    }

    @Override
    @Transactional
    public Response save(Mailing entity) {
        // check qute template
        Response response = checkQuteTemplate(entity.message);
        if (response.getStatus() != Response.Status.OK.getStatusCode())
            return response;
        response = checkQuteTemplate(entity.subject);
        if (response.getStatus() != Response.Status.OK.getStatusCode())
            return response;

        return saveImpl(entity);
    }

    /**
     * 
     * @param mailing   - the {@link Mailing} entity to prepare
     * @param variables - a map with initial variable-value list to insert or
     *                  null
     * @param entities  - a list of {@link TAEntity} to be used as source for
     *                  variable sustitution
     * @return
     */
    public static Mail prepareMail(Mailing mailing, Map<String, Object> variables, TAEntity... entities) {
        Map<String, Object> varsMap = getVariableMap(entities);
        if (variables != null)
            varsMap.putAll(variables);

        String to = "";
        if (Mailing.INTERNAL.equals(mailing.audience)) {
            Object entity = varsMap.get(User.class.getSimpleName());
            if (entity != null) {
                User user = (User) entity;
                to = user.lastName + ", " + user.firstName + "<" + user.email + ">";
            }
        } else {
                // to = donation.lastName + ", " + donation.firstName + "<" + donation.email + ">";
        }

        String subject = formatText(mailing.subject, varsMap);
        String body = formatText(mailing.message, varsMap);

        GlobalProperty property = GlobalProperty.getInstance();
        String from = property.appName + "<" + property.appEmail + ">";

        Mail mail = Mailing.HTML.equals(mailing.type) ? Mail.withHtml(to, subject, body)
                : Mail.withText(to, subject, body);
        mail.setFrom(from);
        return mail;
    }

    public static String formatText(String template, Map<String, Object> varsMap) {
        // silent return a empty string if pattern
        if (template == null)
            return "";

        String text = "*** Error in template rendering";
        try {
            // Regular expression to capture `data-src` and `src` attributes
            String regex = "(<img[^>]*data-src=\"([^\"]+)\"[^>]*src=\"([^\"]+)\"[^>]*>)";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(template);

            // Replace the `src` value with the value from `data-src`
            template = matcher.replaceAll(m -> {
                String dataSrc = m.group(2); // The value of `data-src`
                String src = m.group(3); // The value of `src` (to be replaced)
                // Return the updated HTML tag with `src` replaced by `data-src`
                return m.group(1).replace("src=\"" + src + "\"", "src=\"" + dataSrc + "\"");
            });


             regex = "(<span[^>]*>([^<]*)</span>)";
             pattern = Pattern.compile(regex);
             matcher = pattern.matcher(template);
             template = matcher.replaceAll(m -> {
                String varName = m.group(2);
                return varName;
            });

            
            text = Qute.fmt(template, varsMap);
        } catch (Exception e) {
            e.printStackTrace();
            text = ExceptionUtils.getRootCauseMessage(e);
        }
        return text;
    }

    public static Map<String, Object> getVariableMap(TAEntity... entities) {
        Map<String, Object> varsMap = new HashMap<>();
        // add all entities to the variable map
        for (TAEntity taEntity : entities) {
            String key = taEntity.getClass().getSimpleName();
            varsMap.put(key, taEntity);
        }

        return varsMap;
    }

    public static String formatText(String template, TAEntity... entities) {
        return formatText(template, getVariableMap(entities));
    }

    /**
     * return a {@link Map} of all field inside of the entites passes as
     * argument. this method autometic add the variables declared in
     * {@link GlobalProperty} file
     * 
     * @param forSelection - true return the varable name to be used within mail
     *                     template ${variableName}. false return only the
     *                     variableName
     * @param entities     - list of entities to retrive the variables from
     * @return
     */
    public Map<String, String> getEntitiesVariables(boolean withBrakets, TAEntity... entities) {
        Map<String, String> vars = new TreeMap<>();
        for (TAEntity entity : entities) {
            vars.putAll(getEntityVariables(entity));
            // add the related variable
            String prefix = entity.getClass().getSimpleName() + ".";
            addRelatedVariables(prefix, vars);
        }
        // set the key as variable pattern
        Map<String, String> vars2 = new TreeMap<>();
        vars.forEach((k, v) -> vars2.put("{" + k + "}", v));
        return withBrakets ? vars2 : vars;
    }

    public Map<String, String> getEntitiesVariables(TAEntity... entities) {
        return getEntitiesVariables(true, entities);
    }

    /**
     * add to the list all related variables with the given prefix. the related
     * variables are aditional variables not found in the asociated entity but in
     * ...
     * 
     * @param prefix - prefix of the global property
     * @param toList - list to append the variables
     */
    private void addRelatedVariables(String prefix, Map<String, String> toList) {
        // TODO: not implemented jet. i need to create a related file subsistem for
        // aditionals variables not found in original entities. this will be needed as
        // par of cusomization / aditional requeriment of client with spetias needs
    }

    @SuppressWarnings("java:S1192") // copy and paste the entity name is more visual for me
    private Map<String, String> getAddressVariables(String variableName) {
        Map<String, String> variables = new TreeMap<>();
        variables.putAll(variables);
        variables.put(variableName + ".city", TranslationProvider.getTranslation("Address.city"));
        variables.put(variableName + ".countryName", TranslationProvider.getTranslation("Country.name"));
        variables.put(variableName + ".country", TranslationProvider.getTranslation("Country.code"));
        variables.put(variableName + ".email", TranslationProvider.getTranslation("Address.email"));
        variables.put(variableName + ".firstName", TranslationProvider.getTranslation("Address.firstName"));
        variables.put(variableName + ".id", TranslationProvider.getTranslation("TAEntity.id"));
        variables.put(variableName + ".isMain", TranslationProvider.getTranslation("Address.isMain"));
        variables.put(variableName + ".lastName", TranslationProvider.getTranslation("Address.lastName"));
        variables.put(variableName + ".phone", TranslationProvider.getTranslation("Address.phone"));
        variables.put(variableName + ".postcode", TranslationProvider.getTranslation("Address.postcode"));
        variables.put(variableName + ".salutation", TranslationProvider.getTranslation("Address.salutation"));
        variables.put(variableName + ".street", TranslationProvider.getTranslation("Address.street"));
        variables.put(variableName + ".title", TranslationProvider.getTranslation("Address.title"));
        variables.put(variableName + ".type", TranslationProvider.getTranslation("Address.type"));

        variables.put(variableName + ".mailSalutation", TranslationProvider.getTranslation("Address.mailSalutation"));
        variables.put(variableName + ".embeddedPicture", TranslationProvider.getTranslation("Address.picture"));
        variables.put(variableName + ".embeddedSignature", TranslationProvider.getTranslation("Address.signature"));
        variables.put(variableName + ".getGermanSalutation(\"soft\")",
                TranslationProvider.getTranslation("Address.getGermanSalutation"));
        variables.put(variableName + ".getGermanSalutation(\"formal\")",
                TranslationProvider.getTranslation("Address.getGermanSalutation"));
        variables.put(variableName + ".address",
                TranslationProvider.getTranslation("Address"));

        return variables;
    }

    
    /**
     * main entry point to send emails.
     * 
     * @param mailing  - the Mailing element to send
     * @param entities - the entities used to fill all information inside the email
     * @return - response
     */
    public Response sendMail(Mailing mailing, TAEntity... entities) {
        Mail mail = MailingsService.prepareMail(mailing, null, entities);
        mailer.send(mail);
        return getOkResponse("Response.email.sent", "mailing", mailing.mailingName);
    }

    private Map<String, String> getBankAccountVariables(String variableName) {
        Map<String, String> variables = new TreeMap<>();
        variables.put(variableName + ".bankName", TranslationProvider.getTranslation("BankAccount.bankName"));
        variables.put(variableName + ".bankNumber", TranslationProvider.getTranslation("BankAccount.bankNumber"));
        variables.put(variableName + ".bic", TranslationProvider.getTranslation("BankAccount.bic"));
        variables.put(variableName + ".city", TranslationProvider.getTranslation("BankAccount.city"));
        variables.put(variableName + ".iban", TranslationProvider.getTranslation("BankAccount.iban"));
        variables.put(variableName + ".id", TranslationProvider.getTranslation("TAEntity.id"));
        variables.put(variableName + ".ownerName", TranslationProvider.getTranslation("BankAccount.ownerName"));
        variables.put(variableName + ".postCode", TranslationProvider.getTranslation("BankAccount.postCode"));
        return variables;
    }

    private Map<String, String> getEntityVariables(TAEntity entity) {
        Map<String, String> variables = new TreeMap<>();
        if (entity instanceof User) {
            variables.putAll(MiscellaneousService.getEntityFields(User.class, true));
            variables.put("User.embeddedAvatar", TranslationProvider.getTranslation("User.avatar"));

            variables.remove("User.password");
            variables.remove("User.sessionId");
            variables.remove("User.userSecret");
        }

        return variables;
    }

    @Override
    @Transactional
    public Response duplicate(Long id) {
        return duplicateImpl(id);
    }

}
