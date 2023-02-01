public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;
    public Date() { //create an object with today’s date (see Calendar class)
        this.year = 2000;
        this.month = 9;
        this.day = 5;
    }
    public Date(String date) { //take “mm/dd/yyyy” and create a Date object
        this.year = 2000;
        this.month = 9;
        this.day = 5;
    }

    public String printDate() {
        return this.day + "/" + this.month + "/" + this.year;
    }
    public boolean isValid() {return true;} //check if a date is a valid calendar date
    @Override public int compareTo(Date ree) {
        return this.month - ree.month;
    }
}