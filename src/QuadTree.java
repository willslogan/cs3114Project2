/**
 * QuadTree Class
 * 
 * @author Will Logan
 * @version 1.0
 */
public class QuadTree {
    private QuadNode root;
    private final Rectangle defaultRegion = new Rectangle(0, 0, 1024);

    /**
     * Constructor of DB
     */
    public QuadTree() {
        this.root = EmptyNode.getInstance();
    }


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
    public void insert(Point point) {
        this.root = this.root.insert(point, defaultRegion);
    }


    /**
     * Removes item from quadtree
     * 
     * @param x
     *            x coordinate
     * @param y
     *            y coordinate
     * @param quadrant
     * @return Point Point that has been removed
     */
    public Point remove(int x, int y) {
        Point removed = root.remove(x, y, defaultRegion);
        this.root = root.merge();
        return removed;
    }


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
    public Point removeCheckKey(int x, int y, String key) {
        Point removed = root.removeCheckKey(x, y, defaultRegion, key);
        this.root = root.merge();
        return removed;
    }


    /**
     * Returns contents of the InternalNode
     * 
     * @param quadrant
     *            Quadrant currently in. Used in leaf for printing
     * @param indents
     *            Number of indents for proper formatting
     * @return int number of QuadNodes printed
     */
    public int dump() {
        return root.dump(defaultRegion, 0);
    }


    /**
     * Prints out all duplicates within node
     */
    public void duplicates() {
        root.duplicates();
    }


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
    public int regionsearch(int x, int y, int w, int h) {
        return root.regionsearch(x, y, w, h);
    }


    /**
     * Getter for the root of the tree
     * 
     * @return The root
     */
    public QuadNode getRoot() {
        return root;
    }
}
