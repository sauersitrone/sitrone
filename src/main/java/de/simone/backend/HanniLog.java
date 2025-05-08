package de.simone.backend;

import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;

@Entity
@Table(name = "HanniLogs", indexes = {
        @Index(name = "HanniLogs_secondaryKey", columnList = "secondaryKey") })
public class HanniLog extends TAEntity {

    public String messageId;

}
