package com.voting.VotingApplication.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.voting.VotingApplication.Service.VoterService;
import com.voting.VotingApplication.model.Voter;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/voter")
// If we connect with react than @crossOrigin
public class VoterController {

    private final VoterService voterService;

    @Autowired
    public VoterController(VoterService voterService) {
        this.voterService = voterService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Voter>> getAllVoters() {
        List<Voter> allVoters = voterService.getAllVoters();
        return new ResponseEntity<>(allVoters, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Voter> getVoterById(@PathVariable Long id) {
        return new ResponseEntity<>(voterService.getVoterById(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Voter> registerVoter(@RequestBody @Valid Voter voter) {
        Voter savedVoter = voterService.registerVoter(voter);
        return new ResponseEntity<>(savedVoter, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Voter> updateVoter(@PathVariable Long id, @RequestBody Voter voter) {
        return new ResponseEntity<>(voterService.updateVoter(id, voter), HttpStatus.OK);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<String> deleteVoter(@PathVariable Long id) {
        voterService.deleteVoter(id);
        return new ResponseEntity<>("Voter has been deletd with id: " + id, HttpStatus.OK);
    }
}
