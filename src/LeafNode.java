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
    public int numUniquePoints() {
        int count = 0;
        for (int i = 0; i < unique.length; i++) {
            if (unique[i] == null) {
              break;  
            }
            count++;
        }
        
        //----------------------
        for(int i = 0; i < unique.length; i++) {
            System.out.print(unique[i] + " ");
        }
        System.out.println();
        System.out.println(count);
        return count;
    }
    private boolean contains(Point point) {
        for(int i = 0; i < unique.length; i++) {
            if(unique[i].equals(point)) {
                return true;
            }
        }
        return false;
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
        if (numUniquePoints() < 3 || contains(point)) {
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
    public Point remove(int x, int y, Rectangle region) {
        // TODO Auto-generated method stub
        Point removed = null;
        for(int i = 0; i<points.size(); i++) {
            
            if(points.get(i).getX() == x && points.get(i).getY() == y) {
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

        System.out.println(indentsString + "Node at " + region + ": ");
        for (int i = 0; i < points.size(); i++) {
            System.out.println(indentsString + "\t" + points.get(i));
        }
        return 1;
    }


    @Override
    public void duplicates() {
        // TODO Auto-generated method stub
        int dupCount;
        for (int i = 0; i < unique.length; i++) {
            if (unique[i] == null) {
                break;
            }
            dupCount = 0;
            for (int j = 0; j < points.size(); j++) {
                if (unique[i].equals(points.get(j))) {
                    dupCount++;
                    if (dupCount > 1) {
                        System.out.println("(" + unique[i].getX() + ", "
                            + unique[i].getY() + ")");
                        break;
                    }
                }
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


    @Override
    public ArrayList<Point> pointsContained() {
        return points;
    }
    
    private void updateUnique() {
        Point[] uniqueUpdate = new Point[3];
        int index = 0;
        for(int i = 0; i<points.size(); i++) {
            if(contains(uniqueUpdate, points.get(index))) {
                unique[i] = points.get(index);
                index++;
            }
        }
        unique = uniqueUpdate;
    }
    
    private boolean contains(Point[] uniqueUpdate, Point p) {
        for(int i = 0; i< uniqueUpdate.length; i++) {
            if(p.equals(uniqueUpdate[i])) {
                return true;
            }
        }
        return false;
    }


    @Override
    public QuadNode merge() {
        if(points.size() == 0) {
            return EmptyNode.getInstance();
        }
        return this;
    }


}
