package com.library.my_library.controllers;

import com.library.my_library.connection.startConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.*;
import java.util.Random;

public class signupController {

    @FXML
    private TextField nameInput;


    @FXML
    private PasswordField passwordInput;
    @FXML
    private Button signupButton;

    @FXML
    private TextField usernameInput;
    String getRandomCode(){
        String ch = "";
        Random rand = new Random();
        int n;
        for (int i = 0; i < 6; i++) {
            n = rand.nextInt(10);
            ch += Integer.toString(n);
        }
        return ch;
    }
    Alert alert = null;
    Statement st;
    ResultSet rs;
    PreparedStatement ps;

    @FXML
    void signup(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (nameInput.getText().isEmpty() || passwordInput.getText().isEmpty() || usernameInput.getText().isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setContentText("Please Enter Valid Data");
            alert.show();
        } else {
            //establish connection
            startConnection sc = new startConnection();
            Connection con = sc.getCon();
            st = con.createStatement();

            //generating a random id for this sign up transaction
            String currentCode = getRandomCode();

            //insertion of data in table utilisateur
            String query = "INSERT INTO utilisateur (username, password,role) VALUES (?,?,?);";
            ps = con.prepareStatement(query);
            ps.setString(1, usernameInput.getText());
            ps.setString(2, passwordInput.getText());
            ps.setString(3, "1");
            ps.execute();

            ////insertion of data in table adhrent
            query = "INSERT INTO adhrent (code, name) VALUES (?,?);";
            ps = con.prepareStatement(query);
            ps.setString(1, currentCode);
            ps.setString(2, nameInput.getText());
            ps.execute();

            //update table utilisateur to establish a relation
            query = "UPDATE utilisateur SET code = ? WHERE username = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, currentCode);
            ps.setString(2, usernameInput.getText());
            ps.execute();
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("You have signed up with success");
            alert.show();
            con.close();
        }
    }
}
