/**
 * @author Xavier Akers
 * 
 * @version Last Updated 2023-09-29
 * 
 * @since 2023-09-22
 * 
 *        A Generic Binary Search Tree
 *        Implementing Key Value Pairs
 * 
 * @param <K>
 *            Primitive Type of the Key
 * @param <V>
 *            Primitive Type of the Value
 */
public class BST<K extends Comparable<K>, V> {
    private BinNode<K, V> root;
    private int nodeCount;

    /**
     * Default Constructor
     */
    public BST() {
        this.root = null;
        this.nodeCount = 0;
    }


    /**
     * Clears the BST
     */
    public void clear() {
        this.root = null;
        nodeCount = 0;
    }


    /**
     * Public Insert Method
     * 
     * @param id
     *            The Key Value
     * @param value
     *            The Data Value to be inserted
     */
    public void insert(K id, V value) {
        // Creating node to insert
        BinNode<K, V> node = new BinNode<K, V>(id, value);
        this.root = insertHelp(this.root, node);
        this.nodeCount++;
    }


    /**
     * Public Search Method
     * 
     * @param key
     *            Data to look for
     * @return The KVPair containing the data
     */
    public KVPair<K, V> search(K key) {
        // Reference to node to find
        BinNode<K, V> result = searchHelp(root, key);
        if (result != null) {
            return result.getValue();
        }
        return null;
    }


    /**
     * Recursive search to traverse the tree
     * 
     * @param rt
     *            BST node to check
     * @param key
     *            ID to search for
     * @return The Node
     */
    private BinNode<K, V> searchHelp(BinNode<K, V> rt, K key) {
        if (rt == null) {
            return null;
        }
        // checking to move to right or left child
        if (rt.getValue().compareTo(key) > 0) {
            return searchHelp(rt.getLeft(), key);
        }
        else if (rt.getValue().compareTo(key) == 0) {
            return rt;
        }
        else {
            return searchHelp(rt.getRight(), key);
        }
    }


    /**
     * Public Range Search Method
     * 
     * @param key1
     *            Lower Bound
     * @param key2
     *            Higher Bound
     * @param count
     *            Pass-by-reference to count number of traversals
     * @return The KVPair containing the data
     */
    public Object[] search(K key1, K key2, int[] count) {
        Object[] results = new Object[0];
        results = rangeSearchHelp(root, key1, key2, results, count);
        return results;
    }


    /**
     * A Search to find all keys within range
     * 
     * @param rt
     *            The node to check
     * @param min
     *            The lower bound
     * @param max
     *            The upper bound
     * @param results
     *            Array of matching KVPairs
     * @param count
     *            Pass-by-reference to count number of traversals
     * @return Array of matching KVPairs
     */
    public Object[] rangeSearchHelp(
        BinNode<K, V> rt,
        K min,
        K max,
        Object[] results,
        int[] count) {
        count[0]++;
        if (rt == null) {
            return results;
        }

        // Traverse the left subtree (in-order)
        if (rt.getValue().getKey().compareTo(min) >= 0) {
            results = rangeSearchHelp(rt.getLeft(), min, max, results, count);
        }

        // Check if the current node's value is within the range
        if (rt.getValue().getKey().compareTo(min) >= 0 && rt.getValue().getKey()
            .compareTo(max) <= 0) {
            Object[] newList = new Object[results.length + 1];
            System.arraycopy(results, 0, newList, 0, results.length);
            newList[results.length] = rt.getValue().getValue();
            results = newList;
        }

        // Traverse the right subtree (in-order)
        if (rt.getValue().getKey().compareTo(max) < 0) {
            results = rangeSearchHelp(rt.getRight(), min, max, results, count);
        }

        return results;

    }


    /**
     * Public method for finding multiple KVPairs
     * 
     * @param key
     *            the Key to match
     * @return Array of matching KVPairs
     */
    public Object[] multiSearch(K key) {
        // Array to hold found seminars
        Object[] results = new Object[0];
        results = multiSearchHelp(root, key, results);
        return results;
    }


    /**
     * A search that returns all matching Keys
     * 
     * @param rt
     *            the node to check
     * @param key
     *            key to match
     * @param results
     *            array of found KVPairs
     * @return Array of found KVPairs
     */
    public Object[] multiSearchHelp(BinNode<K, V> rt, K key, Object[] results) {
        if (rt == null) {
            return results;
        }
        // Traverse the left subtree (in-order)
        results = multiSearchHelp(rt.getLeft(), key, results);

        // Check if the current node's key matches the specified key
        if (rt.getValue().getKey().compareTo(key) == 0) {
            // Add the value to the results
            Object[] newList = new Object[results.length + 1];
            System.arraycopy(results, 0, newList, 0, results.length);
            newList[results.length] = rt.getValue().getValue();
            results = newList;
        }

        // Traverse the right subtree (in-order)
        results = multiSearchHelp(rt.getRight(), key, results);

        return results;
    }


    /**
     * Insert Helper
     * 
     * @param rt
     *            BST Node
     * @param node
     *            Node to be inserted
     * @return BST Root
     */
    private BinNode<K, V> insertHelp(BinNode<K, V> rt, BinNode<K, V> node) {
        // If empty tree
        if (rt == null) {
            rt = node;
            return node;
        }
        // move left if node is less than
        if (rt.getValue().compareTo(node.getValue()) >= 0) {
            rt.setLeft(insertHelp(rt.getLeft(), node));
        }
        // move right
        else {
            rt.setRight(insertHelp(rt.getRight(), node));
        }
        return rt;

    }


