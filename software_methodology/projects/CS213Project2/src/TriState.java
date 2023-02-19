public class TriState extends NonResident{
    private String state;

    public TriState(Profile profile, Major major, int creditCompleted, String state){
        super(profile, major, creditCompleted);
        this.state = state;
    }
}
public class TriState extends NonResident{
    private String state;

    public TriState(Profile profile, Major major, int creditCompleted, String state){
        super(profile, major, creditCompleted);
        this.state = state;
    }

    public int tristateDiscount(String state){
        int discount = 0;
        if(state == "NY"){
            discount = 4000;
        }
        if(state == "CT"){
            discount = 5000;
        }
        return discount;
    }

    public double tuitionDue(int creditsEnrolled){
        if(isValid(creditsEnrolled)){
            int discount = tristateDiscount(this.state);
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
                tuition -= discount;
                return tuition;
            } // Fulltime students who don't exceed 16 credits
            tuition = universityFee + fulltimeTuition;
            tuition -= discount;
            return tuition;
        }
        return -1; //credits not valid
    }
}
