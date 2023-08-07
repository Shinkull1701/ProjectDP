import java.awt.GridLayout;
import java.sql.Connection;
import data_access_object.*;
import presentation.Controller.*;
import presentation.View.*;
import presentation.View.Dat.ViewDat;
import presentation.View.Nha.ViewNha;

import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Connection connection = null;
        try {
            // Create a new connection to the database
            String url = "jdbc:mysql://localhost:3306/giaodich";
            String username = "root";
            String password = "123456";
            connection = DriverManager.getConnection(url, username, password);

            // Create a new instance of the GiaoDichDAO class
            GiaoDichDAO datdao = new GiaoDichDatDAO(connection);
            GiaoDichDAO nhadao = new GiaoDichNhaDAO(connection);

            // Create a new instance of the ViewDat class
            ViewDat viewDat = new ViewDat();
            DatController datController = new DatController(datdao, viewDat);
            ViewNha viewNha = new ViewNha();
            NhaController nhaController = new NhaController(nhadao, viewNha);

            // Create a JFrame to hold the menu
            JFrame frame = new JFrame("Menu");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new GridLayout(3, 1)); 
            frame.setSize(800, 600);

            // Create buttons for each function
            JButton datButton = new JButton("Giao dịch đất");
            datButton.addActionListener(e -> {
                viewDat.setVisible(true);
                datController.displayData();
            });

            JButton nhaButton = new JButton("Giao dịch nhà");
            nhaButton.addActionListener(e -> {
                viewNha.setVisible(true);
                nhaController.displayData();
            });

            JButton exitButton = new JButton("Thoát");
            exitButton.addActionListener(e -> {
                frame.dispose();
            });

            // Add buttons to the frame
            frame.add(datButton);
            frame.add(nhaButton);
            frame.add(exitButton);

            // Pack the frame to fit the buttons nicely
            
            // Center the frame on the screen
            frame.setLocationRelativeTo(null);

            // Display the frame
            frame.setVisible(true);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the connection to the database
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}