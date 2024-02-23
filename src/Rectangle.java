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
    // the distance from the x coordinate the rectangle spans
    private int width;
    // the distance from the y coordinate the rectangle spans
    private int height;

    private static final int WORLD_BOX = 1024;

    /**
     * Creates an object with the values to the parameters given in the
     * xCoordinate, yCoordinate, width, height
     * 
     * @param x
     *            x-coordinate of the rectangle
     * @param y
     *            y-coordinate of the rectangle
     * @param w
     *            width of the rectangle
     * @param h
     *            height of the rectangle
     */
    public Rectangle(int x, int y, int w, int h) {
        xCoordinate = x;
        yCoordinate = y;
        width = w;
        height = h;
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
     * Getter for the width
     *
     * @return the width
     */
    public int getWidth() {
        return width;
    }


    /**
     * Getter for the height
     *
     * @return the height
     */
    public int getHeight() {
        return height;
    }


    /**
     * Checks if the invoking rectangle intersects with rec.
     * 
     * @param r2
     *            Rectangle parameter
     * @return true if the rectangle intersects with rec, false if not
     */
    public boolean intersect(Rectangle r2) {
        // Check if the any points of the rectangle intersect
        if (!(this.xCoordinate < (r2.xCoordinate + r2.width))) {
            return false;
        }
        else if (!((this.xCoordinate + this.width) > r2.xCoordinate)) {
            return false;
        }
        else if (!(this.yCoordinate < (r2.yCoordinate + r2.height))) {
            return false;
        }
        else if (!((this.yCoordinate + this.height) > r2.yCoordinate)) {
            return false;
        }
        else {
            return true;
        }
    }


    /**
     * Checks, if the invoking rectangle has the same coordinates as rec.
     * 
     * @param rec
     *            the rectangle parameter
     * @return true if the rectangle has the same coordinates as rec, false if
     *         not
     */
    public boolean equals(Object rec) {
        if (rec == this) {
            return true;
        }
        if (rec == null) {
            return false;
        }

        // Ensure they are the same class
        if (rec.getClass() != Rectangle.class) {
            return false;
        }

        // Quality of life so casting doesn't have to be done every time for
        // every boolean operation
        Rectangle otherRectangle = (Rectangle)rec;

        // If initial xCordinate and width are the same then xCoordinates are
        // the same
        if (this.getxCoordinate() == otherRectangle.getxCoordinate()) {
            if (this.getWidth() == otherRectangle.getWidth()) {
                // If initial yCordinate and height are the same then
                // yCoordinates are the same
                if (this.getyCoordinate() == otherRectangle.getyCoordinate()) {
                    if (this.getHeight() == otherRectangle.getHeight()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    /**
     * Outputs a human readable string with information about the rectangle
     * which includes the x and y coordinate and its height and width
     * 
     * @return a human readable string containing information about the
     *         rectangle
     */
    public String toString() {
        int x = this.xCoordinate;
        int y = this.yCoordinate;
        int w = this.width;
        int h = this.height;
        return "" + x + ", " + y + ", " + w + ", " + h;
    }


    /**
     * Checks if the rectangle has invalid parameters
     * 
     * @return true if the rectangle has invalid parameters, false if not
     */
    public boolean isInvalid() {
        // Invalid if height and width are not greater than 0
        if (this.height <= 0 || this.width <= 0) {
            return true;
        }
        // Invalid if xCoordinate or yCoordinate are less than zero
        else if (this.xCoordinate < 0 || this.yCoordinate < 0) {
            return true;
        }

        // Invalid if perimeter goes beyond world box
        else if ((this.xCoordinate + this.width) > WORLD_BOX
            || (this.yCoordinate + this.height) > WORLD_BOX) {
            return true;
        }
        else {
            return false;
        }
    }
}
