/**
 * @author Xavier Akers
 * 
 * @version Last Updated 2023-10-05
 * 
 * @since 2023-10-05
 * 
 *        Link-able Nodes for a Bintree
 *        Storing Seminar Objects
 * 
 */
public class SeminarNode {
    private Seminar sem;
    private SeminarNode next;

    /**
     * Constructor
     * 
     * @param sem
     *            the seminar to store
     */
    public SeminarNode(Seminar sem) {
        this.sem = sem;
        this.next = null;
    }


    /**
     * @return the seminar
     */
    public Seminar getSem() {
        return sem;
    }


    /**
     * @param sem
     *            the seminar to set
     */
    public void setSem(Seminar sem) {
        this.sem = sem;
    }


    /**
     * @return the next
     */
    public SeminarNode getNext() {
        return next;
    }


    /**
     * @param next
     *            the next to set
     */
    public void setNext(SeminarNode next) {
        this.next = next;
    }

}
