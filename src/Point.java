/**
 * Point Class
 * 
 * @author Will Logan
 * @version 1.0
 */
public class Point {
    // the x coordinate of the point
    private int x;
    // the y coordinate of the point
    private int y;
    // the name of the point
    private String name;

    /**
     * Creates an object with the values given in the parameters
     * 
     * @param x
     *            x-coordinate of the point
     * @param y
     *            y-coordinate of the point
     * @param name
     *            name of the point
     */
    public Point(int x, int y, String name) {
        this.x = x;
        this.y = y;
        this.name = name;
    }


    /**
     * Get the x coordinate of a point
     * 
     * @return x
     *         x-coordinate of the point
     */
    public int getX() {
        return x;
    }


    /**
     * Get the y coordinate of a point
     * 
     * @return y
     *         y-coodinate of the point
     */
    public int getY() {
        return y;
    }


    /**
     * Get the name of a point
     * 
     * @return name
     *         name of the point
     */
    public String getName() {
        return name;
    }


    /**
     * Checks if two points are equal. Points are considered equal if they have
     * the same x and y coordinate
     * 
     * @param other
     *            the point we are comparing with
     * @return true if the points have the same x and y coordinate
     */
    public boolean equals(Object other) {
        // Check if they reference the same thing
        if (this == other) {
            return true;
        }
        // Check if the other object is null
        if (other == null) {
            return false;
        }

        // Ensure they are same class
        if (other.getClass() != Point.class) {
            return false;
        }

        // Cast other to a point object
        Point otherPoint = (Point)other;
        // If the x and y coordinate, of both points, are equal return true
        if (this.getX() == otherPoint.getX() && this.getY() == otherPoint
            .getY()) {
            return true;
        }
        // Otherwise return false
        return false;
    }


    /**
     * Outputs a human readable string with information about the point which
     * includes the x and y coordinate and its name
     * 
     * @return a human readable string containing information about the point
     */
    public String toString() {
        return "(" + name + ", " + x + ", " + y + ")";
    }


    /**
     * Check if the point has valid parameters
     * 
     * @return true if the points has valid parameters, false if not
     */
    public boolean isValid() {
        if (getX() < 0 || getX() > 1024) {
            return false;
        }
        if (getY() < 0 || getY() > 1024) {
            return false;
        }

        return true;
    }
}
