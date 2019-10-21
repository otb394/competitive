package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class D1TheWorldIsJustAProgrammingTaskEasyVersion {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n=in.nextInt();
        String s=in.nextString();
        if (s.length() != n) {
            out.print("Invalid input");
            return;
        }
        long countOpen = s.chars().filter(c -> c=='(').count();
        if ((2*countOpen) != n) {
            out.println(0);
            out.print(1,1);
            return;
        }
        int ans=getAns(s,n);
        int mi=0,mj=0;
        for (int i = 0; i < n-1; i++) {
            for (int j = i+1; j < n; j++) {
                String ns=swap(i,j,s);
//                System.out.println("i = " + i);
//                System.out.println("j = " + j);
                int newAns=getAns(ns,n);
                if (newAns>ans) {
                    ans=newAns;mi=i;mj=j;
                }
            }
        }
        out.println(ans);
        out.print(mi+1,mj+1);
    }

    private int getAns(String s, int n) {
        int[] a=new int[n];
        for (int i = 0; i < n; i++) {
            a[i]=(s.charAt(i)=='(')?1:-1;
        }
        int[] cum=new int[n];
        cum[0]=a[0];
        for (int i = 1; i < n; i++) {
            cum[i]=cum[i-1]+a[i];
        }
        int[] minPref=new int[n];
        minPref[0]=cum[0];
        for (int i = 1; i < n; i++) {
            minPref[i]=Math.min(minPref[i-1],cum[i]);
        }
        int[] minCumSuff=new int[n];
        minCumSuff[n-1]=cum[n-1];
        for (int i = n-2; i >=0; i--) {
            minCumSuff[i]=Math.min(minCumSuff[i+1],cum[i]);
        }
//        System.out.println("a = " + Arrays.toString(a));
//        System.out.println("cum = " + Arrays.toString(cum));
//        System.out.println("minCumSuff = " + Arrays.toString(minCumSuff));
//        System.out.println("minPref = " + Arrays.toString(minPref));
        int ans=(minCumSuff[0]>=0)?1:0;
        for (int i = 1; i < n; i++) {
            if (minCumSuff[i] >= cum[i-1]) {
                int bfr=cum[n-1]-cum[i-1];
                if(minPref[i-1]>=(-bfr)) ans++;
            }
        }
//        System.out.println("s = " + s);
//        System.out.println("n = " + n);
//        System.out.println("ans = " + ans);
        return ans;
    }

//    private int getAns(String s, int n) {
//        long countOpen = s.chars().filter(c -> c == '(').count();
//        if ((2*countOpen) != n) {
//            return 0;
//        }
//        int ans = 0;
//        int[] val = new int[n];
//        val[0]=(s.charAt(0)=='(')?(1):0;
//        for (int i = 1; i < n; i++) {
//            if (s.charAt(i) == '(') val[i]=val[i-1]+1;
//            else val[i]=val[i-1]-1;
//        }
//        Map<Integer,Integer> mp=new HashMap<>();
//        for (int i = 0; i < n; i++) {
//            int v=val[i];
//            if (!(Optional.ofNullable(mp.get(v)).isPresent())) {
//                mp.put(v,i);
//            }
//        }
//        int[] f=new int[(int)countOpen+1];
//        for (int i = 0; i <=countOpen; i++) {
//            int v=-1-i;
//            Optional<Integer> ind = Optional.ofNullable(mp.get(v));
//            int index = ind.orElse(n);
//            f[i]=index;
//        }
//        int op=0;
//        int[] min=new int[n];
//        min[n-1]=val[n-1];
//        for (int i = n-2; i >= 0; i++) {
//            min[i]=Math.min(val[i],min[i+1]);
//        }
//        if (min[0] >= 0) {
//            ans = 1;
//        }
//        for (int j = 1; j < n; j++) {
//            int index=n-j;
//            int prev=val[index-1];
//            int after=
//            if (s.charAt(index) == '(' && min[index] >= (-prev) && ) {
//                ans++;
//            }
//        }
//        return ans;
//    }

    private String swap(int i,int j,String s) {
        char[] c=s.toCharArray();
        char temp=c[i];
        c[i]=c[j];
        c[j]=temp;
        return new String(c);
    }
}
