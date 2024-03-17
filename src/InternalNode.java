
import java.util.ArrayList;

/**
 * Internal Node class
 * 
 * @author Will Logan and Jacob Fast
 * @version 1.0
 */
public class InternalNode implements QuadNode {
    private QuadNode NE;
    private QuadNode NW;
    private QuadNode SE;
    private QuadNode SW;
    private Rectangle region;

    /**
     * Constructor
     * 
     * @param region
     *            region to be stored
     */
    public InternalNode(Rectangle region) {
        this.NW = EmptyNode.getInstance();
        this.NE = EmptyNode.getInstance();
        this.SE = EmptyNode.getInstance();
        this.SW = EmptyNode.getInstance();
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
     * Getter for NE node
     * 
     * @return NE
     */
    public QuadNode getNE() {
        return NE;
    }


    /**
     * Getter for NW node
     * 
     * @return NW
     */
    public QuadNode getNW() {
        return NW;
    }


    /**
     * Getter for SE node
     * 
     * @return SE
     */
    public QuadNode getSE() {
        return SE;
    }


    /**
     * Getter for SW node
     * 
     * @return SW
     */
    public QuadNode getSW() {
        return SW;
    }


    /**
     * Setter for NE node
     * 
     * @param NE
     *            node to set to
     */
    public void setNE(QuadNode NE) {
        this.NE = NE;
    }


    /**
     * Setter for NW node
     * 
     * @param NW
     *            node to set to
     */
    public void setNW(QuadNode NW) {
        this.NW = NW;
    }


    /**
     * Setter for SE node
     * 
     * @param SE
     *            node to set to
     */
    public void setSE(QuadNode SE) {
        this.SE = SE;
    }


    /**
     * Setter for SW node
     * 
     * @param SW
     *            node to set to
     */
    public void setSW(QuadNode SW) {
        this.SW = SW;
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
                setNW(NW.insert(point, updateRegion));
            }
            // Bottom left
            else {
                updateRegion = new Rectangle(quadrant.getxCoordinate(),
                    regionYMidpoint, quadrant.getSize() / 2);
                setSW(SW.insert(point, updateRegion));
            }
        }
        // Right Side
        else {
            // Top Right
            if (point.getY() < regionYMidpoint) {
                updateRegion = new Rectangle(regionXMidpoint, quadrant
                    .getyCoordinate(), quadrant.getSize() / 2);
                setNE(NE.insert(point, updateRegion));
            }
            // Bottom Right
            else {
                updateRegion = new Rectangle(regionXMidpoint, regionYMidpoint,
                    quadrant.getSize() / 2);
                setSE(SE.insert(point, updateRegion));
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
        // TODO Auto-generated method stub
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
                removed = NW.remove(x, y, updateRegion);
            }
            // Bottom left
            else {
                updateRegion = new Rectangle(quadrant.getxCoordinate(),
                    regionYMidpoint, quadrant.getSize() / 2);
                removed = SW.remove(x, y, updateRegion);
            }
        }
        // Right Side
        else {
            // Top Right
            if (y < regionYMidpoint) {
                updateRegion = new Rectangle(regionXMidpoint, quadrant
                    .getyCoordinate(), quadrant.getSize() / 2);
                removed = NE.remove(x, y, updateRegion);
            }
            // Bottom Right
            else {
                updateRegion = new Rectangle(regionXMidpoint, regionYMidpoint,
                    quadrant.getSize() / 2);
                removed = SE.remove(x, y, updateRegion);
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
        nodesCountNW = getNW().dump(updateRegion, indents + 1);

        updateRegion = new Rectangle(regionXMidpoint, quadrant.getyCoordinate(),
            quadrant.getSize() / 2);
        nodesCountNE = getNE().dump(updateRegion, indents + 1);

        updateRegion = new Rectangle(quadrant.getxCoordinate(), regionYMidpoint,
            quadrant.getSize() / 2);
        nodesCountSW = getSW().dump(updateRegion, indents + 1);

        updateRegion = new Rectangle(regionXMidpoint, regionYMidpoint, quadrant
            .getSize() / 2);
        nodesCountSE = getSE().dump(updateRegion, indents + 1);

        return nodesCountNW + nodesCountNE + nodesCountSW + nodesCountSE + 1;
    }


    /**
     * Prints out all duplicates within each child node
     */
    @Override
    public void duplicates() {
        // TODO Auto-generated method stub
        NW.duplicates();
        NE.duplicates();
        SW.duplicates();
        SE.duplicates();
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
        // TODO Auto-generated method stub
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
            count += NW.regionsearch(x, y, w, h);

        }

        if (intersect(rightTopRegion, x, y, w, h)) {
            count += NE.regionsearch(x, y, w, h);
        }

        if (intersect(leftBotRegion, x, y, w, h)) {
            count += SW.regionsearch(x, y, w, h);
        }

        if (intersect(rightBotRegion, x, y, w, h)) {
            count += SE.regionsearch(x, y, w, h);
        }

        return count;

    }


    private boolean intersect(Rectangle region, int x, int y, int w, int h) {
        // Check if the any points of the rectangle intersect
        if (!(region.getxCoordinate() < (x + w))) {
            return false;
        }
        else if (!((region.getxCoordinate() + region.getSize()) > x)) {
            return false;
        }
        else if (!(region.getyCoordinate() < (y + h))) {
            return false;
        }
        else if (!((region.getyCoordinate() + region.getSize()) > y)) {
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

        ArrayList<Point> nwPoints = NW.uniquePoints();
        ArrayList<Point> nePoints = NE.uniquePoints();
        ArrayList<Point> swPoints = SW.uniquePoints();
        ArrayList<Point> sePoints = SE.uniquePoints();

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
        setNW(NW.merge());
        setNE(NE.merge());
        setSW(SW.merge());
        setSE(SE.merge());

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
        ArrayList<Point> nwPoints = NW.pointsContained();
        ArrayList<Point> nePoints = NE.pointsContained();
        ArrayList<Point> swPoints = SW.pointsContained();
        ArrayList<Point> sePoints = SE.pointsContained();

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
        // TODO Auto-generated method stub
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
                removed = NW.remove(x, y, updateRegion);
            }
            // Bottom left
            else {
                updateRegion = new Rectangle(quadrant.getxCoordinate(),
                    regionYMidpoint, quadrant.getSize() / 2);
                removed = SW.remove(x, y, updateRegion);
            }
        }
        // Right Side
        else {
            // Top Right
            if (y < regionYMidpoint) {
                updateRegion = new Rectangle(regionXMidpoint, quadrant
                    .getyCoordinate(), quadrant.getSize() / 2);
                removed = NE.remove(x, y, updateRegion);
            }
            // Bottom Right
            else {
                updateRegion = new Rectangle(regionXMidpoint, regionYMidpoint,
                    quadrant.getSize() / 2);
                removed = SE.remove(x, y, updateRegion);
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

        if (unique.size() == 2 && allPoints.size() < 4) {
            return true;
        }

        if (unique.size() == 3 && allPoints.size() == 3) {
            return true;
        }

        return false;

    }

}
