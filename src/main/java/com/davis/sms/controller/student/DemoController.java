package com.davis.sms.controller.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.davis.sms.service.student.StudentCrudService;

@RestController
public class DemoController
{
    private StudentCrudService service;

    @Autowired
    public DemoController(StudentCrudService service)
    {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/demo")
    public void loadDemoContents()
    {
        service.reloadDemoData();
    }
}
