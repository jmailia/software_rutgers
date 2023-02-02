public class Roster {
    private Student[] roster;
    private int size;

    public Roster() {}

    private int find(Student student) {
        return 1;
    } //search the given student in roster

    private void grow() { //increase the array capacity by 4
        //System.out.println("Now Growing"); //TODO: TO REMOVE THIS println
        Student[] temp = new Student[(roster == null) ? 4 : roster.length+4]; // transfer old roster info into new temp array
        //System.out.println(temp.length); //TODO: TO REMOVE THIS println
        for (int j = 0; j < ((roster == null) ? 0 : temp.length); j++) {
            temp[j] = ((roster == null || j >= roster.length) ? null : roster[j]);
        }
        roster = temp;
    }

    public boolean add(Student student) { //add student to end of array
        if (roster == null || roster[roster.length-1] != null) { //if the roster is fully empty, or if it is entirely full
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
    public boolean remove(Student student){return true;}//maintain the order after remove
    public boolean contains(Student student){return true;} //if the student is in roster
    public void print () {//print roster sorted by profiles
        if (roster == null) {
            System.out.println("Student roster is empty!");
        } else {
            System.out.println(roster.length);
            System.out.println("* Student roster sorted by last name, first name, DOB **");

            for (int x=0;x<roster.length;x++) { //THIS IS NOT SORTED AND NEEDS TO BE REWORKED, THIS IS JUST FOR DEBUGGING
                if (roster[x]!=null) {
                    System.out.println(roster[x].printStudentRoster()); //print each student on the roster
                }
            }
        }
    }
    public void printBySchoolMajor() {
        Student[] tempRoster = this.roster;
        for (int i = 1; i < size; i++) {
            Student temp = tempRoster[i];
            Major majori = temp.getMajor();
            int j = i-1;
            Student temp2 = tempRoster[j];
            Major majorj = temp2.getMajor();
            char majoriChar = majori.toString().charAt(0);
            char majorjChar = majorj.toString().charAt(0);
            while ((j > -1) && (Character.compare(majorjChar, majoriChar) > 0)) {
                tempRoster[j+1] = tempRoster[j];
                j--;
            }
            tempRoster[j+1] = temp;
        }
        for (int i = 0; i < size; i++){
            Student tempStu = tempRoster[i];
            String profile = tempStu.printStudentProfile();
            Major major = tempStu.getMajor();
            int credit = tempStu.getCreditCompleted();
            System.out.println(profile + " " + major + " " + credit);
        }
    }
    public void printByStanding() {
        Student[] tempRoster = this.roster;
        for (int i = 1; i < size; i++) {
            Student temp = tempRoster[i];
            int crediti = temp.getCreditCompleted();
            int j = i-1;
            Student temp2 = tempRoster[j];
            int creditj = temp2.getCreditCompleted();
            while ((j > -1) && (creditj > crediti)) {
                tempRoster[j+1] = tempRoster[j];
                j--;
            }
            tempRoster[j+1] = temp;
        }
        for (int i = 0; i < size; i++){
            Student tempStu = tempRoster[i];
            String profile = tempStu.printStudentProfile();
            Major major = tempStu.getMajor();
            int credit = tempStu.getCreditCompleted();
            System.out.println(profile + " " + major + " " + credit);
        }
    }
}
