package com.voting.VotingApplication.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ElectionResultRequestDTO {
    @NotBlank(message = "election name is required")
    private String electionName;
}