
public class LeafNode extends QuadNode {
    private Point point;
    
    public LeafNode(Point point) {
        this.point = point;
    }
    
    public Point getPoint() {
        return point;
    }
}
