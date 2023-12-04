package com.example.homeartifact;


import java.sql.*;
import java.util.ArrayList;

public class DataBaseHandler extends Configs {
    Connection dbConnection;
    public Connection getDbConnection()
            throws ClassNotFoundException, SQLException {

        String connectionString = "jdbc:mysql://" + dbHost + ":"
                + dbPort + "/" + dbName;

        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConnection = DriverManager.getConnection(
                connectionString,
                dbUser,
                dbPass
        );

        if (dbConnection != null) {
            System.out.println("Database Connected successfully");
        } else {
            System.out.println("Database Connection failed");
        }
        return  dbConnection;
    }

    public void addTask(String taskName, Date deadline, Date created) {
        String insert = "INSERT INTO " + Const.TASK_TABLE + " (" +
                Const.TASK_NAME + "," + Const.COMPLETED + "," +
                Const.DEADLINE + "," + Const.CREATED + ")" +
                "VALUES(?,?,?,?)";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, taskName);
            prSt.setBoolean(2, false);
            prSt.setDate(3, deadline);
            prSt.setDate(4, created);
            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<TaskModel> getTodayTask() {
        ArrayList<TaskModel> tasks = new ArrayList<>();

        try {
            Statement statement = getDbConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + Const.TASK_TABLE);

            while(resultSet.next()){

                String task_name = resultSet.getString(Const.TASK_NAME);
                boolean isCompleted = resultSet.getBoolean(Const.COMPLETED);
                Date task_deadline = resultSet.getDate(Const.DEADLINE);
                Date created = resultSet.getDate(Const.CREATED);

                TaskModel task = new TaskModel(task_name, isCompleted);
                task.setDeadline(task_deadline);
                task.setCreated(created);


                tasks.add(task);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return tasks;
    }
}
