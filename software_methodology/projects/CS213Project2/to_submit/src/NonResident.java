/* @author Henry Hecht */
/* @author Aidan Cronin */

public class NonResident extends Student {
    public NonResident(Profile profile, Major major, int creditCompleted){
        super(profile, major, creditCompleted);
    }

    public double tuitionDue(int creditsEnrolled){
        if(isValid(creditsEnrolled)){
            double tuition = 0;
            int universityFee = 3268;
            int creditsPerHour = 966;
            int exceededCredits = 16;
            int fulltimeCredits = 12;
            int fulltimeTuition = 29737;
            if(creditsEnrolled < fulltimeCredits){ //Parttime students
                double parttimeFeePercentage = 0.8;
                tuition = (parttimeFeePercentage * universityFee) + (creditsPerHour * creditsEnrolled);
                return tuition;
            }
            if(creditsEnrolled > exceededCredits){ //Students with over 16 credits
                int extraCredits = creditsEnrolled - exceededCredits;
                tuition = (extraCredits * creditsPerHour) + universityFee + fulltimeTuition;
                return tuition;
            } // Fulltime students who don't exceed 16 credits
            tuition = universityFee + fulltimeTuition;
            return tuition;
        }
        return -1; //credits not valid
    }

    public boolean isResident(){
        return false;
    }

    public void setScholarship(int scholarship){
        return;
    }
}
