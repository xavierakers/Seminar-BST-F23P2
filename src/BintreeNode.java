/**
 * @author Xavier Akers
 * 
 * @version Last Updated 2023-10-04
 * 
 * @since 2023-10-04
 * 
 *        An Interface for Nodes
 *        for a Composite BinTree
 * 
 */
public interface BintreeNode {
    /**
     * @return true if a leaf node
     */
    public boolean isLeaf();


    /**
     * How to display itself
     */
    public void print();
}
