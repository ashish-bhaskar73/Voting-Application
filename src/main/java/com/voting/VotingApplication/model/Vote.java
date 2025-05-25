package com.voting.VotingApplication.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "voter_id", unique = true)
    @JsonIgnore
    private Voter voter;

    @ManyToOne
    @JoinColumn(name = "candidate_id")
    @JsonIgnore
    private Candidate candidate;

    @JsonProperty("voterId")
    public Long getVoterId() {
        return voter != null ? voter.getId() : null;
    }

    @JsonProperty("candidateId")
    public Long getCandidateId() {
        return candidate != null ? candidate.getId() : null;
    }
}
