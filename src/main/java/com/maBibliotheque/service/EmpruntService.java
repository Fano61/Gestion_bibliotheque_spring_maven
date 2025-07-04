package com.maBibliotheque.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import com.maBibliotheque.repository.AdherentRepository;
import com.maBibliotheque.repository.ExemplaireRepository;
import com.maBibliotheque.util.DatabaseConnection;
import com.maBibliotheque.repository.EmpruntRepository;

public class EmpruntService {

    private AdherentRepository adherentRepository;
    private ExemplaireRepository exemplaireRepository;
    private EmpruntRepository empruntRepository;

    // Constructeur attendu par Spring
    public EmpruntService(AdherentRepository adherentRepository, ExemplaireRepository exemplaireRepository, EmpruntRepository empruntRepository) {
        this.adherentRepository = adherentRepository;
        this.exemplaireRepository = exemplaireRepository;
        this.empruntRepository = empruntRepository;
    }

    public String emprunterLivre(int idAdherent, int idExemplaire) {

    // Règle 1 : L’adhérent existe
    if (!adherentRepository.adherentExiste(idAdherent)) {
        return "Erreur : adhérent inexistant.";
    }

    // Règle 2 à 6 (à implémenter dans AdherentRepository + ExemplaireRepository)
    if (!adherentRepository.adherentActif(idAdherent)) {
        return "Erreur : adhérent inactif.";
    }

    if (adherentRepository.estSanctionne(idAdherent)) {
        return "Erreur : adhérent sanctionné.";
    }

    if (!exemplaireRepository.exemplaireExiste(idExemplaire)) {
        return "Erreur : exemplaire inexistant.";
    }

    if (!exemplaireRepository.estDisponible(idExemplaire)) {
        return "Erreur : exemplaire non disponible.";
    }

    if (adherentRepository.quotaDepasse(idAdherent)) {
        return "Erreur : quota d'emprunts atteint.";
    }

    if (!adherentRepository.estAutoriseAEmprunter(idAdherent)) {
        return "Erreur : type d’adhérent non autorisé à emprunter.";
    }

    // Tout est OK : Calculer la date de retour et enregistrer l’emprunt
    LocalDate dateRetour = empruntRepository.calculerDateRetour(idAdherent);
    empruntRepository.enregistrerEmprunt(idAdherent, idExemplaire, dateRetour);
    exemplaireRepository.marquerIndisponible(idExemplaire);
    // adherentRepository.decrementerQuota(idAdherent);

    return "Emprunt validé. Retour prévu le : " + dateRetour;
}

public String retournerLivre(int idAdherent, int idExemplaire) {
    if (!adherentRepository.adherentExiste(idAdherent)) {
        return "Erreur : adhérent inexistant.";
    }

    if (!exemplaireRepository.exemplaireExiste(idExemplaire)) {
        return "Erreur : exemplaire inexistant.";
    }

    if (!empruntRepository.empruntActifExiste(idAdherent, idExemplaire)) {
        return "Erreur : emprunt inexistant ou déjà retourné.";
    }

    LocalDate dateRetourPrevue = empruntRepository.getDateRetourPrevue(idAdherent, idExemplaire);
    LocalDate dateRetourEffectif = LocalDate.now();

    // Récupérer l'id de l'emprunt actif
    int idEmprunt = getIdEmpruntActif(idAdherent, idExemplaire);
    if (idEmprunt == -1) {
        return "Erreur : emprunt introuvable.";
    }

    // Mettre à jour la date de retour dans la table emprunt
    empruntRepository.enregistrerRetour(idAdherent, idExemplaire, dateRetourEffectif);

    // Enregistrer l'action "retour" dans l'historique (idAction = 2)
    empruntRepository.enregistrerHistorique(idEmprunt, 2);

    // Marquer l'exemplaire comme disponible
    exemplaireRepository.marquerDisponible(idExemplaire);

    // Vérifier retard et appliquer pénalité si besoin
    if (dateRetourEffectif.isAfter(dateRetourPrevue)) {
        int dureePenalite = adherentRepository.getDureePenalite(idAdherent);
        adherentRepository.appliquerPenalite(idAdherent, dureePenalite);
        return "Retour enregistré avec retard. Pénalité appliquée pour " + dureePenalite + " jours.";
    } else {
        return "Retour enregistré dans les temps.";
    }
}

public int getIdEmpruntActif(int idAdherent, int idExemplaire) {
    String sql = "SELECT id_emprunt FROM emprunt WHERE id_adherent = ? AND id_exemplaire = ? AND date_retour_effective IS NULL";
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, idAdherent);
        ps.setInt(2, idExemplaire);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt("id_emprunt");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return -1; // pas trouvé
}


}
