package com.davis.sms.domain.db.student;

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

    @SuppressWarnings("unused")
    private StudentEntity()
    {
        ; // for JPA
    }

    public StudentEntity(String studentId)
    {
        this.studentId = studentId;
    }
}
