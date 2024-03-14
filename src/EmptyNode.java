

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
    public QuadNode remove(Point point) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int dump(Rectangle region, int indents, int count) {
        // TODO Auto-generated method stub
        String indentsString = "";
        for(int i = 0; i < indents; i++) {
            indentsString += " ";
        }
        System.out.println(indentsString + "Node at " + region + ": Empty");
        return 0;
        
    }

    @Override
    public void duplicates() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void regionsearch(int x, int y, int w, int h) {
        return;
        
    }

    @Override
    public void search(String name) {
        return;
        
    }
   
}
