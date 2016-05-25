package com.davis.sms.repository.student;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.davis.sms.domain.db.student.StudentEntity;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long>
{
    public List<StudentEntity> findByLastNameAndFirstName(String lastName, String firstName);

    public StudentEntity findByStudentId(String id);
}
