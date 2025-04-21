package de.simone.backend;

import jakarta.enterprise.context.*;
import jakarta.transaction.*;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

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
