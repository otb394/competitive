package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import datastructure.UnionFind;

import java.util.ArrayList;
import java.util.List;

public class NetworkConnections {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int t = in.nextInt();
        in.readLine(false);
        for (int i = 1; i <= t; i++) {
            slve(i, in, out);
        }
    }

    private void slve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        List<Input> inputs = new ArrayList<>();
        while (true) {
            String s;
            try {
                s = in.readLine(false);
            } catch (Exception e) {
                break;
            }
            if (s.trim().equals("")) {
                break;
            }
            String[] tokens = s.split("\\s+");
            inputs.add(new Input(tokens[0], Integer.parseInt(tokens[1])-1, Integer.parseInt(tokens[2])-1));
        }
        UnionFind ufd = new UnionFind(n);
        int s = 0;int f = 0;
        for (Input input : inputs) {
            if (input.type.equals("c")) {
                ufd.union(input.i, input.j);
            } else {
                if (ufd.isSameSet(input.i, input.j)) {
                    s++;
                } else {
                    f++;
                }
            }
        }
        if (testNumber != 1) {
            out.println();
        }
        out.printf("%d,%d\n", s, f);
    }

    private class Input {
        public String type;
        public int i;
        public int j;

        public Input(String type, int i, int j) {
            this.type = type;
            this.i = i;
            this.j = j;
        }
    }
}
