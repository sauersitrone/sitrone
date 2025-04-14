package de.simone.backend;

import de.simone.SecurityUtils;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
public class TamagotchiesService extends TAService<Tamagotchi> {

  public TamagotchiesService() {
    super(Tamagotchi.class);
  }

  @Override
  public Tamagotchi get(Long id) throws WebApplicationException {
    return getImpl(id);
  }

  @Override
  @Transactional
  public Response delete(Long id) {
    return deleteImpl(id);
  }

  @Override
  @Transactional
  public Response save(Tamagotchi entity) {
    return saveImpl(entity);
  }

  @Override
  public Response duplicate(Long id) {
    throw new UnsupportedOperationException("Unimplemented method 'duplicate'");
  }
}
