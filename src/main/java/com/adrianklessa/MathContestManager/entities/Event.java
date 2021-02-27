package com.adrianklessa.MathContestManager.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.joda.time.LocalDate;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="refEventId")
@Table(name = "wydarzenia")
public class Event {

    @Id
    @GeneratedValue
    private int id;

    @Column
    private String name;

    @Column
    private String country;

    @Column
    private String city;

    @Column
    private LocalDate date;

    @ManyToMany(mappedBy="events")
    Set<Topic> topics = new HashSet<>();

    @OneToMany(mappedBy="event",fetch=FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Set<Award> awards = new HashSet<>();

    public Set<Topic> getTopics() {
        return topics;
    }

    public void setTopics(Set<Topic> topics) {
        this.topics = topics;
    }

    public Set<Award> getAwards() {
        return awards;
    }

    public void setAwards(Set<Award> awards) {
        this.awards = awards;
    }

    public Set<Member> getMembers() {
        return members;
    }

    public void setMembers(Set<Member> membgers) {
        this.members = membgers;
    }

    @ManyToMany(cascade = {CascadeType.PERSIST},fetch=FetchType.EAGER)
    @JoinTable(name="events_members")
    private Set<Member> members = new HashSet<>();

    public Event(String name, String country, String city, String date){
        this.name=name;
        this.country=country;
        this.city=city;
        this.date=new LocalDate(date);
    }

    public Event(){

    }
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @PreRemove
    private void removeFromOthers(){
        for (Member member : this.getMembers()){
            member.getEvents().remove(this);
        }

        for(Topic topic : this.getTopics()){
            topic.getEvents().remove(this);
        }

        for(Award award : this.getAwards()){
            award.setEvent(null);
        }
    }
}
