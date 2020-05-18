package datastructure;

public class Prefix {
    private int level;

    public Prefix(int level) {
        this.level = level;
    }

    @Override
    public String toString() {
//        return "|  ".repeat(level);
        String str = "|  ";
        int n = str.length();
        StringBuilder builder = new StringBuilder(n * level);
        for (int i = 0; i < level; i++) {
            builder.append(str);
        }
        return builder.toString();
    }

    public Prefix next() {
        return new Prefix(level + 1);
    }

    public Prefix previous() {
        return new Prefix(level - 1);
    }
}
