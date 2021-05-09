package app.domain.model;

import app.domain.model.Exceptions.InvalidDateException;

import javax.sound.midi.InvalidMidiDataException;

public class DateBirth {
    private int year;
    private int month;
    private int day;

    public DateBirth(int day, int month, int year ){
        setDay(day);
        setMonth(month);
        setYear(year);
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
    public String toString() {
        return "" + day +"/"+month+"/"+year;
    }
}
