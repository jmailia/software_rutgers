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
            boolean studyAbroad;
            if (lineInputs[6] != null)
                studyAbroad = (lineInputs[6] == "true");
            else
                studyAbroad = false;
            Major tempmajor = Major.CS;
            for (Major validMajor : Major.values()) {
                if (validMajor.name().equals(lineInputs[4].toUpperCase())) {
                    tempmajor = validMajor;
                }
                switch (lineInputs[0]) {
                    case "R":
                        Date tempDateR = new Date(lineInputs[3]);
                        Profile tempProfileR = new Profile(lineInputs[2], lineInputs[1], tempDateR);
                        Resident resident = new Resident(tempProfileR, tempmajor, Integer.parseInt(lineInputs[5]));
                        break;
                    case "I":
                        Date tempDateI = new Date(lineInputs[3]);
                        Profile tempProfileI = new Profile(lineInputs[2], lineInputs[1], tempDateI);
                        International international = new International(tempProfileI, tempmajor, Integer.parseInt(lineInputs[5]), studyAbroad);
                        break;
                    case "T":
                        Date tempDateT = new Date(lineInputs[3]);
                        Profile tempProfileT = new Profile(lineInputs[2], lineInputs[1], tempDateT);
                        TriState tristate = new TriState(tempProfileT, tempmajor, Integer.parseInt(lineInputs[5]), lineInputs[6]);
                        break;
                    case "N":
                        Date tempDateN = new Date(lineInputs[3]);
                        Profile tempProfileN = new Profile(lineInputs[2], lineInputs[1], tempDateN);
                        NonResident nonresident = new NonResident(tempProfileN, tempmajor, Integer.parseInt(lineInputs[5]));
                        break;
                }
            }
        }
    }

    private void AR_Command(String fname, String lname, String date, String major, int creditsEnrolled) {

    }

    private void AN_Command(String fname, String lname, String date, String major, int creditsEnrolled) {

    }

    private void AT_Command(String fname, String lname, String date, String major, int creditsEnrolled, String state) {

    }

    private void AI_Command(String fname, String lname, String date, String major, int creditsEnrolled, boolean studyAbroad) {

    }

    private void E_Command(String fname, String lname, String date, String major, int creditsEnrolled) {

    }

    private void D_Command(String fname, String lname, String date) {

    }

    private void S_Command(String fname, String lname, String date, int scholarship) {

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
            String[] input = sc.nextLine().split("\\s+"); //parses arguments
            if (firstTimeLastTime[0]) {
                System.out.println("Tuition Manager running...\n");    //user knows software is ready for commands
                firstTimeLastTime[0] = false;
            }
            switch (input[0]) {                                //get the current command\
                case "":        //if the enter key is pressed without any input, this prevents an error
                    break;
                case "LS":
                    LS_Command(file);
                    break;
                case "AR":
                    AR_Command(input[1], input[2], input[3], input[4],Integer.parseInt(input[5]));
                    break;
                case "AN":
                    AN_Command(input[1], input[2], input[3], input[4],Integer.parseInt(input[5]));
                    break;
                case "AT":
                    AT_Command(input[1], input[2], input[3], input[4],Integer.parseInt(input[5]) ,input[6]);
                    break;
                case "AI":
                    AI_Command(input[1], input[2], input[3], input[4],Integer.parseInt(input[5]), (input[6] == "true"));
                    break;
                case "E":
                    E_Command(input[1], input[2], input[3], input[4],Integer.parseInt(input[5]));
                    break;
                case "D":
                    D_Command(input[1], input[2], input[3]);
                    break;
                case "S":
                    S_Command(input[1], input[2], input[3], Integer.parseInt(input[4]));
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
