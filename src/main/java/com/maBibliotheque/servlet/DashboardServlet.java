package com.maBibliotheque.servlet;

import com.maBibliotheque.repository.DashboardRepository;
import com.maBibliotheque.service.DashboardService;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class DashboardServlet extends HttpServlet {

    private DashboardService dashboardService;

    @Override
    public void init() {
        DashboardRepository repo = new DashboardRepository();
        this.dashboardService = new DashboardService(repo, null);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> topLivres = dashboardService.getLivresLesPlusEmpruntes();
List<String> topAdherents = dashboardService.getAdherentsLesPlusActifs();

req.setAttribute("topLivres", topLivres);
req.setAttribute("topAdherents", topAdherents);

        req.setAttribute("livres", dashboardService.getLivresLesPlusEmpruntes());
        req.setAttribute("adherents", dashboardService.getAdherentsLesPlusActifs());
        // req.setAttribute("profils", dashboardService.getNombreEmpruntsParProfil());
        req.setAttribute("tauxRetard", dashboardService.getTauxDeRetard());
        req.setAttribute("livresPlusEmpruntes", dashboardService.getLivresPlusEmpruntes());
req.setAttribute("adherentsActifs", dashboardService.getAdherentsActifs());

req.getRequestDispatcher("/WEB-INF/jsp/dashboard.jsp").forward(req, resp);
    }
}
