package servlets;

import beans.SessionServiceBean;
import entity.Student;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gijoe on 7/15/2015.
 */
@WebServlet("ServletFind")
public class ServletFind extends HttpServlet {

    @EJB
    private SessionServiceBean sessionServiceBean;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String criterion = request.getParameter("findCriterion");
        if (criterion == null) {
            criterion = "";
        }
        Student student = new Student();
        ArrayList<Student> students = new ArrayList<>(sessionServiceBean.findByCriterion("'%" + criterion + "%'"));
        request.getSession().setAttribute("students", students);
        request.getServletContext().getRequestDispatcher("/find.jsp").forward(request, response);
    }
}
