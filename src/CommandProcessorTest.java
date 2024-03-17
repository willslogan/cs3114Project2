/**
 * 
 */
import student.TestCase;
/**
 * @author Jacob Fast
 * @version 1.0 
 */
public class CommandProcessorTest extends TestCase {
    private CommandProcessor cmdProc;
    private String insert;

    private String removebn;
    private String removebx;
    private String rsearch;
    private String search;
    private String dump;
    private String duplicates;
    private String unrecognized;
    
    /**
     * Set up variables used in testing
     */
    public void setUp() {
        cmdProc = new CommandProcessor();
        insert = "insert a2 5 10";
        rsearch = "regionsearch 1 1 100 100";
        search = "search a2";
        dump = "dump";
        unrecognized = "Gibberish";
        duplicates = "duplicates";
        removebn = "remove a5";
        removebx = "remove 20 5";
    }
    
    public void testProcessor() {
        cmdProc.processor(insert);
        assertFuzzyEquals("Point inserted: (a2, 5, 10)\n", systemOut()
            .getHistory());
        systemOut().clearHistory();

        
        cmdProc.processor(removebn);
        assertFuzzyEquals("Point not removed: (a5)\n", systemOut()
            .getHistory());
        systemOut().clearHistory();
        
        
        cmdProc.processor(removebx);
        assertFuzzyEquals("Point not found: (20, 5)\n", systemOut()
            .getHistory());
        systemOut().clearHistory();
        
        cmdProc.processor(rsearch);
        assertFuzzyEquals("Points intersecting region (1, 1, 100, 100)\n" + "Point found (a2, 5, 10)\n + 1 quadtree nodes visited",
            systemOut().getHistory());
        systemOut().clearHistory();


        cmdProc.processor(search);
        assertFuzzyEquals("Found: " + "(a2, 5, 10)\n",
            systemOut().getHistory());
        systemOut().clearHistory();
        
        cmdProc.processor(duplicates);
        assertFuzzyEquals(systemOut().getHistory(), "Duplicate points:\n");
        systemOut().clearHistory();

        cmdProc.processor(dump);
        assertFuzzyEquals("Skiplist Dump:\n" + "Node with depth: 4 Value null\n"
            + "Node with depth: 4 Value (a2, 5, 10)\n"
            + "SkipList size is: 1\n"
            + "QuadTree Dump:\n"
            + "Node At: 0, 0, 1024\n"
            + "(a2, 5, 10)\n"
            + "1 quadtree node(s) printed.\n"
            , systemOut().getHistory());
        systemOut().clearHistory();

        cmdProc.processor(unrecognized);
        assertEquals(systemOut().getHistory(), "Unrecognized command.\n");
    }
}
