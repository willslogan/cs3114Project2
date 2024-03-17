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


    @Override
    public ArrayList<Point> numUniquePoints() {
        int count = 0;
        ArrayList<Point> uniquePoints = new ArrayList<Point>();
        for (int i = 0; i < unique.length; i++) {
            if (unique[i] == null) {
                break;
            }
            uniquePoints.add(unique[i]);
        }

        return uniquePoints;
    }


    private boolean contains(Point point) {
        for (int i = 0; i < unique.length; i++) {
            if (unique[i].equals(point)) {
                return true;
            }
        }
        return false;
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


    @Override
    public QuadNode insert(Point point, Rectangle region) {
        boolean shouldSplit = shouldSplit(point);
        if (shouldSplit) {
            // Split
            InternalNode splitNode = new InternalNode(region);

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


    @Override
    public Point remove(int x, int y, Rectangle region) {
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


    @Override
    public int dump(Rectangle region, int indents) {

        String indentsString = "";
        for (int i = 0; i < indents; i++) {
            indentsString += "\t";
        }

        System.out.println(indentsString + "Node at " + region + ":");
        for (int i = 0; i < points.size(); i++) {
            System.out.println(indentsString + "\t" + points.get(i));
        }
        return 1;
    }


    @Override
    public void duplicates() {
        int dupCount1 = 0;
        int dupCount2 = 0;
        int dupCount3 = 0;

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

            if (unique[2] != null) {
                if (unique[2].equals(points.get(i))) {
                    dupCount3++;
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

        // Shouldn't every be able to get to this case since a leaf node can
        // only hold three points that are unique
// if(dupCount3 > 1) {
// System.out.println("(" + unique[2].getX() + ", "
// + unique[2].getY() + ")");
// }

    }


    @Override
    public int regionsearch(int x, int y, int w, int h) {
        for (int i = 0; i < points.size(); i++) {
            if (withinRegion(points.get(i), x, y, w, h)) {
                System.out.println("Point found: " + points.get(i));
            }
        }
        return 1;

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


    @Override
    public QuadNode merge() {
        if (points.size() == 0) {
            return EmptyNode.getInstance();
        }
        return this;
    }


    @Override
    public Point removeCheckKey(int x, int y, Rectangle region, String name) {
        // TODO Auto-generated method stub
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
        if (countUnique == 1 && p.equals(unique[0])) {
            return false;
        }
        if (countUnique == 1 && points.size() < 3) {
            return false;
        }

        if (countUnique == 2 && points.size() == 2) {
            return false;
        }
// if (countUnique == 2 && points.size()== 3) {
// return true;
// }
// if (countUnique == 3) {
// return true;
// }
        return true;
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
