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

import com.voting.VotingApplication.Service.CandidateService;
import com.voting.VotingApplication.model.Candidate;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/Candidate")
public class CandidateController {
    @Autowired
    CandidateService candidateService;

    @PostMapping("/add")
    public ResponseEntity<Candidate> registerCandidate(@RequestBody Candidate candidate) {
        return new ResponseEntity<>(candidateService.registerCandidate(candidate), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Candidate>> getAllCandidate() {
        List<Candidate> candidate = candidateService.getAllCandidate();
        return new ResponseEntity<>(candidate, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Candidate> getCandidateById(@PathVariable Long id) {
        return new ResponseEntity<>(candidateService.getCandidateById(id), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Candidate> updateCandidate(@PathVariable Long id, @RequestBody @Valid Candidate candidate) {
        Candidate updatedCandidate = candidateService.updateCandidate(id, candidate);
        return new ResponseEntity<>(updatedCandidate, HttpStatus.OK);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<String> deleteCandidate(@PathVariable Long id) {
        candidateService.deleteCandidate(id);
        return new ResponseEntity<>("Candidate Deleted by id:" + id, HttpStatus.OK);
    }
}
