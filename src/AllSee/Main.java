package AllSee;

import java.awt.EventQueue;
import java.awt.GridLayout;
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
import javax.swing.border.EmptyBorder;

public class Main extends JFrame {

    private ResultSet resultSet;
    private JComboBox<String> comboBox;
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JButton btnNewButton;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Main frame = new Main();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Main() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel lblNewLabel = new JLabel("Id de la pêche :");
        contentPane.add(lblNewLabel);

        comboBox = new JComboBox<>();
        contentPane.add(comboBox);

        btnNewButton = new JButton("Valider");
        contentPane.add(btnNewButton);

        AfficherLotetBac();

        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                afficherDonneesLotetBac();
            }
        });
    }

    public ResultSet AfficherLotetBac() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://127.0.0.1/bddcriee";
            String user = "root";
            String password = "";
            Connection connection = DriverManager.getConnection(url, user, password);

            String query = "SELECT DISTINCT idDatepeche FROM peche";
            PreparedStatement statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                comboBox.addItem(resultSet.getString("idDatepeche"));
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public void afficherDonneesLotetBac() {
        String datePecheSelectionnee = (String) comboBox.getSelectedItem();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://127.0.0.1/bddcriee";
            String user = "root";
            String password = "";
            Connection connection = DriverManager.getConnection(url, user, password);

            String query = "SELECT * FROM lot l JOIN bac b ON l.datePeche = b.datePeche AND l.idBateau = b.Idbateau WHERE l.datePeche = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, datePecheSelectionnee);

            resultSet = statement.executeQuery();

            // Créer une instance de ResultFrame avec le ResultSet
            ResultFrame resultFrame = new ResultFrame(resultSet);

            resultSet.close();
            statement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

}

class ResultFrame extends JFrame {

    public ResultFrame(ResultSet resultSet) throws SQLException {
        setTitle("Résultats de la pêche");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLayout(new GridLayout(0, 1));

        while (resultSet.next()) {
            JLabel label = new JLabel(" Date: " + resultSet.getDate("datePeche") +
                    " Lot : " + resultSet.getInt("idLot") +
                    ", IdBateau: " + resultSet.getInt("idBateau") +
                    ", IdEspece: " + resultSet.getInt("idEspece") +
                    ", IdBac: " + resultSet.getString("idBac") +
                    ", IdTypeBac: " + resultSet.getString("idTypeBac"));

            add(label);
        }

        setVisible(true);
    }
}
