
public interface QuadNode {
    // Define all the functionality a node should have.
    public QuadNode insert(Point point, Rectangle region);


    public QuadNode remove(Point point);


    public int dump(Rectangle region, int indents, int numNodes);


    public void duplicates();


    public void regionsearch(int x, int y, int w, int h);
    
    public void search(String name);
}
