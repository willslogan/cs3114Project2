import java.lang.reflect.Array;
import student.TestCase;
import java.util.ArrayList;

/**
 * Test class for Skiplist
 * 
 * @author Will Logan
 * @version 1.0
 *          Test class for Skiplist
 */
public class SkipListTest extends TestCase {
    private SkipList<String, Point> sList;
    private Point[] poi;
    private KVPair<String, Point>[] listPoints;

    /**
     * Sets up values for testing skiplist
     */
    @SuppressWarnings("unchecked")
    public void setUp() {
        sList = new SkipList<String, Point>();
        poi = new Point[10];
        poi[0] = new Point(0, 0, "p0");
        poi[1] = new Point(1, 1, "p1");
        poi[2] = new Point(-1, -5, "p2");
        poi[3] = new Point(20, 20, "p3");
        poi[4] = new Point(2, 2, "p4");
        poi[5] = new Point(-10, -10, "p5");
        poi[6] = new Point(9, 4, "p6");
        poi[7] = new Point(1030, 400, "p7");
        poi[8] = new Point(400, 1030, "p8");
        listPoints = (KVPair[])Array.newInstance(KVPair.class, 10);
        for (int i = 0; i < 5; i++) {
            listPoints[i] = new KVPair<String, Point>(poi[i].getName(), poi[i]);
        }
        for (int i = 5; i < 9; i++) {
            listPoints[i] = new KVPair<String, Point>("q" + i, poi[i]);
        }
    }


    /**
     * Tests Skiplists insert function
     */
    public void testInsert() {
        sList.insert(listPoints[0]);
        assertEquals(1, sList.size());
        sList.insert(listPoints[1]);
        assertEquals(2, sList.size());
        sList.insert(listPoints[0]);
        assertEquals(3, sList.size());
        sList.insert(listPoints[2]);
        assertEquals(4, sList.size());
        sList.insert(listPoints[3]);
        assertEquals(5, sList.size());
        sList.insert(listPoints[4]);
        assertEquals(6, sList.size());
        sList.insert(listPoints[5]);
        assertEquals(7, sList.size());
    }


    /**
     * Tests Skiplists dump function
     */
    public void testDump() {
        // Levels from see
        // 4, 0, 5, 1
        sList.dump();
        assertFuzzyEquals(systemOut().getHistory(),
            "SkipList dump: \nNode has depth 1, value null "
            + "\nSkipList size is: 0");

        systemOut().clearHistory();
        sList.insert(listPoints[0]);
        sList.dump();
        assertFuzzyEquals(systemOut().getHistory(),
            "SkipList dump: \nNode has depth 4, Value null"
                + "\nNode has depth 4, Value (p0, 0, 0)"
                + "\nSkipList size is: 1");

        systemOut().clearHistory();
        sList.insert(listPoints[1]);
        sList.dump();
        assertFuzzyEquals(systemOut().getHistory(),
            "SkipList dump: \nNode has depth 4, Value null"
                + "\nNode has depth 4, Value (p0, 0, 0)"
                + "\nNode has depth 0, Value (p1, 1, 1)"
                + "\nSkipList size is: 2");

        systemOut().clearHistory();
        sList.insert(listPoints[5]);
        sList.dump();
        assertFuzzyEquals(systemOut().getHistory(),
            "SkipList dump: \nNode has depth 5, Value null"
                + "\nNode has depth 4, Value (p0, 0, 0)"
                + "\nNode has depth 0, Value (p1, 1, 1)"
                + "\nNode has depth 5, Value (p5, 10, 10)"
                + "\nSkipList size is: 3");

        systemOut().clearHistory();
        sList.insert(listPoints[2]);
        sList.dump();
        assertFuzzyEquals(systemOut().getHistory(),
            "SkipList dump: \nNode has depth 5, Value null"
                + "\nNode has depth 4, Value (p0, 0, 0)"
                + "\nNode has depth 0, Value (p1, 1, 1)"
                + "\nNode has depth 1, Value (p2, 1, 5)"
                + "\nNode has depth 5, Value (p5, 10, 10)"
                + "\nSkipList size is: 4");
    }


