import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Nondeterministic finite automaton
 * ==========================================
 * Hausaufgabe 13: NFA
 * Algorithmen und Datenstrukturen, SoSe 2023
 * Aufgaben vom 12.06.2023
 * Abgabe der Loesungen am 18.06.2023
 *
 * @author Samuel Thesing, samuel.thesing@rwth-aachen.de
 * @author Christian Rene Thelen, christian.thelen@rwth-aachen.de
 * @author Michael Conrads, michael.conrads@rwth-aachen.de
 */
public class NFA {

    private record Transition(int targetNode, char transitionChar) {}

    private List<List<Transition>> adjacencyList;

    private int endNode;

    public NFA(String nodelist) {
        parseNodeList(nodelist);
    }

    /**
     * Parses a string to an integer. Removes leading and trailing spaces.
     * @param s string containing an integer
     * @return parsed integer
     * @throws NumberFormatException if the string does not contain a parsable integer
     */
    private int parseInteger(String s) {
        return Integer.parseInt(s.trim());
    }

    /**
     * Constructs an adjacencylist from an edgelist. The second element of the edgelist is the endnote.
     * After an edge follows a character
     * @param edgelist the edgelist
     */
    private void parseNodeList(String edgelist) {
        String[] entries = edgelist.split(",");
        if (entries.length < 2) {
            throw new IllegalArgumentException("Expected nodelist to have at least a node count and a end node");
        }

        // parse nodecount and endnode
        int nodeCount = parseInteger(entries[0]);
        endNode = parseInteger(entries[1]) - 1;
        if (endNode > nodeCount) {
            throw new IllegalArgumentException("Endnode does not exist");
        }

        // create empty adjacencylist
        adjacencyList = new ArrayList<>();
        for (int i = 0; i < nodeCount; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        // parse edges
        for (int i = 2; i < entries.length; i += 3) {
            int startNode = parseInteger(entries[i]) - 1;
            int targetNode = parseInteger(entries[i+1]) - 1;

            if (entries[i+2].trim().length() != 1) {
                throw new IllegalArgumentException("Unexpected Transition-Character");
            }

            char transitionChar = entries[i+2].charAt(0);

            adjacencyList.get(startNode).add(new Transition(targetNode, transitionChar));
        }
    }

    /**
     * Checks if the given String matches the regex described by the NFA
     * @param s string to check
     * @return true if the string matches the NFA
     */
    public boolean testString(String s) {
        Set<Integer> curNodes = new HashSet<>();
        curNodes.add(0);
        for (int i = 0; i < s.length(); i++) {
            final char c = s.charAt(i);
            Set<Integer> nextNodes = new HashSet<>();

            // gets all Nodes that are occupied in the next iteration
            for (Integer node : curNodes) {
                nextNodes.addAll(
                    adjacencyList.get(node)
                        .stream()
                        .filter(t -> t.transitionChar == c)
                        .map(Transition::targetNode)
                        .collect(Collectors.toSet()));
            }

            // not necessary since it is only a advice
            //System.out.println(Arrays.toString(nextNodes.stream().map(n -> n + 1).toArray()));
            
            if (nextNodes.isEmpty()) return false;

            curNodes = nextNodes;
        }

        return curNodes.contains(endNode);
    }

}
