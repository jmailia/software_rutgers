public class Resident extends Student {
    private int scholarship;
    public Resident(Profile profile, Major major, int creditCompleted, int scholarship){
        super(profile, major, creditCompleted);
        this.scholarship = scholarship;
    }
}
