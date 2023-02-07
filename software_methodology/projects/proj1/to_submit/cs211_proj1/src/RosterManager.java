/* @author Henry Hecht */
/* @author Aidan Cronin */

import java.util.Scanner;
public class RosterManager {

    /**
     *
     * @param firstName
     * @param lastName
     * @param DOB
     * @param major
     * @param creditsCompleted
     * @param myRoster
     */
    private void A_Command(String firstName, String lastName, String DOB, String major, String creditsCompleted, Roster myRoster) {

        //boolean to determine if student meets requirements to be added
        boolean isValidAddition = true;

        //The student is not added to the roster given the following conditions:

        // The major does not exist
        boolean isMajorValid = false;
        for (Major value : Major.values()) {
            if (value.name().equals(major.toUpperCase())) { //TODO: Is this equals() okay? I still dont know what is required
                isMajorValid = true;
            }
        }
        if (!isMajorValid) {
            System.out.println("Major code invalid: " + major);
            isValidAddition = false;
        }

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

        if (isValidAddition) {
            //create student profile
            Student myStudent = new Student(
                    new Profile(lastName, firstName, new Date(DOB)),
                    Major.valueOf(major.toUpperCase()),
                    Integer.parseInt(creditsCompleted));

            //Any date of birth that is not a valid calendar date
            if (!myStudent.getProfile().getDob().isCalendarDateValid()) {
                System.out.println("DOB invalid: " + myStudent.getProfile().getDob() + " not a valid calendar date!");
                isValidAddition = false;
            }

            // A student who is less than 16 years old
            //Any date of birth that is today or a future date
            // (NOTE: there is no example of today/future the sample output, but it is required by the written instructions)
            if (!myStudent.getProfile().getDob().isStudentOver16()) {
                System.out.println("DOB invalid: " + myStudent.getProfile().getDob() + " younger than 16 years old.");
                isValidAddition = false;
            }

            // The student is in the roster already
            if (myRoster.contains(myStudent)) {
                System.out.println(myStudent.getProfile().toString() + " is already in the roster.");
                isValidAddition = false;
            }
            // If the student meets the requirements to be added
            if (isValidAddition) {
                myRoster.add(myStudent);
                System.out.println(myStudent.printStudentProfile() + " added to the roster.");
            }
        }
    }

    /**
     * Removes a student from Roster
     * @param firstName First name of the student who might be removed
     * @param lastName Last name of the student who might be removed
     * @param DOB DOB of the student who might be removed
     * @param myRoster Roster which might contain the student who, if in it, is to be removed
     */
    private void R_Command(String firstName, String lastName, String DOB, Roster myRoster) {
        Student studentToRemove = new Student(new Profile(lastName, firstName, new Date(DOB)),
                Major.CS, 1);
        if (myRoster.contains(studentToRemove)) {
            myRoster.remove(studentToRemove);
            System.out.println(studentToRemove.getProfile().toString() + " removed from the roster.");
        } else {
            System.out.println(studentToRemove.getProfile().toString() + " is not in the roster.");
        }
    }

    /**
     * Helper method for P, PS, and PC
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
            //System.out.println(whichP); //TODO: REMOVE THIS
            if (whichP.equals("P")) {
                System.out.println("* Student roster sorted by last name, first name, DOB **");
                myRoster.print();
            } else if (whichP.equals("PS")) {
                //TODO: IMPORTANT PLEASE FIX:
                //TODO: Read my notes for PS. PS DOES NOT order by increasing credits but by (more generally) increasing standing. See the final TestOutput for PS for an example.
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
     *
     * @param school
     * @param myRoster
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
                    if (myRoster.getRoster()[k].getMajor() == Major.CS) {
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
     *
     * @param firstName
     * @param lastName
     * @param DOB
     * @param majorChangingTo
     * @param myRoster
     */
    private void C_Command(String firstName, String lastName, String DOB, String majorChangingTo, Roster myRoster) {

        boolean isMajorInvalid = true;
        for (Major validMajor : Major.values()) {
            if (validMajor.name().equals(majorChangingTo.toUpperCase())) {
                isMajorInvalid = false;
                Profile profileOfStudent = new Profile(lastName, firstName, new Date(DOB));
                if (myRoster.contains(new Student (profileOfStudent,Major.CS,1))) {
                    //TODO: I would've called find() here but its a private method, so I have to do its math here ;(
                    //TODO: Any better way to do this? I have no ideas....
                    //TODO: Unless the bulk of this goes into Roster.java, but then I feel hesitant to add another public method, tho idk

                    if (myRoster!=null) { //TODO: Something to do though is to have a private method in Roster.java which prints true/false if a Roster is null, as this if-statement happens A LOT
                        for (int k = 0; k < myRoster.getRoster().length; k++) {
                            if (myRoster.getRoster()[k] != null) {
                                if (myRoster.getRoster()[k].getProfile().equals(profileOfStudent)) {
                                    myRoster.getRoster()[k].setMajor(Enum.valueOf(Major.class,majorChangingTo));
                                    System.out.println(profileOfStudent.toString() + " major changed to " + majorChangingTo);

                                }
                            }
                        }
                    }
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

    public void run() {
        System.out.println("Roster Manager running...");     //user knows software is ready for commands
        boolean hasQuit = false;
        Roster myRoster = new Roster();
        Scanner sc = new Scanner(System.in);
        while(! (hasQuit && sc.hasNextLine())) {                           //continuously read the line commands until the user quits
            String[] parsedCommandArguments = sc.nextLine().split("\\s+"); //parses arguments
            switch (parsedCommandArguments[0]) {                                //get the current command\
                case "":        //if the enter key is pressed without any input, this prevents an error
                    break;
                case "A":
                    A_Command(parsedCommandArguments[1],parsedCommandArguments[2],parsedCommandArguments[3],parsedCommandArguments[4],parsedCommandArguments[5],myRoster);
                    break;
                case "R":
                    R_Command(parsedCommandArguments[1],parsedCommandArguments[2],parsedCommandArguments[3],myRoster);
                    break;
                case "P":
                case "PS":
                case "PC":
                    P_Command(parsedCommandArguments[0],myRoster);
                    break;
                case "L":
                    L_Command(parsedCommandArguments[1],myRoster);
                    break;
                case "C":
                    C_Command(parsedCommandArguments[1],parsedCommandArguments[2],parsedCommandArguments[3],parsedCommandArguments[4],myRoster);
                    break;
                case "Q":
                    System.out.println("Roster Manager terminated.");    //user has terminated the software normally
                    hasQuit = true;
                    break;
                default:
                    System.out.println(parsedCommandArguments[0] + " is an invalid command!");
                    break;
            }
        }
        sc.close();
    }
}