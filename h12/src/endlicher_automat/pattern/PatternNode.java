package endlicher_automat.pattern;

public abstract class PatternNode {

    private PatternNode next;

    public abstract boolean match(char c);

    public void setNext(PatternNode node) {
        next = node;
    }

    public PatternNode getNext() {
        return next;
    }

}
