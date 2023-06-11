package endlicher_automat.pattern;

/**
 * Stellt einen Teil eines Pattern dar
 */
public abstract class PatternNode {

    private PatternNode next;

    /**
     * Prueft, ob das uebergebene Zeichen zu dem Pattern passt
     * @param c zu pruefendes Zeichen
     * @return true, falls das Zeichen passt
     */
    public abstract boolean match(char c);

    /**
     * setzt den naechsten Knoten des endlichen Automaten
     * @param node naechster Knoten
     */
    public void setNext(PatternNode node) {
        next = node;
    }

    /**
     * Gibt den naechsten Knoten des endlichen Automaten zurueck
     * @return naechster Knoten
     */
    public PatternNode getNext() {
        return next;
    }

}
