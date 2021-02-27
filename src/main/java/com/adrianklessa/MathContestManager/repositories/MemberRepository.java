package com.adrianklessa.MathContestManager.repositories;

import com.adrianklessa.MathContestManager.entities.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface MemberRepository extends CrudRepository<Member, Integer> {

    @Query("SELECT m FROM Member m WHERE m.section IS NULL ")
    Iterable<Member> findMembersNotBelongingToSection();


    @Query("SELECT m FROM Member m WHERE m.awards IS NOT EMPTY ")
    Page<Member> findMembersWhoWonAward(Pageable pageable);


}
