package endlicher_automat.pattern;

public class SimplePatternNode extends PatternNode{


    private final char specificChar;

    public SimplePatternNode(char c) {
        specificChar = c;
    }

    @Override
    public boolean match(char c) {
        return specificChar == c;
    }
}
