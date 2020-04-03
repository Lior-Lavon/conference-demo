package com.pluralsight.conferencedemo.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
public class HomeController {

    // Setting up custome application peoperty class to read the value

    // Injecting the app version
    // look in the property section of the app and find the app.version and inject the value to appVersion
    @Value("${app.version}")
    private String appVersion;

    @GetMapping
    @RequestMapping("/")
    public Map<String, String> getStatus(){
        Map<String, String> version = new HashMap();
        version.put("app-version", appVersion);
        return version;
    }
}
