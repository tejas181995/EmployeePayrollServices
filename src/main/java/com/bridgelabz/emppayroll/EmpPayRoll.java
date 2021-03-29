package com.bridgelabz.emppayroll;


import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class EmpPayRoll {
    private static final String URL = "jdbc:mysql://localhost:3306/ Emp_payroll_service?allowPublicKeyRetrieval=true&useSSL=false";
    private static final String user = "tejas";
    private static final String password = "Password@123";

    private Connection connectonEstablish() {
        Connection connection = null;
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
        return connection;
    }

    private void listDrivers() {
        Enumeration<Driver> driverList = DriverManager.getDrivers();
        while (driverList.hasMoreElements()) {
            Driver driverClass = driverList.nextElement();
            System.out.println("Driver: " + driverClass.getClass().getName());
        }
    }
    public List<EmpPayRollData> readData() {
        String sql = "SELECT * FROM emp_payroll";
        List<EmpPayRollData> employeePayrollData = new ArrayList<>();
        try (Connection connection = this.connectonEstablish()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String gender = resultSet.getString("gender");
                LocalDate startDate = resultSet.getDate("start").toLocalDate();
                employeePayrollData.add(new EmpPayRollData(id, name, gender, startDate));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeePayrollData;
    }

    public static void main(String[] args) {
        EmpPayRoll employeePayroll = new EmpPayRoll();
        employeePayroll.connectonEstablish();
    }
}
