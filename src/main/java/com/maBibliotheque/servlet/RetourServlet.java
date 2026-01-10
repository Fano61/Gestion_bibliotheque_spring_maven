package com.maBibliotheque.servlet;

import com.maBibliotheque.service.EmpruntService;
import com.maBibliotheque.repository.AdherentRepository;
import com.maBibliotheque.repository.ExemplaireRepository;
import com.maBibliotheque.repository.EmpruntRepository;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class RetourServlet extends HttpServlet {

    private EmpruntService empruntService;

    @Override
    public void init() {
        empruntService = new EmpruntService(
            new AdherentRepository(),
            new ExemplaireRepository(),
            new EmpruntRepository()
        );
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/retour.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idAdherentStr = request.getParameter("idAdherent");
        String idExemplaireStr = request.getParameter("idExemplaire");
        String message;

        try {
            int idAdherent = Integer.parseInt(idAdherentStr);
            int idExemplaire = Integer.parseInt(idExemplaireStr);

            message = empruntService.retournerLivre(idAdherent, idExemplaire);
        } catch (NumberFormatException e) {
            message = "Entrée invalide.";
        }

        request.setAttribute("message", message);
        request.getRequestDispatcher("/WEB-INF/jsp/retour.jsp").forward(request, response);
    }
}
