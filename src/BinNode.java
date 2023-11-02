/**
 * @author Xavier Akers
 * 
 * @version Last Updated 2023-09-22
 * 
 * @since 2023-09-22
 * 
 *        Link-able Nodes for a Generic Binary Search Tree
 *        Implementing KV Pairs Data Storage
 * 
 * @param <K>
 *            Primitive Type for the Key
 * @param <V>
 *            Primitive Type for the Value
 */
public class BinNode<K extends Comparable<K>, V> {
    private KVPair<K, V> value;
    private BinNode<K, V> left;
    private BinNode<K, V> right;

    /**
     * Default Constructor
     */
    public BinNode() {
        this.value = null;
        this.left = null;
        this.right = null;
    }


    /**
     * Custom Constructor
     * Takes in KVPair
     * 
     * @param value
     *            Data to store
     */
    public BinNode(KVPair<K, V> value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }


    /**
     * Custom Constructor
     * Creates the KVPair
     * 
     * @param key
     *            The Key
     * @param value
     *            The Value
     */
    public BinNode(K key, V value) {
        this.value = new KVPair<K, V>(key, value);
        this.left = null;
        this.right = null;
    }


    /**
     * @return the value
     */
    public KVPair<K, V> getValue() {
        return value;
    }


    /**
     * @param value
     *            the value to set
     */
    public void setValue(KVPair<K, V> value) {
        this.value = value;
    }


    /**
     * @return the left
     */
    public BinNode<K, V> getLeft() {
        return left;
    }


    /**
     * @param left
     *            the left to set
     */
    public void setLeft(BinNode<K, V> left) {
        this.left = left;
    }


    /**
     * @return the right
     */
    public BinNode<K, V> getRight() {
        return right;
    }


    /**
     * @param right
     *            the right to set
     */
    public void setRight(BinNode<K, V> right) {
        this.right = right;
    }

}
