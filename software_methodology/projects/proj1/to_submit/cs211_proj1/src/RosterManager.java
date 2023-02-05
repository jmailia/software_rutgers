import java.util.*;
public class RosterManager {

    /**
     * Determines whether student meets criteria to be added to roster
     * @param parsedArguments a String array containing the user's arguments of the form
     *                        ["A", "First_Name", "Last_Name","DOB", "Major", "Credits_Completed"]
     * @param myRoster the roster which the student might be added to
     */
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
                //Any date of birth that is today or a future date
                // (NOTE: there is no example of today/future the sample output, but it is required by the written instructions)

                //TODO: this needs to be fully implemented whenever Date() is completed
                if (false) {
                    System.out.println("DOB invalid: " + myStudent.getProfile().getDob() + " not a valid calendar date!");
                    isValidAddition = false;

                    // A student who is less than 16 years old
                    System.out.println("DOB invalid: " + myStudent.getProfile().getDob() + " younger than 16 years old.");
                    isValidAddition = false;
                }

                // The student is in the roster already
                if (!myRoster.contains(myStudent)) { //TODO: Remove ! when contains is complete
                    System.out.println(myStudent.getProfile().printProfile() + " is already in the roster.");
                    isValidAddition = true;
                }
                // If the student meets the requirements to be added
                if (isValidAddition) { //TODO: Revert this back
                    myRoster.add(myStudent);
                    System.out.println(myStudent.printStudentProfile() + " added to the roster.");
                }
            }
        }
    }

    /**
     * Tests whether a specified student is in roster, and if so, removes the student from the roster
     * @param parsedArguments a String array containing the user's arguments of the form
     *      *                        ["R", "First_Name", "Last_Name","DOB"]
     * @param myRoster a Roster which the student which want to remove might be in
     */
    private void R_Command(String[] parsedArguments, Roster myRoster) {
        if (parsedArguments.length == 4) {

        }
    }

    /**
     * Display the Roster sorted by last name, first name, and DOB
     * @param myRoster the Roster to be sorted by the above categories.
     */
    private void P_Command(Roster myRoster) {
        myRoster.print();
    }

    /**
     * Display the Roster sorted by standing
     * @param myRoster the Roster to be sorted by standing.
     */
    private void PS_Command(Roster myRoster) {
        myRoster.printByStanding();
    }

    /**
     * Display the Roster sorted by school and major
     * @param myRoster the Roster to be sorted by the above categories.
     */
    private void PC_Command(Roster myRoster) {
        myRoster.printBySchoolMajor();
    }
    private void L_Command(String[] parsedArguments, Roster myRoster) {
        if (parsedArguments.length == 2) {

        }
    }
    private void C_Command(String[] parsedArguments, Roster myRoster) {
        if (parsedArguments.length == 5) {

        }
    }
    public void run() {
        System.out.println("Roster Manager running...");     //user knows software is ready for commands
        boolean hasQuit = false;
        Roster myRoster = new Roster();
        while(!hasQuit) {                           //continuously read the line commands until the user quits
            Scanner sc = new Scanner(System.in);
            String[] parsedCommandArguments = sc.nextLine().split("\\s"); //parses arguments

            //for (int i=0;i< parsedCommandArguments.length;i++){System.out.println(parsedCommandArguments[i]);}//TODO: Remove this

            switch (parsedCommandArguments[0]) {                                //get the current command
                case "A":
                    A_Command(parsedCommandArguments,myRoster);
                    break;
                case "R":
                    R_Command(parsedCommandArguments,myRoster);
                    break;
                case "P":
                    P_Command(myRoster);
                    break;
                case "PS":
                    PS_Command(myRoster);
                    break;
                case "PC":
                    PC_Command(myRoster);
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
    }
}
