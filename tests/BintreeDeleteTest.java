import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import student.TestCase;

/**
 * @author Xavier Akers
 * 
 * @version Last Updated 2023-10-05
 * 
 * @since 2023-10-05
 * 
 *        Bintree Delete tests from Input Files
 * 
 */
public class BintreeDeleteTest extends TestCase {
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
    public void testBintreeDelete() throws IOException {
        cp.readCommands("testInputFiles/testBintreeDeleteHard.txt");
        String expected = new String(Files.readAllBytes(Paths.get(
            "testOutputFiles/testBintreeDeleteHard_output.txt")));
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
