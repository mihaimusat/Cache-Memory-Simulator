import java.io.*;

/**
 * @author mihaimusat
 */

public class Basic extends Free {

    private int numberOfBasic;

    public Basic(String name, int numberOfBasic) {
        super(name);
        this.numberOfBasic = numberOfBasic;
    }

    public void display(boolean flag, PrintWriter fileWriter) {
        if(flag == true && numberOfBasic > 0) {
            fileWriter.print("0 Basic\n");
        }
        else if(flag == false && numberOfBasic > 0) {
            fileWriter.print("1 Basic\n");
        }
        else {
            super.display(flag, fileWriter);
        }
    }

    public void decrement() {
        if(numberOfBasic > 0) {
            numberOfBasic--;
        }
        else {
            super.decrement();
        }
    }

}