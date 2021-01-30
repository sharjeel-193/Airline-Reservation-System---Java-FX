package main.Models;

import java.time.LocalDateTime;

public class FlightModel {

    String flight_no, origin, destination, time, status;
    int seats;

    public FlightModel(String flight_no, String origin, String destination, String time, int seats, String status) {
        this.flight_no = flight_no;
        this.origin = origin;
        this.destination = destination;
        this.time = time;
        this.seats = seats;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setFlight_no(String flight_no) {
        this.flight_no = flight_no;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getSeats() {
        return seats;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public String getFlight_no() {
        return flight_no;
    }

    public String getTime() {
        return time;
    }
}
