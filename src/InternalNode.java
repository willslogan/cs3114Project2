
import java.util.ArrayList;

/**
 * Internal Node class
 * 
 * @author Will Logan and Jacob Fast
 * @version 1.0
 */
public class InternalNode implements QuadNode {
    private QuadNode ne;
    private QuadNode nw;
    private QuadNode se;
    private QuadNode sw;
    private Rectangle region;

    /**
     * Constructor
     * 
     * @param region
     *            region to be stored
     */
    public InternalNode(Rectangle region) {
        this.nw = EmptyNode.getInstance();
        this.ne = EmptyNode.getInstance();
        this.se = EmptyNode.getInstance();
        this.sw = EmptyNode.getInstance();
        this.region = region;
    }


    /**
     * Getter for region
     * 
     * @return region
     */
    public Rectangle getRegion() {
        return region;
    }


    /**
     * Getter for ne node
     * 
     * @return ne
     */
    public QuadNode getNe() {
        return ne;
    }


    /**
     * Getter for nw node
     * 
     * @return nw
     */
    public QuadNode getNw() {
        return nw;
    }


    /**
     * Getter for se node
     * 
     * @return se
     */
    public QuadNode getSe() {
        return se;
    }


    /**
     * Getter for sw node
     * 
     * @return sw
     */
    public QuadNode getSw() {
        return sw;
    }


    /**
     * Setter for ne node
     * 
     * @param ne
     *            node to set to
     */
    public void setNe(QuadNode ne) {
        this.ne = ne;
    }


    /**
     * Setter for nw node
     * 
     * @param nw
     *            node to set to
     */
    public void setNw(QuadNode nw) {
        this.nw = nw;
    }


    /**
     * Setter for se node
     * 
     * @param se
     *            node to set to
     */
    public void setSe(QuadNode se) {
        this.se = se;
    }


