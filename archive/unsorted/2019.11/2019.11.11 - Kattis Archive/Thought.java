package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Thought {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        Map<Integer, String> ref = new HashMap<>();
        Expression ex = new Expression();
        for (int i = 0; i < 64; i++) {
            int ans = ex.evaluate();
            ref.put(ans, ex.toString());
            ex = ex.next();
        }
        int m = in.nextInt();
        for (int t = 0; t < m; t++) {
            int n=in.nextInt();
            out.println(ref.getOrDefault(n, "no solution"));
        }
    }

    private static class Expression {
        public static char[] OPERATORS = {'/','*','+','-'};
        int[] myOperators;

        public Expression() {
            myOperators = new int[]{0, 0, 0};
        }

        public Expression(int[] newOp) {
            this.myOperators = newOp;
        }

        public Expression next() {
            int i = 2;
            int[] newOp = new int[3];
            for (int j = 0; j < 3; j++) {
                newOp[j] = myOperators[j];
            }
            while(i>=0) {
                if (newOp[i] == 3) {
                    newOp[i] = 0;
                    i--;
                } else {
                    newOp[i]++;
                    break;
                }
            }
            return new Expression(newOp);
        }

        public int evaluate() {
            Stack<Integer> valueStack = new Stack<>();
            Stack<Integer> operatorStack = new Stack<>();
            for (int i = 0; i < 3; i++) {
                valueStack.push(4);
                int thisOp = myOperators[i];
                while(!operatorStack.empty() && thisOp >= operatorStack.peek()) {
                    int topOp = operatorStack.pop();
                    int op2= valueStack.pop();
                    int op1 = valueStack.pop();
                    int val = apply(op1,op2,topOp);
                    valueStack.push(val);
                }
                operatorStack.push(thisOp);
            }
            valueStack.push(4);
            while(!operatorStack.empty()) {
                int op = operatorStack.pop();
                int op2 = valueStack.pop();
                int op1 = valueStack.pop();
                valueStack.push(apply(op1, op2, op));
            }
            int ans = valueStack.pop();
            if (!valueStack.isEmpty()) {
                throw new RuntimeException("Incorrect expression");
            }
            return ans;
        }

        private int apply(int a, int b, int op) {
            switch (op) {
                case 0:return a/b;
                case 1:return a*b;
                case 2:return a+b;
                case 3:
                default: return a-b;
            }
        }

        @Override
        public String toString() {
            int ans = evaluate();
            return "4 " + OPERATORS[myOperators[0]] + " 4 " + OPERATORS[myOperators[1]] + " 4 "
                    + OPERATORS[myOperators[2]] + " 4 = " + ans;
        }
    }
}
