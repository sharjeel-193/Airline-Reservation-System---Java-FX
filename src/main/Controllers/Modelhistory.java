package main.Controllers;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Modelhistory {
 StringProperty date;
StringProperty dest,from;
     IntegerProperty seat;
    StringProperty   flight;

   public Modelhistory(String flight, int seat, String date,String from,String dest) {
        this.date =new SimpleStringProperty(date) ;
this.from=new SimpleStringProperty(from);
       this.dest=new SimpleStringProperty(dest);
        this.seat = new SimpleIntegerProperty(seat);
        this.flight = new SimpleStringProperty(flight);
    }

    public Modelhistory() {

    }

    public void setDate(String value) {
        this.date.set(value);
    }



    public void setSeat(int value) {
        this.seat.set(value);
    }

    public void setFlight(String value) {
        this.flight.set(value);
    }
    public void setFrom(String value) {
        this.from.set(value);
    }
    public void setdest(String value) {
        this.dest.set(value);
    }

    public String getDate() {
        return date.get();
    }

    public String getfrom() {
        return from.get();
    }
    public String getdest() {
        return dest.get();
    }

    public int getSeat() {
        return seat.get();
    }

    public String getFlight() {
        return flight.get();
    }
    public IntegerProperty seat(){
        return  seat;
    }
    public StringProperty date(){
        return  date;
    }
    public StringProperty flight(){
        return  flight;
    }
}
