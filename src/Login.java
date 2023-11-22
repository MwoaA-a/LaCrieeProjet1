import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;


public class Login extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel lblNewLabel;
    private JLabel lblDateDuJour;

    // Établir une connexion à la base de données
    Connection conn = MySQLConnection.getConnection();

    // Listes pour stocker les données des combobox
    private List<LotItem> lotItems;
    private List<BacItem> bacItems;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Login frame = new Login();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Login() {
        setTitle("Login Frame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 250);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        lblNewLabel = new JLabel("Modification du poids d'un bac :");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNewLabel.setBounds(10, 14, 317, 13);
        contentPane.add(lblNewLabel);

        JButton btnNewButton_1 = new JButton("Valider");
        btnNewButton_1.setBounds(189, 182, 79, 21);
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO: Ajouter la logique de validation ici
            }
        });
        contentPane.add(btnNewButton_1);

        lblDateDuJour = new JLabel("Date du jour : xx-xx-xxxx");
        lblDateDuJour.setBounds(337, 10, 139, 13);
        contentPane.add(lblDateDuJour);

        JLabel lblLotDuJour = new JLabel("Lot du jour* :");
        lblLotDuJour.setBounds(97, 104, 82, 13);
        contentPane.add(lblLotDuJour);

        JLabel lblBac = new JLabel("Bac* :");
        lblBac.setBounds(126, 143, 65, 13);
        contentPane.add(lblBac);

        JComboBox<LotItem> Lot = new JComboBox<>();
        Lot.setBounds(189, 100, 96, 21);
        contentPane.add(Lot);

        JComboBox<BacItem> Bac = new JComboBox<>();
        Bac.setBounds(189, 139, 96, 21);
        contentPane.add(Bac);

        // Charger les données dans les combobox
        chargerDonneesCombobox();

        // Configurer l'événement de changement de sélection pour Lot
        Lot.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                JComboBox<?> source = (JComboBox<?>) event.getSource();
                LotItem selectedLot = (LotItem) source.getSelectedItem();

                // Mettre à jour les données de Bac en fonction du Lot sélectionné
                chargerBacsPourLot(selectedLot.getIdLot());
            }
        });
    }
    

    // Méthode pour charger les données dans les combobox
    private void chargerDonneesCombobox() {
        // Charger les données pour Lot
        lotItems = chargerLots();
        DefaultComboBoxModel<LotItem> lotModel = new DefaultComboBoxModel<>(lotItems.toArray(new LotItem[0]));
        JComboBox<LotItem> Lot = (JComboBox<LotItem>) contentPane.getComponentAt(189, 100);
        Lot.setModel(lotModel);

        // Charger les données initiales pour Bac (basé sur le premier Lot)
        if (!lotItems.isEmpty()) {
            chargerBacsPourLot(lotItems.get(0).getIdLot());
        }
    }

    // Méthode pour charger les lots depuis la base de données
    private List<LotItem> chargerLots() {
        List<LotItem> lots = new ArrayList<>();

        try {
            String query = "SELECT id, datePeche FROM lot";
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int idLot = resultSet.getInt("id");
                String datePeche = resultSet.getString("datePeche");
                LotItem lotItem = new LotItem(idLot, datePeche);
                lots.add(lotItem);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer les erreurs SQL ici
        }

        return lots;
    }

    // Méthode pour charger les bacs depuis la base de données en fonction du Lot sélectionné
    private void chargerBacsPourLot(int idLot) {
        bacItems = chargerBacs(idLot);
        DefaultComboBoxModel<BacItem> bacModel = new DefaultComboBoxModel<>(bacItems.toArray(new BacItem[0]));
        JComboBox<BacItem> Bac = (JComboBox<BacItem>) contentPane.getComponentAt(189, 139);
        Bac.setModel(bacModel);
    }

    // Méthode pour charger les bacs depuis la base de données
    private List<BacItem> chargerBacs(int idLot) {
        List<BacItem> bacs = new ArrayList<>();

        try {
            String query = "SELECT id, idBac FROM bac WHERE IdLot = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, idLot);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int idBac = resultSet.getInt("id");
                String idBacValue = resultSet.getString("idBac");
                BacItem bacItem = new BacItem(idBac, idBacValue);
                bacs.add(bacItem);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer les erreurs SQL ici
        }

        return bacs;
    }

    // Classe pour représenter un élément Lot
    private static class LotItem {
        private final int idLot;
        private final String datePeche;

        public LotItem(int idLot, String datePeche) {
            this.idLot = idLot;
            this.datePeche = datePeche;
        }

        public int getIdLot() {
            return idLot;
        }

        @Override
        public String toString() {
            return datePeche;
        }
    }

    // Classe pour représenter un élément Bac
    private static class BacItem {
        private final int idBac;
        private final String idBacValue;

        public BacItem(int idBac, String idBacValue) {
            this.idBac = idBac;
            this.idBacValue = idBacValue;
        }

        public int getIdBac() {
            return idBac;
        }

        @Override
        public String toString() {
            return idBacValue;
        }
    }
}
