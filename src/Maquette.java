import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.Border;

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
import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Locale;

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
	private JLabel lbl_Titre;
	private JButton btn_retour;
	private JButton btn_send;
	public  Object[][] bat;
	public  Object[][] esp;
	public  Object[][] qual;
	public  Object[][] tail;
	public  Object[][] pres;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Maquette frame = new Maquette();
					frame.setLocationRelativeTo(null); // Permet d'avoir le frame au milieu de l'écran
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
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(50, 50, 544, 392);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lbl_Titre = new JLabel("Création d'un nouveau lot");
		lbl_Titre.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lbl_Titre.setBounds(20, 11, 498, 39);
		contentPane.add(lbl_Titre);
		
		JLabel lbl_bat = new JLabel("Bateau de pêche :");
		lbl_bat.setBounds(104, 111, 101, 14);
		contentPane.add(lbl_bat);
		
		JLabel lbl_qual = new JLabel("Qualité  :");
		lbl_qual.setBounds(149, 136, 56, 14);
		contentPane.add(lbl_qual);
		
		JLabel lbl_tail = new JLabel("Taille :");
		lbl_tail.setBounds(159, 161, 46, 14);
		contentPane.add(lbl_tail);
		
		JLabel lbl_esp = new JLabel("Espèces :");
		lbl_esp.setBounds(149, 186, 56, 14);
		contentPane.add(lbl_esp);
		
		JLabel lbl_pres = new JLabel("Présentation :");
		lbl_pres.setBounds(124, 211, 81, 14);
		contentPane.add(lbl_pres);
		
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
		
		btn_retour = new JButton("Retour");
		btn_retour.setBounds(82, 275, 123, 23);
		btn_retour.setForeground(Color.WHITE);
		btn_retour.setBackground(new Color(255, 0, 51));
		btn_retour.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {   
				dispose();
			}
		});
		contentPane.add(btn_retour);
		
		
		btn_send = new JButton("Envoyer le lot");
		btn_send.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btn_send.setForeground(Color.BLACK);
		btn_send.setBounds(314, 275, 132, 23);
		btn_send.setForeground(Color.WHITE);
		btn_send.setBackground(new Color(0, 51, 204));
		btn_send.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {   
				send();
			}
		});
		contentPane.add(btn_send);
		
		Update();
	}
	
	void Update() {
		
		// Titre de la page
		Locale locale = new Locale("fr", "FR");
		DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT, locale);
		java.util.Date currentDate = new java.util.Date();
		String date = dateFormat.format(currentDate);
		lbl_Titre.setText("Création d'un nouveau lot pour le " + date);

		
		// Création du tableau "bat" pour les bateaux...
		try{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT COUNT(*) as total FROM `bateau`;");
			while (rs.next()){
				int total = rs.getInt("total");
			    bat = new Object[total][2];
			}
		}catch (SQLException ex){
			JOptionPane.showMessageDialog(null, "Une erreur lors de l'up du tab bat.", "Erreur", JOptionPane.INFORMATION_MESSAGE);
		}
		
		// Création du tableau "esp" pour les espèces...
		try{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT COUNT(*) as total FROM `espece`;");
			while (rs.next()){
				int total = rs.getInt("total");
			    esp = new Object[total][2];
			}
		}catch (SQLException ex){
			JOptionPane.showMessageDialog(null, "Une erreur lors de l'up du tab esp.", "Erreur", JOptionPane.INFORMATION_MESSAGE);
		}
		
	    // Création du tableau "qual" pour les Qualitées...
		try{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT COUNT(*) as total FROM `qualite`;");
			while (rs.next()){
				int total = rs.getInt("total");
			    qual = new Object[total][2];
			}
		}catch (SQLException ex){
			JOptionPane.showMessageDialog(null, "Une erreur lors de l'up du tab qual.", "Erreur", JOptionPane.INFORMATION_MESSAGE);
		}
		
		// Création du tableau "tail" pour les tailles...
		try{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT COUNT(*) as total FROM `taille`;");
			while (rs.next()){
				int total = rs.getInt("total");
			    tail = new Object[total][2];
			}
		}catch (SQLException ex){
			JOptionPane.showMessageDialog(null, "Une erreur lors de l'up du tab tail.", "Erreur", JOptionPane.INFORMATION_MESSAGE);
		}
		
		// Création du tableau "pres" pour les presentations...
		try{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT COUNT(*) as total FROM `presentation`;");
			while (rs.next()){
				int total = rs.getInt("total");
			    pres = new Object[total][2];
			}
		}catch (SQLException ex){
			JOptionPane.showMessageDialog(null, "Une erreur lors de l'up du tab pres.", "Erreur", JOptionPane.INFORMATION_MESSAGE);
		}
				
		// Ajouts des "nom" et "id" pour les bateaux...
		try{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT nom, id FROM `bateau`;");
			while (rs.next()){
				bat[(rs.getRow()-1)][0] = rs.getString("nom");
				bat[(rs.getRow()-1)][1] = rs.getString("id");
				CB_Bateau.addItem(rs.getString("nom"));
			}
		}catch (SQLException ex){
			JOptionPane.showMessageDialog(null, "Une erreur lors de l'up de la liste bateau.", "Erreur", JOptionPane.INFORMATION_MESSAGE);
		}
		
		// Ajouts des "nom" et "id" pour les especes...
		try{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT nom, id FROM `espece`;");
			while (rs.next()){
				esp[(rs.getRow()-1)][0] = rs.getString("nom");
				esp[(rs.getRow()-1)][1] = rs.getString("id");
				CB_Espe.addItem(rs.getString("nom"));
			}
		}catch (SQLException ex){
			JOptionPane.showMessageDialog(null, "Une erreur lors de l'up de la liste especes.", "Erreur", JOptionPane.INFORMATION_MESSAGE);
		}
		
		// Ajouts des "nom" et "id" pour les Qualitées...
		try{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT libelle, id FROM `qualite`;");
			while (rs.next()){
				qual[(rs.getRow()-1)][0] = rs.getString("libelle");
				qual[(rs.getRow()-1)][1] = rs.getString("id");
				CB_Qual.addItem(rs.getString("libelle"));
			}
		}catch (SQLException ex){
			JOptionPane.showMessageDialog(null, "Une erreur lors de l'up de la liste Qualitées.", "Erreur", JOptionPane.INFORMATION_MESSAGE);
		}
		
		// Ajouts des "specification" et "id" pour les Tailles...
		try{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT specification, id FROM `taille`;");
			while (rs.next()){
				tail[(rs.getRow()-1)][0] = rs.getString("specification");
				tail[(rs.getRow()-1)][1] = rs.getString("id");
				CB_taille.addItem(rs.getString("specification"));
			}
		}catch (SQLException ex){
			JOptionPane.showMessageDialog(null, "Une erreur lors de l'up de la liste tail.", "Erreur", JOptionPane.INFORMATION_MESSAGE);
		}
		
		// Ajouts des "libelle" et "id" pour les presentations...
		try{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT libelle, id FROM `presentation`;");
			while (rs.next()){
				pres[(rs.getRow()-1)][0] = rs.getString("libelle");
				pres[(rs.getRow()-1)][1] = rs.getString("id");
				CB_Prése.addItem(rs.getString("libelle"));
			}
		}catch (SQLException ex){
			JOptionPane.showMessageDialog(null, "Une erreur lors de l'up de la liste pres.", "Erreur", JOptionPane.INFORMATION_MESSAGE);
		}	}
	
	void send() {
		
		int result = JOptionPane.showConfirmDialog(null, "Voulez-vous envoyer le nouveau lot ?", "Confirmer l'envoi", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
			if((CB_Prése.getSelectedItem()=="...")||(CB_Bateau.getSelectedItem()=="...")||(CB_Qual.getSelectedItem()=="...")||(CB_taille.getSelectedItem()=="...")||(CB_Espe.getSelectedItem()=="...")) {
				JOptionPane.showMessageDialog(null, "Veuillez séléctionnez une valeurs.", "Erreur", JOptionPane.INFORMATION_MESSAGE);
			}else {
				try {
					   String date = java.time.LocalDate.now()+"";
					   
					   PreparedStatement st6 = con.prepareStatement("INSERT INTO `lot` (`datePeche`, `idBateau`, `idEspece`, `idTaille`, `idPresentation`, `idQualite`) VALUES (?, ?, ?, ?, ?, ?);");
					   
					   // ENVOIR DE LA REQ SQL ......
					   st6.setString(1, date);
					   st6.setString(2, (bat[CB_Bateau.getSelectedIndex()-1][1]+""));
					   st6.setString(3, (esp[CB_Espe.getSelectedIndex()-1][1]+""));
					   st6.setString(4, (tail[CB_taille.getSelectedIndex()-1][1]+""));
					   st6.setString(5, (pres[CB_Prése.getSelectedIndex()-1][1]+""));
					   st6.setString(6, (qual[CB_Qual.getSelectedIndex()-1][1]+""));
					   
					   int rs6 = st6.executeUpdate();
					   
					   CB_Prése.setSelectedIndex(0);
					   CB_Qual.setSelectedIndex(0);
					   CB_taille.setSelectedIndex(0);
					   CB_Espe.setSelectedIndex(0);
					   CB_Bateau.setSelectedIndex(0);
					   
					   if (rs6>0) {
					       JOptionPane.showMessageDialog(null, "L'Ajout a été effectué.", "Ajout", JOptionPane.INFORMATION_MESSAGE);
					       ListLot.updateTable();
					       dispose();
					   } else {
					       JOptionPane.showMessageDialog(null, "Une erreur s'est produite eX01.", "Erreur", JOptionPane.INFORMATION_MESSAGE);
					   }
					} catch(SQLException ex) {
					   JOptionPane.showMessageDialog(null, "Une erreur lors de l'envoie du lot. Error: " +"\n"+ ex.getMessage() + ". SQL State: " + ex.getSQLState() + ". Error Code: " + ex.getErrorCode(), "Erreur", JOptionPane.INFORMATION_MESSAGE);
					}
			}
        }else {
        	JOptionPane.showMessageDialog(null, "Vous annulez l'envoi du nouveau lot.", "annulation", JOptionPane.INFORMATION_MESSAGE);
        }
	}
}
