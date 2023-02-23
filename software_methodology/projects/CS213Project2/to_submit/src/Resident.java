public class Resident extends Student {

    private int scholarship;

    public Resident(Profile profile, Major major, int creditCompleted){
        super(profile, major, creditCompleted);
        this.scholarship = 0;
    }

    public void setScholarship(int scholarship) {
        this.scholarship = scholarship;
    }

    /**
     * The tuition due for resident students
     * @param creditsEnrolled The number of credits the resident student is enrolled in
     * @return the tuition which the student is to pay
     */
    public double tuitionDue(int creditsEnrolled){
        if(isValid(creditsEnrolled)){
            int universityFee = 3268;
            int creditsPerHour = 404;
            int exceededCredits = 16;
            int fulltimeCredits = 12;
            int fulltimeTuition = 12536;
            if(creditsEnrolled < fulltimeCredits){ //Parttime students
                double parttimeFeePercentage = 0.8;
                return (parttimeFeePercentage * universityFee) + (creditsPerHour * creditsEnrolled) - this.scholarship;
            }
            if(creditsEnrolled > exceededCredits){ //Students with over 16 credits
                int extraCredits = creditsEnrolled - exceededCredits;
                return (extraCredits * creditsPerHour) + universityFee + fulltimeTuition - this.scholarship;
            } // Fulltime students who don't exceed 16 credits
            return universityFee + fulltimeTuition - this.scholarship;
        }
        return -1; //credits not valid
    }

    public boolean isResident(){
        return true;
    }
}

