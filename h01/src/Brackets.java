import java.util.ArrayDeque;
import java.util.Set;

/**
 * Brackets stellt die Funktion isValid bereit.
 * ==========================================
 * Hausaufgabe 01: Brackets
 * Algorithmen und Datenstrukturen, SoSe 2023
 * Aufgaben vom 20.03.2023
 * Abgabe der Loesungen am 26.03.2023
 *
 * @author Samuel Thesing, samuel.thesing@rwth-aachen.de
 * @author Christian Rene Thelen, christian.thelen@rwth-aachen.de
 * @author Michael Conrads, michael.conrads@rwth-aachen.de
 */
public class Brackets {

    /**
     * invert liefert fuer eine uebergebene, oeffnende Klammer die passende schliessende Klammer zurueck.
     * @param c Oeffnende Klammer: { [ (
     * @return char schliessende Klammer oder - sollte das uebergebene Zeichen keine der bekannten Klammern gewesen sein.
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
     * isValid ueberprueft die Klammern eines String auf Gueltigkeit. Gueltig ist ein String, wenn jede geoeffnete Klammer
     * der gleichen Form auch durch ihr Gegenstueck in richtiger Reihenfolge geschlossen wird.
     * Zeichen, die keine Klammern sind, werden dabei ignoriert.
     * @param s Zu ueberpruefender Ausdruck.
     * @return boolean True, wenn valide, ansonsten false.
     */
    public static boolean isValid(String s) {
        Set<Character> open = Set.of('(', '[', '{');
        Set<Character> close = Set.of(')', ']', '}');

        ArrayDeque<Character> stack = new ArrayDeque<>();
        for (var c :  s.toCharArray()) {
            if (open.contains(c)) {
                // Oeffnende Klammer auf Stack ablegen.
                stack.push(c);
            } else if (close.contains(c) && (stack.isEmpty() || invert(stack.pop()) != c)) {
                // Schliessende Klammer von Stack entfernen. Abbrechen, wenn Stack leer ist oder Klammer nicht passt.
                return false;
            }
        }
        return stack.isEmpty();
    }
}
