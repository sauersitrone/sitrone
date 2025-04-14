package de.simone.backend;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Emojies")
public class Emoji extends TAEntity {

    public String name;
    public String face;
    public String unicode;
    public int level;
    public String emotion;

}
