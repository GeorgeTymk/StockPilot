package com.stockpilot.controller;


import com.stockpilot.model.Recipe;
import com.stockpilot.service.RecipeService;
import com.stockpilot.util.Navigator;


import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;



public class AddRecipeController {





    @FXML
    private TextField nameField;

        @FXML
private void goBack(){

    Navigator.goBack();}

    @FXML
    private TextArea descriptionField;


    @FXML
    private TextField priceField;



    private final RecipeService recipeService =
            new RecipeService();



    @FXML
    private void saveRecipe(){


        System.out.println("SAVE BUTTON CLICKED");



        try {



            String name =
                    nameField.getText();


            String description =
                    descriptionField.getText();


            double price =
                    Double.parseDouble(
                            priceField.getText()
                    );



            System.out.println(
                    "Recipe name: " + name
            );


            System.out.println(
                    "Price: " + price
            );



            Recipe recipe =
                    new Recipe(

                            0,

                            name,

                            description,

                            price

                    );



            recipeService.addRecipe(recipe);



            System.out.println(
                    "Returned from RecipeService"
            );



            Alert alert =
                    new Alert(
                            Alert.AlertType.INFORMATION
                    );


            alert.setTitle("Success");

            alert.setHeaderText(null);

            alert.setContentText(
                    "Recipe saved"
            );


            alert.showAndWait();



            Navigator.goTo(
                    "recipes.fxml"
            );



        }catch(Exception e){


            e.printStackTrace();


        }


    }


}