package com.adrianklessa.MathContestManager.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="refMemberId")
@Table(name = "members")
public class Member {



    public Member(){

    }

    public Member(String name, String surname){
        this.name=name;
        this.surname=surname;
    }
    @Id
    @GeneratedValue
    private int id;

    @Column
    private String name;

    @Column
    private String surname;


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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }


    public Set<Award> getAwards() {
        return awards;
    }

    public void setAwards(Set<Award> awards) {
        this.awards = awards;
    }

    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy="member")
    private Set<Award> awards = new HashSet<>();

    @ManyToMany(mappedBy="members")
    Set<Event> events = new HashSet<>();

    @ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
    private Section section ;

    @PreRemove
    private void removeFromOthers(){
        for (Event event : this.getEvents()){
            event.getMembers().remove(this);
        }

        if(this.getSection()!=null){
           this.getSection().getMembers().remove(this);
        }

        if(this.getAwards()!=null){
            for(Award award : this.getAwards()){
                award.setMember(null);
            }
        }



    }
}
