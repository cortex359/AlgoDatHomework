import java.util.Queue;
import java.util.LinkedList;

/**
 * Ein binaerer Suchbaum mit ganzen Zahlen als Datensatz:
 * Grundlage fuer die Musterloesung zu algo-h06 und Vorlage fuer algo-h11.
 * Als Operationen stehen zusaetzlich zu denen der Basisklasse
 * `clear', `show', `getNode', `remove' und `isLeaf' zur Verfuegung.
 *
 * @author
 * Autor: Juergen Dietel, ITC/CSE/MATSE, RWTH Aachen<br>
 * Datum: FR 4. 6. 2021 - MI 26. 4. 2023
 */
public class BinarySearchTree2 extends BinarySearchTree {

    /** den Baum leeren */
    public void clear() {
        this.root = null;
    }

    /** den Baum levelorder ausgeben */
    public void show() {
        System.out.print("Baum levelorder = [ ");
        if (this.root != null) {
            Queue<TreeNode> schlange = new LinkedList<>();
            schlange.add(this.root);
            while (! schlange.isEmpty()) {
                TreeNode k = schlange.remove();
                System.out.print(k + " ");
                if (k.getLeft() != null)
                    schlange.add(k.getLeft());
                if (k.getRight() != null)
                    schlange.add(k.getRight());
            }
        }
        System.out.println("]");
    }

    /**
     * Sucht nach dem Wert x im Baum (iterativ).
     *
     * @param   x  zu suchender Wert
     * @return     Knoten, der den Wert x enthaelt, oder null
     */
    public TreeNode getNode(int x) {
        TreeNode node = this.root;
        while (node != null && node.getValue()!= x) {
            if (x < node.getValue())
                node = node.getLeft();
            else
                node = node.getRight();
        }
        return node;
    }

    /**
     * Loescht den Knoten mit dem Wert n iterativ, falls vorhanden.
     *
     * @param   n                    Wert, dessen Knoten geloescht werden soll
     * @throws  ArithmeticException  wenn der Wert nicht im Baum enthalten ist
     */
    public void remove(int n) {

        TreeNode parent = null;       // Zeigerpaar aus Knoten und seinem
        TreeNode node   = this.root;  // Elter, beginnend bei der Wurzel

        while (node != null) {
            if (node.getValue() == n) { // Knoten mit n drin gefunden?
                remove(node, parent);     // diesen Knoten aus dem Baum entfernen
                return;                   // Knoten entfernt => Methode beenden
            }
            parent = node;              // erstmal neuen Elter setzen
            node   = nextNode(node, n); // im richtigen Teilbaum weitersuchen
        }
        // regulaeres Schleifenende => Knoten nicht gefunden, Ausnahme:
        throw new ArithmeticException("Knoten " + n + " gibt es nicht!");
    }

    // Hilfsmethoden fuer `remove(int n')

    /**
     * Hilfsmethode fuer `remove(int n)':
     * den Knoten `node' mit dem Elternknoten `parent' aus dem Baum entfernen.
     *
     * @param  node    aus dem Baum zu entfernender Knoten
     * @param  parent  Elter des zu entfernenden Knotens
     */
    protected void remove(TreeNode node, TreeNode parent) {

        if (isLeaf(node))                   // 1. Fall: Blatt
            removeLeaf(node, parent);
        else if (isInnerNode(node))         // 2. Fall: zwei Kinder
            removeTwoChildren(node);
        else                                // 3. Fall: ein Kind
            removeOneChild(node);
    }

    /**
     * Herausfinden, ob der gegebene Knoten ein Blatt ist.
     *
     * @param   baum  zu pruefender Baumknoten
     * @return        Ist der Knoten ein Blatt?
     */
    public static boolean isLeaf(TreeNode baum) {
        return baum.getLeft() == null && baum.getRight() == null;
    }

    /**
     * Melden, ob der uebergebene Knoten 2 Kinder hat.
     *
     * @param   baum  zu pruefender Baumknoten
     * @return        Hat der uebergebene Knoten 2 Kinder?
     */
    public static boolean isInnerNode(TreeNode baum) {
        return baum.getLeft() != null && baum.getRight() != null;
    }

    /**
     * Hilfsmethode fuer `remove(int n)':
     * weiter zum Teilbaum, wo Knoten n liegen muss
     *
     * @param   node  aktueller Knoten
     * @param   n     gesuchter Knoteninhalt
     * @return        Teilbaum, in dem der gesuchte Knoten liegen muss
     */
    protected TreeNode nextNode(TreeNode node, int n) {
        return (n < node.getValue())? node.getLeft() : node.getRight();
    }

    /**
     * Den Blattknoten `node' mit `parent' als Elter aus dem Baum entfernen,
     * falls nur ein Blatt entfernt werden muss.
     *
     * @param  node    aus dem Baum zu entfernender Knoten
     * @param  parent  Elter des zu entfernenden Knotens
     */
    private void removeLeaf(TreeNode node, TreeNode parent) {
        if (parent == null)                 // kein Elter vorhanden?
            clear();                          // => Baum nun leer
        else if (parent.getLeft() == node)  // Knoten ist linkes Kind?
            parent.setLeft(null);             // => nun kein linkes Kind mehr da
        else                                // Knoten ist rechtes Kind?
            parent.setRight(null);            // => nun kein rechtes Kind mehr da
    }

    /**
     * Den Knoten `node' aus dem Baum entfernen, falls der Knoten nur ein Kind hat.
     *
     * @param  node    aus dem Baum zu entfernender Knoten
     */
    private void removeOneChild(TreeNode node) {
        TreeNode kind  = (node.getLeft() != null) ? node.getLeft() : node.getRight();
        node.setValue(kind.getValue());  // Inhalt des Kindes in den zu loeschenden
        node.setLeft(kind.getLeft());    // Knoten kopieren, der damit faktisch
        node.setRight(kind.getRight());  // verschwunden ist
    }

    /**
     * den Knoten `node' aus dem Baum entfernen, falls der Knoten ein
     * normaler innerer Knoten mit 2 Kindern ist.
     *
     * @param  node    aus dem Baum zu entfernender Knoten
     */
    protected void removeTwoChildren(TreeNode node) {

        // den Ersatzknoten fuer den zu entfernenden Knoten suchen, also
        // den naechstgroesseren im rechten Teilbaum, gleichzeitig auch
        // noch den Elternknoten des Ersatzknotens:
        TreeNode elter  = node;
        TreeNode ersatz = elter.getRight();     // vom rechten Kind des zu
        for ( ;                                 // loeschenden aus so weit
              ersatz.getLeft() != null;          // weit nach links gehen wie
              elter = ersatz,                    // moeglich, dabei einen
                      ersatz = elter.getLeft())          // Elternknoten mitfuehren
            ;

        // Der Ersatzknoten tritt nun an die Stelle des zu loeschenden Knotens:
        node.setValue(ersatz.getValue());       // Daten aus Ersatzknoten uebernehmen

        // Der Ersatzknoten kann nun ausgehaengt werden:
        if (elter == node)                      // Ist er Kind des zu entfernenden Knotens?
            elter.setRight(ersatz.getRight());    // => neues rechtes Kind im Elternknoten
        else                                    // Schleife mindestens einmal durchlaufen?
            elter.setLeft(ersatz.getRight());     // => neues linkes Kind im Elternknoten
    }
}

