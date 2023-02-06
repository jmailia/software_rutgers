/* @author Henry Hecht */
/* @author Aidan Cronin */

import java.util.Scanner;
public class RosterManager {

    private void A_Command(String[] parsedArguments, Roster myRoster) {

        //boolean to determine if student meets requirements to be added
        boolean isValidAddition = true;

        if (parsedArguments.length == 6) {
            //The student is not added to the roster given the following conditions:

            // The major does not exist
            boolean isMajorValid = false;
            for (Major value : Major.values()) {
                if (value.name().equals(parsedArguments[4].toUpperCase())) { //TODO: Is this equals() okay? I still dont know what is required
                    isMajorValid = true;
                }
            }
            if (!isMajorValid) {
                System.out.println("Major code invalid: " + parsedArguments[4]);
                isValidAddition = false;
            }

            int numberOfCredits = 0;
            try {
                numberOfCredits = Integer.parseInt(parsedArguments[5]);
                // Negative number of credits completed
                if (numberOfCredits < 0 || numberOfCredits == -0) {
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
                        new Profile(parsedArguments[2], parsedArguments[1], new Date(parsedArguments[3])),
                        Major.valueOf(parsedArguments[4].toUpperCase()),
                        Integer.parseInt(parsedArguments[5]));

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
    }
    private void R_Command(String[] parsedArguments, Roster myRoster) {
        if (parsedArguments.length == 4) {

        }
    }
    private void P_Command(String[] parsedArguments, Roster myRoster) {
        if (parsedArguments.length == 1) {
            if (myRoster.getRoster() == null) {
                System.out.println("Student roster is empty!");
            } else {
                System.out.println(parsedArguments[0]);
                if (parsedArguments[0].equals("P")) {
                    System.out.println("* Student roster sorted by last name, first name, DOB **");
                    myRoster.print();
                } else if (parsedArguments[0].equals("PS")) {
                    System.out.println("* Student roster sorted by school, major **");
                    myRoster.printByStanding();
                } else {
                    System.out.println("* Student roster sorted by standing **");
                    myRoster.printBySchoolMajor();
                }
                System.out.println("* end of roster **");
            }
        }
    }

    private void L_Command(String[] parsedArguments, Roster myRoster) {
        if (parsedArguments.length == 2) {
            boolean validSchool = true;
            Roster tempRoster = new Roster();
            for (int k = 0; k < myRoster.getRoster().length; k++) {
                if (parsedArguments[1].toUpperCase().equals("SAS")) {
                    if((myRoster.getRoster()[k].getMajor() == Major.CS) ||
                            (myRoster.getRoster()[k].getMajor() == Major.MATH)) {
                        tempRoster.add(myRoster.getRoster()[k]);
                    }
                } else if (parsedArguments[1].toUpperCase().equals("SOE")) {
                    if (myRoster.getRoster()[k].getMajor() == Major.EE) {
                        tempRoster.add(myRoster.getRoster()[k]);
                    }
                } else if (parsedArguments[1].toUpperCase().equals("SC&I")) {
                    if(myRoster.getRoster()[k].getMajor() == Major.ITI) {
                        tempRoster.add(myRoster.getRoster()[k]);
                    }
                } else if (parsedArguments[1].toUpperCase().equals("RBS")) {
                    if (myRoster.getRoster()[k].getMajor() == Major.BAIT) {
                        tempRoster.add(myRoster.getRoster()[k]);
                    }
                } else {
                    validSchool = false;
                    break;
                }
            }

            if (validSchool) {
                System.out.println("* Students in " + parsedArguments[1].toUpperCase() + " *");
                tempRoster.print();
            }
        }
    }


    private void C_Command(String[] parsedArguments, Roster myRoster) {
        if (parsedArguments.length == 5) {
            String majorStr = parsedArguments[4];
            //Check to see if argument is a valid major.
            if((majorStr.equals("CS")) || (majorStr.equals("EE")) || (majorStr.equals("MATH")) || //Janky way to check if major is valid but works
                    (majorStr.equals("ITI")) || (majorStr.equals("BAIT"))) {
                Major major = Enum.valueOf(Major.class, parsedArguments[4]);
                Date date = new Date(parsedArguments[3]);
                Profile profile = new Profile(parsedArguments[1], parsedArguments[2], date);
                for (int k = 0; k < myRoster.getRoster().length; k++) { //Find student based off of profile in roster
                    if (myRoster.getRoster()[k].getProfile().equals(profile)) {
                        myRoster.getRoster()[k].setMajor(Enum.valueOf(Major.class, parsedArguments[4]));
                        return;
                    }
                }
                System.out.println(profile.toString() + " is not in the roster.");
                return;
            }
            else
                System.out.println("Major code invalid: " + majorStr);
            return;
        }
        System.out.println("Invalid Command Length");
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
                    A_Command(parsedCommandArguments,myRoster);
                    break;
                case "R":
                    R_Command(parsedCommandArguments,myRoster);
                    break;
                case "P":
                case "PS":
                case "PC":
                    P_Command(parsedCommandArguments,myRoster);
                    break;
                case "L":
                    L_Command(parsedCommandArguments,myRoster);
                    break;
                case "C":
                    C_Command(parsedCommandArguments,myRoster);
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