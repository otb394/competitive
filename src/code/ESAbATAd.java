package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import datastructure.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ESAbATAd {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int t = in.nextInt();
        int b = in.nextInt();
        while((t--) > 0) {
            int queryCount = 0;
            int found = 0;
            int x = 0;
            List<IndPair> indPairs = new ArrayList<>();
            List<IndPair> indicativeIndPairs = new ArrayList<>();
            while (queryCount < 150 && found < b) {
                ReturnNode node = recover(x, indPairs, indicativeIndPairs, in, out);
                int newQueries = node.queries;
                indPairs = node.indPairs;
                indicativeIndPairs = node.indicativePairs;
                int remq = 10 - newQueries;
                //remq always 10 or 8
                int deltaX = remq/2;
                for (int i = 1; i <= deltaX; i++) {
                    int startIndex = x+i;
                    int lastIndex = b-startIndex+1;
                    int first = query(startIndex, in, out);
                    int second = query(lastIndex, in, out);
                    queryCount+=2;
                    PairType type;
                    if (first == 0 && second == 0) {
                        type = PairType.BO;
                    } else if (first == 0 && second == 1) {
                        type = PairType.FO;
                    } else if (first == 1 && second == 0) {
                        type = PairType.FI;
                    } else {
                        type = PairType.BI;
                    }
                    IndPair indPair = new IndPair(type, startIndex);
                    indPairs.add(indPair);
                    found+=2;
                    int indLen = indicativeIndPairs.size();
                    if (indLen == 0) {
                        indicativeIndPairs.add(indPair);
                    } else if (indLen == 1) {
                        IndPair indi = indicativeIndPairs.get(0);
                        if (PairType.conflict(type, indi.pairType)) {
                            indicativeIndPairs.add(indPair);
                        }
                    }
                    if (found >= b) {
                        break;
                    }
                }
            }
        }
    }

    //Always returns 0 or 2. May have to waste a query
    private ReturnNode recover(int x, List<IndPair> indPairs, List<IndPair> indicativeIndPairs,
                        InputReader in, OutputWriter out) {
        if (x == 0) {
            return new ReturnNode(indPairs, indicativeIndPairs, 0);
        }
        int indLen = indicativeIndPairs.size();
        if (indLen == 1) {
            Action action = indicativeIndPairs.get(0).getAction(in,out).getLeft();
            indPairs = indPairs.stream().map(p -> p.apply(action)).collect(Collectors.toList());
            indicativeIndPairs = indicativeIndPairs.stream().map(p -> p.apply(action)).collect(Collectors.toList());
            //Dummy
            query(1, in, out);
            return new ReturnNode(indPairs, indicativeIndPairs, 2);
        } else if (indLen == 2) {
            Pair<Action,Action> actions = indicativeIndPairs.get(0).getAction(in,out);
            Pair<Action,Action> action2 = indicativeIndPairs.get(1).getAction(in,out);
            Action action = getCommon(actions, action2);
            indPairs = indPairs.stream().map(p -> p.apply(action)).collect(Collectors.toList());
            indicativeIndPairs = indicativeIndPairs.stream().map(p -> p.apply(action)).collect(Collectors.toList());
            return new ReturnNode(indPairs, indicativeIndPairs, 2);
        }
        return null;
    }

    private Action getCommon(Pair<Action,Action> a1, Pair<Action,Action> a2) {
        if (a1.getLeft() == a2.getLeft()) {
            return a1.getLeft();
        } else if (a1.getLeft() == a2.getRight()) {
            return a1.getLeft();
        } else if (a1.getRight() == a2.getLeft()) {
            return a1.getRight();
        } else {
            return a1.getRight();
        }
    }

    private enum PairType {
        BO {
            @Override
            public PairType apply(Action action) {
                if (action == Action.SAME || action == Action.REV) {
                    return BO;
                }
                return BI;
            }
        },
        BI {
            @Override
            public PairType apply(Action action) {
                if (action == Action.SAME || action == Action.BOTH) {
                    return BI;
                }
                return BO;
            }
        },
        FO {
            @Override
            public PairType apply(Action action) {
                if (action == Action.SAME || action == Action.BOTH) {
                    return FO;
                }
                return FI;
            }
        },
        FI {
            @Override
            public PairType apply(Action action) {
                if (action == Action.SAME || action == Action.BOTH) {
                    return FI;
                }
                return FO;
            }
        };

        public static boolean conflict(PairType t1, PairType t2) {
            if (t1 == BO || t1 == BI) {
                return t2 == FO || t2 == FI;
            } else {
                return t2 == BO || t2 == BI;
            }
        }

        public abstract PairType apply(Action action);
    }

    private enum Action {
        SAME, COMP, REV, BOTH
    }

    private static class IndPair {
        public PairType pairType;
        //Index of left bit
        public int index;

        public IndPair(PairType pairType, int index) {
            this.pairType = pairType;
            this.index = index;
        }

        public Pair<Action,Action> getAction(InputReader in, OutputWriter out) {
            int bit = query(index, in, out);
            if (pairType == PairType.BO) {
                if (bit == 0) {
                    return Pair.of(Action.SAME, Action.REV);
                } else {
                    return Pair.of(Action.COMP, Action.BOTH);
                }
            } else if (pairType == PairType.BI) {
                if (bit == 1) {
                    return Pair.of(Action.SAME, Action.REV);
                } else {
                    return Pair.of(Action.COMP, Action.BOTH);
                }
            } else if (pairType == PairType.FO) {
                if (bit == 0) {
                    return Pair.of(Action.SAME, Action.BOTH);
                } else {
                    return Pair.of(Action.COMP, Action.REV);
                }
            } else {
                if (bit == 1) {
                    return Pair.of(Action.SAME, Action.BOTH);
                } else {
                    return Pair.of(Action.COMP, Action.REV);
                }
            }
        }

        public IndPair apply(Action action) {
            return new IndPair(pairType.apply(action), index);
        }
    }

    private static int query(int index, InputReader in, OutputWriter out) {
        out.println(index);
        out.flush();
        return in.nextInt();
    }

    private static class ReturnNode {
        public List<IndPair> indPairs;
        public List<IndPair> indicativePairs;
        public int queries;

        public ReturnNode(List<IndPair> indPairs, List<IndPair> indicativePairs, int queries) {
            this.indPairs = indPairs;
            this.indicativePairs = indicativePairs;
            this.queries = queries;
        }
    }
}
