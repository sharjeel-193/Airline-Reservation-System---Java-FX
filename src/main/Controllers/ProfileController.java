package main.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.Database.DatabaseConnection;
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
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;

public class ProfileController {
    @FXML
    Button backButton;
    @FXML
    Button ProfileButton;
    @FXML
    Label number,status,dob,name,cnic,user,lab_message;
    @FXML
    TextField nameField, dobField,contactField,statusField, cnicField,userinfo;
    public void profileAction() throws SQLException {
        if (userinfo.getText().isEmpty()){
            lab_message.setText("Enter userename please!");
        }
        else
        try{
            lab_message.setText(" ");
        DatabaseConnection dbConnection = new DatabaseConnection();
        Connection connectDB = dbConnection.getconnection();
        String query="Select * from users where username='" +userinfo.getText()+"';";
                Statement statement = connectDB.createStatement();
        ResultSet queryResult = statement.executeQuery(query);
        nameField.setText(String.valueOf(queryResult));
        if (queryResult==null){
            lab_message.setText("Enter userename please!");
         }
else
            while (queryResult.next()) {
                // testing if it gets the data from the db
                System.out.println(queryResult.getString("name").toUpperCase(Locale.ROOT));
                System.out.println(queryResult.getString("cnic"));
                name.setVisible(true);
                number.setVisible(true);
                dob.setVisible(true);
                status.setVisible(true);
                cnic.setVisible(true);
                // attempt to put it in a textfield
                nameField.setVisible(true);
                nameField.setText(queryResult.getString("name").toUpperCase(Locale.ROOT));
                cnicField.setVisible(true);
                cnicField.setText(queryResult.getString("cnic"));
                statusField.setVisible(true);
                statusField.setText(queryResult.getString("status").toUpperCase(Locale.ROOT));
                dobField.setVisible(true);
                dobField.setText(queryResult.getString("dob"));
                contactField.setVisible(true);
                contactField.setText(queryResult.getString("contact"));
            }
        }
        catch (Exception exc) {
            exc.printStackTrace();
        } }
   /* public void CancelButton(ActionEvent event) throws MalformedURLException {
        URL url = new File("src/main/FXML/booking.fxml").toURI().toURL();
        Parent root = null;
        try {
            root = FXMLLoader.load(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
//            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("./FXML/register.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Booking");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root, 1000, 700));
        stage.show();

    }  */
    public void Back(ActionEvent event){
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
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }
}
