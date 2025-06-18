package org.example.atlassian.voterating;

import org.example.atlassian.voterating.service.VoteRanker;
import org.example.atlassian.voterating.service.impl.VoteRankByLeetCodeImpl;
import org.example.atlassian.voterating.service.impl.VoteRankerImpl;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

public class Main {

    public String rankTeams(String[] votes) {
        int n = votes[0].length();
        int[][] arr = new int[26][n + 1];

        for (int i = 0; i < 26; i++)
            arr[i][n] = i; // Store team ID to break ties

        int maxWeight = votes.length;

        for (int voteIndex = 0; voteIndex < votes.length; voteIndex++) {
            String vote = votes[voteIndex];
            int weight = maxWeight - voteIndex;

            for (int pos = 0; pos < vote.length(); pos++) {
                char team = vote.charAt(pos);
                arr[team - 'A'][pos] += weight;
            }
        }

        Arrays.sort(arr, (a, b) -> {
            for (int i = 0; i < n; i++) {
                if (a[i] != b[i])
                    return b[i] - a[i];
            }
            return a[n] - b[n]; // Tie-breaker: lex order
        });

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append((char) (arr[i][n] + 'A'));
        }

        return sb.toString();
    }
    public static void main(String[] args) {

        List<List<Character>> voteList = Arrays.asList(
                Arrays.asList('A', 'B', 'C'),
                Arrays.asList('A', 'C', 'D'),
                Arrays.asList('D','A', 'C')
        );
        VoteRanker voteRanker = new VoteRankByLeetCodeImpl();
        System.out.println(voteRanker.rankVotes(voteList));
        String[] votes = {"ABC", "ACD", "DAC"};

        Main main = new Main();
        String result = main.rankTeams(votes);
        System.out.println(result); // Output: "ACD"

        System.out.println(System.currentTimeMillis());
        System.out.println(System.nanoTime());
        System.out.println(Instant.now());
        System.out.println(Instant.now().toEpochMilli());

    }
}
