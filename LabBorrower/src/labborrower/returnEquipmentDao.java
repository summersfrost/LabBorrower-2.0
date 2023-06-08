/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labborrower;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author Frost
 */
public class returnEquipmentDao {
    
        public static int save(String studentID, String equipmentID,String date) {
         int status = 0;
    try {
        Connection conn = DB.getConnection();
        
        // Update the borrowed_equipment table to mark the equipment as returned
        java.sql.PreparedStatement returnPs = conn.prepareStatement("UPDATE borrowed_equipment SET returned_date = ? WHERE student_id = ? AND equipment_id = ?");
        returnPs.setString(1,date);
        returnPs.setString(2, studentID);
        returnPs.setString(3, equipmentID);
        status = returnPs.executeUpdate();
        
        // Update the equipment availability
        java.sql.PreparedStatement updatePs = conn.prepareStatement("UPDATE equipments SET isAvailable = ?, isBorrowed = ? WHERE refID = ?");
        updatePs.setBoolean(1, true);
        updatePs.setBoolean(2, false);
        updatePs.setString(3, equipmentID);
        updatePs.executeUpdate();
        
        // Close the statements and connection
        returnPs.close();
        updatePs.close();
        conn.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return status;
    }
}

