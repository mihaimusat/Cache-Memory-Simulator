import java.io.*;

/**
 * @author mihaimusat
 * <p> Subscription is an abstract class which we will use for implementing the type
 *     of objects and the type of cache memory
 * </p>
 */

public abstract class Subscription {

    protected String name; //used for type of objects
    private long timestamp; //used for LruCache
    private int visits; //used for LfuCache

    /**
     * <p> Basic constructor for Subscription object, which is extended by
     *     all the objects: Basic, Free or Premium
     * </p>
     *
     * @param name defines the type of object introduced in cache
     */

    public Subscription(String name) {
        this.name = name;
        timestamp = 0;
        visits = 0;
    }

    public String getName() {
        return name;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getVisits() {
        return visits;
    }

    public void setVisits(int visits) {
        this.visits = visits;
    }

    /**
     * Function that prints the type of object in a certain format
     *
     * @param flag a boolean variable that formats the display if it's 0 or 1
     * @param fileWriter the file in which we will print the result
     */

    public abstract void display(boolean flag, PrintWriter fileWriter);

    /**
     * <p> Function used for decrementing the number of requests
     * for a certain object
     * </p>
     */

    public abstract void decrement();
}
