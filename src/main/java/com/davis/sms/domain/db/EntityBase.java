package com.davis.sms.domain.db;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

/**
 * Base JPA entity superclass, defining standard fields for every entity
 * 
 * @author Adam Davis
 * @param <E>
 *            current EntityBase subclass
 * @param <K>
 *            data type for the entity's generated primary key
 */
@MappedSuperclass
public class EntityBase<E extends EntityBase<E, K>, K>
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private K    primaryKey;

    @Version
    private long version;

    /**
     * Accessor for the current primary key
     * 
     * @return the key
     */
    public K getPrimaryKey()
    {
        return primaryKey;
    }

    /**
     * Accessor for the current record version
     * 
     * @return the version
     */
    public long getVersion()
    {
        return version;
    }
}
