

public class EmptyNode implements QuadNode{
    public static final EmptyNode flyweight = new EmptyNode();
    
    private EmptyNode() {

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
