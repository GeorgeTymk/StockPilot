package com.stockpilot;


import com.stockpilot.database.Database;
import com.stockpilot.database.DatabaseInitializer;
import com.stockpilot.database.UserSeeder;

import java.sql.Connection;



public class Main {


    public static void main(String[] args) throws Exception {


        try(Connection connection = Database.connect()){


            DatabaseInitializer.createTables();


            UserSeeder.createAdmin();


        }



        App.main(args);


    }

}