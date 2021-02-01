package main.Controllers;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.Database.DatabaseConnection;
import main.Models.FlightModel;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class AdminController implements Initializable {

    ObservableList<FlightModel> previousList = FXCollections.observableArrayList();
    ObservableList<FlightModel> upcomingList = FXCollections.observableArrayList();
    ObservableList<FlightModel> currentList = FXCollections.observableArrayList();

    @FXML
    private TableView<FlightModel> upcoming_table;
    @FXML
    private TableColumn<FlightModel, String> upcoming_flight_name;
    @FXML
    private TableColumn<FlightModel, String> upcoming_origin;
    @FXML
    private TableColumn<FlightModel, String> upcoming_destination;
    @FXML
    private TableColumn<FlightModel, Integer> upcoming_seats;
    @FXML
    private TableColumn<FlightModel, LocalDate> upcoming_date;
    @FXML
    private TableColumn<FlightModel, LocalTime> upcoming_time;
    @FXML
    private TableView<FlightModel> previous_table;
    @FXML
    private TableColumn<FlightModel, String> previous_flight_name;
    @FXML
    private TableColumn<FlightModel, String> previous_origin;
    @FXML
    private TableColumn<FlightModel, String> previous_destination;
    @FXML
    private TableColumn<FlightModel, Integer> previous_seats;
    @FXML
    private TableColumn<FlightModel, LocalDate> previous_date;
    @FXML
    private TableColumn<FlightModel, LocalTime> previous_time;
    @FXML
    private TableView<FlightModel> current_table;
    @FXML
    private TableColumn<FlightModel, String> current_flight_name;
    @FXML
    private TableColumn<FlightModel, String> current_origin;
    @FXML
    private TableColumn<FlightModel, String> current_destination;
    @FXML
    private TableColumn<FlightModel, Integer> current_seats;
    @FXML
    private TableColumn<FlightModel, LocalDate> current_date;
    @FXML
    private TableColumn<FlightModel, LocalTime> current_time;
    @FXML
    private Label message_change;
    @FXML
    private Button cancel_btn;
    @FXML
    private Button active_btn;
    DatabaseConnection dbConnection = new DatabaseConnection();
    Connection connectDB = dbConnection.getconnection();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        get_upcoming_flights();
        get_previousflights();
        get_currentflights();


        upcoming_table.setRowFactory(row -> new TableRow<FlightModel>(){
            @Override
            protected void updateItem(FlightModel item, boolean empty) {
                super.updateItem(item, empty);

                if (item==null || empty){
                    setStyle("");
                } else {
                    if (item.getStatus().equals("Active")) {
                        setStyle("-fx-background-color: #a5d6a7");

                    } else {
                        setStyle("-fx-background-color: #ef9a9a");
                    }
                }
            }
        });
        previous_table.setRowFactory(row -> new TableRow<FlightModel>(){
            @Override
            protected void updateItem(FlightModel item, boolean empty) {
                super.updateItem(item, empty);

                if (item==null || empty){
                    setStyle("");
                } else {
                    if (item.getStatus().equals("Active")) {
                        setStyle("-fx-background-color: #a5d6a7");

                    } else {
                        setStyle("-fx-background-color: #ef9a9a");
                    }
                }
            }
        });
    }
    public void handleRefresh(){
        upcomingList.clear();
        previousList.clear();
        currentList.clear();
        get_upcoming_flights();
        get_previousflights();
        get_currentflights();
    }
    public void get_upcoming_flights(){

        try{
            PreparedStatement statement = connectDB.prepareStatement("select * from Flight where departure_date > CURDATE() order by departure_time asc;");
            ResultSet result = statement.executeQuery();
            while (result.next()){
                upcomingList.add(new FlightModel(result.getString("flight_number"), result.getString("start_location"), result.getString("destination"), result.getString("departure_date"), result.getString("departure_time"),Integer.parseInt(result.getString("total_seats")),result.getString("status")));
            }
            upcoming_flight_name.setCellValueFactory(new PropertyValueFactory<FlightModel, String>("flight_no"));
            upcoming_origin.setCellValueFactory(new PropertyValueFactory<FlightModel, String>("origin"));
            upcoming_destination.setCellValueFactory(new PropertyValueFactory<FlightModel, String>("destination"));
            upcoming_date.setCellValueFactory(new PropertyValueFactory<FlightModel, LocalDate>("date"));
            upcoming_time.setCellValueFactory(new PropertyValueFactory<FlightModel, LocalTime>("time"));
            upcoming_seats.setCellValueFactory(new PropertyValueFactory<FlightModel, Integer>("seats"));

            upcoming_table.setItems(upcomingList);
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }


    }
    public void get_previousflights(){
        DatabaseConnection dbConnection = new DatabaseConnection();
        Connection connectDB = dbConnection.getconnection();

        try{
            PreparedStatement statement = connectDB.prepareStatement("select * from Flight where departure_date < CURDATE() order by departure_time desc;");
            ResultSet result = statement.executeQuery();
            while (result.next()){
                previousList.add(new FlightModel(result.getString("flight_number"), result.getString("start_location"), result.getString("destination"), result.getString("departure_date"), result.getString("departure_time"),Integer.parseInt(result.getString("total_seats")),result.getString("status")));
            }
            previous_flight_name.setCellValueFactory(new PropertyValueFactory<FlightModel, String>("flight_no"));
            previous_origin.setCellValueFactory(new PropertyValueFactory<FlightModel, String>("origin"));
            previous_destination.setCellValueFactory(new PropertyValueFactory<FlightModel, String>("destination"));
            previous_date.setCellValueFactory(new PropertyValueFactory<FlightModel, LocalDate>("date"));
            previous_time.setCellValueFactory(new PropertyValueFactory<FlightModel, LocalTime>("time"));
            previous_seats.setCellValueFactory(new PropertyValueFactory<FlightModel, Integer>("seats"));

            previous_table.setItems(previousList);
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
    public void get_currentflights(){


        try{
            PreparedStatement statement = connectDB.prepareStatement("select * from Flight where date(departure_date) = CURDATE() order by departure_time asc;");
            ResultSet result = statement.executeQuery();
            while (result.next()){
                currentList.add(new FlightModel(result.getString("flight_number"), result.getString("start_location"), result.getString("destination"), result.getString("departure_date"), result.getString("departure_time"),Integer.parseInt(result.getString("total_seats")),result.getString("status")));
            }
            current_flight_name.setCellValueFactory(new PropertyValueFactory<FlightModel, String>("flight_no"));
            current_origin.setCellValueFactory(new PropertyValueFactory<FlightModel, String>("origin"));
            current_destination.setCellValueFactory(new PropertyValueFactory<FlightModel, String>("destination"));
            current_date.setCellValueFactory(new PropertyValueFactory<FlightModel, LocalDate>("date"));
            current_time.setCellValueFactory(new PropertyValueFactory<FlightModel, LocalTime>("time"));
            current_seats.setCellValueFactory(new PropertyValueFactory<FlightModel, Integer>("seats"));

            current_table.setItems(currentList);
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
    public void addFlightWindow(ActionEvent event){
        try{
            URL url = new File("src/main/FXML/addFlight.fxml").toURI().toURL();
            Parent root = FXMLLoader.load(url);
            Stage stage = new Stage();
            stage.setTitle("Add Flight - Your AIR");
            stage.setScene(new Scene(root, 600, 400));
            stage.show();
        }  catch (IOException e){
            e.printStackTrace();
            e.getCause();
        }
    }
    public void activeAction(ActionEvent event){
        if (upcoming_table.getSelectionModel().getSelectedItem()!=null){
            if (!upcoming_table.getSelectionModel().getSelectedItem().getStatus().equals("Active")){
                String flightNo = upcoming_table.getSelectionModel().getSelectedItem().getFlight_no();
                String query = "UPDATE Flight SET status = 'Active' WHERE flight_number='"+flightNo+"';";

                try {
                    Statement statement = connectDB.createStatement();
                    statement.executeUpdate(query);
                    message_change.setText("Flight Activated");
                    handleRefresh();
                    message_change.setText("");
                } catch (Exception e){
                    e.printStackTrace();
                    e.getCause();
                }
            }
        }

    }
    public void cancelAction(ActionEvent event){
        if (upcoming_table.getSelectionModel().getSelectedItem()!=null){
            if (upcoming_table.getSelectionModel().getSelectedItem().getStatus().equals("Active")){
                String flightNo = upcoming_table.getSelectionModel().getSelectedItem().getFlight_no();
                String query = "UPDATE Flight SET status = 'Cancel' WHERE flight_number='"+flightNo+"';";
                System.out.println(query);
                try {
                    Statement statement = connectDB.createStatement();
                    statement.executeUpdate(query);
                    message_change.setText("Flight Cancelled");
                    handleRefresh();
                    message_change.setText("");
                } catch (Exception e){
                    e.printStackTrace();
                    e.getCause();
                }
            }
        }
    }

}
