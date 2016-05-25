package com.davis.sms.domain.db.student;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import com.davis.sms.domain.db.EntityBase;

@Entity
@Table(name = "Student")
public class StudentEntity extends EntityBase<StudentEntity, Long>
{
    @NaturalId
    private String studentId;

    private String firstName;
    private String lastName;
    private String middleName;

    private StudentEntity()
    {
        ; // for JPA
    }

    private StudentEntity setStudentId(String studentId)
    {
        this.studentId = studentId;
        return this;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public StudentEntity setFirstName(String firstName)
    {
        this.firstName = firstName;

        return this;
    }

    public String getLastName()
    {
        return lastName;
    }

    public StudentEntity setLastName(String lastName)
    {
        this.lastName = lastName;

        return this;
    }

    public String getMiddleName()
    {
        return middleName;
    }

    public StudentEntity setMiddleName(String middleName)
    {
        this.middleName = middleName;

        return this;
    }

    public String getStudentId()
    {
        return studentId;
    }

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

    @Override
    public int hashCode()
    {
        return Objects.hash(getStudentId());
    }

    @Override
    public String toString()
    {
        return getStudentId();
    }

    public static StudentEntity create(String studentId)
    {
        return new StudentEntity().setStudentId(studentId);
    }

    public static StudentEntity create(StudentEntity source)
    {
        return create(source.getStudentId())
                .setFirstName(source.getFirstName())
                .setMiddleName(source.getMiddleName())
                .setLastName(source.getLastName());
    }
}
