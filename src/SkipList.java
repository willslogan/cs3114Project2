import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import student.TestableRandom;

/**
 * This class implements SkipList data structure and contains an inner SkipNode
 * class which the SkipList will make an array of to store data.
 * 
 * @author CS Staff
 * 
 * @version 2021-08-23
 * @param <K>
 *            Key
 * @param <V>
 *            Value
 */
public class SkipList<K extends Comparable<? super K>, V>
    implements Iterable<KVPair<K, V>> {
    private SkipNode head; // First element (Sentinel Node)
    private int size; // number of entries in the Skip List
    private TestableRandom random = new TestableRandom(11111111111111L);

    /**
     * Initializes the fields head, size and level
     */
    public SkipList() {
        head = new SkipNode(null, 1);
        size = 0;
    }


    /**
     * Returns a random level number which is used as the depth of the SkipNode
     * 
     * @return a random level number
     */
    int randomLevel() {
        int lev;
        for (lev = 0; Math.abs(random.nextInt()) % 2 == 0; lev++) {
            // Do nothing
        }
        return lev; // returns a random level
    }


    /**
     * Searches for the KVPair using the key which is a Comparable object.
     * 
     * @param key
     *            key to be searched for
     * @return KVPair found, null if not
     */
    public ArrayList<KVPair<K, V>> search(K key) {
        SkipNode x = head; // Dummy header node
        for (int i = head.level; i >= 0; i--) // For each level...
            while ((x.forward[i] != null) && (x.forward[i].element().getKey()
                .compareTo(key) < 0)) // go forward
                x = x.forward[i]; // Go one last step
        x = x.forward[0]; // Move to actual record, if it exists
        if ((x != null) && (x.element().getKey().compareTo(key) == 0)) {
            ArrayList<KVPair<K, V>> results = new ArrayList<KVPair<K, V>>();
            while (x != null && x.element().getKey().compareTo(key) == 0) {
                results.add(x.element());
                x = x.forward[0];
            }
            return results;
        }
        else
            return null; // Its not there
    }


    /**
     * @return the size of the SkipList
     */
    public int size() {
        return size;
    }


    /**
     * Inserts the KVPair in the SkipList at its appropriate spot as designated
     * by its lexicoragraphical order.
     * 
     * @param it
     *            the KVPair to be inserted
     */
    @SuppressWarnings("unchecked")
    public void insert(KVPair<K, V> it) {
        int newLevel = randomLevel(); // New node's level
        if (newLevel > head.level) // If new node is deeper
            adjustHead(newLevel); // adjust the header
        // Track end of level
        SkipNode[] update = (SkipNode[])Array.newInstance(
            SkipList.SkipNode.class, head.level + 1);
        SkipNode x = head; // Start at header node
        for (int i = head.level; i >= 0; i--) { // Find insert position
            while ((x.forward[i] != null) && (x.forward[i].element().getKey()
                .compareTo(it.getKey()) < 0))
                x = x.forward[i];
            update[i] = x; // Track end at level i
        }
        x = new SkipNode(it, newLevel);
        for (int i = 0; i <= newLevel; i++) { // Splice into list
            x.forward[i] = update[i].forward[i]; // Who x points to
            update[i].forward[i] = x; // Who points to x
        }
        size++; // Increment dictionary size
    }


    /**
     * Increases the number of levels in head so that no element has more
     * indices than the head.
     * 
     * @param newLevel
     *            the number of levels to be added to head
     */
    @SuppressWarnings("unchecked")
    public void adjustHead(int newLevel) {
        SkipNode temp = head;
        head = new SkipNode(null, newLevel);
        for (int i = 0; i <= temp.level; i++)
            head.forward[i] = temp.forward[i];
        head.level = newLevel;
    }


    /**
     * Removes the KVPair that is passed in as a parameter and returns true if
     * the pair was valid and false if not.
     * 
     * @param key
     *            the KVPair to be removed
     * @return returns the removed pair if the pair was valid and null if not
     */

    @SuppressWarnings("unchecked")
    public KVPair<K, V> remove(K key) {
        // Track end of level
        SkipNode[] update = (SkipNode[])Array.newInstance(
            SkipList.SkipNode.class, head.level + 1);
        SkipNode temp = head; // Start at header node
        for (int i = head.level; i >= 0; i--) { // Find insert position
            while ((temp.forward[i] != null) && (temp.forward[i].element()
                .getKey().compareTo(key) < 0))
                temp = temp.forward[i];
            update[i] = temp; // Track end at level i
        }

        temp = temp.forward[0]; // ideal node we are trying to remove if it is
                                // there
        SkipNode deletedNode = temp;
        if (deletedNode == null || temp.element().getKey().compareTo(
            key) != 0) {
            return null;
        }

        for (int i = 0; i <= head.level; i++) { // Splice into list
            if (update[i].forward[i] == temp) {
                update[i].forward[i] = temp.forward[i];
            }
        }

        size--; // decrement dictionary size
        return deletedNode.element();
    }


    /**
     * Removes a KVPair with the specified value.
     * 
     * @param val
     *            the value of the KVPair to be removed
     * @return returns true if the removal was successful
     */
    @SuppressWarnings("unchecked")
    public KVPair<K, V> removeByValue(V val) {
        SkipNode[] update = (SkipNode[])Array.newInstance(
            SkipList.SkipNode.class, head.level + 1);
        SkipNode current = head;

        // Empty List will not have an item to be removed
        if (size() == 0) {
            return null;
        }

        for (int i = head.level; i >= 0; i--) {
            while (current.forward[i] != null && !current.forward[i].element()
                .getValue().equals(val)) {
                current = current.forward[i];
            }
            update[i] = current;
            current = head;

        }

        if (update[0].forward[0] == null) {
            return null;
        }

        // item to be removed
        current = update[0].forward[0];
        KVPair<K, V> removedItem = current.element();

        // Update Pointers
        for (int i = 0; i < current.level + 1; i++) {
            if (update[i] != null) {
                update[i].forward[i] = current.forward[i];
            }
        }

        // Update Size
        --size;

        // Return removedItem
        return removedItem;
    }


    /**
     * Prints out the SkipList in a human readable format to the console.
     */
    public void dump() {
        SkipNode temp = head;
        System.out.println("SkipList dump:");
        String depth = "Node with depth " + temp.level + ", ";
        String value = "value null";
        System.out.println(depth + value);
        if (size() >= 1) {
            temp = temp.forward[0];
            while (temp != null) {
                depth = "Node with depth " + temp.level + ", ";
                value = "value (" + temp.element().getKey() + ", " + temp
                    .element().getValue() + ")";
                System.out.println(depth + value);
                temp = temp.forward[0];
            }
        }
        System.out.println("SkipList size is: " + this.size());
    }

    /**
     * This class implements a SkipNode for the SkipList data structure.
     * 
     * @author CS Staff
     * 
     * @version 2016-01-30
     */
    private class SkipNode {

        // the KVPair to hold
        private KVPair<K, V> pair;
        // An array of pointers to subsequent nodes
        private SkipNode[] forward;
        // the level of the node
        private int level;

        /**
         * Initializes the fields with the required KVPair and the number of
         * levels from the random level method in the SkipList.
         * 
         * @param tempPair
         *            the KVPair to be inserted
         * @param level
         *            the number of levels that the SkipNode should have
         */
        @SuppressWarnings("unchecked")
        public SkipNode(KVPair<K, V> tempPair, int level) {
            pair = tempPair;
            forward = (SkipNode[])Array.newInstance(SkipList.SkipNode.class,
                level + 1);
            this.level = level;
        }


        /**
         * Returns the KVPair stored in the SkipList.
         * 
         * @return the KVPair
         */
        public KVPair<K, V> element() {
            return pair;
        }

    }


    private class SkipListIterator implements Iterator<KVPair<K, V>> {
        private SkipNode current;

        public SkipListIterator() {
            current = head;
        }


        /**
         *
         */
        @Override
        public boolean hasNext() {
            return current.forward[0] != null;
        }


        /**
         *
         */
        @Override
        public KVPair<K, V> next() {
            KVPair<K, V> elem = current.forward[0].element();
            current = current.forward[0];
            return elem;
        }

    }

    /**
     *
     */
    @Override
    public Iterator<KVPair<K, V>> iterator() {
        return new SkipListIterator();
    }

}
