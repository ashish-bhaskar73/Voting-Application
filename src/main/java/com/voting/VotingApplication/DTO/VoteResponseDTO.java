package com.voting.VotingApplication.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoteResponseDTO {

    private String message;
    private boolean success;
    private Long voteId;
    private Long candidateId;
}
