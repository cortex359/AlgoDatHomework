import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NoSuchElementException;

/**
 * Ein binaerer Suchbaum mit ganzen Zahlen als Datensatz:
 * Vorlage fuer die A1 von algo-pr05 und fuer die A1 von algo-h06.
 * Als Operationen stehen `contains' und `insert' zur Verfuegung
 */
public class BinarySearchTree {

    /**
     * Die Knotenklasse als statische innere Klasse.
     */
    public static class TreeNode {
        private int value;
        private TreeNode left ;
        private TreeNode right;

        //region Hausaufgabe 11
        /************************************************** BEGIN ***************************************************/
        public int sumOfSubNodes = 0; // including this one
        public int numberOfSubNodes = 1; // including this one

        public double getAverageOfSubtree() {
            return (this.sumOfSubNodes * 1.0 / this.numberOfSubNodes);
        }

        public TreeNode(int value) {
            this.value = value;
            this.sumOfSubNodes = value;
        }
        /*************************************************** END ****************************************************/
        //endregion

        //region Unmodifiziert
        public void setLeft(TreeNode node) {
            this.left = node;
        }

        public void setRight(TreeNode node) {
            this.right = node;
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
            if (this.value != 0) {
                this.sumOfSubNodes -= this.value;
            }
            this.value = value;
            this.sumOfSubNodes += value;
        }
        //endregion
    }

    //region Hausaufgabe 11
    /************************************************** BEGIN ***************************************************/
    /**
     * addToStats arbeitet eine Queue mit TreeNodes ab, welche durch das Einfügen einer Node geupdated werden müssen.
     * @param path Deque mit betroffenen TreeNodes.
     * @param data Neuer Wert, der den Summen hinzugefügt werden muss.
     */
    protected void addToStats(Deque<TreeNode> path, int data) {
        while (!path.isEmpty()) {
            TreeNode t = path.pop();
            t.sumOfSubNodes += data;
            t.numberOfSubNodes += 1;
        }
    }

    /**
     * removeFromStats arbeitet eine Queue mit TreeNodes ab, welche durch das Löschen einer Node geupdated werden müssen.
     * @param path Deque mit betroffenen TreeNodes.
     * @param data Wert, der von den Summen abgezogen werden muss.
     */
    protected void removeFromStats(Deque<TreeNode> path, int data) {
        while (!path.isEmpty()) {
            TreeNode t = path.pop();
            t.sumOfSubNodes -= data;
            t.numberOfSubNodes -= 1;
        }
    }

    /**
     * pathToNode erzeugt den Pfad zu einer Node mit dem übergebenen Wert.
     * @param data Schlüssel
     * @return Pfad als Deque von TreeNodes.
     */
    protected Deque<TreeNode> pathToNode(int data) {
        if (!this.contains(data))
            throw new NoSuchElementException();

        // Speichere Pfad der nach dem Einfügen zu updatenden Daten
        Deque<TreeNode> path = new ArrayDeque<>();
        TreeNode temp = root;

        while (temp != null) {
            path.add(temp);
            if (temp.getValue() == data) {
                return path;
            }
            if (temp.getValue() > data) {
                temp = temp.getLeft();
            } else {
                temp = temp.getRight();
            }
        }
        return path;
    }

    /**
     * Einen neuen Datensatz in den binaeren Suchbaum einfuegen.
     *
     * @param data einzufuegender Datensatz
     * @return true: Datensatz wurde eingefuegt; false: Datensatz war schon vorhanden.
     */
    public boolean insert(int data) {
        if (root == null) {
            root = new TreeNode(data);
            return true;
        }

        // Überprüfung ob Einfügen möglich ist.
        if (this.contains(data))
            return false;

        // Speichere Pfad der nach dem Einfügen zu updatenden Daten
        Deque<TreeNode> path = new ArrayDeque<>();

        TreeNode temp = root;
        while (temp.getValue() != data) {
            path.add(temp); // speichere Pfad beim Einfügen

            if (temp.getValue() > data) {
                if (temp.getLeft() == null) {
                    temp.setLeft(new TreeNode(data));

                    // ändere alle sumOfSubNodes/numberOfSubNodes-Attribute der durchlaufenen TreeNodes
                    addToStats(path, data);
                    return true;
                }
                temp = temp.getLeft();
            } else {
                if (temp.getRight() == null) {
                    temp.setRight(new TreeNode(data));

                    // // ändere alle sumOfSubNodes/numberOfSubNodes-Attribute der durchlaufenen TreeNodes
                    addToStats(path, data);
                    return true;
                }
                temp = temp.getRight();
            }
        }
        return false;
    }
    //endregion

    //region Unmodifiziert
    /**
     * Baumwurzel
     */
    protected TreeNode root;

    /**
     * Herausfinden, ob ein gewisser Datensatz schon im binaeren Suchbaum enthalten ist.
     *
     * @param data zu suchender Datensatz
     * @return true: Datensatz ist vorhanden; false: Datensatz ist nicht vorhanden.
     */
    public boolean contains(int data) {
        TreeNode temp = root;
        while (temp != null) {
            if (temp.getValue() == data) {
                return true;
            }
            if (temp.getValue() > data) {
                temp = temp.getLeft();
            } else {
                temp = temp.getRight();
            }
        }
        return false;
    }

    //endregion
}

