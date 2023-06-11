package endlicher_automat;

import endlicher_automat.pattern.PatternNode;

import java.util.ArrayList;
import java.util.List;

public class TextSearch {

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
