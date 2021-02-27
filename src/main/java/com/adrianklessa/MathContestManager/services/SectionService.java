package com.adrianklessa.MathContestManager.services;

import com.adrianklessa.MathContestManager.entities.Member;
import com.adrianklessa.MathContestManager.entities.Section;

import java.util.Optional;

public interface SectionService {

    public Iterable<Section> listAllSections();

    public Optional<Section> getSectionById(Integer id);

    public Section saveSection(Section section);

    public void deleteSection(Integer id);
}
