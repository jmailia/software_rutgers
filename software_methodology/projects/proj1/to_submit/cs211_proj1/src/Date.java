import java.util.Calendar;

public class Date {
    private int year;
    private int month;
    private int day;

    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUATERCENTENNIAL = 400;

    public int getYear(){
        return this.year;
    }
    public int getMonth(){
        return this.month;
    }
    public int getDay(){
        return this.day;
    }

    /**
     * Default constructor fir Date class. Creates an instance
     * from calendar class to get the current day, month and year.
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
     * Three argument constructor for Date class. Creates an instance
     * with given month, day and year arguments to create the specified
     * date.
     * @param month
     * @param day
     * @param year
     */
    public Date(int month, int day, int year){
        this.month = month;
        this.day = day;
        this.year = year;
    }

    /**
     * One argument constructor for Date class. Creates an instance
     * by taking a given string containing a date and translating
     * that into an object.
     * @param date
     */
    public Date(String date) {
        String[] arrDate = date.split("/");
        this.month = Integer.parseInt(arrDate[0]);
        this.day = Integer.parseInt(arrDate[1]);;
        this.year = Integer.parseInt(arrDate[2]);;
    }

    /**
     * Method to compare two given dates to each other. Will
     * return a 0 if dates are equal, a positive number if original date
     * is younger than compared date, and a negative number if original date is
     * older than compared date
     * @param date2
     * @return int

    @Override
    public int compareTo(String date2) {
        String[] arrDate = date2.split("/");
        int month2 = Integer.parseInt(arrDate[0]);
        int day2 = Integer.parseInt(arrDate[1]);;
        int year2 = Integer.parseInt(arrDate[2]);
        int yearComp = this.year - year2;
        int monthComp = this.month - month2;
        int dayComp = this.day - day2;
        switch(yearComp){ //Comparing years
            case 1:
                return 1;
            break;
            case 0:
                switch(monthComp){ //Comparing months
                    case 1:
                        return 1;
                    break;
                    case 0:
                        switch(dayComp){ //Comparing days
                            case 1:
                                return 1;
                            break;
                            case 0:
                                return 0;
                            break;
                            case -1:
                                return -1;
                            break;
                        }
                        break;
                    case -1:
                        return -1;
                    break;
                }
                break;
            case -1:
                return -1;
            break;
        }
        return 0;
    }


     * Method compares original date to given date and
     * determines if they are equal or not. If equal,
     * true is returned and if not equal, false is returned.
     * @param date
     * @return boolean

    @Override
    public boolean equals(Date date) {
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
     * Method takes a Date object and returns
     * it as a string.
     * @return String

    @Override
    public String toString(){
        return this.month + "/" + this.day + "/" + this.year;
    }
*/
    /**
     * Method takes a Date object and verifies if it is
     * a valid date. Returns false if not and true if it
     * is.
     * @return boolean
     */
    public boolean isValid() {
        if(this.month < 0 || this.day < 0 || this.year < 1900){
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
                if (this.year % QUADRENNIAL == 0){
                    if (this.year % CENTENNIAL == 0){
                        if (this.year % QUATERCENTENNIAL == 0){
                            if (this.day > 29){
                                return false;
                            }
                        }
                    }
                    else
                    if (this.day > 29){
                        return false;
                    }
                }
                else
                if (this.day > 28){
                    return false;
                }
                break;
        }
        Calendar calendar = Calendar.getInstance(); //Checks if date is older than 16 years
        calendar.add(Calendar.YEAR, -16);
        if (calendar.get(Calendar.YEAR) > this.year){
            return true;
        }
        if (calendar.get(Calendar.YEAR) < this.year){
            return false;
        }
        if (calendar.get(Calendar.YEAR) == this.year){
            if (calendar.get(Calendar.MONTH) > this.month) {
                return true;
            }
            if(calendar.get(Calendar.MONTH) == this.month)
                if (calendar.get(Calendar.DATE) < this.day){
                    return false;
                }
        }
        return true;
    }
}