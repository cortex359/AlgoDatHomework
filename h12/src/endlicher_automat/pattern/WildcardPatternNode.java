package endlicher_automat.pattern;

/**
 * Pattern, das jedes Zeichen erlaubt
 */
public class WildcardPatternNode extends PatternNode{

    /**
     * Erlaubt jedes Zeichen
     * @param c zu pruefendes Zeichen
     * @return true
     */
    @Override
    public boolean match(char c) {
        return true;
    }
}
