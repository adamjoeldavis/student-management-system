package com.davis.sms.domain.view.student;

public class StudentView
{
    private String studentId;
    private String lastName;
    private String firstName;
    private String middleName;

    public String getStudentId()
    {
        return studentId;
    }

    private StudentView setStudentId(String studentId)
    {
        this.studentId = studentId;

        return this;
    }

    public String getLastName()
    {
        return lastName;
    }

    public StudentView setLastName(String lastName)
    {
        this.lastName = lastName;

        return this;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public StudentView setFirstName(String firstName)
    {
        this.firstName = firstName;

        return this;
    }

    public String getMiddleName()
    {
        return middleName;
    }

    public StudentView setMiddleName(String middleName)
    {
        this.middleName = middleName;

        return this;
    }

    public static StudentView create(String studentId)
    {
        return new StudentView().setStudentId(studentId);
    }
}
