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

import com.voting.VotingApplication.DTO.VoteRequestDTO;
import com.voting.VotingApplication.DTO.VoteResponseDTO;
import com.voting.VotingApplication.Service.VoteService;
import com.voting.VotingApplication.model.Vote;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/vote")
public class VoteController {

    private final VoteService voteService;

    @Autowired
    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @PostMapping("/castVote")
    public ResponseEntity<VoteResponseDTO> voteCaste(@RequestBody @Valid VoteRequestDTO voteRequest) {
        Vote vote = voteService.voteCaste(voteRequest.getVoterId(), voteRequest.getCandidateId());
        VoteResponseDTO voteResponseDTO = new VoteResponseDTO("vote casted successsfully", true, vote.getVoterId(),
                vote.getCandidateId());
        return new ResponseEntity<>(voteResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Vote>> getAllVotes() {
        List<Vote> vote = voteService.getAllVotes();
        return new ResponseEntity<>(vote, HttpStatus.OK);
    }
}
