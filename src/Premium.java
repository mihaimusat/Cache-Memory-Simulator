import java.io.*;

/**
 * @author mihaimusat
 */

public class Premium extends Basic {

    private int numberOfPremium;

    public Premium(String name, int numberOfBasic, int numberOfPremium) {
        super(name, numberOfBasic);
        this.numberOfPremium = numberOfPremium;
    }

    public void display(boolean flag, PrintWriter fileWriter) {
        if(flag == true && numberOfPremium > 0) {
            fileWriter.print("0 Premium\n");
        }
        else if(flag == false && numberOfPremium > 0) {
            fileWriter.print("1 Premium\n");
        }
        else {
            super.display(flag, fileWriter);
        }
    }

    public void decrement() {
        if(numberOfPremium > 0) {
            numberOfPremium--;
        }
        else {
            super.decrement();
        }
    }

}