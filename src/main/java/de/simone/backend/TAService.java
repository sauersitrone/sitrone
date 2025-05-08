package de.simone.backend;

import java.io.*;
import java.util.*;
import java.util.stream.*;

import org.apache.commons.lang3.*;
import org.apache.commons.lang3.exception.*;

import com.ibm.icu.text.*;
import com.opencsv.bean.*;

import de.simone.*;
import de.simone.backend.restop.*;
import io.quarkus.hibernate.orm.panache.*;
import io.quarkus.logging.*;
import io.quarkus.panache.common.*;
import io.quarkus.security.runtime.*;
import jakarta.inject.*;
import jakarta.transaction.*;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

/**
 * master — Can access all admin — Can access all capabilities inside
 * organisation. edit — Can
 * perfom all CRUD operation non critial endpoints. contributor — Can only _RU_
 * operation non
 * critial endpoints. user — Can only read.
 */
public abstract class TAService<E extends TAEntity> {

    private static final String RESPONSE_IMPORTERROR = "Response.importError";
    private static final String KEY_ERROR = "error";
    private static final String KEY_ENTITY = "entity";

    private List<E> toImportEntities = new ArrayList<>();
    protected Class<? extends TAEntity> clazz;
    @Context
    protected HttpHeaders httpHeaders;
    @Context
    protected UriInfo uriInfo;
    @Inject
    protected SecurityIdentityAssociation securityIdentityAssociation;
    // @Inject
    // public SearchSession searchSession;

    /**
     * All sub classes müssen dieses Methode ausführen
     *
     * @param clazz - entsprechende Instanz von {@link TAEntity}
     */
    protected TAService(Class<? extends TAEntity> clazz) {
        this.clazz = clazz;
    }

    public abstract Response delete(Long id);

    public abstract Response duplicate(Long id);

    public abstract Response save(E entity);

    public abstract E get(Long id) throws WebApplicationException;

    @Transactional
    @SuppressWarnings("static-access")
    protected Response duplicateImpl(Long id) {
        E entity = get(id);
        entity.id = null;
        // aparently non static call to this method work.
        entity.getEntityManager().detach(entity);
        entity.persist();
        return getCreatedResponse(entity.id);
    }

    @Transactional
    @SuppressWarnings("static-access")
    protected Response saveImpl(E entity) {
        Response response;
        if (!entity.isNewEntity()) {
            AuditLog.logUpdate(entity);
            entity.getEntityManager().merge(entity);
            return getUpdatedResponse(entity.id);
        } else {
            // entity.setOwner(SecurityUtils.getAccount());
            AuditLog.logInsert(entity);
            entity.persist();
            response = getCreatedResponse(entity.id);
        }
        return response;
    }

    @Transactional
    protected Response deleteImpl(Long id) {
        E e = getImpl(id);
        e.delete();
        AuditLog.logDelete(e);
        return getDeletedResponse(id);
    }

    public WebApplicationException getEntityNotFoundException(Long id) {
        return getEntityNotFoundException(clazz.getSimpleName(), id);
    }

    public static WebApplicationException getEntityNotFoundException(String clazzSimpleName, Object id) {
        String entityName = TranslationProvider.getTranslation(clazzSimpleName);
        return getWebApplicationException(Response.Status.NOT_FOUND, "Response.notFound", id,
                entityName);
    }

    /**
     * standar get endpoint method. This method
     * <li>retrive and return the request entitiy if exist
     * <li>return a new entity if the id is null o 0L
     * 
     * @param id - the entity id or null or 0L
     * @return the entity
     * @throws WebApplicationException - if the requested entity id doesn exit
     */
    @SuppressWarnings({ "unchecked", "java:S2201" })
    protected E getImpl(Long id) throws WebApplicationException {
        E entity = null;
        // Account account = SecurityUtils.getAccount();
        if (id == null || id == 0) {
            try {
                entity = (E) clazz.getDeclaredConstructor().newInstance();
                entity.id = null;
                // TODO:temp. external access dont hat logged user nor account.
                // entity.setOwner(account);
            } catch (Exception e) {
                Log.error(e);
            }
        } else {
            Optional<E> optional = Optional.empty();
            try {
                optional = (Optional<E>) clazz.getMethod("findByIdOptional", Object.class).invoke(null, id);
            } catch (Exception e) {
                Log.error(e);
            }
            if (optional.isPresent())
                entity = optional.get();
            else
                optional.orElseThrow(() -> getEntityNotFoundException(id));
        }
        return entity;
    }

