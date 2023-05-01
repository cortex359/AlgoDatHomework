public class TestBinarySearchTree {
    /**
     * Testprogramm
     * @param  args  Parameter des Programmaufrufs
     */
    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree(20, 10, 50);
        System.out.println(tree);
        for (int i = 0; i < 50; i++) {
            System.out.println(i + ": " + tree.contains(i));
        }
        tree.printPreOrder();
        System.out.println(tree.getLeaves());
        System.out.println(tree.getMaxPathSum());
        System.out.println(tree.getElementsInLevel(3));
        System.out.println(tree.isComplete());
        System.out.println(tree.isAVL());
    }
}
