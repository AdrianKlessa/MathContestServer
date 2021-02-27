package com.adrianklessa.MathContestManager.services;


import com.adrianklessa.MathContestManager.entities.Section;
import com.adrianklessa.MathContestManager.repositories.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SectionServiceImpl implements SectionService {


    @Autowired
    private SectionRepository sectionRepository;

    @Override
    public Iterable<Section> listAllSections() {
        return sectionRepository.findAll();
    }

    @Override
    public Optional<Section> getSectionById(Integer id) {
        return sectionRepository.findById(id);
    }

    @Override
    public Section saveSection(Section section) {
        return sectionRepository.save(section);
    }

    @Override
    public void deleteSection(Integer id) {
        sectionRepository.deleteById(id);
    }
}
