package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import datastructure.AdjacencyList;
import datastructure.Graph;
import datastructure.Pair;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.Stack;

public class StableWall {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int r=in.nextInt();
        int c=in.nextInt();
        String[] map = new String[r];
        for (int i = 0; i < r; i++) {
            map[i] = in.nextString();
        }

        Set<Character> st = new HashSet<>();
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                st.add(map[i].charAt(j));
            }
        }
        Graph<Character, MyEdge> g = new AdjacencyList<>(true);
        for (Character ch: st) {
            g.addVertex(ch);
        }

        Set<Pair<Character,Character>> id=new HashSet<>();

        for (int i = 0; i < (r-1); i++) {
            for (int j = 0; j < c; j++) {
                Character f = map[i].charAt(j);
                Character s = map[i+1].charAt(j);
                if (!id.contains(Pair.of(f,s)) && f!=s) {
//                    System.out.println("f = " + f);
//                    System.out.println("s = " + s);
                    g.addEdge(new MyEdge(f,s));
                }
                id.add(Pair.of(f,s));
            }
        }

        boolean isCyclic = isCyclic(g);
        if (isCyclic) {
            out.println(String.format("Case #%d: -1", testNumber));
        } else {
            List<Character> ret = topSort(g);
            out.print(String.format("Case #%d: ", testNumber));
            for (Character ch: ret) {
                out.print(ch);
            }
            out.println();
        }
    }

    private boolean isCyclic(Graph<Character,MyEdge> g) {
        Set<Character> dis=new HashSet<>();
        Set<Character> recst= new HashSet<>();
        List<Character> v=g.getVertices();
        for (Character c: v) {
            if (isCyclicUtil(g,dis,recst,c)) {
                return true;
            }
        }
//        for (Character c: v) {
//            if (!dis.contains(c)) {
//                dis.add(c);
//                boolean flag = isCyclicUtil(g,dis,c);
//                if (flag) return true;
//            }
//        }
        return false;
    }

    private boolean isCyclicUtil(Graph<Character,MyEdge> g, Set<Character> dis,Set<Character> rec, Character s) {
        if(rec.contains(s)) return true;
        if (dis.contains(s)) return false;
        dis.add(s);
        rec.add(s);
        List<MyEdge> edges = g.getEdges(s);
        for (MyEdge edge: edges) {
            if (isCyclicUtil(g,dis,rec,edge.second)) {
                return true;
            }
//            if (dis.contains(edge.second)) {
//                return true;
//            }
//            dis.add(edge.second);
//            boolean flag = isCyclicUtil(g, dis, edge.second);
//            if (flag) return true;
        }
        rec.remove(s);
        return false;
    }

//    private boolean isCyclicUtil(Graph<Character,MyEdge> g, Set<Character> dis, Character source) {
//        List<MyEdge> edges = g.getEdges(source);
//        for (MyEdge edge: edges) {
//            if (dis.contains(edge.second)) {
//                return true;
//            }
//            dis.add(edge.second);
//            boolean flag = isCyclicUtil(g, dis, edge.second);
//            if (flag) return true;
//        }
//        return false;
//    }

    private List<Character> topSort(Graph<Character,MyEdge> g) {
        Stack<Character> st = new Stack<>();
        List<Character> v=g.getVertices();
        Set<Character> dis=new HashSet<>();
        for (Character c: v) {
            if(!dis.contains(c)) {
                dis.add(c);
                topSortUtil(g,c,st,dis);
            }
        }
        List<Character> ret = new ArrayList<>();
        Set<Character> pres = new HashSet<>();
        while (!st.isEmpty()) {
            Character ch = st.pop();
            if (!pres.contains(ch)) {
                ret.add(ch);
                pres.add(ch);
            }
        }
        return ret;
    }

    private void topSortUtil(Graph<Character,MyEdge> g, Character s, Stack<Character> st, Set<Character> dis) {
        st.push(s);
        List<MyEdge> edges=g.getEdges(s);
        for(MyEdge edge:edges) {
            dis.add(edge.second);
            topSortUtil(g,edge.second,st,dis);
        }
    }

    private static class MyEdge extends Graph.Edge<Character,MyEdge> {
        public MyEdge(Character first, Character second) {
            super(first, second);
        }

        @Override
        public MyEdge reverse() {
            return new MyEdge(second, first);
        }
    }
}
