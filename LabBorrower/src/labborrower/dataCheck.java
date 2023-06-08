/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labborrower;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Frost
 */
public class dataCheck {
    public dataCheck(){}
    public boolean checkIfRefIDExists(String refID) {
     boolean exists = false;
    try {
        Connection conn = DB.getConnection();
         java.sql.PreparedStatement ps = conn.prepareStatement(
                "SELECT COUNT(*) FROM equipments WHERE refID = ?");
        ps.setString(1, refID);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            int count = rs.getInt(1);
            exists = count > 0;
        }

        conn.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return exists;
}

  public boolean checkIfStudentIDExists(String studentID) {
    boolean exists = false;
    try {
        Connection conn = DB.getConnection();
        java.sql.PreparedStatement ps = conn.prepareStatement(
                "SELECT COUNT(*) FROM students WHERE studentID = ?");
        ps.setString(1, studentID);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            int count = rs.getInt(1);
            exists = count > 0;
        }

        conn.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }

    System.out.println("Student ID: " + studentID + " Exists: " + exists); // Debug statement

    return exists;
}

    
}
