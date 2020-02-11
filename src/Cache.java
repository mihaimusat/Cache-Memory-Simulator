/**
 * @author mihaimusat
 */

public interface Cache {

    /**
     * Implements the add operation as it is described in homework task
     * @param object
     *              add in a specific cache type the object given as parameter
     */

    public void add(Subscription object);

    /**
     * Implements the removal of an object for the get operation
     * @param object
     *              remove from a specific cache type the object received as parameter
     */

    public void remove(Subscription object);

    /**
     * Checks if an object is present in a specific cache type
     * @param name
     *              defines the type of object: Basic, Free or Premium
     * @return
     *              true/false if the object is in the cache or not
     */

    public boolean isFound(String name);

}