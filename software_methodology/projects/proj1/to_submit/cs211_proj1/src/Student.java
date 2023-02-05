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
     * Method to print Student details.
     * @return String
     */
    public String toString() {
        return printStudentProfile() + " " + printMajor(this.major) + " credits completed: " + creditCompleted;
    }

    /**
     * Method to print Student profile.
     * @return String
     */
    public String printStudentProfile() {
        return this.profile.toString();
    }

    /**
     * Method compares two students and returns true if they are
     * equal and false if they are not.
     * @param student
     * @return boolean
     */
    public boolean equals(Student student) {
        if(this.profile.equals(student.getProfile())) {
            if(this.major == student.getMajor()){
                if(this.creditCompleted == student.getCreditCompleted()){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Method to compare two students together. Returns 1 if original student
     * is ordered before compared student, returns 2 if original student
     * is ordered after compared student, returns 0 if equal.
     * @param student
     * @return int
     */
    public int compareTo(Student student) {
        if(this.profile.compareTo(student.getProfile()) == 1){
            return 1;
        }
        if(this.profile.compareTo(student.getProfile()) == -1){
            return -1;
        }
        else {
            if (this.creditCompleted > student.getCreditCompleted()) { //stu1 has more credits than stu2
                return 1;
            }
            if (this.creditCompleted > student.getCreditCompleted()) { //stu1 has less credits than stu2
                return 1;
            }
            return 0; //stu1 and stu2 are equal
        }
    }
}

