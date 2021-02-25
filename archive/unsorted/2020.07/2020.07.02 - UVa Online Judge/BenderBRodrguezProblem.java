package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

public class BenderBRodrguezProblem {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        while (true) {
            int l = in.nextInt();
            if (l == 0) {
                break;
            }
            State curr = State.posx;
            for (int i = 0; i < (l-1); i++) {
                curr = nextState(curr, in.nextString());
            }
            out.println(curr);
        }
    }

    private State nextState(State curr, String movement) {
        if ("No".equals(movement)) {
            return curr;
        }
        return curr.next(movement);
    }

    private enum State {
        posx {
            @Override
            public String toString() {
                return "+x";
            }

            @Override
            public State next(String move) {
                switch (move) {
                    case "+y": return State.posy;
                    case "-y": return State.negy;
                    case "+z": return State.posz;
                    case "-z":
                    default: return State.negz;
                }
            }
        }, posy {
            @Override
            public String toString() {
                return "+y";
            }

            @Override
            public State next(String move) {
                switch (move) {
                    case "+y": return State.negx;
                    case "-y": return State.posx;
                    case "+z": return State.posy;
                    case "-z":
                    default: return State.posy;
                }
            }
        }, posz {
            @Override
            public String toString() {
                return "+z";
            }

            @Override
            public State next(String move) {
                switch (move) {
                    case "+y": return State.posz;
                    case "-y": return State.posz;
                    case "+z": return State.negx;
                    case "-z":
                    default: return State.posx;
                }
            }
        }, negx {
            @Override
            public String toString() {
                return "-x";
            }

            @Override
            public State next(String move) {
                switch (move) {
                    case "+y": return State.negy;
                    case "-y": return State.posy;
                    case "+z": return State.negz;
                    case "-z":
                    default: return State.posz;
                }
            }
        }, negy {
            @Override
            public String toString() {
                return "-y";
            }

            @Override
            public State next(String move) {
                switch (move) {
                    case "+y": return State.posx;
                    case "-y": return State.negx;
                    case "+z": return State.negy;
                    case "-z":
                    default: return State.negy;
                }
            }
        }, negz {
            @Override
            public String toString() {
                return "-z";
            }

            @Override
            public State next(String move) {
                switch (move) {
                    case "+y": return State.negz;
                    case "-y": return State.negz;
                    case "+z": return State.posx;
                    case "-z":
                    default: return State.negx;
                }
            }
        };

        public abstract State next(String move);
    }
}

