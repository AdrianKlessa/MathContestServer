package com.adrianklessa.MathContestManager.controllers;



import com.adrianklessa.MathContestManager.entities.Member;
import com.adrianklessa.MathContestManager.entities.Topic;
import com.adrianklessa.MathContestManager.services.TopicService;
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
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("/api")
public class TopicController {
    @Autowired
    private TopicService topicService;

    @GetMapping(value = "/topics.xml", produces = MediaType.APPLICATION_XML_VALUE)
    public Iterable <Topic> listXML(Model model)
    {
        return topicService.listAllTopics();
    }

    @GetMapping(value = "/topics.json", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable <Topic> listJson(Model model)
    {
        return topicService.listAllTopics();
    }

    @GetMapping(value = "/topic/{id}.xml",produces = MediaType.APPLICATION_XML_VALUE)
    public Optional<Topic> getByPublicIdXML(@PathVariable("id") Integer publicId){
        return topicService.getTopicById(publicId);
    }

    @GetMapping(value = "/topic/{id}.json",produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Topic> getByPublicIdJson(@PathVariable("id") Integer publicId){
        return topicService.getTopicById(publicId);
    }

    @PostMapping(value = "/topic.xml")
    public ResponseEntity<Topic> createXML(@RequestBody @NonNull @Validated(Topic.class)
                                                   Topic topic) {
        topic.setId(ThreadLocalRandom.current().nextInt(-100000, 100000));
        topicService.saveTopic(topic);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(value = "/topic.json")
    public ResponseEntity<Topic> createJson(@RequestBody @NonNull @Validated(Topic.class)
                                                   Topic topic) {
        topic.setId(ThreadLocalRandom.current().nextInt(-100000, 100000));
        topicService.saveTopic(topic);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiIgnore
    @RequestMapping(value = "/topics.json", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Topic> redirect(Model model) {
        return topicService.listAllTopics();
    }

    @DeleteMapping(value="/deleteTopic{id}")
    public RedirectView delete(@PathVariable Integer id) {
        topicService.deleteTopic(id);
        return new RedirectView("/api/topics.json", true);
    }
}
