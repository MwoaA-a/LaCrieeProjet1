package ModifierPoids;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ModificationPoidsBacFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JComboBox<String> comboBoxLots;
    private JTextField textFieldNouveauPoids;
    private JButton btnModifier;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ModificationPoidsBacFrame frame = new ModificationPoidsBacFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ModificationPoidsBacFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblLot = new JLabel("Lot :");
        lblLot.setBounds(30, 30, 80, 25);
        contentPane.add(lblLot);

        comboBoxLots = new JComboBox<>();
        comboBoxLots.setBounds(120, 30, 200, 25);
        contentPane.add(comboBoxLots);
        remplirComboBoxLots();

        JLabel lblNouveauPoids = new JLabel("Nouveau Poids :");
        lblNouveauPoids.setBounds(30, 80, 120, 25);
        contentPane.add(lblNouveauPoids);

        textFieldNouveauPoids = new JTextField();
        textFieldNouveauPoids.setBounds(160, 80, 160, 25);
        contentPane.add(textFieldNouveauPoids);

        btnModifier = new JButton("Modifier Poids");
        btnModifier.setBounds(150, 150, 150, 30);
        contentPane.add(btnModifier);

        btnModifier.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                modifierPoidsBac();
            }
        });
    }

    private void remplirComboBoxLots() {
        // Code pour récupérer les lots depuis la base de données
        try {
            // Charger le pilote JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Établir la connexion à la base de données
            String url = "jdbc:mysql://127.0.0.1/bddcriee";
            String user = "root";
            String password = "";
            Connection connection = DriverManager.getConnection(url, user, password);

            // Requête SQL pour récupérer les lots
            String query = "SELECT idLot FROM lot";
            PreparedStatement statement = connection.prepareStatement(query);

            // Exécuter la requête
            ResultSet resultSet = statement.executeQuery();

            // Ajouter les lots à la JComboBox
            while (resultSet.next()) {
                comboBoxLots.addItem(resultSet.getString("idLot"));
            }

            // Fermer les ressources
            resultSet.close();
            statement.close();
            connection.close();

        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            // Gérer les erreurs de récupération des lots (afficher un message d'erreur, journaliser, etc.)
        }
    }

    private void modifierPoidsBac() {
        String idLotSelectionne = (String) comboBoxLots.getSelectedItem();
        String nouveauPoids = textFieldNouveauPoids.getText();

        try {
            // Charger le pilote JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Établir la connexion à la base de données
            String url = "jdbc:mysql://127.0.0.1/bddcriee";
            String user = "root";
            String password = "";
            Connection connection = DriverManager.getConnection(url, user, password);

            // Requête SQL pour mettre à jour le poids du bac
            String query = "UPDATE lot SET poidsBrutLot = ? WHERE idLot = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, nouveauPoids);
            statement.setString(2, idLotSelectionne);

            // Exécuter la mise à jour
            statement.executeUpdate();

            // Fermer les ressources
            statement.close();
            connection.close();

            System.out.println("Poids du bac mis à jour avec succès.");

        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            // Gérer les erreurs de mise à jour (afficher un message d'erreur, journaliser, etc.)
        }
    }
}
