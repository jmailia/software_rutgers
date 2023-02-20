/* @author Henry Hecht */
/* @author Aidan Cronin */

import java.io.File;
import java.util.Scanner;

public class TuitionManager {

    private void LS_Command(Scanner file) {
        Enrollment temp = new Enrollment();
        while(file.hasNextLine()) {
            String line = file.nextLine();
            String[] lineInputs = line.split(",");
            switch (lineInputs[0]) {
                case "R":
                    Date tempDateR = new Date(lineInputs[3]);
                    Profile tempProfileR = new Profile(lineInputs[2], lineInputs[1], tempDateR);
                    Resident resident = new Resident(tempProfileR, lineInputs[4], Integer.parseInt(lineInputs[5]));
                    break;
                case "I":
                    Date tempDateI = new Date(lineInputs[3]);
                    Profile tempProfileI = new Profile(lineInputs[2], lineInputs[1], tempDateI);
                    International international = new International(tempProfileR, lineInputs[4], Integer.parseInt(lineInputs[5]));
                    break;
                case "T":
                    Date tempDateT = new Date(lineInputs[3]);
                    Profile tempProfileT = new Profile(lineInputs[2], lineInputs[1], tempDateT);
                    TriState tristate = new TriState(tempProfileR, lineInputs[4], Integer.parseInt(lineInputs[5]));
                    break;
                case "N":
                    Date tempDateN = new Date(lineInputs[3]);
                    Profile tempProfileN = new Profile(lineInputs[2], lineInputs[1], tempDateN);
                    NonResident nonresident = new NonResident(tempProfileR, lineInputs[4], Integer.parseInt(lineInputs[5]));
                    break;
            }
        }

    }

    private void AR_Command() {

    }

    private void AN_Command() {

    }

    private void AT_Command() {

    }

    private void AI_Command() {

    }

    private void E_Command() {

    }

    private void D_Command() {

    }

    private void S_Command() {

    }

    private void PE_Command() {

    }

    private void PT_Command() {

    }

    private void SE_Command() {

    }

    public void run(){
        boolean[] firstTimeLastTime = {true,false};
        Scanner file = new Scanner(new File("studentList.txt"));
        Enrollment enrollStudents = new Enrollment();
        Scanner sc = new Scanner(System.in);
        while(! (firstTimeLastTime[1] && sc.hasNextLine())) {                           //continuously read the line commands until the user quits
            String[] parsedCommandArguments = sc.nextLine().split("\\s+"); //parses arguments
            if (firstTimeLastTime[0]) {
                System.out.println("Tuition Manager running...\n");    //user knows software is ready for commands
                firstTimeLastTime[0] = false;
            }
            switch (parsedCommandArguments[0]) {                                //get the current command\
                case "":        //if the enter key is pressed without any input, this prevents an error
                    break;
                case "LS":
                    LS_Command(file);
                    break;
                case "AR":
                    AR_Command();
                    break;
                case "AN":
                    AN_Command();
                    break;
                case "AT":
                    AT_Command();
                    break;
                case "AI":
                    AT_Command();
                    break;
                case "E":
                    E_Command();
                    break;
                case "D":
                    D_Command();
                    break;
                case "S":
                    S_Command();
                    break;
                case "PE":
                    PE_Command();
                    break;
                case "PT":
                    PT_Command();
                    break;
                case "SE":
                    SE_Command();
                    break;    
                case "Q":
                    System.out.println("Tuition Manager terminated.");    //user has terminated the software normally
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
