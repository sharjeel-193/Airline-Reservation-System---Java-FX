package main.Controllers;

import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import main.Database.DatabaseConnection;
import javafx.scene.control.Label;
import javafx.scene.control.*;
import java.awt.*;


import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class bookingController {
    ObservableList<String> FlightChoicesList = FXCollections.observableArrayList("FSD-KHI-001", "FSD-LHR-001","ISB-KHI-001","ISB-LHR-001",
            "ISB-LHR-002");
    ObservableList<String> PayChoicesList = FXCollections.observableArrayList("Online", "On Cash");
    ObservableList<Integer> SeatChoicesList = FXCollections.observableArrayList(1, 2,3);
    @FXML
    public Label passenger1, date,  lab_message,seat,pay;
    @FXML
    Button BookButton,back ;
    @FXML
    DatePicker dtPicker;

    @FXML
    TextField txt_user;
    @FXML
    private ChoiceBox<String> FlightChoice;
@FXML
    private ChoiceBox<String> PayChoice;
@FXML
    private ChoiceBox<Integer> SeatChoice;
    @FXML
    public void initialize(){
        FlightChoice.setItems(FlightChoicesList);
        FlightChoice.setValue("Flights");
        SeatChoice.setItems(SeatChoicesList);

        PayChoice.setItems(PayChoicesList);
        PayChoice.setValue("Payment");
    }
String sqlQuery;



    public void BookButton(ActionEvent event){

        if(txt_user.getText().isEmpty() || dtPicker.getValue()==null )

            lab_message.setText("Please Enter details of booking");
        else {
            validateTicket(event);


        }
    }

    public void validateTicket(ActionEvent event){
        lab_message.setText("");
        DatabaseConnection dbConnection = new DatabaseConnection();
        Connection connectDB = dbConnection.getconnection();
        sqlQuery="insert into Passengers (flight_no,user_name,date_of_travel,seat_no) values ('"+FlightChoice.getValue()+"','" +txt_user.getText()+"','" +dtPicker.getValue()+"','" + SeatChoice.getValue()+"');";


        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(sqlQuery);
            PauseTransition pause = new PauseTransition(Duration.seconds(0.3));
            lab_message.setText("congrats");
            pause.play();

            URL url = new File("src/main/FXML/profile.fxml").toURI().toURL();
            Parent root = FXMLLoader.load(url);
//            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("./FXML/register.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Profile");
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root, 1000, 700));
            stage.show();

            cancelButtonAction(event);
        }catch (Exception e){
            System.out.println("error:"+e.getMessage());
        }

    }

public void backAction(ActionEvent event) throws Exception{
    try{
        URL url = new File("src/main/FXML/Client_Menu.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
//            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("./FXML/register.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Menu - Your AIR");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root, 800, 600));
        stage.show();
        cancelButtonAction(event);

    } catch (Exception e){
        e.printStackTrace();
        e.getCause();
    }
}
    public void cancelButtonAction(ActionEvent event){
        Stage stage = (Stage) back.getScene().getWindow();
        stage.close();
    }
}
