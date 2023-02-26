/* @author Henry Hecht*/
/* @author Aidan Cronin*/

/**
 * A nonresident student from either Connecticut or New York
 */
public class TriState extends NonResident{
    private String state;

    /**
     * Construct a tristate student
     * @param profile the profile of the tristate student
     * @param major the major of the tristate student
     * @param creditCompleted the credits completed by the tristate student
     * @param state the origin state of the tristate student (either NY or CT)
     */
    public TriState(Profile profile, Major major, int creditCompleted, String state){
        super(profile, major, creditCompleted);
        this.state = state;
    }



    /**
     * Non-resident students from the tristate area get a tuition discount; CT gets 5000 off, NY gets 4000.
     * @param state the state which the non-resident tri-state student is from
     * @return the tuition discount for the non-resident tri-state student
     */
    private int tristateDiscount(String state){
        return ((state.toUpperCase() == "CT") ? 5000 : 4000);
    }

    /**
     * The tuition due for tristate students
     * @param creditsEnrolled The number of credits the tristate student is enrolled in
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
            if(creditsEnrolled > 16){ //Students with over 16 credits
                int extraCredits = creditsEnrolled - 16;
                return (extraCredits * creditsPerHour) + universityFee + fulltimeTuition - tristateDiscount(this.state);
            } // Fulltime students who don't exceed 16 credits
            return universityFee + fulltimeTuition - tristateDiscount(this.state);
        }
        return -1; //credits not valid
    }

    /**
     * Determines whether object is a resident
     * @return True if object is resident, false otherwise
     */
    public boolean isResident(){
        return false;
    }

    /**
     * Method prints this object's class name
     * @return Classname as a string
     */
    public String printClassname(){
        return "TriState";
    }

    /**
     * Determines whether object is a TriState
     * @return True if object is TriState, false otherwise
     */
    public boolean isTriState(){
        return true;
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

    /**
     * If the student is a nonresident tristate student, returns the string containing their origin state.
     * @return "NY" if the student is from NY, "CT" if the student is from CT, "" if not from either.
     */
    public String whichTristate(){return this.state;}
}