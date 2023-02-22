/* @author Henry Hecht */
/* @author Aidan Cronin */

public class Enrollment {
    private EnrollStudent[] enrollStudents;
    private int size;

    public EnrollStudent[] getEnrollStudents(){
        return this.enrollStudents;
    }

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
        size = this.enrollStudents.length;
    }

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
        return -1;
    } //search the given student in enrollStudent[]

    public void remove(EnrollStudent enrollStudent) {
        for (int indexOfCoveredOverStudent = find(enrollStudent); indexOfCoveredOverStudent < size - 1;
             indexOfCoveredOverStudent++) {
            this.enrollStudents[indexOfCoveredOverStudent] = this.enrollStudents[indexOfCoveredOverStudent + 1];
        }
        // in cases where a full Enrollment has to remove a student, we will explicitly set the last spot to null so there is no duplicated second-to-last student,
        this.enrollStudents[size - 1] = null;
    }

    public boolean contains(EnrollStudent enrollStudent) {
        if (find(enrollStudent) >= 0){
            return true;
        }
        return false;
    } //if the student is in enrollStudents

    public void print() {
        for (int k = 0; k < size; k++) {
            if (enrollStudents[k] != null) {
                System.out.println(enrollStudents[k].toString());
            }
        }
    }
}
