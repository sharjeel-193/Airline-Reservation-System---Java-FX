package main.Controllers;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import main.Database.DatabaseConnection;
import main.Models.FlightModel;

import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

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
    private TableColumn<FlightModel, LocalDateTime> upcoming_date_time;
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
    private TableColumn<FlightModel, LocalDateTime> previous_date_time;
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
    private TableColumn<FlightModel, LocalDateTime> current_date_time;
    ContextMenu rowMenu = new ContextMenu();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        get_upcoming_flights();
        get_previousflights();


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
        get_upcoming_flights();
    }
    public void get_upcoming_flights(){
        DatabaseConnection dbConnection = new DatabaseConnection();
        Connection connectDB = dbConnection.getconnection();

        try{
            PreparedStatement statement = connectDB.prepareStatement("select * from Flight where departure_time > now() order by departure_time asc;");
            ResultSet result = statement.executeQuery();
            while (result.next()){
                upcomingList.add(new FlightModel(result.getString("flight_number"), result.getString("start_location"), result.getString("destination"), result.getString("departure_time"),Integer.parseInt(result.getString("total_seats")),result.getString("status")));
            }
            upcoming_flight_name.setCellValueFactory(new PropertyValueFactory<FlightModel, String>("flight_no"));
            upcoming_origin.setCellValueFactory(new PropertyValueFactory<FlightModel, String>("origin"));
            upcoming_destination.setCellValueFactory(new PropertyValueFactory<FlightModel, String>("destination"));
            upcoming_date_time.setCellValueFactory(new PropertyValueFactory<FlightModel, LocalDateTime>("time"));
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
            PreparedStatement statement = connectDB.prepareStatement("select * from Flight where departure_time < now() order by departure_time desc;");
            ResultSet result = statement.executeQuery();
            while (result.next()){
                previousList.add(new FlightModel(result.getString("flight_number"), result.getString("start_location"), result.getString("destination"), result.getString("departure_time"),Integer.parseInt(result.getString("total_seats")),result.getString("status")));
            }
            previous_flight_name.setCellValueFactory(new PropertyValueFactory<FlightModel, String>("flight_no"));
            previous_origin.setCellValueFactory(new PropertyValueFactory<FlightModel, String>("origin"));
            previous_destination.setCellValueFactory(new PropertyValueFactory<FlightModel, String>("destination"));
            previous_date_time.setCellValueFactory(new PropertyValueFactory<FlightModel, LocalDateTime>("time"));
            previous_seats.setCellValueFactory(new PropertyValueFactory<FlightModel, Integer>("seats"));

            previous_table.setItems(previousList);
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
    public void get_currentflights(){
        DatabaseConnection dbConnection = new DatabaseConnection();
        Connection connectDB = dbConnection.getconnection();

        try{
            PreparedStatement statement = connectDB.prepareStatement("select * from Flight where departure_time = now() order by departure_time asc;");
            ResultSet result = statement.executeQuery();
            while (result.next()){
                currentList.add(new FlightModel(result.getString("flight_number"), result.getString("start_location"), result.getString("destination"), result.getString("departure_time"),Integer.parseInt(result.getString("total_seats")),result.getString("status")));
            }
            current_flight_name.setCellValueFactory(new PropertyValueFactory<FlightModel, String>("flight_no"));
            current_origin.setCellValueFactory(new PropertyValueFactory<FlightModel, String>("origin"));
            current_destination.setCellValueFactory(new PropertyValueFactory<FlightModel, String>("destination"));
            current_date_time.setCellValueFactory(new PropertyValueFactory<FlightModel, LocalDateTime>("time"));
            current_seats.setCellValueFactory(new PropertyValueFactory<FlightModel, Integer>("seats"));

            current_table.setItems(currentList);
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

}
