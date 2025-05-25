package com.voting.VotingApplication.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.voting.VotingApplication.model.Vote;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

}
