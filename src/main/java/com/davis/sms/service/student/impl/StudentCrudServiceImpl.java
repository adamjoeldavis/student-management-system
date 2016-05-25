package com.davis.sms.service.student.impl;

import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.davis.sms.domain.db.student.StudentEntity;
import com.davis.sms.domain.view.student.StudentView;
import com.davis.sms.repository.student.StudentRepository;
import com.davis.sms.service.student.StudentConverter;
import com.davis.sms.service.student.StudentCrudService;

@Service
public class StudentCrudServiceImpl implements StudentCrudService
{
    private Logger            log = LoggerFactory.getLogger(getClass());

    private StudentRepository repository;
    private StudentConverter  converter;

    @Autowired
    public StudentCrudServiceImpl(StudentRepository repository, StudentConverter converter)
    {
        Objects.requireNonNull(repository);
        Objects.requireNonNull(converter);

        this.repository = repository;
        this.converter = converter;
    }

    @Override
    public List<StudentEntity> list()
    {
        log.debug("list()");

        return repository.findAll();
    }

    @Override
    public List<StudentEntity> search(String criteria)
    {
        log.debug("search({})", criteria);

        if (criteria == null || criteria.trim().isEmpty())
        {
            return list();
        }

        return repository.findByLastName(criteria);
    }

    @Override
    public StudentEntity load(String studentId)
            throws NullPointerException, IllegalArgumentException
    {
        log.debug("load({})", studentId);

        Objects.requireNonNull(studentId);

        StudentEntity existingEntity = repository.findByStudentId(studentId);
        if (existingEntity == null)
        {
            throw new IllegalArgumentException(
                    "No matching entity for ID = [" + studentId + "]");
        }

        return existingEntity;
    }

    @Override
    public StudentEntity create(StudentView contents) throws NullPointerException
    {
        log.debug("create({})", contents);

        return repository.saveAndFlush(converter.toNewEntity(contents));
    }

    @Override
    public StudentEntity update(String studentId, StudentView contents)
            throws NullPointerException, IllegalArgumentException
    {
        log.debug("update({}, {})", studentId, contents);

        Objects.requireNonNull(contents);

        StudentEntity existingEntity = load(studentId);

        converter.populateEntity(existingEntity, contents);

        return repository.saveAndFlush(existingEntity);
    }

    @Override
    public void delete(String studentId) throws NullPointerException, IllegalArgumentException
    {
        log.debug("delete({})", studentId);

        repository.delete(load(studentId));
    }
}
