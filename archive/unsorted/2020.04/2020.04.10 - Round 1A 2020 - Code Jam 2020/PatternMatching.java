package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import datastructure.Pair;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PatternMatching {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n=in.nextInt();
        List<String> p = new ArrayList<>();
//        int completedIndex = -1;
        for (int i = 0; i < n; i++) {
            String s = in.nextString();
//            if (isComplete(s)) {
//                completedIndex = i;
//            }
            p.add(s);
        }
        String ans = p.stream().map(this::rem).collect(Collectors.joining(""));
        List<String> starters = p.stream().filter(this::start).collect(Collectors.toList());
        int sLen = starters.size();
        List<String> enders = p.stream().filter(this::end).collect(Collectors.toList());
        int eLen = enders.size();
        //.out.println("eLen = " + eLen);
        //.out.println("sLen = " + sLen);
        Optional<String> pre = getStart(starters);
        if (!pre.isPresent()) {
            //.out.println("Pre is empty");
        }
        Optional<String> post = getEnd(enders);
        if (!post.isPresent()) {
            //.out.println("Post is empty");
        }
        if (!pre.isPresent() || !post.isPresent()) {
            out.println(String.format("Case #%d: *", testNumber));
        } else {
            String rans = pre.get() + ans + post.get();
            out.println(String.format("Case #%d: %s", testNumber, rans));
        }
    }

//    private boolean isComplete(String s) {
//        return !s.contains("*");
//    }

    private Optional<String> getStart(List<String> starters) {
        List<String> starts = starters.stream().map(this::getPre).collect(Collectors.toList());
        int ns = starts.size();
        for (int i = 0; i < ns; i++) {
            //.out.println("starts.get(i) = " + starts.get(i));
        }
        Comparator<Pair<String,Integer>> comp = Comparator.comparing(Pair::getRight);
        Optional<String> maxString = starts.stream().map(s -> Pair.of(s,s.length())).max(comp).map(Pair::getLeft);
        if (!maxString.isPresent()) {
            return Optional.of("");
        } else {
            String maxStr = maxString.get();
            //.out.println("maxStr = " + maxStr);
            int n = starts.size();
            for (int i = 0; i < n; i++) {
                String thisStr = starts.get(i);
                //.out.println("thisStr = " + thisStr);
                int tlen = thisStr.length();
                //.out.println("tlen = " + tlen);
                if (!thisStr.equals(maxStr.substring(0, tlen))) {
                    return Optional.empty();
                }
            }
            return Optional.ofNullable(maxStr);
        }
    }

    private Optional<String> getEnd(List<String> enders) {
        List<String> ends = enders.stream().map(this::getPost).collect(Collectors.toList());
        Comparator<Pair<String,Integer>> comp = Comparator.comparing(Pair::getRight);
        Optional<String> maxString = ends.stream().map(s -> Pair.of(s,s.length())).max(comp).map(Pair::getLeft);
        if (!maxString.isPresent()) {
            return Optional.of("");
        } else {
            String maxStr = maxString.get();
            //.out.println("maxStr = " + maxStr);
            int maxN = maxStr.length();
            //.out.println("maxN = " + maxN);
            int n = ends.size();
            for (int i = 0; i < n; i++) {
                String thisStr = ends.get(i);
                //.out.println("thisStr = " + thisStr);
                int tlen = thisStr.length();
                //.out.println("tlen = " + tlen);
                if (!thisStr.equals(maxStr.substring(maxN-tlen, maxN))) {
                    return Optional.empty();
                }
            }
            return Optional.ofNullable(maxStr);
        }
    }

    private String getPost(String s) {
        StringBuilder builder = new StringBuilder();
        int len = s.length();
        for (int i = len-1; i >= 0; i--) {
            char c = s.charAt(i);
            if (c == '*') {
                return builder.reverse().toString();
            } else {
                builder.append(c);
            }
        }
        return builder.reverse().toString();
    }

    private String getPre(String s) {
        StringBuilder builder = new StringBuilder();
        int len = s.length();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (c == '*') {
                return builder.toString();
            } else {
                builder.append(c);
            }
        }
        return builder.toString();
    }

    private boolean start(String s) {
        return s.charAt(0) != '*';
    }

    private boolean end(String s) {
        int len = s.length();
        return s.charAt(len-1) != '*';
    }

    private String rem(String str) {
        StringBuilder build = new StringBuilder();
        int n = str.length();
        for (int i = 0; i < n; i++) {
            if (str.charAt(i) != '*') {
                build.append(str.charAt(i));
            }
        }
        return build.toString();
    }
}
