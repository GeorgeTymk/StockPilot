package com.stockpilot.controller;

import com.stockpilot.util.Navigator;

import com.stockpilot.service.AuthService;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class LoginController {



    @FXML
private void goBack(){

    Navigator.goBack();

}

    @FXML
    private TextField usernameField;


    @FXML
    private PasswordField passwordField;


    private final AuthService authService = new AuthService();



    @FXML
    private void login() {


        String username = usernameField.getText();

        String password = passwordField.getText();



        boolean success = authService.authenticate(username, password);



        if(success){

    System.out.println("Login successful");

    Navigator.goTo("dashboard.fxml");

    } else {


            System.out.println("Invalid username or password");


        }


    }


}