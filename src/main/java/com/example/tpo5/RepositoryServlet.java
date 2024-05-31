package com.example.tpo5;
import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "repositoryServlet", value = "/repository_servlet")
public class RepositoryServlet extends HttpServlet {
    List<Car> carList = new ArrayList<>();
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String connectionString = "jdbc:sqlserver://MICHO\\SQLEXPRESS;databaseName=ServletApp;encrypt=true;trustServerCertificate=true;user=sa;password=admin123;portNumber=1434";

        String carType = request.getParameter("getCars");

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection(connectionString);

            String queryString = "SELECT * FROM [ServletApp].[dbo].[Cars] where [car_type] = ?";

            PreparedStatement selectStatement = connection.prepareStatement(queryString);
            selectStatement.setString(1, carType);
            ResultSet queryResult = selectStatement.executeQuery();

            while (queryResult.next()) {
                Car car = new Car();
                car.setId(queryResult.getInt("id"));
                car.setCarName(queryResult.getString("car_name"));
                car.setCarBrand(queryResult.getString("car_brand"));
                car.setCarType(queryResult.getString("car_type"));
                car.setProductionDate(queryResult.getString("production_date"));
                car.setFuelConsumption(queryResult.getString("fuel_consumption"));
                carList.add(car);
            }

            request.setAttribute("cars", carList);
            request.getRequestDispatcher("/output-servlet").forward(request, response);

            carList.clear();

            selectStatement.close();
            queryResult.close();
            connection.close();
        } catch (SQLException | ClassNotFoundException | ServletException e) {
            throw new RuntimeException(e);
        }
    }









}
