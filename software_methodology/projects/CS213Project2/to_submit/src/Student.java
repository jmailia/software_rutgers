/* @author Henry Hecht */
/* @author Aidan Cronin */

/**
 * Class defines a student by their profile, major, and credits completed; also determines whether students are equals and have the same profiles
 */
public abstract class Student implements Comparable<Student> {
    private Profile profile;
    private Major major; //Major is an enum type
    private int creditCompleted;

    /**
     * Constructor method for Student class which sets profile, major and creditsCompleted.
     * @param profile The profile of the student
     * @param major The major of the student
     * @param creditCompleted The number of credits completed of the student
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
     * Setter method for major
     * @param major major to set
     */
    public void setMajor(Major major){
        this.major = major;
    }

    /**
     * Setter method for creditCompleted
     * @param creditCompleted credit to set
     */
    public void setCreditCompleted(int creditCompleted){
        this.creditCompleted = creditCompleted;
    }

    /**
     * Method to print Student details.
     * @return String
     */
    public String toString() {
        String grade = " (Freshman)";
        if (this.creditCompleted < 60 && this.creditCompleted >=30) {
            grade = " (Sophomore)";
        } else if (this.creditCompleted < 90 && this.creditCompleted >= 60) {
            grade = " (Junior)";
        } else if (this.creditCompleted >= 90) {
            grade = " (Senior)";
        }
        return printStudentProfile() + " " + this.major.getDisplayName() +
                " credits completed: " + this.creditCompleted + grade;
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
     * @param obj the student which will be compared
     * @return true if students are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        final Student student = (Student) obj;
        if(this.profile.equals(student.getProfile())) {
            if (this.major.equals(student.getMajor())) {
                if (this.creditCompleted == student.getCreditCompleted()) {
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * Method to compare two students together
     * @param student which we will compare
     * @return int 1 if original student is ordered before compared student,
     *  -1 if original student is ordered after compared student, returns 0 if equal.
     */
    public int compareTo(Student student) {
        if(this.profile.compareTo(student.getProfile()) == 1){
            return 1;
        }
        if(this.profile.compareTo(student.getProfile()) == -1) {
            return -1;
        }
        if(this.major != student.getMajor()) {
            return -1;
        }
        if (this.creditCompleted > student.getCreditCompleted()) { //stu1 has more credits than stu2
            return 1;
        }
        if (this.creditCompleted < student.getCreditCompleted()) { //stu1 has less credits than stu2
            return -1;
        }
        return 0; //stu1 and stu2 are equal
    }

    public boolean isValid(int creditEnrolled) {
        if(creditEnrolled < 0 || creditEnrolled > 24)
            return false;
        return true;
    }

    public abstract double tuitionDue(int creditsEnrolled);

    public abstract boolean isResident();

    public abstract boolean isInternational();

    public abstract boolean isTriState();

    public abstract boolean isStudyAbroad();

    public abstract void setScholarship(int scholarship);

}