    /**
     * Test search
     */
    public void testSearch() {
        // Levels for random seed in order
        // 4, 0, 5, 1, 4, 0, 1, 4, 0

        ArrayList<KVPair<String, Point>> expected =
            new ArrayList<KVPair<String, Point>>();
        ArrayList<KVPair<String, Point>> actual = null;

        // test1 empty skiplist
        assertNull(sList.search("p3"));

        // test2 test where first point is lower level than second
        sList.insert(listPoints[0]);
        sList.insert(listPoints[1]);
        sList.insert(listPoints[1]);
        expected.add(listPoints[1]);
        expected.add(listPoints[1]);
        actual = sList.search("p1");
        assertEquals(expected, actual);

        // test3 test when point is at end of list
        sList.insert(listPoints[2]);
        expected.clear();
        expected.add(listPoints[2]);
        actual = sList.search("p2");
        assertEquals(expected, actual);

        // test4 test when point is first item
        expected.clear();
        actual = sList.search("p0");
        expected.add(listPoints[0]);
        assertEquals(expected, actual);

        // test5 item isn't in list
        actual = sList.search("x5");
        assertNull(actual);

        // Test when point is in the middle of the list
        sList.insert(listPoints[3]);
        expected.clear();
        expected.add(listPoints[2]);
        actual = sList.search("p2");
        assertEquals(expected, actual);
    }


    /**
     * Test remove by name
     */
    public void testRemove() {
        // Levels for random seed order
        // 4, 0, 5, 1, 4, 0, 1, 4, 0, 0

        // Test 1 empty list
        systemOut().clearHistory();
        assertNull(sList.remove("p1"));
        sList.dump();
        assertFuzzyEquals("SkipList dump:" + "\nNode has depth 1, value null"
            + "\nSkipList size is: 0", systemOut().getHistory());

        // Test 2 one item in list
        systemOut().clearHistory();
        sList.insert(listPoints[1]); // Size 1 level 4
        assertEquals(listPoints[1], sList.remove("p1"));
        sList.dump();
        assertFuzzyEquals("SkipList dump:" + "\nNode has depth 4, Value null"
            + "\nSkipList size is: 0", systemOut().getHistory());
        assertEquals(0, sList.size());

        // Test 2 one Item in list and item to be removed not there
        systemOut().clearHistory();
        sList.insert(listPoints[1]); // size 1 , level 0
        assertNull(sList.remove("r1"));

        sList.dump();
        assertFuzzyEquals("SkipList dump:" + "\nNode has depth 4, Value null"
            + "\nNode has depth 0, Value (p1, 1, 1)" + "\nSkipList size is: 1",
            systemOut().getHistory());

        assertEquals(1, sList.size());

        // Test 3 add a couple more and remove last node
        systemOut().clearHistory();
        sList.insert(listPoints[0]); // size 2 level 5
        sList.insert(listPoints[2]); // size 3 level 1
        sList.insert(listPoints[3]); // size 4 level 4
        sList.dump();
        assertFuzzyEquals("SkipList dump:" + "\nNode has depth 5, Value null"
            + "\nNode has depth 5, Value (p0, 0, 0)"
            + "\nNode has depth 0, Value (p1, 1, 1)"
            + "\nNode has depth 1, Value (p2, 1, 5)"
            + "\nNode has depth 4, Value (p3, 20, 20)"
            + "\nSkipList size is: 4", systemOut().getHistory());
        assertEquals(listPoints[3], sList.remove("p3"));

        systemOut().clearHistory();
        sList.dump();
        assertFuzzyEquals("SkipList dump:" + "\nNode has depth 5, Value null"
            + "\nNode has depth 5, Value (p0, 0, 0)"
            + "\nNode has depth 0, Value (p1, 1, 1)"
            + "\nNode has depth 1, Value (p2, 1, 5)" + "\nSkipList size is: 3",
            systemOut().getHistory());
        assertEquals(3, sList.size());

        // Test 4 remove first node
        systemOut().clearHistory();
        sList.insert(listPoints[3]); // size 4 level 0
        assertEquals(listPoints[0], sList.remove("p0"));
        sList.dump();
        assertFuzzyEquals("SkipList dump:" + "\nNode has depth 5, Value null"
            + "\nNode has depth 0, Value (p1, 1, 1)"
            + "\nNode has depth 1, Value (p2, 1, 5)"
            + "\nNode has depth 0, Value (p3, 20, 20)"
            + "\nSkipList size is: 3", systemOut().getHistory());

        assertEquals(3, sList.size());

        // Test 5 remove node that has duplicates that are middle
        systemOut().clearHistory();
        sList.insert(listPoints[2]); // size 4 level 1
        sList.insert(listPoints[2]); // size 5 level 4
        assertEquals(listPoints[2], sList.remove("p2"));
        sList.dump();
        assertFuzzyEquals("SkipList dump:" + "\nNode has depth 5, Value null"
            + "\nNode has depth 0, Value (p1, 1, 1)"
            + "\nNode has depth 1, Value (p2, 1, 5)"
            + "\nNode has depth 1, Value (p2, 1, 5)"
            + "\nNode has depth 0, Value (p3, 20, 20)"
            + "\nSkipList size is: 4", systemOut().getHistory());
    }


