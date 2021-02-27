package com.adrianklessa.MathContestManager.repositories;

import com.adrianklessa.MathContestManager.entities.Member;
import com.adrianklessa.MathContestManager.entities.Section;
import org.springframework.data.repository.CrudRepository;

public interface SectionRepository extends CrudRepository<Section, Integer> {
}
