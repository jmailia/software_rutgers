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

    public Date() {
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH);
        month++;
        this.month = month;
        this.day = calendar.get(Calendar.DATE);
        this.year = calendar.get(Calendar.YEAR);
    }

    public Date(int month, int day, int year){
        this.month = month;
        this.day = day;
        this.year = year;
    }

    public Date(String date) {
        String[] arrDate = date.split("/");
        this.month = Integer.parseInt(arrDate[0]);
        this.day = Integer.parseInt(arrDate[1]);;
        this.year = Integer.parseInt(arrDate[2]);;
    }

    @Override
    public int compareTo(String date2) {
        String date1 = this.month + "/" + this.day + "/" + this.year;
        int length1 = date1.length();
        int length2 = date2.length();
        int limit = Math.min(length1, length2);
        int i = 0;
        while (i < limit) {
            char ch1 = date1.charAt(i);
            char ch2 = date2.charAt(i);
            if (ch1 != ch2) {
                return ch1 - ch2;
            }
            i++;
        }
        return length1 - length2;
    }

    @Override
    public boolean equals(Date date) {
        String date1 = this.month + "/" + this.day + "/" + this.year;
        String date2 = date.toString();
        if(date1.compareTo(date2) == 0){
            return true;
        }
        return false;
    }

    @Override
    public String toString(){
        return this.month + "/" + this.day + "/" + this.year;
    }

    public boolean isValid() {
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
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -16);
        if (calendar.get(Calendar.YEAR) > this.year){
            return true;
        }
        if (calendar.get(Calendar.YEAR) < this.year){
            return false;
        }
        if (this.year < 1900){
            return false;
        }
        if (calendar.get(Calendar.YEAR) == this.year){
            if (calendar.get(Calendar.MONTH) > this.month){
                if (calendar.get(Calendar.DATE) > this.day){
                    return false;
                }
            }
        }
        return true;
    }
}
