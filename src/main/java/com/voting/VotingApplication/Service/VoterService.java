package com.voting.VotingApplication.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voting.VotingApplication.Exception.DuplicateResourceException;
import com.voting.VotingApplication.Exception.ResourceNotFoundException;
import com.voting.VotingApplication.Repository.CandidateRepsoitory;
import com.voting.VotingApplication.Repository.VoterRepository;
import com.voting.VotingApplication.model.Candidate;
import com.voting.VotingApplication.model.Vote;
import com.voting.VotingApplication.model.Voter;

import jakarta.transaction.Transactional;

@Service
public class VoterService {
    @Autowired
    private VoterRepository voterRepository;
    @Autowired
    private CandidateRepsoitory candidateRepsoitory;

    // Post request
    public Voter registerVoter(Voter voter) {
        if (voterRepository.existsByEmail(voter.getEmail())) {
            throw new DuplicateResourceException("voter with email: " + voter.getEmail());
        }
        return voterRepository.save(voter);
    }

    // Get Request of all voter

    public List<Voter> getAllVoters() {
        return voterRepository.findAll();
    }

    // get request of single user by email id

    public Voter getVoterById(Long id) {
        Voter voter = voterRepository.findById(id).orElse(null);
        if (voter == null) {
            throw new ResourceNotFoundException("voter Not found by id:" + id);
        }
        return voter;
    }

    public Voter updateVoter(Long id, Voter updatedvoter) {
        Voter voter = voterRepository.findById(id).orElse(null);
        if (voter == null) {
            throw new ResourceNotFoundException("voter not found with id:" + id);
        }
        voter.setName(updatedvoter.getName());
        if (voterRepository.existsByEmail(updatedvoter.getEmail())) {
            throw new DuplicateResourceException(
                    "mail can't be updated it because alreeady used: " + updatedvoter.getEmail());
        }
        voter.setEmail(updatedvoter.getEmail());
        return voterRepository.save(voter);
    }

    @Transactional
    public void deleteVoter(Long id) {
        Voter voter = voterRepository.findById(id).orElse(null);
        if (voter == null) {
            throw new ResourceNotFoundException("Voter can't be deleted with id: " + id);
        }
        Vote vote = voter.getVote();
        if (vote != null) {
            Candidate candidate = vote.getCandidate();
            candidate.setVoteCount(candidate.getVoteCount() - 1);
            candidateRepsoitory.save(candidate);
        }
        voterRepository.deleteById(id);
    }
}
