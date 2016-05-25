package com.davis.sms.domain.db;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
public class EntityBase<E extends EntityBase<E, K>, K>
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private K    primaryKey;

    @Version
    private long version;
}
