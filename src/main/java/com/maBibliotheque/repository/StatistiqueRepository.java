package com.maBibliotheque.repository;

import com.maBibliotheque.util.DatabaseConnection;

import java.sql.*;
import java.util.*;

public class StatistiqueRepository {

    public Map<String, Integer> getLivresLesPlusEmpruntes() {
        Map<String, Integer> resultats = new LinkedHashMap<>();
        String sql = """
            SELECT l.titre, COUNT(*) AS nb
            FROM emprunt e
            JOIN exemplaire ex ON e.id_exemplaire = ex.id_exemplaire
            JOIN livre l ON ex.id_livre = l.id_livre
            GROUP BY l.titre
            ORDER BY nb DESC
            LIMIT 5
        """;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                resultats.put(rs.getString("titre"), rs.getInt("nb"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultats;
    }

    public Map<String, Integer> getAdherentsLesPlusActifs() {
        Map<String, Integer> resultats = new LinkedHashMap<>();
        String sql = "SELECT a.nom, COUNT(*) AS nb FROM emprunt e JOIN adherent a ON e.id_adherent = a.id_adherent GROUP BY a.nom ORDER BY nb DESC LIMIT 5";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                resultats.put(rs.getString("nom"), rs.getInt("nb"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultats;
    }
}
