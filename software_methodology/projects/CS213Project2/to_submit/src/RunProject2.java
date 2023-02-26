/* @author Henry Hecht */
/* @author Aidan Cronin */

import java.io.FileNotFoundException;
/**
 * Runs project 2 by calling TuitionManager().run()
 */
public class RunProject2 {
    /**
     * TuitionManager().run() is initiated
     * @param args a String[] for arguments passed
     * @throws FileNotFoundException signals that an attempt to open the file denoted by a specified pathname has failed
     */
    public static void main(String[] args) throws FileNotFoundException {

        new TuitionManager().run();
    }
}