package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

public class BKanaAndDragonQuestGame {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int x=in.nextInt();
        int n=in.nextInt();
        int m = in.nextInt();

        while(x>0) {
            if (x>=21){
                if (n>0) {
                    x = (x>>1) + 10;
                    n--;
                } else if (m>0) {
                    x -= 10;
                    m--;
                } else {
                    break;
                }
            } else if (m>0){
                x -= 10;
                m--;
            } else {
                break;
            }
        }

//        while(x > 0) {
//            if (x >= 39) {
//                if (n > 0) {
//                    x = (x>>1) + 10;
//                    n--;
//                } else if (m > 0) {
//                    x -= 10;
//                    m--;
//                } else {
//                    break;
//                }
//            } else {
//                if (m > 0) {
//                    x-=10;
//                    m--;
//                } else if (n > 0 && x >= 21) {
//                    x = (x>>1) + 10;
//                    n--;
//                } else {
//                    break;
//                }
//            }
//        }
        if (x <= 0) {
            out.println("YES");
        } else {
            out.println("NO");
        }
    }
}
