package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import util.MiscUtility;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class ContestScoreboard {
    MiscUtility ut = new MiscUtility(false);
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int t = in.nextInt();
        in.readLine(false);
        ut.printDebug(t, "t");
        for (int i = 1; i <= t; i++) {
            slve(i, in, out);
        }
    }

    private void slve(int testNumber, InputReader in, OutputWriter out) {
        ut.printDebug(testNumber, "testNo");
        Map<Integer, Contestant> mp = new HashMap<>();
        List<Input> ilist = new ArrayList<>();
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
            int c = Integer.parseInt(tokens[0]);
            int p = Integer.parseInt(tokens[1]);
            int time = Integer.parseInt(tokens[2]);
            String res = tokens[3];
            ilist.add(new Input(c, p, time, res));
//            Contestant contestant = Optional.ofNullable(mp.get(c)).orElseGet(() -> new Contestant(c));
//            contestant.add(p, time, res);
//            mp.put(c, contestant);
        }
        ilist.sort(Comparator.comparingInt(inp -> inp.t));
        ut.printDebug(ilist, "ilist");
        for (Input inp : ilist) {
            int c = inp.con;
            int p = inp.p;
            int time = inp.t;
            String res = inp.res;
            Contestant contestant = Optional.ofNullable(mp.get(c)).orElseGet(() -> new Contestant(c));
            contestant.add(p, time, res);
            mp.put(c, contestant);
        }
        List<Contestant> list = mp.values().stream().sorted().collect(Collectors.toList());
        if (testNumber != 1) {
            out.println();
        }
//        String output = list.stream().map(Contestant::toString).collect(Collectors.joining(" "));
        for (Contestant con : list) {
            out.println(con);
        }
//        out.println(output);
//        out.println();
    }

    private class Input {
        public int con;
        public int p;
        public int t;
        public String res;


        public Input(int con, int p, int t, String res) {
            this.con = con;
            this.p = p;
            this.t = t;
            this.res = res;
        }

        @Override
        public String toString() {
            return "Input{" +
                    "con=" + con +
                    ", p=" + p +
                    ", t=" + t +
                    ", res='" + res + '\'' +
                    '}';
        }
    }

    private class Contestant implements Comparable<Contestant> {
        public Set<Integer> done;
        public Map<Integer, Integer> probToAtt;
        public int p;
        public int t;
        public int teamNo;

        public Contestant(int c) {
            teamNo = c;
            t = 0;
            p = 0;
            probToAtt = new HashMap<>();
            done = new HashSet<>();
        }

        public void add(int pr, int time, String res) {
            if (done.contains(pr)) {
                return;
            }
            if (res.equals("C")) {
                done.add(pr);
                t += time + probToAtt.getOrDefault(pr, 0) * 20;
                p++;
            } else if (res.equals("I")) {
                int val = probToAtt.getOrDefault(pr, 0);
                probToAtt.put(pr, val + 1);
            }
            ut.printDebug(teamNo, "tNo", p, "p", t, "time");
        }

        @Override
        public int compareTo(Contestant o) {
            Comparator<Contestant> comp = Comparator.comparingInt(con -> con.p);
            comp = comp.reversed();
            comp = comp.thenComparingInt(con -> con.t);
            comp = comp.thenComparingInt(con -> con.teamNo);
            return comp.compare(this, o);
        }

        @Override
        public String toString() {
            return teamNo + " " + p + " " + t;
        }
    }
}
