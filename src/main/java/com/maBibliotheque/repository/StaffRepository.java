package com.maBibliotheque.repository;

import com.maBibliotheque.util.DatabaseConnection;
import java.sql.*;

public class StaffRepository {

    public boolean validerLogin(String username, String password) {
        String sql = "SELECT password FROM staff WHERE username = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String storedPassword = rs.getString("password");
                // Ici, comparaison simple (changer en hash plus tard)
                return storedPassword.equals(password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
