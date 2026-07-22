package com.stockpilot.controller;


import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;


import java.io.IOException;



public class MainShellController {



    private static MainShellController instance;



    @FXML
    private StackPane contentArea;






    public MainShellController(){


        instance = this;


    }






    public static MainShellController getInstance(){


        return instance;


    }









    @FXML
    public void initialize(){



        Platform.runLater(() -> {



            Parent root =
                    contentArea.getScene()
                            .getRoot();



            root.getProperties()
                    .put(
                            "controller",
                            this
                    );




            loadPage(
                    "/fxml/dashboard.fxml"
            );



        });



    }









    public void loadPage(String page){



        try {



            FXMLLoader loader =
                    new FXMLLoader(
                            getClass()
                            .getResource(page)
                    );



            Node view =
                    loader.load();




            contentArea.getChildren()
                    .clear();



            contentArea.getChildren()
                    .add(view);




        }
        catch(IOException e){



            e.printStackTrace();



        }



    }




}