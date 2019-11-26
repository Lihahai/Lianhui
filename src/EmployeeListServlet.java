import DAO.EmployeeDAO;
import shitilei.Employee;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class EmployeeListServlet extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Employee> employees = new EmployeeDAO().list();
        request.setAttribute("employees", employees);
        request.getRequestDispatcher("listEmployee.jsp").forward(request, response);

    }
}