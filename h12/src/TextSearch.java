import java.util.ArrayList;
import java.util.regex.PatternSyntaxException;
public class TextSearch {
    public static ArrayList<Integer> textSearch(String text, String pattern) {
        ArrayList<Integer> indices = new ArrayList<>();

        for (int i = 0; i <= text.length(); i++) {
            if (isMatch(text, i, pattern)) {
                indices.add(i);
            }
        }
        
        return indices;
    }
    
    private static boolean isMatch(String text, int startIndex, String pattern) {

        
        for (int i = 0; i < pattern.length(); i++) {
            if (startIndex + i >= text.length()) {
                return false;  // Pattern exceeds remaining text length
            }
            
            char textChar = text.charAt(startIndex + i);
            char patternChar = pattern.charAt(i);
            
            if (patternChar == '.') {
                continue;  // Match any character
            } else if (patternChar == '[') {
                int closingBracketIndex = pattern.indexOf(']', i);
                if (closingBracketIndex == -1) {
                    throw new PatternSyntaxException("Missing closing bracket in pattern", pattern, i);
                }
                
                String characterClass = pattern.substring(i + 1, closingBracketIndex);
                if (isCharacterClassMatch(characterClass, textChar)) {
                    i = closingBracketIndex;  // Skip to after the closing bracket
                    continue;
                } else {
                    return false;  // Character class mismatch
                }
            } else if (patternChar == '\\' && i < pattern.length() - 1) {
                char escapedChar = pattern.charAt(i + 1);
                if (escapedChar == '.' || escapedChar == '[' || escapedChar == '\\') {
                    patternChar = escapedChar;  // Treat escaped character as normal
                    i++;  // Skip the escaped character
                }
            }
            
            if (textChar != patternChar) {
                return false;  // Character mismatch
            }
        }
        
        return true;
    }
    
    private static boolean isCharacterClassMatch(String characterClass, char c) {
        for (int i = 0; i < characterClass.length(); i++) {
            char charClassChar = characterClass.charAt(i);
            if (charClassChar == '\\') {
                if (i < characterClass.length() - 1) {
                    char escapedChar = characterClass.charAt(i + 1);
                    if (escapedChar == '\\' || escapedChar == ']') {
                        charClassChar = escapedChar;  // Treat escaped character as normal
                        i++;  // Skip the escaped character
                    }
                }
            }
            
            if (charClassChar == c) {
                return true;  // Character matches one in the character class
            }
        }
        
        return false;  // No character in the character class matches
    }
}
