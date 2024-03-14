//Treat as like database class 
public class QuadTree {
    private QuadNode root;
    private final Rectangle defaultRegion = new Rectangle(0,0,1024);
    
    public QuadTree() {
        this.root = EmptyNode.getInstance();
    }
    
    public void insert(Point point) {
        this.root = this.root.insert(point, defaultRegion);
    }
    
    public QuadNode remove(Point point) {
        return null;
    }
    
    public int dump() {
        return root.dump(defaultRegion, 0);
    }
    
    public void duplicates() {
        
    }
    
    public void search(String name) {
        root.search(name);
    }
    public void regionsearch(int x, int y, int w, int h) {
        root.regionsearch(x, y, w, h);
    }
    
    public QuadNode getRoot() {
        return root;
    }
}
