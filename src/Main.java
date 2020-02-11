import java.io.*;
import java.util.ArrayList;

/**
 * @author mihaimusat
 */

public class Main {

    public static void main(String[] args) {

        try {
            FileReader fileInput = new FileReader(args[0]); //args[0] = input file
            BufferedReader buffer = new BufferedReader(fileInput);
            FileWriter fileOutput = new FileWriter(args[1]); //args[1] = output file
            PrintWriter printOutput = new PrintWriter(fileOutput);

            Cache cache = null;

            //main memory is represented as an ArrayList of objects of type Subscription
            ArrayList<Subscription> memory = new ArrayList<Subscription>();

            //read the type of cache used
            String type = buffer.readLine();

            if(type.equals("FIFO")) {
                cache = new FifoCache(Integer.parseInt(buffer.readLine()));
            }
            else if(type.equals("LRU")) {
                cache = new LruCache(Integer.parseInt(buffer.readLine()));
            }
            else if(type.equals("LFU")) {
                cache = new LfuCache(Integer.parseInt(buffer.readLine()));
            }

            //the number of lines we get as input
            int numberOfLines = Integer.parseInt(buffer.readLine());
            long timestamp = 0;

            while(numberOfLines != 0) {

                Subscription element;
                String line = buffer.readLine(); //read a line
                String[] tokens = line.split(" "); //split it by spaces

                if(tokens[0].equals("ADD")) {
                    //check in function of the numbers of parameters of add operation the type of element
                    if(tokens.length == 3) {
                        element = new Basic(tokens[1], Integer.parseInt(tokens[2]));
                    }
                    else {
                        element = new Premium(tokens[1], Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]));
                    }

                    int index = searchInMemory(memory, element.getName()); //search in main memory an element by name

                    if(index == -1) {
                        memory.add(element); //if not found, we add it
                    }
                    else {
                        memory.remove(index); //if found, we delete it
                        cache.remove(element); //delete it also from cache
                        memory.add(element); //overwrite it
                    }
                }
                else if(tokens[0].equals("GET")) {

                    int index = searchInMemory(memory, tokens[1]);

                    if(index == -1) {
                        printOutput.print("2\n");
                    }
                    else {
                        if(cache.isFound(tokens[1]) == true) {
                            memory.get(index).display(cache.isFound(tokens[1]), printOutput);
                        }
                        else {
                            memory.get(index).display(cache.isFound(tokens[1]), printOutput);
                            cache.add(memory.get(index));
                        }
                        timestamp++; //counter used for LruCache
                        memory.get(index).setTimestamp(timestamp); //put current timestamp
                        memory.get(index).decrement(); //decrement number of requests of object at this step
                        memory.get(index).setVisits(memory.get(index).getVisits() + 1); //increase visits
                    }
                }
                numberOfLines--;
            }
            buffer.close();
            fileOutput.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println("The file" + args[0] + "not found");
        }
        catch(IOException ex) {
            System.out.println("Error when reading");
        }

    }

    /**
     * Function that searches in memory an object by type
     * @param memory
     *              the main memory as an ArrayList of objects of type Subscription
     * @param name
     *              the type of object searched
     * @return
     *              index of element of certain type in memory
     */
    public static int searchInMemory(ArrayList<Subscription> memory, String name) {
        int index = -1;
        for(int i = 0; i < memory.size(); i++) {
            if(memory.get(i).getName().equals(name)) {
                index = i;
                break;
            }
        }
        return index;
    }
}
