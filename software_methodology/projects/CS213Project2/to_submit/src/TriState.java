public class TriState extends NonResident{
    private String state;

    public TriState(Profile profile, Major major, int creditCompleted, String state){
        super(profile, major, creditCompleted);
        this.state = state;
    }

    /**
     * Non-resident students from the tristate area get a tuition discount
     * @param state the state which the non-resident tri-state student is from
     * @return the tuition discount for the non-resident tri-state student
     */
    public int tristateDiscount(String state){
        return (state == "CT") ? 5000 : (state == "NY") ? 4000 : 0;
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
            int exceededCredits = 16;
            int fulltimeCredits = 12;
            int fulltimeTuition = 29737;
            if(creditsEnrolled < fulltimeCredits){ //Parttime students
                return (0.8 * universityFee) + (creditsPerHour * creditsEnrolled);
            }
            if(creditsEnrolled > exceededCredits){ //Students with over 16 credits
                int extraCredits = creditsEnrolled - exceededCredits;
                return (extraCredits * creditsPerHour) + universityFee + fulltimeTuition - tristateDiscount(this.state);
            } // Fulltime students who don't exceed 16 credits
            return universityFee + fulltimeTuition - tristateDiscount(this.state);
        }
        return -1; //credits not valid
    }
}
