package com.stockpilot.database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;



public class Database {


    private static final String URL =
            "jdbc:sqlite:stockpilot.db";





    public static Connection connect()
            throws SQLException {


        Connection connection =
                DriverManager.getConnection(URL);



        Statement statement =
                connection.createStatement();



        statement.execute(
                "PRAGMA busy_timeout=5000"
        );



        statement.close();



        System.out.println(
                "Database connected successfully"
        );



        return connection;


    }


}