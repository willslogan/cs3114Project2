import student.TestCase;

public class QuadTreeTest extends TestCase {
    private Point point1;
    private Point point2;
    private Point point3;
    private Point point4;
    private Point point5;
    private Point point6;
    private Point point7;
    private Point point8;
    private Point point9;
    private Point point10;
    private QuadTree database;
    
    public void setUp() {
        point1 = new Point(500, 250, "point1");
        point2 = new Point(750, 580, "point2");
        point3 = new Point(120, 896, "point3");
        point4 = new Point(320, 64, "point4");
        point5 = new Point(0, 0, "point5");
        point6 = new Point(1, 1, "point6");
        point7 = new Point(780, 120, "point7");
        point8 = new Point(800, 200, "point8");
        point9 = new Point(600, 120, "point9");
        point10 = new Point(600, 300, "point10");
        database = new QuadTree();
    }
    
    public void testInsert() {
        //Test 0
        systemOut().clearHistory();
        assertEquals(1, database.dump());
        assertFuzzyEquals("Node at 0, 0, 1024: Empty", systemOut().getHistory());
        
        
        //Test 1
        database.insert(point1);
        systemOut().clearHistory();
        assertEquals(1, database.dump());
        assertFuzzyEquals("Node at 0, 0, 1024:\n(point1, 500, 250", systemOut().getHistory());
        
        //Test 2
        database.insert(point2);
        database.insert(point3);
        database.insert(point4);
        systemOut().clearHistory();
        assertEquals(5, database.dump());
        assertFuzzyEquals("Node at 0, 0, 1024: Internal"
            + "\nNode at 0, 0, 512:"
            + "\n(point1, 500, 250)"
            + "\n(point4, 320, 64)"
            + "\nNode at 512, 0, 512: Empty"
            + "\nNode at 0, 512, 512:"
            + "\n(point3, 120, 896)"
            + "\nNode at 512, 512, 512:"
            + "\n(point2, 750, 580)", systemOut().getHistory());
        
        //Test 3 several copies of same node and splits
        database.insert(point1);
        database.insert(point1);
        database.insert(point1);
        database.insert(point1);
        systemOut().clearHistory();
        assertEquals(13, database.dump());
        assertFuzzyEquals("Node at 0, 0, 1024: Internal"
            + "\nNode at 0, 0, 512: Internal"
            + "\nNode at 0 0 256 empty"
            + "\nNode at 256 0 256 internal"
            + "\nNode at 256 0 128"
            + "\npoint4 320 64"
            + "\nNode at 384 0 128 empty"
            + "\nNode at 256 128 128 empty"
            + "\nNode at 384 128 128"
            + "\npoint1 500 250"
            + "\npoint1 500 250"
            + "\npoint1 500 250"
            + "\npoint1 500 250"
            + "\npoint1 500 250"
            + "\nNode at 0 256 256 empty"
            + "\nNode at 256 256 256 empty"
            + "\nNode at 512, 0, 512: Empty"
            + "\nNode at 0, 512, 512:"
            + "\n(point3, 120, 896)"
            + "\nNode at 512, 512, 512:"
            + "\n(point2, 750, 580)", systemOut().getHistory());
        
        //Test 4 
        database.insert(point5);
        database.insert(point6);
        systemOut().clearHistory();
        assertEquals(13, database.dump());
        assertFuzzyEquals("Node at 0, 0, 1024: Internal"
            + "\nNode at 0, 0, 512: Internal"
            + "\nNode at 0, 0, 256:"
            + "\n(point5, 0, 0)"
            + "\n(point6, 1, 1)"
            + "\nNode at 256, 0, 256: Internal"
            + "\nNode at 256 0 128"
            + "\npoint4 320 64"
            + "\nNode at 384 0 128 empty"
            + "\nNode at 256 128 128 empty"
            + "\nNode at 384 128 128"
            + "\n(point1, 500, 250)"
            + "\n(point1, 500, 250)"
            + "\n(point1, 500, 250)"
            + "\n(point1, 500, 250)"
            + "\n(point1, 500, 250)"
            + "\nNode at 0, 256, 256: Empty"
            + "\nNode at 256, 256, 256: Empty"
            + "\nNode at 512, 0, 512: Empty"
            + "\nNode at 0, 512, 512:"
            + "\n(point3, 120, 896)"
            + "\nNode at 512, 512, 512:"
            + "\n(point2, 750, 580)", systemOut().getHistory());
        
        //Test 5 split on other end of canvas
        database.insert(point7);
        database.insert(point8);
        database.insert(point9);
        database.insert(point10);
        systemOut().clearHistory();
        database.dump();
        assertFuzzyEquals("Node at 0, 0, 1024: Internal"
            + "\nNode at 0, 0, 512: Internal"
            + "\nNode at 0, 0, 256:"
            + "\n(point5, 0, 0)"
            + "\n(point6, 1, 1)"
            + "\nNode at 256, 0, 256: Internal"
            + "\nNode at 256 0 128"
            + "\npoint4 320 64"
            + "\nNode at 384 0 128 empty"
            + "\nNode at 256 128 128 empty"
            + "\nNode at 384 128 128"
            + "\n(point1, 500, 250)"
            + "\n(point1, 500, 250)"
            + "\n(point1, 500, 250)"
            + "\n(point1, 500, 250)"
            + "\n(point1, 500, 250)"
            + "\nNode at 0, 256, 256: Empty"
            + "\nNode at 256, 256, 256: Empty"
            + "\nNode at 512, 0, 512: Internal"
            +"\nNode at 512 0 256"
            +"\npoint9 600 120"
            +"\nNode at 768 0 256"
            +"\npoint7 780 120"
            +"\npoint8 800 200"
            +"\nNode at 512 256 256"
            +"\npoint10 600 300"
            +"\nNode at 768 256 256 empty"
            + "\nNode at 0, 512, 512:"
            + "\n(point3, 120, 896)"
            + "\nNode at 512, 512, 512:"
            + "\n(point2, 750, 580)", systemOut().getHistory());
        
    }
    
