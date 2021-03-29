package com.bridgelabz.emppayroll;


import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

public class EmpPayRoll {
    private static final String URL = "jdbc:mysql://localhost:3306/ Emp_payroll_service?allowPublicKeyRetrieval=true&useSSL=false";
    private static final String user = "tejas";
    private static final String password = "Password@123";

    private void connectonEstablish() {
        Connection connection;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver found!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        listDrivers();
        try {
            System.out.println("\nConnecting to database: " + URL);
            connection = DriverManager.getConnection(URL, user, password);
            System.out.println("Connection established with: " + connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void listDrivers() {
        Enumeration<Driver> driverList = DriverManager.getDrivers();
        while (driverList.hasMoreElements()) {
            Driver driverClass = driverList.nextElement();
            System.out.println("Driver: " + driverClass.getClass().getName());
        }
    }

    public static void main(String[] args) {
        EmpPayRoll employeePayroll = new EmpPayRoll();
        employeePayroll.connectonEstablish();
    }
}
