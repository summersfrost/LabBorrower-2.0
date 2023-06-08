/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labborrower;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Frost
 */
public class issueEquipmentDao {
  
    
      public static int save(String studentID, String equipmentID, String date) {
    int status = 0;
    try {
        Connection conn = DB.getConnection();

        // Check if the student ID exists
        java.sql.PreparedStatement studentPs = conn.prepareStatement("SELECT studentID FROM students WHERE studentID = ?");
        studentPs.setString(1, studentID);
        ResultSet studentRs = studentPs.executeQuery();

        boolean studentExists = studentRs.next();

        // Close the result set and statement
        studentRs.close();
        studentPs.close();

        if (studentExists) {
            // Check if the equipment is available for borrowing
            java.sql.PreparedStatement availabilityPs = conn.prepareStatement("SELECT isAvailable, isBorrowed FROM equipments WHERE refID = ?");
            availabilityPs.setString(1, equipmentID);
            ResultSet availabilityRs = availabilityPs.executeQuery();

            boolean isAvailable = false;
            boolean isBorrowed = false;

            if (availabilityRs.next()) {
                isAvailable = availabilityRs.getBoolean("isAvailable");
                isBorrowed = availabilityRs.getBoolean("isBorrowed");
            }

            // Close the result set and statement
            availabilityRs.close();
            availabilityPs.close();

            if (isAvailable && !isBorrowed) {
                // Update the borrowed_equipment table to mark the equipment as borrowed
                java.sql.PreparedStatement borrowPs = conn.prepareStatement("INSERT INTO borrowed_equipment (student_id, equipment_id, borrowed_date) VALUES (?, ?, ?)");
                borrowPs.setString(1, studentID);
                borrowPs.setString(2, equipmentID);
                borrowPs.setString(3, date);
                status = borrowPs.executeUpdate();

                // Update the equipment availability
                java.sql.PreparedStatement updatePs = conn.prepareStatement("UPDATE equipments SET isAvailable = ?, isBorrowed = ? WHERE refID = ?");
                updatePs.setBoolean(1, false);
                updatePs.setBoolean(2, true);
                updatePs.setString(3, equipmentID);
                updatePs.executeUpdate();

                // Close the statements
                borrowPs.close();
                updatePs.close();
            } else {
                // The equipment is either already borrowed or not available
                status = -1;
                   JOptionPane.showMessageDialog(null, "Item is either not available or borrowed.");
            }
        }

        // Close the connection
        conn.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return status;
}
}

