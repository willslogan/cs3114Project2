
import java.util.ArrayList;

public class InternalNode implements QuadNode {
    private QuadNode NE;
    private QuadNode NW;
    private QuadNode SE;
    private QuadNode SW;
    private Rectangle region;

    public InternalNode(Rectangle region) {
        this.NW = EmptyNode.getInstance();
        this.NE = EmptyNode.getInstance();
        this.SE = EmptyNode.getInstance();
        this.SW = EmptyNode.getInstance();
        this.region = region;
    }


    public Rectangle getRegion() {
        return region;
    }


    public QuadNode getNE() {
        return NE;
    }


    public QuadNode getNW() {
        return NW;
    }


    public QuadNode getSE() {
        return SE;
    }


    public QuadNode getSW() {
        return SW;
    }


    public void setNE(QuadNode NE) {
        this.NE = NE;
    }


    public void setNW(QuadNode NW) {
        this.NW = NW;
    }


    public void setSE(QuadNode SE) {
        this.SE = SE;
    }


    public void setSW(QuadNode SW) {
        this.SW = SW;
    }


    @Override
    public QuadNode insert(Point point, Rectangle region) {
        int regionXMidpoint = (region.getxCoordinate() + region.getSize() / 2);
        int regionYMidpoint = (region.getyCoordinate() + region.getSize() / 2);
        Rectangle updateRegion;

        // Left side
        if (point.getX() < regionXMidpoint) {
            // Top left
            if (point.getY() < regionYMidpoint) {
                updateRegion = new Rectangle(region.getxCoordinate(), region
                    .getyCoordinate(), region.getSize() / 2);
                setNW(NW.insert(point, updateRegion));
            }
            // Bottom left
            else {
                updateRegion = new Rectangle(region.getxCoordinate(),
                    regionYMidpoint, region.getSize() / 2);
                setSW(SW.insert(point, updateRegion));
            }
        }
        // Right Side
        else {
            // Top Right
            if (point.getY() < regionYMidpoint) {
                updateRegion = new Rectangle(regionXMidpoint, region
                    .getyCoordinate(), region.getSize() / 2);
                setNE(NE.insert(point, updateRegion));
            }
            // Bottom Right
            else {
                updateRegion = new Rectangle(regionXMidpoint, regionYMidpoint,
                    region.getSize() / 2);
                setSE(SE.insert(point, updateRegion));
            }
        }
        return this;
    }


    @Override
    public Point remove(int x, int y, Rectangle region) {
        // TODO Auto-generated method stub
        int regionXMidpoint = (region.getxCoordinate() + region.getSize() / 2);
        int regionYMidpoint = (region.getyCoordinate() + region.getSize() / 2);
        Rectangle updateRegion;
        Point removed;
        // Left side
        if (x < regionXMidpoint) {
            // Top left
            if (y < regionYMidpoint) {
                updateRegion = new Rectangle(region.getxCoordinate(), region
                    .getyCoordinate(), region.getSize() / 2);
                removed = NW.remove(x, y, updateRegion);
            }
            // Bottom left
            else {
                updateRegion = new Rectangle(region.getxCoordinate(),
                    regionYMidpoint, region.getSize() / 2);
                removed = SW.remove(x, y, updateRegion);
            }
        }
        // Right Side
        else {
            // Top Right
            if (y < regionYMidpoint) {
                updateRegion = new Rectangle(regionXMidpoint, region
                    .getyCoordinate(), region.getSize() / 2);
                removed = NE.remove(x, y, updateRegion);
            }
            // Bottom Right
            else {
                updateRegion = new Rectangle(regionXMidpoint, regionYMidpoint,
                    region.getSize() / 2);
                removed = SE.remove(x, y, updateRegion);
            }
        }

        return removed;

    }


