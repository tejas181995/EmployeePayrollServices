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
    public Statement statement;
    List<EmpPayRollData> employeePayrollData = new ArrayList<>();


    private Connection connectonEstablish() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver found!");
            connection = DriverManager.getConnection(URL, user, password);
            statement = connection.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
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
    public List<EmpPayRollData> readData(String sql) {
        try {
            try {
                if (connection == null || connection.isClosed())
                    throw new EmpPayRollException("Connection is not established");
            }
            catch (EmpPayRollException e) {
                System.out.println(e);
                connectonEstablish();
            }
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double salary = resultSet.getDouble("salary");
                String gender = resultSet.getString("gender");
                LocalDate startDate = resultSet.getDate("start").toLocalDate();
                employeePayrollData.add(new EmpPayRollData(id, name, gender, startDate, salary));
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        employeePayrollData.forEach(data -> System.out.println(data.id
                +" "+data.name+" "+data.salary+" "+data.start+" "+data.gender));
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
        String sql = "SELECT * FROM emp_payroll";
        this.readData(sql);
        for (EmpPayRollData data : employeePayrollData) {
            if(data.name.equals(name)){
                return data.salary;
            }
        }
        return 0.0;
    }
}

