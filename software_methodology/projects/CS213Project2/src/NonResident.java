/* @author Henry Hecht */
/* @author Aidan Cronin */

public class NonResident extends Student {
    public NonResident(Profile profile, Major major, int creditCompleted){
        super(profile, major, creditCompleted);
    }

    public double tuitionDue(int creditsEnrolled){
        if(isValid(creditsEnrolled)){
            if(creditsEnrolled < ){ //Part time students

            }
        }
        return -1;
    }

    public boolean isResident(){
        return false;
    }
}
