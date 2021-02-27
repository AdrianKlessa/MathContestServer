package com.adrianklessa.MathContestManager.services;


import com.adrianklessa.MathContestManager.entities.Award;
import com.adrianklessa.MathContestManager.entities.Topic;
import com.adrianklessa.MathContestManager.repositories.AwardRepository;
import com.adrianklessa.MathContestManager.repositories.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


//TODO

@Service
public class TopicServiceImpl implements TopicService{
    @Autowired
    private TopicRepository topicRepository;


    @Override
    public Iterable<Topic> listAllTopics() {
        return topicRepository.findAll();
    }

    @Override
    public Optional<Topic> getTopicById(Integer id) {
        return topicRepository.findById(id);
    }

    @Override
    public Topic saveTopic(Topic topic) {
        return topicRepository.save(topic);
    }

    @Override
    public void deleteTopic(Integer id) {
        topicRepository.deleteById(id);
    }

    @Override
    public int query4() {
        return topicRepository.countTopics();
    }
}
