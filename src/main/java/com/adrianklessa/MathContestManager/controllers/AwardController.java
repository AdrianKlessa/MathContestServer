package com.adrianklessa.MathContestManager.controllers;


import com.adrianklessa.MathContestManager.entities.Award;
import com.adrianklessa.MathContestManager.services.AwardService;
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
public class AwardController {

    @Autowired
    private AwardService awardService;





    @GetMapping(value = "/awards.xml", produces = MediaType.APPLICATION_XML_VALUE)
    public Iterable <Award> listXML(Model model)
    {
        return awardService.listAllAwards();
    }

    @GetMapping(value = "/awards.json", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable <Award> listJson(Model model)
    {
        return awardService.listAllAwards();
    }

    @GetMapping(value = "/award/{id}.xml",produces = MediaType.APPLICATION_XML_VALUE)
    public Optional<Award> getByPublicIdXML(@PathVariable("id") Integer publicId){
        return awardService.getAwardById(publicId);
    }

    @GetMapping(value = "/award/{id}.json",produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Award> getByPublicIdJson(@PathVariable("id") Integer publicId){
        return awardService.getAwardById(publicId);
    }

    @PostMapping(value = "/award.xml")
    public ResponseEntity<Award> createXML(@RequestBody @NonNull @Validated(Award.class)
                                                   Award award) {
        award.setId(ThreadLocalRandom.current().nextInt(-100000, 100000));
        awardService.saveAward(award);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(value = "/award.json")
    public ResponseEntity<Award> createJSON(@RequestBody @NonNull @Validated(Award.class)
                                                    Award award) {
        award.setId(ThreadLocalRandom.current().nextInt(-100000, 100000));
        awardService.saveAward(award);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiIgnore
    @RequestMapping(value = "/awards.json", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Award> redirect(Model model) {
        return awardService.listAllAwards();
    }

    @DeleteMapping(value="/deleteAward{id}")
    public RedirectView delete(@PathVariable Integer id) {
        awardService.deleteAward(id);
        return new RedirectView("/api/awards.json", true);
    }
}
