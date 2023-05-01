import java.util.ArrayList;

/**
 * BinarySearchTree Datenstrukturen
 * ====================
 * Hausaufgabe 06: BinarySearchTree Datenstrukturen
 * Algorithmen und Datenstrukturen, SoSe 2023
 * Aufgaben vom 25.04.2023
 * Abgabe der Loesungen am 01.05.2023
 *
 * @author Samuel Thesing, samuel.thesing@rwth-aachen.de
 * @author Christian Rene Thelen, christian.thelen@rwth-aachen.de
 * @author Michael Conrads, michael.conrads@rwth-aachen.de
 */

/** Ein binaerer Suchbaum mit ganzen Zahlen als Datensatz
 */
public class BinarySearchTree {
    
    /** Die Knotenklasse als statische innere Klasse. */
    public static class TreeNode {
        private int value;
        private TreeNode left;
        private TreeNode right;
        
        public TreeNode(int value) {
            this.value = value;
        }
        
        public String toString() {
            return this.value + " ";
        }
        
        public int getValue() {
            return this.value;
        }
        
        public TreeNode getLeft() {
            return this.left;
        }
        
        public TreeNode getRight() {
            return this.right;
        }
        
        public void setValue(int value) {
            this.value = value;
        }
        
        public void setLeft(TreeNode node) {
            this.left = node;
        }
        
        public void setRight(TreeNode node) {
            this.right = node;
        }
    }
    
    /** Baumwurzel */
    protected TreeNode root;
    
    /**
     * Konstruktor:<p>
     * Erzeugt einen zufaelligen binaeren Suchbaum mit count Knoten,
     * die alle zwischen den Werten min und max (einschliesslich) liegen.
     * @param count Anzahl Knoten, die der erzeugte Baum hat
     * @param min untere Grenze der Werte
     * @param max obere Grenze der Werte
     */
    
    public BinarySearchTree(int count, int min, int max) {
        for (int i = 0; i < count; i++) {
            int x = (int) (Math.random() * (max - min + 1)) + min;
            if (!this.insert(x)) {
                i--;
            }
        }
    }
    
    /**
     * Herausfinden, ob ein gewisser Datensatz schon im binaeren Suchbaum enthalten ist.
     *
     * @param   data  zu suchender Datensatz
     * @return true: Datensatz ist vorhanden; false: Datensatz ist nicht vorhanden.
     */
    public boolean contains(int data) {
        TreeNode temp = root;
        while (temp != null) {
            if (temp.getValue() == data) {
                return false;
            }
            if (temp.getValue() > data) {
                temp = temp.getLeft();
            } else {
                temp = temp.getRight();
            }
        }
        return false;
    }
    
    /**
     * Einen neuen Datensatz in den binaeren Suchbaum einfuegen.
     *
     * @param   data  einzufuegender Datensatz
     * @return true: Datensatz wurde eingefuegt; false: Datensatz war schon vorhanden.
     */
    public boolean insert(int data) {
        if (root == null) {
            root = new TreeNode(data);
            return true;
        }
        
        TreeNode temp = root;
        while (temp.getValue() != data) {
            if (temp.getValue() > data) {
                if (temp.getLeft() == null) {
                    temp.setLeft(new TreeNode(data));
                    return true;
                }
                temp = temp.getLeft();
            } else {
                if (temp.getRight() == null) {
                    temp.setRight(new TreeNode(data));
                    return true;
                }
                temp = temp.getRight();
            }
        }
        return false;
    }
    
    /**
     * Gibt sowohl die Knoten in Preorder-Reihenfolge als auch die Anzahl der Knoten auf dem Bildschirm aus
     */
    public void printPreOrder() {
        ArrayList<Integer> elems = new ArrayList<>();
        getElementsPreorder(root, elems);
        System.out.println(elems);
        System.out.println(elems.size());
    }
    
    /**
     * private Methode, die alle Elemente des Baumes in Preorder-Reihenfolge in einer mitgegebenen ArrayList speichert.
     * @param node Anfangsknoten, in dem die Methode startet
     * @param elems mitgegebene ArrayList
     */
    private void getElementsPreorder(TreeNode node, ArrayList<Integer> elems) {
        if (node == null) {
            return;
        }
        elems.add(node.value);
        
        getElementsPreorder(node.left, elems);
        getElementsPreorder(node.right, elems);
    }
    
