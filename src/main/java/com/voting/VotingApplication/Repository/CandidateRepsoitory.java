package com.voting.VotingApplication.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.voting.VotingApplication.model.Candidate;

@Repository
public interface CandidateRepsoitory extends JpaRepository<Candidate, Long> {
    List<Candidate> findAllByOrderByVoteCountDesc();
}
