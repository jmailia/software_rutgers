/* @author Henry Hecht */
/* @author Aidan Cronin */

public class International extends NonResident {
    private boolean isStudyAbroad;

    public International(Profile profile, Major major, int creditCompleted, boolean isStudyAbroad){
        super(profile, major, creditCompleted);
        this.isStudyAbroad = isStudyAbroad;
    }

    public double tuitionDue(int creditsEnrolled){
        if(isValid(creditsEnrolled)){
            double tuition = 0;
            int universityFee = 3268;
            int creditsPerHour = 404;
            int exceededCredits = 16;
            int fulltimeCredits = 12;
            int fulltimeTuition = 12536;
            int healthInsuranceFee = 2650;
            if(isStudyAbroad){
                tuition = universityFee + healthInsuranceFee;
                return tuition;
            }
            if(creditsEnrolled < fulltimeCredits){ // Parttime students
                double parttimeFeePercentage = 0.8;
                tuition = (parttimeFeePercentage * universityFee) + (creditsPerHour * creditsEnrolled);
                return tuition;
            }
            if(creditsEnrolled > exceededCredits){ // Students with over 16 credits
                int extraCredits = creditsEnrolled - exceededCredits;
                tuition = (extraCredits * creditsPerHour) + universityFee + fulltimeTuition + healthInsuranceFee;
                return tuition;
            } // Fulltime students who don't exceed 16 credits
            tuition = universityFee + fulltimeTuition + healthInsuranceFee;
            return tuition;
        }
        return -1; //credits not valid
    }

    /**
     * Method prints this object's class name
     * @return Classname as a string
     */
    public String printClassname(){
        return "International";
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
        return true;
    }

    /**
     * Determines whether object is study abroad
     * @return True if object is study abroad, false otherwise
     */
    public boolean isStudyAbroad(){
        return this.isStudyAbroad;
    }

    /**
     * If the student is a nonresident tristate student, returns the string containing their origin state.
     * @return "NY" if the student is from NY, "CT" if the student is from CT, "" if not from either.
     */
    public String whichTristate(){return "";}
}
