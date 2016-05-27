package com.davis.sms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller for the single-page "index.html" that serves the application
 * 
 * @author Adam Davis
 */
@Controller
public class IndexController
{
    @RequestMapping("/index")
    public String index()
    {
        return "index";
    }
}
