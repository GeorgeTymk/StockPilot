package com.stockpilot.controller;


import com.stockpilot.model.Recipe;
import com.stockpilot.service.RecipeService;
import com.stockpilot.service.SaleService;
import com.stockpilot.util.Navigator;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;



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



        // Update total when recipe changes

        recipeComboBox.setOnAction(
                event -> calculateTotal()
        );



        // Update total when quantity changes

        quantityField.textProperty()
                .addListener(

                        (observable, oldValue, newValue) -> 
                                calculateTotal()

                );


    }







    @FXML
    public void calculateTotal(){


        Recipe recipe =
                recipeComboBox.getValue();



        if(recipe == null){


            totalLabel.setText("0");

            return;

        }





        try{


            int quantity =
                    Integer.parseInt(
                            quantityField.getText().trim()
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
    public void saveSale(){



        Recipe recipe =
                recipeComboBox.getValue();




        if(recipe == null){


            System.out.println(
                    "Please select a recipe"
            );


            return;


        }






        try{



            int quantity =
                    Integer.parseInt(
                            quantityField.getText().trim()
                    );





            if(quantity <= 0){


                System.out.println(
                        "Invalid quantity"
                );


                return;


            }







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


                System.out.println(
                        "Sale completed successfully"
                );



                quantityField.clear();



                recipeComboBox
                        .getSelectionModel()
                        .clearSelection();



                totalLabel.setText("0");



            }
            else{


                System.out.println(
                        "Sale failed"
                );


            }





        }
        catch(NumberFormatException e){


            System.out.println(
                    "Enter a valid quantity"
            );


        }
        catch(Exception e){


            e.printStackTrace();


        }



    }









    @FXML
    public void goBack(){


        Navigator.goBack();


    }



}