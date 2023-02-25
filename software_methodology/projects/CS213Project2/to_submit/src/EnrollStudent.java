/* @author Henry Hecht */
/* @author Aidan Cronin */

public class EnrollStudent {
    private Profile profile;
    private int creditsEnrolled;

    /**
     * Getter method for this.profile
     * @return The profile we want to get
     */
    public Profile getProfile(){
        return this.profile;
    }

    /**
     * Getter method for this.creditsEnrolled
     * @return this.creditsEnrolled
     */
    public int getCreditsEnrolled() {
        return this.creditsEnrolled;
    }

    /**
     * Setter method for this.creditsEnrolled
     * @param creditsEnrolled the creditsEnrolled that we want to change to
     */
    public void setCreditsEnrolled(int creditsEnrolled){
        this.creditsEnrolled = creditsEnrolled;
    }

    /**
     * Constructor method for EnrollStudent
     * @param profile Profile of EnrollStudent
     * @param creditsEnrolled creditsEnrolled of EnrollStudent
     */
    public EnrollStudent(Profile profile, int creditsEnrolled){
        this.profile= profile;
        this.creditsEnrolled = creditsEnrolled;
    }

    /**
     * toString Override method for EnrollStudent
     * @return this.EnrollStudent to string
     */
    @Override
    public String toString(){
        return this.profile.toString() + " " + this.creditsEnrolled;
    }

    /**
     * Method that determines whether two enrollStudents are equal
     * @param obj The EnrollStudent we want to compare
     * @return True if enrollStudents are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj){
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        final EnrollStudent student = (EnrollStudent) obj;
        if(this.profile.equals(student.getProfile()) && this.creditsEnrolled == student.getCreditsEnrolled())
            return true;
        else
            return false;
    }
}
