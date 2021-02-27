package com.adrianklessa.MathContestManager.services;

import com.adrianklessa.MathContestManager.entities.Award;
import com.adrianklessa.MathContestManager.entities.Event;

import java.util.Optional;

public interface EventService {

    public Iterable<Event> listAllEvents();

    public Optional<Event> getEventById(Integer id);

    public Event saveEvent(Event event);

    public void deleteEvent(Integer id);

    public Iterable <String> query5();

    public int numberOfEvents();
}