    /**
     * Test removeByValue
     */
    public void testRemoveByValue() {
        assertNull(sList.removeByValue(listPoints[0].getValue()));

        sList.insert(listPoints[0]);
        sList.insert(listPoints[1]);
        sList.insert(listPoints[5]);
        sList.insert(listPoints[2]);
        sList.dump();
        assertFuzzyEquals("SkipList dump:" + "\nNode has depth 5, Value null"
            + "\nNode has depth 4, Value (p0, 0, 0)"
            + "\nNode has depth 0, Value (p1, 1, 1)"
            + "\nNode has depth 1, Value (p2, 1, 5)"
            + "\nNode has depth 5, Value (p5, 10, 10)"
            + "\nSkipList size is: 4", systemOut().getHistory());

        systemOut().clearHistory();
        sList.removeByValue(listPoints[0].getValue());
        sList.dump();
        assertFuzzyEquals("SkipList dump:" + "\nNode has depth 5, Value null"
            + "\nNode has depth 0, Value (p1, 1, 1)"
            + "\nNode has depth 1, Value (p2, 1, 5)"
            + "\nNode has depth 5, Value (p5, 10, 10)"
            + "\nSkipList size is: 3", systemOut().getHistory());

        systemOut().clearHistory();
        sList.removeByValue(listPoints[2].getValue());
        sList.dump();
        assertFuzzyEquals("SkipList dump:" + "\nNode has depth 5, Value null"
            + "\nNode has depth 0, Value (p1, 1, 1)"
            + "\nNode has depth 5, Value (p5, 10, 10)"
            + "\nSkipList size is: 2", systemOut().getHistory());

        systemOut().clearHistory();
        Point tempPoi = new Point(20, 20, "p20");
        assertNull(sList.removeByValue(tempPoi));
        sList.dump();
        assertFuzzyEquals("SkipList dump:" + "\nNode has depth 5, Value null"
            + "\nNode has depth 0, Value (p1, 1, 1)"
            + "\nNode has depth 5, Value (p5, 10, 10)"
            + "\nSkipList size is: 2", systemOut().getHistory());
    }


    /**
     * Test removeByValue
     */
    public void testRemoveByValueKeyCheck() {
        assertNull(sList.removeByValueKeyCheck(listPoints[0].getKey(),
            listPoints[0].getValue()));

        sList.insert(listPoints[0]);
        sList.insert(listPoints[1]);
        sList.insert(listPoints[5]);
        sList.insert(listPoints[2]);
        sList.dump();
        assertFuzzyEquals("SkipList dump:" + "\nNode has depth 5, Value null"
            + "\nNode has depth 4, Value (p0, 0, 0)"
            + "\nNode has depth 0, Value (p1, 1, 1)"
            + "\nNode has depth 1, Value (p2, 1, 5)"
            + "\nNode has depth 5, Value (p5, 10, 10)"
            + "\nSkipList size is: 4", systemOut().getHistory());

        systemOut().clearHistory();
        sList.removeByValueKeyCheck(listPoints[0].getKey(), listPoints[0]
            .getValue());
        sList.dump();
        assertFuzzyEquals("SkipList dump:" + "\nNode has depth 5, Value null"
            + "\nNode has depth 0, Value (p1, 1, 1)"
            + "\nNode has depth 1, Value (p2, 1, 5)"
            + "\nNode has depth 5, Value (p5, 10, 10)"
            + "\nSkipList size is: 3", systemOut().getHistory());

        systemOut().clearHistory();
        sList.removeByValueKeyCheck(listPoints[2].getKey(), listPoints[2]
            .getValue());
        sList.dump();
        assertFuzzyEquals("SkipList dump:" + "\nNode has depth 5, Value null"
            + "\nNode has depth 0, Value (p1, 1, 1)"
            + "\nNode has depth 5, Value (p5, 10, 10)"
            + "\nSkipList size is: 2", systemOut().getHistory());

        systemOut().clearHistory();
        Point tempPoi = new Point(20, 20, "p20");
        assertNull(sList.removeByValueKeyCheck(tempPoi.getName(), tempPoi));
        sList.dump();
        assertFuzzyEquals("SkipList dump:" + "\nNode has depth 5, Value null"
            + "\nNode has depth 0, Value (p1, 1, 1)"
            + "\nNode has depth 5, Value (p5, 10, 10)"
            + "\nSkipList size is: 2", systemOut().getHistory());
    }
}
