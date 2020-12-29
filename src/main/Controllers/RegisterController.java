package main.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.Database.DatabaseConnection;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterController {
    @FXML
    private Button cancelButton;
    @FXML
    private TextField fullNameField;
    @FXML
    private DatePicker dobField;
    @FXML
    private TextField contactField;
    @FXML
    private TextField cnicField;
    @FXML
    private TextField userNameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField confirmPassword;
    @FXML
    private Label signUpMessage;

    public void signUpAction(ActionEvent event){
        signUpMessage.setText("SIGN UP");
        System.out.println(dobField.getValue());
        if(fullNameField.getText().isEmpty()||dobField.getValue()==null||contactField.getText().isEmpty()||cnicField.getText().isEmpty()||userNameField.getText().isEmpty()||passwordField.getText().isEmpty()){
            signUpMessage.setText("Please fill Up in all the Fields");
        } else if (cnicField.getText().length()!=13 || !cnicField.getText().matches("\\d+")){
            signUpMessage.setText("Invalid CNIC No.");
        } else if (contactField.getText().length()!=12 || !contactField.getText().matches("\\d+")){
            signUpMessage.setText("Invalid Contact Number");
        } else if (!valdateUsername(userNameField.getText())){
            signUpMessage.setText("Invalid Username Chosen");
        } else if (passwordField.getText().length()<12){
            signUpMessage.setText("Password is too short");
        } else if (!passwordField.getText().equals(confirmPassword.getText())){
            signUpMessage.setText("Your Password dont match");
        } else if (!validatePassword(passwordField.getText())){
            signUpMessage.setText("invalid Password");
        } else {
            addingtoDatabase();
        }
    }
    public void addingtoDatabase(){
        DatabaseConnection dbConnection = new DatabaseConnection();
        Connection connectDB = dbConnection.getconnection();
        String query = "INSERT INTO users VALUES ('" + userNameField.getText() +"', '"+ fullNameField.getText() + "', '" + dobField.getValue() + "', '" + contactField.getText() + "', '" + cnicField.getText() + "', '" + passwordField.getText() + "', 'client');";

        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(query);
            signUpMessage.setText("User has been added successfully");


        } catch (SQLIntegrityConstraintViolationException e){
            signUpMessage.setText("Username Exists");
        } catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
    public boolean validatePassword(String password){
        Pattern letter = Pattern.compile("[a-zA-z]");
        Pattern digit = Pattern.compile("[0-9]");
        Pattern special = Pattern.compile ("[!@#$%&*()_+=|<>?{}\\[\\]~-]");
        //Pattern eight = Pattern.compile (".{8}");


        Matcher hasLetter = letter.matcher(password);
        Matcher hasDigit = digit.matcher(password);
        Matcher hasSpecial = special.matcher(password);

        return hasLetter.find() && hasDigit.find() && hasSpecial.find();
    }
    public boolean valdateUsername(String username){
        Pattern letter = Pattern.compile("[a-zA-z]");
        Pattern digit = Pattern.compile("[0-9]");
        Pattern special = Pattern.compile ("[!@#$%&*()_+=|<>?{}\\[\\]~-]");
        //Pattern eight = Pattern.compile (".{8}");


        Matcher hasLetter = letter.matcher(username);
        Matcher hasDigit = digit.matcher(username);
        Matcher hasSpecial = special.matcher(username);

        return hasLetter.find() && hasDigit.find() && !hasSpecial.find();
    }
    public void loginWindow(ActionEvent event){
        try{
            URL url = new File("src/main/FXML/login.fxml").toURI().toURL();
            Parent root = FXMLLoader.load(url);
            Stage stage = new Stage();
            stage.setTitle("Login - Your AIR");
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root, 1000, 600));
            stage.show();
            cancelButtonAction(event);
        }  catch (IOException e){
            e.printStackTrace();
            e.getCause();
        }
    }

    public void cancelButtonAction(ActionEvent event){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
