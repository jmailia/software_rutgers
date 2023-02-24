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

    public boolean isResident(){
        return false;
    }

    public boolean isTriState(){
        return false;
    }

    public boolean isInternational(){
        return true;
    }

    public boolean isStudyAbroad(){
        return this.isStudyAbroad;
    }
}
