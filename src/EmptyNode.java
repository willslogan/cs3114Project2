import java.util.ArrayList;

public class EmptyNode implements QuadNode{
    private static EmptyNode flyweight = null;
    
    private EmptyNode() {

    }
    
    public static EmptyNode getInstance() {
        if (flyweight == null) {
            flyweight = new EmptyNode();
        }
        return flyweight;
    }
    @Override
    public QuadNode insert(Point point, Rectangle region) {
        LeafNode node = new LeafNode();
        node.insert(point, region);
        return node;
    }

    @Override
    public Point remove(int x, int y, Rectangle region) {
        return null;
    }

    
    @Override
    public int dump(Rectangle region, int indents) {
        // TODO Auto-generated method stub
        String indentsString = "";
        for(int i = 0; i < indents; i++) {
            indentsString += "\t";
        }
        System.out.println(indentsString + "Node at " + region + ": Empty");
        return 1;
        
    }

    @Override
    public void duplicates() {
        
    }

    @Override
    public int regionsearch(int x, int y, int w, int h) {
        return 1;
        
    }

    @Override
    public void search(String name) {
        return;
        
    }

    @Override
    public ArrayList<Point> numUniquePoints() {
        return null;
    }

    @Override
    public ArrayList<Point> pointsContained() {
        return null;
    }

    @Override
    public QuadNode merge() {
        return this;
    }

    @Override
    public Point removeCheckKey(int x, int y, Rectangle region, String name) {
        // TODO Auto-generated method stub
        return null;
    }

    

   
}
