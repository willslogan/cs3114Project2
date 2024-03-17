/**
 * This class holds the coordinates and dimensions of a rectangle and methods to
 * check if it intersects or has the same coordinates as an other rectangle.
 * 
 * @author CS Staff
 * 
 * @version 2021-08-23
 */
public class Rectangle {
    // the x coordinate of the rectangle
    private int xCoordinate;
    // the y coordinate of the rectangle
    private int yCoordinate;

    private int size;

    /**
     * Constructor
     * 
     * @param x
     *            x coordinate
     * @param y
     *            y coordinate
     * @param size
     *            size of rectangle
     */
    public Rectangle(int x, int y, int size) {
        xCoordinate = x;
        yCoordinate = y;
        this.size = size;
    }


    /**
     * Getter for the x coordinate
     *
     * @return the x coordinate
     */
    public int getxCoordinate() {
        return xCoordinate;
    }


    /**
     * Getter for the y coordinate
     *
     * @return the y coordinate
     */
    public int getyCoordinate() {
        return yCoordinate;
    }


    /**
     * Getter for size
     * 
     * @return returns size of rectangle
     */
    public int getSize() {
        return size;
    }


    /**
     * String version to Rectangle
     * @return String version of rectangle
     */
    public String toString() {
        return "" + xCoordinate + ", " + yCoordinate + ", " + size;
    }

}
