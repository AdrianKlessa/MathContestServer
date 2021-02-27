package com.adrianklessa.MathContestManager.services;

import com.adrianklessa.MathContestManager.entities.Member;

import java.util.Optional;

public interface MemberService {

    public Iterable<Member> listAllMembers();

    public Optional<Member> getMemberById(Integer id);

    public Member saveMember(Member member);

    public void deleteMember(Integer id);

    public Iterable<Member> query1();

    public Iterable<Member> query3(int pageNumber);


}
