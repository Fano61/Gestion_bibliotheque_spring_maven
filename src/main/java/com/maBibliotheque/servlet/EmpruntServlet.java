package com.maBibliotheque.servlet;

import com.maBibliotheque.service.EmpruntService;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class EmpruntServlet extends HttpServlet {

    private EmpruntService empruntService;

    @Override
    public void init() {
        // Injection manuelle des dépendances
        // Ou récupérer via Spring si intégré (complexe, hors scope)
        empruntService = new EmpruntService(
            new com.maBibliotheque.repository.AdherentRepository(),
            new com.maBibliotheque.repository.ExemplaireRepository(),
            new com.maBibliotheque.repository.EmpruntRepository()
        );
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/emprunt.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idAdherentStr = request.getParameter("idAdherent");
        String idExemplaireStr = request.getParameter("idExemplaire");

        try {
            int idAdherent = Integer.parseInt(idAdherentStr);
            int idExemplaire = Integer.parseInt(idExemplaireStr);

            String message = empruntService.emprunterLivre(idAdherent, idExemplaire);
            request.setAttribute("message", message);
        } catch (NumberFormatException e) {
            request.setAttribute("message", "Entrée invalide.");
        }

        request.getRequestDispatcher("/WEB-INF/jsp/emprunt.jsp").forward(request, response);
    }
}
