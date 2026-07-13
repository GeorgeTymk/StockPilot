package com.stockpilot.service;

import com.stockpilot.database.Database;
import com.stockpilot.model.Ingredient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class IngredientService {


    private final InventoryHistoryService historyService =
            new InventoryHistoryService();



    public void addIngredient(
            String name,
            double quantity,
            String unit,
            double minimumStock
    ) {


        String sql =
                """
                INSERT INTO ingredients
                (
                    name,
                    quantity,
                    unit,
                    minimum_stock
                )
                VALUES (?, ?, ?, ?)
                """;


        try(
                Connection connection = Database.connect();

                PreparedStatement statement =
                        connection.prepareStatement(sql)

        ){


            statement.setString(1,name);
            statement.setDouble(2,quantity);
            statement.setString(3,unit);
            statement.setDouble(4,minimumStock);


            statement.executeUpdate();


            System.out.println(
                    "Ingredient added successfully"
            );


        }
        catch(Exception e){

            e.printStackTrace();

        }


    }






    public List<Ingredient> getAllIngredients(){


        List<Ingredient> ingredients =
                new ArrayList<>();


        String sql =
                """
                SELECT *
                FROM ingredients
                ORDER BY name
                """;


        try(
                Connection connection = Database.connect();

                PreparedStatement statement =
                        connection.prepareStatement(sql);

                ResultSet result =
                        statement.executeQuery()

        ){


            while(result.next()){

                ingredients.add(
                        mapIngredient(result)
                );

            }


        }
        catch(Exception e){

            e.printStackTrace();

        }


        return ingredients;

    }







    public List<Ingredient> getLowStockIngredients(){


        List<Ingredient> ingredients =
                new ArrayList<>();


        String sql =
                """
                SELECT *
                FROM ingredients
                WHERE quantity <= minimum_stock
                ORDER BY quantity ASC
                """;


        try(
                Connection connection = Database.connect();

                PreparedStatement statement =
                        connection.prepareStatement(sql);

                ResultSet result =
                        statement.executeQuery()

        ){


            while(result.next()){

                ingredients.add(
                        mapIngredient(result)
                );

            }


        }
        catch(Exception e){

            e.printStackTrace();

        }


        return ingredients;

    }
















    public void addStock(
            int ingredientId,
            double quantity
    ){


        String sql =
                """
                UPDATE ingredients

                SET quantity = quantity + ?

                WHERE id = ?
                """;


        try(
                Connection connection = Database.connect();

                PreparedStatement statement =
                        connection.prepareStatement(sql)

        ){


            statement.setDouble(
                    1,
                    quantity
            );


            statement.setInt(
                    2,
                    ingredientId
            );


            int updated =
                    statement.executeUpdate();



            if(updated > 0){


                historyService.recordMovement(

                        ingredientId,

                        "RESTOCK",

                        quantity

                );


                System.out.println(
                        "Stock added successfully"
                );


            }



        }
        catch(Exception e){

            e.printStackTrace();

        }


    }








    public void deductIngredient(
            int ingredientId,
            double quantity
    ){


        String sql =
                """
                UPDATE ingredients

                SET quantity = quantity - ?

                WHERE id = ?
                AND quantity >= ?
                """;


        try(
                Connection connection = Database.connect();

                PreparedStatement statement =
                        connection.prepareStatement(sql)

        ){


            statement.setDouble(
                    1,
                    quantity
            );


            statement.setInt(
                    2,
                    ingredientId
            );


            statement.setDouble(
                    3,
                    quantity
            );



            int updated =
                    statement.executeUpdate();



            if(updated > 0){


                historyService.recordMovement(

                        ingredientId,

                        "SALE",

                        quantity

                );


            }


        }
        catch(Exception e){

            e.printStackTrace();

        }


    }









    public Ingredient getIngredientById(
            int ingredientId
    ){


        String sql =
                """
                SELECT *
                FROM ingredients
                WHERE id = ?
                """;


        try(
                Connection connection = Database.connect();

                PreparedStatement statement =
                        connection.prepareStatement(sql)

        ){


            statement.setInt(
                    1,
                    ingredientId
            );


            ResultSet result =
                    statement.executeQuery();



            if(result.next()){

                return mapIngredient(result);

            }


        }
        catch(Exception e){

            e.printStackTrace();

        }


        return null;

    }








    public void updateQuantity(
            int ingredientId,
            double quantity
    ){


        String sql =
                """
                UPDATE ingredients

                SET quantity = ?

                WHERE id = ?
                """;


        try(
                Connection connection = Database.connect();

                PreparedStatement statement =
                        connection.prepareStatement(sql)

        ){


            statement.setDouble(
                    1,
                    quantity
            );


            statement.setInt(
                    2,
                    ingredientId
            );


            statement.executeUpdate();


        }
        catch(Exception e){

            e.printStackTrace();

        }


    }











public int getLowStockCount() {

    String sql = """
        SELECT COUNT(*)
        FROM ingredients
        WHERE quantity > minimum_stock
          AND quantity <= (minimum_stock * 1.5)
        """;

    try (
            Connection connection = Database.connect();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery()
    ) {

        if (result.next()) {
            return result.getInt(1);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return 0;
}







    private Ingredient mapIngredient(
            ResultSet result
    ) throws Exception{


        return new Ingredient(

                result.getInt("id"),

                result.getString("name"),

                result.getDouble("quantity"),

                result.getString("unit"),

                result.getDouble("minimum_stock")

        );


    }

public int getIngredientCount() {

    String sql =
            """
            SELECT COUNT(*)
            FROM ingredients
            """;

    try (

            Connection connection =
                    Database.connect();

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            ResultSet result =
                    statement.executeQuery()

    ) {

        if (result.next()) {

            return result.getInt(1);

        }

    }
    catch (Exception e) {

        e.printStackTrace();

    }

    return 0;

}



public int getOutOfStockCount() {

    String sql =
            """
            SELECT COUNT(*)
            FROM ingredients
            WHERE quantity <= 0
            """;

    try (

            Connection connection =
                    Database.connect();

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            ResultSet result =
                    statement.executeQuery()

    ) {

        if (result.next()) {

            return result.getInt(1);

        }

    }
    catch (Exception e) {

        e.printStackTrace();

    }

    return 0;

}

}