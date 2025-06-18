package org.example.atlassian.voterating.service.impl;

import org.example.atlassian.voterating.service.VoteRanker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VoteRankByLeetCodeImpl implements VoteRanker {

    @Override
    public List<Character> rankVotes(List<List<Character>> votes) {
        if (votes==null || votes.isEmpty())
            return new ArrayList<>();
        int n = votes.get(0).size();
        int[][] arr = new int[26][n+1];
        for(int i=0;i<26;i++){
            arr[i][n]=i;
        }

        for(List<Character> vote: votes){
            for(int i=0;i<n;i++){
                char c = vote.get(i);
                arr[c-'A'][i]++;
            }
        }

        Arrays.sort(arr, (a,b)->{
            for(int i=0;i<n;i++){
                if(a[i] != b[i]){
                    return b[i] - a[i];
                }
            }
            return a[n] - b[n]; // If all scores are equal, sort by character
        });
        for(int[] a: arr)
            System.out.println(Arrays.toString(a));
        return List.of();
    }
}
