import java.util.ArrayList;

/**
 * LeafNode class
 * 
 * @author Will Logan and Jacob Fast
 * @version 1.0
 */
public class LeafNode implements QuadNode {
    private ArrayList<Point> points;
    private Point[] unique;

    /**
     * Constructor for LeafNode
     */
    public LeafNode() {
        this.points = new ArrayList<Point>();
        unique = new Point[3];
    }


    /**
     * Getter for points in the node
     * 
     * @return The list of points within the node
     */
    public ArrayList<Point> getPoints() {
        return points;
    }


    /**
     * Getter for all the unique points contained within each leaf
     * 
     * @return ArrayList A list of all unique points contained within each child
     *         node
     */

    @Override
    public ArrayList<Point> uniquePoints() {
        ArrayList<Point> uniquePoints = new ArrayList<Point>();
        for (int i = 0; i < unique.length; i++) {
            if (unique[i] == null) {
                break;
            }
            uniquePoints.add(unique[i]);
        }

        return uniquePoints;
    }


    private void addPoint(Point point) {
        // Unique point 1 scenario
        if (unique[0] == null) {
            unique[0] = point;
            points.add(point);
        }
        else if (unique[0].equals(point)) {
            points.add(point);
        }

        // Unique point 2 scenario
        else if (unique[1] == null) {
            unique[1] = point;
            points.add(point);
        }
        else if (unique[1].equals(point)) {
            points.add(point);
        }

        // Unique point 3 scenario
        else if (unique[2] == null) {
            unique[2] = point;
            points.add(point);
        }
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
    @Override
    public QuadNode insert(Point point, Rectangle quadrant) {
        boolean shouldSplit = shouldSplit(point);
        if (shouldSplit) {
            // Split
            InternalNode splitNode = new InternalNode(quadrant);

            for (int i = 0; i < points.size(); i++) {
                // Insert Previous nodes
                splitNode.insert(points.get(i), splitNode.getRegion());
            }
            splitNode.insert(point, splitNode.getRegion());
            return splitNode;
        }
        else {
            // Add
            addPoint(point);
            return this;
        }

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
    @Override
    public Point remove(int x, int y, Rectangle quadrant) {
        Point removed = null;
        for (int i = 0; i < points.size(); i++) {

            if (points.get(i).getX() == x && points.get(i).getY() == y) {
                removed = points.remove(i);
                updateUnique();
                break;
            }
        }

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
    @Override
    public int dump(Rectangle quadrant, int indents) {

        String indentsString = "";
        for (int i = 0; i < indents; i++) {
            indentsString += "  ";
        }

        System.out.println(indentsString + "Node at " + quadrant + ":");
        for (int i = 0; i < points.size(); i++) {
            System.out.println(indentsString + points.get(i));
        }
        return 1;
    }


    /**
     * Prints out all duplicates within node
     */
    @Override
    public void duplicates() {
        int dupCount1 = 0;
        int dupCount2 = 0;

        for (int i = 0; i < points.size(); i++) {
            if (unique[0] != null) {
                if (unique[0].equals(points.get(i))) {
                    dupCount1++;
                }
            }

            if (unique[1] != null) {
                if (unique[1].equals(points.get(i))) {
                    dupCount2++;
                }
            }

        }

        if (dupCount1 > 1) {
            System.out.println("(" + unique[0].getX() + ", " + unique[0].getY()
                + ")");
        }

        if (dupCount2 > 1) {
            System.out.println("(" + unique[1].getX() + ", " + unique[1].getY()
                + ")");
        }

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
    @Override
    public int regionsearch(int x, int y, int w, int h) {
        for (int i = 0; i < points.size(); i++) {
            if (withinRegion(points.get(i), x, y, w, h)) {
                System.out.println("Point found: " + points.get(i));
            }
        }
        return 1;

    }


    private boolean withinRegion(Point point, int x, int y, int w, int h) {
        if (point.getX() >= x && point.getY() >= y) {
            int x2 = x + w;
            int y2 = y + h;
            return point.getX() <= x2 && point.getY() <= y2;
        }
        return false;
    }


    /**
     * Getter for all the points contained within each child node
     * 
     * @return ArrayList All the points contained within each child node
     */
    @Override
    public ArrayList<Point> pointsContained() {
        return points;
    }


    private void updateUnique() {
        Point[] uniqueUpdate = new Point[3];
        int index = 0;
        for (int i = 0; i < points.size(); i++) {
            if (contains(uniqueUpdate, points.get(index))) {
                unique[i] = points.get(index);
                index++;
            }
        }
        unique = uniqueUpdate;
    }


    private boolean contains(Point[] uniqueUpdate, Point p) {
        for (int i = 0; i < uniqueUpdate.length; i++) {
            if (p.equals(uniqueUpdate[i])) {
                return true;
            }
        }
        return false;
    }


    /**
     * Method that merges nodes that should be merged together
     * 
     * @return QuadNode Used to maintain structure of the tree
     */
    @Override
    public QuadNode merge() {
        if (points.size() == 0) {
            return EmptyNode.getInstance();
        }
        return this;
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
    @Override
    public Point removeCheckKey(int x, int y, Rectangle quadrant, String name) {
        Point removed = null;
        for (int i = 0; i < points.size(); i++) {

            if (points.get(i).getX() == x && points.get(i).getY() == y && points
                .get(i).getName().equals(name)) {
                removed = points.remove(i);
                updateUnique();
                break;
            }
        }

        return removed;
    }


    private boolean shouldSplit(Point p) {
        int countUnique = countUnique();
        if (countUnique == 0) {
            return false;
        }
        else if (countUnique == 1 && p.equals(unique[0])) {
            return false;
        }
        else if (countUnique == 1 && points.size() < 3) {
            return false;
        }

        return !(countUnique == 2 && points.size() == 2);
    }


    private int countUnique() {
        int count = 0;
        for (int i = 0; i < unique.length; i++) {
            if (unique[i] != null)
                count++;
            else {
                break;
            }
        }
        return count;
    }

}
