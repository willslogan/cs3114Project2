import java.util.ArrayList;

/**
 * This class is responsible for interfacing between the command processor and
 * the SkipList. The responsibility of this class is to further interpret
 * variations of commands and do some error checking of those commands. This
 * class further interpreting the command means that the two types of remove
 * will be overloaded methods for if we are removing by name or by coordinates.
 * Many of these methods will simply call the appropriate version of the
 * SkipList method after some preparation.
 * 
 * Also note that the Database class will have a clearer role in Project2,
 * where we will have two data structures. The Database class will then
 * determine
 * which command should be directed to which data structure.
 * 
 * @author Will Logan and Jacob Fast
 * 
 * @version 1.0
 */
public class Database {

    // this is the SkipList object that we are using
    // a string for the name of the rectangle and then
    // a rectangle object, these are stored in a KVPair,
    // see the KVPair class for more information
    private SkipList<String, Point> list;
    private QuadTree quadtreeDB;

    /**
     * The constructor for this class initializes a SkipList object with String
     * and Rectangle a its parameters.
     */
    public Database() {
        list = new SkipList<String, Point>();
        quadtreeDB = new QuadTree();
    }


    /**
     * Inserts the KVPair in the SkipList if the rectangle has valid coordinates
     * and dimensions, that is that the coordinates are non-negative and that
     * the rectangle object has some area (not 0, 0, 0, 0). This insert will
     * add the KVPair specified into the sorted SkipList appropriately
     * 
     * @param pair
     *            the KVPair to be inserted
     */
    public void insert(KVPair<String, Point> pair) {
        // Delegates the decision mostly to SkipList, only
        // writing the correct message to the console from
        // that
        Point currentPoint = pair.getValue();
        // Add case here if empty string is possible
        // valid string is being used.
        char firstChar = pair.getKey().charAt(0);
        if (!Character.isLetter(firstChar)) {
            System.out.println("Point rejected: " + currentPoint);
        }
        else if (!currentPoint.isValid()) {
            System.out.println("Point rejected: " + currentPoint);
        }

        else {
            ArrayList<KVPair<String, Point>> sameNamePoints = list.search(pair
                .getKey());
            if (sameNamePoints == null) {
                list.insert(pair);
                quadtreeDB.insert(currentPoint);
                System.out.println("Point inserted: " + currentPoint);
            }
            else {
                for (int i = 0; i < sameNamePoints.size(); i++) {
                    if (sameNamePoints.get(i).getValue().equals(pair
                        .getValue())) {
                        System.out.println("Point rejected: " + currentPoint);
                        return;
                    }
                }
                list.insert(pair);
                quadtreeDB.insert(currentPoint);
                System.out.println("Point inserted: " + currentPoint);
            }

        }

    }


    /**
     * Removes a rectangle with the name "name" if available. If not an error
     * message is printed to the console.
     * 
     * @param name
     *            the name of the rectangle to be removed
     */
    public void remove(String name) {
        // Making temp variable to make life easier
        KVPair<String, Point> tempKV = list.remove(name);

        // Point was found and successfully removed from the list
        if (tempKV != null) {
            quadtreeDB.removeCheckKey(tempKV.getValue().getX(), tempKV
                .getValue().getY(), name);
            System.out.println("Point removed: " + tempKV.getValue());
        }

        // Point with specified rectangle doesn't exist within the list
        else {
            System.out.println("Point not removed: " + name);
        }

    }


    /**
     * Removes a rectangle with the specified coordinates if available. If not
     * an error message is printed to the console.
     * 
     * @param x
     *            x-coordinate of the rectangle to be removed
     * @param y
     *            x-coordinate of the rectangle to be removed
     */
    public void remove(int x, int y) {
        Point temp = new Point(x, y, "temp");
        // Case where dimensions given are invalid
        if (temp.getX() < 0 || temp.getY() < 0) {
            System.out.println("Point rejected: (" + x + ", " + y + ")");
            return;
        }
        // Making temp variables to make life easier
        Point removed = quadtreeDB.remove(x, y);

        // Case where point is found
        if (removed != null) {
            list.removeByValueKeyCheck(removed.getName(), removed);
            System.out.println("Point removed: " + removed);
        }

        // Case where point is not found
        else {
            System.out.println("Point not found: (" + x + ", " + y + ")");
        }
    }


    /**
     * Displays all the points inside the specified region.
     * 
     * @param x
     *            x-Coordinate of the region
     * @param y
     *            y-Coordinate of the region
     * @param w
     *            width of the region
     * @param h
     *            height of the region
     */
    public void regionsearch(int x, int y, int w, int h) {
        // Check skiplist for intersections with region and display them
        if (w <= 0 || h <= 0) // Check if the rectangle region is valid
        {
            // Print out reject statment
            System.out.println("Rectangle rejected: (" + x + ", " + y + ", " + w
                + ", " + h + ")");
        }
        else {
            // Output expecterd header
            System.out.println("Points intersecting region (" + x + ", " + y
                + ", " + w + ", " + h + "):");
            System.out.println(quadtreeDB.regionsearch(x, y, w, h)
                + " quadtree nodes visited");

        }
    }


    /**
     * Prints out all the rectangles with the specified name in the SkipList.
     * This method will delegate the searching to the SkipList class completely.
     * 
     * @param name
     *            name of the Rectangle to be searched for
     */
    public void search(String name) {
        ArrayList<KVPair<String, Point>> results = list.search(name);
        // No rectangle was found with that name
        if (results == null) {
            System.out.println("Point not found: " + name);
        }
        // One or more rectangle was found with name
        else {
            for (int i = 0; i < results.size(); i++) {
                // temp variable so it less writing
                Point currentPoint = results.get(i).getValue();
                System.out.println("Found " + currentPoint.toString());
            }
        }
    }


    /**
     * Prints out a dump of the SkipList which includes information about the
     * size of the SkipList and shows all of the contents of the SkipList. This
     * will all be delegated to the SkipList.
     */
    public void dump() {
        list.dump();
        System.out.println("QuadTree dump:");
        int numNodes = quadtreeDB.dump();
        System.out.println(numNodes + " quadtree nodes printed");
    }


    /**
     * Finds duplicates with the Database
     */
    public void duplicates() {
        System.out.println("Duplicate points:");
        quadtreeDB.duplicates();

    }

}
