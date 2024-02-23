
public class InternalNode extends QuadNode {
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
}
