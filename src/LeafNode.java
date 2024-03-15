import java.util.ArrayList;

public class LeafNode implements QuadNode {
    private ArrayList<Point> points;
    private Point[] unique;

    public LeafNode() {
        this.points = new ArrayList<Point>();
        unique = new Point[3];
    }


    public ArrayList<Point> getPoints() {
        return points;
    }


    private int numUniquePoints() {
        int count = 0;
        for (int i = 0; i < unique.length; i++) {
            if (unique[i] != null) {
                count++;
            }
        }
        return count;
    }


    private void addPoint(Point point) {
        for (int i = 0; i < unique.length; i++) {
            if (unique[i] == null) {
                points.add(point);
                unique[i] = point;
                break;
            }

            else if (unique[i].equals(point)) {
                points.add(point);
                break;
            }
        }
    }


    @Override
    public QuadNode insert(Point point, Rectangle region) {
        if (numUniquePoints() < 3) {
            // Don't split
            addPoint(point);
            return this;
        }
        else {
            // Split
            InternalNode splitNode = new InternalNode(region);

            for (int i = 0; i < points.size(); i++) {
                // Insert Previous nodes
                splitNode.insert(points.get(i), splitNode.getRegion());
            }
            splitNode.insert(point, splitNode.getRegion());
            return splitNode;
        }

    }


    @Override
    public QuadNode remove(Point point) {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public int dump(Rectangle region, int indents) {

        String indentsString = "";
        for (int i = 0; i < indents; i++) {
            indentsString += "\t";
        }

        System.out.println(indentsString + "Node at " + region + ": ");
        for (int i = 0; i < points.size(); i++) {
            System.out.println(indentsString + "\t" + points.get(i));
        }
        return 1;
    }


    @Override
    public void duplicates() {
        // TODO Auto-generated method stub
        for (int i = 0; i < unique.length; i++) {
            for (int j = 0; j < points.size(); j++) {

            }

            for (int j = 0; j < points.size(); j++) {

            }
        }

    }


    @Override
    public void regionsearch(int x, int y, int w, int h) {
        for (int i = 0; i < points.size(); i++) {
            if (withinRegion(points.get(i), x, y, w, h)) {
                System.out.println("Point Found: " + points.get(i));
            }
        }

    }


    @Override
    public void search(String name) {
        for (int i = 0; i < points.size(); i++) {
            if (points.get(i).getName().equals(name)) {
                System.out.println("Found " + points.get(i));
            }
        }

    }


    private boolean withinRegion(Point point, int x, int y, int w, int h) {
        if (point.getX() >= x && point.getY() >= y) {
            int x2 = x + w;
            int y2 = y + h;
            if (point.getX() <= x2 && point.getY() <= y2) {
                return true;
            }
            return false;
        }
        return false;
    }
}
