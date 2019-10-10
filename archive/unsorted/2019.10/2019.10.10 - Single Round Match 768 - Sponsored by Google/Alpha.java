package code;

public class Alpha {
    private static final String str = "abcdefghijklmnopqrstuvwxyz";

    public int maxPref(String s) {
        int i = 0;
        int len = s.length();
        while (i < 26 && i < len) {
            if (str.charAt(i) == s.charAt(i)) {
                i++;
            } else {
                break;
            }
        }
        return i;
    }
}
