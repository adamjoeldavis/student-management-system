package com.davis.sms.controller.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.davis.sms.service.student.StudentCrudService;

/**
 * Rest controller that enables auto-population of a set of "demo" records
 * 
 * @author Adam Davis
 */
@RestController
public class DemoController
{
    /**
     * Auto-wired service
     */
    private StudentCrudService service;

    /**
     * @param service
     */
    @Autowired
    public DemoController(StudentCrudService service)
    {
        this.service = service;
    }

    /**
     * Loads demo contents, resetting the state of the database.
     */
    @RequestMapping(method = RequestMethod.POST, path = "/demo")
    public void loadDemoContents()
    {
        service.reloadDemoData();
    }
}
