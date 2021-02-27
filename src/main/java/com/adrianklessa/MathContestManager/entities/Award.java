package com.adrianklessa.MathContestManager.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="refAwardId")
@Table(name = "awards")
public class Award {

    @Id
    @GeneratedValue
    private int id;

    @Column
    private String name;


    public Award(){}

    public Award(String name){
        this.name=name;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Event getEvent() {
        return event;
    }



    public void setEvent(Event event) {
        this.event = event;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    @ManyToOne(fetch=FetchType.EAGER,cascade = {CascadeType.PERSIST})
    private Event event;

    @ManyToOne(fetch=FetchType.EAGER,cascade = {CascadeType.PERSIST})
    private Member member;

    @PreRemove
    private void removeFromOthers(){
        if(this.getEvent()!=null){
            this.getEvent().getAwards().remove(this);
        }

        if(this.getMember()!=null){
            this.getMember().getAwards().remove(this);
        }

    }
}
