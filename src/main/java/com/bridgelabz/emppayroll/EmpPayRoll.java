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
    public Connection connection;
    List<EmpPayRollData> employeePayrollData = new ArrayList<>();


    private Connection connectonEstablish() {

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
        employeePayrollData = new ArrayList<>();
        try (Connection connection = this.connectonEstablish()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String gender = resultSet.getString("gender");
                LocalDate startDate = resultSet.getDate("start").toLocalDate();
                double salary = resultSet.getDouble("salary");
                employeePayrollData.add(new EmpPayRollData(id, name, gender, startDate, salary));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeePayrollData;
    }

    public double updateEmployeeData(double salary, String name) {
        try {
            this.connectonEstablish();
            PreparedStatement preparedStatement = connection.prepareStatement("update emp_payroll set salary=? where name =?");
            preparedStatement.setDouble(1, salary);
            preparedStatement.setString(2,name);
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.readData();
        for (EmpPayRollData data : employeePayrollData) {
            if(data.name.equals(name)){
                return data.salary;
            }
        }
        return 0.0;
    }
}

