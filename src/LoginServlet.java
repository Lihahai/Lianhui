import DAO.UserDAO;
import shitilei.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class LoginServlet extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userName= (String) request.getParameter("user");
        String pwd= (String) request.getParameter("pwd");
        System.out.println(userName);
        System.out.println(pwd);
        User user;

        user=new UserDAO().get(userName);
        System.out.println(user.getuAccount());
        System.out.println(user.getuPwd());
        if(null!=user){
            if(!user.getuPwd().equals(pwd)){
                response.sendRedirect("Login.jsp");
            }else{
                request.getSession().setAttribute("user",user);
                request.getRequestDispatcher("index.jsp").forward(request,response);
            }
        }else {
            response.sendRedirect("Login.jsp");
        }



    }
}
