/* @author Henry Hecht */
/* @author Aidan Cronin */

import java.util.Scanner;

public class TuitionManager {

    private void LS_Command(String file) {

    }

    private void AR_Command(String school, Roster myRoster) {

    }

    private void AN_Command(String school, Roster myRoster) {

    }

    private void AT_Command(String school, Roster myRoster) {

    }

    private void AI_Command(String fname, String lname, Date date, Major major, int creditsCompleted) {

    }

    private void E_Command(String school, Roster myRoster) {

    }

    private void D_Command(String school, Roster myRoster) {

    }

    private void S_Command(String school, Roster myRoster) {

    }

    private void PE_Command() {

    }

    private void PT_Command() {

    }

    private void SE_Command() {

    }

    public void run(){
        boolean[] firstTimeLastTime = {true,false};
        Roster myRoster = new Roster();
        Scanner sc = new Scanner(System.in);
        while(! (firstTimeLastTime[1] && sc.hasNextLine())) {                           //continuously read the line commands until the user quits
            String[] parsedCommandArguments = sc.nextLine().split("\\s+"); //parses arguments
            if (firstTimeLastTime[0]) {
                System.out.println("Roster Manager running...\n");    //user knows software is ready for commands
                firstTimeLastTime[0] = false;
            }
            switch (parsedCommandArguments[0]) {     //TODO: tweak                            //get the current command\
                case "":        //if the enter key is pressed without any input, this prevents an error
                    break;
                case "LS":
                    LS_Command(parsedCommandArguments[1],parsedCommandArguments[2],parsedCommandArguments[3],parsedCommandArguments[4],parsedCommandArguments[5],myRoster);
                    break;
                case "AR":
                    AR_Command(parsedCommandArguments[1],parsedCommandArguments[2],parsedCommandArguments[3],myRoster);
                    break;
                case "AN":
                    AN_Command(parsedCommandArguments[1],parsedCommandArguments[2],parsedCommandArguments[3],myRoster);
                    break;
                case "AT":
                    AT_Command(parsedCommandArguments[1],parsedCommandArguments[2],parsedCommandArguments[3],myRoster);
                    break;
                case "AI":
                    AT_Command(parsedCommandArguments[1],parsedCommandArguments[2],parsedCommandArguments[3],myRoster);
                    break;
                case "E":
                    E_Command(parsedCommandArguments[0],myRoster);
                    break;
                case "D":
                    D_Command(parsedCommandArguments[1],myRoster);
                    break;
                case "S":
                    S_Command(parsedCommandArguments[1],parsedCommandArguments[2],parsedCommandArguments[3],parsedCommandArguments[4],myRoster);
                    break;
                case "PE":
                    PE_Command(parsedCommandArguments[1],parsedCommandArguments[2],parsedCommandArguments[3],parsedCommandArguments[4],myRoster);
                    break;
                case "PT":
                    PT_Command(parsedCommandArguments[1],parsedCommandArguments[2],parsedCommandArguments[3],parsedCommandArguments[4],myRoster);
                    break;
                case "SE":
                    SE_Command(parsedCommandArguments[1],parsedCommandArguments[2],parsedCommandArguments[3],parsedCommandArguments[4],myRoster);
                    break;    
                case "Q":
                    System.out.println("Roster Manager terminated.");    //user has terminated the software normally
                    firstTimeLastTime[1] = true;
                    break;
                default:
                    System.out.println(parsedCommandArguments[0] + " is an invalid command!");
                    break;
            }
        }
        sc.close();
    }
}
