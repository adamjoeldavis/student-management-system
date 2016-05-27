package com.davis.sms.service.student;

import com.davis.sms.domain.db.student.StudentEntity;
import com.davis.sms.domain.view.student.StudentView;

/**
 * Handles conversions between the view ( {@link StudentView} ) and db (
 * {@link StudentEntity} ) versions of a Student record
 * 
 * @author Adam Davis
 */
public interface StudentConverter
{
    /**
     * Populates the given entity with values from the given view. Note that
     * this will not populate the unique student ID field -
     * {@link StudentEntity#getStudentId()}
     * 
     * @param entity
     *            the entity to populate
     * @param view
     *            the source of new values
     * @throws NullPointerException
     *             if either the entity or the view are null
     */
    public void populateEntity(StudentEntity entity, StudentView view) throws NullPointerException;

    /**
     * Converts the given view into a new matching entity object. Note that this
     * does copy the unique student ID - {@link StudentEntity#getStudentId()}
     * 
     * @param view
     *            source of the new entity
     * @return newly created entity
     * @throws NullPointerException
     *             if the given view is null
     */
    public StudentEntity toNewEntity(StudentView view) throws NullPointerException;

    /**
     * Converts the given entity into a new matching view object.
     * 
     * @param entity
     *            source for the view
     * @return newly created view
     * @throws NullPointerException
     *             if the given entity is null
     */
    public StudentView toView(StudentEntity entity) throws NullPointerException;
}
