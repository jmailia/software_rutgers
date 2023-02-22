/* @author Henry Hecht */
/* @author Aidan Cronin */

public class International extends NonResident {
    private boolean isStudyAbroad;

    public International(Profile profile, Major major, int creditCompleted, boolean isStudyAbroad){
        super(profile, major, creditCompleted);
        this.isStudyAbroad = isStudyAbroad;
    }
    /**
     * The tuition due for international students
     * @param creditsEnrolled The number of credits the international student is enrolled in
     * @return the tuition which the student is to pay
     */
    public double tuitionDue(int creditsEnrolled){
        if(isValid(creditsEnrolled)){
            int universityFee = 3268;
            int creditsPerHour = 404;
            int exceededCredits = 16;
            int fulltimeCredits = 12;
            int fulltimeTuition = 12536;
            int healthInsuranceFee = 2650;
            if(isStudyAbroad){
                return universityFee + healthInsuranceFee;
            }
            if(creditsEnrolled < fulltimeCredits){ // Parttime students
                return (0.8 * universityFee) + (creditsPerHour * creditsEnrolled);
            }
            if(creditsEnrolled > exceededCredits){ // Students with over 16 credits
                int extraCredits = creditsEnrolled - exceededCredits;
                return (extraCredits * creditsPerHour) + universityFee + fulltimeTuition + healthInsuranceFee;
            } // Fulltime students who don't exceed 16 credits
            return universityFee + fulltimeTuition + healthInsuranceFee;
        }
        return -1; //credits not valid
    }

}
