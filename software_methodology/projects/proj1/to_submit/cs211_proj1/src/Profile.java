public class Profile implements Comparable<Profile> {
    private String lname;
    private String fname;
    private Date dob; //use the Date class described in (f)

    public Profile(String lname, String fname, Date dob) {
        this.lname = lname;
        this.fname = fname;
        this.dob = dob;
    }

    public String printProfile() {
        return fname + " " + lname + " " + dob.printDate();
    }

    public int compareTo(Profile profile) {
        return (this.lname).compareTo(profile.lname);
    }
}