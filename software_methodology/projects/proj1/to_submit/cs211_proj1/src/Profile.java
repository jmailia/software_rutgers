public class Profile implements Comparable<Profile> {
    private String lname;
    private String fname;
    private Date dob;

    /**
     * Getter method for lname
     * @return String
     */
    public String getLname(){
        return this.lname;
    }

    /**
     * Getter method for fname
     * @return String
     */
    public String getFname(){
        return this.fname;
    }

    /**
     * Getter method for dob
     * @return Date
     */
    public Date getDob(){
        return this.dob;
    }

    /**
     * Three argument constructor for Profile class. Creates an
     * instance from first name, last name and date of birth.
     * @param lname
     * @param fname
     * @param dob
     */
    public Profile(String lname, String fname, Date dob) {
        this.lname = lname;
        this.fname = fname;
        this.dob = dob;
    }

    /**
     * Method returns the string version of profile.
     * @return String
     */
    public String printProfile() {
        return fname + " " + lname + " " + dob.toString();
    }

    public int compareTo(Profile profile) { //Needs to get worked
        return (this.lname).compareTo(profile.lname);
    }
}
