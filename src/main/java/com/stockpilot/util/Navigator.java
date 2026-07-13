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



            Scene scene =
                    new Scene(
                            root,
                            stage.getWidth(),
                            stage.getHeight()
                    );



            stage.setScene(scene);


            stage.setResizable(true);


            // Normal desktop window size
            if(stage.getWidth() < 1200){

                stage.setWidth(1200);

            }


            if(stage.getHeight() < 750){

                stage.setHeight(750);

            }



            stage.show();



            // Allow JavaFX to finish sizing before refreshing
            Platform.runLater(() -> {

                root.applyCss();

                root.layout();

            });



        } catch(Exception e){

            e.printStackTrace();

        }


    }




    public static void goBack(){


        try {


            if(!history.isEmpty()){


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



                Scene scene =
                        new Scene(
                                root,
                                stage.getWidth(),
                                stage.getHeight()
                        );



                stage.setScene(scene);


                stage.show();



                Platform.runLater(() -> {

                    root.applyCss();

                    root.layout();

                });


            }


        }catch(Exception e){

            e.printStackTrace();

        }


    }


}