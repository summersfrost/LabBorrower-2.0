/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labborrower;

/**
 *
 * @author Frost
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
public class equipmentDao {
    
public static int save(String refID, String name, String manufacturer, String model, String date) {
    int status = 0;
    
    // Check if any of the required fields are blank
    if (refID.isEmpty() || name.isEmpty() || manufacturer.isEmpty() || model.isEmpty() || date.isEmpty()) {
        JOptionPane.showMessageDialog(null, "One or more required fields are blank.", "Error", JOptionPane.ERROR_MESSAGE);
        return status;
    }

    try {
        Connection conn = DB.getConnection();

        // Check if a record with the same refID already exists (case-insensitive)
        PreparedStatement checkStatement = conn.prepareStatement("SELECT COUNT(*) FROM equipments WHERE UPPER(refID) = ?");
        checkStatement.setString(1, refID.toUpperCase());
        ResultSet resultSet = checkStatement.executeQuery();
        resultSet.next();
        int count = resultSet.getInt(1);

        if (count == 0) {
            // No record exists with the same refID, proceed with insertion
            PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO equipments(refID, name, manufacturer, model, dateAdded, isBorrowed, isAvailable) VALUES(?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, refID);
            ps.setString(2, capitalizeEachWord(name));
            ps.setString(3, capitalizeEachWord(manufacturer));
            ps.setString(4, model);
            ps.setString(5, date);
            ps.setBoolean(6, false);  // isBorrowed is set to false
            ps.setBoolean(7, true);   // isAvailable is set to true

            status = ps.executeUpdate();
        } else {
            // A record with the same refID already exists
            JOptionPane.showMessageDialog(null, "A record with the same refID already exists.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        conn.close();
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "An error occurred while saving the record.", "Error", JOptionPane.ERROR_MESSAGE);
    }
    return status;
}

private static String capitalizeEachWord(String input) {
    String[] words = input.toLowerCase().split("\\s");
    StringBuilder capitalized = new StringBuilder();
    for (String word : words) {
        if (word.length() > 0) {
            capitalized.append(Character.toUpperCase(word.charAt(0))).append(word.substring(1)).append(" ");
        }
    }
    return capitalized.toString().trim();
}


public static int update(int id, String newRefID, String name, String manufacturer, String model, int isBorrowed, int isAvailable) {
    int status = 0;

    // Check if any of the required fields are blank
    if (newRefID.isEmpty() || name.isEmpty() || manufacturer.isEmpty() || model.isEmpty()) {
        JOptionPane.showMessageDialog(null, "One or more required fields are blank.", "Error", JOptionPane.ERROR_MESSAGE);
        return status;
    }

    try {
        Connection conn = DB.getConnection();

        // Retrieve the existing record for comparison
        PreparedStatement retrieveStatement = conn.prepareStatement("SELECT refID, name, manufacturer, model, isBorrowed, isAvailable FROM equipments WHERE id = ?");
        retrieveStatement.setInt(1, id);
        ResultSet retrieveResultSet = retrieveStatement.executeQuery();

        if (retrieveResultSet.next()) {
            // Retrieve the existing values
            String existingRefID = retrieveResultSet.getString("refID");
            String existingName = retrieveResultSet.getString("name");
            String existingManufacturer = retrieveResultSet.getString("manufacturer");
            String existingModel = retrieveResultSet.getString("model");
            int existingIsBorrowed = retrieveResultSet.getInt("isBorrowed");
            int existingIsAvailable = retrieveResultSet.getInt("isAvailable");

            // Check if the new values are different from the existing values
            boolean hasChanges = !newRefID.equalsIgnoreCase(existingRefID)
                    || !name.equalsIgnoreCase(existingName)
                    || !manufacturer.equalsIgnoreCase(existingManufacturer)
                    || !model.equalsIgnoreCase(existingModel)
                    || isBorrowed != existingIsBorrowed
                    || isAvailable != existingIsAvailable;

            // Check if the item is still on loan
            PreparedStatement checkBorrowedStatement = conn.prepareStatement("SELECT COUNT(*) FROM borrowed_equipment WHERE equipment_id = ? AND returned_date IS NULL");
            checkBorrowedStatement.setString(1, existingRefID);
            ResultSet borrowedResultSet = checkBorrowedStatement.executeQuery();
            borrowedResultSet.next();
            int borrowedCount = borrowedResultSet.getInt(1);

            if (borrowedCount > 0) {
                // Item is on loan, display an error message
                JOptionPane.showMessageDialog(null, "The item is still on loan. Item cannot be updated.", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (hasChanges) {
                // Check if the newRefID already exists in the database
                PreparedStatement checkRefIDStatement = conn.prepareStatement("SELECT COUNT(*) FROM equipments WHERE UPPER(refID) = ?");
                checkRefIDStatement.setString(1, newRefID.toUpperCase());
                ResultSet refIDResultSet = checkRefIDStatement.executeQuery();
                refIDResultSet.next();
                int refIDCount = refIDResultSet.getInt(1);

                if (refIDCount == 0 || newRefID.equalsIgnoreCase(existingRefID)) {
                    // No record with the newRefID exists or the newRefID is the same as the existing one, update the record
                    PreparedStatement ps = conn.prepareStatement(
                            "UPDATE equipments SET refID = ?, name = ?, manufacturer = ?, model = ?, isBorrowed = ?, isAvailable = ? WHERE id = ?");
                    ps.setString(1, newRefID);
                    ps.setString(2, capitalizeEachWord(name));
                    ps.setString(3, capitalizeEachWord(manufacturer));
                    ps.setString(4, model);
                    ps.setInt(5, isBorrowed);
                    ps.setInt(6, isAvailable);
                    ps.setInt(7, id);

                    status = ps.executeUpdate();
                } else {
                    // A record with the newRefID already exists
                    JOptionPane.showMessageDialog(null, "A record with the specified refID already exists.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                // No changes in availability and borrowability fields, display a message or take appropriate action
                JOptionPane.showMessageDialog(null, "No changes in the item. Item update is not required.", "Message", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            // No record exists with the specified id
            JOptionPane.showMessageDialog(null, "No record with the specified id exists.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        conn.close();
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "An error occurred while updating the record.", "Error", JOptionPane.ERROR_MESSAGE);
    }
    return status;
}

public static int delete(String refID) {
    int status = 0;

    try {
        Connection conn = DB.getConnection();

        // Check if the equipment has a borrowing history
        PreparedStatement checkBorrowingStatement = conn.prepareStatement("SELECT * FROM borrowed_equipment WHERE equipment_id = ?");
        checkBorrowingStatement.setString(1, refID);
        ResultSet checkBorrowingResultSet = checkBorrowingStatement.executeQuery();

        if (checkBorrowingResultSet.next()) {
            // Equipment has a borrowing history, display an error message or handle it as per your requirements
            JOptionPane.showMessageDialog(null, "The equipment has a borrowing history and cannot be deleted.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            // No borrowing history, proceed with deleting the equipment
            PreparedStatement deleteStatement = conn.prepareStatement("DELETE FROM equipments WHERE refID = ?");
            deleteStatement.setString(1, refID);

            status = deleteStatement.executeUpdate();
        }

        conn.close();
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "An error occurred while deleting the equipment.", "Error", JOptionPane.ERROR_MESSAGE);
    }

    return status;
}

}
