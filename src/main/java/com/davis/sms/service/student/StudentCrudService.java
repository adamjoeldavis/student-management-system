package com.davis.sms.service.student;

import java.util.List;

import com.davis.sms.domain.db.student.StudentEntity;
import com.davis.sms.domain.view.student.StudentView;

/**
 * Handles CRUD operations for a {@link StudentEntity} object
 * 
 * @author Adam Davis
 */
public interface StudentCrudService
{
    /**
     * Lists all entities in the database
     * 
     * @return all student entities
     */
    public List<StudentEntity> list();

    /**
     * Lists all entities in the database whose last name starts with the given
     * criteria, ignoring case
     * 
     * @param criteria
     *            last name criteria
     * @return matching students
     */
    public List<StudentEntity> search(String criteria);

    /**
     * Loads the student with the given unique ID from the database.
     * 
     * @param studentId
     *            unique ID to load
     * @return loaded entity
     * @see StudentEntity#getStudentId()
     * @throws NullPointerException
     *             if the given ID is null
     * @throws IllegalArgumentException
     *             if there is no matching student in the database
     */
    public StudentEntity load(String studentId)
            throws NullPointerException, IllegalArgumentException;

    /**
     * Creates a new entity with the given contents and saves it to the database
     * 
     * @param contents
     *            contents to save
     * @return newly created entity
     * @throws NullPointerException
     *             if the given contents are null
     */
    public StudentEntity create(StudentView contents) throws NullPointerException;

    /**
     * Updates the entity with the given unique ID with the given contents
     * 
     * @param studentId
     *            unique ID to update
     * @param contents
     *            contents to update with
     * @return updated entity
     * @see StudentEntity#getStudentId()
     * @throws NullPointerException
     *             if either of the given parameters are null
     * @throws IllegalArgumentException
     *             if there is no existing student with the given ID
     */
    public StudentEntity update(String studentId, StudentView contents)
            throws NullPointerException, IllegalArgumentException;

    /**
     * Deletes the student with the given unique ID from the database
     * 
     * @param studentId
     *            unique ID of the student to delete
     * @see StudentEntity#getStudentId()
     * @throws NullPointerException
     *             if the given studentId is null
     * @throws IllegalArgumentException
     *             if there is no existing student with the given ID
     */
    public void delete(String studentId) throws NullPointerException, IllegalArgumentException;

    /**
     * Resets the state of the Student table to contain a standard set of "demo"
     * records.
     */
    public void reloadDemoData();
}
