package com.adrianklessa.MathContestManager.repositories;

import com.adrianklessa.MathContestManager.entities.Member;
import com.adrianklessa.MathContestManager.entities.Topic;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TopicRepository extends CrudRepository<Topic, Integer> {


    @Query("SELECT COUNT(id) FROM Topic ")
    Integer countTopics();
}
