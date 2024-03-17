import student.TestCase;

/**
 * @author Jacob Fast
 * @version 1.0
 */
public class DatabaseTest extends TestCase {
    private Database data;

    private Point invalid;
    private Point valid;
    private Point badN;
    private Point differentName;
    private Point sameName;
    private KVPair<String, Point> pair;
    private KVPair<String, Point> badName;
    private KVPair<String, Point> badPoint;
    private KVPair<String, Point> diffName;
    private KVPair<String, Point> sName;

    /**
     * Set up variables used in testing
     */
    public void setUp() {
        data = new Database();
        invalid = new Point(-5, 5, "a5");
        valid = new Point(5, 5, "b5");
        badN = new Point(20, 20, "62");
        differentName = new Point(5, 5, "c5");
        sameName = new Point(10, 10, "b5");
        pair = new KVPair<String, Point>("b5", valid);
        badName = new KVPair<String, Point>("62", badN);
        badPoint = new KVPair<String, Point>("a5", invalid);
        diffName = new KVPair<String, Point>("c5", differentName);
        sName = new KVPair<String, Point>("b5", sameName);
    }


    /**
     * 
     */
    public void testInsert() {
        // test inserting a point with an invalid name
        data.insert(badName);
        assertEquals(systemOut().getHistory(),
            "Point rejected: (62, 20, 20)\n");
        systemOut().clearHistory();

        // test inserting a point with an invalid location
        data.insert(badPoint);
        assertEquals(systemOut().getHistory(), "Point rejected: (a5, -5, 5)\n");
        systemOut().clearHistory();

        // test inserting a point with an valid position
        data.insert(pair);
        assertEquals(systemOut().getHistory(), "Point inserted: (b5, 5, 5)\n");
        systemOut().clearHistory();

        // test inserting a duplicate point (same name and position)
        data.insert(pair);
        assertEquals(systemOut().getHistory(), "Point rejected: (b5, 5, 5)\n");
        systemOut().clearHistory();

        // test inserting a duplicate point (same name, different position)
        data.insert(diffName);
        assertEquals(systemOut().getHistory(), "Point inserted: (c5, 5, 5)\n");
        systemOut().clearHistory();

    }


    /**
     * 
     */
    public void testRegionSearch() {
        // Test all possible invalid rectangle
        data.regionsearch(5, 5, -10, 5);
        assertEquals(systemOut().getHistory(),
            "Rectangle rejected: (5, 5, -10, 5)\n");
        systemOut().clearHistory();

        data.regionsearch(5, 5, 5, -10);
        assertEquals(systemOut().getHistory(),
            "Rectangle rejected: (5, 5, 5, -10)\n");
        systemOut().clearHistory();

        // Test valid rectangle
        data.regionsearch(5, 5, 10, 10);
        assertEquals(systemOut().getHistory(),
            "Points intersecting region (5, 5, 10, 10):\n1 quadtree nodes visited\n");
        systemOut().clearHistory();

    }


    /**
     * 
     */
    public void testSearch() {
        // Test point that isn't in the database
        data.search("a5");
        assertEquals(systemOut().getHistory(), "Point not found: a5\n");
        systemOut().clearHistory();

        // Test points in the data base
        data.insert(pair);
        data.insert(sName);
        systemOut().clearHistory();
        data.search("b5");
        assertEquals(systemOut().getHistory(), "Found (b5, 10, 10)\n" + "Found (b5, 5, 5)\n");
    }


    /**
     * 
     */
    public void testRemoveByName() {
        // Test point that isn't in database
        data.remove("a5");
        assertFuzzyEquals("Point not removed: (a5)\n", systemOut().getHistory());
        systemOut().clearHistory();

        // Test removing a point in the database
        data.insert(pair);
        systemOut().clearHistory();
        data.remove("b5");
        assertEquals("Point removed: (b5, 5, 5)\n", systemOut().getHistory());
        systemOut().clearHistory();

    }


    /**
     * 
     */
    public void testRemoveByValue() {
        // Test invalid points
        data.remove(-1, 5);
        assertEquals("Point rejected: (-1, 5)\n", systemOut().getHistory());
        systemOut().clearHistory();

        data.remove(5, -1);
        assertEquals("Point rejected: (5, -1)\n", systemOut().getHistory());
        systemOut().clearHistory();

        // Test point not found
        data.remove(5, 5);
        assertEquals("Point not found: (5, 5)\n", systemOut().getHistory());
        systemOut().clearHistory();

        // Test point in database
        data.insert(pair);
        data.insert(diffName);
        systemOut().clearHistory();

        data.remove(5, 5);
        assertEquals("Point removed: (b5, 5, 5)\n", systemOut().getHistory());

    }


    /**
     * 
     */
    public void testDump() {
        data.insert(pair);
        data.insert(diffName);
        systemOut().clearHistory();

        data.dump();
        assertFuzzyEquals("SkipList dump:\n" + "Node has depth 4, value null\n"
            + "Node has depth 4, value (b5, 5, 5)\n"
            + "Node has depth 0, value (c5, 5, 5)\n"
            + "SkipList size is: 2\n"
            + "QuadTree Dump:\n" +  "Node at 0, 0, 1024: \n" + "    (b5, 5, 5)\n" + "    (c5, 5, 5)\n"+"1 quadtree nodes printed\n", systemOut()
                .getHistory());

    }


    /**
     * 
     */
    public void testDuplicates() {
        data.insert(pair);
        data.insert(diffName);
        systemOut().clearHistory();
        
        data.duplicates();
        assertFuzzyEquals("Duplicate points:\n(5, 5)\n", systemOut().getHistory());
    }
}
