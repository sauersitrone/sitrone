package de.simone.backend;

import de.simone.SecurityUtils;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
public class AdultsService extends TAService<Adult> {

  public AdultsService() {
    super(Adult.class);
  }

  @Override
  public List<Adult> listAll() {
    User user = SecurityUtils.getLoggedUser();
    return super.list("carerId = :carerId", Parameters.with("carerId", user.id));
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
    // the carer is the user, who created this adult
    entity.carerId = SecurityUtils.getLoggedUser().id;
    return saveImpl(entity);
  }

  @Override
  public Response duplicate(Long id) {
    throw new UnsupportedOperationException("Unimplemented method 'duplicate'");
  }
}
