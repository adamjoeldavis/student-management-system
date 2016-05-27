package com.davis.sms.controller.student;

import static java.util.Comparator.comparing;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

/**
 * REST controller for student-related actions.
 * 
 * @author Adam Davis
 */
@RestController
public class StudentController
{
    /**
     * Default sorting comparator, for use during
     * {@link #list(String, HttpServletResponse)} calls. Sorts by last name,
     * first name, middle name, in that order
     */
    private static final Comparator<StudentView> DEFAULT_DISPLAY_ORDER = comparing(
            StudentView::getLastName, ObjectUtils::compare)
                    .thenComparing(StudentView::getFirstName, ObjectUtils::compare)
                    .thenComparing(StudentView::getMiddleName, ObjectUtils::compare);

    /**
     * Standard logger
     */
    private Logger                               log                   = LoggerFactory
            .getLogger(getClass());

    /**
     * Auto-wired CRUD service
     */
    private StudentCrudService                   service;

    /**
     * Auto-wired view <=> entity converter
     */
    private StudentConverter                     converter;

    /**
     * @param service
     * @param converter
     */
    @Autowired
    public StudentController(StudentCrudService service, StudentConverter converter)
    {
        this.service = service;
        this.converter = converter;
    }

    /**
     * Disabled response caching for the given response object
     * 
     * @param response
     *            the response on which to disable caching
     */
    private void disableResponseCache(HttpServletResponse response)
    {
        response.setHeader("Cache-Control", "no-cache");
    }

    /**
     * Lists all students in the system, optionally filtering the student last
     * name by the given criteria. Filtering is done as a case-insensitive
     * "starts with" match
     * 
     * @param criteria
     *            filter for student last name
     * @param response
     *            servlet response object
     * @return list of students that match the given criteria
     */
    @RequestMapping(method = RequestMethod.GET, value = "/students")
    public List<StudentView> list(
            @RequestParam(name = "lastName", defaultValue = "", required = false) String criteria,
            HttpServletResponse response)
    {
        log.debug("list({})", criteria);

        disableResponseCache(response);

        return service.search(criteria).stream()
                .map(converter::toView)
                .sorted(DEFAULT_DISPLAY_ORDER)
                .collect(Collectors.toList());
    }

    /**
     * Loads and returns the student with the given ID.
     * 
     * @param studentId
     *            ID of the student to load. Must be non-null.
     * @param response
     *            servlet response object
     * @return matching student view
     * @throws NullPointerException
     *             if the given studentId is null
     * @throws IllegalArgumentException
     *             if there is no matching record for the given studentId
     */
    @RequestMapping(method = RequestMethod.GET, value = "/students/{id}")
    public StudentView get(@PathVariable("id") String studentId, HttpServletResponse response)
            throws NullPointerException, IllegalArgumentException
    {
        log.debug("get({})", studentId);

        disableResponseCache(response);

        return converter.toView(service.load(studentId));
    }

    /**
     * Adds a new student to the database, populated with the data from the
     * given view object.
     * 
     * @param contents
     *            contents to add to the database. Must be non-null.
     * @return newly added entity view
     * @throws NullPointerException
     *             if the given contents are null
     */
    @RequestMapping(method = RequestMethod.POST, value = "/students")
    public StudentView add(@RequestBody(required = true) StudentView contents)
            throws NullPointerException
    {
        log.debug("add({})", contents);

        return converter.toView(service.create(contents));
    }

    /**
     * Updates the entity with the given ID with the given contents.
     * 
     * @param studentId
     *            ID of the student to update
     * @param contents
     *            contents with which to update the student
     * @return updated student
     * @throws NullPointerException
     *             if either the given studentId or the given contents are null
     * @throws IllegalArgumentException
     *             if there is no existing student with the given ID
     */
    @RequestMapping(method = RequestMethod.PUT, value = "/students/{id}")
    public StudentView update(@PathVariable("id") String studentId,
            @RequestBody(required = true) StudentView contents)
            throws NullPointerException, IllegalArgumentException
    {
        log.debug("update({}, {})", studentId, contents);

        return converter.toView(service.update(studentId, contents));
    }

    /**
     * Deletes the student with the given ID from the database
     * 
     * @param studentId
     *            ID of the student to delete
     * @throws NullPointerException
     *             if the given studentId is null
     * @throws IllegalArgumentException
     *             if there is no existing student with the given ID
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/students/{id}")
    public void delete(@PathVariable("id") String studentId)
            throws NullPointerException, IllegalArgumentException
    {
        log.debug("delete({})", studentId);

        service.delete(studentId);
    }
}
