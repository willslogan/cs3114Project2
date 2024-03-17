import java.util.ArrayList;

/**
 * QuadNode Interface
 * 
 * @author Will Logan
 * @version 1.0
 */
public interface QuadNode {
    /**
     * Adds item to quadtree
     * 
     * @param point
     *            Point to be added
     * @param quadrant
     *            Quadrant currently in
     * @return QuadNode This is node is used for maintaining structure of
     *         quadtree
     */
    public QuadNode insert(Point point, Rectangle quadrant);


    /**
     * Removes item from quadtree
     * 
     * @param x
     *            x coordinate
     * @param y
     *            y coordinate
     * @param quadrant
     *            quadrant point is being removed
     * @return Point Point that has been removed
     */
    public Point remove(int x, int y, Rectangle quadrant);


    /**
     * Remove method which makes sure that item being removed is consistant with
     * item being removed from skiplist by checking the key
     * 
     * @param x
     *            x coordinate of the point being removed
     * @param y
     *            y coordinate of the point being removed
     * @param quadrant
     *            quadrant currently in
     * @param name
     *            name of point being removed
     * @return Point point being removed
     */
    public Point removeCheckKey(int x, int y, Rectangle quadrant, String name);


    /**
     * Returns contents of the InternalNode
     * 
     * @param quadrant
     *            Quadrant currently in. Used in leaf for printing
     * @param indents
     *            Number of indents for proper formatting
     * @return int number of QuadNodes printed
     */
    public int dump(Rectangle quadrant, int indents);


    /**
     * Prints out all duplicates within node
     */
    public void duplicates();


    /**
     * Searches for points with in specified region
     * 
     * @param x
     *            The x coordinate for where the region starts
     * @param y
     *            The y coordinate for where the region starts
     * @param w
     *            The width of the region
     * @param h
     *            The height of the region
     * @return total number of nodes visited
     */
    public int regionsearch(int x, int y, int w, int h);


    /**
     * Getter for all the unique points contained within each leaf
     * 
     * @return ArrayList A list of all unique points contained within each child
     *         node
     */
    public ArrayList<Point> uniquePoints();


    /**
     * Getter for all the points contained within each child node
     * 
     * @return ArrayList All the points contained within each child node
     */
    public ArrayList<Point> pointsContained();


    /**
     * Method that merges nodes that should be merged together
     * 
     * @return QuadNode Used to maintain structure of the tree
     */
    public QuadNode merge();
}
