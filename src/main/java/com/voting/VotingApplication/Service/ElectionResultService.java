package com.voting.VotingApplication.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voting.VotingApplication.Exception.DuplicateResourceException;
import com.voting.VotingApplication.Exception.ResourceNotFoundException;
import com.voting.VotingApplication.Repository.CandidateRepsoitory;
import com.voting.VotingApplication.Repository.ElectionRepository;
import com.voting.VotingApplication.Repository.VoteRepository;
import com.voting.VotingApplication.model.Candidate;
import com.voting.VotingApplication.model.ElectionResult;

@Service
public class ElectionResultService {
    @Autowired
    private ElectionRepository electionRepository;
    @Autowired
    private CandidateRepsoitory candidateRepsoitory;
    @Autowired
    private VoteRepository voteRepository;

    public ElectionResult declareResult(String electionName) {
        // ElectionRepository election =
        // electionRepository.findByElectionName(electionName).orElse(null);
        Optional<ElectionResult> electionResult = electionRepository.findByElectionName(electionName);
        if (electionResult.isPresent()) {
            throw new DuplicateResourceException(
                    "Election result has already been declared for election: " + electionName);
        }
        // 2. Validate votes exist
        if (voteRepository.count() == 0) {
            throw new IllegalStateException("Cannot declare result as no votes have been cast.");
        }

        // 3. Get candidates ordered by vote count
        List<Candidate> allCandidates = candidateRepsoitory.findAllByOrderByVoteCountDesc();
        if (allCandidates.isEmpty()) {
            throw new ResourceNotFoundException("No candidates have received any votes.");
        }
        Candidate winner = allCandidates.get(0);

        // 4. Compute winner and total vote count
        int totalVotes = 0;
        for (Candidate candidate : allCandidates) {
            totalVotes += candidate.getVoteCount();
        }
        // Long winnerId = winner.getId();

        // Create and save ElectionResult
        ElectionResult result = new ElectionResult();
        result.setElectionName(electionName);
        result.setWinner(winner);
        result.setTotalVotes(totalVotes);

        return electionRepository.save(result);
    }

    // get all election result;
    public List<ElectionResult> getAllResult() {
        return electionRepository.findAll();
    }

}
