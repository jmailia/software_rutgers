/* @author Henry Hecht */
/* @author Aidan Cronin */

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

        if (this.roster!=null) {
            for (int k = 0; k < this.roster.length; k++) {
                if (this.roster[k] != null) {
                    if (this.roster[k].equals(student)) {
                        return k;
                    }
                }
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

    public Student[] insertionSortForEachRoster(int choiceForComparison) {
        Student[] tempRoster = this.roster;
        for (int i = 0; i < this.roster.length; i++) { // Start of sort
            for (int j = i + 1; j < this.roster.length; j++) {
                if (tempRoster[i] != null && tempRoster[j] != null) {
                    if (choiceForComparison == 0) {
                        // Compare the two student profiles
                        if (tempRoster[i].compareTo(tempRoster[j]) < 0) {
                            tempRoster = swapStudentsForInsertionSort(tempRoster, i, j);
                        }
                    } else if (choiceForComparison == 1) {
                        // Turn both majors into strings and compare the first chars
                        if (tempRoster[i].getMajor().toString().charAt(0) > tempRoster[j].getMajor().toString().charAt(0)) {
                            tempRoster = swapStudentsForInsertionSort(tempRoster, i, j);
                        }
                    } else {
                        // Get and compare each student's completed credits
                        if (tempRoster[i].getCreditCompleted() > tempRoster[j].getCreditCompleted()) {
                            tempRoster = swapStudentsForInsertionSort(tempRoster, i, j);
                        }
                    }
                }
            }
        }
        return tempRoster;
    }

    /**
     * Helper method which swaps the students for insertion sort
     * @param tempRoster
     * @param student1Index
     * @param student2Index
     * @return
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
        printRosterLines(insertionSortForEachRoster(0));
    }

    /**
     * Print the Roster sorted by School Major
     */
    public void printBySchoolMajor() {
        printRosterLines(insertionSortForEachRoster(1));
    }

    /**
     * Print the Roster by standing
     */
    public void printByStanding() {
        printRosterLines(insertionSortForEachRoster(2));
    }

    public void printRosterLines (Student[] tempRoster) {
        // Print New Sorted Temp Roster
        for (int k = 0; k < tempRoster.length; k++) {
            if (tempRoster[k] != null) {
                System.out.println(tempRoster[k].toString());
            }
        }
    }
}