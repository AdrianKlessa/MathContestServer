package com.adrianklessa.MathContestManager.services;


import com.adrianklessa.MathContestManager.entities.Event;
import com.adrianklessa.MathContestManager.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EventServiceImpl implements EventService{

    @Autowired
    private EventRepository eventRepository;

    @Override
    public Iterable<Event> listAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public Optional<Event> getEventById(Integer id) {
        return eventRepository.findById(id);
    }

    @Override
    public Event saveEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public void deleteEvent(Integer id) {
        eventRepository.deleteById(id);
    }

    @Override
    public Iterable <String> query5() {
        return eventRepository.findMostPopularCity();
    }

    @Override
    public int numberOfEvents() {
        return eventRepository.numberOfEvents();
    }


}