    @Override
    public int dump(Rectangle region, int indents) {
        // TODO Auto-generated method stub
        String indentsString = "";
        for (int i = 0; i < indents; i++) {
            indentsString += "\t";
        }

        System.out.println(indentsString + "Node At: " + region + ": Internal");
        Rectangle updateRegion;
        int regionXMidpoint = (region.getxCoordinate() + region.getSize() / 2);
        int regionYMidpoint = (region.getyCoordinate() + region.getSize() / 2);

        int nodesCountNW = 0;
        int nodesCountNE = 0;
        int nodesCountSW = 0;
        int nodesCountSE = 0;

        updateRegion = new Rectangle(region.getxCoordinate(), region
            .getyCoordinate(), region.getSize() / 2);
        nodesCountNW = getNW().dump(updateRegion, indents + 1);

        updateRegion = new Rectangle(regionXMidpoint, region.getyCoordinate(),
            region.getSize() / 2);
        nodesCountNE = getNE().dump(updateRegion, indents + 1);

        updateRegion = new Rectangle(region.getxCoordinate(), regionYMidpoint,
            region.getSize() / 2);
        nodesCountSW = getSW().dump(updateRegion, indents + 1);

        updateRegion = new Rectangle(regionXMidpoint, regionYMidpoint, region
            .getSize() / 2);
        nodesCountSE = getSE().dump(updateRegion, indents + 1);

        return nodesCountNW + nodesCountNE + nodesCountSW + nodesCountSE + 1;
    }


    @Override
    public void duplicates() {
        // TODO Auto-generated method stub
        NW.duplicates();
        NE.duplicates();
        SW.duplicates();
        SE.duplicates();
    }


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


    @Override
    public void search(String name) {
        // TODO Auto-generated method stub
        NW.search(name);
        NE.search(name);
        SW.search(name);
        SE.search(name);
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


    @Override
    public ArrayList<Point> numUniquePoints() {
        ArrayList<Point> combinedPoints = new ArrayList<Point>();

        ArrayList<Point> nwPoints = NW.numUniquePoints();
        ArrayList<Point> nePoints = NE.numUniquePoints();
        ArrayList<Point> swPoints = SW.numUniquePoints();
        ArrayList<Point> sePoints = SE.numUniquePoints();

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


    public QuadNode merge() {
        setNW(NW.merge());
        setNE(NE.merge());
        setSW(SW.merge());
        setSE(SE.merge());

        ArrayList<Point> unique = numUniquePoints();
        ArrayList<Point> allPoints = pointsContained();

        if (allPoints.size() < 4) {
            LeafNode merged = new LeafNode();
            for (int i = 0; i < allPoints.size(); i++) {
                merged.insert(allPoints.get(i), region);
            }
            return merged;
        }
        else if (unique.size() > 1) {
            // dont merge
            return this;
        }
        else {
            // merge
            LeafNode merged = new LeafNode();
            for (int i = 0; i < allPoints.size(); i++) {
                merged.insert(allPoints.get(i), region);
            }
            return merged;
        }
    }


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


    @Override
    public Point removeCheckKey(int x, int y, Rectangle region, String name) {
        // TODO Auto-generated method stub
        int regionXMidpoint = (region.getxCoordinate() + region.getSize() / 2);
        int regionYMidpoint = (region.getyCoordinate() + region.getSize() / 2);
        Rectangle updateRegion;
        Point removed;
        // Left side
        if (x < regionXMidpoint) {
            // Top left
            if (y < regionYMidpoint) {
                updateRegion = new Rectangle(region.getxCoordinate(), region
                    .getyCoordinate(), region.getSize() / 2);
                removed = NW.remove(x, y, updateRegion);
            }
            // Bottom left
            else {
                updateRegion = new Rectangle(region.getxCoordinate(),
                    regionYMidpoint, region.getSize() / 2);
                removed = SW.remove(x, y, updateRegion);
            }
        }
        // Right Side
        else {
            // Top Right
            if (y < regionYMidpoint) {
                updateRegion = new Rectangle(regionXMidpoint, region
                    .getyCoordinate(), region.getSize() / 2);
                removed = NE.remove(x, y, updateRegion);
            }
            // Bottom Right
            else {
                updateRegion = new Rectangle(regionXMidpoint, regionYMidpoint,
                    region.getSize() / 2);
                removed = SE.remove(x, y, updateRegion);
            }
        }

        return removed;
    }

}
