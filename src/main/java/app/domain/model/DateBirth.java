package app.domain.model;

import app.domain.model.Exceptions.InvalidDateException;

public class DateBirth {
    private int year;
    private int month;
    private int day;


    public static DateBirth parse(String date) {
        try {
            String[] dateComponents = date.split("/");
            return new DateBirth(Integer.parseInt(dateComponents[0]),
                    Integer.parseInt(dateComponents[1]),
                    Integer.parseInt(dateComponents[2]));
        } catch (NumberFormatException | IndexOutOfBoundsException ex) {
            throw new InvalidDateException();
        }
    }

    public DateBirth(int day, int month, int year ){
        setDay(day);
        setMonth(month);
        setYear(year);
    }
    public int getYear() {
        return year;
    }

    public void setDay(int day) {
        if(day <0 || day> 31){
            throw new InvalidDateException("Invalid Day!");
        }
        this.day = day;
    }
    public void setMonth(int month) {
        if (month<0 || month >12){
            throw new InvalidDateException("Invalid Month!");
        }
        this.month = month;
    }
    public void setYear(int year) {
        if (year<1870 || year>2021){
            throw new InvalidDateException("Invalid Year!");
        }
        this.year = year;
    }

    @Override
    public String toString(){
        return String.format("%s/%s/%s", day, month, year);
    }
}