    /**
     * create a return a {@link Response} containing the values passes as arguments.
     * The message
     * value can be a patter for {@link MessageFormat}. in this case, the values
     * from keyValues
     * argument is used as params.
     *
     * @param statusCode - any of the Success standard status code.
     * @param messageId  - message id
     * @param keyValues  - additional key=value info. Array of {@link String} where
     *                   each element has
     *                   key:value format.
     * @return response
     */
    public static Response getResponse(Response.Status statusCode, String messageId, Object... keyValues) {
        Validate.isTrue((keyValues.length % 2) == 0, "Number of arguments in 'keyValues'parameter muss be eben.");

        TreeMap<String, Object> map = new TreeMap<>();
        // parameters in case of message is a pattern
        Object[] params = new Object[keyValues.length / 2];
        // key value arguments
        for (int i = 0; i < keyValues.length; i += 2) {
            params[i / 2] = keyValues[i + 1];
            map.put(keyValues[i].toString(), keyValues[i + 1]);
        }
        String msg = TranslationProvider.getTranslation(messageId, params);
        map.put("messageId", messageId);
        map.put("message", msg);
        return Response.status(statusCode).entity(map).build();
    }

    public Response getEntityNotFoundResponse(long id) {
        String entityName = TranslationProvider.getTranslation(clazz.getSimpleName());
        return getResponse(Response.Status.NOT_FOUND, "Response.notFound", "id", id, KEY_ENTITY,
                entityName);
    }

    public Response getServerErrorResponse(Exception e) {
        int line = e.getStackTrace()[0].getLineNumber();
        String clz = e.getStackTrace()[0].getClassName();
        String msg = e.getLocalizedMessage();
        Response response = getResponse(Response.Status.INTERNAL_SERVER_ERROR, msg, "class", clz, "line", line);
        return response;
    }

    public static Response getOkResponse(String message, Object... keyValues) {
        return getResponse(Response.Status.OK, message, keyValues);
    }

    public static Response getBadRequestResponse(String message, Object... keyValues) {
        return getResponse(Response.Status.BAD_REQUEST, message, keyValues);
    }

    public static Response getBadRequestResponse(Map<?, ?> map) {
        return Response.status(Response.Status.BAD_REQUEST).entity(map).build();
    }

    public Response getDeletedResponse(Long id) {
        String entityName = TranslationProvider.getTranslation(clazz.getSimpleName());
        return getResponse(Response.Status.OK, "Response.deleted", "id", id, KEY_ENTITY, entityName);
    }

    public Response getForeignKeyResponse(Class<?> parent, Class<?> child) {
        String entityName = TranslationProvider.getTranslation(parent.getSimpleName());
        String associatedEntityName = TranslationProvider.getTranslation(child.getSimpleName());
        return getResponse(Response.Status.BAD_REQUEST, "Response.foreignKey", KEY_ENTITY, entityName,
                "Associated entity", associatedEntityName);
    }

    public Response getCreatedResponse(Long id) {
        String entityName = TranslationProvider.getTranslation(clazz.getSimpleName());
        return getResponse(Response.Status.CREATED, "Response.created", "id", id, KEY_ENTITY,
                entityName);
    }

    public Response getUpdatedResponse(Long id) {
        String entityName = TranslationProvider.getTranslation(clazz.getSimpleName());
        return getResponse(Response.Status.ACCEPTED, "Response.updated", "id", id, KEY_ENTITY,
                entityName);
    }

    // private Response getBadImportResponse()

    /**
     * export function implementation.
     * 
     * @param request - the specifications of sub list to be exported. null to
     *                export the complete sublist
     * @return the string to export
     */
    protected String exportImpl(ListDataRequest request) {
        String toByteString = "";
        try {
            ListDataRequest dataRequest = request == null ? new ListDataRequest(null, null) : request;
            dataRequest.toExport = true;
            ListDataProvider<E> dataProvider = listImpl(dataRequest);
            Stream<E> items = dataProvider.data.stream();
            StringWriter output = new StringWriter();
            StatefulBeanToCsv<E> writer = new StatefulBeanToCsvBuilder<E>(output).withApplyQuotesToAll(false).build();
            writer.write(items);
            toByteString = output.toString();
        } catch (Exception e) {
            Log.error("", e);
        }
        return toByteString;
    }

    public List<E> listAll() {
        return listAll(null);
    }

    public List<E> listAll(Sort sort) {
        return list(null, sort, null);
    }

    public List<E> list(String query, Sort sort, Parameters parameters) {
        User user = SecurityUtils.getLoggedUser();
        return list(user.currentAdultId, query, sort, parameters);
    }

    public List<E> list(String query, Parameters parameters) {
        User user = SecurityUtils.getLoggedUser();
        return list(user.currentAdultId, query, null, parameters);
    }

