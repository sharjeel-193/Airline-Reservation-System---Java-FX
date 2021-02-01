package main.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import main.Database.DatabaseConnection;

import java.sql.*;

public class AddFlightController {
    ObservableList<String> locations = FXCollections.observableArrayList();
    DatabaseConnection dbConnection = new DatabaseConnection();
    Connection connectDB = dbConnection.getconnection();
    @FXML
    private ChoiceBox<String> departure_box;
    @FXML
    private ChoiceBox<String> arrival_box;
    @FXML
    private DatePicker departure_date;
    @FXML
    private TextField seats_field;
    @FXML
    private TextField flight_no_field;
    @FXML
    private Button add_btn;
    @FXML
    private Label add_message;
    public void initialize(){
        settingLocatons();
        add_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                addBtnAction(event);
            }
        });

    }
    public void settingLocatons(){
        try {
            PreparedStatement statement = connectDB.prepareStatement("select airport_name from Airport");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                locations.add(resultSet.getString("airport_name"));
            }
            departure_box.setItems(locations);
            arrival_box.setItems(locations);

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
    public void addingToDatabase(){
        String query = "INSERT INTO Flight (flight_number, total_seats, departure_date, start_location, destination, status) VALUES ('"+flight_no_field.getText()+"',"+
                Integer.parseInt(seats_field.getText())+",'"+departure_date.getValue()+"','"+departure_box.getValue().toString()+"','"+arrival_box.getValue().toString()+"','Active');";
        System.out.println(query);
        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(query);
            add_message.setText("Flight Added");
        } catch (SQLIntegrityConstraintViolationException e){
            add_message.setText("This Flight Exists");
        } catch (Exception e){
            add_message.setText("Sorry , We ran into some problem - Try Again Later");
            e.printStackTrace();
            e.getCause();
        }
    }
    public void addBtnAction(ActionEvent event){
        addingToDatabase();
    }

}
