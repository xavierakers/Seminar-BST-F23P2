import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import student.TestCase;

/**
 * @author Xavier Akers
 * 
 * @version Last Updated 2023-14-2023
 * 
 * @since 2023-09-01
 * 
 *        Master Test Class
 * 
 */
public class MasterTest extends TestCase {
    /**
     * Check the toString method
     */
    public void testtoString() {
        String[] keywords = { "Good", "Bad", "Ugly" };
        String expected = "ID: 1729, Title: Seminar Title\n"
            + "Date: 2405231000, Length: 75, X: 15, Y: 33, Cost: 125\n"
            + "Description: This is a great seminar\n"
            + "Keywords: Good, Bad, Ugly";
        Seminar mysem = new Seminar(1729, "Seminar Title", "2405231000", 75,
            (short)15, (short)33, 125, keywords, "This is a great seminar");
        String semPrint = mysem.toString();
        System.out.println("testtoString");
        System.out.println(semPrint);
        assertTrue(semPrint.equals(expected));
        assertTrue(mysem.id() == 1729);
        assertTrue(mysem.cost() == 125);
        assertTrue(mysem.x() == 15);
        assertTrue(mysem.y() == 33);
        assertTrue(mysem.date().equals("2405231000"));
        String[] tempKeywords = mysem.keywords();
        for (int i = 0; i < keywords.length; i++) {
            assertTrue(tempKeywords[i].equals(keywords[i]));
        }
    }


    /**
     * Check the serialization/deserialization process
     * 
     * @throws Exception
     */
    public void testSeminarDS() throws Exception {
        String[] keywords = { "Good", "Bad", "Ugly" };

        Seminar mysem = new Seminar(1729, "Seminar Title", "2405231000", 75,
            (short)15, (short)33, 125, keywords, "This is a great seminar");
        String semPrint = mysem.toString();
        System.out.println(semPrint);
        assertTrue(semPrint.equals("ID: 1729, Title: Seminar Title\n"
            + "Date: 2405231000, Length: 75, X: 15, Y: 33, Cost: 125\n"
            + "Description: This is a great seminar\n"
            + "Keywords: Good, Bad, Ugly"));
    }

    // Creating SeminarDB to handle function calls
    private SeminarDB controller = new SeminarDB(128);
    // Creating the commandPrcoessor to test
    private CommandProcessor cp = new CommandProcessor(controller);

    /**
     * Testing hasNextLine() mutation
     * Reads in two different command files
     * 
     * @throws IOException
     */
    public void testHasNextLine() throws IOException {
        cp.readCommands("testInputFiles/P2syntaxInsert_input.txt");

        String expected = new String(Files.readAllBytes(Paths.get(
            "P2syntaxInsert_output.txt")));
        assertEquals(expected, systemOut().getHistory());

        cp.readCommands("testInputFiles/testNullInput.txt");

        expected = new String(Files.readAllBytes(Paths.get(
            "testOutputFiles/testHasNextLine_output.txt")));
        assertEquals(expected, systemOut().getHistory());
    }


    /**
     * Testing against the sample output
     * 
     * @throws IOException
     */
    public void testSampleInput() throws IOException {
        cp.readCommands("P2Sample_input.txt");
        String expected = new String(Files.readAllBytes(Paths.get(
            "P2Sample_output.txt")));
        assertEquals(expected, systemOut().getHistory());
    }


    /**
     * Basic Insert Command Parser Test
     * Read 3 insert commands and execute proper SeminarDB function
     * 
     * @throws IOException
     */
    public void testInsert() throws IOException {
        cp.readCommands("testInputFiles/P2syntaxInsert_input.txt");
        String expected = new String(Files.readAllBytes(Paths.get(
            "testOutputFiles/testBSTInsert_output.txt")));

        assertEquals(expected, systemOut().getHistory());
    }


    /**
     * Testing an insertion and search in the ID Tree
     * 
     * @throws IOException
     */
    public void testBasicInsertBSTAll() throws IOException {
        cp.readCommands("testInputFiles/testBSTBasicInserAll.txt");
        String expected = new String(Files.readAllBytes(Paths.get(
            "testOutputFiles/testBSTBasicInsertAll_output.txt")));
        assertEquals(expected, systemOut().getHistory());
    }


