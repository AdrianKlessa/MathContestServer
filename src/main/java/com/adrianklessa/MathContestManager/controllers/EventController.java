package com.adrianklessa.MathContestManager.controllers;


import com.adrianklessa.MathContestManager.entities.Award;
import com.adrianklessa.MathContestManager.entities.Event;
import com.adrianklessa.MathContestManager.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping(value = "/events.xml", produces = MediaType.APPLICATION_XML_VALUE)
    public Iterable <Event> listXML(Model model)
    {
        return eventService.listAllEvents();
    }

    @GetMapping(value = "/events.json", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable <Event> listJson(Model model)
    {
        return eventService.listAllEvents();
    }

    @GetMapping(value = "/event/{id}.xml",produces = MediaType.APPLICATION_XML_VALUE)
    public Optional<Event> getByPublicIdXML(@PathVariable("id") Integer publicId){
        return eventService.getEventById(publicId);
    }

    @GetMapping(value = "/event/{id}.json",produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Event> getByPublicIdJson(@PathVariable("id") Integer publicId){
        return eventService.getEventById(publicId);
    }

    @PostMapping(value = "/event.xml")
    public ResponseEntity<Event> createXML(@RequestBody @NonNull @Validated(Event.class)
                                                       Event event) {
        event.setId(ThreadLocalRandom.current().nextInt(-100000, 100000));
        eventService.saveEvent(event);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(value = "/event.json")
    public ResponseEntity<Event> createJSON(@RequestBody @NonNull @Validated(Event.class)
                                                         Event event) {
        event.setId(ThreadLocalRandom.current().nextInt(-100000, 100000));
        eventService.saveEvent(event);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiIgnore
    @RequestMapping(value = "/events.json", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Event> redirect(Model model) {
        return eventService.listAllEvents();
    }

    @DeleteMapping(value="/deleteEvent{id}")
    public RedirectView delete(@PathVariable Integer id) {
        eventService.deleteEvent(id);
        return new RedirectView("/api/events.json", true);
    }
}
