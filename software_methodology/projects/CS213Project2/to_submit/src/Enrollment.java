/* @author Henry Hecht */
/* @author Aidan Cronin */

public class Enrollment {
    private EnrollStudent[] enrollStudents;
    private int size;

    /**
     * The Enrollment which holds the list of students
     */
    public Enrollment() {}

    /**
     * Constant identifier which is returned if the student is not in the Roster
     */
    public static final int NOT_FOUND = -1;

    /**
     * Getter method for enrollStudents
     * @return enrollStudents The enrollStudents we want
     */
    public EnrollStudent[] getEnrollStudents(){
        return this.enrollStudents;
    }

    /**
     * Enables the Enrollment to have an initial capacity of 4, automatically grow (increases) the capacity by 4
     * whenever it is full, and never decrease in capacity.
     */
    private void grow() {
        // transfer old roster info into new temp array;
        // this new temp array is of length 4 if the roster is initially empty;
        // otherwise the temp array's length is the roster's length plus 4

        EnrollStudent[] temp = new EnrollStudent[(this.enrollStudents == null) ? 4 : size + 4];

        for (int j = 0; j < ((this.enrollStudents == null) ? 0 : temp.length); j++) {
            if (this.enrollStudents == null || j >= size) {
                temp[j] = null;
            } else {
                temp[j] = this.enrollStudents[j];
            }
        }
        this.enrollStudents = temp;
        this.size = this.enrollStudents.length;
    }

    /**
     * Add a student to the end of an array, but determine if we need to grow() first
     * @param enrollStudent The enrollStudent that we want to add
     */
    public void add(EnrollStudent enrollStudent) {
        size = (this.enrollStudents == null ? 0 : this.enrollStudents.length); //set the size to 0 if the roster is null,
        // otherwise set it to its length
        if (this.enrollStudents == null || this.enrollStudents[size - 1] != null) { //if the roster is fully empty, or if it is entirely full
            grow();
        }
        for (int x = 0; x < size; x++) { //add the student at the smallest index
            if (this.enrollStudents[x] == null) {
                this.enrollStudents[x] = enrollStudent;
                break;
            }
        }
    }

    /**
     * Method that finds student in a given enrollStudent[] and returns the index of
     * where it is located
     * @param enrollStudent The enrollStudent that we want to add
     * @return index of where student is at
     */
    public int find(EnrollStudent enrollStudent) {
        if (this.enrollStudents!=null) {
            for (int k = 0; k < size; k++) {
                if (this.enrollStudents[k] != null) {
                    if (this.enrollStudents[k].getProfile().equals(enrollStudent.getProfile())) {
                        return k;
                    }
                }
            }
        }
        return NOT_FOUND;
    } //search the given student in enrollStudent[]

    /**
     * Method finds the index of given student and removes it from the array
     * @param enrollStudent The enrollStudent that we want to move
     */
    public void remove(EnrollStudent enrollStudent) {
        for (int indexOfCoveredOverStudent = find(enrollStudent); indexOfCoveredOverStudent < size - 1;
             indexOfCoveredOverStudent++) {
            this.enrollStudents[indexOfCoveredOverStudent] = this.enrollStudents[indexOfCoveredOverStudent + 1];
        }
        // in cases where a full Enrollment has to remove a student, we will explicitly set the last spot to null so there is no duplicated second-to-last student,
        this.enrollStudents[size - 1] = null;
    }

    /**
     * Method determines whether a given student is in the enrollStudent array
     * @param enrollStudent The enrollStudent we want to find
     * @return True if student is in array, false if not
     */
    public boolean contains(EnrollStudent enrollStudent) {
        if (find(enrollStudent) >= 0){
            return true;
        }
        return false;
    } //if the student is in enrollStudents

    /**
     * Print this objects array in the order that students
     * were added
     */
    public void print() {
        for (int k = 0; k < this.enrollStudents.length; k++) {
            if (enrollStudents[k] != null) {
                System.out.println(enrollStudents[k].toString());
            }
        }
    }
}
