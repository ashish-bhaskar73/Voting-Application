package com.voting.VotingApplication.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.voting.VotingApplication.model.ElectionResult;

@Repository
public interface ElectionRepository extends JpaRepository<com.voting.VotingApplication.model.ElectionResult, Long> {
    Optional<ElectionResult> findByElectionName(String electionName);
}
