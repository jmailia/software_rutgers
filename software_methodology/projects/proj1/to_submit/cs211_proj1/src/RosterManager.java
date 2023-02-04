import java.util.*;
public class RosterManager {



    private void A_Command(String[] parsedArguments, Roster myRoster) {

        if (parsedArguments.length == 6) {

            //create student profile
            Student myStudent = new Student(
                    new Profile(parsedArguments[2], parsedArguments[1], new Date(parsedArguments[3])),
                    Major.valueOf(parsedArguments[4]),
                    Integer.parseInt(parsedArguments[5]));

            //The student is not added to the roster given the following conditions



            myRoster.add(myStudent);
            System.out.println(myStudent.printStudentProfile() + " added to the roster.");

        }

    }
    private void R_Command(String[] parsedArguments, Roster myRoster) {
        if (parsedArguments.length == 4) {

        }
    }
    private void P_Command(String[] parsedArguments, Roster myRoster) {
        if (parsedArguments.length == 1) {
            myRoster.print();
        }
    }
    private void PS_Command(String[] parsedArguments, Roster myRoster) {
        if (parsedArguments.length == 1) {

        }
    }
    private void PC_Command(String[] parsedArguments, Roster myRoster) {
        if (parsedArguments.length == 1) {

        }
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

            for (int i=0;i< parsedCommandArguments.length;i++){System.out.println(parsedCommandArguments[i]);}//TODO: Remove this

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
