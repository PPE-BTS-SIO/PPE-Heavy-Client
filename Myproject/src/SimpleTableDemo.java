/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Joel
 */
package components;
 
/*
 * SimpleTableDemo.java requires no other files.
 */
 
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import static myproject.Myproject.getString;
 
public class SimpleTableDemo extends JPanel {
    private boolean DEBUG = false;
 
    public SimpleTableDemo() throws SQLException {
        
        super(new GridLayout(1,0));
            DefaultTableModel model = new DefaultTableModel(new String[]{"Numero_Intervention", "Date_Intervention","Commentaire","Localisation",
            "Matricule","NumeroClient"}, 0);
            String url = "jdbc:mysql://localhost:3306/PPE";
            String username = "root";
            String password = "";
       
       //Ouverture de connexion avec MySQL tournant sur la meme machine
         Connection conn = DriverManager.getConnection(url, username, password);
         Statement state = conn.createStatement();
         
         ResultSet result = state.executeQuery("SELECT * FROM intervention");
         /*ResultSetMetaData resultMeta = result.getMetaData();*/
         
         while(result.next()){
            String d = getString(result,"Numero_Intervention");
            String e = getString(result, "Date_Intervention");
            String f = getString(result, "Commentaire");
            String g = getString(result, "Localisation");
            String h = getString(result, "Matricule");
            String i = getString(result, "NumeroClient");
            
            
            model.addRow(new Object[]{d, e, f, g, h ,i});
        }
        String[] columnNames = {};
 
        Object[][] data = {/*
        {"Kathy", "Smith",
         "Snowboarding", new Integer(5), new Boolean(false)},
        {"John", "Doe",
         "Rowing", new Integer(3), new Boolean(true)},
        {"Sue", "Black",
         "Knitting", new Integer(2), new Boolean(false)},
        {"Jane", "White",
         "Speed reading", new Integer(20), new Boolean(true)},
        {"Joe", "Brown",
         "Pool", new Integer(10), new Boolean(false)}*/
        };
 
        final JTable table = new JTable(data, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(500, 200));
        table.setFillsViewportHeight(true);
        table.setModel(model);
        if (DEBUG) {
            table.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    printDebugData(table);
                }
            });
            
        }
        
 
        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);
 
        //Add the scroll pane to this panel.
        add(scrollPane);
    }
    public static String getString(ResultSet result, String column) {
        String str = new String();
        try {
        InputStreamReader in = new InputStreamReader(result
        .getAsciiStream(column));
        while (in.ready())
        str = str + (char) in.read();
        } catch (SQLException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        } finally {
        return str;
        }
}
 
    private void printDebugData(JTable table) {
        int numRows = table.getRowCount();
        int numCols = table.getColumnCount();
        javax.swing.table.TableModel model = table.getModel();
 
        System.out.println("Value of data: ");
        for (int i=0; i < numRows; i++) {
            System.out.print("    row " + i + ":");
            for (int j=0; j < numCols; j++) {
                System.out.print("  " + model.getValueAt(i, j));
            }
            System.out.println();
        }
        System.out.println("--------------------------");
    }
 
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() throws SQLException {
        //Create and set up the window.
        JFrame frame = new JFrame("SimpleTableDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Ask the windows to be in the center 
        frame.setLocationRelativeTo(null);
        //Create and set up the content pane.
        SimpleTableDemo newContentPane = new SimpleTableDemo();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
 
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    createAndShowGUI();
                } catch (SQLException ex) {
                    Logger.getLogger(SimpleTableDemo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}
