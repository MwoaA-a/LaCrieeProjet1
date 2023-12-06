import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Color;

public class Maquette extends JFrame {

	private static final long serialVersionUID = 1L;
	static Connection con;
	private JPanel contentPane;
	private JComboBox<String> CB_Bateau;
	private JComboBox<String> CB_Qual;
	private JComboBox<String> CB_taille;
	private JComboBox<String> CB_Espe;
	private JComboBox<String> CB_Prése;
	private JButton BTN_retour;
	private JButton btn_send;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Maquette frame = new Maquette();
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
	public Maquette() {
		
		con = connexion.connexion();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 544, 392);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Création d'un nouveau lot");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel.setBounds(20, 11, 309, 39);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Bateau de pêche :");
		lblNewLabel_1.setBounds(104, 111, 101, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Qualité  :");
		lblNewLabel_1_1.setBounds(149, 136, 56, 14);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Taille :");
		lblNewLabel_1_1_1.setBounds(159, 161, 46, 14);
		contentPane.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Espèces :");
		lblNewLabel_1_1_2.setBounds(149, 186, 56, 14);
		contentPane.add(lblNewLabel_1_1_2);
		
		JLabel lblNewLabel_1_1_2_1 = new JLabel("Présentation :");
		lblNewLabel_1_1_2_1.setBounds(124, 211, 81, 14);
		contentPane.add(lblNewLabel_1_1_2_1);
		
		CB_Bateau = new JComboBox();
		CB_Bateau.setBounds(215, 107, 190, 22);
		contentPane.add(CB_Bateau);
		CB_Bateau.addItem("...");
		
		CB_Qual = new JComboBox();
		CB_Qual.setBounds(215, 132, 190, 22);
		contentPane.add(CB_Qual);
		CB_Qual.addItem("...");
		
		CB_taille = new JComboBox();
		CB_taille.setBounds(215, 157, 190, 22);
		contentPane.add(CB_taille);
		CB_taille.addItem("...");
		
		CB_Espe = new JComboBox();
		CB_Espe.setBounds(215, 182, 190, 22);
		contentPane.add(CB_Espe);
		CB_Espe.addItem("...");
		
		CB_Prése = new JComboBox();
		CB_Prése.setBounds(215, 207, 190, 22);
		contentPane.add(CB_Prése);
		CB_Prése.addItem("...");
		
		BTN_retour = new JButton("Retour");
		BTN_retour.setBounds(57, 275, 89, 23);
		contentPane.add(BTN_retour);
		
		
		btn_send = new JButton("Envoyer le lot");
		btn_send.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btn_send.setForeground(Color.BLACK);
		btn_send.setBounds(363, 275, 101, 23);
		btn_send.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				   
				send();
			}
		});
		contentPane.add(btn_send);
		
		Update();
	}
	
	void Update() {

		try{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT nom FROM `bateau`;");
			while (rs.next()){
				CB_Bateau.addItem(rs.getString("nom"));
			}
			}catch (SQLException ex){
			JOptionPane.showMessageDialog(null, "Une erreur lors de l'up de la liste.", "Erreur", JOptionPane.INFORMATION_MESSAGE);
		}

		try{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT libelle FROM `qualite`;");
			while (rs.next()){
				CB_Qual.addItem(rs.getString("libelle"));
			}
			}catch (SQLException ex){
			JOptionPane.showMessageDialog(null, "Une erreur lors de l'up de la liste.", "Erreur", JOptionPane.INFORMATION_MESSAGE);
		}

		try{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT specification FROM `taille`;");
			while (rs.next()){
				CB_taille.addItem(rs.getString("specification"));
			}
			}catch (SQLException ex){
			JOptionPane.showMessageDialog(null, "Une erreur lors de l'up de la liste.", "Erreur", JOptionPane.INFORMATION_MESSAGE);
		}

		try{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT nom FROM `espece`;");
			while (rs.next()){
				CB_Espe.addItem(rs.getString("nom"));
			}
			}catch (SQLException ex){
			JOptionPane.showMessageDialog(null, "Une erreur lors de l'up de la liste.", "Erreur", JOptionPane.INFORMATION_MESSAGE);
		}

		try{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT libelle FROM `presentation`;");
			while (rs.next()){
				CB_Prése.addItem(rs.getString("libelle"));
			}
			}catch (SQLException ex){
			JOptionPane.showMessageDialog(null, "Une erreur lors de l'up de la liste.", "Erreur", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	void send() {
		
		if((CB_Prése.getSelectedItem()=="...")||(CB_Bateau.getSelectedItem()=="...")||(CB_Qual.getSelectedItem()=="...")||(CB_taille.getSelectedItem()=="...")||(CB_Espe.getSelectedItem()=="...")) {
			JOptionPane.showMessageDialog(null, "Veuillez séléctionnez une valeurs.", "Erreur", JOptionPane.INFORMATION_MESSAGE);
		}else {
			try {
				   String date = java.time.LocalDate.now()+"";
				   PreparedStatement st1 = con.prepareStatement("SELECT id INTO @n FROM presentation WHERE libelle = ?;");
				   PreparedStatement st2 = con.prepareStatement("SELECT id INTO @m FROM qualite WHERE libelle = ?;");
				   PreparedStatement st3 = con.prepareStatement("SELECT id INTO @p FROM taille WHERE specification = ?;");
				   PreparedStatement st4 = con.prepareStatement("SELECT id INTO @n FROM espece WHERE nom = ?;");
				   PreparedStatement st5 = con.prepareStatement("SELECT id INTO @d FROM bateau WHERE nom = ?;");
				   PreparedStatement st6 = con.prepareStatement("INSERT INTO `lot` (`datePeche`, `idBateau`, `idEspece`, `idTaille`, `idPresentation`, `idQualite`) VALUES (?, @d, @n, @p, @n, @m);");

				   st1.setString(1, CB_Prése.getSelectedItem()+"");
				   st2.setString(1, CB_Qual.getSelectedItem()+"");
				   st3.setString(1, CB_taille.getSelectedItem()+"");
				   st4.setString(1, CB_Espe.getSelectedItem()+"");
				   st5.setString(1, CB_Bateau.getSelectedItem()+"");

				   ResultSet rs1 = st1.executeQuery();
				   ResultSet rs2 = st2.executeQuery();
				   ResultSet rs3 = st3.executeQuery();
				   ResultSet rs4 = st4.executeQuery();
				   ResultSet rs5 = st5.executeQuery();

				   st6.setString(1, date);
				   int rs6 = st6.executeUpdate();
				   CB_Prése.setSelectedIndex(0);
				   CB_Qual.setSelectedIndex(0);
				   CB_taille.setSelectedIndex(0);
				   CB_Espe.setSelectedIndex(0);
				   CB_Bateau.setSelectedIndex(0);
				   if (rs6>0) {
				       JOptionPane.showMessageDialog(null, "L'Ajout à était fait.", "Ajout", JOptionPane.INFORMATION_MESSAGE);
				   } else {
				       JOptionPane.showMessageDialog(null, "Une erreur s'est produite eX01.", "Erreur", JOptionPane.INFORMATION_MESSAGE);
				   }
				} catch(SQLException ex) {
				   JOptionPane.showMessageDialog(null, "Une erreur lors de l'envoie du lot. Error: " +"\n"+ ex.getMessage() + ". SQL State: " + ex.getSQLState() + ". Error Code: " + ex.getErrorCode(), "Erreur", JOptionPane.INFORMATION_MESSAGE);
				}
		}
	}
}
