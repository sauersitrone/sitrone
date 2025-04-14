package de.simone.backend;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class HistoriesService extends TAService<History> {

  public HistoriesService() {
    super(History.class);
  }

  @Override
  public History get(Long id) throws WebApplicationException {
    return getImpl(id);
  }

  @Override
  @Transactional
  public Response delete(Long id) {
    return deleteImpl(id);
  }

  @Override
  @Transactional
  public Response save(History entity) {
    return saveImpl(entity);
  }

  @Override
  public Response duplicate(Long id) {
    throw new UnsupportedOperationException("Unimplemented method 'duplicate'");
  }

}
