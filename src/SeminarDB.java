import java.util.ArrayList;

/**
 * @author Xavier Akers
 * 
 * @version Last Updated 2023-09-21
 * 
 * @since 2023-09-21
 * 
 *        Handles Record and Memory Integration
 * 
 */
public class SeminarDB {

    private int worldSize;

    private BST<Integer, Seminar> idBST;
    private BST<Integer, Seminar> costBST;
    private BST<String, Seminar> dateBST;
    private BST<String, Seminar> keywordBST;
    private Bintree locationTree;

    /**
     * Initializing connection between Input and Storage
     * 
     * @param worldSize
     *            The size ???
     */
    public SeminarDB(int worldSize) {

        this.worldSize = worldSize;

        this.idBST = new BST<Integer, Seminar>();
        this.costBST = new BST<Integer, Seminar>();
        this.dateBST = new BST<String, Seminar>();
        this.keywordBST = new BST<String, Seminar>();
        this.locationTree = new Bintree(worldSize, worldSize);
    }


    /**
     * 1. Creates a Seminar
     * 2. Sends to byte pool
     * 3. Stores pool handle in record table
     * 
     * @param input
     *            ArrayList containing data from the input file
     * @return True if successful
     * @throws Exception
     *             Serialization
     */
    public boolean insertSem(ArrayList<String> input) throws Exception {

        // Extracting data from data parser
        // Converting to proper variable type
        int id = Integer.parseInt(input.get(0).trim());
        String title = input.get(1);
        String date = (String)input.get(2);
        int length = Integer.parseInt(input.get(3).trim());
        Short x = Short.parseShort(input.get(4).trim());
        Short y = Short.parseShort(input.get(5).trim());
        int cost = Integer.parseInt(input.get(6));
        String keywordsLine = input.get(7);
        String[] keywords = keywordsLine.split("\\s+");
        String desc = input.get(8);

        // Checking valid location
        if ((x < 0 || y < 0) || (x >= worldSize) || (y >= worldSize)) {
            System.out.printf("Insert FAILED - Bad x, y coordinates: %d, %d%n",
                x, y);
            return false;
        }

        Seminar sem = new Seminar(id, title, date, length, x, y, cost, keywords,
            desc);

        // If record ID does not exist
        if (idBST.search(id) == null) {

            idBST.insert(id, sem);
            costBST.insert(cost, sem);
            dateBST.insert(date, sem);
            locationTree.insert(sem);

            for (int i = 0; i < keywords.length; i++) {
                keywordBST.insert(keywords[i], sem);
            }

            System.out.printf("Successfully inserted record with ID %d%n", id);
            System.out.println(sem);

            return true;
        }
        else {
            System.out.printf(
                "Insert FAILED - There is already a record with ID %d%n", id);
            return false;
        }
    }


    /**
     * Retrieves Seminar information
     * 
     * @param id
     *            Reference to search hash table reference
     * @return Returns true if found successfully
     * @throws Exception
     */
    public boolean searchSem(String id) throws Exception {
        return false;
    }


    /**
     * Printing idBST
     */
    public void printIDBST() {
        System.out.println("ID Tree:");
        idBST.printBST();
    }


    /**
     * Printing dateBST
     */
    public void printDateBST() {
        System.out.println("Date Tree:");
        dateBST.printBST();
    }


    /**
     * Printing costBST
     */
    public void printCostBST() {
        System.out.println("Cost Tree:");
        costBST.printBST();
    }


    /**
     * Printing keywordBST
     */
    public void printKeywordBST() {
        System.out.println("Keyword Tree:");
        keywordBST.printBST();
    }


    /**
     * Printing the locationTree
     */
    public void printLocationTree() {
        System.out.println("Location Tree:");
        locationTree.printTree();
    }


    /**
     * Searching in idBST
     * 
     * @param id
     *            the key
     */
    public void exactSearch(String id) {
        int key = Integer.parseInt(id);

        KVPair<Integer, Seminar> node = idBST.search(key);

        if (node == null) {
            System.out.printf(
                "Search FAILED -- There is no record with ID %d%n", key);
            return;
        }
        else {
            System.out.printf("Found record with ID %d:%n", key);
            System.out.println(node.getValue());
            return;
        }
    }


    /**
     * Searching for a Range
     * 
     * @param min
     *            lower bound
     * @param max
     *            upper bound
     * @param tree
     *            which tree to search
     */
    public void rangeSearch(String min, String max, String tree) {
        Object[] results = null;
        int[] count = { 0 };
        switch (tree) {
            case "date":
                results = dateBST.search(min, max, count);
                System.out.printf("Seminars with dates in range %s to %s:%n",
                    min, max);
                break;
            case "cost":
                results = costBST.search(Integer.parseInt(min), Integer
                    .parseInt(max), count);
                System.out.printf("Seminars with costs in range %s to %s:%n",
                    min, max);
                break;
            default:
                System.out.println("Invalid Tree");
                break;
        }

        for (int i = 0; i < results.length; i++) {
            System.out.println(results[i]);
        }
        System.out.printf("%d nodes visited in this search%n", count[0]);
    }


    /**
     * Searches for all Seminar with matching keyword
     * 
     * @param key
     *            Keyword to match
     */
    public void multiSearch(String key) {
        Object[] results = keywordBST.multiSearch(key);
        System.out.printf("Seminars matching keyword %s:%n", key);
        for (int i = 0; i < results.length; i++) {
            System.out.println(results[i]);
        }
    }


    /**
     * Deletes the Seminar from all BSTs and Bintree
     * Requires only the ID
     * 
     * @param key
     *            The ID key to delete
     */
    public void deleteSem(String key) {
        BinNode<Integer, Seminar> result = idBST.remove(Integer.parseInt(key));

        if (result == null) {
            System.out.printf(
                "Delete FAILED -- There is no record with ID %s%n", key);
            return;
        }

        Seminar sem = result.getValue().getValue();

        dateBST.remove(sem.date(), sem.id());

        costBST.remove(sem.cost(), sem.id());

        for (int i = 0; i < sem.keywords().length; i++) {
            keywordBST.remove(sem.keywords()[i], sem.id());
        }
        locationTree.delete(sem);

        System.out.printf(
            "Record with ID %s successfully deleted from the database%n", key);

    }


    /**
     * Searches for seminars within the specified range
     * 
     * @param x
     *            x search coord
     * @param y
     *            y search coord
     * @param d
     *            distance from search coord
     */
    public void locationSearch(String x, String y, String d) {
        System.out.printf("Seminars within %s units of %s, %s:%n", d, x, y);
        int[] count = new int[] { 0 };
        int xcoord = Integer.parseInt(x);
        int ycoord = Integer.parseInt(y);
        int radius = Integer.parseInt(d);
        Seminar[] results = locationTree.search(xcoord, ycoord, radius, count);
        for (int i = 0; i < results.length; i++) {
            System.out.printf("Found a record with key value %d at %d, %d%n",
                results[i].id(), results[i].x(), results[i].y());

        }
        System.out.printf("%d nodes visited in this search%n", count[0]);
    }
}
