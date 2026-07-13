package com.stockpilot.controller;


import com.stockpilot.service.SaleService;
import com.stockpilot.util.Navigator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;



public class ReportsController {


    @FXML
    private Label todaySalesLabel;


    @FXML
    private Label bestSellerLabel;



    private final SaleService saleService =
            new SaleService();




    @FXML
    public void initialize(){


        double todaySales =
                saleService.getTodaySales();



        String bestSeller =
                saleService.getBestSellingRecipe();



        todaySalesLabel.setText(
                String.format("%.2f", todaySales)
        );



        bestSellerLabel.setText(
                bestSeller
        );


    }





    @FXML
    private void goBack(){

        Navigator.goBack();

    }


}