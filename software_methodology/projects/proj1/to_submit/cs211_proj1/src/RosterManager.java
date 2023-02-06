import java.util.Scanner;
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
            System.out.println("* Student roster sorted by last name, first name, DOB **");
            myRoster.print();
        }
    }
    private void PS_Command(String[] parsedCommandArguments, Roster myRoster) {
        if (parsedCommandArguments.length == 1) {
            myRoster.printByStanding();
        }
    }
    private void PC_Command(String[] parsedCommandArguments, Roster myRoster) {
        if (parsedCommandArguments.length == 1) {
            myRoster.printBySchoolMajor();
        }
    }

    private void L_Command(String[] parsedCommandArguments, Roster myRoster) {
        if (parsedCommandArguments.length == 2) {
            if (parsedCommandArguments[1].equals("SAS")) {
                Roster tempRoster = new Roster();
                for (int k = 0; k < myRoster.getRoster().length; k++) {
                    if((myRoster.getRoster()[k].getMajor() == Major.CS) ||
                    (myRoster.getRoster()[k].getMajor() == Major.MATH))
                        tempRoster.add(myRoster.getRoster()[k]);
                }
                System.out.println("* Students in SAS *");
                tempRoster.print();
            }

            if (parsedCommandArguments[1].equals("SOE")) {
                Roster tempRoster = new Roster();
                for (int k = 0; k < myRoster.getRoster().length; k++) {
                    if(myRoster.getRoster()[k].getMajor() == Major.EE)
                        tempRoster.add(myRoster.getRoster()[k]);
                }
                System.out.println("* Students in SOE *");
                tempRoster.print();
            }
            if (parsedCommandArguments[1].equals("SC&I")) {
                Roster tempRoster = new Roster();
                for (int k = 0; k < myRoster.getRoster().length; k++) {
                    if(myRoster.getRoster()[k].getMajor() == Major.ITI)
                        tempRoster.add(myRoster.getRoster()[k]);
                }
                System.out.println("* Students in SC&I *");
                tempRoster.print();
            }
            if (parsedCommandArguments[1].equals("RBS")) {
                Roster tempRoster = new Roster();
                for (int k = 0; k < myRoster.getRoster().length; k++) {
                    if(myRoster.getRoster()[k].getMajor() == Major.BAIT)
                        tempRoster.add(myRoster.getRoster()[k]);
                }
                System.out.println("* Students in RBS *");
                tempRoster.print();
            }
            }
        }


    private void C_Command(String[] parsedCommandArguments, Roster myRoster) {
        if (parsedCommandArguments.length == 5) {
            String majorStr = parsedCommandArguments[4];
            //Check to see if argument is a valid major.
            if((majorStr.equals("CS")) || (majorStr.equals("EE")) || (majorStr.equals("MATH")) || //Janky way to check if major is valid but works
                    (majorStr.equals("ITI")) || (majorStr.equals("BAIT"))) {
                Major major = Enum.valueOf(Major.class, parsedCommandArguments[4]);
                Date date = new Date(parsedCommandArguments[3]);
                Profile profile = new Profile(parsedCommandArguments[1], parsedCommandArguments[2], date);
                for (int k = 0; k < myRoster.getRoster().length; k++) { //Find student based off of profile in roster
                    if (myRoster.getRoster()[k].getProfile().equals(profile)) {
                        myRoster.getRoster()[k].setMajor(Enum.valueOf(Major.class, parsedCommandArguments[4]));
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
