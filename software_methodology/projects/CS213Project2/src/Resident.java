public class Resident extends Student {
    
    private int scholarship;

    public Resident(Profile profile, Major major, int creditCompleted, int scholarship){
        super(profile, major, creditCompleted);
        this.scholarship = scholarship;
    }
    
        public double tuitionDue(int creditsEnrolled){
            if(isValid(creditsEnrolled)){
                double tuition = 0;
                int universityFee = 3268;
                int creditsPerHour = 404;
                int exceededCredits = 16;
                int fulltimeCredits = 12;
                int fulltimeTuition = 12536;
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
            return true;
        }
    }
    
