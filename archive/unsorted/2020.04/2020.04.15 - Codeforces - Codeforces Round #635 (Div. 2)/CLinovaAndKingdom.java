package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import datastructure.AdjacencyList;
import datastructure.Graph;
import datastructure.Tree;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.Collectors;

public class CLinovaAndKingdom {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        Map<Integer,Vertex> vertexMap = new HashMap<>();
        Graph<Vertex, MyEdge> graph = new AdjacencyList<>();
        for (int i = 0; i < (n-1); i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            Vertex ux = Optional.ofNullable(vertexMap.get(u)).orElseGet(() -> new Vertex(u));
            Vertex vx = Optional.ofNullable(vertexMap.get(v)).orElseGet(() -> new Vertex(v));
            vertexMap.put(u, ux);
            vertexMap.put(v, vx);
            graph.addEdge(new MyEdge(ux, vx));
        }
//        graph.getVertices().forEach(System.out::println);
        Tree<TreeVertex> tree = getTree(graph, vertexMap.get(1));
//        tree.stream().forEach(System.out::println);
        List<TreeVertex> vertices = tree.stream().collect(Collectors.toList());
        PriorityQueue<TreeVertex> pq = new PriorityQueue<>(Comparator.comparingInt(TreeVertex::compVal));
        //Don't need to calculate this again.
        List<TreeVertex> leaves = vertices.stream().filter(Tree.TreeNode::isLeaf).collect(Collectors.toList());
        //We may be able to do this in logK, instead of logN, by only maintaining top K elements in pq at any time.
        pq.addAll(leaves);
        int rem = k;
        while (!pq.isEmpty() && (rem > 0)) {
            TreeVertex best = pq.poll();
//            System.out.println("best = " + best);
            best.isBlack = true;
            rem--;
            best.b++;
            Optional<TreeVertex> parent = best.getParent();
            parent.ifPresent(par -> {
                par.b += best.b;
                par.nonBlackChild--;
                if (par.nonBlackChild == 0) {
                    pq.add(par);
                }
//                System.out.println("par = " + par);
            });
        }
//        bubbleUp(tree.root);
        Map<TreeVertex, Integer> outDeg = new HashMap<>();
        for (TreeVertex tv : vertices) {
            outDeg.put(tv, tv.children.size());
        }
        Queue<TreeVertex> q = new LinkedList<>(leaves);
        while (!q.isEmpty()) {
            TreeVertex tv = q.poll();
            int subsum = tv.children.stream().mapToInt(f -> f.b).sum();
            tv.b = (tv.isBlack) ? (subsum + 1) : subsum;
            Optional<TreeVertex> par = tv.getParent();
            par.ifPresent(pr -> {
                int val = outDeg.getOrDefault(pr, 0);
                if (val == 1) {
                    q.add(pr);
                }
                outDeg.put(pr, val-1);
            });
        }
        long ans = vertices.stream().filter(v -> !v.isBlack).mapToLong(v -> v.b).sum();
        out.println(ans);
    }

    private Tree<TreeVertex> getTree(Graph<Vertex, MyEdge> g, Vertex root) {
        Queue<Vertex> queue = new LinkedList<>();
        TreeVertex rt = new TreeVertex(root.index);
        Tree<TreeVertex> tree = new Tree<>(rt);
        Map<Vertex, TreeVertex> mp = new HashMap<>();
        mp.put(root, rt);
        queue.add(root);
        while (!queue.isEmpty()) {
            Vertex u = queue.poll();
            List<Vertex> neighbors = g.getEdges(u).stream().map(e -> e.second).collect(Collectors.toList());
            TreeVertex ut = mp.get(u);
            for (Vertex v : neighbors) {
                Optional<TreeVertex> parent = ut.getParent().filter(tt -> tt.index == v.index);
                if (parent.isPresent()) {
                    continue;
                }
                TreeVertex vt = new TreeVertex(1, ut.height+1, ut, v.index);
                tree.addChild(ut, vt);
                ut.nonBlackChild++;
                mp.put(v, vt);
                queue.add(v);
            }
        }
        return tree;
    }

    private static class TreeVertex extends Tree.TreeNode<TreeVertex> {
        public int b;
        public boolean isBlack;
        public int nonBlackChild;
        public int index;
        public TreeVertex(int index) {
            super();
            this.b = 0;
            this.isBlack = false;
            this.nonBlackChild = 0;
            this.index = index;
        }

        public TreeVertex(int size, int height, TreeVertex parent, int index) {
            super(size, height, parent);
            this.b = 0;
            this.isBlack = false;
            this.nonBlackChild = 0;
            this.index = index;
        }

        public int compVal() {
            int a = height+1;
            return -(a-b-1);
        }

        @Override
        public String toString() {
            return "TreeVertex{" +
                    "b=" + b +
                    ", index=" + index +
                    ", parent.index=" + getParent().map(pr -> Integer.toString(pr.index)).orElse("null")+
                    ", isBlack=" + isBlack +
                    ", nonBlackChild=" + nonBlackChild +
                    ", size=" + size +
                    ", height=" + height +
                    '}';
        }
    }

    private static class Vertex {
        public int index;

        @Override
        public String toString() {
            return "Vertex{" +
                    "index=" + index +
                    '}';
        }

        public Vertex(int index) {
            this.index = index;
        }
    }

    private static class MyEdge extends Graph.Edge<Vertex, MyEdge> {
        public MyEdge(Vertex first, Vertex second) {
            super(first, second);
        }

        @Override
        public MyEdge reverse() {
            return new MyEdge(second, first);
        }
    }
}
