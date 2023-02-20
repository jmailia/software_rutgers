/* @author Henry Hecht */
/* @author Aidan Cronin */

import java.io.File;
import java.util.Scanner;

public class TuitionManager {

    private void LS_Command(Scanner file) {
        Roster temp = new Roster();
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
                        temp.add(resident);
                        break;
                    case "I":
                        Date tempDateI = new Date(lineInputs[3]);
                        Profile tempProfileI = new Profile(lineInputs[2], lineInputs[1], tempDateI);
                        International international = new International(tempProfileI, tempmajor, Integer.parseInt(lineInputs[5]), studyAbroad);
                        temp.add(international);
                        break;
                    case "T":
                        Date tempDateT = new Date(lineInputs[3]);
                        Profile tempProfileT = new Profile(lineInputs[2], lineInputs[1], tempDateT);
                        TriState tristate = new TriState(tempProfileT, tempmajor, Integer.parseInt(lineInputs[5]), lineInputs[6]);
                        temp.add(tristate);
                        break;
                    case "N":
                        Date tempDateN = new Date(lineInputs[3]);
                        Profile tempProfileN = new Profile(lineInputs[2], lineInputs[1], tempDateN);
                        NonResident nonresident = new NonResident(tempProfileN, tempmajor, Integer.parseInt(lineInputs[5]));
                        temp.add(nonresident);
                        break;
                }
            }
        }
    }

    private void AR_Command(String fname, String lname, String date, String major, int creditsCompleted, Roster myRoster) {
        boolean isValidAddition = true;
        boolean isMajorValid = false;
        for (Major value : Major.values()) {
            if (value.name().equals(major.toUpperCase())) {
                isMajorValid = true;
            }
        }
        if (!isMajorValid) {
            System.out.println("Major code invalid: " + major);
            isValidAddition = false;
        }
        int numberOfCredits = 0;
        try {
            numberOfCredits = creditsCompleted;
            if ((numberOfCredits!=0 && numberOfCredits != +0) && (numberOfCredits < 0 || numberOfCredits == -0)) {
                System.out.println("Credits completed invalid: cannot be negative!");
                isValidAddition = false;
            }
        } catch (NumberFormatException e) {
            // Non-integer inputted for of credits completed
            System.out.println("Credits completed invalid: not an integer!");
            isValidAddition = false;
        }
        if (isValidAddition) {
            Resident myResident = new Resident(
                    new Profile(lname, fname, new Date(date)),
                    Major.valueOf(major.toUpperCase()), creditsCompleted);

            if (!myResident.getProfile().getDob().isCalendarDateValid()) {
                System.out.println("DOB invalid: " + myResident.getProfile().getDob() + " not a valid calendar date!");
                isValidAddition = false;
            }

            if (!myResident.getProfile().getDob().isStudentOver16()) {
                System.out.println("DOB invalid: " + myResident.getProfile().getDob() + " younger than 16 years old.");
                isValidAddition = false;
            }
            if (myRoster.contains(myResident)) {
                System.out.println(myResident.getProfile().toString() + " is already in the roster.");
                isValidAddition = false;
            }
            if (isValidAddition) {
                myRoster.add(myResident);
                System.out.println(myResident.printStudentProfile() + " added to the roster.");
            }
        }
    }

    private void AN_Command(String fname, String lname, String date, String major, int creditsCompleted, Roster myRoster) {
        boolean isValidAddition = true;
        boolean isMajorValid = false;
        for (Major value : Major.values()) {
            if (value.name().equals(major.toUpperCase())) {
                isMajorValid = true;
            }
        }
        if (!isMajorValid) {
            System.out.println("Major code invalid: " + major);
            isValidAddition = false;
        }
        int numberOfCredits = 0;
        try {
            numberOfCredits = creditsCompleted;
            if ((numberOfCredits!=0 && numberOfCredits != +0) && (numberOfCredits < 0 || numberOfCredits == -0)) {
                System.out.println("Credits completed invalid: cannot be negative!");
                isValidAddition = false;
            }
        } catch (NumberFormatException e) {
            // Non-integer inputted for of credits completed
            System.out.println("Credits completed invalid: not an integer!");
            isValidAddition = false;
        }
        if (isValidAddition) {
            NonResident myNonResident = new NonResident(
                    new Profile(lname, fname, new Date(date)),
                    Major.valueOf(major.toUpperCase()), creditsCompleted);

            if (!myNonResident.getProfile().getDob().isCalendarDateValid()) {
                System.out.println("DOB invalid: " + myNonResident.getProfile().getDob() + " not a valid calendar date!");
                isValidAddition = false;
            }

            if (!myNonResident.getProfile().getDob().isStudentOver16()) {
                System.out.println("DOB invalid: " + myNonResident.getProfile().getDob() + " younger than 16 years old.");
                isValidAddition = false;
            }
            if (myRoster.contains(myNonResident)) {
                System.out.println(myNonResident.getProfile().toString() + " is already in the roster.");
                isValidAddition = false;
            }
            if (isValidAddition) {
                myRoster.add(myNonResident);
                System.out.println(myNonResident.printStudentProfile() + " added to the roster.");
            }
        }
    }

    private void AT_Command(String fname, String lname, String date, String major, int creditsCompleted, String state, Roster myRoster) {
        boolean isValidAddition = true;
        boolean isMajorValid = false;
        for (Major value : Major.values()) {
            if (value.name().equals(major.toUpperCase())) {
                isMajorValid = true;
            }
        }
        if (!isMajorValid) {
            System.out.println("Major code invalid: " + major);
            isValidAddition = false;
        }
        int numberOfCredits = 0;
        try {
            numberOfCredits = creditsCompleted;
            if ((numberOfCredits!=0 && numberOfCredits != +0) && (numberOfCredits < 0 || numberOfCredits == -0)) {
                System.out.println("Credits completed invalid: cannot be negative!");
                isValidAddition = false;
            }
        } catch (NumberFormatException e) {
            // Non-integer inputted for of credits completed
            System.out.println("Credits completed invalid: not an integer!");
            isValidAddition = false;
        }
        if (isValidAddition) {
            TriState myTriState = new TriState(
                    new Profile(lname, fname, new Date(date)),
                    Major.valueOf(major.toUpperCase()), creditsCompleted, state);

            if (!myTriState.getProfile().getDob().isCalendarDateValid()) {
                System.out.println("DOB invalid: " + myTriState.getProfile().getDob() + " not a valid calendar date!");
                isValidAddition = false;
            }

            if (!myTriState.getProfile().getDob().isStudentOver16()) {
                System.out.println("DOB invalid: " + myTriState.getProfile().getDob() + " younger than 16 years old.");
                isValidAddition = false;
            }
            if (myRoster.contains(myTriState)) {
                System.out.println(myTriState.getProfile().toString() + " is already in the roster.");
                isValidAddition = false;
            }
            if (isValidAddition) {
                myRoster.add(myTriState);
                System.out.println(myTriState.printStudentProfile() + " added to the roster.");
            }
        }
    }

    private void AI_Command(String fname, String lname, String date, String major, int creditsCompleted, boolean studyAbroad, Roster myRoster) {
        boolean isValidAddition = true;
        boolean isMajorValid = false;
        for (Major value : Major.values()) {
            if (value.name().equals(major.toUpperCase())) {
                isMajorValid = true;
            }
        }
        if (!isMajorValid) {
            System.out.println("Major code invalid: " + major);
            isValidAddition = false;
        }
        int numberOfCredits = 0;
        try {
            numberOfCredits = creditsCompleted;
            if ((numberOfCredits!=0 && numberOfCredits != +0) && (numberOfCredits < 0 || numberOfCredits == -0)) {
                System.out.println("Credits completed invalid: cannot be negative!");
                isValidAddition = false;
            }
        } catch (NumberFormatException e) {
            // Non-integer inputted for of credits completed
            System.out.println("Credits completed invalid: not an integer!");
            isValidAddition = false;
        }
        if (isValidAddition) {
            International myInternational = new International(
                    new Profile(lname, fname, new Date(date)),
                    Major.valueOf(major.toUpperCase()), creditsCompleted, studyAbroad);

            if (!myInternational.getProfile().getDob().isCalendarDateValid()) {
                System.out.println("DOB invalid: " + myInternational.getProfile().getDob() + " not a valid calendar date!");
                isValidAddition = false;
            }

            if (!myInternational.getProfile().getDob().isStudentOver16()) {
                System.out.println("DOB invalid: " + myInternational.getProfile().getDob() + " younger than 16 years old.");
                isValidAddition = false;
            }
            if (myRoster.contains(myInternational)) {
                System.out.println(myInternational.getProfile().toString() + " is already in the roster.");
                isValidAddition = false;
            }
            if (isValidAddition) {
                myRoster.add(myInternational);
                System.out.println(myInternational.printStudentProfile() + " added to the roster.");
            }
        }
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
        Enrollment myEnrollment = new Enrollment();
        Roster myRoster = new Roster();
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
                    AR_Command(input[1], input[2], input[3], input[4],Integer.parseInt(input[5]), myRoster);
                    break;
                case "AN":
                    AN_Command(input[1], input[2], input[3], input[4],Integer.parseInt(input[5]), myRoster);
                    break;
                case "AT":
                    AT_Command(input[1], input[2], input[3], input[4],Integer.parseInt(input[5]) ,input[6], myRoster);
                    break;
                case "AI":
                    AI_Command(input[1], input[2], input[3], input[4],Integer.parseInt(input[5]), (input[6] == "true"), myRoster);
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
                    System.out.println(input[0] + " is an invalid command!");
                    break;
            }
        }
        sc.close();
    }
}
