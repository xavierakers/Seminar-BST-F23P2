import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import student.TestCase;

/**
 * @author Xavier Akers
 * 
 * @version Last Updated 2023-10-10
 * 
 * @since 2023-10-05
 * 
 *        Bintree Insert tests from Input Files
 * 
 */
public class BintreeInsertTest extends TestCase {
    // Creating SeminarDB to handle function calls
    private SeminarDB controller = new SeminarDB(128);
    // Creating the commandPrcoessor to test
    private CommandProcessor cp = new CommandProcessor(controller);

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
}
