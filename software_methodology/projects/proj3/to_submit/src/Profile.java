package com.example.cs213project3;/* @author Henry Hecht */
/* @author Aidan Cronin */

/**
 * The profile of a student, containing their last name, first name, and date of birth
 */
public class Profile implements Comparable<Profile> {
    private String lname;
    private String fname;
    private Date dob;

    /**
     * Getter method for lname
     * @return the last name of the profile
     */
    private String getLname(){
        return this.lname;
    }

    /**
     * Getter method for fname
     * @return the first name of the profile
     */
    private String getFname(){
        return this.fname;
    }

    /**
     * Getter method for dob
     * @return the date of birth of the profile
     */
    public Date getDob() {
        return this.dob;
    }

    /**
     * Three argument constructor for Profile class. Creates an
     * instance from first name, last name and date of birth.
     * @param lname Last name of the profile
     * @param fname First name of the profile
     * @param dob Date of Birth of the profile
     */
    public Profile(String lname, String fname, Date dob) {
        this.lname = lname;
        this.fname = fname;
        this.dob = dob;
    }

    /**
     * Method returns the string version of profile.
     * @return a string with a space between the first name, last name, and date of birth
     */
    public String toString() {
        return fname + " " + lname + " " + dob.toString();
    }

    /**
     * Method determines whether two profiles are equal to each other
     * @param obj the profile which we will compare to
     * @return true if profiles are equal and false if they are not.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        final Profile profile = (Profile) obj;
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
     * Method to compare two profiles together.
     * @param profile the profile which we will compare
     * @return 1 if original profile is alphabetically before compared profile,
     * 2 if original profile is alphabetically after compared profile, 0 if equal.
     */
    public int compareTo(Profile profile) { //Needs to get worked
        String stu1 = this.lname + this.fname;
        String stu2 = profile.getLname() + profile.getFname();
        int min;
        if(stu1.length() > stu2.length()) {
            min = stu2.length();
        } else {
            min = stu1.length();
        }
        for(int i = 0; i < min; i++) {
            //stu1 is alphabetically before stu2
            if(Character.toUpperCase(stu1.charAt(i)) < Character.toUpperCase(stu2.charAt(i))) {
                return 1;
            }
            //Characters at this index are equal
            if(Character.toUpperCase(stu1.charAt(i)) == Character.toUpperCase(stu2.charAt(i))) {
                continue;
            }
            //stu1 is alphabetically after stu2
            if(Character.toUpperCase(stu1.charAt(i)) > Character.toUpperCase(stu2.charAt(i))) {
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