package com.stockpilot.controller;


import com.stockpilot.model.Ingredient;
import com.stockpilot.service.IngredientService;
import com.stockpilot.util.Navigator;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;



public class LowStockController {


    @FXML
    private TableView<Ingredient> lowStockTable;


    @FXML
    private TableColumn<Ingredient, String> nameColumn;


    @FXML
    private TableColumn<Ingredient, Double> quantityColumn;


    @FXML
    private TableColumn<Ingredient, Double> minimumColumn;


    @FXML
    private TableColumn<Ingredient, String> unitColumn;



    private final IngredientService service =
            new IngredientService();





    @FXML
    public void initialize() {


        nameColumn.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );


        quantityColumn.setCellValueFactory(
                new PropertyValueFactory<>("quantity")
        );


        minimumColumn.setCellValueFactory(
                new PropertyValueFactory<>("minimumStock")
        );


        unitColumn.setCellValueFactory(
                new PropertyValueFactory<>("unit")
        );


        loadLowStock();


        styleRows();


    }





    private void loadLowStock() {


        lowStockTable.setItems(

                FXCollections.observableArrayList(

                        service.getLowStockIngredients()

                )

        );


    }





    private void styleRows() {


        lowStockTable.setRowFactory(table ->


                new TableRow<>() {


                    @Override
                    protected void updateItem(

                            Ingredient ingredient,

                            boolean empty

                    ) {


                        super.updateItem(
                                ingredient,
                                empty
                        );



                        if(empty || ingredient == null) {


                            setStyle("");


                        }
                        else {


                            if(ingredient.getQuantity()
                                    <= ingredient.getMinimumStock()) {


                                setStyle(
                                        "-fx-background-color:#ffcccc;"
                                );


                            }
                            else {


                                setStyle("");

                            }


                        }


                    }


                }


        );


    }






    @FXML
    private void refresh() {


        loadLowStock();


    }






    @FXML
    private void goBack() {


        Navigator.goBack();


    }



}