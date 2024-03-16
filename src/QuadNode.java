import java.util.ArrayList;

public interface QuadNode {
    // Define all the functionality a node should have.
    public QuadNode insert(Point point, Rectangle region);


    public Point remove(int x, int y, Rectangle region);
    
    public Point removeCheckKey(int x, int y, Rectangle region, String name);

    public int dump(Rectangle region, int indents);


    public void duplicates();


    public int regionsearch(int x, int y, int w, int h);
    
    public void search(String name);
    
    public ArrayList<Point> numUniquePoints();
    
    public ArrayList<Point> pointsContained();
    
    public QuadNode merge();
}
