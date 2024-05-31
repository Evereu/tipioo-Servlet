package com.example.tpo5;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "inputServlet", value = "/input-servlet")
public class InputServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        String getCarsInput = request.getParameter("getCars");

        if (getCarsInput == null || getCarsInput.trim().isEmpty()) {
            response.sendRedirect("error.jsp");
        } else {
            request.setAttribute("getCars", getCarsInput);
            request.getRequestDispatcher("repository_servlet").forward(request, response);
        }
    }
}