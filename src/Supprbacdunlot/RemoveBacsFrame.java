package Supprbacdunlot;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class RemoveBacsFrame extends JFrame {

    private JComboBox<String> lotComboBox;
    private JList<String> bacList;
    private DefaultListModel<String> bacListModel;

    public RemoveBacsFrame() {
        setTitle("Supprimer des Bacs d'un Lot");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        JPanel panel = new JPanel();
        getContentPane().add(panel, "Center");
        panel.setLayout(null);

        lotComboBox = new JComboBox<>();
        lotComboBox.setBounds(20, 20, 150, 25);
        panel.add(lotComboBox);

        bacListModel = new DefaultListModel<>();
        bacList = new JList<>(bacListModel);
        bacList.setBounds(20, 60, 150, 150);
        panel.add(bacList);

        JButton removeButton = new JButton("Supprimer Bacs");
        removeButton.setBounds(200, 60, 150, 25);
        panel.add(removeButton);

        // Remplir la liste des lots au lancement
        fillLotComboBox();

        // Ajouter un écouteur d'événements pour le bouton Supprimer Bacs
        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeSelectedBacs();
            }
        });
    }

    // Remplir la liste déroulante avec les lots disponibles
    private void fillLotComboBox() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://127.0.0.1/bddcriee";
            String user = "root";
            String password = "";
            Connection connection = DriverManager.getConnection(url, user, password);

            String query = "SELECT DISTINCT id FROM lot";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                lotComboBox.addItem(resultSet.getString("id"));
            }

            resultSet.close();
            statement.close();
            connection.close();

            // Charger les bacs associés au premier lot (vous pouvez changer cela en fonction de vos besoins)
            loadBacsForLot((String) lotComboBox.getSelectedItem());

        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Charger les bacs associés à un lot spécifique
    private void loadBacsForLot(String lotId) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://127.0.0.1/bddcriee";
            String user = "root";
            String password = "";
            Connection connection = DriverManager.getConnection(url, user, password);

            String query = "SELECT idBac FROM bac WHERE IdLot = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, lotId);
            ResultSet resultSet = statement.executeQuery();

            bacListModel.clear(); // Effacer les éléments existants

            while (resultSet.next()) {
                bacListModel.addElement(resultSet.getString("idBac"));
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Supprimer les bacs sélectionnés
    private void removeSelectedBacs() {
        String selectedLot = (String) lotComboBox.getSelectedItem();
        int[] selectedIndices = bacList.getSelectedIndices();

        if (selectedIndices.length == 0) {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner au moins un bac à supprimer.", "Avertissement", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://127.0.0.1/bddcriee";
            String user = "root";
            String password = "";
            Connection connection = DriverManager.getConnection(url, user, password);

            String query = "DELETE FROM bac WHERE IdLot = ? AND idBac = ?";
            PreparedStatement statement = connection.prepareStatement(query);

            for (int selectedIndex : selectedIndices) {
                String selectedBac = bacListModel.getElementAt(selectedIndex);
                statement.setString(1, selectedLot);
                statement.setString(2, selectedBac);
                statement.addBatch(); // Ajouter la requête à la batch pour un traitement en lot
            }

            // Exécuter la batch
            statement.executeBatch();

            statement.close();
            connection.close();

            // Rafraîchir la liste des bacs après la suppression
            loadBacsForLot(selectedLot);

        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    RemoveBacsFrame frame = new RemoveBacsFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}