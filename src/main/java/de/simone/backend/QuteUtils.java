package de.simone.backend;

import java.util.*;

import org.apache.commons.lang3.exception.*;

import de.simone.*;
import io.quarkus.mailer.*;
import io.quarkus.qute.*;
import jakarta.enterprise.context.*;
import jakarta.inject.*;
import jakarta.ws.rs.core.*;

@ApplicationScoped
public class QuteUtils {

    @Inject
    Mailer mailer;

    public static Response checkQuteTemplate(String template) {
        Response response = TAService.getOkResponse("Response.qute", "msg", "Qute template Ok");
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
                response = TAService.getBadRequestResponse("Response.qute", "msg", e.getMessage());
        }
        return response;
    }

    public static Mail prepareMail(Mailing mailing, String to, TAEntity... entities) {
        Map<String, Object> varsMap = getVariableMap(entities);

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
            text = Qute.fmt(template, varsMap);
        } catch (Exception e) {
            e.printStackTrace();
            text = ExceptionUtils.getRootCauseMessage(e);
        }
        return text;
    }

    public static String formatText(String template, TAEntity... entities) {
        return formatText(template, getVariableMap(entities));
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

    public Map<String, String> getEntitiesVariables(boolean withBrakets, TAEntity... entities) {
        Map<String, String> vars = new TreeMap<>();
        for (TAEntity entity : entities) {
            vars.putAll(getEntityVariables(entity));
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
     * main entry point to send emails.
     * 
     * @param mailing  - the Mailing element to send
     * @param entities - the entities used to fill all information inside the email
     * @return - response
     */
    public Response sendMail(Mailing mailing, TAEntity... entities) {
        Mail mail = MailingsService.prepareMail(mailing, null, entities);
        mailer.send(mail);
        return TAService.getOkResponse("Response.email.sent", "mailing", mailing.mailingName);
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
            variables.put("User.embeddedAvatar", TranslationProvider.getTranslation("Person.avatar"));

            variables.remove("User.password");
            variables.remove("User.sessionId");
            variables.remove("User.userSecret");
        }

        if (entity instanceof Adult) {
            variables.putAll(MiscellaneousService.getEntityFields(Adult.class, true));
            variables.put("Person.fullName", TranslationProvider.getTranslation("Person.fullName"));
            variables.put("Person.age", TranslationProvider.getTranslation("Person.age"));
            variables.put("Person.embeddedPicture", TranslationProvider.getTranslation("Person.picture"));
            variables.put("Person.personalityValues", TranslationProvider.getTranslation("Person.personality"));
            variables.put("Person.interestsValues", TranslationProvider.getTranslation("Person.interests"));
            variables.put("Person.ocupationValues", TranslationProvider.getTranslation("Person.ocupation"));
        }

        if (entity instanceof Tamagotchi) {
            variables.putAll(MiscellaneousService.getEntityFields(Tamagotchi.class, true));
            variables.put("Tamagotchi.embeddedAvatar", TranslationProvider.getTranslation("Person.avatar"));
            variables.put("Tamagotchi.personalityValues", TranslationProvider.getTranslation("Tamagotchi.personality"));
            variables.put("Tamagotchi.strengthsValues", TranslationProvider.getTranslation("Tamagotchi.strengths"));
            variables.put("Tamagotchi.weaknessesValues", TranslationProvider.getTranslation("Tamagotchi.weaknesses"));
        }

        return variables;
    }
}
