
public class LeafNode implements QuadNode {
    private Point point;
    
    public LeafNode(Point point) {
        this.point = point;
    }
    
    public Point getPoint() {
        return point;
    }

    @Override
    public QuadNode insert(Point point) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public QuadNode remove(Point point) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void dump() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void duplicates() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void regionsearch(int x, int y, int w, int h) {
        // TODO Auto-generated method stub
        
    }
}
