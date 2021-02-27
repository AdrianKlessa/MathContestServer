package com.adrianklessa.MathContestManager.services;

import com.adrianklessa.MathContestManager.entities.Award;
import com.adrianklessa.MathContestManager.entities.Member;

import java.util.Optional;

public interface AwardService {

    public Iterable<Award> query2();

    public Iterable<Award> listAllAwards();

    public Optional<Award> getAwardById(Integer id);

    public Award saveAward(Award award);

    public void deleteAward(Integer id);
}
