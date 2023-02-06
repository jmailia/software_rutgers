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
    public Date getDob() {
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
    public String toString() {
        return fname + " " + lname + " " + dob.toString();
    }

    public boolean equals(Profile profile) {
        if(this.lname.length() == profile.getLname().length()){
            if(this.fname.length() == profile.getFname().length()){
                if(this.dob.equals(profile.getDob())){
                    for(int i = 0; i < lname.length(); i++){
                        if(Character.toUpperCase(this.lname.charAt(i)) == Character.toUpperCase(profile.getLname().charAt(i)))
                            continue;
                        else
                            return false;
                    }
                    for(int j = 0; j < fname.length(); j++){
                        if(Character.toUpperCase(this.fname.charAt(j)) == Character.toUpperCase(profile.getFname().charAt(j)))
                            continue;
                        else
                            return false;
                    }
                    if(this.dob.equals(profile.getDob()))
                        return true;
                }
            }
        }
        return false;
    }

    /**
     * Method to compare two profiles together. Returns 1 if original profile
     * is alphabetically before compared profile, returns 2 if original profile
     * is alphabetically after compared profile, returns 0 if equal.
     * @param profile
     * @return int
     */
    public int compareTo(Profile profile) { //Needs to get worked
        String stu1 = this.lname + this.fname;
        String stu2 = profile.getLname() + profile.getFname();
        int min;
        if(stu1.length() > stu2.length())
            min = stu2.length();
        else
            min = stu1.length();
        for(int i = 0; i < min; i++){
            if(Character.toUpperCase(stu1.charAt(i)) < Character.toUpperCase(stu2.charAt(i))){ //stu1 is alphabetically before stu2
                return 1;
            }
            if(Character.toUpperCase(stu1.charAt(i)) == Character.toUpperCase(stu2.charAt(i))){ //Characters at this index are equal
                continue;
            }
            if(Character.toUpperCase(stu1.charAt(i)) > Character.toUpperCase(stu2.charAt(i))){ //stu1 is alphabetically after stu2
                return -1;
            }
        }
        if(this.dob.compareTo(profile.getDob()) == 1){ //stu1 dob is after stu2
            return -1;
        }
        if(this.dob.compareTo(profile.getDob()) == -1){//stu1 dob is before stu2
            return 1;
        }
        return 0; //profiles are equal
    }
}
