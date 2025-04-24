package de.simone.backend;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.exception.ExceptionUtils;

import com.ibm.icu.text.MessageFormat;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

import de.simone.SecurityUtils;
import de.simone.TranslationProvider;
import de.simone.backend.restop.Filter;
import de.simone.backend.restop.FilterBuilder;
import de.simone.backend.restop.Links;
import de.simone.backend.restop.LinksBuilder;
import de.simone.backend.restop.Meta;
import de.simone.backend.restop.Pagination;
import de.simone.backend.restop.SortBuilder;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.logging.Log;
import io.quarkus.panache.common.Parameters;
import io.quarkus.panache.common.Sort;
import io.quarkus.security.runtime.SecurityIdentityAssociation;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

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
        return list( null, sort, null);
    }

    public List<E> list(String query, Sort sort, Parameters parameters) {
        return list(0L, query, sort, parameters);
    }

    public List<E> list(String query, Parameters parameters) {
        return list(0L, query, null, parameters);
    }

    /**
     * service endpoint equivalent to {@link PanacheEntity#find(String query, Sort
     * sort, Map<String, Object> params)}
     * 
     * @param owner      - sub list owner to retrive
     * @param query      - the query
     * @param sort       - the sort
     * @param parameters - the query parameters
     * @return the list
     */
    protected List<E> list(Long owner, String query, Sort sort, Parameters parameters) {
        ListDataProvider<E> dataProvider = list(owner, query, sort, parameters, -1, -1);
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

        Long ownerId = 0L;

        ListDataProvider<E> dataProvider = list(ownerId, query, sort, parameters, limit, page);
        dataProvider.sortBy = request.sortBy;
        dataProvider.partialWhere = query;
        return dataProvider;
    }

    @SuppressWarnings("unchecked")
    private ListDataProvider<E> list(Long owner, String query, Sort sort, Parameters parameters, int limit,
            int page) {
        ListDataProvider<E> dataProvider = new ListDataProvider<>(null);
        try {
            String baseQuery = "";
            Map<String, Object> baseParms = new HashMap<>();

            // ownership
            Long owner2 = owner;
            // redirect ownership for know entities. this files are read direct from
            // Sitrone
            // if (clazz.isAssignableFrom(Country.class) || clazz.isAssignableFrom(Currency.class)
            //         || clazz.isAssignableFrom(EndPoint.class)) {
            //     owner2 = Account.getGoodDevId();
            // }
            // baseParms.put("ownerId", owner2);
            // baseQuery = "ownerId = :ownerId";

            // append internal query to original query
            if (query != null) {
                baseQuery += query;
                // baseQuery += " AND " + query;
                baseParms.putAll(parameters.map());
            }

            // access from Sitrone platform don't need filter by ownerId or isLive
            if (owner == null) {
                baseQuery = query;
                baseParms.clear();
            }

            PanacheQuery<E> panacheQuery = null;
            if (sort == null) {
                panacheQuery = (PanacheQuery<E>) clazz.getMethod("find", String.class, Map.class).invoke(null,
                        baseQuery, baseParms);
            } else {
                panacheQuery = (PanacheQuery<E>) clazz.getMethod("find", String.class, Sort.class, Map.class)
                        .invoke(null, baseQuery, sort, baseParms);
            }
            // System.out.println("baseQuery " + baseQuery);
            if (sort != null) {
                String cols = sort.getColumns().stream().map(c -> c.getName()).collect(Collectors.joining(", "));
                // System.out.println("sort " + cols);
            }
            // System.out.println("baseParms " + baseParms);

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

    /**
     * Provied supor for:
     * <ul>
     * <li>read one: GET request to /fruit/{id} endpoint with a single Fruit result
     * read many "first
     * page": GET request to /fruit endpoint with an ordered (by ID) list of Fruit
     * results using
     * default (and opinionated) values for limit (i.e. 25) and offset (i.e. 0)
     * parameters
     *
     * <li>read many "n-th page":GET request to /fruit?limit=10&offset=20 endpoint
     * with an ordered
     * (by ID) list of (up to 10) Fruit results starting for the 20th element
     *
     * <li>read many with sorting:GET request to /fruit?sort=name:Ascending endpoint
     * with an ordered
     * by Fruit's name field ascending list of (up to 25) Fruit results starting for
     * first element.
     * Note that sorting works on multiple fields using the syntax
     * sort=field1:asc,field2:desc. a,
     * asc and ascending can be used for ascending direction (it's the default and
     * fallback
     * direction) and d, desc and descending for descending direction
     *
     * <li>read many with "equals" filter:GET request to /fruit?name=Banana endpoint
     * with an ordered
     * (by ID) list of (up to 25) Fruit results whose name field value is Banana
     *
     * <li>read many with "in" filter:GET request to
     * /fruit?name=Banana&name=Apple&name=Kiwi
     * endpoint with an ordered (by ID) list of (up to 25) Fruit results whose name
     * field value is
     * Banana or Apple or Kiwi
     * </ul>
     *
     * @param limit  - number of elements per page
     * @param page   - page number
     * @param sortBy - secuence of fieldName:order
     * @return {@link Pagination} with all in
     * @throws Exception
     */
    protected Pagination<E> readPageImpl(int limit, int page, String sortBy) {
        try {
            User user = SecurityUtils.getLoggedUser();
            String owner = "ownerId = '" + user.id + "'";
            Sort sort = SortBuilder.build(sortBy);
            Filter filter = FilterBuilder.withUriInfo(uriInfo).andWhere(owner).build();
            @SuppressWarnings("unchecked")
            PanacheQuery<E> query = (PanacheQuery<E>) clazz.getMethod("find", String.class, Sort.class, Map.class)
                    .invoke(null, filter.getQuery(), sort, filter.getParameters());
            long count = query.count();
            Meta meta = new Meta(count, limit, page);
            Links links = LinksBuilder.withBasePath(uriInfo).andLimit(limit).andOffset(page)
                    .andCount(count).build();
            List<E> list = query.page(page, limit).list();
            Pagination<E> pagination = new Pagination<>(meta, links, list);
            return pagination;
        } catch (Exception e) {
            Log.error("", e);
        }
        return null;
    }

    // public List<GlobalSearchElement> elasticSearch(HttpHeaders httpHeaders, String query)
    //         throws Exception {
    //     this.httpHeaders = httpHeaders;
    //     List<String> fields = new ArrayList<>(getElasticFields(clazz).keySet());

    //     if (query == null || query.trim().isEmpty())
    //         throw getIllegalSeachException();

    //     String query2 = "{'query_string' : {'query' : '" + query + "'}}";
    //     // System.out.println(query2);

    //     ElasticsearchSearchRequestTransformer transformer = new ElasticsearchSearchRequestTransformer() {
    //         @Override
    //         public void transform(ElasticsearchSearchRequestTransformerContext context) {
    //             // Map<String, String> parameters = context.parametersMap();
    //             // parameters.put( "search_type", "dfs_query_then_fetch" );
    //             // System.out.println(parameters.toString());
    //             JsonObject body = context.body();
    //             JsonObject highlight = new JsonObject();
    //             JsonObject fields2 = new JsonObject();
    //             highlight.add("fields", fields2);
    //             for (String field : fields) {
    //                 JsonObject content = new JsonObject();
    //                 content.addProperty("fragment_size", 100);
    //                 content.addProperty("number_of_fragments", 1);
    //                 fields2.add(field, content);
    //             }
    //             body.add("highlight", highlight);
    //             System.out.println(body.toString());
    //         }
    //     };
    //     ElasticsearchSearchQueryOptionsStep<? extends TAEntity, SearchLoadingOptionsStep> step = searchSession
    //             .search(clazz).extension(ElasticsearchExtension.get())
    //             .where(f -> f.fromJson(query2)).requestTransformer(transformer);
    //     ElasticsearchSearchResult<? extends TAEntity> result = step.fetch(10);
    //     List<? extends TAEntity> list = result.hits();

    //     // String jsonText = result.responseBody().toString();
    //     JsonObject json = result.responseBody();
    //     JsonArray array = json.get("hits").getAsJsonObject().getAsJsonArray("hits");
    //     // System.out.println(array.toString());
    //     List<GlobalSearchElement> list2 = new ArrayList<>();
    //     // the list inside json response hat the same order as the entities
    //     for (int ii = 0; ii < list.size(); ii++) {
    //         TAEntity entity = (TAEntity) list.get(ii);
    //         String jsonText = array.get(ii).toString();
    //         // System.out.println("-- " + jsonText);
    //         Map<String, String> fieldText = new TreeMap<>();
    //         for (String field : fields) {
    //             String fld = "\"" + field + "\"";
    //             int i = jsonText.indexOf(fld);
    //             if (i > 0) {
    //                 int a = jsonText.indexOf("[", i);
    //                 int b = jsonText.indexOf("]", a);
    //                 String text = jsonText.substring(a + 2, b - 1);
    //                 fieldText.put(field, text);
    //             }
    //         }
    //         list2.add(new GlobalSearchElement(entity, fieldText));
    //     }
    //     return list2;
    // }

    /**
     * return a Map of all fields marked as {@link FullTextField},
     * {@link GenericField} or
     * {@link KeywordField} and the correspondig java object type
     *
     * @return map with field name and java type
     */
    // public Map<String, String> getElasticFields() {
    //     Map<String, String> map = getElasticFields(clazz);
    //     // only for root class, add manualy created and updated fields
    //     map.put("created_at", LocalDateTime.class.getSimpleName());
    //     map.put("updated_at", LocalDateTime.class.getSimpleName());
    //     return map;
    // }

    // private Map<String, String> getElasticFields(Class<?> clazz2) {
    //     Map<String, String> map = new HashMap<>();
    //     Field[] fields = clazz2.getDeclaredFields();
    //     for (Field field : fields) {
    //         field.setAccessible(true);
    //         if (field.getAnnotation(FullTextField.class) != null
    //                 || field.getAnnotation(GenericField.class) != null
    //                 || field.getAnnotation(KeywordField.class) != null) {
    //             map.put(field.getName(), field.getType().getSimpleName());
    //         }
    //         if (field.getAnnotation(IndexedEmbedded.class) != null) {
    //             Map<String, String> map2 = new HashMap<>();
    //             // only gooddev entities
    //             if (field.getType().getName().contains("gooddev.")) {
    //                 map2.putAll(getElasticFields(field.getType()));
    //             }
    //             if (field.getType().equals(List.class) || field.getType().equals(Set.class)) {
    //                 String typeName = field.getGenericType().getTypeName();
    //                 // only list with gooddev entities
    //                 if (typeName.contains("gooddev.")) {
    //                     int a = typeName.indexOf("<");
    //                     int b = typeName.indexOf(">");
    //                     String className = typeName.substring(a + 1, b);
    //                     try {
    //                         Class<?> class1 = Class.forName(className);
    //                         map2.putAll(getElasticFields(class1));
    //                     } catch (Exception e) {
    //                         Log.error("", e);
    //                     }
    //                 }
    //             }
    //             for (Map.Entry<String, String> ele : map2.entrySet()) {
    //                 map.put(field.getName() + "." + ele.getKey(), ele.getValue());
    //             }
    //         }
    //     }
    //     return map;
    // }

}