    public void testBigInsert() {
        
    }
    public void testSearch() {
        
        
        //Test 0 Empty node search
        systemOut().clearHistory();
        database.search("Not here");
        assertEquals("", systemOut().getHistory());
        
        //Test 1 test for non existant node in leaf
        database.insert(point1);
        systemOut().clearHistory();
        database.search("Not here");
        assertEquals("", systemOut().getHistory());
        
        //Test 2 with only leafnode
        database.insert(point2);
        database.insert(point3);
        
        systemOut().clearHistory();
        database.search("point1");
        assertFuzzyEquals("Found (point1, 500, 250)", systemOut().getHistory());
        
        systemOut().clearHistory();
        database.search("point2");
        assertFuzzyEquals("Found (point2, 750, 580)", systemOut().getHistory());
        
        systemOut().clearHistory();
        database.search("point3");
        assertFuzzyEquals("Found (point3, 120, 896)", systemOut().getHistory());
        
        //Test 3 with duplicates in singular leafNode
        database.insert(point1);
        database.insert(point1);
        database.insert(point1);
        database.insert(point1);
        systemOut().clearHistory();
        database.search("point1");
        assertFuzzyEquals("Found (point1, 500, 250)"
            + "\nFound (point1, 500, 250)"
            + "\nFound (point1, 500, 250)"
            + "\nFound (point1, 500, 250)"
            + "\nFound (point1, 500, 250)", systemOut().getHistory());
        
        
        //Test 4 with internal nodes
        database.insert(point2);
        database.insert(point3);
        database.insert(point4);
        systemOut().clearHistory();
        database.search("point4");
        assertFuzzyEquals("Found (point4, 320, 64)", systemOut().getHistory());
        
        
        //A lot more internal nodes
        database.insert(point5);
        database.insert(point6);
        database.insert(point7);
        database.insert(point8);
        database.insert(point9);
        database.insert(point10);
        
        systemOut().clearHistory();
        database.search("point4");
        assertFuzzyEquals("Found (point4, 320, 64)", systemOut().getHistory());
        
        systemOut().clearHistory();
        database.search("point5");
        assertFuzzyEquals("Found (point5, 0, 0)", systemOut().getHistory());
        
        systemOut().clearHistory();
        database.search("point6");
        assertFuzzyEquals("Found (point6, 1, 1)", systemOut().getHistory());
        
        systemOut().clearHistory();
        database.search("point7");
        assertFuzzyEquals("Found (point7, 780, 120)", systemOut().getHistory());
        
        
        systemOut().clearHistory();
        database.search("point8");
        assertFuzzyEquals("Found (point8, 800, 200)", systemOut().getHistory());
        
        systemOut().clearHistory();
        database.search("point9");
        assertFuzzyEquals("Found (point9, 600, 120)", systemOut().getHistory());
        
        systemOut().clearHistory();
        database.search("point10");
        assertFuzzyEquals("Found (point10, 600, 300)", systemOut().getHistory());
        

    }
    
