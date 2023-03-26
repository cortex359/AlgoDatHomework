import java.util.ArrayDeque;
import java.util.Set;

/**
 * Brackets stellt die Funktion isValid bereit.
 * ==========================================
 * Hausaufgabe 01: Brackets
 * Algorithmen und Datenstrukturen, SoSe 2023
 * Aufgaben vom 20.03.2023
 * Abgabe der Lösungen am 26.03.2023
 *
 * @author Samuel Thesing, samuel.thesing@rwth-aachen.de
 * @author Christian Rene Thelen, christian.thelen@rwth-aachen.de
 * @author Michael Conrads, michael.conrads@rwth-aachen.de
 */
public class Brackets {

    /**
     * invert liefert für eine übergebene, öffnende Klammer die passende schließende Klammer zurück.
     * @param c Öffnende Klammer: { [ (
     * @return char schließende Klammer oder `-` sollte das übergebene Zeichen keine der bekannten Klammern gewesen sein.
     */
    private static char invert(char c) {
        return switch (c) {
            case '[' -> ']';
            case '(' -> ')';
            case '{' -> '}';
            default -> '-';
        };
    }

    /**
     * isValid überprüft die Klammern eines String auf Gültigkeit. Gültig ist ein String, wenn jede geöffnete Klammer
     * der gleichen Form auch durch ihr Gegenstück in richtiger Reihenfolge geschlossen wird.
     * Zeichen, die keine Klammern sind, werden dabei ignoriert.
     * @param s Zu überprüfender Ausdruck.
     * @return boolean True, wenn valide, ansonsten false.
     */
    public static boolean isValid(String s) {
        Set<Character> open = Set.of('(', '[', '{');
        Set<Character> close = Set.of(')', ']', '}');

        ArrayDeque<Character> stack = new ArrayDeque<>();
        for (var c :  s.toCharArray()) {
            if (open.contains(c)) {
                // Öffnende Klammer auf Stack ablegen.
                stack.push(c);
            } else if (close.contains(c) && (stack.isEmpty() || invert(stack.pop()) != c)) {
                // Schließende Klammer von Stack entfernen. Abbrechen, wenn Stack leer ist oder Klammer nicht passt.
                return false;
            }
        }
        return stack.isEmpty();
    }
}
