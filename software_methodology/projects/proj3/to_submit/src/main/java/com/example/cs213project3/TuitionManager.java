package com.example.cs213project3;/* @author Henry Hecht */
/* @author Aidan Cronin */

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * Processes line commands, with new functionality for processing tuitions based on the enrollment of the semester
 */
public class TuitionManager {

    /**
     * Decimal format for PT_Command
     */
    public static final DecimalFormat df = new DecimalFormat( "##,###.00" );

    /**
     * Load the student roster from an external file of which each line is an instance of Student.
     * @param fileString The external file which contains students of varying statuses.
     * @param myRoster The roster which we will want to add them to.
     */
    private void LS_Command(String fileString, Roster myRoster) throws FileNotFoundException {
        Scanner file = new Scanner(new File(fileString));
        while (file.hasNextLine()) {
            A_Command_ParseArguments(file.nextLine().split(","), myRoster, true);
        }
        file.close();
        System.out.println("Students loaded to the roster.");
    }

    /**
     * Add a student to the roster, but only if the student's provided information is valid and eligible
     * @param typeOfStudent is "(A)R" for Resident, "(A)N" for NonResident,
     *                      "(A)T" for Tristate, "(A)I" for International
     * @param profile The profile of the student containing their first name, last name, and DOB
     * @param major The major of the student to be added; major is NOT case-sensitive
     * @param creditsCompleted The number of credits completed by the student to be added;
     *                         inputted as a string in case it is not an integer
     * @param isStudyingAbroad true if student is studying abroad as an international student, false otherwise
     * @param whichTriState a potential abbreviation for a tristate student, NY/CT
     */
    private Student createCorrectStudentInstance(String typeOfStudent, Profile profile, Major major,
                                                 int creditsCompleted, boolean isStudyingAbroad, String whichTriState){
        //TODO: I am not sure if this is correct, but it appears to work because res/nonres/intnl/trist is an extension of Student (the type which is returned as in the function definition)? Hope this makes sense.
        switch(typeOfStudent) {
            case "AI":
            case "I":
                return (new International(profile, major, creditsCompleted, isStudyingAbroad));
            case "AT":
            case "T":
                return (new TriState(profile, major, creditsCompleted, whichTriState.toUpperCase()));
            case "AN":
            case "N":
                return (new NonResident(profile, major, creditsCompleted));
            default: //is resident
                return (new Resident(profile, major, creditsCompleted));
        }
    }
    /**
     * This helper method parses the arguments for the A_Command; this is needed as
     * @param input the arguments of the user when they are adding a student
     * @param myRoster the roster which we want to add the student to
     */
    private void A_Command_ParseArguments(String[] input, Roster myRoster, boolean isLS){

        if ((input[0].equals("AT") || input[0].equals("T")) && input.length > 5) { //if tristate student
            if (input.length == 6) {
                System.out.println("Missing the state code.");
                return;
            }
            A_Command(input[0], input[1], input[2], new Date(input[3]),  input[4], input[5],
                    myRoster, false, input[6], isLS);
        } else if ((input[0].equals("AI") || input[0].equals("I")) && input.length > 5 ){ //otherwise, international student
            // if there is no seventh argument for an international student, assume false (for study abroad)
            A_Command(input[0], input[1], input[2], new Date(input[3]),  input[4], input[5],
                    myRoster, (input.length != 6) ? Boolean.parseBoolean(input[6]) : false,
            "", isLS);
        } else if (input.length > 5) { //otherwise, resident/nonresident student
            A_Command(input[0], input[1], input[2], new Date(input[3]), input[4], input[5],
                    myRoster, false, "", isLS);
        }
        else {
            System.out.println("Missing data in line command.");
        }
    }


