package com.davis.sms.controller.student;

import static java.util.Comparator.comparing;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.davis.sms.domain.view.student.StudentView;
import com.davis.sms.service.student.StudentConverter;
import com.davis.sms.service.student.StudentCrudService;

@RestController
public class StudentController
{
    private static final Comparator<StudentView> DEFAULT_DISPLAY_ORDER = comparing(
            StudentView::getLastName)
                    .thenComparing(comparing(StudentView::getFirstName))
                    .thenComparing(comparing(StudentView::getMiddleName));

    private StudentCrudService                   service;
    private StudentConverter                     converter;

    @Autowired
    public StudentController(StudentCrudService service, StudentConverter converter)
    {
        this.service = service;
        this.converter = converter;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/students")
    public List<StudentView> list(@RequestParam String criteria)
    {
        return service.search(criteria).stream()
                .map(converter::toView)
                .sorted(DEFAULT_DISPLAY_ORDER)
                .collect(Collectors.toList());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/students/{id}")
    public StudentView get(@PathVariable("id") String studentId)
    {
        return converter.toView(service.load(studentId));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/students")
    public StudentView add(@RequestBody StudentView contents)
    {
        return converter.toView(service.create(contents));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/students/{id}")
    public StudentView update(@PathVariable("id") String studentId,
            @RequestBody StudentView contents)
    {
        return converter.toView(service.update(studentId, contents));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/students/{id}")
    public void delete(@PathVariable("id") String studentId)
    {
        service.delete(studentId);
    }
}
