package com.adrianklessa.MathContestManager.services;

import com.adrianklessa.MathContestManager.entities.Award;
import com.adrianklessa.MathContestManager.repositories.AwardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AwardServiceImpl implements AwardService{

    @Autowired
    private AwardRepository awardRepository;

    @Override
    public Iterable<Award> query2() {
        return awardRepository.findAwardsWithRiemann();
    }

    @Override
    public Iterable<Award> listAllAwards() {
        return awardRepository.findAll();
    }

    @Override
    public Optional<Award> getAwardById(Integer id) {
        return awardRepository.findById(id);
    }

    @Override
    public Award saveAward(Award award) {
        return awardRepository.save(award);
    }

    @Override
    public void deleteAward(Integer id) {
        awardRepository.deleteById(id);
    }


}
