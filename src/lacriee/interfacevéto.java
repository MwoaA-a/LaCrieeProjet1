package lacriee;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;

public class interfacevéto extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_1;
	private JTextField textField_3;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					interfacevéto frame = new interfacevéto();
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
	public interfacevéto() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 530, 352);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("type poisson :");
		lblNewLabel.setBounds(55, 66, 96, 14);
		contentPane.add(lblNewLabel);
		
		JComboBox comboBox = new JComboBox();
		JComboBox<String> comboBox1 = new JComboBox<String>();
        comboBox1.addItem("TURBO");
        comboBox1.addItem("BARBU");
        comboBox1.addItem("PLIE");
        comboBox1.addItem("MERLU");
        comboBox1.addItem("CABIL");
        comboBox1.addItem("LJAUL");
        comboBox1.addItem("LNOI");
        comboBox1.addItem("LINGU");
        comboBox1.addItem("CONGR");
        comboBox1.addItem("STPIE");
        comboBox1.addItem("BARCH");
        comboBox1.addItem("BARLI");
        comboBox1.addItem("CERNI");
        comboBox1.addItem("MEROU");
        comboBox1.addItem("ROUGT");
        comboBox1.addItem("DORAC");
        comboBox1.addItem("DORAL");
        comboBox1.addItem("DORAD");
        comboBox1.addItem("PAGEO");
        comboBox1.addItem("PAGEC");
        comboBox1.addItem("VIEIL");
        comboBox1.addItem("GRONG");
        comboBox1.addItem("GRONR");
        comboBox1.addItem("BAUDR");
        comboBox1.addItem("BAUDM");
		comboBox1.setBounds(55, 90, 96, 22);
		contentPane.add(comboBox1);

		textField_1 = new JTextField();
		textField_1.setBounds(55, 189, 96, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
				
		JLabel lblNewLabel_2 = new JLabel("date pêche :");
		lblNewLabel_2.setBounds(55, 164, 96, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Immatriculation :");
		lblNewLabel_3.setBounds(366, 66, 96, 14);
		contentPane.add(lblNewLabel_3);
		
		JComboBox comboBox_1 = new JComboBox();
		JComboBox<String> comboBox2 = new JComboBox<String>();
		comboBox2.addItem("Ad 895511");
		comboBox2.addItem("Ad 584873");
		comboBox2.addItem("Ad 584930");
		comboBox2.addItem("Ad 627846");
		comboBox2.addItem("Ad 730414");
		comboBox2.addItem("Ad 815532");
		comboBox2.addItem("Ad 584826");
		comboBox2.addItem("Ad 614221");
		comboBox2.addItem("Ad 584846");
		comboBox2.addItem("Ad 584871");
		comboBox2.addItem("Ad 895472");
		comboBox2.addItem("Ad 895479");
		comboBox2.addItem("Ad 895519");
		comboBox2.addItem("Ad 584865");
		comboBox2.addItem("Ad 741312");
		comboBox2.addItem("Ad 584830");
		comboBox2.addItem("Ad 715792");
		comboBox2.addItem("Ad 584772");
		comboBox2.addItem("Ad 895516");
		comboBox2.setBounds(366, 90, 96, 22);
		contentPane.add(comboBox2);
		
		textField_3 = new JTextField();
		textField_3.setBounds(366, 189, 96, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("poids poissons :");
		lblNewLabel_4.setBounds(366, 164, 96, 14);
		contentPane.add(lblNewLabel_4);
		
		JButton btnNewButton = new JButton("validation");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 Date currentDate = new Date();
	             java.sql.Date sqlDate = new java.sql.Date(currentDate.getTime());

	             DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	             String formattedDate = dateFormat.format(currentDate);

	             textField_1.setText(formattedDate);

	             insererDansBaseDeDonnees(sqlDate, (String) comboBox1.getSelectedItem(), (String) comboBox2.getSelectedItem(), textField_3.getText());
			}
		});
		btnNewButton.setBounds(206, 248, 104, 23);
		contentPane.add(btnNewButton);
	}
	private void insererDansBaseDeDonnees(java.sql.Date datePeche, String typePoisson, String immatriculation, String poidsPoisson) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql:///lacriee", "root", "");) {
            String query = "INSERT INTO bac (Type_poisson, Date_pêche, Immatriculation, Poids_poisson) VALUES (?, ?, ?, ?)";
            try (PreparedStatement pstmt = connection.prepareStatement(query)) {
                pstmt.setString(1, typePoisson);
                pstmt.setDate(2, datePeche);
                pstmt.setString(3, immatriculation);
                pstmt.setString(4, poidsPoisson);
                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Donnée insérée avec succès dans la base de données !");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erreur lors de l'insertion dans la base de données : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
	}
}
