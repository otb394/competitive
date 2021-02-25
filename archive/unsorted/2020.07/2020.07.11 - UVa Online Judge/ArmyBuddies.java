package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import datastructure.CustomLinkedList;
import util.MiscUtility;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ArmyBuddies {
    MiscUtility ut = new MiscUtility(false);
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        while (true) {
            int s = in.nextInt();
            int b = in.nextInt();
            if (s == 0 && b == 0) {
                break;
            }
            List<CustomLinkedList.Node<Integer>> nodes = new ArrayList<>();
            CustomLinkedList<Integer> linkedList = new CustomLinkedList<>();
            for (int i = 1; i <= s; i++) {
                linkedList.add(i);
            }
            CustomLinkedList.Node<Integer> curr = linkedList.getFirst();
            nodes.add(null);
            while (curr != null) {
                nodes.add(curr);
                curr = curr.getNext();
            }

            ut.printDebug(s, "s", b, "b", nodes, "nodes", linkedList, "list");

            for (int i = 0; i < b; i++) {
                int l = in.nextInt();
                int r = in.nextInt();
                String sl = Optional.ofNullable(nodes.get(l).getPrevious()).map(nd -> Integer.toString(nd.getElement()))
                        .orElse("*");
                String sr = Optional.ofNullable(nodes.get(r).getNext()).map(nd -> Integer.toString(nd.getElement()))
                        .orElse("*");
                out.println(sl + " " + sr);
                curr = nodes.get(l);
                CustomLinkedList.Node<Integer> rn = nodes.get(r);
                while (curr != rn) {
                    CustomLinkedList.Node<Integer> nx = curr.getNext();
                    linkedList.remove(curr);
                    curr = nx;
                }
                linkedList.remove(curr);
            }
            out.println("-");
        }
    }
}
