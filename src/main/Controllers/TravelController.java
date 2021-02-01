package main.Controllers;

import javafx.beans.property.Property;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.Database.DatabaseConnection;
import javafx.scene.control.TextField;
import javax.swing.text.TabableView;
import java.awt.*;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class TravelController   {
    @FXML
    Label username;
    @FXML
    TextField tfield;
    @FXML
    TableView  <Modelhistory> table;
    @FXML
    TableColumn <Modelhistory,String> col_dest;
    @FXML
    TableColumn <Modelhistory,String> col_from;
    @FXML
    TableColumn <Modelhistory,String> col_date;
    @FXML
    TableColumn <Modelhistory,String> col_flight;
    @FXML
    TableColumn <Modelhistory,Integer> col_seat;
    @FXML Button fetch,back;
    ObservableList <Modelhistory> oblist ;
@FXML
 public void FetchData()  {


        try{
        DatabaseConnection dbConnection = new DatabaseConnection();
        Connection connectDB = dbConnection.getconnection();
        oblist= FXCollections.observableArrayList();
        String query="Select flight_no,seat_no,date_of_travel,start_location,destination from Passengers p  inner JOIN Flight f on p.flight_no=f.flight_number where user_name='" +tfield.getText()+"';";
          //  String query="Select flight_number,seat_no,date_of_travel from passengers where username='mmmalik645'"  ;
        Statement statement = connectDB.createStatement();
        ResultSet queryResult = statement.executeQuery(query);
        while (queryResult.next()){
            oblist.add(new Modelhistory(queryResult.getString("flight_no"),queryResult.getInt("seat_no"),queryResult.getString("date_of_travel"),queryResult.getString("start_location"),queryResult.getString("destination")));
            System.out.println(oblist);
            col_dest.setCellValueFactory(cellData->cellData.getValue().dest);
            col_from.setCellValueFactory(cellData->cellData.getValue().from);
            col_date.setCellValueFactory(cellData->cellData.getValue().date);
            col_seat.setCellValueFactory(cellData-> cellData.getValue().seat.asObject());
            col_flight.setCellValueFactory(cellData -> cellData.getValue().flight);
            System.out.println(oblist);
            table.setItems(oblist);
          //  table.setItems(oblist);
        }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }




    }
    public void backAction(ActionEvent event) {
        try {
            URL url = new File("src/main/FXML/Client_Menu.fxml").toURI().toURL();
            Parent root = FXMLLoader.load(url);
//            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("./FXML/register.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Menu - Your AIR");
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root, 800, 600));
            stage.show();
              cancelButtonAction(event);
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
    public void cancelButtonAction(ActionEvent event){
        Stage stage = (Stage) back.getScene().getWindow();
        stage.close();
    }
    /*@Override
    public void initialize(URL location, ResourceBundle resources) {
        col_date.setCellValueFactory(new PropertyValueFactory<Modelhistory,String>("date"));
        col_seat.setCellValueFactory(new PropertyValueFactory<Modelhistory ,Integer>("seat"));
        col_flight.setCellValueFactory(new PropertyValueFactory<Modelhistory ,Integer>("flight"));
    } */
}
