package com.davis.sms.service.student.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.davis.sms.domain.db.student.StudentEntity;
import com.davis.sms.domain.view.student.StudentView;

/**
 * Unit tests for the {@link StudentConverterImpl} class
 * 
 * @author Adam Davis
 */
public class StudentConverterImplTest
{
    /**
     * 
     */
    @Test
    public void testToView_NullParameter_Exception()
    {
        StudentConverterImpl converter = new StudentConverterImpl();

        try
        {
            converter.toView(null);
            fail();
        } catch (NullPointerException exception)
        {
            ; // expected
        }
    }

    /**
     * 
     */
    @Test
    public void testToView_ValidParameter_Matches()
    {
        StudentConverterImpl converter = new StudentConverterImpl();

        StudentEntity expected = StudentEntity.create("123")
                .setFirstName("H")
                .setMiddleName("John")
                .setLastName("Benjamin");

        StudentView actual = converter.toView(expected);

        assertEquals(expected.getFirstName(), actual.getFirstName());
        assertEquals(expected.getLastName(), actual.getLastName());
        assertEquals(expected.getMiddleName(), actual.getMiddleName());
        assertEquals(expected.getStudentId(), actual.getStudentId());
    }

    /**
     * 
     */
    @Test
    public void testPopulateEntity_NullEntity_Exception()
    {
        StudentConverterImpl converter = new StudentConverterImpl();

        try
        {
            converter.populateEntity(null, StudentView.create("123"));
            fail();
        } catch (NullPointerException exception)
        {
            ; // expected
        }
    }

    /**
     * 
     */
    @Test
    public void testPopulateEntity_NullView_Exception()
    {
        StudentConverterImpl converter = new StudentConverterImpl();

        try
        {
            converter.populateEntity(StudentEntity.create("123"), null);
            fail();
        } catch (NullPointerException exception)
        {
            ; // expected
        }
    }

    /**
     * 
     */
    @Test
    public void testPopulateEntity_ValidParameters_Matches()
    {
        StudentConverterImpl converter = new StudentConverterImpl();

        StudentEntity actual = StudentEntity.create("123");
        StudentView expected = StudentView.create("123")
                .setFirstName("H")
                .setMiddleName("John")
                .setLastName("Benjamin");

        converter.populateEntity(actual, expected);

        assertEquals(expected.getFirstName(), actual.getFirstName());
        assertEquals(expected.getLastName(), actual.getLastName());
        assertEquals(expected.getMiddleName(), actual.getMiddleName());
    }

    /**
     * 
     */
    @Test
    public void testToNewEntity_NullParameter_Exception()
    {
        StudentConverterImpl converter = new StudentConverterImpl();

        try
        {
            converter.toNewEntity(null);
            fail();
        } catch (NullPointerException exception)
        {
            ; // expected
        }
    }

    /**
     * 
     */
    @Test
    public void testToNewEntity_ValidParameter_Matches()
    {
        StudentConverterImpl converter = new StudentConverterImpl();

        StudentView expected = StudentView.create("123")
                .setFirstName("H")
                .setMiddleName("John")
                .setLastName("Benjamin");

        StudentEntity actual = converter.toNewEntity(expected);

        assertEquals(expected.getFirstName(), actual.getFirstName());
        assertEquals(expected.getLastName(), actual.getLastName());
        assertEquals(expected.getMiddleName(), actual.getMiddleName());
        assertEquals(expected.getStudentId(), actual.getStudentId());
    }
}
