/* @author Henry Hecht */
/* @author Aidan Cronin */

public class Roster {
    private Student[] roster;
    private int size;
    public Roster() {}
    public static final int NOT_FOUND = -1;

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

        if (this.roster!=null) {
            for (int k = 0; k < this.roster.length; k++) {
                if (this.roster[k] != null) {
                    if (this.roster[k].getProfile().equals(student.getProfile())) {
                        return k;
                    }
                }
            }
        }
        return NOT_FOUND;
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
        for (int indexOfCoveredOverStudent = find(student); indexOfCoveredOverStudent<this.roster.length-1;
             indexOfCoveredOverStudent++) {
            this.roster[indexOfCoveredOverStudent] = this.roster[indexOfCoveredOverStudent+1];
        }
        // in cases where a full roster has to remove a student, we will explicitly set the last spot to null so there is no duplicated second-to-last student,
        this.roster[this.roster.length-1] = null;
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
     * This method does the insertion sort for all P commands, calling swapStudentsForInsertionSort(tempRoster, i, j)
     * for each in-place swap
     * @param whichP designates how we will compare each two students and will equal:
     *                            "P", which compares the students' profiles;
     *                            "PC", which compares the students' majors.
     * @return the Roster which was sorted by the comparison method for the corresponding print command
     */
    public Student[] insertionSort(String whichP) {
        Student[] tempRoster = this.roster;
        for (int i = 0; i < this.roster.length; i++) { // Start of sort
            for (int j = i + 1; j < this.roster.length; j++) {
                if (tempRoster[i] != null && tempRoster[j] != null) {
                    //"P", which compares the students' profiles
                    if (whichP.equals("P")) {
                        if (tempRoster[i].compareTo(tempRoster[j]) < 0) {
                            tempRoster = swapStudentsForInsertionSort(tempRoster, i, j);
                        }
                    }  else {
                        if (tempRoster[i].getMajor().toString().charAt(0) > tempRoster[j].getMajor().toString().charAt(0)) {
                            tempRoster = swapStudentsForInsertionSort(tempRoster, i, j);
                        }
                    }
                }
            }
        }
        return tempRoster;
    }

    /**
     * Helper method which swaps the students in-place for insertion sort
     * @param tempRoster the Student[] where the students will be in-place swapped
     * @param student1Index the index of Student 1 in tempRoster
     * @param student2Index the index of Student 2 in tempRoster
     * @return an updated tempRoster with both students swapped
     */
    public Student[] swapStudentsForInsertionSort(Student[] tempRoster, int student1Index, int student2Index) {
        Student tempStu = tempRoster[student1Index];
        tempRoster[student1Index] = tempRoster[student2Index];
        tempRoster[student2Index] = tempStu;
        return tempRoster;
    }
    /**
     * Print the roster sorted by profiles (last name, first name, DOB)
     */
    public void print() {//print roster sorted by profiles
        printRosterLines(insertionSort("P"));
    }

    /**
     * Print the Roster sorted by School Major
     */
    public void printBySchoolMajor() {
        printRosterLines(insertionSort("PC"));
    }

    /**
     * Print the Roster by standing
     */
    public void printByStanding() {
        for(int k = 0; k < this.roster.length; k++){
            if(this.roster[k] != null)
                if(this.roster[k].getCreditCompleted() < 30)
                    System.out.println(this.roster[k].toString());
        }
        for(int k = 0; k < this.roster.length; k++){
            if(this.roster[k] != null)
                if(this.roster[k].getCreditCompleted() >= 60 && this.roster[k].getCreditCompleted() < 90)
                    System.out.println(this.roster[k].toString());
        }
        for(int k = 0; k < this.roster.length; k++){
            if(this.roster[k] != null)
                if(this.roster[k].getCreditCompleted() >= 90)
                    System.out.println(this.roster[k].toString());
        }
        for(int k = 0; k < this.roster.length; k++){
            if(this.roster[k] != null)
                if(this.roster[k].getCreditCompleted() >= 30 && this.roster[k].getCreditCompleted() < 60)
                    System.out.println(this.roster[k].toString());
        }
    }

    /**
     * Print the roster with the correct formatting for each student
     * @param roster the roster to be printed
     */
    public void printRosterLines (Student[] roster) {
        // Print New Sorted Temp Roster
        for (int k = 0; k < roster.length; k++) {
            if (roster[k] != null) {
                System.out.println(roster[k].toString());
            }
        }
    }
}
