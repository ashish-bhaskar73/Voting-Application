package com.voting.VotingApplication.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class VoteRequestDTO {
    @NotNull(message = "voter id is required")
    Long voterId;
    @NotNull(message = "Candidate id is required")
    Long candidateId;
}
