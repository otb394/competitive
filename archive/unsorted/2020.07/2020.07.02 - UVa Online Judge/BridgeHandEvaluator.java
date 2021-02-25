package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import util.MiscUtility;

import java.util.ArrayList;

public class BridgeHandEvaluator {
    char[] indexToSuit = new char[]{'H', 'D', 'S', 'C'};
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        while (!in.isExhausted()) {
            int[] cards = new int[13];
            for (int i = 0; i < 13; i++) {
                cards[i] = getIndex(in.nextString());
            }
            int points = 0;
            int[] suitsCounts = new int[4];
            boolean[] stopped = new boolean[4];
            for (int i = 0; i < 13; i++) {
                if (isKing(cards[i])) points+=3;
                else if (isAce(cards[i])) points+=4;
                else if (isQueen(cards[i])) points+=2;
                else if (isJack(cards[i])) points++;
                int suit = getSuit(cards[i]);
                suitsCounts[suit]++;
            }
            for (int i = 0; i < 13; i++) {
                int suit = getSuit(cards[i]);
                if (isKing(cards[i]) && suitsCounts[suit] == 1) {
                    points--;
                } else if (isQueen(cards[i]) && suitsCounts[suit] <= 2) {
                    points--;
                } else if (isJack(cards[i]) && suitsCounts[suit] <= 3) {
                    points--;
                }
            }
            int tpoints = points;
            for (int i = 0; i < 4; i++) {
                if (suitsCounts[i] == 2) points++;
                else if (suitsCounts[i] == 1) points += 2;
                else if (suitsCounts[i] == 0) points += 2;
            }
            for (int i = 0; i < 13; i++) {
                int suit = getSuit(cards[i]);
                if (isAce(cards[i]) || (isKing(cards[i]) && suitsCounts[suit] >= 2) || (isQueen(cards[i]) && suitsCounts[suit] >= 3)) {
                    stopped[suit] = true;
                }
            }
            if (points < 14) {
                out.println("PASS");
            } else {
                if (tpoints >= 16) {
                    boolean flag = true;
                    for (int i = 0; i < 4; i++) {
                        if (!stopped[i]) {
                            flag = false;
                            break;
                        }
                    }
                    if (flag) {
                        out.println("BID NO-TRUMP");
                        continue;
                    }
                }
                int[] order = new int[]{2, 0, 1, 3};
                int ansSuit = 2;
                int ansCount = suitsCounts[2];
                for (int i = 1; i < 4; i++) {
                    int suit = order[i];
                    if (suitsCounts[suit] > ansCount) {
                        ansCount = suitsCounts[suit];
                        ansSuit = suit;
                    }
                }
                out.printf("BID %c\n", indexToSuit[ansSuit]);
            }
        }
    }

    // HDSC
    private int getSuit(int index) {
        return index/13;
    }

    private boolean isJack(int index) {
        return index % 13 == 10;
    }

    private boolean isQueen(int index) {
        return index % 13 == 11;
    }

    private boolean isAce(int index) {
        return index % 13 == 0;
    }

    private boolean isKing(int index) {
        return index % 13 == 12;
    }

    private int getIndex(String card) {
        MiscUtility.assertion(card.length() == 2, String.format("card [%s] is not of length 2", card));
        char suit = card.charAt(1);
        int suitIndex = 0;
        for (int i = 1; i < 4; i++) {
            if (indexToSuit[i] == suit) {
                suitIndex = i;
            }
        }
        int base = suitIndex * 13;
        char rank = card.charAt(0);
        return base + getRankIndex(rank);
    }

    private int getRankIndex(char rank) {
        if (rank >= '2' && rank <= '9') return (rank - '0')-1;
        else if (rank == 'A') return 0;
        else if (rank == 'T') return 9;
        else if (rank == 'J') return 10;
        else if (rank == 'Q') return 11;
        else if (rank == 'K') return 12;
        throw new RuntimeException("Unrecognized rank = " + rank);
    }
}
