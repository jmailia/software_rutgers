package com.example.cs213project3;/* @author Henry Hecht */
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
        String resident = this.isResident() ? "(resident)" : "(non-resident)" +
                (this.isTriState()?("(tri-state:"+this.whichTristate()+")"):
                        (this.isInternational()?"(international" +
                                (this.isStudyAbroad()?":study abroad)":")"):""));

        return printStudentProfile() + " " + this.major.getDisplayName() +
                " credits completed: " + this.creditCompleted + grade + resident;
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

    /**
     * If the credits enrolled of the student is valid
     * @param creditEnrolled credits enrolled by the student
     * @return true if the credits enrolled is valid, false otherwise
     */
    public boolean isValid(int creditEnrolled) {
        if(creditEnrolled < 0 || creditEnrolled > 24)
            return false;
        return true;
    }

    /**
     * The tuition due for student
     * @param creditsEnrolled The number of credits the tristate student is enrolled in
     * @return the tuition which the student is to pay
     */
    public abstract double tuitionDue(int creditsEnrolled);

    /**
     * Method prints this object's class name
     * @return Classname as a string
     */
    public abstract String printClassname();

    /**
     * Determines whether object is a resident
     * @return True if object is resident, false otherwise
     */
    public abstract boolean isResident();

    /**
     * Determines whether object is an International
     * @return True if object is International, false otherwise
     */
    public abstract boolean isInternational();

    /**
     * Determines whether object is a TriState
     * @return True if object is TriState, false otherwise
     */
    public abstract boolean isTriState();

    /**
     * Determines whether object is study abroad
     * @return True if object is study abroad, false otherwise
     */
    public abstract boolean isStudyAbroad();

    /**
     * Setter method for scholarship
     * @param scholarship The amount we want to set scholarship to
     */
    public abstract void setScholarship(int scholarship);

    /**
     * If the student is a nonresident tristate student, returns the string containing their origin state.
     * @return "NY" if the student is from NY, "CT" if the student is from CT, "" if not from either.
     */
    public abstract String whichTristate();
}