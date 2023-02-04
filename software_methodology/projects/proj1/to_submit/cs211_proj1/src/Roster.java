public class Roster {
    private Student[] roster;
    private int size;

    public Roster() {
    }

    private int find(Student student) {
        return 1;
    } //search the given student in roster

    private void grow() { //increase the array capacity by 4
        //System.out.println("Now Growing"); //TODO: TO REMOVE THIS println
        Student[] temp = new Student[(roster == null) ? 4 : roster.length + 4]; // transfer old roster info into new temp array
        //System.out.println(temp.length); //TODO: TO REMOVE THIS println
        for (int j = 0; j < ((roster == null) ? 0 : temp.length); j++) {
            temp[j] = ((roster == null || j >= roster.length) ? null : roster[j]);
        }
        roster = temp;
    }

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

    public boolean remove(Student student) {
        return true;
    }//maintain the order after remove

    public boolean contains(Student student) {
        return true;
    } //if the student is in roster

    public void print() {//print roster sorted by profiles
        if (this.roster == null) {
            System.out.println("Student roster is empty!");
        } else {
            System.out.println(this.roster.length);
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
                    Student tempStu = tempRoster[k];
                    String profile = tempStu.printStudentProfile();
                    Major major = tempStu.getMajor();
                    int credit = tempStu.getCreditCompleted();
                    System.out.println(profile + " " + major + " " + credit);
                }
            }
        }
    }

    public void printBySchoolMajor() {
        if (this.roster == null) {
            System.out.println("Student roster is empty!");
        } else {
            System.out.println(this.roster.length);
            System.out.println("* Student roster sorted by School Major **");
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
                            if (tempRoster[i] != null) {
                                Student tempStu = tempRoster[i];
                                tempRoster[i] = tempRoster[j];
                                tempRoster[j] = tempStu;
                            }
                        }
                    }
                }
            }
            // Print New Sorted Temp Roster
            for (int k = 0; k < this.roster.length; k++) {
                System.out.println(tempRoster[k].printStudentProfile() + " " + tempRoster[k].getMajor() + " " +
                        tempRoster[k].getCreditCompleted());
            }
        }
    }

    public void printByStanding() {
        if (this.roster == null) {
            System.out.println("Student roster is empty!");
        } else {
            System.out.println(this.roster.length);
            System.out.println("* Student roster sorted by Standing **");
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
                    Student tempStu = tempRoster[k];
                    String profile = tempStu.printStudentProfile();
                    Major major = tempStu.getMajor();
                    int credit = tempStu.getCreditCompleted();
                    System.out.println(profile + " " + major + " " + credit);
                }
            }
        }
    }
