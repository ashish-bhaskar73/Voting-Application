package com.voting.VotingApplication.Exception;

public class VoteNotAllowedException extends RuntimeException {
    public VoteNotAllowedException(String message) {
        super(message);
    }
}
