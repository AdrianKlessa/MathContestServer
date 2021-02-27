package com.adrianklessa.MathContestManager.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="refTopicId")
@Table(name = "topics")
public class Topic {


    public Topic(String name){
        this.name=name;
    }

    public Topic(){

    }

    @Id
    @GeneratedValue
    private int id;

    @Column
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }

    @ManyToMany(cascade = {CascadeType.PERSIST},fetch=FetchType.EAGER)
    @JoinTable(name="events_topics")
    private Set<Event> events = new HashSet<>();

    @PreRemove
    private void removeFromOthers(){
        for(Event event : this.getEvents()){
            event.getTopics().remove(this);
        }
    }
}
