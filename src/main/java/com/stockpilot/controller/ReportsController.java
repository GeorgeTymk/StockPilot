package com.stockpilot.controller;


import com.stockpilot.service.SaleService;
import com.stockpilot.util.Navigator;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;



public class ReportsController {


    @FXML
    private CheckBox salesSummaryCheck;

    @FXML
    private CheckBox todaySalesCheck;

    @FXML
    private CheckBox inventoryCheck;

    @FXML
    private CheckBox lowStockCheck;

    @FXML
    private CheckBox outOfStockCheck;

    @FXML
    private CheckBox recipeCheck;

    @FXML
    private CheckBox activityCheck;

    @FXML
    private CheckBox bestSellerCheck;


    @FXML
    private TextArea previewArea;



    private final SaleService saleService =
            new SaleService();




    @FXML
    public void initialize() {


        salesSummaryCheck.setSelected(true);
        todaySalesCheck.setSelected(true);
        bestSellerCheck.setSelected(true);


        generatePreview();

    }







    @FXML
    private void generatePreview() {


        StringBuilder report =
                new StringBuilder();



        report.append("STOCKPILOT SALES REPORT\n");
        report.append("============================\n\n");




        if(todaySalesCheck.isSelected()) {


            double todaySales =
                    saleService.getTodaySales();


            report.append(
                    "Today's Sales:\n"
            );


            report.append(
                    String.format(
                            "MK %,.2f",
                            todaySales
                    )
            );


            report.append("\n\n");

        }






        if(bestSellerCheck.isSelected()) {


            String bestSeller =
                    saleService.getBestSellingRecipe();



            report.append(
                    "Best Selling Recipe:\n"
            );


            if(bestSeller == null ||
                    bestSeller.isEmpty()) {


                report.append(
                        "No sales recorded"
                );


            } else {


                report.append(
                        bestSeller
                );

            }


            report.append("\n\n");


        }







        if(salesSummaryCheck.isSelected()) {


            report.append(
                    "✓ Sales Summary Included\n"
            );

        }





        if(inventoryCheck.isSelected()) {


            report.append(
                    "✓ Inventory Status Included\n"
            );

        }





        if(lowStockCheck.isSelected()) {


            report.append(
                    "✓ Low Stock Items Included\n"
            );

        }





        if(outOfStockCheck.isSelected()) {


            report.append(
                    "✓ Out Of Stock Items Included\n"
            );

        }





        if(recipeCheck.isSelected()) {


            report.append(
                    "✓ Recipe Performance Included\n"
            );

        }





        if(activityCheck.isSelected()) {


            report.append(
                    "✓ Recent Activity Included\n"
            );

        }




        previewArea.setText(
                report.toString()
        );


    }







    @FXML
    private void exportPDF() {


        Alert alert =
                new Alert(
                        Alert.AlertType.INFORMATION
                );


        alert.setTitle(
                "Export PDF"
        );


        alert.setHeaderText(null);


        alert.setContentText(
                "PDF export will be connected next."
        );


        alert.showAndWait();


    }







    @FXML
    private void goBack() {


        Navigator.goBack();


    }



}