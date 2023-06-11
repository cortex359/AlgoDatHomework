package endlicher_automat.pattern;

public class WildcardPatternNode extends PatternNode{
    @Override
    public boolean match(char c) {
        return true;
    }
}
