package com.davis.sms.service.student;

import com.davis.sms.domain.db.student.StudentEntity;
import com.davis.sms.domain.view.student.StudentView;

public interface StudentConverter
{
    public void populateEntity(StudentEntity entity, StudentView view);

    public StudentEntity toNewEntity(StudentView view);

    public StudentView toView(StudentEntity entity);
}
