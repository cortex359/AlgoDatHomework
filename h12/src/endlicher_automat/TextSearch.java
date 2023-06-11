package endlicher_automat;

import endlicher_automat.pattern.PatternNode;

import java.util.ArrayList;
import java.util.List;

public class TextSearch {

    /**
     * Sucht alle Indexe bei denen das Pattern angewendet werden kann
     * @param text uebergebener Text
     * @param pattern gesuchtes Pattern
     * @return ArrayList mit den Indexen der Patternstarts innerhalb des Textes
     */
    public static List<Integer> textSearch(String text, String pattern) {
        PatternConstructor constructor = new PatternConstructor(pattern);
        PatternNode startNode = constructor.getStartNode();

        List<Integer> matches = new ArrayList<>();
        for (int i = 0; i < text.length(); i++) {
            if (matchText(text, i, startNode))
                matches.add(i);
        }
        return matches;
    }

    /**
     * Prueft, ob das Pattern an dn dem gegebenen Index auf den Text passt
     * @param text zu pruefender text
     * @param index startindex
     * @param node start-node des Patterns
     * @return true, falls das Pattern an der Stelle passt
     */
    private static boolean matchText(String text, int index, PatternNode node) {
        while (node != null) {
            if (index >= text.length() || !node.match(text.charAt(index))) {
                return false;
            }
            node = node.getNext();
            index++;
        }
        return true;
    }

}
