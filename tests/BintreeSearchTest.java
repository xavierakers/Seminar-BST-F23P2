import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import student.TestCase;

/**
 * @author Xavier Akers
 * 
 * @version Last Updated 2023-10-10
 * 
 * @since 2023-10-09
 * 
 *        Master Test Class
 * 
 */
public class BintreeSearchTest extends TestCase {
    // Creating SeminarDB to handle function calls
    private SeminarDB controller = new SeminarDB(128);
    // Creating the commandPrcoessor to test
    private CommandProcessor cp = new CommandProcessor(controller);

    /**
     * Testing deletes from sample input files
     * @throws IOException 
     */
    public void testBintreeSearch() throws IOException {
        cp.readCommands("testInputFiles/testBintreeSearchTest.txt");
        String expected = new String(Files.readAllBytes(Paths.get(
            "testOutputFiles/testBintreeSearch_output.txt")));
        assertEquals(expected, systemOut().getHistory());
    }
}
