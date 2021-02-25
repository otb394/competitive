package datastructure;

public class UnionFind {
    private int noOfSets;
    private int[] p;
    private int[] rank;
    private int[] size;

    public UnionFind(int n) {
        p = new int[n];
        this.size = new int[n];
        for (int i = 0; i < n; i++) {
            p[i]=i;
            size[i]=1;
        }
        rank = new int[n];
        this.noOfSets = n;
    }

    public boolean isSameSet(int i, int j) {
        return (findSet(i) == findSet(j));
    }

    public void union(int i, int j) {
        int pi = findSet(i);
        int pj = findSet(j);
        if (pi != pj) {
            link(pi, pj);
        }
    }

    private void link(int i, int j) {
        if (rank[i] > rank[j]) {
            p[j]=i;
            size[i]+=size[j];
        } else {
            p[i]=j;
            size[j]+=size[i];
            if (rank[i] == rank[j]) {
                rank[j]++;
            }
        }
        noOfSets--;
    }

    public int findSet(int i) {
        if (p[i] != i) {
            p[i] = findSet(p[i]);
        }
        return p[i];
    }

    public int getSizeOfSet(int i) {
        return size[findSet(i)];
    }

    public int getNoOfSets() {
        return noOfSets;
    }
}
