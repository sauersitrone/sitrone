package de.simone.backend;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class EndPointsService extends TAService<EndPoint> {

    public EndPointsService() {
        super(EndPoint.class);
    }

    @Override
    public EndPoint get(Long id) throws WebApplicationException {
        return getImpl(id);

    }

    @Transactional
    public Response delete(Long id) {
        return deleteImpl(id);
    }

    @Transactional
    public Response save(EndPoint entity) {
        return saveImpl(entity);
    }

    @Override
    @Transactional
    public Response duplicate(Long id) {
        return duplicateImpl(id);
    }

}
