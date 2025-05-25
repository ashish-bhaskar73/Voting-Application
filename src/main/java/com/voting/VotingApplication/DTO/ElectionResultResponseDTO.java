package com.voting.VotingApplication.DTO;

import lombok.Data;

@Data
public class ElectionResultResponseDTO {
    private String electionName;
    private int totalVote;
    private Long winnerId;
    private int winnerVote;

}
