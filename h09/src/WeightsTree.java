public class WeightsTree {
    /**
     * Innere Klasse Node des WeightsTree
     */
    private static class Node{
        private int value;
        private final Node[] nextNodes = new Node[3];
        private Node parent;
        
        public Node(int value){
            setValue(value);
        }
    
        public int getValue() {
            return value;
        }
        
        public Node[] getNextNodes(){
            return nextNodes;
        }
        
        public Node getParent(){
            return parent;
        }
        
        public void setValue(int value){
            this.value = value;
        }
        public void setNextNodes(int value){
            nextNodes[0] = new Node(getValue() + value);
            nextNodes[1] = new Node(getValue());
            nextNodes[2] = new Node(getValue() - value);
        }
        
        public void setParent(Node node){
            this.parent = node;
        }
    }
    
    private Node root;
    
    /**
     * Konstruktor von WeightsTree, baut direkt den Baum mit allen möglichen Gewichtsverteilungen
     */
    public WeightsTree(int[] weights){
        constructTree(weights);
    }
    
    private void constructTree(int[] weights){
        this.root = new Node(0);
        constructTree(root, 0, weights);
    }
    
    private void constructTree(Node node, int i, int[] weights){
        if(i > 3)
            return;
        node.setNextNodes(weights[i]);
        i++;
        constructTree(node.nextNodes[0], i, weights);
        constructTree(node.nextNodes[1], i, weights);
        constructTree(node.nextNodes[2], i, weights);
    }
    
    public Node getRoot(){
        return root;
    }
}
