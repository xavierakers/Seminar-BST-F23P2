import student.TestCase;

/**
 * @author Xavier Akers
 * 
 * @version Last Updated 2023-10-10
 * 
 * @since 2023-09-23
 * 
 *        Binary Search Tree Class Tests
 * 
 */
public class BSTTest extends TestCase {

    /**
     * Inserts One, Searches One
     * Searches for non existent
     */
    public void testInsertAndSearch() {
        BST<Integer, String> bst = new BST<Integer, String>();
        KVPair<Integer, String> expected = new KVPair<>(1, "node1");
        bst.insert(1, "node1");
        assertNotNull(bst.search(1));
        assertEquals(bst.search(1).getValue(), expected.getValue());
        assertNull(bst.search(2));
        assertEquals(bst.getNodeCount(), 1);
    }


    /**
     * Inserting 3 nodes
     * Root has both right and left child
     */
    public void testInsertAndSearchSimple() {
        BST<Integer, String> bst = new BST<Integer, String>();
        KVPair<Integer, String> expected2 = new KVPair<>(2, "node2");
        KVPair<Integer, String> expected1 = new KVPair<>(1, "node1");
        KVPair<Integer, String> expected3 = new KVPair<>(3, "node3");

        bst.insert(2, "node2");
        assertEquals(bst.getNodeCount(), 1);
        bst.insert(1, "node1");
        assertEquals(bst.getNodeCount(), 2);
        bst.insert(3, "node3");
        assertEquals(bst.getNodeCount(), 3);

        assertEquals(bst.search(1).getValue(), expected1.getValue());
        assertEquals(bst.search(2).getValue(), expected2.getValue());
        assertEquals(bst.search(3).getValue(), expected3.getValue());

    }

}
