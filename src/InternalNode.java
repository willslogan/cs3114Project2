
public class InternalNode implements QuadNode {
    private QuadNode NE;
    private QuadNode NW;
    private QuadNode SE;
    private QuadNode SW;
    private Rectangle region;
    
    public InternalNode(QuadNode NE, QuadNode NW, QuadNode SE, QuadNode SW, Rectangle region) {
        this.NW = NW;
        this.NE = NE;
        this.SE = SE;
        this.SW = SW;
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
