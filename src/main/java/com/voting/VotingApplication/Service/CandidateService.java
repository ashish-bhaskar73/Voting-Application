package com.voting.VotingApplication.Service;

import java.util.DuplicateFormatFlagsException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.voting.VotingApplication.Exception.ResourceNotFoundException;
import com.voting.VotingApplication.Repository.CandidateRepsoitory;
import com.voting.VotingApplication.model.Candidate;
import com.voting.VotingApplication.model.Vote;

@Service
public class CandidateService {
    @Autowired
    private CandidateRepsoitory candidateRepsoitory;

    // retrive all candidate
    public List<Candidate> getAllCandidate() {
        return candidateRepsoitory.findAll();
    }

    // get candidate by candidate id
    public Candidate getCandidateById(Long id) {
        Candidate candi = candidateRepsoitory.findById(id).orElse(null);
        if (candi == null) {
            throw new ResourceNotFoundException("Candidate not found with candidate id: " + id);
        }
        return candi;
    }

    // there is no uniqueness in candidate model

    public Candidate registerCandidate(Candidate candidate) {
        return candidateRepsoitory.save(candidate);
    }

    // update candidate

    public Candidate updateCandidate(Long id, Candidate updatedCandidate) {
        Candidate candi = candidateRepsoitory.findById(updatedCandidate.getId()).orElse(null);

        if (candi == null) {
            throw new ResourceNotFoundException(
                    "Candidate does not pressent with id:" + id + ". So not able to update");
        }

        if (candi.getName() != null) {
            candi.setName(updatedCandidate.getName());
        }
        if (candi.getParty() != null) {
            candi.setParty(updatedCandidate.getParty());
        }

        return candidateRepsoitory.save(candi);
    }

    @Transactional
    public void deleteCandidate(Long id) {
        Candidate candidate = candidateRepsoitory.findById(id).orElse(null);

        if (candidate == null) {
            throw new DuplicateFormatFlagsException("Candidate can't be deleted with id:" + id);
        }

        // removing dependency from candidate to vote because vote is linked to
        // candidate
        List<Vote> votes = candidate.getVote();
        for (Vote vote : votes) {
            vote.setCandidate(null);
        }

        candidate.getVote().clear();
        candidateRepsoitory.delete(candidate);
    }
}
