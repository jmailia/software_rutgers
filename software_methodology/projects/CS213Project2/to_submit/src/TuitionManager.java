/* @author Henry Hecht */
/* @author Aidan Cronin */

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.Scanner;

public class TuitionManager {

    public static final DecimalFormat df = new DecimalFormat( "#.00" ); // Decimal format for PT_Command

    /**
     * Load the student roster from an external file of which each line is an instance of Student.
     * @param file     The external file which contains students of varying statuses.
     * @param myRoster The roster which we will want to add them to.
     */
    private void LS_Command(Scanner file, Roster myRoster) {
        while (file.hasNextLine()) {
            String line = file.nextLine();
            String[] lineInputs = line.split(",");
            boolean isLS = true;
            A_Command_ParseArguments(lineInputs, myRoster, isLS);
        }
        file.close();
        System.out.println("Students loaded to the roster.");
    }

    /**
     * Add a student to the roster, but only if the student's provided information is valid and eligible
     * @param typeOfStudent is "R" for Resident, "N" for NonResident, "T" for Tristate, "I" for International
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
            case "I":
                return (new International(profile, major, creditsCompleted, isStudyingAbroad));
            case "T":
                return (new TriState(profile, major, creditsCompleted, whichTriState));
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
        if ((input[0] == "AT" || input[0] == "T") && input.length > 5) { //if tristate student
            A_Command(input[0], input[1],input[2], new Date(input[3]),  input[4], input[5], myRoster, false, input[6], isLS);
        } else if ((input[0] == "AI" || input[0] == "I") && input.length > 5 ){ //otherwise, international student
            A_Command(input[0], input[1],input[2], new Date(input[3]),  input[4], input[5], myRoster, Boolean.parseBoolean(input[6]), "", isLS);
        } else if (input.length > 5) { //otherwise, resident/nonresident student
            A_Command(input[0], input[1], input[2], new Date(input[3]), input[4], input[5], myRoster, false, "", isLS);
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

    private void A_Command(String typeOfStudent, String fname, String lname, Date date, String major, String creditsCompleted, Roster myRoster, boolean isStudyingAbroad, String whichTriState, boolean isLS) {

        boolean isValidAddition = true; //boolean to determine if student meets requirements to be added

        //The student is not added to the roster given the following conditions:

        // The major does not exist
        isValidAddition = isMajorValid(major, isValidAddition);

        // The number of credits completed is invalid
        isValidAddition = (isValidAddition ? isCreditsCompletedValid(creditsCompleted, isValidAddition) : false);
        // The DOB is invalid
        isValidAddition = (isValidAddition ? isDOBValid(date) : false);

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
        }
        System.out.println("Roster is empty."); // Roster is empty
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
            if (myRoster != null) {
                for (Student student : myRoster.getRoster()) { //Check to see if student is in roster
                    if (student != null) {
                        if (student.getProfile().equals(profile)) {
                            if (myEnrollment.getEnrollStudents() != null) {
                                for (EnrollStudent enrollStudent : myEnrollment.getEnrollStudents()) { //Check to see if student is already enrolled
                                    if (enrollStudent != null) {
                                        if (student.getProfile().equals(profile)) {
                                            if(!student.isInternational()){ //TODO: NOT COMPLETELY FIXED!
                                                if(creditsEnrolled > 24 || creditsEnrolled < 3){
                                                    System.out.println(student.getClass() + " " + creditsEnrolled + ": invalid credit hours.");
                                                    return;
                                                }
                                            }
                                            enrollStudent.setCreditsEnrolled(creditsEnrolled);
                                            System.out.println(profile.toString() + " enrolled " + creditsEnrolled + " credits");
                                            return;
                                        }
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
                return;
            }
            System.out.println("Enrollment is empty!");
            return;
        }
        System.out.println("Credits enrolled is not an integer.");
    }

    /**
     * Method searches the enrollment for a particular
     * student that is to be dropped.
     * @param fname First name of student
     * @param lname Last name of student
     * @param date Date of birth of student
     * @param myEnrollment Enrollment that we want to drop from
     */
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

