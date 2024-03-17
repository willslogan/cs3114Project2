
/**
 * 
 */
import student.TestCase;

/**
 * @author Jacob Fast
 * @version 1.0
 */
public class PointTest extends TestCase {
    private Point point1;
    private Point point2;
    private Point point3;
    private Point point4;
    private Point point5;
    private Point point6;
    private Point point7;
    private Point negx;
    private Point negy;
    private Point greatx;
    private Point greaty;

    /**
     * Set up variables used in testing
     */
    public void setUp() {
        point1 = new Point(1, 0, "p1");
        point2 = new Point(2, 0, "p2");
        point3 = new Point(3, 3, "p3");
        point4 = new Point(4, 4, "p4");
        point5 = new Point(4, 5, "p5");
        point6 = new Point(5, 4, "p6");
        point7 = new Point(4, 4, "p7");
        // Invalid points
        negx = new Point(-10, 10, "I1");
        negy = new Point(10, -10, "I2");
        greatx = new Point(1030, 500, "I3");
        greaty = new Point(500, 1030, "I4");
    }


    /**
     * Test that the correct x coordinate is returned
     */
    public void testGetX() {
        assertEquals(1, point1.getX());
        assertEquals(2, point2.getX());
    }


    /**
     * Test that the correct y coordinate is returned
     */
    public void testGetY() {
        assertEquals(3, point3.getY());
        assertEquals(4, point4.getY());
    }


    /**
     * Test that the correct name is returned
     */
    public void testGetName() {
        assertEquals("p1", point1.getName());
        assertEquals("p2", point2.getName());
    }


    /**
     * Test all possiblities for equals method
     */
    @SuppressWarnings("unlikely-arg-type")
    public void testEquals() {
        assertTrue(point1.equals(point1));
        assertFalse(point1.equals(null));
        assertFalse(point1.equals("NotAPoint"));

        assertFalse(point4.equals(point5));
        assertFalse(point4.equals(point6));
        assertTrue(point4.equals(point7));
    }


    /**
     * Test that the invalid method correctly identifies points
     */
    public void testIsInvalid() {
        // Valid points
        assertTrue(point1.isValid());
        assertTrue(point2.isValid());
        // Invalid points
        assertFalse(negx.isValid());
        assertFalse(negy.isValid());
        assertFalse(greatx.isValid());
        assertFalse(greaty.isValid());
    }

}
