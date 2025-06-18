package org.example.atlassian.voterating.service;

import java.util.List;

public interface VoteRanker {
    List<Character> rankVotes(List<List<Character>> votes);
}
