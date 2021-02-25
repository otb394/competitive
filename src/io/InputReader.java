package io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Optional;
import java.util.StringTokenizer;

public class InputReader {
    private BufferedReader br;
    private StringTokenizer tokenizer;

    public InputReader(InputStream inputStream) {
        br = new BufferedReader(new InputStreamReader(inputStream));
    }

    public String next() {
        while (tokenizer == null || !tokenizer.hasMoreElements()) {
            try {
                tokenizer = new StringTokenizer(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return tokenizer.nextToken();
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }

    public long nextLong() {
        return Long.parseLong(next());
    }

    public double nextDouble() {
        return Double.parseDouble(next());
    }

    public String readLine() {
        try {
            return Optional.ofNullable(br.readLine()).orElse("");
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
