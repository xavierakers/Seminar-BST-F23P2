import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import student.TestCase;

/**
 * @author Xavier Akers
 * 
 * @version Last Updated 2023-10-10
 * 
 * @since 2023-09-01
 * 
 *        Command Processor Class Tests
 * 
 */
public class CommandProcessorTest extends TestCase {
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

}
