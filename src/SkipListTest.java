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
    private SkipList<String, Rectangle> test1 = null;
    private Rectangle[] rec;
    private KVPair<String, Rectangle>[] listRecs;

    /**
     * Sets up values for testing skiplist
     */
    @SuppressWarnings("unchecked")
    public void setUp() {
        test1 = new SkipList<String, Rectangle>();
        rec = new Rectangle[10];
        rec[0] = new Rectangle(0, 0, 0, 0);
        rec[1] = new Rectangle(1, 1, 5, 10);
        rec[2] = new Rectangle(-1, -5, 15, 20);
        rec[3] = new Rectangle(20, 20, 10, 10);
        rec[4] = new Rectangle(2, 2, 10, 10);
        rec[5] = new Rectangle(-10, -10, 10, 10);
        rec[6] = new Rectangle(9, 4, 10, 10);
        rec[7] = new Rectangle(-100, 4, 10, 10);
        rec[8] = new Rectangle(-100, 4, 20, 10);
        rec[9] = new Rectangle(-100, 4, 10, 8);
        listRecs = (KVPair[])Array.newInstance(KVPair.class, 10);
        for (int i = 1; i <= 5; i++) {
            listRecs[i - 1] = new KVPair<String, Rectangle>("r" + i, rec[i
                - 1]);
        }
        for (int i = 5; i <= 10; i++) {
            listRecs[i - 1] = new KVPair<String, Rectangle>("a" + i, rec[i
                - 1]);
        }

    }


    /**
     * Tests Skiplists insert function
     */
    public void testInsert() {
        test1.insert(listRecs[0]);
        assertEquals(1, test1.size());
        test1.insert(listRecs[1]);
        assertEquals(2, test1.size());
        test1.insert(listRecs[0]);
        assertEquals(3, test1.size());
        test1.insert(listRecs[2]);
        assertEquals(4, test1.size());
        test1.insert(listRecs[3]);
        assertEquals(5, test1.size());
        test1.insert(listRecs[4]);
        assertEquals(6, test1.size());
        for (int i = 0; i <= 9; i++) {
            test1.insert(listRecs[i]);
            assertEquals(7 + i, test1.size());
        }
        KVPair<String, Rectangle> endList = new KVPair<String, Rectangle>(
            "zeta", rec[9]);
        test1.insert(endList);
        assertEquals(17, test1.size());
    }


    /**
     * Tests Skiplists dump function
     */
    public void testDump() {
        // levels from seed
        // 4
        // 0
        // 5
        // 1
        // 4
        // 0
        // 1
        // 4
        // 0
        test1.dump();
        assertFuzzyEquals("SkipList dump: " + "\nNode with depth 1, value null"
            + "\nSkipList size is: 0", systemOut().getHistory());
        systemOut().clearHistory();
        test1.insert(listRecs[0]);
        test1.dump();
        assertFuzzyEquals("SkipList dump:" + "\nNode with depth 4, Value null"
            + "\nNode with depth 4, Value (r1, 0, 0, 0, 0)"
            + "\nSkipList size is: 1", systemOut().getHistory());
        systemOut().clearHistory();
        test1.insert(listRecs[1]);
        test1.dump();
        assertFuzzyEquals("SkipList dump:\nNode with depth 4, Value null"
            + "\nNode with depth 4, Value (r1, 0, 0, 0, 0)"
            + "\nNode with depth 0, Value (r2, 1, 1, 5, 10)"
            + "\nSkipList size is: 2", systemOut().getHistory());
        systemOut().clearHistory();
        test1.insert(listRecs[5]);
        test1.dump();
        assertFuzzyEquals("SkipList dump:" + "\nNode with depth 5, Value null"
            + "\nNode with depth 5, Value (a6, -10, -10, 10, 10)"
            + "\nNode with depth 4, Value (r1, 0, 0, 0, 0)"
            + "\nNode with depth 0, Value (r2, 1, 1, 5, 10)"
            + "\nSkipList size is: 3", systemOut().getHistory());
        systemOut().clearHistory();
        test1.insert(listRecs[2]);
        test1.dump();
        assertFuzzyEquals("SkipList dump:" + "\nNode with depth 5, Value null"
            + "\nNode with depth 5, Value (a6, -10, -10, 10, 10)"
            + "\nNode with depth 4, Value (r1, 0, 0, 0, 0)"
            + "\nNode with depth 0, Value (r2, 1, 1, 5, 10)"
            + "\nNode with depth 1, Value (r3, -1, -5, 15, 20)"
            + "\nSkipList size is: 4", systemOut().getHistory());
    }


    /**
     * Test search
     */
    public void testSearch() {
        // levels for random seed in order
        // 4,0,5,1,4,0,1,4,0

        ArrayList<KVPair<String, Rectangle>> expected =
            new ArrayList<KVPair<String, Rectangle>>();
        ArrayList<KVPair<String, Rectangle>> actual = null;

        // test 1 empty skiplist test
        actual = test1.search("r2");
        assertNull(actual);

        // Test 2 one item in list
        expected.clear();
        test1.insert(listRecs[1]);
        actual = test1.search("r2");
        expected.add(listRecs[1]);
        assertEquals(expected, actual);

        // Test 2.5 a
        expected.clear();
        test1.insert(listRecs[0]);
        actual = test1.search("r1");
        expected.add(listRecs[0]);
        assertEquals(expected, actual);

        // Test 2.5 b
        expected.clear();
        actual = test1.search("r2");
        expected.add(listRecs[1]);
        assertEquals(expected, actual);

        // test 3 test where first rec is lower level than secon
        expected.clear();
        test1 = new SkipList<String, Rectangle>();
        test1.insert(listRecs[0]);
        test1.insert(listRecs[1]);
        test1.insert(listRecs[1]);
        test1.dump();
        expected.add(listRecs[1]);
        expected.add(listRecs[1]);
        actual = test1.search("r2");
        assertEquals(expected, actual);

        // test 4 test where rec is at end of list
        expected.clear();
        test1.insert(listRecs[2]);
        expected.add(listRecs[2]);
        actual = test1.search("r3");
        assertEquals(expected, actual);

        // test 5 test where rec is first item
        expected.clear();
        actual = test1.search("r1");
        expected.add(listRecs[0]);
        assertEquals(expected, actual);

        // test 6 item isn't in list
        expected.clear();
        actual = test1.search("x5");
        assertNull(actual);

        // Test 7
        expected.clear();
        test1.insert(listRecs[6]);
        test1.insert(listRecs[7]);
        test1.insert(listRecs[7]);
        test1.insert(listRecs[5]);
        actual = test1.search("a6");
        expected.add(listRecs[5]);
        assertEquals(expected, actual);
        test1.dump();

        // Test 8
        expected.clear();
        actual = test1.search("a7");
        expected.add(listRecs[6]);
        assertEquals(expected, actual);

        // Test 9
        expected.clear();
        actual = test1.search("a8");
        expected.add(listRecs[7]);
        expected.add(listRecs[7]);
        assertEquals(expected, actual);

    }


    /**
     * Test remove by name
     */
    public void testRemove() {
        // levels for random seed order
        // 4,0,5,1,4,0,1,4,0,0

        // Test 1 empty list
        systemOut().clearHistory();
        assertNull(test1.remove("r1"));
        test1.dump();
        assertFuzzyEquals("SkipList dump:" + "\nNode with depth 1, value null"
            + "\nSkipList size is: 0", systemOut().getHistory());

        // Test 2 one item in list
        systemOut().clearHistory();
        test1.insert(listRecs[1]); // Size 1 level 4
        assertEquals(listRecs[1], test1.remove("r2"));
        test1.dump();
        assertFuzzyEquals("SkipList dump:" + "\nNode with depth 4, Value null"
            + "\nSkipList size is: 0", systemOut().getHistory());
        assertEquals(0, test1.size());

        // Test 2 one Item in list and item to be removed not there
        systemOut().clearHistory();
        test1.insert(listRecs[1]); // size 1 , level 0
        assertNull(test1.remove("r1"));

        test1.dump();
        assertFuzzyEquals("SkipList dump:" + "\nNode with depth 4, Value null"
            + "\nNode with depth 0, Value (r2, 1, 1, 5, 10)"
            + "\nSkipList size is: 1", systemOut().getHistory());

        assertEquals(1, test1.size());

        // Test 3 add a couple more and remove last node
        systemOut().clearHistory();
        test1.insert(listRecs[0]); // size 2 level 5
        test1.insert(listRecs[2]); // size 3 level 1
        test1.insert(listRecs[3]); // size 4 level 4
        test1.dump();
        assertFuzzyEquals("SkipList dump:" + "\nNode with depth 5, Value null"
            + "\nNode with depth 5, Value (r1, 0, 0, 0, 0)"
            + "\nNode with depth 0, Value (r2, 1, 1, 5, 10)"
            + "\nNode with depth 1, Value (r3, -1, -5, 15, 20)"
            + "\nNode with depth 4, Value (r4, 20, 20, 10, 10)"
            + "\nSkipList size is: 4", systemOut().getHistory());
        assertEquals(listRecs[3], test1.remove("r4"));
        systemOut().clearHistory();
        test1.dump();
        assertFuzzyEquals("SkipList dump:" + "\nNode with depth 5, Value null"
            + "\nNode with depth 5, Value (r1, 0, 0, 0, 0)"
            + "\nNode with depth 0, Value (r2, 1, 1, 5, 10)"
            + "\nNode with depth 1, Value (r3, -1, -5, 15, 20)"
            + "\nSkipList size is: 3", systemOut().getHistory());
        assertEquals(3, test1.size());

        // Test 4 remove first node
        systemOut().clearHistory();
        test1.insert(listRecs[3]); // size 4 level 0
        assertEquals(listRecs[0], test1.remove("r1"));
        test1.dump();
        assertFuzzyEquals("SkipList dump:" + "\nNode with depth 5, Value null"
            + "\nNode with depth 0, Value (r2, 1, 1, 5, 10)"
            + "\nNode with depth 1, Value (r3, -1, -5, 15, 20)"
            + "\nNode with depth 0, Value (r4, 20, 20, 10, 10)"
            + "\nSkipList size is: 3", systemOut().getHistory());

        assertEquals(3, test1.size());

        // Test 5 remove node that has duplicates that are middle
        systemOut().clearHistory();
        test1.insert(listRecs[2]); // size 4 level 1
        test1.insert(listRecs[2]); // size 5 level 4
        assertEquals(listRecs[2], test1.remove("r3"));
        test1.dump();
        assertFuzzyEquals("SkipList dump:" + "\nNode with depth 5, Value null"
            + "\nNode with depth 0, Value (r2, 1, 1, 5, 10)"
            + "\nNode with depth 1, Value (r3, -1, -5, 15, 20)"
            + "\nNode with depth 1, Value (r3, -1, -5, 15, 20)"
            + "\nNode with depth 0, Value (r4, 20, 20, 10, 10)"
            + "\nSkipList size is: 4", systemOut().getHistory());

    }


    /**
     * Test removeByValue
     */
    public void testRemoveByValue() {
        assertNull(test1.removeByValue(listRecs[0].getValue()));
        test1.insert(listRecs[0]);
        test1.insert(listRecs[1]);
        test1.insert(listRecs[5]);
        test1.insert(listRecs[2]);
        test1.dump();
        assertFuzzyEquals("SkipList dump:" + "\nNode with depth 5, Value null"
            + "\nNode with depth 5, Value (a6, -10, -10, 10, 10)"
            + "\nNode with depth 4, Value (r1, 0, 0, 0, 0)"
            + "\nNode with depth 0, Value (r2, 1, 1, 5, 10)"
            + "\nNode with depth 1, Value (r3, -1, -5, 15, 20)"
            + "\nSkipList size is: 4", systemOut().getHistory());

        systemOut().clearHistory();
        test1.removeByValue(listRecs[5].getValue());
        test1.dump();
        assertFuzzyEquals("SkipList dump:" + "\nNode with depth 5, Value null"
            + "\nNode with depth 4, Value (r1, 0, 0, 0, 0)"
            + "\nNode with depth 0, Value (r2, 1, 1, 5, 10)"
            + "\nNode with depth 1, Value (r3, -1, -5, 15, 20)"
            + "\nSkipList size is: 3", systemOut().getHistory());

        systemOut().clearHistory();
        test1.removeByValue(listRecs[2].getValue());
        test1.dump();
        assertFuzzyEquals("SkipList dump:" + "\nNode with depth 5, Value null"
            + "\nNode with depth 4, Value (r1, 0, 0, 0, 0)"
            + "\nNode with depth 0, Value (r2, 1, 1, 5, 10)"
            + "\nSkipList size is: 2", systemOut().getHistory());

        systemOut().clearHistory();
        Rectangle tempRec = new Rectangle(20, 20, 20, 20);
        assertNull(test1.removeByValue(tempRec));
        test1.dump();
        assertFuzzyEquals("SkipList dump:" + "\nNode with depth 5, Value null"
            + "\nNode with depth 4, Value (r1, 0, 0, 0, 0)"
            + "\nNode with depth 0, Value (r2, 1, 1, 5, 10)"
            + "\nSkipList size is: 2", systemOut().getHistory());
    }
}
