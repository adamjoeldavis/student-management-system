package com.davis.sms.service.student.impl;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.davis.sms.domain.db.student.StudentEntity;
import com.davis.sms.domain.view.student.StudentView;
import com.davis.sms.service.student.StudentConverter;

@Service
public class StudentConverterImpl implements StudentConverter
{
    private Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public StudentView toView(StudentEntity entity) throws NullPointerException
    {
        log.debug("toView({})", entity);

        Objects.requireNonNull(entity);

        return StudentView.create(entity.getStudentId())
                .setFirstName(entity.getFirstName())
                .setLastName(entity.getLastName())
                .setMiddleName(entity.getMiddleName());
    }

    @Override
    public void populateEntity(StudentEntity entity, StudentView view) throws NullPointerException
    {
        log.debug("populateEntity({}, {})", entity, view);

        Objects.requireNonNull(entity);
        Objects.requireNonNull(view);

        entity.setFirstName(view.getFirstName())
                .setLastName(view.getLastName())
                .setMiddleName(view.getMiddleName());
    }

    @Override
    public StudentEntity toNewEntity(StudentView view) throws NullPointerException
    {
        log.debug("toNewEntity({})", view);

        Objects.requireNonNull(view);

        StudentEntity entity = StudentEntity.create(view.getStudentId());
        populateEntity(entity, view);

        return entity;
    }
}
