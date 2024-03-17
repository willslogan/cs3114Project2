import java.util.ArrayList;

/**
 * EmptyNode class
 * 
 * @author Will Logan and Jacob Fast
 * @version 1.0
 */
public class EmptyNode implements QuadNode {
    private static EmptyNode flyweight = null;

    private EmptyNode() {

    }


    /**
     * Method which ensures the same instance of EmptyNode is being pointed to
     * 
     * @return The only instance of EmptyNode
     */
    public static EmptyNode getInstance() {
        if (flyweight == null) {
            flyweight = new EmptyNode();
        }
        return flyweight;
    }


    /**
     * Inserts point into quadtree
     * 
     * @param point
     *            Point to be inserted
     * @param quadrant
     *            Quadrant currently in
     * @return QuadNode To maintain quadtree structure
     */
    @Override
    public QuadNode insert(Point point, Rectangle quadrant) {
        LeafNode node = new LeafNode();
        node.insert(point, quadrant);
        return node;
    }


    /**
     * Removes item from quadtree. Not needed for EmptyNode
     * 
     * @param x
     *            coordinate
     * @param y
     *            coordinate
     * @param quadrant
     * @return Point Point that has been removed
     */
    @Override
    public Point remove(int x, int y, Rectangle quadrant) {
        return null;
    }


    /**
     * Returns contents of the EmptyNode
     * 
     * @param quadrant
     *            Quadrant currently in. Used in leaf for printing
     * @param indents
     *            Number of indents for proper formatting
     * @return int number of QuadNodes printed
     */
    @Override
    public int dump(Rectangle region, int indents) {
        // TODO Auto-generated method stub
        String indentsString = "";
        for (int i = 0; i < indents; i++) {
            indentsString += "\t";
        }
        System.out.println(indentsString + "Node at " + region + ": Empty");
        return 1;

    }


    /**
     * Prints out all duplicates within each child node. Does virtually nothing.
     * Required for recursion purposes.
     */
    @Override
    public void duplicates() {

    }


    /**
     * Searches for points with in specified region. Required for recursion
     * purposes but does functionally nothing except return the number of nodes
     * visited
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
    @Override
    public int regionsearch(int x, int y, int w, int h) {
        return 1;

    }


    /**
     * Getter for all the unique points contained within each leaf. EmptyNode
     * doesn't make use of this
     * 
     * @return ArrayList A list of all unique points contained within each child
     *         node
     */
    @Override
    public ArrayList<Point> uniquePoints() {
        return null;
    }


    @Override
    public ArrayList<Point> pointsContained() {
        return null;
    }


    /**
     * Method that merges nodes that should be merged together
     * 
     * @return QuadNode Used to maintain structure of the tree
     */
    @Override
    public QuadNode merge() {
        return this;
    }


    /**
     * Remove method which makes sure that item being removed is consistant with
     * item being removed from skiplist by checking the key. Is not used in
     * EmptyNode
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
    @Override
    public Point removeCheckKey(int x, int y, Rectangle region, String name) {
        // TODO Auto-generated method stub
        return null;
    }

}
