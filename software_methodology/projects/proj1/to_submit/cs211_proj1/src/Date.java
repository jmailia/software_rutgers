/* @author Henry Hecht */
/* @author Aidan Cronin */

import java.util.Calendar;

/**
 * The Date class creates Date objects, converts strings into these objects,
 * and determines whether a date is a valid calendar date
 */
public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;

    /**
     * Constant identifier for a quadrennial
     */
    public static final int QUADRENNIAL = 4;
    /**
     * Constant identifier for a centennial
     */
    public static final int CENTENNIAL = 100;
    /**
     * Constant identifier for a quatercentennial
     */
    public static final int QUATERCENTENNIAL = 400;

    /**
     * Get method for the year
     * @return the year
     */
    public int getYear(){
        return this.year;
    }
    /**
     * Get method for the month
     * @return the month
     */
    public int getMonth(){
        return this.month;
    }
    /**
     * Get method for the day
     * @return the day
     */
    public int getDay(){
        return this.day;
    }

    /**
     * Default constructor for the Date class which creates an instance
     * from calendar class to get today's day, month and year.
     */
    public Date() {
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH);
        month++;
        this.month = month;
        this.day = calendar.get(Calendar.DATE);
        this.year = calendar.get(Calendar.YEAR);
    }

    /**
     * Three argument constructor for Date class which creates an instance
     * with given month, day and year arguments to create the specified
     * date.
     * @param month The month of the given date
     * @param day The day of the given date
     * @param year The year of the given date
     */
    public Date(int month, int day, int year) {
        this.month = month;
        this.day = day;
        this.year = year;
    }

    /**
     * One argument constructor for Date class which creates an instance
     * by taking a given string containing a date and translating
     * that into an object.
     * @param date The date, provided as formatted string, which will generate an instance
     */
    public Date(String date) {
        String[] arrDate = date.split("/");
        this.month = Integer.parseInt(arrDate[0]);
        this.day = Integer.parseInt(arrDate[1]);;
        this.year = Integer.parseInt(arrDate[2]);;
    }

    /**
     * Method to compare two given dates to each other.
     * @param dateToCompare the date to compare
     * @return 0 if dates are equal, a positive number if original date is younger than compared date,
     * or a negative number if original date is older than compared date
     */
    @Override
    public int compareTo(Date dateToCompare) {
        String[] arrDate = dateToCompare.toString().split("/");

        int yearComp = this.year - Integer.parseInt(arrDate[2]);
        int monthComp = this.month - Integer.parseInt(arrDate[0]);
        int dayComp = this.day - Integer.parseInt(arrDate[1]);

        if(yearComp > 0) {
            return 1;
        }
        if(yearComp < 0) {
            return -1;
        }
        if(monthComp > 0) {
            return 1;
        }
        if(monthComp < 0) {
            return -1;
        }
        if(dayComp > 0) {
            return 1;
        }
        if(dayComp < 0) {
            return -1;
        }
        return 0;
    }

    /**
     * Method compares original date to given date and
     * determines if they are equal or not.
     * @param obj the date we are to compare
     * @return true if the dates are equal, otherwise false
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        final Date date = (Date) obj;
        String date1 = this.month + "/" + this.day + "/" + this.year;
        String date2 = date.toString();
        if(this.month == date.getMonth()) {
            if(this.day == date.getDay()) {
                if(this.year == date.getYear()){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Method takes a Date object and returns it formatted as a String.
     * @return a String containing the date separating the month, day, and year, by the String "/"
     */
    @Override
    public String toString(){
        return this.month + "/" + this.day + "/" + this.year;
    }

    /**
     * Method takes a Date object and determines whether the DOB is both on the calendar and was at least 16 years ago
     * @return false when the DOB is invalid, otherwise true
     */
    public boolean isValid() {
        return (isCalendarDateValid() && isStudentOver16());
    }

    /**
     * Method determines whether a calendar date is valid
     * @return true if the calendar date is valid, false otherwise
     */
    public boolean isCalendarDateValid() {
        if(this.month < 1 || this.day < 1 || this.year < 1900){
            return false;
        }
        if (this.month > 12){
            return false;
        }
        switch(this.month){
            case 1, 3, 5, 7, 8, 10, 12:
                if(this.day > 31){
                    return false;
                }
                break;
            case 4, 6, 9, 11:
                if (this.day > 30){
                    return false;
                }
                break;
            case 2:
                if (this.year % QUADRENNIAL == 0) {
                    if (this.year % CENTENNIAL == 0) {
                        if (this.year % QUATERCENTENNIAL == 0) {
                            if (this.day > 29) {
                                return false;
                            }
                        }
                    } else if (this.day > 29) {
                        return false;
                    }
                } else if (this.day > 28) {
                    return false;
                }
                break;
        }
        return true;
    }

    /**
     * Returns whether student is over the age of 16
     * @return true if student is over the age of 16, false otherwise
     */
    public boolean isStudentOver16() {
        Calendar calendar = Calendar.getInstance(); //Checks if date is older than 16 years
        calendar.add(Calendar.YEAR, -16);
        if (calendar.get(Calendar.YEAR) > this.year){
            return true;
        }
        else if (calendar.get(Calendar.YEAR) < this.year){
            return false;
        }
        else {
            if (calendar.get(Calendar.MONTH) > this.month) {
                return true;
            }
            else if (calendar.get(Calendar.MONTH) < this.month) {
                return false;
            }
            else {
                if (calendar.get(Calendar.DATE) < this.day) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Testbed implementing the test cases in isValid() of the Date class.
     * @param args Arguments for the testbed
     */
    public static void main(String args[]){
        Date test1 = new Date("4/20/2015");
        Date test2 = new Date("10/7/1885");
        Date test3 = new Date("13/17/2001");
        Date test4 = new Date("3/32/2003");
        Date test5 = new Date("4/31/2003");
        Date test6 = new Date("2/29/2003");
        Date test7 = new Date("2/30/2000");
        Date test8 = new Date("5/14/2004");
        Date test9 = new Date("8/31/1997");
        Date test10 = new Date("2/29/2000");
        Date test11 = new Date("2/28/2001");
        System.out.println(test1.isValid());
        System.out.println(test2.isValid());
        System.out.println(test3.isValid());
        System.out.println(test4.isValid());
        System.out.println(test5.isValid());
        System.out.println(test6.isValid());
        System.out.println(test7.isValid());
        System.out.println(test8.isValid());
        System.out.println(test9.isValid());
        System.out.println(test10.isValid());
        System.out.println(test11.isValid());
    }
}
