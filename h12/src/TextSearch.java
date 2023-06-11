import java.util.ArrayList;
import java.util.regex.PatternSyntaxException;

/**
 * Textsuche
 * ==========================================
 * Hausaufgabe 12: Textsuche
 * Algorithmen und Datenstrukturen, SoSe 2023
 * Aufgaben vom 05.06.2023
 * Abgabe der Loesungen am 11.06.2023
 *
 * @author Samuel Thesing, samuel.thesing@rwth-aachen.de
 * @author Christian Rene Thelen, christian.thelen@rwth-aachen.de
 * @author Michael Conrads, michael.conrads@rwth-aachen.de
 */

public class TextSearch {
    /**
     * Ueberprueft fuer jeden Charakter des uebergebenen Textes, ob er der Start des Patterns ist und fuegt dann den Index
     * des Charakters in eine ArrayList ein
     * @param text uebergebener Text
     * @param pattern gesuchtes Pattern
     * @return ArrayList mit den Indexen der Patternstarts innerhalb des Textes
     */
    public static ArrayList<Integer> textSearch(String text, String pattern) {
        ArrayList<Integer> indices = new ArrayList<>();

        for (int i = 0; i <= text.length(); i++) {
            if (isMatch(text, i, pattern)) {
                indices.add(i);
            }
        }
        
        return indices;
    }
    
    /**
     * Ueberprueft fuer den uebergebenen Index, ob dieser ein Anfangsindex des Patterns ist.
     * @param text zu ueberpruefender Text
     * @param startIndex moeglicher Anfang des Patterns
     * @param pattern gesuchtes Pattern
     * @return true, wenn der startIndex Anfang des Patterns ist, false, wenn nicht
     */
    private static boolean isMatch (String text, int startIndex, String pattern) throws PatternSyntaxException{

        int textIndex = startIndex;
        for (int i = 0; i < pattern.length(); i++) {
            if (textIndex >= text.length()) {
                return false;  // Pattern exceeds remaining text length
            }
            
            char textChar = text.charAt(textIndex);
            char patternChar = pattern.charAt(i);
            
            if (patternChar == '.') {
                textIndex++;
                continue;  // Jeder Charakter wird gematched
            } else if (patternChar == '[') {
                int closingBracketIndex = pattern.indexOf(']', i);
                if (closingBracketIndex == -1) {
                    throw new PatternSyntaxException("Fehlende schließende Klammer zu oeffnender Klammer", pattern, i);
                }
                
                String characterClass = pattern.substring(i + 1, closingBracketIndex);
                if (isCharacterClassMatch(characterClass, textChar)) {
                    i = closingBracketIndex;  // Springe bis hinter die geschlossene Klammer
                    textIndex++;
                    continue;
                } else {
                    return false;  // Charakterklasse matched nicht
                }
            } else if (patternChar == '\\' && i < pattern.length() - 1) {
                char escapedChar = pattern.charAt(i + 1);
                if (escapedChar == '.' || escapedChar == '[' || escapedChar == '\\') {
                    patternChar = escapedChar;  // 'Escaped'-Charakter wird als normaler behandelt
                    i++;  // 'Escaped'-Charakter wird uebersprungen
                }
            }
            
            if (textChar != patternChar) {
                return false;  // Charakter matched nicht
            }
            textIndex++;
        }
        
        return true;
    }
    
    /**
     * Ueberprueft, ob der Charakter in der Charakterklasse enthalten ist
     * @param characterClass Gegebene Charakterklasse
     * @param c zu ueberpruefender Charakter
     * @return true, wenn der Charakter in der Klasse enthalten ist, false, wenn nicht
     */
    private static boolean isCharacterClassMatch(String characterClass, char c) {
        for (int i = 0; i < characterClass.length(); i++) {
            char charClassChar = characterClass.charAt(i);
            if (charClassChar == '\\') {
                if (i < characterClass.length() - 1) {
                    char escapedChar = characterClass.charAt(i + 1);
                    if (escapedChar == '\\' || escapedChar == ']') {
                        charClassChar = escapedChar;  // 'Escaped'-Charakter wird als normaler behandelt
                        i++;  // 'Escaped'-Charakter wird uebersprungen
                    }
                }
            }
            
            if (charClassChar == c) {
                return true;  // Charakter matched einem in der Charakterklasse
            }
        }
        
        return false;  // Kein Charakter matched
    }
}
