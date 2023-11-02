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
 *        BST Print tests from Input Files
 * 
 */
public class BSTPrintTest extends TestCase {
    // Creating SeminarDB to handle function calls
    private SeminarDB controller = new SeminarDB(128);
    // Creating the commandPrcoessor to test
    private CommandProcessor cp = new CommandProcessor(controller);

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
}
