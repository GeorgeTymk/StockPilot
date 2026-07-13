package com.stockpilot.controller;


import com.stockpilot.model.Recipe;
import com.stockpilot.service.RecipeService;
import com.stockpilot.util.Navigator;


import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;



public class RecipeController {



    @FXML
    private TableView<Recipe> recipeTable;



    @FXML
    private TableColumn<Recipe, Integer> idColumn;



    @FXML
    private TableColumn<Recipe, String> nameColumn;



    @FXML
    private TableColumn<Recipe, String> descriptionColumn;



    @FXML
    private TableColumn<Recipe, Double> priceColumn;




    private final RecipeService recipeService =
            new RecipeService();




    // Stores currently selected recipe
    public static Recipe selectedRecipe;





    @FXML
    public void initialize(){


        idColumn.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );


        nameColumn.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );


        descriptionColumn.setCellValueFactory(
                new PropertyValueFactory<>("description")
        );


        priceColumn.setCellValueFactory(
                new PropertyValueFactory<>("sellingPrice")
        );


        loadRecipes();


    }





    private void loadRecipes(){


        recipeTable.setItems(

                FXCollections.observableArrayList(
                        recipeService.getAllRecipes()
                )

        );


        System.out.println(
                "Recipes loaded: "
                + recipeTable.getItems().size()
        );


    }





    @FXML
    private void addRecipe(){


        Navigator.goTo(
                "add_recipe.fxml"
        );


    }





    @FXML
    private void viewRecipe(){


        Recipe recipe =
                recipeTable
                        .getSelectionModel()
                        .getSelectedItem();



        if(recipe == null){


            System.out.println(
                    "Please select a recipe first"
            );


            return;


        }



        selectedRecipe = recipe;



        System.out.println(
                "Selected recipe: "
                + recipe.getName()
        );



        Navigator.goTo(
                "recipe_details.fxml"
        );


    }





    @FXML
    private void goBack(){


        Navigator.goBack();


    }




    public void refresh(){


        loadRecipes();


    }



}