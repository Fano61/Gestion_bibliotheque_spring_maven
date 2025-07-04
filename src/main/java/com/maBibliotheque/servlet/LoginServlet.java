package com.maBibliotheque.servlet;

import com.maBibliotheque.repository.StaffRepository;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    private StaffRepository staffRepo = new StaffRepository();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if(staffRepo.validerLogin(username, password)) {
            HttpSession session = request.getSession();
            session.setAttribute("user", username);
            response.sendRedirect("emprunt"); // redirige vers page emprunt
        } else {
            request.setAttribute("error", "Utilisateur ou mot de passe incorrect");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }
}
