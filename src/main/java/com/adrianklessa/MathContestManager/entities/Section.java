package com.adrianklessa.MathContestManager.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="refSectionId")
@Table(name = "sections")
public class Section {


    public Section(){

    }

    public Section(String name){
        this.name=name;
    }

    @Id
    @GeneratedValue
    private int sectionId;

    @Column
    private String name;

    public int getSectionId() {
        return sectionId;
    }

    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Member> getMembers() {
        return members;
    }

    public void setMembers(Set<Member> members) {
        this.members = members;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy="section", cascade=CascadeType.PERSIST)
    private Set<Member> members = new HashSet<>();

    @PreRemove
    private void removeFromOthers(){
        for (Member member: this.getMembers()){
            member.setSection(null);
        }


    }
}
