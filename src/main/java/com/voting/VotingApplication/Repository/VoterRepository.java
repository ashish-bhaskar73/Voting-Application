package com.voting.VotingApplication.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.voting.VotingApplication.model.Voter;

@Repository
public interface VoterRepository extends JpaRepository<Voter, Long> {

    boolean existsByEmail(String email);

    boolean existsById(Long id);
}
