/**
 * @author Xavier Akers
 * 
 * @version Last Updated 2023-10-05
 * 
 * @since 2023-10-05
 * 
 *        A FlyWeight for the BinTree
 * 
 */
public class EmptyNode implements BintreeNode {
    private static EmptyNode instance = new EmptyNode();

    private EmptyNode() {

    }


    /**
     * @return the instance of EmptyNode
     */
    public static EmptyNode getInstance() {
        return instance;
    }


    @Override
    public boolean isLeaf() {
        return true;
    }


    @Override
    public void print() {
        System.out.println("E");
        
    }
}
