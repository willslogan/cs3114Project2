
public class InternalNode implements QuadNode {
    private QuadNode NE;
    private QuadNode NW;
    private QuadNode SE;
    private QuadNode SW;
    private Rectangle region;
    
    public InternalNode(Rectangle region) {
        this.NW = EmptyNode.getInstance();
        this.NE = EmptyNode.getInstance();
        this.SE = EmptyNode.getInstance();
        this.SW = EmptyNode.getInstance();
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
    
    public void setNE(QuadNode NE) {
        this.NE = NE;
    }
    
    public void setNW(QuadNode NW) {
        this.NW = NW;
    }
    
    public void setSE(QuadNode SE) {
        this.SE = SE;
    }
    
    public void setSW(QuadNode SW) {
        this.SW = SW;
    }

    @Override
    public QuadNode insert(Point point, Rectangle region) {
        int regionXMidpoint = (region.getxCoordinate() + region.getSize() / 2);
        int regionYMidpoint = (region.getyCoordinate() + region.getSize() / 2);
        Rectangle updateRegion;
        
        //Left side
        if(point.getX()< regionXMidpoint) {
            //Top left
            if(point.getY() < regionYMidpoint) {
                updateRegion = new Rectangle(region.getxCoordinate(), region.getyCoordinate(), region.getSize()/2);
                setNW(NW.insert(point, updateRegion));
            }
            //Bottom left
            else {
                updateRegion = new Rectangle(region.getxCoordinate(), regionYMidpoint, region.getSize() / 2);
                setSW(SW.insert(point, updateRegion));
            }
        }
        //Right Side
        else {
            //Top Right
            if(point.getY() < regionYMidpoint) {
                updateRegion = new Rectangle(regionXMidpoint, region.getyCoordinate(), region.getSize() / 2);
                setNE(NE.insert(point, updateRegion));
            }
            //Bottom Right
            else {
                updateRegion = new Rectangle(regionXMidpoint, regionYMidpoint, region.getSize() / 2);
                setSE(SE.insert(point, updateRegion));
            }
        }
        return this;
    }

    @Override
    public QuadNode remove(Point point) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int dump(Rectangle region, int indents) {
        // TODO Auto-generated method stub
        String indentsString = "";
        for(int i = 0; i < indents; i++) {
            indentsString += "\t";
        }
        
        System.out.println(indentsString + "Node At: " + region + ": Internal");
        Rectangle updateRegion;
        int regionXMidpoint = (region.getxCoordinate() + region.getSize() / 2);
        int regionYMidpoint = (region.getyCoordinate() + region.getSize() / 2);
        
        int nodesCountNW = 0;
        int nodesCountNE = 0;
        int nodesCountSW = 0;
        int nodesCountSE = 0;
        
        updateRegion = new Rectangle(region.getxCoordinate(), region.getyCoordinate(), region.getSize() / 2);
        nodesCountNW = getNW().dump(updateRegion, indents + 1);
        
        updateRegion = new Rectangle(regionXMidpoint, region.getyCoordinate(), region.getSize() / 2);
        nodesCountNE = getNE().dump(updateRegion, indents + 1);
        
        updateRegion = new Rectangle(region.getxCoordinate(), regionYMidpoint, region.getSize() / 2);
        nodesCountSW = getSW().dump(updateRegion, indents + 1);
        
        
        updateRegion = new Rectangle(regionXMidpoint, regionYMidpoint, region.getSize() / 2);
        nodesCountSE = getSE().dump(updateRegion, indents + 1);
        
        return nodesCountNW + nodesCountNE + nodesCountSW + nodesCountSE + 1;
    }

    @Override
    public void duplicates() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void regionsearch(int x, int y, int w, int h) {
        // TODO Auto-generated method stub
        int regionXMidpoint = (region.getxCoordinate() + region.getSize() / 2);
        int regionYMidpoint = (region.getyCoordinate() + region.getSize() / 2);
        
        Rectangle leftTopRegion = new Rectangle(region.getxCoordinate(), region.getyCoordinate(), region.getSize()/2);
        Rectangle leftBotRegion = new Rectangle(region.getxCoordinate(), regionYMidpoint, region.getSize() / 2);
        Rectangle rightTopRegion = new Rectangle(regionXMidpoint, region.getyCoordinate(), region.getSize() / 2);
        Rectangle rightBotRegion = new Rectangle(regionXMidpoint, regionYMidpoint, region.getSize() / 2);
        
        if(intersect(leftTopRegion, x, y, w, h)) {
            NW.regionsearch(x, y, w, h);
        }
        
        if(intersect(rightTopRegion, x, y, w, h)) {
            NE.regionsearch(x, y, w, h);
        }
        
        if(intersect(leftBotRegion, x, y, w, h)) {
            SW.regionsearch(x, y, w, h);
        }
        
        if(intersect(rightBotRegion, x, y, w, h)) {
            SE.regionsearch(x, y, w, h);
        }
        
        
    }

    @Override
    public void search(String name) {
        // TODO Auto-generated method stub
        NW.search(name);
        NE.search(name);
        SW.search(name);
        SE.search(name);
    }
    
    
    private boolean intersect(Rectangle region, int x, int y, int w, int h) {
        // Check if the any points of the rectangle intersect
        if (!(region.getxCoordinate() < (x + w))) {
            return false;
        }
        else if (!((region.getxCoordinate() + region.getSize()) > x)) {
            return false;
        }
        else if (!(region.getyCoordinate() < (y + h))) {
            return false;
        }
        else if (!((region.getyCoordinate() + region.getSize()) > y)) {
            return false;
        }
        else {
            return true;
        }
    }
}
