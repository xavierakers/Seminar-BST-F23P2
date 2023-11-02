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
 *        BST Insert tests from Input Files
 * 
 */
public class BSTInsertTest extends TestCase {
    // Creating SeminarDB to handle function calls
    private SeminarDB controller = new SeminarDB(128);
    // Creating the commandPrcoessor to test
    private CommandProcessor cp = new CommandProcessor(controller);

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
     * @throws IOException 
     */
    public void testSearchBSTAll() throws IOException {
        cp.readCommands("testInputFiles/testBSTSearchAll.txt");
        String expected = new String(Files.readAllBytes(Paths.get(
            "testOutputFiles/testBSTSearchAll_output.txt")));
        assertEquals(expected, systemOut().getHistory());
    }
}
