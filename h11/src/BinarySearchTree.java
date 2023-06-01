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
        private TreeNode left;
        private TreeNode right;

        //region Hausaufgabe 11
        /************************************** BEGIN *************************** */
        private int sumOfSubNodes = 0;
        private int numberOfSubNodes = 0;

        public double getAverageOfSubtree() {
            return this.sumOfSubNodes / this.numberOfSubNodes;
        }

        private void removeSubtreeStats(TreeNode node) {
            this.sumOfSubNodes -= node.sumOfSubNodes;
            this.numberOfSubNodes -= node.numberOfSubNodes + 1;
        }

        private void addSubtreeStats(TreeNode node) {
            this.sumOfSubNodes += node.sumOfSubNodes;
            this.numberOfSubNodes += node.numberOfSubNodes + 1;
        }

        public void setLeft(TreeNode node) {
            if (this.left != null)
                removeSubtreeStats(this.left);

            this.left = node;
            addSubtreeStats(node);
        }

        public void setRight(TreeNode node) {
            if (this.right != null)
                removeSubtreeStats(this.right);

            this.right = node;
            addSubtreeStats(node);
        }

        /************************************** END *************************** */
        //endregion

        //region Unmodifiziert
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
        //endregion
    }

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

    //endregion
}

