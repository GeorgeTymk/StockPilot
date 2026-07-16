package com.stockpilot.controller;


import com.stockpilot.model.Activity;
import com.stockpilot.service.ActivityService;
import com.stockpilot.service.DashboardService;
import com.stockpilot.service.IngredientService;
import com.stockpilot.service.SaleService;
import com.stockpilot.util.Navigator;


import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Tooltip;

import javafx.scene.layout.VBox;


import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Map;



public class DashboardController {


    private final DashboardService dashboardService =
            new DashboardService();


    private final SaleService saleService =
            new SaleService();


    private final IngredientService ingredientService =
            new IngredientService();


    private final ActivityService activityService =
            new ActivityService();



    private final NumberFormat currencyFormat =
            NumberFormat.getNumberInstance(Locale.US);



    @FXML
    private SidebarController sidebarController;



    @FXML
    private Label totalSalesLabel;

    @FXML
    private Label totalOrdersLabel;

    @FXML
    private Label todaySalesLabel;

    @FXML
    private Label bestSellerLabel;

    @FXML
    private Label lowStockLabel;

    @FXML
    private Label totalIngredientsLabel;

    @FXML
    private Label outOfStockLabel;



    @FXML
    private LineChart<String,Number> salesChart;



    @FXML
    private ListView<Activity> activityListView;




    @FXML
    public void initialize(){


        loadDashboardStatistics();


        loadSalesChart();


        loadRecentActivity();



        if(sidebarController != null){

            sidebarController.setActive("dashboard");

        }


    }






    private void loadDashboardStatistics(){


        double totalSales =
                saleService.getTotalSales();


        int totalOrders =
                saleService.getTotalOrders();


        double todaySales =
                saleService.getTodaySales();


        String bestSeller =
                saleService.getBestSellingRecipe();



        int lowStock =
                ingredientService.getLowStockCount();



        int totalIngredients =
                ingredientService.getIngredientCount();



        int outOfStock =
                ingredientService.getOutOfStockCount();





        if(totalSalesLabel != null)
            totalSalesLabel.setText(
                    "MK "
                    + currencyFormat.format(totalSales)
                    + ".00"
            );



        if(totalOrdersLabel != null)
            totalOrdersLabel.setText(
                    String.valueOf(totalOrders)
            );



        if(todaySalesLabel != null)
            todaySalesLabel.setText(
                    "MK "
                    + currencyFormat.format(todaySales)
                    + ".00"
            );



        if(bestSellerLabel != null)
            bestSellerLabel.setText(
                    bestSeller
            );



        if(lowStockLabel != null)
            lowStockLabel.setText(
                    String.valueOf(lowStock)
            );



        if(totalIngredientsLabel != null)
            totalIngredientsLabel.setText(
                    String.valueOf(totalIngredients)
            );



        if(outOfStockLabel != null)
            outOfStockLabel.setText(
                    String.valueOf(outOfStock)
            );


    }








    private void loadRecentActivity(){


        if(activityListView == null){

            System.out.println(
                    "activityListView not connected"
            );

            return;

        }



        setupActivityList();



        ObservableList<Activity> activities =

                FXCollections.observableArrayList(

                        activityService.getRecentActivities()

                );



        System.out.println(
                "Dashboard received: "
                + activities.size()
        );



        for(Activity activity : activities){

            System.out.println(
                    activity.getMessage()
            );

        }



        activityListView.setItems(
                activities
        );


        activityListView.refresh();


    }








    private void setupActivityList(){


        activityListView.setCellFactory(list ->

                new ListCell<Activity>(){


                    @Override
                    protected void updateItem(
                            Activity activity,
                            boolean empty
                    ){


                        super.updateItem(
                                activity,
                                empty
                        );



                        if(empty || activity == null){


                            setGraphic(null);

                            setText(null);

                            return;

                        }





                        Label message =
                                new Label(
                                        activity.getMessage()
                                );


                        message.getStyleClass()
                                .add(
                                        "activity-title"
                                );



                        Label type =
                                new Label(
                                        activity.getType()
                                                .toUpperCase()
                                );



                        switch(
                                activity.getType()
                                .toUpperCase()
                        ){


                            case "RESTOCK":

                                type.getStyleClass()
                                        .add(
                                                "activity-restock"
                                        );

                                break;



                            case "SALE":

                                type.getStyleClass()
                                        .add(
                                                "activity-sale"
                                        );

                                break;



                            default:

                                type.getStyleClass()
                                        .add(
                                                "activity-default"
                                        );

                        }




                        Label time =
                                new Label(
                                        activity.getTime()
                                );



                        time.getStyleClass()
                                .add(
                                        "activity-time"
                                );





                        VBox box =
        new VBox(6);

box.setPrefWidth(600);


box.getChildren()
        .addAll(
                message,
                type,
                time
        );



                        box.getStyleClass()
                                .add(
                                        "activity-card"
                                );



                        setPrefHeight(80);



                        setGraphic(box);


                    }


                }

        );


    }










    private void loadSalesChart(){


        if(salesChart == null)
            return;




        if(salesChart.getYAxis() instanceof NumberAxis axis){


            axis.setTickLabelFormatter(

                    new NumberAxis.DefaultFormatter(
                            axis,
                            "MK ",
                            ""
                    )

            );

        }




        XYChart.Series<String,Number> series =

                new XYChart.Series<>();


        series.setName("Sales");



        Map<String,Double> sales =

                saleService.getSalesOverview();




        DateTimeFormatter formatter =

                DateTimeFormatter.ofPattern(
                        "dd MMM yy"
                );




        for(
                Map.Entry<String,Double> entry :
                sales.entrySet()
        ){


            LocalDate date =
                    LocalDate.parse(
                            entry.getKey()
                    );



            series.getData()
                    .add(

                            new XYChart.Data<>(

                                    date.format(formatter),

                                    entry.getValue()

                            )

                    );

        }




        salesChart.getData()
                .clear();


        salesChart.getData()
                .add(series);



        Platform.runLater(() -> {


            for(
                    XYChart.Data<String,Number> data :
                    series.getData()
            ){


                if(data.getNode()!=null){


                    Tooltip.install(

                            data.getNode(),

                            new Tooltip(

                                    data.getXValue()
                                    +
                                    "\nSales: MK "
                                    +
                                    String.format(
                                            "%,.2f",
                                            data.getYValue()
                                    )

                            )

                    );


                }

            }


        });


    }







    @FXML
    private void openSellRecipe(){

        Navigator.goTo("sell_recipe.fxml");

    }


    @FXML
    private void openInventory(){

        Navigator.goTo("inventory.fxml");

    }


    @FXML
    private void openSales(){

        Navigator.goTo("sales.fxml");

    }


    @FXML
    private void openSalesHistory(){

        Navigator.goTo("sales_history.fxml");

    }


    @FXML
    private void openRecipes(){

        Navigator.goTo("recipes.fxml");

    }


    @FXML
    private void openSuppliers(){

        Navigator.goTo("suppliers.fxml");

    }


    @FXML
    private void openIngredients(){

        Navigator.goTo("ingredients.fxml");

    }


    @FXML
    private void openLowStock(){

        Navigator.goTo("low_stock.fxml");

    }


    @FXML
    private void openReports(){

        Navigator.goTo("reports.fxml");

    }


    @FXML
    private void logout(){

        Navigator.goTo("login.fxml");

    }


}