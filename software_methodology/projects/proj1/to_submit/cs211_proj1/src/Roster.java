public class Roster {
    private Student[] roster;
    private int size;
    public Roster() {}

    /**
     * Getter method for roster.
     * @return Student[]
     */
    public Student[] getRoster(){
        return this.roster;
    }

    /**
     * Searches for the given student in the Roster
     * @param student the student we want to find
     * @return the student's index if it is found, otherwise -1 if the student is not in the roster
     */
    private int find(Student student) {
        for (int k = 0; k < this.roster.length; k++) {
            if(this.roster[k].equals(student)){
                return k;
            }
        }
        return -1;
    } //search the given student in roster


    /**
     * Enables the Roster to have an initial capacity of 4, automatically grow (increases) the capacity by 4
     * whenever it is full, and never decrease in capacity.
     */
    private void grow() {
        //System.out.println("Now Growing"); //TODO: TO REMOVE THIS println
        int tempLength = 0;
        if (this.roster == null) {
            tempLength = 4;
        } else {
            tempLength = this.roster.length + 4;
        }
        Student[] temp = new Student[tempLength]; // transfer old roster info into new temp array
        //System.out.println(temp.length); //TODO: TO REMOVE THIS println

        int jMax;
        if (this.roster == null) {
            jMax = 0;
        } else {
            jMax = temp.length;
        }
        for (int j = 0; j < jMax; j++) {
            if (this.roster == null || j >= this.roster.length) {
                temp[j] = null;
            } else {
                temp[j] = this.roster[j];
            }
        }
        this.roster = temp;
    }

    /**
     * Add a student to the end of the array, first determining whether the Roster needs to grow
     * @param student
     * @return true if the student was successfully added, false otherwise.
     */
    public boolean add(Student student) { //add student to end of array

        if (this.roster == null || this.roster[this.roster.length - 1] != null) { //if the roster is fully empty, or if it is entirely full
            grow();
        }
        for (int x = 0; x < this.roster.length; x++) { //add the student at the smallest index
            if (this.roster[x] == null) {
                this.roster[x] = student;
                break;
            }
        }
        //System.out.println(roster.length); //TODO: TO REMOVE THIS println
        return true;
    }

    /**
     * Removes a student from the roster while maintaining the order of other students
     * @param student the student we want to remove
     * @return true if the student was successfully removed, false otherwise.
     */
    public boolean remove(Student student) {
        return true;
    }//maintain the order after remove

    /**
     * Finds whether the Roster contains the student
     * @param student the student which may or may not be in the Roster
     * @return true if the Roster contains the student, false otherwise.
     */
    public boolean contains(Student student) {
        if (find(student) >= 0){
            return true;
        }
        return false;
    } //if the student is in roster




    /**
     * Print the roster sorted by profiles (last name, first name, DOB)
     */
    public void print() {//print roster sorted by profiles
        if (this.roster == null) {
            System.out.println("Student roster is empty!");
        } else {
            System.out.println("* Student roster sorted by last name, first name, DOB **");
            Student[] tempRoster = this.roster;
            for (int i = 0; i < this.roster.length; i++) { // Start of sort
                for (int j = i + 1; j < this.roster.length; j++) {
                    if (tempRoster[i] != null && tempRoster[j] != null) {
                        Student temp = tempRoster[i];
                        Student temp2 = tempRoster[j];
                        if (temp.compareTo(temp2) < 0) {
                            Student tempStu = tempRoster[i];
                            tempRoster[i] = tempRoster[j];
                            tempRoster[j] = tempStu;
                        }
                    }
                }
            }
            // Print New Sorted Temp Roster
            for (int k = 0; k < this.roster.length; k++) {
                if (tempRoster[k] != null) {
                    tempRoster[k].toString();
                }
            }
            System.out.println("* end of roster **");
        }
    }

    /**
     * Print the Roster sorted by School Major
     */
    public void printBySchoolMajor() {
        if (this.roster == null) {
            System.out.println("Student roster is empty!");
        } else {
            System.out.println("* Student roster sorted by school, major **");
            Student[] tempRoster = this.roster;
            for (int i = 0; i < this.roster.length; i++) { // Start of sort
                for (int j = i + 1; j < this.roster.length; j++) {
                    if (tempRoster[i] != null && tempRoster[j] != null) {
                        Student temp = tempRoster[i];
                        Student temp2 = tempRoster[j];

                        Major majori = temp.getMajor();
                        Major majorj = temp2.getMajor();

                        String majoriString = majori.toString(); // Turn major into strings
                        String majorjString = majorj.toString();
                        char majoriChar = majoriString.charAt(0); // Turn major strings into chars
                        char majorjChar = majorjString.charAt(0);

                        if (majoriChar > majorjChar) {
                            Student tempStu = tempRoster[i];
                            tempRoster[i] = tempRoster[j];
                            tempRoster[j] = tempStu;
                        }
                    }
                }
            }
            // Print New Sorted Temp Roster
            for (int k = 0; k < this.roster.length; k++) {
                if (tempRoster[k] != null) {
                    tempRoster[k].toString();
                }
            }
            System.out.println("* end of roster **");
        }
    }

    /**
     * Print the Roster by standing
     */
    public void printByStanding() {
        if (this.roster == null) {
            System.out.println("Student roster is empty!");
        } else {
            System.out.println("* Student roster sorted by standing **");
            Student[] tempRoster = this.roster;
            for (int i = 0; i < this.roster.length; i++) { // Start of sort
                for (int j = i + 1; j < this.roster.length; j++) {
                    if (tempRoster[i] != null && tempRoster[j] != null) {

                        Student temp = tempRoster[i]; // Get credits completed for both student
                        Student temp2 = tempRoster[j];

                        int crediti = temp.getCreditCompleted();
                        int creditj = temp2.getCreditCompleted();

                        if (crediti > creditj) { // Compare both students credits
                            Student tempStu = tempRoster[i];
                            tempRoster[i] = tempRoster[j];
                            tempRoster[j] = tempStu;
                        }
                    }
                }
            }
            // Print New Sorted Temp Roster
            for (int k = 0; k < this.roster.length; k++) {
                if (tempRoster[k] != null) {
                    tempRoster[k].toString();
                }
            }
            System.out.println("* end of roster **");
        }
    }
}
