package com.stockpilot.util;


import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Stack;


public class Navigator {


    private static Stage stage;


    private static final Stack<String> history =
            new Stack<>();



    public static void setStage(Stage primaryStage){

        stage = primaryStage;

    }




    public static void goTo(String fxmlFile){


        try {


            // Save current page for back button
            if(stage.getScene() != null
                    && stage.getScene().getRoot().getUserData() != null){


                history.push(
                        stage.getScene()
                                .getRoot()
                                .getUserData()
                                .toString()
                );

            }



            FXMLLoader loader =
                    new FXMLLoader(
                            Navigator.class.getResource(
                                    "/fxml/" + fxmlFile
                            )
                    );



            Parent root = loader.load();


            root.setUserData(fxmlFile);



            Scene oldScene = stage.getScene();


            double width = 1280;
            double height = 750;


            // Keep current window size
            if(oldScene != null){

                width = oldScene.getWidth();
                height = oldScene.getHeight();

            }



            Scene scene =
                    new Scene(
                            root,
                            width,
                            height
                    );



            stage.setScene(scene);



            // Normal desktop window
            stage.setResizable(true);

            stage.setMinWidth(1100);

            stage.setMinHeight(650);


            // Prevent accidental fullscreen behaviour
            stage.setMaximized(false);



            stage.show();




            Platform.runLater(() -> {

                root.applyCss();

                root.layout();


            });



        }
        catch(Exception e){

            e.printStackTrace();

        }


    }







    public static void goBack(){


        try {


            if(history.isEmpty()){

                return;

            }



            String previous =
                    history.pop();




            FXMLLoader loader =
                    new FXMLLoader(
                            Navigator.class.getResource(
                                    "/fxml/" + previous
                            )
                    );




            Parent root =
                    loader.load();




            root.setUserData(previous);





            Scene oldScene =
                    stage.getScene();



            Scene scene =
                    new Scene(
                            root,
                            oldScene.getWidth(),
                            oldScene.getHeight()
                    );




            stage.setScene(scene);




            stage.setResizable(true);

            stage.setMaximized(false);




            stage.show();





            Platform.runLater(() -> {

                root.applyCss();

                root.layout();


            });



        }
        catch(Exception e){

            e.printStackTrace();

        }


    }



}