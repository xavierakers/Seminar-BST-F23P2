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
 *        BST Delete tests from Input Files
 * 
 */
public class BSTDeleteTest extends TestCase {
    // Creating SeminarDB to handle function calls
    private SeminarDB controller = new SeminarDB(128);
    // Creating the commandPrcoessor to test
    private CommandProcessor cp = new CommandProcessor(controller);

    /**
     * Attempts to delete a record that does not exist
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
     * @throws IOException 
     */
    public void testHarderDelete() throws IOException {
        cp.readCommands("testInputFiles/testBSTDeleteHard.txt");
        String expected = new String(Files.readAllBytes(Paths.get(
            "testOutputFiles/testBSTDeleteHard_output.txt")));
        assertEquals(expected, systemOut().getHistory());
    }

}