    /**
     * Setter for sw node
     * 
     * @param sw
     *            node to set to
     */
    public void setSw(QuadNode sw) {
        this.sw = sw;
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
        int regionXMidpoint = (quadrant.getxCoordinate() + quadrant.getSize()
            / 2);
        int regionYMidpoint = (quadrant.getyCoordinate() + quadrant.getSize()
            / 2);
        Rectangle updateRegion;

        // Left side
        if (point.getX() < regionXMidpoint) {
            // Top left
            if (point.getY() < regionYMidpoint) {
                updateRegion = new Rectangle(quadrant.getxCoordinate(), quadrant
                    .getyCoordinate(), quadrant.getSize() / 2);
                setNw(nw.insert(point, updateRegion));
            }
            // Bottom left
            else {
                updateRegion = new Rectangle(quadrant.getxCoordinate(),
                    regionYMidpoint, quadrant.getSize() / 2);
                setSw(sw.insert(point, updateRegion));
            }
        }
        // Right Side
        else {
            // Top Right
            if (point.getY() < regionYMidpoint) {
                updateRegion = new Rectangle(regionXMidpoint, quadrant
                    .getyCoordinate(), quadrant.getSize() / 2);
                setNe(ne.insert(point, updateRegion));
            }
            // Bottom Right
            else {
                updateRegion = new Rectangle(regionXMidpoint, regionYMidpoint,
                    quadrant.getSize() / 2);
                setSe(se.insert(point, updateRegion));
            }
        }
        return this;
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
        int regionXMidpoint = (quadrant.getxCoordinate() + quadrant.getSize()
            / 2);
        int regionYMidpoint = (quadrant.getyCoordinate() + quadrant.getSize()
            / 2);
        Rectangle updateRegion;
        Point removed;
        // Left side
        if (x < regionXMidpoint) {
            // Top left
            if (y < regionYMidpoint) {
                updateRegion = new Rectangle(quadrant.getxCoordinate(), quadrant
                    .getyCoordinate(), quadrant.getSize() / 2);
                removed = nw.remove(x, y, updateRegion);
            }
            // Bottom left
            else {
                updateRegion = new Rectangle(quadrant.getxCoordinate(),
                    regionYMidpoint, quadrant.getSize() / 2);
                removed = sw.remove(x, y, updateRegion);
            }
        }
        // Right Side
        else {
            // Top Right
            if (y < regionYMidpoint) {
                updateRegion = new Rectangle(regionXMidpoint, quadrant
                    .getyCoordinate(), quadrant.getSize() / 2);
                removed = ne.remove(x, y, updateRegion);
            }
            // Bottom Right
            else {
                updateRegion = new Rectangle(regionXMidpoint, regionYMidpoint,
                    quadrant.getSize() / 2);
                removed = se.remove(x, y, updateRegion);
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

        System.out.println(indentsString + "Node at " + quadrant
            + ": Internal");
        Rectangle updateRegion;
        int regionXMidpoint = (quadrant.getxCoordinate() + quadrant.getSize()
            / 2);
        int regionYMidpoint = (quadrant.getyCoordinate() + quadrant.getSize()
            / 2);

        int nodesCountNW = 0;
        int nodesCountNE = 0;
        int nodesCountSW = 0;
        int nodesCountSE = 0;

        updateRegion = new Rectangle(quadrant.getxCoordinate(), quadrant
            .getyCoordinate(), quadrant.getSize() / 2);
        nodesCountNW = getNw().dump(updateRegion, indents + 1);

        updateRegion = new Rectangle(regionXMidpoint, quadrant.getyCoordinate(),
            quadrant.getSize() / 2);
        nodesCountNE = getNe().dump(updateRegion, indents + 1);

        updateRegion = new Rectangle(quadrant.getxCoordinate(), regionYMidpoint,
            quadrant.getSize() / 2);
        nodesCountSW = getSw().dump(updateRegion, indents + 1);

        updateRegion = new Rectangle(regionXMidpoint, regionYMidpoint, quadrant
            .getSize() / 2);
        nodesCountSE = getSe().dump(updateRegion, indents + 1);

        return nodesCountNW + nodesCountNE + nodesCountSW + nodesCountSE + 1;
    }


    /**
     * Prints out all duplicates within each child node
     */
    @Override
    public void duplicates() {
        nw.duplicates();
        ne.duplicates();
        sw.duplicates();
        se.duplicates();
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
        int count = 1;
        int regionXMidpoint = (region.getxCoordinate() + region.getSize() / 2);
        int regionYMidpoint = (region.getyCoordinate() + region.getSize() / 2);

        Rectangle leftTopRegion = new Rectangle(region.getxCoordinate(), region
            .getyCoordinate(), region.getSize() / 2);
        Rectangle leftBotRegion = new Rectangle(region.getxCoordinate(),
            regionYMidpoint, region.getSize() / 2);
        Rectangle rightTopRegion = new Rectangle(regionXMidpoint, region
            .getyCoordinate(), region.getSize() / 2);
        Rectangle rightBotRegion = new Rectangle(regionXMidpoint,
            regionYMidpoint, region.getSize() / 2);

        if (intersect(leftTopRegion, x, y, w, h)) {
            count += nw.regionsearch(x, y, w, h);

        }

        if (intersect(rightTopRegion, x, y, w, h)) {
            count += ne.regionsearch(x, y, w, h);
        }

        if (intersect(leftBotRegion, x, y, w, h)) {
            count += sw.regionsearch(x, y, w, h);
        }

        if (intersect(rightBotRegion, x, y, w, h)) {
            count += se.regionsearch(x, y, w, h);
        }

        return count;

    }


    private boolean intersect(Rectangle quadrant, int x, int y, int w, int h) {
        // Check if the any points of the rectangle intersect
        if (!(quadrant.getxCoordinate() < (x + w))) {
            return false;
        }
        else if (!((quadrant.getxCoordinate() + quadrant.getSize()) > x)) {
            return false;
        }
        else if (!(quadrant.getyCoordinate() < (y + h))) {
            return false;
        }
        else if (!((quadrant.getyCoordinate() + quadrant.getSize()) > y)) {
            return false;
        }
        else {
            return true;
        }
    }


    /**
     * Getter for all the unique points contained within each leaf
     * 
     * @return ArrayList A list of all unique points contained within each child
     *         node
     */
    @Override
    public ArrayList<Point> uniquePoints() {
        ArrayList<Point> combinedPoints = new ArrayList<Point>();

        ArrayList<Point> nwPoints = nw.uniquePoints();
        ArrayList<Point> nePoints = ne.uniquePoints();
        ArrayList<Point> swPoints = sw.uniquePoints();
        ArrayList<Point> sePoints = se.uniquePoints();

        if (nwPoints != null) {
            combinedPoints.addAll(nwPoints);
        }
        if (nePoints != null) {
            combinedPoints.addAll(nePoints);
        }
        if (swPoints != null) {
            combinedPoints.addAll(swPoints);
        }
        if (sePoints != null) {
            combinedPoints.addAll(sePoints);
        }

        return combinedPoints;
    }


    /**
     * Method that merges nodes that should be merged together
     * 
     * @return QuadNode Used to maintain structure of the tree
     */
    public QuadNode merge() {
        setNw(nw.merge());
        setNe(ne.merge());
        setSw(sw.merge());
        setSe(se.merge());

        ArrayList<Point> allPoints = pointsContained();

        if (shouldMerge()) {
            // merge
            LeafNode merged = new LeafNode();
            for (int i = 0; i < allPoints.size(); i++) {
                merged.insert(allPoints.get(i), region);
            }
            return merged;
        }
        else {
            // dont merge
            return this;
        }
    }


    /**
     * Getter for all the points contained within each child node
     * 
     * @return ArrayList All the points contained within each child node
     */
    @Override
    public ArrayList<Point> pointsContained() {
        ArrayList<Point> combinedPoints = new ArrayList<Point>();
        ArrayList<Point> nwPoints = nw.pointsContained();
        ArrayList<Point> nePoints = ne.pointsContained();
        ArrayList<Point> swPoints = sw.pointsContained();
        ArrayList<Point> sePoints = se.pointsContained();

        if (nwPoints != null) {
            combinedPoints.addAll(nwPoints);
        }
        if (nePoints != null) {
            combinedPoints.addAll(nePoints);
        }
        if (swPoints != null) {
            combinedPoints.addAll(swPoints);
        }
        if (sePoints != null) {
            combinedPoints.addAll(sePoints);
        }

        return combinedPoints;
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
        int regionXMidpoint = (quadrant.getxCoordinate() + quadrant.getSize()
            / 2);
        int regionYMidpoint = (quadrant.getyCoordinate() + quadrant.getSize()
            / 2);
        Rectangle updateRegion;
        Point removed;
        // Left side
        if (x < regionXMidpoint) {
            // Top left
            if (y < regionYMidpoint) {
                updateRegion = new Rectangle(quadrant.getxCoordinate(), quadrant
                    .getyCoordinate(), quadrant.getSize() / 2);
                removed = nw.remove(x, y, updateRegion);
            }
            // Bottom left
            else {
                updateRegion = new Rectangle(quadrant.getxCoordinate(),
                    regionYMidpoint, quadrant.getSize() / 2);
                removed = sw.remove(x, y, updateRegion);
            }
        }
        // Right Side
        else {
            // Top Right
            if (y < regionYMidpoint) {
                updateRegion = new Rectangle(regionXMidpoint, quadrant
                    .getyCoordinate(), quadrant.getSize() / 2);
                removed = ne.remove(x, y, updateRegion);
            }
            // Bottom Right
            else {
                updateRegion = new Rectangle(regionXMidpoint, regionYMidpoint,
                    quadrant.getSize() / 2);
                removed = se.remove(x, y, updateRegion);
            }
        }

        return removed;
    }


    private boolean shouldMerge() {
        ArrayList<Point> unique = uniquePoints();
        ArrayList<Point> allPoints = pointsContained();

        if (unique.size() == 1) {
            return true;
        }

        else if (unique.size() == 2 && allPoints.size() < 4) {
            return true;
        }

        return unique.size() == 3 && allPoints.size() == 3;

    }

}
