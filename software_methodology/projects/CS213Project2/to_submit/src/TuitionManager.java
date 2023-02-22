/* @author Henry Hecht */
/* @author Aidan Cronin */

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * The User Interface class to process the line commands entered on the console and display the results of the console
 */
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



    /**
     * Helper method for A_Command which checks if student is in the roster already
     * @param myRoster the roster which the student might already be part of
     * @param myResident the student which we want to add to the roster, if they are not part of it already
     * @return the roster which the student had wanted to be added to
     */
    private Roster checkIfStudentInRosterAlready (Roster myRoster, Resident myResident) {
        if (myRoster.contains(myResident)) {
            System.out.println(myResident.getProfile().toString() + " is already in the roster.");
        } else {
            // If the student meets the requirements to be added
            myRoster.add(myResident);
            System.out.println(myResident.printStudentProfile() + " added to the roster.");
        }
        return myRoster;
    }

    /**
     * Helper method for A_Command which checks if the DOB is valid
     * @param myDate the DOB which may be valid
     * @return true if the DOB is valid and, subsequently, the student still potentially meets all requirements; otherwise false
     */
    private boolean isDOBValid (Date myDate) {
        //Any date of birth that is not a valid calendar date
        if (!myDate.isCalendarDateValid()) {
            System.out.println("DOB invalid: " + myDate + " not a valid calendar date!");
            return false;
        }

        // A student who is less than 16 years old
        //Any date of birth that is today or a future date
        // (NOTE: there is no example of today/future the sample output,
        // but it is required by the written instructions; I use the same error message)
        if (!myDate.isStudentOver16()) {
            System.out.println("DOB invalid: " + myDate + " younger than 16 years old.");
            return false;
        }
        return true;
    }

    /**
     * Helper method for A_Command which checks if the number of credits completed is valid
     * @param creditsCompleted the number of credits completed which may be valid
     * @param isValidAddition is true if the credits completed is valid and, subsequently, the student still potentially meets all requirements; otherwise false
     * @return isValidAddition is true if the credits completed is valid and, subsequently, the student still potentially meets all requirements; otherwise false
     */
    private boolean isCreditsCompletedValid(String creditsCompleted, boolean isValidAddition) {
        int numberOfCredits = 0;
        try {
            numberOfCredits = Integer.parseInt(creditsCompleted);
            // Negative number of credits completed
            if ((numberOfCredits!=0 && numberOfCredits != +0) && (numberOfCredits < 0 || numberOfCredits == -0)) {
                System.out.println("Credits completed invalid: cannot be negative!");
                isValidAddition = false;
            }
        } catch (NumberFormatException e) {
            // Non-integer inputted for of credits completed
            System.out.println("Credits completed invalid: not an integer!");
            isValidAddition = false;
        }
        return isValidAddition;
    }


    /**
     * Helper method for A_Command which checks if the inputted major is valid
     * @param major the given string which might be a valid major for the student
     * @param isValidAddition is true if the major is valid and, subsequently, the student still potentially meets all requirements; otherwise false
     * @return isValidAddition is true if the major is valid and, subsequently, the student still potentially meets all requirements; otherwise false
     */
    private boolean isMajorValid(String major, boolean isValidAddition) {
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
        return isValidAddition;
    }

    /**
     * This helper method parses the arguments for the A_Command; this is needed as
     * @param input the arguments of the user when they are adding a student
     * @param myRoster the roster which we want to add the student to
     */
    private void A_Command_ParseArguments(String[] input, Roster myRoster){
        if (input.length > 6) {
            //if tristate student
            if (input[6] == "NY" || input[6] == "NJ" || input[6] == "CT"){
                A_Command(input[0], input[1],input[2], new Date(input[3]),  input[4], input[5], myRoster, false, input[6]);
            } else { //otherwise, international student
                A_Command(input[0], input[1],input[2], new Date(input[3]),  input[4], input[5], myRoster, Boolean.parseBoolean(input[6]), "");
            }
        } else { //otherwise, resident/nonresident student
            A_Command(input[0], input[1], input[2], new Date(input[3]), input[4], input[5], myRoster, false, "");
        }
    }


    /**
     * Add a student to the roster, but only if the student's provided information is valid and eligible
     * @param typeOfStudent is "R" for Resident, "N" for NonResident, "T" for Tristate, "I" for International
     * @param fname The first name of the student to be added; names are NOT case-sensitive
     * @param lname The last name of the student to be added; names are NOT case-sensitive
     * @param date The date of birth of the student to be added; given in mm/dd/yyyy format
     * @param major The major of the student to be added; major is NOT case-sensitive
     * @param creditsCompleted The number of credits completed by the student to be added;
     *                         inputted as a string in case it is not an integer
     * @param myRoster The roster to which the student might be added, but only under specific conditions
     * @param isStudyingAbroad true if student is studying abroad as an international student, false otherwise
     * @param whichTriState a potential abbreviation for a tristate student, NY/NJ/CT
     */

    private void A_Command(String typeOfStudent, String fname, String lname, Date date, String major, String creditsCompleted, Roster myRoster, boolean isStudyingAbroad, String whichTriState) {

        boolean isValidAddition = true; //boolean to determine if student meets requirements to be added

        //The student is not added to the roster given the following conditions:

        // The major does not exist
        isValidAddition = isMajorValid(major, isValidAddition);

        // The number of credits completed is invalid
        isValidAddition = (isValidAddition ? isCreditsCompletedValid(creditsCompleted, isValidAddition) : false);
        // The DOB is invalid
        isValidAddition = (isValidAddition ? isDOBValid(date) : false);
        // The student is in the roster already, otherwise add the student
        myRoster =  (isValidAddition ? checkIfStudentInRosterAlready(myRoster, new Resident(
                new Profile(lname, fname, date), Major.valueOf(major.toUpperCase()), Integer.parseInt(creditsCompleted))) : myRoster);
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

    /**
     * Change the students major, first testing whether the major is valid and the student is in the roster.
     * @param firstName First name of the student who wants to change their major
     * @param lastName Last name of the student who wants to change their major
     * @param DOB Date of birth of the student who wants to change their major
     * @param majorChangingTo The major which the student wants to change to
     * @param myRoster The roster which might contain the student who wants to change their major
     */
    private void C_Command(String firstName, String lastName, String DOB, String majorChangingTo, Roster myRoster) {
        Profile profileOfStudent = new Profile(lastName, firstName, new Date(DOB));
        boolean isMajorInvalid = true;
        for (Major validMajor : Major.values()) {
            if (validMajor.name().equals(majorChangingTo.toUpperCase())) {
                isMajorInvalid = false;
                if (myRoster != null) {
                    for (Student student : myRoster.getRoster()) { //Check to see if student is in roster
                        if (student != null) {
                            if (student.getProfile().equals(profileOfStudent)) {
                                student.setMajor(Major.valueOf(majorChangingTo.toUpperCase()));
                                System.out.println(profileOfStudent.toString() + " major changed to " + majorChangingTo);
                                return;
                            }
                        }
                    }
                    System.out.println(profileOfStudent.toString() + " is not in the roster.");
                }
                System.out.println("Roster is empty!");
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

    private void SE_Command() {

    }


    /**
     * Helper method that, when the user has inputted their first command, prints out "Tuition Manager running..."
     * @param firstTimeLastTime A boolean[] of size 2; index 0 keeps track of whether the user's command is their first, index 1 keeps track of whether the user's command is their last
     * @return firstTimeLastTime
     */
    private boolean[] ifFirstCommand(boolean[] firstTimeLastTime) {
        if (firstTimeLastTime[0]) {
            System.out.println("Tuition Manager running...\n");    //user knows software is ready for commands
            firstTimeLastTime[0] = false;
        }
        return firstTimeLastTime;
    }
    /**
     * Method that initiates the Roster and Scanner,
     * includes a while loop to continuously read the line commands until the user quits,
     * and calls private methods to handle the different commands
     */
    public void run() throws FileNotFoundException {
        boolean[] firstTimeLastTime = {true,false};
        Scanner file = new Scanner(new File("studentList.txt"));
        Enrollment myEnrollment = new Enrollment();
        Roster myRoster = new Roster();
        Scanner sc = new Scanner(System.in);
        while(! (firstTimeLastTime[1] && sc.hasNextLine())) {  //continuously read the line commands until the user quits
            String[] input = sc.nextLine().split("\\s+"); //parses arguments
            firstTimeLastTime = ifFirstCommand(firstTimeLastTime);
            switch (input[0]) {                                //get the current command\
                case "":        //if the enter key is pressed without any input, this prevents an error
                    break;
                case "LS":
                    LS_Command(file, myRoster);
                    break;
                case "AR":
                case "AN":
                case "AT":
                case "AI":
                    A_Command_ParseArguments(input, myRoster);
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
