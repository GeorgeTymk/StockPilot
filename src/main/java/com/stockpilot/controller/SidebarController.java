package com.stockpilot.controller;

import com.stockpilot.util.Navigator;
import javafx.scene.control.Button;
import javafx.fxml.FXML;

public class SidebarController {

    @FXML
private Button dashboardBtn;

@FXML
private Button inventoryBtn;

@FXML
private Button ingredientsBtn;

@FXML
private Button recipesBtn;

@FXML
private Button suppliersBtn;

@FXML
private Button salesBtn;

@FXML
private Button salesHistoryBtn;

@FXML
private Button reportsBtn;

@FXML
private Button lowStockBtn;


    @FXML
    private void openInventory() {
        Navigator.goTo("inventory.fxml");
    }


    @FXML
    private void openIngredients() {
        Navigator.goTo("ingredients.fxml");
    }


    @FXML
    private void openRecipes() {
        Navigator.goTo("recipes.fxml");
    }


    @FXML
    private void openSuppliers() {
        Navigator.goTo("suppliers.fxml");
    }

    @FXML
private void openDashboard(){

    Navigator.goTo("dashboard.fxml");

}

    @FXML
    private void openSales() {
        Navigator.goTo("sales.fxml");
    }


    @FXML
    private void openSalesHistory() {
        Navigator.goTo("sales_history.fxml");
    }


    @FXML
    private void openReports() {
        Navigator.goTo("reports.fxml");
    }


    @FXML
    private void openLowStock() {
        Navigator.goTo("low_stock.fxml");
    }


    @FXML
    private void logout() {
        Navigator.goTo("login.fxml");
    }

    private void clearActive() {

    dashboardBtn.getStyleClass().remove("sidebar-active");
    inventoryBtn.getStyleClass().remove("sidebar-active");
    ingredientsBtn.getStyleClass().remove("sidebar-active");
    recipesBtn.getStyleClass().remove("sidebar-active");
    suppliersBtn.getStyleClass().remove("sidebar-active");
    salesBtn.getStyleClass().remove("sidebar-active");
    salesHistoryBtn.getStyleClass().remove("sidebar-active");
    reportsBtn.getStyleClass().remove("sidebar-active");
    lowStockBtn.getStyleClass().remove("sidebar-active");
}

public void setActive(String page) {

    clearActive();

    switch (page) {

        case "dashboard":
            dashboardBtn.getStyleClass().add("sidebar-active");
            break;

        case "inventory":
            inventoryBtn.getStyleClass().add("sidebar-active");
            break;

        case "ingredients":
            ingredientsBtn.getStyleClass().add("sidebar-active");
            break;

        case "recipes":
            recipesBtn.getStyleClass().add("sidebar-active");
            break;

        case "suppliers":
            suppliersBtn.getStyleClass().add("sidebar-active");
            break;

        case "sales":
            salesBtn.getStyleClass().add("sidebar-active");
            break;

        case "history":
            salesHistoryBtn.getStyleClass().add("sidebar-active");
            break;

        case "reports":
            reportsBtn.getStyleClass().add("sidebar-active");
            break;

        case "lowstock":
            lowStockBtn.getStyleClass().add("sidebar-active");
            break;
    }
}

}