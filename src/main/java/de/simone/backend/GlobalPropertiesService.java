
package de.simone.backend;

import jakarta.enterprise.context.*;
import jakarta.transaction.*;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

@ApplicationScoped
public class GlobalPropertiesService extends TAService<GlobalProperty> {

    public GlobalPropertiesService() {
        super(GlobalProperty.class);
    }

    @Override
    public GlobalProperty get(Long id) throws WebApplicationException {
        return getImpl(id);
    }

    @Override
    @Transactional
    public Response delete(Long id) {
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    @Transactional
    public Response save(GlobalProperty entity) {
        return saveImpl(entity);
    }

    @Override
    @Transactional
    public Response duplicate(Long id) {
        throw new UnsupportedOperationException("Unimplemented method 'duplicate'");
    }

}