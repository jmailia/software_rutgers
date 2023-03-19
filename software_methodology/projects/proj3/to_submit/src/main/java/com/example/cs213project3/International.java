package com.example.cs213project3;/* @author Henry Hecht */
/* @author Aidan Cronin */

/**
 * A nonresident student from another country, who may or may not be studying abroad.
 */
public class International extends NonResident {
    private boolean isStudyAbroad;

    /**
     * Construct an international student
     * @param profile the profile of the international student
     * @param major the major of the international student
     * @param creditCompleted the credits completed by the international student
     * @param isStudyAbroad is true if the student is studying abroad, false otherwise
     */
    public International(Profile profile, Major major, int creditCompleted, boolean isStudyAbroad){
        super(profile, major, creditCompleted);
        this.isStudyAbroad = isStudyAbroad;
    }

    /**
     * Calculates the tuition due for an international student
     * @param creditsEnrolled The number of credits the international student is enrolled in
     * @return the calculated tuition of the student
     */
    public double tuitionDue(int creditsEnrolled){
        if(isValid(creditsEnrolled)){
            int universityFee = 3268;
            int creditsPerHour = 966;
            int fulltimeTuition = 29737;
            int healthInsuranceFee = 2650;
            if(isStudyAbroad){
                return universityFee + healthInsuranceFee;
            }
            if(creditsEnrolled < 12){ // Parttime students (they are studying abroad)
                return  (0.8 * universityFee) + (creditsPerHour * creditsEnrolled);
            }
            if(creditsEnrolled > 16){ // Students with over 16 credits
                int extraCredits = creditsEnrolled - 16;
                return (extraCredits * creditsPerHour) + universityFee + fulltimeTuition + healthInsuranceFee;
            } // Fulltime students who don't exceed 16 credits
            return universityFee + fulltimeTuition + healthInsuranceFee;
        }
        return -1; //credits not valid
    }

    /**
     * Method prints this object's class name
     * @return Classname as a string
     */
    public String printClassname(){
        return "International";
    }

    /**
     * Determines whether object is a resident
     * @return True if object is resident, false otherwise
     */
    public boolean isResident(){
        return false;
    }

    /**
     * Determines whether object is a TriState
     * @return True if object is TriState, false otherwise
     */
    public boolean isTriState(){
        return false;
    }

    /**
     * Determines whether object is an International
     * @return True if object is International, false otherwise
     */
    public boolean isInternational(){
        return true;
    }

    /**
     * Determines whether object is study abroad
     * @return True if object is study abroad, false otherwise
     */
    public boolean isStudyAbroad(){
        return this.isStudyAbroad;
    }

    /**
     * If the student is a nonresident tristate student, returns the string containing their origin state.
     * @return "NY" if the student is from NY, "CT" if the student is from CT, "" if not from either.
     */
    public String whichTristate(){return "";}
}
