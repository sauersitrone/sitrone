package de.simone.backend;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class DrugsService extends TAService<Drug> {

  public DrugsService() {
    super(Drug.class);
  }

  @Override
  public Drug get(Long id) throws WebApplicationException {
    return getImpl(id);
  }

  @Override
  @Transactional
  public Response delete(Long id) {
    return deleteImpl(id);
  }

  @Override
  @Transactional
  public Response save(Drug entity) {
    return saveImpl(entity);
  }

  @Override
  public Response duplicate(Long id) {
    return duplicateImpl(id);
 }

}
