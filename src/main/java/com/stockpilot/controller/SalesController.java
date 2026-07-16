package com.stockpilot.controller;


import com.stockpilot.model.Recipe;
import com.stockpilot.service.RecipeService;
import com.stockpilot.service.SaleService;
import com.stockpilot.util.Navigator;


import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;



public class SalesController {



    @FXML
    private ComboBox<Recipe> recipeComboBox;



    @FXML
    private TextField quantityField;



    @FXML
    private Label totalLabel;





    private final RecipeService recipeService =
            new RecipeService();



    private final SaleService saleService =
            new SaleService();







    @FXML
    public void initialize(){


        recipeComboBox.setItems(

                FXCollections.observableArrayList(

                        recipeService.getAllRecipes()

                )

        );



        recipeComboBox.setOnAction(
                e -> calculateTotal()
        );



        quantityField.textProperty()
                .addListener(

                        (obs,oldValue,newValue)
                                -> calculateTotal()

                );


    }







    @FXML
    private void calculateTotal(){


        Recipe recipe =
                recipeComboBox.getValue();



        if(recipe == null){

            totalLabel.setText("0");

            return;

        }




        try{


            int quantity =
                    Integer.parseInt(
                            quantityField.getText()
                    );



            if(quantity <= 0){

                totalLabel.setText("0");

                return;

            }




            double total =
                    recipe.getSellingPrice()
                    *
                    quantity;




            totalLabel.setText(

                    String.format(
                            "%.2f",
                            total
                    )

            );



        }
        catch(Exception e){


            totalLabel.setText("0");


        }


    }








    @FXML
    private void saveSale(){



        Recipe recipe =
                recipeComboBox.getValue();



        if(recipe == null){

            return;

        }




        try{


            int quantity =
                    Integer.parseInt(
                            quantityField.getText()
                    );



            double total =
                    recipe.getSellingPrice()
                    *
                    quantity;




            boolean success =
                    saleService.saveSale(

                            recipe.getId(),

                            quantity,

                            total

                    );





            if(success){


                quantityField.clear();


                recipeComboBox
                        .getSelectionModel()
                        .clearSelection();



                totalLabel.setText("0");


            }



        }
        catch(Exception e){

            e.printStackTrace();

        }



    }








    @FXML
    private void openDashboard(){


        Navigator.goTo(
                "dashboard.fxml"
        );


    }







    @FXML
    private void goBack(){


        Navigator.goBack();


    }



}