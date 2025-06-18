package org.example.atlassian.voterating.service.impl;


import org.example.atlassian.voterating.service.VoteRanker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VoteRankerImpl implements VoteRanker {
    private final Map<Character, Integer> voteCountMap;

    public VoteRankerImpl() {
        voteCountMap = new HashMap<>();
    }

    @Override
    public List<Character> rankVotes(List<List<Character>> votes) {
        int weight = votes.size();
        for (List<Character> vote : votes) {
            for (int i = 0; i < vote.size(); i++) {
                Character candidate = vote.get(i);
                int score = vote.size() - i;
                voteCountMap.put(candidate, voteCountMap.getOrDefault(candidate, 0) + score);
            }
//            weight--;

        }

        List<Character> result = new ArrayList<>();

        for (Map.Entry<Character, Integer> entry : voteCountMap.entrySet()) {
            result.add(entry.getKey());
        }
        System.out.println(voteCountMap);

        result.sort((a, b) -> voteCountMap.get(b).compareTo(voteCountMap.get(a)));

        return result;
    }
}
