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


    private static String currentPage;



    public static void setStage(Stage primaryStage){

        stage = primaryStage;

    }





    public static void goTo(String fxmlFile){


        try {


            if(stage == null){

                throw new IllegalStateException(
                        "Navigator stage not initialized"
                );

            }



            if(currentPage != null
                    && !currentPage.equals(fxmlFile)){


                history.push(currentPage);

            }



            FXMLLoader loader =
                    new FXMLLoader(
                            Navigator.class.getResource(
                                    "/fxml/" + fxmlFile
                            )
                    );



            Parent root = loader.load();



            root.setUserData(fxmlFile);



            currentPage = fxmlFile;




            Scene scene = stage.getScene();



            if(scene == null){


                scene = new Scene(
                        root,
                        1280,
                        750
                );


                stage.setScene(scene);


            }
            else {


                /*
                 * Instant page swap
                 * No animation = no flashing
                 */

                scene.setRoot(root);


            }




            stage.setResizable(true);

            stage.setMinWidth(1100);

            stage.setMinHeight(650);


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


        if(history.isEmpty()){

            return;

        }



        String previous =
                history.pop();



        goTo(previous);


    }







    public static String getCurrentPage(){

        return currentPage;

    }







    public static void clearHistory(){

        history.clear();

    }


}