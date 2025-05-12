package de.simone.backend;

import jakarta.enterprise.context.*;
import jakarta.transaction.*;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

@ApplicationScoped
public class AdultsService extends TAService<Adult> {

    public AdultsService() {
        super(Adult.class);
    }

    @Override
    public Adult get(Long id) throws WebApplicationException {
        return getImpl(id);
    }

    @Override
    @Transactional
    public Response delete(Long id) {
        return deleteImpl(id);
    }

    @Override
    @Transactional
    public Response save(Adult entity) {
        return saveImpl(entity);
    }

    @Override
    public Response duplicate(Long id) {
        throw new UnsupportedOperationException("Unimplemented method 'duplicate'");
    }
}
