package com.davis.sms.repository.student;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.davis.sms.domain.db.student.StudentEntity;

/**
 * Student CRUD repository. Handles communication with the database engine
 * 
 * @author Adam Davis
 */
@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long>
{
    /**
     * Finds all students whose last name fields starts with the given
     * paramater, ignoring case
     * 
     * @param lastName
     *            last name to filter by
     * @return all matching students
     */
    public List<StudentEntity> findByLastNameStartingWithIgnoreCase(String lastName);

    /**
     * Finds the existing student whose unique ID matches exactly the given
     * parameter
     * 
     * @param id
     *            exact ID
     * @return matching student, or null if there is no match
     */
    public StudentEntity findByStudentId(String id);
}
