package com.davis.sms.domain.view.student;

/**
 * View object for a Student JPA entity. Decouples the REST communications from
 * the current database structure
 * 
 * @author Adam Davis
 */
public class StudentView
{
    /**
     * Unique student ID
     */
    private String studentId;

    /**
     * Student last name
     */
    private String lastName;

    /**
     * Student first name
     */
    private String firstName;

    /**
     * Student middle name
     */
    private String middleName;

    private StudentView()
    {
        ; // needed for deserialization
    }

    /**
     * Accessor for the student ID
     * 
     * @return the ID
     */
    public String getStudentId()
    {
        return studentId;
    }

    /**
     * Mutator for the student ID. Since this ID is immutable post-creation,
     * this method is private
     * 
     * @param studentId
     *            the ID
     * @return the current object
     */
    private StudentView setStudentId(String studentId)
    {
        this.studentId = studentId;

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
    public StudentView setLastName(String lastName)
    {
        this.lastName = lastName;

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
    public StudentView setFirstName(String firstName)
    {
        this.firstName = firstName;

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
    public StudentView setMiddleName(String middleName)
    {
        this.middleName = middleName;

        return this;
    }

    /**
     * Prints the {@link #getStudentId()}
     */
    @Override
    public String toString()
    {
        return getStudentId();
    }

    /**
     * Creates a new view object with the given unique ID
     * 
     * @param studentId
     *            the unique ID
     * @return newly created object
     */
    public static StudentView create(String studentId)
    {
        return new StudentView().setStudentId(studentId);
    }
}
