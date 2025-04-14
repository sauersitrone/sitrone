package de.simone.backend;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class QueriesService extends TAService<Query> {

    public QueriesService() {
        super(Query.class);
    }

    @Override
    public Query get(Long id) throws WebApplicationException {
        return getImpl(id);

    }

    @Override
    @Transactional
    public Response delete(Long id) {
        return deleteImpl(id);
    }

    @Override
    @Transactional
    public Response save(Query entity) {
        return saveImpl(entity);
    }

    @Override
    @Transactional
    public Response duplicate(Long id) {
        return duplicateImpl(id);
    }
}
