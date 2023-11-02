/**
 * @author Xavier Akers
 * 
 * @version Last Updated 2023-10-04
 * 
 * @since 2023-10-04
 * 
 *        An Internal Node for a Composite BinTree
 * 
 */
public class InternalNode implements BintreeNode {

    private BintreeNode left;
    private BintreeNode right;

    /**
     * Constructor
     * 
     * @param left
     *            left child
     * @param right
     *            right child
     */
    public InternalNode(BintreeNode left, BintreeNode right) {
        this.left = left;
        this.right = right;
    }


    /**
     * Getter for left child
     * 
     * @return the left
     */
    public BintreeNode getLeft() {
        return left;
    }


    /**
     * Setter for left child
     * 
     * @param left
     *            the left to set
     */
    public void setLeft(BintreeNode left) {
        this.left = left;
    }


    /**
     * Getter for right child
     * 
     * @return the right
     */
    public BintreeNode getRight() {
        return right;
    }


    /**
     * Setter for right child
     * 
     * @param right
     *            the right to set
     */
    public void setRight(BintreeNode right) {
        this.right = right;
    }


    @Override
    public boolean isLeaf() {
        return false;
    }


    @Override
    public void print() {
        System.out.println("I");

    }

}
