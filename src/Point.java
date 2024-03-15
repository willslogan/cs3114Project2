
public class Point {
    private int x;
    private int y;
    private String name;
    
    public Point(int x, int y, String name) {
        this.x = x;
        this.y = y;
        this.name = name;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public String getName() {
        return name;
    }
    
    public boolean equals(Object other) {
        if(this == other) {
            return true;
        }
        
        if(other == null) {
            return false;
        }
        
        //Ensure they are same clas
        if(other.getClass() != Point.class) {
            return false;
        }
        
        Point otherPoint = (Point)other;
        
        if(this.getX() == otherPoint.getX() && this.getX() == otherPoint.getY()) {
            return true;
        }
        
        return false;
    }
    
    public String toString() {
        return "(" + name + ", " + x + ", " + y + ")";
    }

    public boolean isValid() {
        if(getX() < 0) {
            return false;
        }
        if(getY() < 0) {
            return false;
        }
        
        if(getY() > 1024) {
            return false;
        }
        
        if(getX() > 1024) {
            return false;
        }
        return true;
    }
}
