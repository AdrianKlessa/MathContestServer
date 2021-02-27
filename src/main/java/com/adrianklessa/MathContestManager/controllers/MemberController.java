package com.adrianklessa.MathContestManager.controllers;

import com.adrianklessa.MathContestManager.entities.Event;
import com.adrianklessa.MathContestManager.entities.Member;
import com.adrianklessa.MathContestManager.services.MemberService;
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
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;


@RestController
@RequestMapping("/api")
public class MemberController {

    @Autowired
    private MemberService memberService;


    @GetMapping(value = "/members.xml", produces = MediaType.APPLICATION_XML_VALUE)
    public Iterable <Member> listXML(Model model)
    {
        return memberService.listAllMembers();
    }

    @GetMapping(value = "/members.json", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable <Member> listJson(Model model)
    {
        return memberService.listAllMembers();
    }

    @GetMapping(value = "/member/{id}.xml",produces = MediaType.APPLICATION_XML_VALUE)
    public Optional<Member> getByPublicIdXML(@PathVariable("id") Integer publicId){
        return memberService.getMemberById(publicId);
    }

    @GetMapping(value = "/member/{id}.json",produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Member> getByPublicIdJson(@PathVariable("id") Integer publicId){
        return memberService.getMemberById(publicId);
    }

    @PostMapping(value = "/member.xml")
    public ResponseEntity<Member> createXML(@RequestBody @NonNull @Validated(Member.class)
                                                        Member member) {
        member.setId(ThreadLocalRandom.current().nextInt(-100000, 100000));
        memberService.saveMember(member);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(value = "/member.json")
    public ResponseEntity<Member> createJSON(@RequestBody @NonNull @Validated(Member.class)
                                                    Member member) {
        member.setId(ThreadLocalRandom.current().nextInt(-100000, 100000));
        memberService.saveMember(member);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiIgnore
    @RequestMapping(value = "/members.json", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Member> redirect(Model model) {
        return memberService.listAllMembers();
    }

    @DeleteMapping(value="/deleteMember{id}")
    public RedirectView delete(@PathVariable Integer id) {
        memberService.deleteMember(id);
        return new RedirectView("/api/members.json", true);
    }

}
