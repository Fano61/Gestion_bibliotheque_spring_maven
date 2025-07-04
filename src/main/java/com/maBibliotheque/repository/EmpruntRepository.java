package com.maBibliotheque.repository;

import com.maBibliotheque.util.DatabaseConnection;
import java.sql.*;
import java.time.LocalDate;

public class EmpruntRepository {

    public void enregistrerEmprunt(int idAdherent, int idExemplaire, LocalDate dateRetourPrevue) {
        String sql = "INSERT INTO emprunt (id_adherent, id_exemplaire, date_emprunt, date_retour_prevue) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idAdherent);
            ps.setInt(2, idExemplaire);
            ps.setDate(3, Date.valueOf(LocalDate.now()));
            ps.setDate(4, Date.valueOf(dateRetourPrevue));

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public LocalDate calculerDateRetour(int idAdherent) {
        String sql = "SELECT ta.duree_emprunt_auth " +
                     "FROM adherent a " +
                     "JOIN type_adherent ta ON a.id_type_adherent = ta.id_type_adherent " +
                     "WHERE a.id_adherent = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idAdherent);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int duree = rs.getInt("duree_emprunt_auth");
                return LocalDate.now().plusDays(duree);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Si erreur ou type non trouvé, retour dans 0 jour (donc aujourd’hui)
        return LocalDate.now();
    }

    public boolean empruntActifExiste(int idAdherent, int idExemplaire) {
    String sql = "SELECT COUNT(*) FROM emprunt WHERE id_adherent = ? AND id_exemplaire = ? AND date_retour_effective IS NULL";
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, idAdherent);
        ps.setInt(2, idExemplaire);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt(1) > 0;
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}

public LocalDate getDateRetourPrevue(int idAdherent, int idExemplaire) {
    String sql = "SELECT date_retour_prevue FROM emprunt WHERE id_adherent = ? AND id_exemplaire = ? AND date_retour_effective IS NULL";
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, idAdherent);
        ps.setInt(2, idExemplaire);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Date date = rs.getDate("date_retour_prevue");
            if (date != null) {
                return date.toLocalDate();
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}

public void enregistrerRetour(int idAdherent, int idExemplaire, LocalDate dateRetourEffectif) {
    String sql = "UPDATE emprunt SET date_retour_effective = ? WHERE id_adherent = ? AND id_exemplaire = ? AND date_retour_effective IS NULL";
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setDate(1, Date.valueOf(dateRetourEffectif));
        ps.setInt(2, idAdherent);
        ps.setInt(3, idExemplaire);
        ps.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

public void enregistrerHistorique(int idEmprunt, int idAction) {
    String sql = "INSERT INTO historique_emprunt (id_emprunt, id_action, date_action) VALUES (?, ?, NOW())";
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, idEmprunt);
        ps.setInt(2, idAction);
        ps.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


}
