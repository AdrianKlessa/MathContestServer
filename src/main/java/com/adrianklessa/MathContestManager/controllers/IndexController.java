package com.adrianklessa.MathContestManager.controllers;

import com.adrianklessa.MathContestManager.entities.*;
import com.adrianklessa.MathContestManager.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")

public class IndexController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private SectionService sectionService;

    @Autowired
    private EventService eventService;

    @Autowired
    private AwardService awardService;

    @Autowired
    private TopicService topicService;
    @GetMapping(value = "")
    String index(){
        return"strona główna";
    }




    @GetMapping(value = "/query1.xml", produces = MediaType.APPLICATION_XML_VALUE)
    public Iterable <Member> query1xml(Model model)
    {
        return memberService.query1();
    }

    @GetMapping(value = "/query1.json", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable <Member> query1json(Model model)
    {
        return memberService.query1();
    }

    @GetMapping(value = "/query2.xml", produces = MediaType.APPLICATION_XML_VALUE)
    public Iterable <Award> query2xml(Model model)
    {
        return awardService.query2();
    }

    @GetMapping(value = "/query2.json", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable <Award> query2json(Model model)
    {
        return awardService.query2();
    }

    @GetMapping(value = "/query3/{page}.xml", produces = MediaType.APPLICATION_XML_VALUE)
    public Iterable <Member> query3xml(@PathVariable("page") Integer page)
    {
        return memberService.query3(page);
    }

    @GetMapping(value = "/query3/{page}.json", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable <Member> query3json(@PathVariable("page") Integer page){ return memberService.query3(page); }

    @GetMapping(value = "/query4.xml", produces = MediaType.APPLICATION_XML_VALUE)
    public Integer query4xml(Model model)
    {
        return topicService.query4();
    }

    @GetMapping(value = "/query4.json", produces = MediaType.APPLICATION_JSON_VALUE)
    public Integer query4json(Model model){ return topicService.query4(); }

    @GetMapping(value = "/query5.xml", produces = MediaType.APPLICATION_XML_VALUE)
    public Iterable <String> query5xml(Model model)
    {
        return eventService.query5();
    }

    @GetMapping(value = "/query5.json", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable <String> query5json(Model model){ return eventService.query5(); }

    @GetMapping(value = "/numberOfEvents", produces = MediaType.APPLICATION_JSON_VALUE)
    public int  numberOfEvents(Model model){ return eventService.numberOfEvents(); }



    @PostMapping(value = "generateModel", produces = MediaType.TEXT_PLAIN_VALUE)
    public String generateModel(){

        Member m1 = new Member("Adrian","Klessa");
        Member m2 = new Member("Szymon", "Kowalski");
        Member m3 = new Member("Adam","Małysz");
        Member m4 = new Member("Maciej", "Gromada");
        Member m5 = new Member("Szymon", "Zorro");



        Event e1 = new Event("World math tournament", "Poland", "Poznań", "2021-01-03");
        Event e2 = new Event("Leibnitz math tourney", "Germany","Berlin", "2021-03-21");
        Event e3 = new Event("Deutsche Mathematische Kompetition","Germany", "Berlin", "2021-02-03");
        e1.getMembers().add(m1);
        m1.getEvents().add(e1);

        Section s1 = new Section("Polska sekcja matematyczna");
        s1.getMembers().add(m1);
        m1.setSection(s1);

        Award a1 = new Award("Nagroda Riemanna");
        a1.setMember(m2);
        m2.getAwards().add(a1);
        a1.setEvent(e1);
        e1.getAwards().add(a1);

        Award a3 = new Award("Award 3");
        Award a4 = new Award("Award 4");
        Award a5 = new Award("Award 5");
        m4.setSection(s1);
        a3.setMember(m3);
        m3.getAwards().add(a3);

        a4.setMember(m4);
        m4.getAwards().add(a4);

        a5.setMember(m5);
        m5.getAwards().add(a5);

        Topic t1 = new Topic("Differential equations");
        t1.getEvents().add(e1);
        e1.getTopics().add(t1);

        Award a2 = new Award("Gauss prize");
        a2.setMember(m2);
        m2.getAwards().add(a2);
        a2.setEvent(e1);
        e1.getAwards().add(a2);



        topicService.saveTopic(t1);
        sectionService.saveSection(s1);
        eventService.saveEvent(e1);
        eventService.saveEvent(e2);
        eventService.saveEvent(e3);
        awardService.saveAward(a1);
        memberService.saveMember(m1);
        awardService.saveAward(a2);
        awardService.saveAward(a4);
        awardService.saveAward(a3);
        awardService.saveAward(a5);
        memberService.saveMember(m2);
        memberService.saveMember(m3);
        memberService.saveMember(m4);
        memberService.saveMember(m5);
        return "Model generated";
    }
}
