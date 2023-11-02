/**
 * @author Xavier Akers
 * 
 * @version Last Updated 2023-10-04
 * 
 * @since 2023-10-04
 * 
 *        A Leaf Node for a Composite BinTree
 *        Implements a Linked-List of Nodes
 * 
 */
public class LeafNode implements BintreeNode {
    private SeminarNode node;

    /**
     * Constructor
     * 
     * @param sem
     *            seminar reference
     */
    public LeafNode(Seminar sem) {
        this.node = new SeminarNode(sem);
    }


    /**
     * Constructor
     * 
     * @param node
     *            the node containing the seminar
     */
    public LeafNode(SeminarNode node) {
        this.node = node;
    }


    /**
     * Adding a seminar to the linked list
     * Uses Priority linking
     * 
     * @param sem
     *            the seminar to add
     */
    public void addSeminar(Seminar sem) {
        // creating a new node for the linked list
        SeminarNode newNode = new SeminarNode(sem);
        // traversing the linked list
        SeminarNode current = node;
        SeminarNode prev = null;
        // while not at the end of the list
        while (current != null) {
            // finding proper sorting of the list
            if (sem.id() <= current.getSem().id()) {
                newNode.setNext(current);
                current = newNode;
                return;
            }
            else {
                prev = current;
                current = current.getNext();
            }
        }
        current = newNode;
        prev.setNext(current);

    }


    /**
     * Removes the stored seminar from the linked list
     * 
     * @param sem
     *            the seminar to remove
     * @return true if successfully removed
     */
    public boolean deleteSem(Seminar sem) {
        // used to traverse the linked list
        SeminarNode current = node;
        SeminarNode prev = null;
        // while not at end of list
        while (current != null) {
            // found seminar!!
            if (current.getSem().id() == sem.id()) {
                if (prev != null) {
                    prev.setNext(current.getNext());
                }
                node = current.getNext();
                return true;
            }
            prev = current;
            current = current.getNext();

        }
        return false;
    }


    /**
     * @return returns the head of the linked list
     */
    public SeminarNode getSemNode() {
        return node;
    }


    /**
     * 
     * @return true if there are no seminars stored in the node
     */
    public boolean isEmpty() {
        return node == null;
    }


    /**
     * Override the original isLeaf method
     */
    @Override
    public boolean isLeaf() {
        return true;
    }


    @Override
    public void print() {
        SeminarNode current = getSemNode();

        String s = "";
        int count = 0;
        while (current != null) {
            s += " " + current.getSem().id();
            current = current.getNext();
            count++;
        }
        System.out.printf("Leaf with %d objects:%s%n", count, s);

    }
}