    /**
     * Gibt eine Liste der Werte aller Blattknoten zurueck
     */
    public ArrayList<Integer> getLeaves() {
        ArrayList<Integer> elems = new ArrayList<>();
        getLeaves(root, elems);
        return elems;
    }
    
    /**
     * sammelt alle Blattknoten in einer mitgegebenen ArrayList
     * @param node Anfangsknoten, in dem die Methode startet
     * @param elems mitgegebene ArrayList
     */
    private void getLeaves(TreeNode node, ArrayList<Integer> elems) {
        
        if (node == null) {
            return;
        }
        if (node.left == null && node.right == null) {
            elems.add(node.value);
        }
        getLeaves(node.left, elems);
        getLeaves(node.right, elems);
    }
    
    /**
     * Gibt die groesste Summe der Knoten aus, die auf einem Pfad
     * zwischen der Wurzel und einem Blatt erreicht werden kann.
     */
    public int getMaxPathSum() {
        return getMaxPathSum(root);
    }
    
    /**
     * Berechnet die groesste Summe der Knoten, die auf einem Pfad
     * zwischen der Wurzel und einem Blatt erreicht werden kann.
     * @param node Anfangsknoten, in dem die Methode startet
     * @return die groesste Summe
     */
    private int getMaxPathSum(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return node.value + Math.max(getMaxPathSum(node.left), getMaxPathSum(node.right));
    }
    
    /**
     * Gibt eine Liste der Werte aller Knoten zurueck,
     * die im uebergebenen Level liegen. Die Wurzel hat Level 0.
     * @param level entsprechendes Level, aus dem die Werte entnommen werden
     * @return eine Liste mit den Werten aller Knoten des uebergebenen Levels
     */
    
    public ArrayList<Integer> getElementsInLevel(int level) {
        ArrayList<Integer> elems = new ArrayList<>();
        getElementsInLevel(root, level, elems);
        return elems;
    }
    
    /**
     * sammelt dann alle Werte aus den Knoten des uebergebenen Levels in einer uebergebenen Liste.
     * @param node Anfangsknoten, in dem die Methode startet
     * @param remainingDepth uebergebene Tiefe, gleich dem Level, aus dem die Werte genommen werden.
     * @param elems uebergebene ArrayList
     */
    private void getElementsInLevel(TreeNode node, int remainingDepth, ArrayList<Integer> elems) {
        if (node == null)
            return;
        if (remainingDepth == 0) {
            elems.add(node.value);
            return;
        }
        getElementsInLevel(node.left, remainingDepth - 1, elems);
        getElementsInLevel(node.right, remainingDepth - 1, elems);
    }
    
    /**
     * Ueberprueft, ob der Baum vollstaendig ist oder nicht
     * @return true - wenn Baum vollstaendig
     * <p> false - wenn Baum nicht vollstaendig.
     */
    public boolean isComplete() {
        return isComplete(root) == 0;
    }
    
    /**
     * Ueberprueft, ob der Baum vollstaendig ist oder nicht
     * @param node Anfangsknoten, in dem die Methode startet
     * @return 0 - wenn Baum vollstaendig
     * <p> -1 - wenn Baum nicht vollstaendig.
     */
    private int isComplete(TreeNode node){
        if(node == null)
            return 0;
        int left = isComplete(node.left);
        int right = isComplete(node.right);
        if (right == -1 || left != right)
            return -1;
        return left + 1;
    }
    
    /**
     * Gibt zurueck, ob der Baum die AVL-Bedingung erfuellt
     * @return true - wenn Baum AVL konform
     * <p> false - wenn Baum nicht AVL konform
     */
    public boolean isAVL() {
        return isAVL(root) != -1;
    }
    
    /**
     * prueft, ob zwischen dem linken und dem rechten Teilbaum der Betrag der Tiefendifferenz groesser 1 ist.
     * @param node Anfangsknoten, in dem die Methode startet
     * @return -1, wenn die Tiefendifferenz zweier benachbarter Teilbaeume groesser 1 ist
     * </p> 0, wenn alles AVL-konform ist
     */
    private int isAVL(TreeNode node) {
        if (node == null)
            return 0;
        int left = isAVL(node.left);
        int right = isAVL(node.right);
        if ( left == -1 || right == -1 || Math.abs(left - right) > 1)
            return -1;
        return Math.max(left, right) + 1;
    }
}

