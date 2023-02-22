/* @author Henry Hecht */
/* @author Aidan Cronin */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TuitionManager {

    private void LS_Command(Scanner file, Roster myRoster) {
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
                        myRoster.add(resident);
                        break;
                    case "I":
                        Date tempDateI = new Date(lineInputs[3]);
                        Profile tempProfileI = new Profile(lineInputs[2], lineInputs[1], tempDateI);
                        International international = new International(tempProfileI, tempmajor, Integer.parseInt(lineInputs[5]), studyAbroad);
                        myRoster.add(international);
                        break;
                    case "T":
                        Date tempDateT = new Date(lineInputs[3]);
                        Profile tempProfileT = new Profile(lineInputs[2], lineInputs[1], tempDateT);
                        TriState tristate = new TriState(tempProfileT, tempmajor, Integer.parseInt(lineInputs[5]), lineInputs[6]);
                        myRoster.add(tristate);
                        break;
                    case "N":
                        Date tempDateN = new Date(lineInputs[3]);
                        Profile tempProfileN = new Profile(lineInputs[2], lineInputs[1], tempDateN);
                        NonResident nonresident = new NonResident(tempProfileN, tempmajor, Integer.parseInt(lineInputs[5]));
                        myRoster.add(nonresident);
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

    /**
     * Removes a student from Roster, but only if they are already in the Roster
     * @param firstName First name of the student who might be removed
     * @param lastName Last name of the student who might be removed
     * @param DOB DOB of the student who might be removed
     * @param myRoster Roster which might contain the student who, if in it, is to be removed
     */
    private void R_Command(String firstName, String lastName, String DOB, Roster myRoster) {
        Profile profileToRemove = new Profile(lastName, firstName, new Date(DOB));
        if (myRoster!=null) {
            for (Student student : myRoster.getRoster()) {
                if (student != null) {
                    if (student.getProfile().equals(profileToRemove)) {
                        myRoster.remove(student);
                        System.out.println(profileToRemove.toString() + " removed from the roster.");
                        return;
                    }
                }
            }
            System.out.println(profileToRemove.toString() + " is not in the roster.");
        }
        System.out.println("Roster is empty.");
    }

    private void E_Command(String fname, String lname, String date, int creditsEnrolled, Enrollment myEnrollment, Roster myRoster) {
        Profile profile = new Profile(lname, fname, new Date(date));
        if (myRoster!=null) {
            for (Student student : myRoster.getRoster()) { //Check to see if student is in roster
                if (student != null) {
                    if (student.getProfile().equals(profile)) {
                        for (EnrollStudent enrollStudent : myEnrollment.getEnrollStudents()) { //Check to see if student is already enrolled
                            if (student != null) {
                                if (student.getProfile().equals(profile)) {
                                    enrollStudent.setCreditsEnrolled(creditsEnrolled);
                                    System.out.println(profile.toString() + " enrolled " + creditsEnrolled + " credits");
                                    return;
                                }
                            }
                        }
                        EnrollStudent enrollStudent = new EnrollStudent(profile, creditsEnrolled);
                        myEnrollment.add(enrollStudent);
                        System.out.println(profile.toString() + " enrolled " + creditsEnrolled + " credits");
                        return;
                    }
                }
            }
            System.out.println("Cannot enroll: " + profile.toString() + " is not in the roster.");
        }
        System.out.println("Enrollment is empty!");
    }

    private void D_Command(String fname, String lname, String date, Enrollment myEnrollment) {
        Profile profile = new Profile(lname, fname, new Date(date));
        if (myEnrollment!=null) {
            for (EnrollStudent student : myEnrollment.getEnrollStudents()) {
                if (student != null) {
                    if (student.getProfile().equals(profile)) {
                        myEnrollment.remove(student);
                        System.out.println(profile.toString() + " dropped.");
                        return;
                    }
                }
            }
            System.out.println(profile.toString() + " is not enrolled.");
        }
        System.out.println("Enrollment is empty!");
    }

    private void S_Command(String fname, String lname, String date, int scholarship, Enrollment myEnrollment, Roster myRoster) {

    }

    /**
     * List the students in a specified school, sorted by last name, first name, and DOB
     * @param school the specified school, case-insensitive, of which we want to list its enrollment
     * @param myRoster the roster containing all current students
     */
    private void L_Command(String school, Roster myRoster) {

        boolean validSchool = false;
        Roster tempRoster = new Roster();

        Major majorToList = Major.MATH;
        if (school.toUpperCase().equals("SOE")) {
            majorToList = Major.EE;
            validSchool = true;
        } else if (school.toUpperCase().equals("SC&I")) {
            majorToList = Major.ITI;
            validSchool = true;
        } else if (school.toUpperCase().equals("RBS")) {
            majorToList = Major.BAIT;
            validSchool = true;
        } else if (school.toUpperCase().equals("SAS")) {
            validSchool = true;
        }
        if (validSchool) {
            for (int k = 0; k < myRoster.getRoster().length; k++) {
                if (myRoster.getRoster()[k] != null) {
                    // SAS has both MATH and CS, so this if statement for CS is needed
                    // The MATH option runs in the second if statement as majorToList is set to this when initialized
                    if ((school.toUpperCase().equals("SAS")) && (myRoster.getRoster()[k].getMajor() == Major.CS)) {
                        tempRoster.add(myRoster.getRoster()[k]);
                    } else if (myRoster.getRoster()[k].getMajor() == majorToList) {
                        tempRoster.add(myRoster.getRoster()[k]);
                    }
                }

            }
            System.out.println("* Students in " + school + " *");
            tempRoster.print();
            System.out.println("* end of list **");
        } else {
            System.out.println("School doesn't exist: " + school);
        }
    }

    /**
     * Change the students major, first testing whether the major is valid and the student is in the roster.
     * @param firstName First name of the student who wants to change their major
     * @param lastName Last name of the student who wants to change their major
     * @param DOB Date of birth of the student who wants to change their major
     * @param majorChangingTo The major which the student wants to change to
     * @param myRoster The roster which might contain the student who wants to change their major
     */
    private void C_Command(String firstName, String lastName, String DOB, String majorChangingTo, Roster myRoster) {

        boolean isMajorInvalid = true;
        for (Major validMajor : Major.values()) {
            if (validMajor.name().equals(majorChangingTo.toUpperCase())) {
                isMajorInvalid = false;
                Profile profileOfStudent = new Profile(lastName, firstName, new Date(DOB));
                if (myRoster.contains(new Student (profileOfStudent,validMajor,1))) {
                    System.out.println(profileOfStudent.toString() + " major changed to " + majorChangingTo);
                } else {
                    System.out.println(profileOfStudent.toString() + " is not in the roster.");
                }
                break;
            }
        }

        if (isMajorInvalid) {
            System.out.println("Major code invalid: " + majorChangingTo);
        }
    }

    private void PE_Command(Enrollment myEnrollment) {
        if (myEnrollment == null || myEnrollment.getEnrollStudents() == null) {
            System.out.println("Enrollment is empty!");
            return;
        }
        myEnrollment.print();
    }

    private void PT_Command() {

    }

    /**
     * Display the roster in various sortings; this is the method for P, PS, and PC
     * @param whichP a String of either "P", "PS", or "PC"
     * @param myRoster the Roster we want to print
     */
    private void P_Command(String whichP, Roster myRoster) {

        if (myRoster == null || myRoster.getRoster() == null) {
            System.out.println("Student roster is empty!");
        }
        else if (myRoster.getRoster()[0] == null) { //All students in an already established Student[] have been removed
            System.out.println("Student roster is empty!");
        } else {
            if (whichP.equals("P")) {
                System.out.println("* Student roster sorted by last name, first name, DOB **");
                myRoster.print();
            } else if (whichP.equals("PS")) {
                System.out.println("* Student roster sorted by standing **");
                myRoster.printByStanding();
            } else {
                System.out.println("* Student roster sorted by school, major **");
                myRoster.printBySchoolMajor();
            }
            System.out.println("* end of roster **");
        }
    }

    private void SE_Command() {

    }

    public void run() throws FileNotFoundException {
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
                    LS_Command(file, myRoster);
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
                case "R":
                    R_Command(input[1],input[2],input[3],myRoster);
                    break;
                case "E":
                    E_Command(input[1], input[2], input[3], Integer.parseInt(input[5]), myEnrollment, myRoster);
                    break;
                case "D":
                    D_Command(input[1], input[2], input[3], myEnrollment);
                    break;
                case "S":
                    S_Command(input[1], input[2], input[3], Integer.parseInt(input[4]), myEnrollment, myRoster);
                    break;
                case "P":
                case "PS":
                case "PC":
                    P_Command(input[0],myRoster);
                    break;
                case "L":
                    L_Command(input[1],myRoster);
                    break;
                case "C":
                    C_Command(input[1],input[2],input[3],input[4],myRoster);
                    break;
                case "PE":
                    PE_Command(myEnrollment);
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
