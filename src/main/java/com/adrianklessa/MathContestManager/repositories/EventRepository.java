package com.adrianklessa.MathContestManager.repositories;

import com.adrianklessa.MathContestManager.entities.Event;
import com.adrianklessa.MathContestManager.entities.Member;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<Event, Integer> {

    @Query(value="SELECT City FROM (SELECT City, COUNT('name') FROM wydarzenia GROUP BY City ORDER BY COUNT('name') DESC LIMIT 1) AS whatever", nativeQuery = true)
    Iterable <String> findMostPopularCity();

    @Query(value="SELECT COUNT(id) from wydarzenia", nativeQuery = true)
    int numberOfEvents();
}
