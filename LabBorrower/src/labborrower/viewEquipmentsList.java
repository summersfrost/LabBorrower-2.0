/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labborrower;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Color;
import java.awt.Component;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Frost
 */
public class viewEquipmentsList extends javax.swing.JFrame {
       private TableRowSorter<DefaultTableModel> sorter;
          private DefaultTableModel model;
             public void scaleImage(){
       ImageIcon inventory=new ImageIcon(getClass().getResource("/labborrower/icons/inventory.png"));
    java.awt.Image img2=inventory.getImage();
    java.awt.Image imgScale2= img2.getScaledInstance(inventoryLogo.getWidth(),inventoryLogo.getHeight(),java.awt.Image.SCALE_SMOOTH);
    ImageIcon inventoryScaled=new ImageIcon(imgScale2);
    inventoryLogo.setIcon(inventoryScaled);
    }
 public void displayTable() {
    try {
        Connection con = DB.getConnection();
        java.sql.PreparedStatement ps = con.prepareStatement("SELECT * FROM equipments");
        ResultSet rs = ps.executeQuery();

        // Create a DefaultTableModel to hold the data
        DefaultTableModel model = new DefaultTableModel();

        // Add columns to the model based on the table structure
        model.addColumn("ID");
        model.addColumn("Reference ID");
        model.addColumn("Name");
        model.addColumn("Manufacturer");
        model.addColumn("Model");
        model.addColumn("Date Added");
        model.addColumn("Borrowability");
        model.addColumn("Availability");
      

        // Add rows to the model with the retrieved data
        while (rs.next()) {
            Vector<Object> row = new Vector<>();
            row.add(rs.getInt("id")); // Add the ID field
            row.add(rs.getString("refID"));
            row.add(rs.getString("name"));
            row.add(rs.getString("manufacturer"));
            row.add(rs.getString("model"));

            // Retrieve the dateAdded value
            Date dateAdded = rs.getDate("dateAdded");
            // Format the date as "Month DD, YYYY"
            DateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy");
            String formattedDate = dateFormat.format(dateAdded);
            row.add(formattedDate);

            boolean isAvailable = rs.getBoolean("isAvailable");
            boolean isBorrowable = rs.getBoolean("isBorrowed");
            row.add(isBorrowable ? "Not Borrowable" : "Borrowable");
            row.add(isAvailable ? "Available" : "Not Available");
         

            model.addRow(row);
        }

        // Set the model to the table
        table.setModel(model);

        // Create a TableRowSorter and apply it to the table
        sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);
      
        con.close();
    } catch (SQLException e) {
        System.out.println(e);
    }
}


   // Custom cell renderer for JButton class
 
    /**
     * Creates new form viewFacilitators
     */
    public viewEquipmentsList() {
        initComponents();
            displayTable(); 
            scaleImage();
            setLocationRelativeTo(null);
              searchTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filterTable();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filterTable();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filterTable();
            }
        });
    }
        class jPanelGradient extends JPanel{
protected void paintComponent(Graphics g){
Graphics2D g2d = (Graphics2D) g;
int width= getWidth();
int height= getHeight();
Color color1=new Color(50,0,0);
Color color2=new Color(154,0,12);
GradientPaint gp= new GradientPaint(0,0,color1,180,height,color2);
g2d.setPaint(gp);
g2d.fillRect(0,0,width,height);

}
}
 private void filterTable() {
        String searchText = searchTextField.getText();
        RowFilter<DefaultTableModel, Object> rowFilter = RowFilter.regexFilter("(?i)" + searchText); // Case-insensitive search
        sorter.setRowFilter(rowFilter);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        contentPane = new jPanelGradient();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        searchTextField = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        PDFButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        inventoryLogo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("AMT Laboratory System");

        contentPane.setBackground(new java.awt.Color(102, 0, 0));

        jButton1.setBackground(new java.awt.Color(0, 0, 0));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("BACK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(table);

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Search:");

        searchTextField.setBackground(new java.awt.Color(255, 255, 255));
        searchTextField.setForeground(new java.awt.Color(0, 0, 0));
        searchTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchTextFieldActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(0, 0, 153));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("REFRESH");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        PDFButton.setBackground(new java.awt.Color(0, 0, 153));
        PDFButton.setForeground(new java.awt.Color(255, 255, 255));
        PDFButton.setText("Save as PDF");
        PDFButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PDFButtonActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(0, 204, 204));

        jLabel1.setFont(new java.awt.Font("Lucida Console", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Laboratory Inventory");

        inventoryLogo.setText("jLabel3");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(inventoryLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(inventoryLogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout contentPaneLayout = new javax.swing.GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(contentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addGap(26, 26, 26)
                        .addComponent(PDFButton)
                        .addGap(381, 381, 381))
                    .addGroup(contentPaneLayout.createSequentialGroup()
                        .addGroup(contentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1))
                        .addContainerGap())))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentPaneLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(contentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2)
                    .addComponent(PDFButton)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(contentPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(contentPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        facilitatorSuccess facilitate=new facilitatorSuccess();
        facilitate.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void searchTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchTextFieldActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        viewEquipmentsList view= new viewEquipmentsList();
        dispose();
        view.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void PDFButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PDFButtonActionPerformed
 // TODO add your handling code here:
 // Show a dialog box asking the user for printing options
String[] options = {"Convert All Items", "Convert Specific Item"};
int selectedOption = JOptionPane.showOptionDialog(null, "Select Printing Option", "Print Options",
    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

if (selectedOption == 0) {
     
Document document = new Document(PageSize.LEGAL.rotate());

try {
    // Generate a unique filename with a timestamp
    String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    String filename = "Inventorytable_" + timestamp + ".pdf";
  
    PdfWriter.getInstance(document, new FileOutputStream(filename));
    document.open();

 

// Add additional information
    Font mainHeadingFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
            Paragraph mainHeading = new Paragraph("Aircraft Maintenance Technology Inventory", mainHeadingFont);
            mainHeading.setAlignment(Element.ALIGN_CENTER);
            document.add(mainHeading);
          

            // Add subheadings
            Font subheadingFont = new Font(Font.FontFamily.HELVETICA, 12);
            Paragraph infoParagraph1 = new Paragraph("UNIVERSITY OF BOHOL", subheadingFont);
            infoParagraph1.setAlignment(Element.ALIGN_CENTER);
            document.add(infoParagraph1);

            Paragraph infoParagraph2 = new Paragraph("Tagbilaran City, Bohol, Philippines", subheadingFont);
            infoParagraph2.setAlignment(Element.ALIGN_CENTER);
            document.add(infoParagraph2);

            Paragraph infoParagraph3 = new Paragraph("www.universityofbohol.edu.ph (038) 411-3484, Fax No. (038) 411-3101", subheadingFont);
            infoParagraph3.setAlignment(Element.ALIGN_CENTER);
            document.add(infoParagraph3);

            Paragraph infoParagraph4 = new Paragraph("COLLEGE OF ENGINEERING & TECHNOLOGY", subheadingFont);
            infoParagraph4.setAlignment(Element.ALIGN_CENTER);
            document.add(infoParagraph4);

            document.add(Chunk.NEWLINE);

            // Add laboratory status and current date
            Font statusFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
            String currentDate = new SimpleDateFormat("MMMM dd, yyyy").format(new Date());
            Paragraph statusParagraph = new Paragraph("Laboratory Inventory Status as of: " + currentDate, statusFont);
            statusParagraph.setAlignment(Element.ALIGN_CENTER);
            document.add(statusParagraph);
            document.add(Chunk.NEWLINE);
    PdfPTable pdfTable = new PdfPTable(table.getColumnCount());

    // Add table headers
    Font headerFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
    for (int i = 0; i < table.getColumnCount(); i++) {
        PdfPCell cell = new PdfPCell(new Paragraph(table.getColumnName(i), headerFont));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        pdfTable.addCell(cell);
    }

    // Add table rows
    for (int i = 0; i < table.getRowCount(); i++) {
        for (int j = 0; j < table.getColumnCount(); j++) {
            PdfPCell cell = new PdfPCell(new Paragraph(table.getValueAt(i, j).toString()));
            pdfTable.addCell(cell);
        }
    }

    document.add(pdfTable);
    document.close();

    JOptionPane.showMessageDialog(this, "Table converted to PDF successfully. Filename: " + filename, "Conversion Status", JOptionPane.INFORMATION_MESSAGE);
} catch (DocumentException | FileNotFoundException e) {
    e.printStackTrace();
} catch (IOException ex) {
               Logger.getLogger(viewEquipmentsList.class.getName()).log(Level.SEVERE, null, ex);
           }
} else if (selectedOption == 1) {
   try {
    // Generate a unique file name based on the current date and time
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
    String fileName = "Inventorytable_" + dateFormat.format(new Date()) + ".pdf";

    // Create a new document
    Document document = new Document(PageSize.LEGAL.rotate());

    PdfPTable pdfTable = new PdfPTable(table.getColumnCount());

    // Add table headers
    Font headerFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
    for (int i = 0; i < table.getColumnCount(); i++) {
        PdfPCell cell = new PdfPCell(new Paragraph(table.getColumnName(i), headerFont));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        pdfTable.addCell(cell);
    }

    // Prompt the user for the specific equipment or cancel the conversion
    String specificEquipment = JOptionPane.showInputDialog(null, "Enter the specific equipment:", "Specific Equipment", JOptionPane.PLAIN_MESSAGE);

    if (specificEquipment != null) {
        boolean foundEquipment = false; // Track if the specific equipment is found

        // Add table rows for the specific equipment
        for (int i = 0; i < table.getRowCount(); i++) {
            String equipmentName = table.getValueAt(i, 2).toString(); // Assuming equipment names are in the first column
            if (equipmentName.equals(specificEquipment)) {
                foundEquipment = true;
                for (int j = 0; j < table.getColumnCount(); j++) {
                    PdfPCell cell = new PdfPCell(new Paragraph(table.getValueAt(i, j).toString()));
                    pdfTable.addCell(cell);
                }
            }
        }

        if (foundEquipment) {
            PdfWriter.getInstance(document, new FileOutputStream(fileName));
            document.open();
            // Add heading
            Font headingFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
            Paragraph heading = new Paragraph("Aircraft Maintenance Technology Inventory", headingFont);
            heading.setAlignment(Element.ALIGN_CENTER);
            document.add(heading);
          
            // Add subheadings
            Font subheadingFont = new Font(Font.FontFamily.HELVETICA, 12);
            Paragraph infoParagraph1 = new Paragraph("UNIVERSITY OF BOHOL", subheadingFont);
            infoParagraph1.setAlignment(Element.ALIGN_CENTER);
            document.add(infoParagraph1);

            Paragraph infoParagraph2 = new Paragraph("Tagbilaran City, Bohol, Philippines", subheadingFont);
            infoParagraph2.setAlignment(Element.ALIGN_CENTER);
            document.add(infoParagraph2);

            Paragraph infoParagraph3 = new Paragraph("www.universityofbohol.edu.ph (038) 411-3484, Fax No. (038) 411-3101", subheadingFont);
            infoParagraph3.setAlignment(Element.ALIGN_CENTER);
            document.add(infoParagraph3);

            Paragraph infoParagraph4 = new Paragraph("COLLEGE OF ENGINEERING & TECHNOLOGY", subheadingFont);
            infoParagraph4.setAlignment(Element.ALIGN_CENTER);
            document.add(infoParagraph4);

            document.add(Chunk.NEWLINE);

            // Add laboratory status and current date
            Font statusFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
            String currentDate = new SimpleDateFormat("MMMM dd, yyyy").format(new Date());
            Paragraph statusParagraph = new Paragraph("Laboratory Inventory Status as of: " + currentDate, statusFont );
            statusParagraph.setAlignment(Element.ALIGN_CENTER);
            document.add(statusParagraph);
            document.add(Chunk.NEWLINE);
         Paragraph statusParagraph1 = new Paragraph("Status of the Specific Equipment: " + specificEquipment, statusFont);
statusParagraph1.setAlignment(Element.ALIGN_LEFT);
statusParagraph1.setIndentationLeft(72); // 1 inch = 72 user units in iText
document.add(statusParagraph1);
document.add(Chunk.NEWLINE);
            document.add(pdfTable);
            document.close();

            JOptionPane.showMessageDialog(null, "Table converted to PDF successfully.", "Conversion Status", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Specific equipment not found. PDF conversion canceled.", "Conversion Status", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(null, "Conversion canceled.", "Conversion Status", JOptionPane.INFORMATION_MESSAGE);
    }
} catch (DocumentException | FileNotFoundException e) {
    e.printStackTrace();
}
} 
 


    }//GEN-LAST:event_PDFButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(viewEquipmentsList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(viewEquipmentsList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(viewEquipmentsList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(viewEquipmentsList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new viewEquipmentsList().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton PDFButton;
    private javax.swing.JPanel contentPane;
    private javax.swing.JLabel inventoryLogo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField searchTextField;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
