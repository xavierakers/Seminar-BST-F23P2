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
 *        Bintree Print tests from Input Files
 * 
 */
public class BintreePrintTest extends TestCase {
    // Creating SeminarDB to handle function calls
    private SeminarDB controller = new SeminarDB(128);
    // Creating the commandPrcoessor to test
    private CommandProcessor cp = new CommandProcessor(controller);

    /**
     * Prints an empty Bintree
     * @throws IOException 
     */
    public void testPrintEmptyBinTree() throws IOException {
        cp.readCommands("testInputFiles/testBintreePrintEmpty.txt");
        String expected = new String(Files.readAllBytes(Paths.get(
            "testOutputFiles/testBintreePrintEmpty_output.txt")));
        assertEquals(expected, systemOut().getHistory());
    }
}