    /**
     * service endpoint equivalent to {@link PanacheEntity#find(String query, Sort
     * sort, Map<String, Object> params)}
     * 
     * @param secondaryKey - sub list owner to retrive
     * @param query        - the query
     * @param sort         - the sort
     * @param parameters   - the query parameters
     * @return the list
     */
    protected List<E> list(Long secondaryKey, String query, Sort sort, Parameters parameters) {
        ListDataProvider<E> dataProvider = list(secondaryKey, query, sort, parameters, -1, -1);
        return dataProvider.data;
    }

    /**
     * Return a {@link ListDataProvider} contain all information to allow restapi
     * enpoints show partial list data. This method is a bridget to
     * {@link #list(Long, String, Sort, Parameters, int, int)} method. the toExport
     * parameter is intended for export operations. this parameters override the
     * limit and page from ListDataRequest and return the complete list
     * 
     * @param request  - the request
     * @param toExport - true = override the limit un page parameter
     * @return the result
     */
    public ListDataProvider<E> listImpl(ListDataRequest request) {
        Sort sort = SortBuilder.build(request.sortBy);
        Parameters parameters = new Parameters();
        String query = request.query;
        // partial where from user
        if (!StringUtils.isBlank(request.partialWhere)) {
            if (StringUtils.isBlank(query))
                query = request.partialWhere;
            else
                query += " AND " + request.partialWhere;
        }

        // override when the resulted list is needed for export
        int limit = request.toExport ? -1 : request.limit;
        int page = request.toExport ? -1 : request.page;

        // TODO: i dont now what to doo !!!!!!!!!!
        Long sKey = 0L;

        ListDataProvider<E> dataProvider = list(sKey, query, sort, parameters, limit, page);
        dataProvider.sortBy = request.sortBy;
        dataProvider.partialWhere = query;
        return dataProvider;
    }

    @SuppressWarnings("unchecked")
    private ListDataProvider<E> list(Long secondaryKey, String query, Sort sort, Parameters parameters, int limit,
            int page) {
        ListDataProvider<E> dataProvider = new ListDataProvider<>(null);
        try {
            String baseQuery = "";
            Map<String, Object> baseParms = new HashMap<>();

            // redirect secondaryKey for know entities. this files are read direct from Sitrone
            Long sK = UserDomain.class.isAssignableFrom(clazz) ? User.ADMIN_USER_ID : secondaryKey;
            baseParms.put("secondaryKey", sK);
            baseQuery = "secondaryKey = :secondaryKey";

            // append internal query to original query
            if (query != null) {
                baseQuery += " AND " + query;
                baseParms.putAll(parameters.map());
            }
            System.out.println("TAService.list() " + baseQuery);
            System.out.println("TAService.list() " + baseParms);

            PanacheQuery<E> panacheQuery = null;
            if (sort == null) {
                panacheQuery = (PanacheQuery<E>) clazz.getMethod("find", String.class, Map.class).invoke(null,
                        baseQuery, baseParms);
            } else {
                panacheQuery = (PanacheQuery<E>) clazz.getMethod("find", String.class, Sort.class, Map.class)
                        .invoke(null, baseQuery, sort, baseParms);
            }
            if (sort != null) {
                String cols = sort.getColumns().stream().map(c -> c.getName()).collect(Collectors.joining(", "));
                // System.out.println("sort " + cols);
            }

            long count = panacheQuery.count();
            List<E> list;
            boolean pagination = page > -1 && limit > -1;
            if (pagination) {
                int pages = (int) count / limit;
                // page parameter out of range
                page = page < 0 ? 0 : page;
                page = page > pages ? pages : page;
                // limit parameter out of range
                limit = limit < 1 ? ListDataRequest.DEFAULT_LIMIT : limit;
                list = panacheQuery.page(page, limit).list();
            } else {
                // return the complete list. No pagination
                list = panacheQuery.list();
            }

            dataProvider = new ListDataProvider<>(list, count, page, limit);
        } catch (Exception e) {
            Log.error("", e);
            dataProvider.errorMessage = ExceptionUtils.getRootCauseMessage(e);
        }

        return dataProvider;
    }

    protected WebApplicationException getPayPalException(Long contributionId, int httpCode, String responseBody) {
        return getWebApplicationException(Response.Status.BAD_REQUEST, "Response.PayPal.error", httpCode, responseBody,
                contributionId);
    }

    public static WebApplicationException getWebApplicationException(Response.Status httpCode, String messageId,
            Object... params) {
        String msg = TranslationProvider.getTranslation(messageId, params);
        WebApplicationException exception = new WebApplicationException(msg, httpCode);
        return exception;
    }

    public WebApplicationException getIllegalSeachException() {
        return getWebApplicationException(Response.Status.BAD_REQUEST, "Response.badQuery");
    }

}