    /**
     * Method g
     * @param fname First name of student
     * @param lname Last name of student
     * @param date Date of birth of student
     * @param scholarship Scholarship amount
     * @param myEnrollment Enrollment we want to access
     * @param myRoster Roster we want to iterate through
     */
    private void S_Command(String fname, String lname, String date, int scholarship, Enrollment myEnrollment,  Roster myRoster) {
        Profile profile = new Profile(lname, fname, new Date(date));
        if(scholarship <= 0 || scholarship > 10000){
            System.out.println(scholarship + ": invalid amount.");
            return;
        }
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
                                                student.setScholarship(scholarship);
                                                System.out.println(profile.toString() + ": scholarship amount updated.");
                                            }
                                            System.out.println(student.getProfile().toString() + " part time student " +
                                                    "is not eligible for the scholarship."); // Student is parttime
                                            return;
                                        }
                                        System.out.println(student.getProfile().toString() + "(" + student.getClass() + ")"
                                                + "is not eligible for the scholarship."); // Student is not a resident
                                        return;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println(profile.toString() + " is not in the roster"); // Not in roster
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

    /**
     * Method prints out a list of the enrolled students based
     * on the order of the array.
     * @param myEnrollment The Enrollment that we want to print
     */
    private void PE_Command(Enrollment myEnrollment) {
        if (myEnrollment == null || myEnrollment.getEnrollStudents() == null) {
            System.out.println("Enrollment is empty!");
            return;
        }
        myEnrollment.print();
    }

    /**
     * Method prints out enrollment based on order of array and displays the tuition due
     * based on the amount of enrolled credits they are taking and their student type.
     * @param myEnrollment
     */
    private void PT_Command(Enrollment myEnrollment, Roster myRoster) {
        if (myEnrollment == null || myEnrollment.getEnrollStudents() == null) {
            System.out.println("Enrollment is empty!");
            return;
        }
        System.out.println("** Tuition due **");
        if (myRoster != null) {
            for (Student student : myRoster.getRoster()) {
                if (student != null) {
                    if (myEnrollment != null) {
                        for (EnrollStudent enrollStudent : myEnrollment.getEnrollStudents()) {
                            if (enrollStudent != null) {
                                if (enrollStudent.getProfile().equals(student.getProfile())) {
                                    double tuitionDue = student.tuitionDue(enrollStudent.getCreditsEnrolled());
                                    System.out.println(student.getProfile().toString() + "(" + student.getClass() + ")" +
                                            " enrolled " + enrollStudent.getCreditsEnrolled() + " credits: tuition due: $" +
                                            df.format(tuitionDue));
                                }
                            }
                        }
                    }
                }
            }
        }
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
     * Command ends the semester and adds all the enrolled students credits to their object in
     * the roster. The students who are then eligible to graduate are then printed.
     * @param myEnrollment the Enrollment we want to iterate through
     * @param myRoster the Roster we want to iterate through
     */
    private void SE_Command(Enrollment myEnrollment, Roster myRoster) {
        if (myEnrollment == null || myEnrollment.getEnrollStudents() == null) { // Check if enrollment is empty
            System.out.println("Enrollment is empty!");
            return;
        }
        if (myRoster == null || myRoster.getRoster() == null) { // Check if roster is empty
            System.out.println("Roster is empty!");
            return;
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


        System.out.println("** list of students eligible for graduation **"); // Printing out list of students eligible for graduation
        for(Student student : myRoster.getRoster()) {
            if(student.getCreditCompleted() >= 120){
                System.out.println(student.toString() + "(" + student.getClass() + ")");
            }
        }
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
                case "AN":
                case "AT":
                case "AI":
                    A_Command_ParseArguments(input, myRoster, false);
                    break;
                case "R":
                    R_Command(input[1],input[2],input[3],myRoster);
                    break;
                case "E":
                    E_Command(input, myEnrollment, myRoster);
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
                    PT_Command(myEnrollment, myRoster);
                    break;
                case "SE":
                    SE_Command(myEnrollment, myRoster);
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
