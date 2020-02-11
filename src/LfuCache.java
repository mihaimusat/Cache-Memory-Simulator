import java.util.ArrayList;

/**
 * @author mihaimusat
 */

public class LfuCache implements Cache {

    //implemented cache as an ArrayList of objects of type Subscription
    public ArrayList<Subscription> cache = new ArrayList<>();

    //used for the size of cache
    private int dimension;

    public LfuCache(int dimension) {
        this.dimension = dimension;
    }

    @Override
    public void add(Subscription object) {
        //check first if we have space
        if(dimension > cache.size()) {
            cache.add(object);
        }
        else {
            long min = cache.get(0).getVisits(); //minimum is the number of visits of first element
            int indexOfMin = 0; //retain index of the element with minimum number of visits
            for(int i = 1; i < cache.size(); i++) {
                if(cache.get(i).getVisits() < min) {
                    min = cache.get(i).getVisits();
                    indexOfMin = i;
                }
            }
            cache.get(indexOfMin).setVisits(0); //reset the number of visits for get operation
            cache.remove(indexOfMin); //delete element with minimum number of visits
            cache.add(object);
        }
    }

    @Override
    public void remove(Subscription object) {
        int index = -1; //used for retaining the first apparition of object in cache
        for(int i = 0; i < cache.size(); i++) {
            if(cache.get(i).getName().equals(object.getName())) {
                index = i;
                break;
            }
        }
        if(index != -1)
            cache.remove(index); //we found the object in cache, since index changed
    }

    @Override
    public boolean isFound(String name) {
        for(int i = 0; i < cache.size(); i++) {
            if(cache.get(i).getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

}