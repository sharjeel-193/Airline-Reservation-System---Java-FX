package main.Controllers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.Database.DatabaseConnection;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


public class LoginController {
    ObservableList<String> statusChoicesList = FXCollections.observableArrayList("Client", "Admin", "Staff");
    @FXML
    private Button cancelButton;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private TextField userNameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private ChoiceBox<String> statusChoice;
    String st[] = {"Client", "Admin"};
    @FXML
    public void initialize(){
        statusChoice.setItems(statusChoicesList);
        statusChoice.setValue("Client");
    }
    public void logInButtonAction(ActionEvent event){

        if(userNameField.getText().isEmpty() || passwordField.getText().isEmpty()){
            loginMessageLabel.setText("Please Enter both credentials");
        } else {
            validateLogin(event);
        }
    }
    public void validateLogin(ActionEvent event){
        DatabaseConnection dbConnection = new DatabaseConnection();
        Connection connectDB = dbConnection.getconnection();

        String verifyLogin = "SELECT COUNT(1) FROM users WHERE username = '"+userNameField.getText()+"' AND password = '"+passwordField.getText()+"';";
        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);
            while(queryResult.next()){
                if(queryResult.getInt(1) == 1){
                    loginMessageLabel.setText("Congrats!!!");
                    URL url = new File("src/main/FXML/Client_Menu.fxml").toURI().toURL();
                    Parent root = FXMLLoader.load(url);
//            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("./FXML/register.fxml"));
                    Stage stage = new Stage();
                    stage.setTitle("Menu");
                    stage.initStyle(StageStyle.UNDECORATED);
                    stage.setScene(new Scene(root, 800, 500));
                    stage.show();
                    cancelButtonAction(event);
                } else {
                    loginMessageLabel.setText("Invalid Credentials. Please try again");
                }
            }
        } catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
    public void registrationWindow(ActionEvent event) throws Exception{

        try{
            URL url = new File("src/main/FXML/register.fxml").toURI().toURL();
            Parent root = FXMLLoader.load(url);
//            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("./FXML/register.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Sign Up - Your AIR");
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root, 1200, 900));
            stage.show();
            cancelButtonAction(event);
        } catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
    public void cancelButtonAction(ActionEvent event){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