    /**
     * Helper method for A_Command which checks if student is in the roster already
     * @param myRoster the roster which the student might already be part of
     * @param myStudent the student which we want to add to the roster, if they are not part of it already
     * @return the roster which the student had wanted to be added to
     */
    private Roster checkIfStudentInRosterAlready (Roster myRoster, Student myStudent, boolean isLS) {
        if (myRoster.contains(myStudent)) {
            System.out.println(myStudent.getProfile().toString() + " is already in the roster.");
        } else {
            // If the student meets the requirements to be added
            myRoster.add(myStudent);
            if(!isLS) {
                System.out.println(myStudent.printStudentProfile() + " added to the roster.");
            }
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
     * Helper method for A_Command which checks if the inputted state is valid
     * @param state the string the user has inputted which might be a valid state
     * @return true if the state is valid and, subsequently, the student still potentially meets
     * all requirements; otherwise false
     */
    private boolean isStateValid(String state) {
        if (state.toUpperCase().equals("CT") || state.toUpperCase().equals("NY")){
            return true;
        }
        System.out.println(state + ": Invalid state code.");
        return false;
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
     * @param isLS true if the command was LS, false if the command was an A*
     */

    private void A_Command(String typeOfStudent, String fname, String lname, Date date, String major,
                           String creditsCompleted, Roster myRoster, boolean isStudyingAbroad,
                           String whichTriState, boolean isLS) {

        boolean isValidAddition = true; //boolean to determine if student meets requirements to be added

        //The student is not added to the roster given the following conditions:

        // The major does not exist
        isValidAddition = isMajorValid(major, isValidAddition);

        // The number of credits completed is invalid
        isValidAddition = (isValidAddition ? isCreditsCompletedValid(creditsCompleted, isValidAddition) : false);
        // The DOB is invalid
        isValidAddition = (isValidAddition ? isDOBValid(date) : false);
        // The state is invalid (for tri-state individuals)
        isValidAddition = (isValidAddition ? (whichTriState=="" ? isValidAddition : isStateValid(whichTriState)) : false);

        // If the student remains a valid addition, check to see if student is in the roster already;
        // if they are not, add the student
        myRoster = (isValidAddition ? checkIfStudentInRosterAlready(myRoster,
                createCorrectStudentInstance(typeOfStudent,
                        new Profile(lname, fname, date), Major.valueOf(major.toUpperCase()),
                        Integer.parseInt(creditsCompleted), isStudyingAbroad, whichTriState), isLS): myRoster);
    }

    /**
     * Removes a student from Roster, but only if they are already in the Roster
     * @param firstName First name of the student who might be removed
     * @param lastName Last name of the student who might be removed
     * @param DOB DOB of the student who might be removed
     * @param myRoster Roster which might contain the student who, if in it, is to be removed
     */
    private void R_Command(String firstName, String lastName, String DOB, Roster myRoster) {
        Profile profileToRemove = new Profile(lastName, firstName, new Date(DOB)); // Create profile with inputted information
        if (myRoster!=null) {
            for (Student student : myRoster.getRoster()) { // Search through roster to find student that will be removed.
                if (student != null) {
                    if (student.getProfile().equals(profileToRemove)) {
                        myRoster.remove(student); // Remove student
                        System.out.println(profileToRemove.toString() + " removed from the roster.");
                        return;
                    }
                }
            }
            System.out.println(profileToRemove.toString() + " is not in the roster."); // Not in roster
        } else {
            System.out.println("Roster is empty."); // Roster is empty
        }
    }

    /**
     * Checks to see if a student has the correct credit count for enrollment; all students must between 3-24 credits,
     * but international students not studying abroad need 12-24 credits,
     * while international students studying abroad need 3-12 credits.
     * @param creditsEnrolled The credits which the student wants to enroll with which may not be valid
     * @param student The student awaiting enrollment
     * @return true if the credits allows for enrollment, false otherwise
     */
    private boolean checkCreditsForEnrollment(int creditsEnrolled, Student student) {
        //all students between 3-24 credits,
        //international student not studying abroad needs 12-24 credits.
        //international student studying abroad needs 3-12 credits,
        if(creditsEnrolled > 24 || creditsEnrolled < 3 ||
                (student.isInternational() &&
                        ((!student.isStudyAbroad() && creditsEnrolled < 12) ||
                                (student.isStudyAbroad() && creditsEnrolled > 12)))) {

            System.out.println(printParenthesizedStudents(student,false) + " " +
                    creditsEnrolled + ": invalid credit hours.");

            return false;
        }
        System.out.println(student.getProfile().toString() + " enrolled " + creditsEnrolled + " credits");
        return true;
    }

    /**
     * Returns a print-ready string of the student's residence
     * @param student The student which we want to print
     * @param printColon whether the colon in tristate should be printed; true for yes, false for no
     * @return String of either (Resident), (Non-Resident),(Tri-state), (International student) with correct variants
     */
    private String printParenthesizedStudents(Student student, boolean printColon){
        return (student.isResident() ? "(Resident)" :
                (!student.isTriState() && !student.isInternational() ? "(Non-Resident)" :
                        (student.isTriState() ? ("(Tri-state" + (printColon?":":" ") + student.whichTristate() + ")") :
                                (student.isInternational() ? "(International student" +
                                        (student.isStudyAbroad() ? "study abroad)" : ")") : ""))));
    }




    /**
     * Method adds a given student into myEnrollment. If a student is already enrolled
     * then it will just change the currently enrolled credits. Method will not add to
     * enrollment if student is not in roster.
     * @param input Input
     * @param myEnrollment Enrollment that we want to add to
     * @param myRoster Roster that we need to iterate through
     */
    private void E_Command(String[] input, Enrollment myEnrollment, Roster myRoster) {
        if (input.length < 5) {
            System.out.println("Missing data in line command.");
            return;
        }
        if (input[4] != null && input[4].matches("[-+]?\\d*\\.?\\d+")) {
            int creditsEnrolled = Integer.parseInt(input[4]);
            Profile profile = new Profile(input[2], input[1], new Date(input[3]));
            // check roster to see if student can be enrolled
            if (myRoster!=null) {
                boolean isStudentInRoster = false;
                //Check to see if student is in roster
                Student studentToBeEnrolled = myRoster.getRoster()[0];
                for (Student student : myRoster.getRoster()) {
                    if (student!=null) {
                        if (student.getProfile().equals(profile)) {
                            isStudentInRoster = true;
                            studentToBeEnrolled = student;
                        }
                    }
                }
                if (isStudentInRoster){
                    EnrollStudent toBeEnrolled = new EnrollStudent(profile, creditsEnrolled);
                    if (myEnrollment.contains(toBeEnrolled)) {
                        if (checkCreditsForEnrollment(creditsEnrolled, studentToBeEnrolled)) {
                            myEnrollment.getEnrollStudents()[myEnrollment.find(toBeEnrolled)].
                                    setCreditsEnrolled(creditsEnrolled);
                            return;
                        }
                        return;
                    }
                    if (checkCreditsForEnrollment(creditsEnrolled, studentToBeEnrolled)) {
                        myEnrollment.add(toBeEnrolled);
                        return;
                    }
                    return;
                }
                System.out.println("Cannot enroll: " + profile.toString() + " is not in the roster."); return;
            }
            System.out.println("Roster is empty!"); return;
        }
        System.out.println("Credits enrolled is not an integer."); return;
    }

    /**
     * Method searches the enrollment for a particular
     * student that is to be dropped.
     * @param profile profile of student
     * @param myEnrollment Enrollment that we want to drop from
     */
    private void D_Command(Profile profile, Enrollment myEnrollment) {
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
            System.out.println(profile.toString() + " is not enrolled."); return;
        }
        System.out.println("Enrollment is empty!");
    }

    /**
     * Determines whether the scholarship is a valid amount
     * @param scholarship the amount of money asked for in the scholarship
     * @return true if the scholarship is not a valid monetary value, false otherwise
     */
    private boolean invalidScholarshipAmount (int scholarship) {
        if(scholarship <= 0 || scholarship > 10000){
            System.out.println(scholarship + ": invalid amount.");
            return true;
        }
        return false;
    }


    /**
     * Method g
     * @param input Input from user in terminal
     * @param myEnrollment Enrollment we want to access
     * @param myRoster Roster we want to iterate through
     */
    private void S_Command(String[] input, Enrollment myEnrollment,  Roster myRoster) {
        if (input.length < 3) {
            System.out.println("Missing data in line command."); return;
        }
        Profile profile = new Profile(input[2], input[1], new Date(input[3]));
        if (!myRoster.contains(new Resident (profile,Major.CS,10))) {
            System.out.println(profile.toString() + " is not in the roster."); return; // Not in roster
        }
        if (input[4] != null && input[4].matches("[-+]?\\d*\\.?\\d+")) {
            if (invalidScholarshipAmount(Integer.parseInt(input[4]))){return;}
            if (myRoster != null) {
                for (Student student : myRoster.getRoster()) { // Get access to student we are looking for in roster
                    if (student != null) {
                        if (student.getProfile().equals(profile)) {
                            if (myEnrollment != null) {
                                for (EnrollStudent enrollStudent : myEnrollment.getEnrollStudents()) { // Get access to student we are looking for in enrollment
                                    if (enrollStudent != null) {
                                        if (enrollStudent.getProfile().equals(profile)) {
                                            if (student.isResident()) {
                                                if (enrollStudent.getCreditsEnrolled() >= 12) { // Check if fulltime
                                                    student.setScholarship(Integer.parseInt(input[4]));
                                                    System.out.println(profile.toString() + ": scholarship amount updated."); return;
                                                } else { //Student is parttime
                                                    System.out.println(student.getProfile().toString() + " part time student " +
                                                            "is not eligible for the scholarship."); return;
                                                }
                                            }
                                            System.out.println(student.getProfile().toString() + " " + //Student is not a resident
                                                    printParenthesizedStudents(student,false) +
                                                    " is not eligible for the scholarship."); return;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println("Amount is not an integer.");
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

        if (myRoster == null) {
            System.out.println("Roster is empty!");
            return;
        }
        Profile profileOfStudent = new Profile(lastName, firstName, new Date(DOB));
        boolean isMajorInvalid = true;
        for (Major validMajor : Major.values()) {
            if (validMajor.name().equals(majorChangingTo.toUpperCase())) {
                isMajorInvalid = false;
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
        }
        if (isMajorInvalid) {
            System.out.println("Major code invalid: " + majorChangingTo);
        }
    }

    /**
     * Method prints out a list of the enrolled students based
     * on the order of the array.
     * @param myEnrollment The Enrollment that we want to print
     */
    private void PE_Command(Enrollment myEnrollment) {
        //Enrollment is empty OR students in an already established enrollStudents[] have been removed
        if (myEnrollment == null || myEnrollment.getEnrollStudents() == null || myEnrollment.getEnrollStudents()[0] == null) {
            System.out.println("Enrollment is empty!");
        } else {
            myEnrollment.print();
        }
    }

    /**
     * Method prints out enrollment based on order of array and displays the tuition due
     * based on the amount of enrolled credits they are taking and their student type.
     * @param myEnrollment
     */
    private void PT_Command(Enrollment myEnrollment, Roster myRoster) {
        if (myRoster == null || myEnrollment == null || myEnrollment.getEnrollStudents() == null){
            System.out.println("Student roster is empty!");
            return;
        }
        System.out.println("** Tuition due **");
        if (myEnrollment != null) {
            for (EnrollStudent enrollStudent : myEnrollment.getEnrollStudents()) {
                if (enrollStudent != null) {
                    if (myRoster != null) {
                        for (Student student : myRoster.getRoster()) {
                            if (student != null) {
                                if (enrollStudent.getProfile().equals(student.getProfile())) {
                                    System.out.println(student.getProfile().toString() + " " +
                                            printParenthesizedStudents(student,false) +
                                            " enrolled " + enrollStudent.getCreditsEnrolled() +
                                            " credits: tuition due: $" +
                                            df.format(student.tuitionDue(enrollStudent.getCreditsEnrolled())));
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println("* end of tuition due *");
    }

    /**
     * Display the roster in various sortings; this is the method for P, PS, and PC
     * @param whichP a String of either "P", "PS", or "PC"
     * @param myRoster the Roster we want to print
     */
    private void P_Command(String whichP, Roster myRoster, Enrollment myEnrollment) {
        if (whichP.equals("PE")) {
            PE_Command(myEnrollment);
        } else if (whichP.equals("PT")) {
            PT_Command(myEnrollment, myRoster);
        } else if (myRoster == null || myRoster.getRoster() == null || myRoster.getRoster()[0] == null) {
            System.out.println("Student roster is empty!");
            //Roster is empty/null or all students in an already established Student[] have been removed
        } else {
            if (whichP.equals("P")) {
                System.out.println("** Student roster sorted by last name, first name, DOB **");
                myRoster.print();
            } else if (whichP.equals("PS")) {
                System.out.println("** Student roster sorted by standing **");
                myRoster.printByStanding();
            } else {
                System.out.println("** Student roster sorted by school, major **");
                myRoster.printBySchoolMajor();
            }
            System.out.println("* end of roster *");
        }
    }


    /**
     * Command ends the semester and adds all the enrolled students credits to their object in
     * the roster. The students who are then eligible to graduate are then printed.
     * @param myEnrollment the Enrollment we want to iterate through
     * @param myRoster the Roster we want to iterate through
     */
    private boolean SE_Command(Enrollment myEnrollment, Roster myRoster) {
        if (myEnrollment == null || myEnrollment.getEnrollStudents() == null) { // Check if enrollment is empty
            System.out.println("Enrollment is empty!"); return false;
        }
        if (myRoster == null || myRoster.getRoster() == null) { // Check if roster is empty
            System.out.println("Roster is empty!"); return false;
        }
        if (myRoster != null) {
            for (Student student : myRoster.getRoster()) {
                if (student != null) {
                    if (myEnrollment != null) {
                        for (EnrollStudent enrollStudent : myEnrollment.getEnrollStudents()) {
                            if (enrollStudent != null) {
                                if (enrollStudent.getProfile().equals(student.getProfile())) {
                                    student.setCreditCompleted(student.getCreditCompleted() + enrollStudent.getCreditsEnrolled()); // Set new creditCompleted by adding current credits and enrolled credits
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println("Credit completed has been updated.");
        myRoster.printCanGraduate();
        return true;
    }


    /**
     * Updates a boolean array which holds information regarding whether the user's command is their first or last
     * @param commandTimes a boolean array of size 3, such that the boolean value at
     *                                the 0th index corresponds to whether the current command is the first command (1);
     *                                the 1st index corresponds to whether an SE command has been executed already (SE);
     *                                the 1st index corresponds to whether the Xth command is the last command (X).
     * @return commandTimes
     */
    private boolean[] commandTimes(boolean[] commandTimes){
        if (commandTimes[0]) {
            System.out.println("Tuition Manager running...\n");    //user knows software is ready for commands
            commandTimes[0] = false;
        }
        return commandTimes;
    }

    /**
     * Continuously processes the line commands until the 'Q' command is entered
     * @throws FileNotFoundException signals that an attempt to open the file denoted by
     * a specified pathname (namely for LS) has failed
     */
    public void run() throws FileNotFoundException {
        boolean[] commandTimes = {true, false, false};
        Enrollment myEnrollment = new Enrollment();
        Roster myRoster = new Roster();
        Scanner sc = new Scanner(System.in);
        while(! (commandTimes[2] && sc.hasNextLine())) {   //continuously read the line commands until the user quits
            String[] input = sc.nextLine().split("\\s+"); //parses arguments
            commandTimes = commandTimes(commandTimes);
            if(input[0] == "") {
                commandTimes[2] = false; //if the enter key is pressed without any input, this prevents an error
            } else if (input[0].equals("LS")) {
                LS_Command(input[1], myRoster);
            } else if (input[0].charAt(0) == 'A') {
                A_Command_ParseArguments(input, myRoster, false);
            } else if (input[0].equals("C")) {
                C_Command(input[1], input[2], input[3], input[4], myRoster);
            } else if (input[0].equals("SE") && !commandTimes[1]) { //SE Command
                commandTimes[1] = SE_Command(myEnrollment, myRoster);
            } else if (input[0].equals("S")){
                S_Command(input,myEnrollment,myRoster);
            } else if (input[0].equals("E")){
                E_Command(input, myEnrollment, myRoster);
            } else if (input[0].equals("D")){
                D_Command(new Profile(input[2],input[1],new Date(input[3])),myEnrollment);
            } else if (input[0].equals("L")) {
                L_Command(input[1], myRoster);
            } else if (input[0].charAt(0) == 'P'){
                P_Command(input[0], myRoster, myEnrollment);
            } else if (input[0].equals("R")) {
                R_Command(input[1], input[2], input[3], myRoster);
            } else if (input[0].equals("Q")) {
                System.out.println("Tuition Manager terminated.");    //user has terminated the software normally
                commandTimes[2] = true;
            } else {
                System.out.println(input[0] + " is an invalid command!");
            }
        }
        sc.close();
    }
}