    /**
     * Public Removal Method
     * 
     * @param key
     *            ID to be removed
     * 
     * @return
     *         Node that was removed
     */
    public BinNode<K, V> remove(K key) {
        // Checking if node exists
        BinNode<K, V> temp = searchHelp(root, key);
        // Variable to store the node that is being deleted
        BinNode<K, V> foundNode = null;
        // The value of the found node
        V found = null;
        // If node does exist
        if (temp != null) {
            found = temp.getValue().getValue();
            // creating new node to return
            foundNode = new BinNode<>(new KVPair<K, V>(key, found));
            // node removing node from the tree
            root = removeHelp(root, key);
            nodeCount--;
        }

        return foundNode;
    }


    /**
     * Public Removal Method
     * Used to remove multiple references to a seminar
     * 
     * @param key
     *            ID to be removed
     * @param id
     *            Seminar id to check
     * @return
     *         Node that was removed
     */
    public BinNode<K, V> remove(K key, int id) {
        // Making sure node exists
        BinNode<K, V> temp = searchHelp(root, key);

        root = removeHelp(root, key, id);
        nodeCount--;

        return temp;
    }


    /**
     * Removal Helper Method
     * Used to remove from the ID tree
     * No duplicate check
     * 
     * @param rt
     *            BST root
     * @param key
     *            ID to be removed
     * @return removed Node
     */
    private BinNode<K, V> removeHelp(BinNode<K, V> rt, K key) {
        // if node is less than, traverse left
        if (rt.getValue().getKey().compareTo(key) > 0) {
            rt.setLeft(removeHelp(rt.getLeft(), key));
        }
        // node is greater than, traverse right
        else if (rt.getValue().getKey().compareTo(key) < 0) {
            rt.setRight(removeHelp(rt.getRight(), key));
        }
        else {
            // found the node
            if (rt.getLeft() == null) {
                return rt.getRight();
            }
            // both nodes exist
            else {
                // find the largest node in the left subtree
                BinNode<K, V> temp = getMax(rt.getLeft());
                // replace values from the largest node
                rt.setValue(temp.getValue());
                // delete the largest node
                rt.setLeft(deleteMax(rt.getLeft()));
            }
        }
        return rt;
    }


    /**
     * Removal Helper Method
     * Removes multiple all copies to the same seminar
     * 
     * @param rt
     *            BST root
     * @param key
     *            ID to be removed
     * @param sem
     *            Seminar to check
     * @return removed Node
     */
    private BinNode<K, V> removeHelp(BinNode<K, V> rt, K key, int id) {
        // the seminar to compare to
        Seminar sem = (Seminar)rt.getValue().getValue();
        // if node is less than, traverse left
        if (rt.getValue().getKey().compareTo(key) > 0) {
            rt.setLeft(removeHelp(rt.getLeft(), key, id));
        }
        // else traverse right
        else if (rt.getValue().getKey().compareTo(key) < 0) {
            rt.setRight(removeHelp(rt.getRight(), key, id));
        }
        // else we have found the node
        else if (rt.getValue().getKey().compareTo(key) == 0 && id == sem.id()) {
            // if only one child, replace with that child
            if (rt.getLeft() == null) {
                return rt.getRight();
            }
            else if (rt.getRight() == null) {
                return rt.getLeft();
            }
            // has two children, special case
            else {
                // node to store the largest node in the left subtree
                BinNode<K, V> temp = getMax(rt.getLeft());
                // replacing values
                rt.setValue(temp.getValue());
                // deleting largest node in left subtree
                rt.setLeft(deleteMax(rt.getLeft()));
            }

        }
        else {
            // if has the same key, but values do not match, move left as we
            // store duplicate keys to the left
            rt.setLeft(removeHelp(rt.getLeft(), key, id));
        }
        return rt;
    }


    /**
     * Finds the maximum node in the given subtree
     * 
     * @param rt
     *            The root of the subtree
     * @return The maximum node
     */
    private BinNode<K, V> getMax(BinNode<K, V> rt) {
        // continually traverses right
        if (rt.getRight() == null) {
            return rt;
        }
        return getMax(rt.getRight());
    }


    /**
     * Deletes the maximum node in the given subtree
     * 
     * @param rt
     *            The root of the subtree
     * @return The new root
     */
    private BinNode<K, V> deleteMax(BinNode<K, V> rt) {
        // continually traverses right
        if (rt.getRight() == null) {
            return rt.getLeft();
        }
        rt.setRight(deleteMax(rt.getRight()));
        return rt;
    }


    /**
     * @return the nodeCount
     */
    public int getNodeCount() {
        return nodeCount;
    }


    /**
     * @param nodeCount
     *            the nodeCount to set
     */
    public void setNodeCount(int nodeCount) {
        this.nodeCount = nodeCount;
    }


    /**
     * Prints contents of the BST
     */
    public void printBST() {
        if (root == null) {
            System.out.println("This tree is empty");
        }
        else {
            printBSTHelper(root, 0);
            System.out.printf("Number of records: %d%n", nodeCount);
        }

    }


    /**
     * Prints contents of the BST
     * 
     * @param rt
     *            The Node
     * @param depth
     *            The depth of the Node
     */
    public void printBSTHelper(BinNode<K, V> rt, int depth) {
        if (rt == null) {
            // proper formatting
            for (int i = 0; i < depth; i++) {
                System.out.print("  ");
            }
            System.out.println("null");
            return;
        }
        printBSTHelper(rt.getRight(), depth + 1);
        // proper formatting
        for (int i = 0; i < depth; i++) {
            System.out.print("  ");
        }

        System.out.println(rt.getValue().getKey());

        printBSTHelper(rt.getLeft(), depth + 1);
    }

}
