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
