package main.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.net.URL;


public class CMenuController {
    @FXML
    Button logout;
    @FXML
    Button prof, book,his;
    @FXML
    Label lab_menu,lab_welcom;
    public void loadHistory(ActionEvent event)throws Exception{
        try{
            URL url = new File("src/main/FXML/travelHistory.fxml").toURI().toURL();
            Parent root = FXMLLoader.load(url);
//            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("./FXML/register.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Travel History - Your AIR");
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root, 800, 600));
            stage.show();
            cancelButtonAction(event);
        } catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
    public void loadBooking(ActionEvent event) throws Exception{
        try{
            URL url = new File("src/main/FXML/booking.fxml").toURI().toURL();
            Parent root = FXMLLoader.load(url);
//            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("./FXML/register.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Booking - Your AIR");
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root, 800, 600));
            stage.show();
          cancelButtonAction(event);
        } catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
    public void loadProfile(ActionEvent event) throws Exception{
        try{
            URL url = new File("src/main/FXML/profile.fxml").toURI().toURL();
            Parent root = FXMLLoader.load(url);
//            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("./FXML/register.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Profile - Your AIR");
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
        Stage stage = (Stage) logout.getScene().getWindow();
        stage.close();
    }

}
