package com.davis.sms.service.student.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.davis.sms.Application;
import com.davis.sms.domain.db.student.StudentEntity;
import com.davis.sms.domain.view.student.StudentView;
import com.davis.sms.repository.student.StudentRepository;
import com.davis.sms.service.student.StudentConverter;
import com.davis.sms.service.student.StudentCrudService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
@Transactional
public class StudentCrudServiceImplIntegrationTest
{
    @Autowired
    private StudentRepository          repository;

    @Autowired
    private StudentConverter           converter;

    @Autowired
    private StudentCrudService         service;

    private static final StudentEntity EXPECTED_ENTITY_ONE = StudentEntity.create("123")
            .setFirstName("H")
            .setMiddleName("John")
            .setLastName("Benjamin");
    private static final StudentEntity EXPECTED_ENTITY_TWO = StudentEntity.create("234")
            .setFirstName("James")
            .setMiddleName("Tiberius")
            .setLastName("Kirk");

    private void populateTable()
    {
        repository.save(StudentEntity.create(EXPECTED_ENTITY_ONE));
        repository.save(StudentEntity.create(EXPECTED_ENTITY_TWO));
        repository.flush();
    }

    @Test
    public void testList()
    {
        populateTable();

        List<StudentEntity> foundEntities = service.list();

        assertEquals(2, foundEntities.size());
        assertTrue(foundEntities.stream()
                .anyMatch(EXPECTED_ENTITY_ONE::equals));
        assertTrue(foundEntities.stream()
                .anyMatch(EXPECTED_ENTITY_TWO::equals));
    }

    @Test
    public void testSearch_NoCriteria_FindsAll()
    {
        populateTable();

        List<StudentEntity> foundEntities = service.search(null);
        assertEquals(2, foundEntities.size());
        assertTrue(foundEntities.stream()
                .anyMatch(EXPECTED_ENTITY_ONE::equals));
        assertTrue(foundEntities.stream()
                .anyMatch(EXPECTED_ENTITY_TWO::equals));

        foundEntities = service.search("");
        assertEquals(2, foundEntities.size());
        assertTrue(foundEntities.stream()
                .anyMatch(EXPECTED_ENTITY_ONE::equals));
        assertTrue(foundEntities.stream()
                .anyMatch(EXPECTED_ENTITY_TWO::equals));
    }

    @Test
    public void testSearch_MatchingCriteria_FindsOne()
    {
        populateTable();

        List<StudentEntity> foundEntities = service.search(EXPECTED_ENTITY_TWO.getLastName());

        assertEquals(1, foundEntities.size());
        assertEquals(EXPECTED_ENTITY_TWO, foundEntities.get(0));
    }

    @Test
    public void testSearch_NonMatchingCritiera_FindsNone()
    {
        populateTable();

        List<StudentEntity> foundEntities = service.search("NonExistant");
        assertEquals(0, foundEntities.size());
    }

    @Test
    public void testLoad_NullParameter_Exception()
    {
        try
        {
            service.load(null);
            fail();
        } catch (NullPointerException exception)
        {
            ; // expected
        }
    }

    @Test
    public void testLoad_NoMatch_Exception()
    {
        populateTable();

        try
        {
            service.load("NoMatch");
            fail();
        } catch (IllegalArgumentException exception)
        {
            ; // expected
        }
    }

    @Test
    public void testLoad_Match_ReturnsEntity()
    {
        populateTable();

        StudentEntity loadedEntity = service.load(EXPECTED_ENTITY_ONE.getStudentId());

        assertEquals(EXPECTED_ENTITY_ONE, loadedEntity);
    }

    public void testCreate_NullParameter_Exception()
    {
        try
        {
            service.create(null);
            fail();
        } catch (NullPointerException exception)
        {
            ; // expected
        }
    }

    @Test
    public void testCreate_DuplicateRecord_ViolatesConstraint()
    {
        populateTable();

        assertEquals(2, repository.count()); // sanity check

        try
        {
            service.create(converter.toView(EXPECTED_ENTITY_ONE));
            fail();
        } catch (DataIntegrityViolationException exception)
        {
            ; // expected
        }
    }

    @Test
    public void testCreate_AddsNewRecord()
    {
        assertEquals(0, repository.count()); // sanity check

        StudentEntity createdEntity = service.create(converter.toView(EXPECTED_ENTITY_TWO));

        assertEquals(1, repository.count());
        assertEquals(EXPECTED_ENTITY_TWO, createdEntity);
    }

    @Test
    public void testUpdate_NullId_Exception()
    {
        try
        {
            service.update(null, StudentView.create("123"));
            fail();
        } catch (NullPointerException exception)
        {
            ; // expected
        }
    }

    @Test
    public void testUpdate_NullView_Exception()
    {
        try
        {
            service.update("123", null);
            fail();
        } catch (NullPointerException exception)
        {
            ; // expected
        }
    }

    @Test
    public void testUpdate_NoMatch_Exception()
    {
        assertEquals(0, repository.count()); // sanity check

        try
        {
            service.update("123", StudentView.create("123").setLastName("White"));
            fail();
        } catch (IllegalArgumentException exception)
        {
            ; // expected
        }
    }

    @Test
    public void testUpdate_MatchingRecord_UpdatesCorrectly()
    {
        populateTable();

        assertEquals(2, repository.count()); // sanity check

        service.update(EXPECTED_ENTITY_TWO.getStudentId(),
                StudentView.create(EXPECTED_ENTITY_TWO.getStudentId()).setLastName("Dent")
                        .setFirstName("Harvey"));

        assertEquals(2, repository.count());
        StudentEntity reloadedEntity = repository
                .findByStudentId(EXPECTED_ENTITY_TWO.getStudentId());

        assertEquals("Dent", reloadedEntity.getLastName());
        assertEquals("Harvey", reloadedEntity.getFirstName());
        assertEquals(null, reloadedEntity.getMiddleName());
    }

    @Test
    public void testDelete_NullId_Exception()
    {
        try
        {
            service.delete(null);
            fail();
        } catch (NullPointerException exception)
        {
            ; // expected
        }
    }

    @Test
    public void testDelete_NoMatch_Exception()
    {
        assertEquals(0, repository.count()); // sanity check

        try
        {
            service.delete("NoMatch");
            fail();
        } catch (IllegalArgumentException exception)
        {
            ; // expected
        }
    }

    @Test
    public void testDelete_MatchingId_Deleted()
    {
        populateTable();

        assertEquals(2, repository.count()); // sanity check

        service.delete(EXPECTED_ENTITY_ONE.getStudentId());

        assertEquals(1, repository.count());
        assertEquals(null, repository.findByStudentId(EXPECTED_ENTITY_ONE.getStudentId()));
        assertEquals(EXPECTED_ENTITY_TWO,
                repository.findByStudentId(EXPECTED_ENTITY_TWO.getStudentId()));
    }
}
