package com.maBibliotheque.repository;

import com.maBibliotheque.util.DatabaseConnection;
import java.sql.*;
import java.util.*;

public class DashboardRepository {

    // Livres les plus empruntés
public List<String> getLivresLesPlusEmpruntes() {
    List<String> resultats = new ArrayList<>();
    String sql = " SELECT l.titre, COUNT(*) as nb FROM emprunt e JOIN exemplaire ex ON e.id_exemplaire = ex.id_exemplaire JOIN livre l ON ex.id_livre = l.id_livre GROUP BY l.titre ORDER BY nb DESC LIMIT 5 ";

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            String ligne = rs.getString("titre") + " (" + rs.getInt("nb") + " emprunts)";
            resultats.add(ligne);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return resultats;
}
    public double getTauxDeRetard() {
        String sql = "SELECT COUNT(*) AS total, SUM(CASE WHEN date_retour_effective > date_retour_prevue THEN 1 ELSE 0 END) AS retards FROM emprunt WHERE date_retour_effective IS NOT NULL";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next() && rs.getInt("total") > 0) {
                int total = rs.getInt("total");
                int retards = rs.getInt("retards");
                return (retards * 100.0) / total;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    // Adhérents les plus actifs
public List<String> getAdherentsLesPlusActifs() {
    List<String> resultats = new ArrayList<>();
    String sql = " SELECT a.nom, a.prenom, COUNT(*) as nb FROM emprunt e JOIN adherent a ON e.id_adherent = a.id_adherent GROUP BY a.id_adherent ORDER BY nb DESC LIMIT 5";

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            String ligne = rs.getString("nom") + " " + rs.getString("prenom") + " (" + rs.getInt("nb") + " emprunts)";
            resultats.add(ligne);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return resultats;
}

    private List<Map<String, Object>> executeListQuery(String sql) {
        List<Map<String, Object>> list = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            ResultSetMetaData meta = rs.getMetaData();
            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                for (int i = 1; i <= meta.getColumnCount(); i++) {
                    row.put(meta.getColumnLabel(i), rs.getObject(i));
                }
                list.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
