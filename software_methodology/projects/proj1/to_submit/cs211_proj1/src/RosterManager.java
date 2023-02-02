import java.util.*;
public class RosterManager {

    private void A_Command(String[] parsedCommandArguments, Roster myRoster) {
        if (parsedCommandArguments.length != 6) {

            Date myDate = new Date();
            myDate.toString();
            Major myMajor = Major.CS;
            int myCreditCompleted = 40;
            Profile myProfile = new Profile("Hecht","Henry",myDate);
            Student myStudent = new Student(myProfile,myMajor,myCreditCompleted);
            myRoster.add(myStudent);
            System.out.println(myStudent.printStudentProfile() + " added to the roster.");
        }
    }
    private void R_Command(String[] parsedCommandArguments, Roster myRoster) {
        if (parsedCommandArguments.length == 4) {

        }
    }
    private void P_Command(String[] parsedCommandArguments, Roster myRoster) {
        if (parsedCommandArguments.length == 1) {
            myRoster.print();
        }
    }
    private void PS_Command(String[] parsedCommandArguments, Roster myRoster) {
        if (parsedCommandArguments.length == 1) {

        }
    }
    private void PC_Command(String[] parsedCommandArguments, Roster myRoster) {
        if (parsedCommandArguments.length == 1) {

        }
    }
    private void L_Command(String[] parsedCommandArguments, Roster myRoster) {
        if (parsedCommandArguments.length == 2) {

        }
    }
    private void C_Command(String[] parsedCommandArguments, Roster myRoster) {
        if (parsedCommandArguments.length == 5) {

        }
    }
    public void run() {
        System.out.println("Roster Manager running...");     //user knows software is ready for commands
        boolean hasQuit = false;
        Roster myRoster = new Roster();
        while(!hasQuit) {                           //continuously read the line commands until the user quits
            Scanner sc = new Scanner(System.in);
            String[] parsedCommandArguments = sc.nextLine().split("\\s"); //parses arguments
            switch (parsedCommandArguments[0]) {                                //get the current command
                case "A":
                    A_Command(parsedCommandArguments,myRoster);
                    break;
                case "R":
                    R_Command(parsedCommandArguments,myRoster);
                    break;
                case "P":
                    P_Command(parsedCommandArguments,myRoster);
                    break;
                case "PS":
                    PS_Command(parsedCommandArguments,myRoster);
                    break;
                case "PC":
                    PC_Command(parsedCommandArguments,myRoster);
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
