public class Student implements Comparable<Student> {
    private Profile profile;
    private Major major; //Major is an enum type
    private int creditCompleted;
    public Student(Profile profile, Major major, int creditCompleted) {
        this.profile = profile;
        this.major = major;
        this.creditCompleted = creditCompleted;
    }

    private String printMajor(Major myMajor){
        switch(myMajor) {
            case CS:
                return "(01:198 CS SAS)";
            case MATH:
                return "(01:640 MATH SAS)";
            case EE:
                return "(14:332 EE SOE)";
            case ITI:
                return "(04:547 ITI SC&I)";
            case BAIT:
                return "(33:136 BAIT RBS)";
        }
        return "";
    }

    public String printStudentRoster() {
        return printStudentProfile() + " " + printMajor(this.major) + " credits completed: " + creditCompleted;
    }

    public String printStudentProfile() {
        return this.profile.printProfile();
    }


    public int compareTo(Student student) {
        return (this.major).compareTo(student.major);
    }
}