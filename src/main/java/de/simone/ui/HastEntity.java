package de.simone.ui;

import de.simone.backend.TAEntity;

public interface HastEntity<E extends TAEntity> {

    abstract E getEntity();

    abstract void setEntity(E entity);

    abstract boolean isValid();

}
