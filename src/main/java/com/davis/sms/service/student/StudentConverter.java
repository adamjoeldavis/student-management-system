package com.davis.sms.service.student;

import com.davis.sms.domain.db.student.StudentEntity;
import com.davis.sms.domain.view.student.StudentView;

public interface StudentConverter
{
    public void populateEntity(StudentEntity entity, StudentView view) throws NullPointerException;

    public StudentEntity toNewEntity(StudentView view) throws NullPointerException;

    public StudentView toView(StudentEntity entity) throws NullPointerException;
}
