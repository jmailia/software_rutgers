/* @author Henry Hecht */
/* @author Aidan Cronin */

public class NonResident extends Student {
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
            int exceededCredits = 16;
            int fulltimeCredits = 12;
            int fulltimeTuition = 29737;
            if(creditsEnrolled < fulltimeCredits){ //Parttime students
                return (0.8 * universityFee) + (creditsPerHour * creditsEnrolled);
            }
            if(creditsEnrolled > exceededCredits){ //Students with over 16 credits
                int extraCredits = creditsEnrolled - exceededCredits;
                return (extraCredits * creditsPerHour) + universityFee + fulltimeTuition;
            } // Fulltime students who don't exceed 16 credits
            return universityFee + fulltimeTuition;
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