    /**
     * Testing an 4 insertions and search in all Tree
     * 
     * @throws IOException
     */
    public void testSearchBSTAll() throws IOException {
        cp.readCommands("testInputFiles/testBSTSearchAll.txt");
        String expected = new String(Files.readAllBytes(Paths.get(
            "testOutputFiles/testBSTSearchAll_output.txt")));
        assertEquals(expected, systemOut().getHistory());
    }


    /**
     * Prints all empty BSTS
     * 
     * @throws IOException
     */
    public void testPrintEmptyBSTs() throws IOException {
        cp.readCommands("testInputFiles/testBSTPrintEmpty.txt");
        String expected = new String(Files.readAllBytes(Paths.get(
            "testOutputFiles/testBSTPrintEmpty_output.txt")));

        assertEquals(expected, systemOut().getHistory());
    }


    /**
     * Inserts 4 different records, checks for expected binary trees
     * 
     * @throws IOException
     */
    public void testPrintBSTs() throws IOException {
        cp.readCommands("testInputFiles/testBSTPrint.txt");
        String expected = new String(Files.readAllBytes(Paths.get(
            "testOutputFiles/testBSTPrint_output.txt")));

        assertEquals(expected, systemOut().getHistory());
    }


    /**
     * Attempts to delete a record that does not exist
     * 
     * @throws IOException
     */
    public void testDeleteNull() throws IOException {
        cp.readCommands("testInputFiles/testBSTDeleteNull.txt");
        String expected = new String(Files.readAllBytes(Paths.get(
            "testOutputFiles/testBSTDeleteNull_output.txt")));
        assertEquals(expected, systemOut().getHistory());
    }


    /**
     * Inserts 4 Seminars, Deletes 4 Seminars
     * Checks to make sure all data is removed from each BST
     * 
     * @throws IOException
     */
    public void testHarderDelete() throws IOException {
        cp.readCommands("testInputFiles/testBSTDeleteHard.txt");
        String expected = new String(Files.readAllBytes(Paths.get(
            "testOutputFiles/testBSTDeleteHard_output.txt")));
        assertEquals(expected, systemOut().getHistory());
    }


    /**
     * Inserts 4 Seminars
     * Checks the Location by printing the tree
     * 
     * @throws IOException
     */
    public void testLocationInsert() throws IOException {
        cp.readCommands("testInputFiles/testBintreeInsert.txt");
        String expected = new String(Files.readAllBytes(Paths.get(
            "testOutputFiles/testBintreeInsert_output.txt")));
        assertEquals(expected, systemOut().getHistory());
    }


    /**
     * Inserted numerous Seminar with various coordinates
     * 
     * @throws IOException
     */
    public void testInsertHarderBinTree() throws IOException {
        cp.readCommands("testInputFiles/testBintreeInsertHard.txt");
        String expected = new String(Files.readAllBytes(Paths.get(
            "testOutputFiles/testBintreeInsertHard_output.txt")));
        assertEquals(expected, systemOut().getHistory());
    }


    /**
     * Testing deletes from sample input files
     * 
     * @throws IOException
     */
    public void testBintreeSearch() throws IOException {
        cp.readCommands("testInputFiles/testBintreeSearchTest.txt");
        String expected = new String(Files.readAllBytes(Paths.get(
            "testOutputFiles/testBintreeSearch_output.txt")));
        assertEquals(expected, systemOut().getHistory());
    }


    /**
     * Prints an empty Bintree
     * 
     * @throws IOException
     */
    public void testPrintEmptyBinTree() throws IOException {
        cp.readCommands("testInputFiles/testBintreePrintEmpty.txt");
        String expected = new String(Files.readAllBytes(Paths.get(
            "testOutputFiles/testBintreePrintEmpty_output.txt")));
        assertEquals(expected, systemOut().getHistory());
    }


    /**
     * Adding more seminars to the right subtrees
     * 
     * @throws IOException
     */
    public void testInsertComplexBinTree() throws IOException {
        cp.readCommands("testInputFiles/testBintreeInsertComplex.txt");
        String expected = new String(Files.readAllBytes(Paths.get(
            "testOutputFiles/testBintreeInsertComplex_output.txt")));
        assertEquals(expected, systemOut().getHistory());
    }


    /**
     * Deleting from the complex insert
     * 
     * @throws IOException
     */
    public void testDeleteComplexBintree() throws IOException {
        cp.readCommands("testInputFiles/testBintreeDeleteComplex.txt");
        String expected = new String(Files.readAllBytes(Paths.get(
            "testOutputFiles/testBintreeDeleteComplex_output.txt")));
        assertEquals(expected, systemOut().getHistory());
    }
}
