import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Xavier Akers
 * 
 * @version Last Updated 2023-09-21
 * 
 * @since 2023-09-21
 * 
 *        A simple file parser
 * 
 */
public class CommandProcessor {
    private SeminarDB controller;

    /**
     * Default constructor to initialize controller
     */
    public CommandProcessor() {
        this.controller = new SeminarDB(128);
    }


    /**
     * Constructor to receive a controller
     * 
     * @param controller
     *            Controls the HashTable and MemoryManager
     */
    public CommandProcessor(SeminarDB controller) {
        this.controller = controller;
    }


    /**
     * 1. Read commands
     * 2. Read in following arguments
     * 3. Send arguments to respective DB function
     * 
     * @param filename
     *            A string containing the input file
     */
    public void readCommands(String filename) {
        try {
            // Input scanner
            Scanner sc = new Scanner(new File(filename));
            // Buffer to read in lines
            String[] line;
            // Isolate the command
            String cmd;
            // Isolate the ID
            String id = null;

            // Scan until EOF
            while (sc.hasNextLine()) {

                line = sc.nextLine().trim().split("\\s+");
                cmd = line[0].trim();
                // If there was an ID associated with the command
                if (line.length > 1) {
                    id = line[1].trim();
                }

                // Skips the random newlines in the input file
                if (!cmd.isEmpty()) {
                    switch (cmd) {
                        case "insert":
                            // Array to store input data
                            ArrayList<String> data = new ArrayList<>();

                            // Storing the data individually
                            String title = sc.nextLine();

                            // Splitting numerical values
                            String[] nums = sc.nextLine().trim().split("\\s+");
                            String date = nums[0];
                            String length = nums[1];
                            String x = nums[2];
                            String y = nums[3];
                            String cost = nums[4];

                            String keywords = sc.nextLine().trim();
                            String desc = sc.nextLine().trim();

                            // Adding data to passing array
                            data.add(id);
                            data.add(title);
                            data.add(date);
                            data.add(length);
                            data.add(x);
                            data.add(y);
                            data.add(cost);
                            data.add(keywords);
                            data.add(desc);

                            controller.insertSem(data);

                            break;
                        case "delete":
                            controller.deleteSem(id);

                            break;
                        case "search":
                            switch (line[1].trim()) {
                                case "ID":
                                    controller.exactSearch(line[2].trim());
                                    break;
                                case "cost":
                                    controller.rangeSearch(line[2].trim(),
                                        line[3].trim(), "cost");
                                    break;
                                case "date":
                                    controller.rangeSearch(line[2].trim(),
                                        line[3].trim(), "date");
                                    break;
                                case "keyword":
                                    controller.multiSearch(line[2].trim());
                                    break;
                                case "location":
                                    controller.locationSearch(line[2].trim(),
                                        line[3].trim(), line[4].trim());
                                    break;
                                default:
                                    break;
                            }
                            controller.searchSem(id);

                            break;
                        case "print":
                            // Checking print type
                            switch (line[1].trim()) {
                                case "date":
                                    controller.printDateBST();
                                    break;
                                case "keyword":
                                    controller.printKeywordBST();
                                    break;
                                case "location":
                                    controller.printLocationTree();
                                    break;
                                case "cost":
                                    controller.printCostBST();
                                    break;
                                case "ID":
                                    controller.printIDBST();
                                    break;
                                default:
                                    break;
                            }
                            break;
                        default:
                            System.out.println("Invalid command");
                    }
                }
            }
            sc.close();

        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
