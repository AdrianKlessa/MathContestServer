package com.adrianklessa.MathContestManager.services;


import com.adrianklessa.MathContestManager.entities.Member;
import com.adrianklessa.MathContestManager.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService
{

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public Iterable<Member> listAllMembers(){
        return memberRepository.findAll();
    }
    @Override
    public Optional<Member> getMemberById(Integer id){
        return memberRepository.findById(id);
    }
    @Override
    public Member saveMember(Member member){
        return memberRepository.save(member);
    }
    @Override
    public void deleteMember(Integer id){
        memberRepository.deleteById(id);
    }

    @Override
    public Iterable<Member> query1() {
        return memberRepository.findMembersNotBelongingToSection();
    }

    @Override
    public Iterable<Member> query3(int pageNumber) {

        Pageable pageable = PageRequest.of(pageNumber, 2, Sort.by(Sort.Order.asc("surname"),
                Sort.Order.desc("id")));

        return memberRepository.findMembersWhoWonAward(pageable);
    }
}
