/* @author Henry Hecht */
/* @author Aidan Cronin */

public class EnrollStudent {
    private Profile profile;
    private int creditsEnrolled;

    public Profile getProfile(){
        return this.profile;
    }

    public int getCreditsEnrolled() {
        return this.creditsEnrolled;
    }

    public void setCreditsEnrolled(int creditsEnrolled){
        this.creditsEnrolled = creditsEnrolled;
    }

    public EnrollStudent(Profile profile, int creditsEnrolled){
        this.profile= profile;
        this.creditsEnrolled = creditsEnrolled;
    }

    @Override
    public String toString(){
        return this.profile.toString() + " " + this.creditsEnrolled;
    }

    @Override
    public boolean equals(Object obj){
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        final EnrollStudent student = (EnrollStudent) obj;
        if(this.profile.equals(student.getProfile()) && this.creditsEnrolled == student.getCreditsEnrolled())
            return true;
        else
            return false;
    }
}
