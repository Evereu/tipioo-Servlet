package com.example.tpo5;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "outputServlet", value = "/output-servlet")
public class OutputServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        List<Car> carsList = (List<Car>) request.getAttribute("cars");

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<table border='1'><tr><th>ID</th><th>Car Name</th><th>Car Brand</th><th>Car Type</th><th>Production Date</th><th>Fuel Consumption</th></tr>");

        if (carsList != null) {
            for (Car car : carsList) {
                out.println("<tr>");
                out.println("<td>" + car.getId() + "</td>");
                out.println("<td>" + car.getCarName() + "</td>");
                out.println("<td>" + car.getCarBrand() + "</td>");
                out.println("<td>" + car.getCarType() + "</td>");
                out.println("<td>" + car.getProductionDate() + "</td>");
                out.println("<td>" + car.getFuelConsumption() + "</td>");
                out.println("</tr>");
            }
        }
        out.println("</table>");
        out.println("<a href=\"index.jsp\">Powrót</a>");
        out.println("</body></html>");

        carsList.clear();
    }
}
