package com.pluralsight.conferencedemo.controllers;

import com.pluralsight.conferencedemo.models.Session;
import com.pluralsight.conferencedemo.repositories.SessionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sessions") // all request to /api/v1/sessions will be sent to this controller
public class SessionsController {

    // tell Spring to connect the sessionRepository class we just created
    @Autowired
    private SessionRepository sessionRepository;

    // create a method that will return all the sessions
    @GetMapping
    public List<Session> list(){
        return sessionRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Session get(@PathVariable Long id){
        return sessionRepository.getOne(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Session create(@RequestBody final Session session){
        return sessionRepository.saveAndFlush(session);
    }

    @RequestMapping(value="{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id){
        sessionRepository.deleteById(id);
    }

    @RequestMapping(value="{id}", method = RequestMethod.PUT)
    public Session update(@PathVariable Long id, @RequestBody Session session){
        // becouse this is a PUT method We expect all attributes to be passsed in the session, other wise they will be null
        // if i only want to update some attribue then user PATCH method
        // get the current session to update
        Session existingSession = sessionRepository.getOne(id);
        BeanUtils.copyProperties(session, existingSession, "session_id");
        return sessionRepository.saveAndFlush(existingSession);
    }
}
