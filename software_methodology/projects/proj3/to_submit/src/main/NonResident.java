/* @author Henry Hecht */
/* @author Aidan Cronin */

/**
 * A student who does not reside in New Jersey
 */
public class NonResident extends Student {
    /**
     * Construct a nonresident student
     * @param profile the profile of the nonresident student
     * @param major the major of the nonresident student
     * @param creditCompleted the credits completed by the nonresident student
     */
    public NonResident(Profile profile, Major major, int creditCompleted){
        super(profile, major, creditCompleted);
    }

    /**
     * The tuition due for nonresident students
     * @param creditsEnrolled The number of credits the nonresident student is enrolled in
     * @return the tuition which the student is to pay
     */
    public double tuitionDue(int creditsEnrolled){
        if(isValid(creditsEnrolled)){
            int universityFee = 3268;
            int creditsPerHour = 966;
            int fulltimeTuition = 29737;
            if(creditsEnrolled < 12){ //Parttime students
                return (0.8 * universityFee) + (creditsPerHour * creditsEnrolled);
            }
            else if(creditsEnrolled > 16){ //Students with over 16 credits
                int extraCredits = creditsEnrolled - 16;
                return (extraCredits * creditsPerHour) + universityFee + fulltimeTuition;
            } // Fulltime students who don't exceed 16 credits
            return universityFee + fulltimeTuition;
        }
        return -1; //credits not valid
    }

    /**
     * Method prints this object's class name
     * @return Classname as a string
     */
    public String printClassname(){
        return "NonResident";
    }

    /**
     * Determines whether object is a resident
     * @return True if object is resident, false otherwise
     */
    public boolean isResident(){
        return false;
    }

    /**
     * Determines whether object is a TriState
     * @return True if object is TriState, false otherwise
     */
    public boolean isTriState(){
        return false;
    }

    /**
     * Determines whether object is an International
     * @return True if object is International, false otherwise
     */
    public boolean isInternational(){
        return false;
    }

    /**
     * Determines whether object is study abroad
     * @return True if object is study abroad, false otherwise
     */
    public boolean isStudyAbroad(){
        return false;
    }

    public void setScholarship(int scholarship){
        return;
    }

    /**
     * If the student is a nonresident tristate student, returns the string containing their origin state.
     * @return "NY" if the student is from NY, "CT" if the student is from CT, "" if not from either.
     */
    public String whichTristate() {return isTriState()?this.whichTristate():"";}
}
