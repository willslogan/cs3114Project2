/**
 * The purpose of this class is to parse a single line from the command text
 * file according to the format specified in the project
 * 
 * @author Jacob Fast
 * @version 1.0
 */
public class CommandProcessor {
    // the PointsDatabse object to manipulate the commands that the command
    // processor feeds to it
    private Database data;

    /**
     * The constructor for the command processor requires a database instance to
     * exist, so the only constructor takes a database class object to feed
     * commands to.
     * 
     */
    public CommandProcessor() {
        data = new Database();
    }


    /**
     * This method parses keywords in the line and calls methods in the database
     * as required. Each line command will be specified by one of the keywords
     * to perform the actions. If the command in the file line is not one of the
     * allowed inputs, an appropriate message will be written in the console.
     * This processor method is called for each line in the file. Note that the
     * methods called will themselves write to the console, this method does
     * not, only calling methods that do.
     * 
     * @param line
     *         a single line form the text file
     */
    public void processor(String line) {
        // converts the stirng of the line into an array of its space (" ")
        // delimited elements
        String[] arr = line.split("\\s{1,}");
        String command = arr[0]; // The command will be the first of these
                                 // elements
        // calls the insert function and passes the correct paramters by
        // converting the string integers into their Integer equivalent,
        // trimming the whitespace
        if (command.equals("insert")) {
            // Store the name of the point we are inserting
            String name = arr[1];
            // Store the x coordinate of the point
            int x = Integer.parseInt(arr[2]);
            // Store the y coordinate of the point
            int y = Integer.parseInt(arr[3]);
            // Create the point to store
            Point p = new Point(x, y, name);
            // Create the KVPair to store
            KVPair<String, Point> pair = new KVPair<String, Point>(name, p);
            // Add the KVPair to the database
            data.insert(pair);
        }
        // calls the appropriate remove method based on the number of white
        // space delimited strings in the line
        else if (command.equals("remove")) {
            // Checks the number of white space delimited strings in the line
            if (arr.length == 2) {
                // Call remove by name
                data.remove(arr[1]);
            }
            else {
                // Call remove by coordinates
                // Store the x coordinate of the point
                int x = Integer.parseInt(arr[1]);
                // Store the y coordinate of the point
                int y = Integer.parseInt(arr[2]);
                data.remove(x, y);
            }
        }
        // Calls the regionsearch method for a pair of coordinates and a width
        // and height.
        // the string integers in the line will be trimmed of whitespace
        else if (command.equals("regionsearch")) {
            // Store the x coordinate of the search
            int x = Integer.parseInt(arr[1]);
            // Store the y coordinate of the search
            int y = Integer.parseInt(arr[2]);
            // Store the width of the search
            int w = Integer.parseInt(arr[3]);
            // Store the height of the search
            int h = Integer.parseInt(arr[4]);
            // Call the regionsearch method on the database
            data.regionsearch(x, y, w, h);
        }
        else if (command.equals("duplicates")) {
            // Call duplicates on the database
            data.duplicates();
        }
        else if (command.equals("search")) {
            // Call search by the name argument of the line (arr[1])
            data.search(arr[1]);
        }
        else if (command.equals("dump")) {
            // Call dump on the database
            data.dump();
        }
        else {
            // The first white space delimited string in the line is not one of
            // the commands which can manipulate the database, a message will be
            // written to the console
            System.out.println("Unrecognized command.");
        }
    }

}