    public void testRegionSearch() {
        //Test 0 Empty node search
        systemOut().clearHistory();
        database.regionsearch(0,0,100,100);
        assertEquals("", systemOut().getHistory());
        
        //Test Suite 1 leafnode 1 point
        
        //Not Found case
        database.insert(point1);
        systemOut().clearHistory();
        database.regionsearch(0,0,100,100);
        assertEquals("", systemOut().getHistory());
        
        //Within region case touch border right side
        systemOut().clearHistory();
        database.regionsearch(0,0,500,500);
        assertFuzzyEquals("Point found: (point1, 500, 250)", systemOut().getHistory());
        
        //Within region case touch border bottom right corner
        systemOut().clearHistory();
        database.regionsearch(0,0,500,250);
        assertFuzzyEquals("Point found: (point1, 500, 250)", systemOut().getHistory());
        
        //Within region case touch border top right corner 
        systemOut().clearHistory();
        database.regionsearch(0,250,500,250);
        assertFuzzyEquals("Point found: (point1, 500, 250)", systemOut().getHistory());
        
        //Within region case touch border top 
        systemOut().clearHistory();
        database.regionsearch(300,250,500,250);
        assertFuzzyEquals("Point found: (point1, 500, 250)", systemOut().getHistory());
        
        //Within region case touch border top left corner 
        systemOut().clearHistory();
        database.regionsearch(500,250,500,250);
        assertFuzzyEquals("Point found: (point1, 500, 250)", systemOut().getHistory());
        
        //Within region case touch border left border 
        systemOut().clearHistory();
        database.regionsearch(500,100,500,250);
        assertFuzzyEquals("Point found: (point1, 500, 250)", systemOut().getHistory());
        
        //Within region case touch bottom left corner 
        systemOut().clearHistory();
        database.regionsearch(500,0,500,250);
        assertFuzzyEquals("Point found: (point1, 500, 250)", systemOut().getHistory());
        
        
        // Test suite 2 leafnode multiple points
        
        //Duplicates
        
        database.insert(point1);
        database.insert(point1);
        systemOut().clearHistory();
        database.regionsearch(100,0,500,500);
        assertFuzzyEquals("Point found: (point1, 500, 250)"
            + "\nPoint found: (point1, 500, 250)"
            + "\nPoint found: (point1, 500, 250)", systemOut().getHistory());
        
        //Only 1 point in region
        database.insert(point2);
        database.insert(point3);
        systemOut().clearHistory();
        database.regionsearch(100,0,500,500);
        assertFuzzyEquals("Point found: (point1, 500, 250)"
            + "\nPoint found: (point1, 500, 250)"
            + "\nPoint found: (point1, 500, 250)", systemOut().getHistory());
        
        //2 points within region
        systemOut().clearHistory();
        database.regionsearch(0,0,512,1024);
        assertFuzzyEquals("Point found: (point1, 500, 250)"
            + "\nPoint found: (point1, 500, 250)"
            + "\nPoint found: (point1, 500, 250)"
            + "\nPoint found: (point3, 120, 896)", systemOut().getHistory());
        
        
    }
    
    public void testDuplicates() {
        //Test 0 with nothing in it
        systemOut().clearHistory();
        database.duplicates();
        assertFuzzyEquals("", systemOut().getHistory());
        
        //Test 1 with 1 item no duplicates
        database.insert(point1);
        systemOut().clearHistory();
        database.duplicates();
        assertFuzzyEquals("", systemOut().getHistory());
        
        //Test 2 with actual dups leaf
        database.insert(point1);
        systemOut().clearHistory();
        database.duplicates();
        assertFuzzyEquals("(500, 250)", systemOut().getHistory());
        
        //Test 3 full leaf node case
        database.insert(point2);
        database.insert(point3);
        systemOut().clearHistory();
        database.duplicates();
        assertFuzzyEquals("(500, 250)", systemOut().getHistory());
        
        //Test 4 
        database.insert(point2);
        database.insert(point3);
        database.insert(point2);
        database.insert(point3);
        systemOut().clearHistory();
        database.duplicates();
        assertFuzzyEquals("(500, 250)\n(120, 896)\n(750, 580)", systemOut().getHistory());
        
        //Test 5 
        database.insert(point4);
        systemOut().clearHistory();
        database.duplicates();
        assertFuzzyEquals("(500, 250)\n(120, 896)\n(750, 580)", systemOut().getHistory());
        
        database.insert(point1);
        database.insert(point1);
        database.insert(point1);
        database.insert(point1);
        database.insert(point5);
        database.insert(point6);
        database.insert(point7);
        database.insert(point8);
        database.insert(point9);
        database.insert(point10);
        
    }
    
    public void testRemoveByValue() {
        database.insert(point1);
        database.insert(point2);
        database.insert(point3);
        database.insert(point4);
        
        systemOut().clearHistory();
        Point p = database.remove(320, 64);
        assertEquals(1, database.dump());
        assertFuzzyEquals("Node at 0, 0, 1024:"
            + "\n(point1, 500, 250)"
            + "\n(point3, 120, 896)"
            + "\n(point2, 750, 580)", systemOut().getHistory());
        
    }
}
