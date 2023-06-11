package endlicher_automat.pattern;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Pattern, das eine Menge von erlaubten Zeichen enthaelt
 */
public class RangePatternNode extends PatternNode{

    private final Set<Character> range;

    /**
     * Erstelle ein Set aus den Zeichen
     * @param range erlaubte Zeichen
     */
    public RangePatternNode(String range) {
        this.range = range.chars().mapToObj(c -> (char)c).collect(Collectors.toSet());
    }

    /**
     * Prueft, ob das zu pruefende Zeichen in der erlaubten Menge enthalten ist
     * @param c zu pruefendes Zeichen
     * @return true, falls das Zeichen enthalten ist
     */
    @Override
    public boolean match(char c) {
        return range.contains(c);
    }

}
