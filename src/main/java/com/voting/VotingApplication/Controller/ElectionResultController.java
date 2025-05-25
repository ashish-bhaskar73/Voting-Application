package com.voting.VotingApplication.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.voting.VotingApplication.DTO.ElectionResultRequestDTO;
import com.voting.VotingApplication.DTO.ElectionResultResponseDTO;
import com.voting.VotingApplication.Service.ElectionResultService;
import com.voting.VotingApplication.model.ElectionResult;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/result")
public class ElectionResultController {
    @Autowired
    private ElectionResultService electionResultService;

    @PostMapping("/declare")
    public ResponseEntity<ElectionResultResponseDTO> declareResult(
            @RequestBody @Valid ElectionResultRequestDTO electionResultRequestDTO) {
        ElectionResult result = electionResultService.declareResult(electionResultRequestDTO.getElectionName());
        ElectionResultResponseDTO electionResultResponse = new ElectionResultResponseDTO();
        electionResultResponse.setElectionName(result.getElectionName());
        electionResultResponse.setTotalVote(result.getTotalVotes());
        electionResultResponse.setWinnerId(result.getWinnerId());
        electionResultResponse.setWinnerVote(result.getWinner().getVoteCount());

        return new ResponseEntity<>(electionResultResponse, HttpStatus.OK);

    }

    @GetMapping("/all")
    public ResponseEntity<List<ElectionResult>> getAllResult() {
        List<ElectionResult> election = electionResultService.getAllResult();
        return new ResponseEntity<>(election, HttpStatus.OK);
    }
}
