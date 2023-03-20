package com.example.cs213project3;/* @author Henry Hecht */
/* @author Aidan Cronin */

/**
 * Governs the array-based linear data structure to hold the list of the students,
 * where an instance of this class is a growable container defined by given constraints
 */
public class Roster {

    private Student[] roster;
    private int size;

    /**
     * The Roster which holds the list of students
     */
    public Roster() {}

    /**
     * Constant identifier which is returned if the student is not in the Roster
     */
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
            for (int k = 0; k < size; k++) {
                if (this.roster[k] != null) {
                    if (this.roster[k].getProfile().equals(student.getProfile())) {
                        this.roster[k].setMajor(student.getMajor());
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
        // transfer old roster info into new temp array;
        // this new temp array is of length 4 if the roster is initially empty;
        // otherwise the temp array's length is the roster's length plus 4

        Student[] temp = new Student[(this.roster == null) ? 4 : size + 4];

        for (int j = 0; j < ((this.roster == null) ? 0 : temp.length); j++) {
            if (this.roster == null || j >= size) {
                temp[j] = null;
            } else {
                temp[j] = this.roster[j];
            }
        }
        this.roster = temp;
        size = this.roster.length;
    }

    /**
     * Add a student to the end of the array, first determining whether the Roster needs to grow
     * @param student the student that might be added to the end of the array
     * @return true if the student was successfully added, false otherwise.
     */
    public boolean add(Student student) { //add student to end of array

        size = (this.roster == null ? 0 : this.roster.length); //set the size to 0 if the roster is null,
        // otherwise set it to its length

        if (this.roster == null || this.roster[size - 1] != null) { //if the roster is fully empty, or if it is entirely full
            grow();
        }
        for (int x = 0; x < size; x++) { //add the student at the smallest index
            if (this.roster[x] == null) {
                this.roster[x] = student;
                break;
            }
        }
        return true;
    }

    /**
     * Removes a student from the roster while maintaining the order of other students
     * @param student the student we want to remove
     * @return true if the student was successfully removed, false otherwise.
     */
    public boolean remove(Student student) {
        for (int indexOfCoveredOverStudent = find(student); indexOfCoveredOverStudent < size - 1;
             indexOfCoveredOverStudent++) {
            this.roster[indexOfCoveredOverStudent] = this.roster[indexOfCoveredOverStudent + 1];
        }
        // in cases where a full roster has to remove a student, we will explicitly set the last spot to null so there is no duplicated second-to-last student,
        this.roster[size - 1] = null;
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
     *                            "PC", which compares the students' majors;
     *                            "PCredit", which compares the students by credit count.
     * @return the Roster which was sorted by the comparison method for the corresponding print command
     */
    public Student[] insertionSort(String whichP) {
        Student[] tempRoster = this.roster;
        for (int i = 0; i < size; i++) { // Start of sort
            for (int j = i + 1; j < size; j++) {
                if (tempRoster[i] != null && tempRoster[j] != null) {
                    //"P", which compares the students' profiles
                    if (whichP.equals("P")) {
                        if (tempRoster[i].compareTo(tempRoster[j]) < 0) {
                            tempRoster = swapStudentsForInsertionSort(tempRoster, i, j);
                        }
                    } else if (whichP.equals("Pcredit")) {
                        if (tempRoster[i].getCreditCompleted() < tempRoster[j].getCreditCompleted()) {
                            tempRoster = swapStudentsForInsertionSort(tempRoster, i, j);
                        }
                    } else {
                        if (tempRoster[i].getMajor().majorOrderWhenSorted(tempRoster[j].getMajor())) {
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
    private Student[] swapStudentsForInsertionSort(Student[] tempRoster, int student1Index, int student2Index) {
        Student tempStu = tempRoster[student1Index];
        tempRoster[student1Index] = tempRoster[student2Index];
        tempRoster[student2Index] = tempStu;
        return tempRoster;
    }
    /**
     * Print the roster sorted by profiles (last name, first name, DOB)
     *///print roster sorted by profiles
    public String print() {return printRosterLines(insertionSort("P"));}

    /**
     * Print the Roster sorted by School Major
     */
    public String printBySchoolMajor() {
        return printRosterLines(insertionSort("PC"));
    }

    /**
     * Print the Roster by standing
     */
    public String printByStanding() {
        String printByStanding = "";
        for (int year = 1; year <=4; year++) {
            for (int k = 0; k < size; k++) {
                if (this.roster[k] != null) {
                    if ((year == 1 && this.roster[k].getCreditCompleted() < 30) ||
                            (year == 2 && this.roster[k].getCreditCompleted() >= 60
                                    && this.roster[k].getCreditCompleted() < 90) ||
                            (year == 3 && this.roster[k].getCreditCompleted() >= 90) ||
                            (year == 4 && this.roster[k].getCreditCompleted() >= 30
                                    && this.roster[k].getCreditCompleted() < 60)) {
                        printByStanding += this.roster[k].toString() + "\n";
                    }
                }
            }
        }
        return printByStanding;
    }

    /**
     * For each student in the roster, if their credits are over 120,
     * add the student to an array sorted by credit count and print the students
     */
    public String printCanGraduate() {
        String graduationListToPrint = "** list of students eligible for graduation **\n";
        // Printing out list of students eligible for graduation
        Student[] toBeGraduated = insertionSort("Pcredit");
        for (int currentStudent = 0; currentStudent<toBeGraduated.length; currentStudent++) {
            if(toBeGraduated[currentStudent]!=null && toBeGraduated[currentStudent].getCreditCompleted()>=120){
                graduationListToPrint += toBeGraduated[currentStudent].toString() + "\n";
            }
        }
        return graduationListToPrint;
    }

    /**
     * Helper method to print the roster with the correct formatting for each student
     * @param roster the roster to be printed
     */
    private String printRosterLines (Student[] roster) {
        String rosterLines = "";
        // Print New Sorted Temp Roster
        for (int k = 0; k < size; k++) {
            if (roster[k] != null) {
                rosterLines += roster[k].toString() + "\n";
            }
        }
        return rosterLines;
    }
}