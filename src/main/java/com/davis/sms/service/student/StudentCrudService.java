package com.davis.sms.service.student;

import java.util.List;

import com.davis.sms.domain.db.student.StudentEntity;
import com.davis.sms.domain.view.student.StudentView;

public interface StudentCrudService
{
    public List<StudentEntity> list();

    public List<StudentEntity> search(String criteria);

    public StudentEntity load(String studentId);

    public StudentEntity create(StudentView contents);

    public StudentEntity update(String studentId, StudentView contents);

    public void delete(String studentId);
}
