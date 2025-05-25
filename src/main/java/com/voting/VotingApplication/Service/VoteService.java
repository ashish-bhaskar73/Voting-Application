package com.voting.VotingApplication.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voting.VotingApplication.Exception.ResourceNotFoundException;
import com.voting.VotingApplication.Exception.VoteNotAllowedException;
import com.voting.VotingApplication.Repository.CandidateRepsoitory;
import com.voting.VotingApplication.Repository.VoteRepository;
import com.voting.VotingApplication.Repository.VoterRepository;
import com.voting.VotingApplication.model.Candidate;
import com.voting.VotingApplication.model.Vote;
import com.voting.VotingApplication.model.Voter;

import jakarta.transaction.Transactional;

@Service
public class VoteService {

    @Autowired
    private CandidateRepsoitory candidateRepsoitory;
    @Autowired
    private VoterRepository voterRepository;
    @Autowired
    private VoteRepository voteRepository;

    @Transactional
    public Vote voteCaste(Long voterId, Long candidateId) {
        Voter voter = voterRepository.findById(voterId).orElse(null);
        Candidate candidate = candidateRepsoitory.findById(candidateId).orElse(null);

        if (voter == null) {
            throw new ResourceNotFoundException("voter not found with id: " + voterId);
        }
        if (candidate == null) {
            throw new ResourceNotFoundException("candidate not found with candidate id:" + candidateId);
        }
        if (voter.isHasVoted()) {
            throw new VoteNotAllowedException("voter has already voted" + voterId);
        }

        // new object of vote
        Vote vote = new Vote();
        // set voter in vote
        vote.setVoter(voter);

        // set candidate in vote
        vote.setCandidate(candidate);
        voter.setVote(vote);
        // save vote
        // voteRepository.save(vote);

        // update vote count in candidate
        candidate.setVoteCount(candidate.getVoteCount() + 1);
        candidateRepsoitory.save(candidate);
        // update vote after its vote has been done
        voter.setHasVoted(true);
        voterRepository.save(voter);

        return vote;
    }

    public List<Vote> getAllVotes() {
        return voteRepository.findAll();
    }
}
