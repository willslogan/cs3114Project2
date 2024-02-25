import student.TestCase;
public class PointTest extends TestCase{
    private Point point1;
    private Point point2;
    private Point point3;
    private Point point4;
    
    public void setUp() {
        point1 = new Point(1,0, "p1");
        point2 = new Point(2,0, "p2");
        point3 = new Point(3,3, "p3");
        point4 = new Point(4,4, "p4");
    }
    
    public void testGetX() {
        assertEquals(1, point1.getX());
        assertEquals(2, point2.getX());
    }
    
    public void testGetY() {
        assertEquals(3, point3.getY());
        assertEquals(4, point4.getY());
    }
    
    public void testGetName() {
        assertEquals("p1", point1.getName());
        assertEquals("p2", point2.getName());
    }
    
    public void testEquals() {
        assertFalse(point1.equals(point2));
    }
    
}
