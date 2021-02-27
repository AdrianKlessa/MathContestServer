package com.adrianklessa.MathContestManager.services;

import com.adrianklessa.MathContestManager.entities.Award;
import com.adrianklessa.MathContestManager.entities.Topic;

import java.util.Optional;

public interface TopicService {
    public Iterable<Topic> listAllTopics();

    public Optional<Topic> getTopicById(Integer id);

    public Topic saveTopic(Topic topic);

    public void deleteTopic(Integer id);

    public int query4();
}
