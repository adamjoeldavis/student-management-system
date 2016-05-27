package com.davis.sms.domain.db.student;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import com.davis.sms.domain.db.EntityBase;

/**
 * Student database entity
 * 
 * @author Adam Davis
 */
@Entity
@Table(name = "Student")
public class StudentEntity extends EntityBase<StudentEntity, Long>
{
    /**
     * Student ID. This is a unique natural ID for this record
     */
    @NaturalId
    private String studentId;

    /**
     * Student's first name
     */
    private String firstName;

    /**
     * Student's last name
     */
    private String lastName;

    /**
     * Student's middle name
     */
    private String middleName;

    /**
     * Private constructor. Users should create an instance of this class via
     * the static creator methods
     */
    private StudentEntity()
    {
    }

    /**
     * Mutator for the student ID. Once set, this value should be immutable.
     * Therefore this method is private.
     * 
     * @param studentId
     *            the unique student ID
     * @return the current object
     */
    private StudentEntity setStudentId(String studentId)
    {
        this.studentId = studentId;
        return this;
    }

    /**
     * Accessor for the first name
     * 
     * @return the first name
     */
    public String getFirstName()
    {
        return firstName;
    }

    /**
     * Mutator for the first name
     * 
     * @param firstName
     *            new first name
     * @return the current object
     */
    public StudentEntity setFirstName(String firstName)
    {
        this.firstName = firstName;

        return this;
    }

    /**
     * Accessor for the last name
     * 
     * @return the last name
     */
    public String getLastName()
    {
        return lastName;
    }

    /**
     * Mutator for the last name
     * 
     * @param lastName
     *            new last name
     * @return the current object
     */
    public StudentEntity setLastName(String lastName)
    {
        this.lastName = lastName;

        return this;
    }

    /**
     * Accessor for the middle name
     * 
     * @return the middle name
     */
    public String getMiddleName()
    {
        return middleName;
    }

    /**
     * Mutator for the middle name
     * 
     * @param middleName
     *            new middle name
     * @return the current object
     */
    public StudentEntity setMiddleName(String middleName)
    {
        this.middleName = middleName;

        return this;
    }

    /**
     * Accessor for the student's ID
     * 
     * @return the ID
     */
    public String getStudentId()
    {
        return studentId;
    }

    /**
     * Equality is based on the {@link #getStudentId()} field
     */
    @Override
    public boolean equals(Object other)
    {
        if (this == other)
        {
            return true;
        }

        if (other == null || getClass() != other.getClass())
        {
            return false;
        }

        return Objects.equals(this.getStudentId(), ((StudentEntity) other).getStudentId());
    }

    /**
     * Hash code is based on the {@link #getStudentId()} field
     */
    @Override
    public int hashCode()
    {
        return Objects.hash(getStudentId());
    }

    /**
     * Returns the {@link #getStudentId()}
     */
    @Override
    public String toString()
    {
        return getStudentId();
    }

    /**
     * Create a new entity with the given unique ID
     * 
     * @param studentId
     *            the unique ID
     * @return newly created student
     */
    public static StudentEntity create(String studentId)
    {
        return new StudentEntity().setStudentId(studentId);
    }

    /**
     * Create a new entity that is an exact copy of the given entity
     * 
     * @param source
     *            entity to copy
     * @return the copy
     */
    public static StudentEntity create(StudentEntity source)
    {
        return create(source.getStudentId())
                .setFirstName(source.getFirstName())
                .setMiddleName(source.getMiddleName())
                .setLastName(source.getLastName());
    }
}
