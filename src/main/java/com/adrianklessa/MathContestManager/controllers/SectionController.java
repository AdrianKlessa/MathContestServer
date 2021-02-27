package com.adrianklessa.MathContestManager.controllers;

import com.adrianklessa.MathContestManager.entities.Member;
import com.adrianklessa.MathContestManager.entities.Section;
import com.adrianklessa.MathContestManager.services.SectionService;
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
public class SectionController {
    @Autowired
    private SectionService sectionService;

    @GetMapping(value = "/sections.xml", produces = MediaType.APPLICATION_XML_VALUE)
    public Iterable <Section> listXML(Model model)
    {
        return sectionService.listAllSections();
    }

    @GetMapping(value = "/sections.json", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable <Section> listJson(Model model)
    {
        return sectionService.listAllSections();
    }

    @GetMapping(value = "/section/{id}.xml",produces = MediaType.APPLICATION_XML_VALUE)
    public Optional<Section> getByPublicIdXML(@PathVariable("id") Integer publicId){
        return sectionService.getSectionById(publicId);
    }

    @GetMapping(value = "/section/{id}.json",produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Section> getByPublicIdJson(@PathVariable("id") Integer publicId){
        return sectionService.getSectionById(publicId);
    }

    @PostMapping(value = "/section.xml")
    public ResponseEntity<Section> createXML(@RequestBody @NonNull @Validated(Section.class)
                                                   Section section) {
        section.setSectionId(ThreadLocalRandom.current().nextInt(-100000, 100000));
        sectionService.saveSection(section);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(value = "/section.json")
    public ResponseEntity<Section> createJSON(@RequestBody @NonNull @Validated(Section.class)
                                                        Section section) {
        section.setSectionId(ThreadLocalRandom.current().nextInt(-100000, 100000));
        sectionService.saveSection(section);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiIgnore
    @RequestMapping(value = "/sections.json", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Section> redirect(Model model) {
        return sectionService.listAllSections();
    }

    @DeleteMapping(value="/deleteSection{id}")
    public RedirectView delete(@PathVariable Integer id) {
        sectionService.deleteSection(id);
        return new RedirectView("/api/sections.json", true);
    }

}
