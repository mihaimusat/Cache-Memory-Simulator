import java.io.*;

/**
 * @author mihaimusat
 */

public class Free extends Subscription {

    public Free(String name) {
        super(name);
    }

    public void display(boolean flag, PrintWriter fileWriter) {
        if(flag == true) {
            fileWriter.print("0 Free\n");
        }
        else {
            fileWriter.print("1 Free\n");
        }
    }

    public void decrement() {
        //nothing to do since Free is unlimited
    }

}