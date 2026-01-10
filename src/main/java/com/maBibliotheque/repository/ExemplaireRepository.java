package com.maBibliotheque.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.maBibliotheque.util.DatabaseConnection;

public class ExemplaireRepository {
    // Code pour gérer les exemplaires en base

    public boolean exemplaireExiste(int idExemplaire) {
    String sql = "SELECT COUNT(*) FROM exemplaire WHERE id_exemplaire = ?";
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        
        ps.setInt(1, idExemplaire);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt(1) > 0;
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}

public boolean estDisponible(int idExemplaire) {
    String sql = "SELECT disponible FROM exemplaire WHERE id_exemplaire = ?";
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setInt(1, idExemplaire);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getBoolean("disponible");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}

public void marquerIndisponible(int idExemplaire) {
    String sql = "UPDATE exemplaire SET disponible = FALSE WHERE id_exemplaire = ?";
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setInt(1, idExemplaire);
        ps.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

public void marquerDisponible(int idExemplaire) {
    String sql = "UPDATE exemplaire SET disponible = TRUE WHERE id_exemplaire = ?";
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, idExemplaire);
        ps.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

}
