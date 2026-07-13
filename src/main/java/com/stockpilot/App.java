package com.stockpilot;


import com.stockpilot.util.Navigator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import atlantafx.base.theme.PrimerLight;
import javafx.stage.Stage;



public class App extends Application {



    @Override
    public void start(Stage stage) throws Exception {



        // Give Navigator access to the main window
        Navigator.setStage(stage);



        // Check if login.fxml exists
        System.out.println(
                "Login FXML location: "
                + getClass().getResource("/fxml/login.fxml")
        );



        FXMLLoader loader =
                new FXMLLoader(
                        getClass().getResource("/fxml/login.fxml")
                );



       Scene scene =
        new Scene(
                loader.load(),
                1280,
                720
        );

// Enable AtlantaFX theme
Application.setUserAgentStylesheet(
        new PrimerLight().getUserAgentStylesheet()
);

// Load our custom stylesheet
scene.getStylesheets().add(
        getClass()
                .getResource("/css/stockpilot.css")
                .toExternalForm()
);



        stage.setTitle(
                "StockPilot"
        );



        stage.setScene(scene);



        stage.setMinWidth(1100);

stage.setMinHeight(650);

stage.centerOnScreen();

        stage.show();



    }





    public static void main(String[] args) {


        launch(args);


    }


}