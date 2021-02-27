package com.adrianklessa.MathContestManager.repositories;

import com.adrianklessa.MathContestManager.entities.Award;
import com.adrianklessa.MathContestManager.entities.Member;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AwardRepository extends CrudRepository<Award, Integer> {
    @Query("SELECT a FROM Award a WHERE a.name LIKE '%Riemann%'")
    Iterable<Award> findAwardsWithRiemann();



}
