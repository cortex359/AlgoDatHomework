package endlicher_automat.pattern;

/**
 * Pattern, das ein einzelnes Zeichen enthaelt
 */
public class SimplePatternNode extends PatternNode{

    private final char specificChar;

    public SimplePatternNode(char c) {
        specificChar = c;
    }

    /**
     * Prueft, ob das zu pruefende Zeichen das erwartete ist
     * @param c zu pruefendes Zeichen
     * @return true, falls das Zeichen das zu Erwartende ist
     */
    @Override
    public boolean match(char c) {
        return specificChar == c;
    }
}
