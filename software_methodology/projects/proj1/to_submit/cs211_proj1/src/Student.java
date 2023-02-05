public class Student implements Comparable<Student> {
    private Profile profile;
    private Major major; //Major is an enum type
    private int creditCompleted;

    /**
     * Three argument constructor method for Student class.
     * Sets profile, major and creditsCompleted.
     * @param profile
     * @param major
     * @param creditCompleted
     */
    public Student(Profile profile, Major major, int creditCompleted) {
        this.profile = profile;
        this.major = major;
        this.creditCompleted = creditCompleted;
    }

    /**
     * Getter method for profile.
     * @return Profile
     */
    public Profile getProfile(){
        return this.profile;
    }

    /**
     * Getter method for creditsCompleted.
     * @return int
     */
    public int getCreditCompleted(){
        return this.creditCompleted;
    }

    /**
     * Getter method for major.
     * @return Major
     */
    public Major getMajor(){
        return this.major;
    }

    /**
     * Method to print given students major
     * @param myMajor
     * @return String
     */
    private String printMajor(Major myMajor){
        switch(myMajor) {
            case CS:
                return "(01:198 CS SAS)";
            case MATH:
                return "(01:640 MATH SAS)";
            case EE:
                return "(14:332 EE SOE)";
            case ITI:
                return "(04:547 ITI SC&I)";
            case BAIT:
                return "(33:136 BAIT RBS)";
        }
        return "";
    }
    /**
     * Method to print the current standing
     * @return String containing Freshman/Sophmore/Junior/Senior
     */
    private String creditsToStanding(int creditsCompleted){
        if (creditsCompleted < 30) {
            return "Freshman";
        }
        if (creditsCompleted>=30 && creditsCompleted <60) {
            return "Sophomore";
        }
        if (creditsCompleted>=30 && creditsCompleted <60) {
            return "Junior";
        } else {
            return "Senior";
        }
    }
    /**
     * Method to print Student details.
     * @return String
     */
    public String printStudentRoster() {
        return printStudentProfile() + " " + printMajor(this.major) + " credits completed: " + creditCompleted +
                "(" + creditsToStanding(creditCompleted)+ ")";
    }

    /**
     * Method to print Student profile.
     * @return String
     */
    public String printStudentProfile() {
        return this.profile.printProfile();
    }

    /**
     * Method to compare two students together.
     * @param student
     * @return int
     */
    public int compareTo(Student student) {
        return (this.major).compareTo(student.major);
    }
}
