package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import code.datastructure.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CalculatingDartScores {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        Solution[] dp = new Solution[181];
        dp[0] = new Solution();
        List<Score> allScores = new ArrayList<>();
        Map<Integer, Score> valueToScore = new HashMap<>();
        for (int i = 1; i <= 20; i++) {
            for (int j = 1; j <= 3; j++) {
                Score score = new Score(i,j);
                allScores.add(score);
                int val = score.getValue();
                List<Score> arr = new ArrayList<>();
                arr.add(score);
//                System.out.println("val = " + val);
                dp[val] = new Solution(arr);
                valueToScore.put(val, score);
            }
        }
        for (int i = 1; i <= n; i++) {
            Solution ans = null;
            if (dp[i] == null) {
                for (int j = 1; j < i; j++) {
                    if (dp[j] != null) {
                        int diff = i - j;
                        Optional<Score> thisScore = Optional.ofNullable(valueToScore.get(diff));
                        if (thisScore.isPresent()) {
                            if (ans == null) {
                                Solution sol = dp[j];
                                ans = sol.plus(thisScore.get());
                            } else {
                                int thisC = ans.getScoreCount();
                                Solution sol = dp[j];
                                int newC = sol.getScoreCount() + 1;
                                if (newC < thisC) {
                                    ans = sol.plus(thisScore.get());
                                }
                            }
                        }
                    }
                }
                dp[i] = ans;
            }
        }
//        for (int i = 1; i <= n; i++) {
//            System.out.println("i = " + i);
//            System.out.println(dp[i]);
//        }
//        for (int i = 0; i < 10; i++) {
//            System.out.println("i = " + i);
//            System.out.println("dp[i] = " + dp[i]);
//        }
        if (dp[n] == null || dp[n].getScoreCount() > 3) {
            out.print("impossible");
        } else {
            out.print(dp[n]);
        }
    }

    public static class Solution {
        private List<Score> scores;

        public Solution() {
            this.scores = new ArrayList<>();
        }

        public Solution(List<Score> scores) {
            this.scores = scores;
        }

        public Solution plus(Score score) {
            List<Score> newScores = new ArrayList<>(scores);
            newScores.add(score);
            return new Solution(newScores);
        }

        public int getScoreCount() {
            return scores.size();
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            for(Score score: scores) {
                builder.append(score.toString()).append("\n");
            }
            return builder.toString();
        }
    }

    public static class Score {
        private Pair<Integer, Integer> pr;

        public Score(int i, int c) {
            this.pr = Pair.of(i,c);
        }

        public int getValue() {
            return pr.getLeft() * pr.getRight();
        }

        @Override
        public String toString() {
            int i = pr.getLeft();
            int c = pr.getRight();
            switch (c) {
                case 1:return "single " + i;
                case 2:return "double " + i;
                case 3:
                default:return "triple " + i;
            }
        }
    }
}
