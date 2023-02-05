public class Roster {
    private Student[] roster;
    private int size;
    public Roster() {}

    /**
     * Searches for the given student in the Roster
     * @param student the student we want to find
     * @return the student's index if it is found, otherwise -1 if the student is not in the roster
     */
    private int find(Student student) {
        return 1;
    }


    /**
     * Enables the Roster to have an initial capacity of 4, automatically grow (increases) the capacity by 4
     * whenever it is full, and never decrease in capacity.
     */
    private void grow() {
        //System.out.println("Now Growing"); //TODO: TO REMOVE THIS println
        int tempLength = 0;
        if (roster == null) {
            tempLength = 4;
        } else {
            tempLength = roster.length + 4;
        }
        Student[] temp = new Student[tempLength]; // transfer old roster info into new temp array
        //System.out.println(temp.length); //TODO: TO REMOVE THIS println

        int jMax;
        if (roster == null) {
            jMax = 0;
        } else {
            jMax = temp.length;
        }
        for (int j = 0; j < jMax; j++) {
            if (roster == null || j >= roster.length) {
                temp[j] = null;
            } else {
                temp[j] = roster[j];
            }
        }
        roster = temp;
    }

    /**
     * Add a student to the end of the array, first determining whether the Roster needs to grow
     * @param student
     * @return true if the student was successfully added, false otherwise.
     */
    public boolean add(Student student) { //add student to end of array

        if (roster == null || roster[roster.length - 1] != null) { //if the roster is fully empty, or if it is entirely full
            grow();
        }
        for (int x = 0; x < roster.length; x++) { //add the student at the smallest index
            if (roster[x] == null) {
                roster[x] = student;
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
        return true;
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
                        Student temp = tempRoster[i]; // Turn Student into profile then get a string of lName, fName, and DOB
                        Student temp2 = tempRoster[j];

                        Profile stuProf1 = temp.getProfile();
                        Profile stuProf2 = temp.getProfile();

                        String student1 = (stuProf1.getLname() + stuProf1.getFname() + stuProf1.getDob());
                        String student2 = (stuProf2.getLname() + stuProf2.getFname() + stuProf2.getDob());

                        if (student1.compareTo(student2) > 0) {
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
                    tempRoster[k].printStudentRoster();
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

                        String majoriChar = majori.toString(); // Turn major into strings
                        String majorjChar = majorj.toString();

                        if (majoriChar.compareTo(majorjChar) > 0) {
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
                    tempRoster[k].printStudentRoster();
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
                    tempRoster[k].printStudentRoster();
                }
            }
            System.out.println("* end of roster **");
        }
    }
}

