package de.simone.backend;

import jakarta.enterprise.context.*;
import jakarta.transaction.*;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

@ApplicationScoped
public class FamiliesService  extends TAService<Family> {

  public FamiliesService () {
    super(Family.class);
  }

  @Override
  public Family get(Long id) throws WebApplicationException {
    return getImpl(id);
  }

  @Override
  @Transactional
  public Response delete(Long id) {
    return deleteImpl(id);
  }

  @Override
  @Transactional
  public Response save(Family entity) {
    return saveImpl(entity);
  }

  @Override
  public Response duplicate(Long id) {
    return duplicateImpl(id);
  }

}
