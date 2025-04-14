package de.simone.backend.restop;

import java.text.MessageFormat;

import jakarta.ws.rs.core.UriInfo;


public class LinksBuilder {

    private final UriInfo uriInfo;
    private int limit;
    private int offset;
    private long count;

    private LinksBuilder(UriInfo uriInfo) {
        this.uriInfo = uriInfo;
    }

    public static LinksBuilder withBasePath(UriInfo uriInfo) {
        return new LinksBuilder(uriInfo);
    }

    public LinksBuilder andLimit(int limit) {
        this.limit = limit;
        return this;
    }

    public LinksBuilder andOffset(int offset) {
        this.offset = offset;
        return this;
    }

    public LinksBuilder andCount(long count) {
        this.count = count;
        return this;
    }

    public Links build() {
        final StringBuilder queryBuilder = new StringBuilder();
        uriInfo.getQueryParameters(true).forEach((key, values) -> {
            if (!(FilterBuilder.QUERY_PARAM_LIMIT.equals(key) ||
                    FilterBuilder.QUERY_PARAM_PAGE.equals(key))) {
                values.forEach(value -> {
                    String[] valori = value.split(",");
                    for (String s : valori) {
                        if (queryBuilder.length() != 0)
                            queryBuilder.append("&");
                        queryBuilder.append(key);
                        queryBuilder.append("=");
                        queryBuilder.append(s);
                    }
                });
            }
        });
        String otherQueryParams = queryBuilder.toString();
        String basePath = uriInfo.getPath();
        String first = buildLink(basePath, limit, 0, otherQueryParams);
        String previous = null;
        if (offset >= limit)
            previous = buildLink(basePath, limit, ((long) offset) - limit, otherQueryParams);
        String next = null;
        if (offset + limit < count)
            next = buildLink(basePath, limit, ((long) offset) + limit, otherQueryParams);
        String last = buildLink(basePath, limit, count / limit * limit, otherQueryParams);
        return new Links(first, previous, next, last);
    }

    private static String buildLink(String baseUrl, int limit, long offset, String otherQueryParams) {
        return MessageFormat.format("{0}?{1}={2}&{3}={4}{5}",
                baseUrl,
                FilterBuilder.QUERY_PARAM_LIMIT,
                String.valueOf(limit),
                FilterBuilder.QUERY_PARAM_PAGE,
                String.valueOf(offset),
                otherQueryParams.isEmpty() ? "" : "&" + otherQueryParams);
    }
}
