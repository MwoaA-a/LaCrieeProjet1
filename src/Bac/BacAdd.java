package Bac;

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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Color;

public class BacAdd extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	static Connection con;
	private JComboBox<String> CB_TB;
	public  Object[][] tyba;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Lot.ListLot frame = new Lot.ListLot();
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
	public BacAdd(String id) {
		con = main.connexion.connexion();
		setTitle("Création d'un Bac");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 441, 204);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_Titre = new JLabel("Création d'un bac");
		lbl_Titre.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lbl_Titre.setBounds(10, 11, 498, 39);
		contentPane.add(lbl_Titre);
		
		JLabel lbl_typeBac = new JLabel("Type de bac");
		lbl_typeBac.setBounds(74, 75, 101, 14);
		contentPane.add(lbl_typeBac);
		
		CB_TB = new JComboBox<String>();
		CB_TB.setBounds(185, 71, 190, 22);
		contentPane.add(CB_TB);
		
		JButton btn_retour = new JButton("Retour");
		btn_retour.setForeground(Color.WHITE);
		btn_retour.setBackground(new Color(255, 0, 51));
		btn_retour.setBounds(36, 131, 123, 23);
		contentPane.add(btn_retour);
		
		JButton btn_send = new JButton("Envoyer le bac");
		btn_send.setForeground(Color.WHITE);
		btn_send.setBackground(new Color(0, 51, 204));
		btn_send.setBounds(268, 131, 132, 23);
		btn_send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				send(id);
			}
		});
		contentPane.add(btn_send);
		
		update();
	}
	
	void update() {
		// Création du tableau "pres" pour les presentations...
				try{
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery("SELECT COUNT(*) as total FROM `typeBac`;");
					while (rs.next()){
						int total = rs.getInt("total");
					    tyba = new Object[total][2];
					}
				}catch (SQLException ex){
					JOptionPane.showMessageDialog(null, "Une erreur lors de l'up du tab pres.", "Erreur", JOptionPane.INFORMATION_MESSAGE);
				}
						
				// Ajouts des "nom" et "id" pour les bateaux...
				try{
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery("SELECT tare, id FROM `typeBac`;");
					while (rs.next()){
						tyba[(rs.getRow()-1)][0] = rs.getString("tare");
						tyba[(rs.getRow()-1)][1] = rs.getString("id");
						CB_TB.addItem(rs.getString("id")+" / "+rs.getString("tare"));
					}
				}catch (SQLException ex){
					JOptionPane.showMessageDialog(null, "Une erreur lors de l'up de la liste tyba.", "Erreur", JOptionPane.INFORMATION_MESSAGE);
				}
	}
	
	void send(String id) {
		int idBac = 0;
		
		int result = JOptionPane.showConfirmDialog(null, "Voulez-vous envoyer le nouveau bac ?", "Confirmer l'envoi", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
			if((CB_TB.getSelectedItem()=="...")){
				JOptionPane.showMessageDialog(null, "Veuillez séléctionnez une valeurs.", "Erreur", JOptionPane.INFORMATION_MESSAGE);
			}else {
				try {
					PreparedStatement st3;
					st3 = con.prepareStatement("SELECT id FROM `bac` WHERE idLot = ? ORDER BY id ASC;");
					st3.setString(1, id);
					ResultSet rs2 = st3.executeQuery();
					// Créer une liste pour stocker tous les id existants
					List<Integer> existingIds = new ArrayList<>();

					while (rs2.next()){
					    existingIds.add(rs2.getInt("id"));
					}
					// Trouver le premier id manquant
					int nextId = 1;
					for (int existingId : existingIds) {
					    if (existingId == nextId) {
					        nextId++;
					    } else {
					        break; // On a trouvé le premier id manquant, on arrête la boucle
					    }
					}
					// Si aucun id manquant n'est trouvé, alors le prochain idBac est simplement le total des id existants + 1
					if (nextId == existingIds.size() + 1) {
					    nextId = existingIds.size() + 1;
					}
					// nextId est maintenant le prochain idBac
					idBac = nextId;
					
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "Une erreur lors de l'up du tab.", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
				
				
				try {
					PreparedStatement st6;
					st6 = con.prepareStatement("INSERT INTO `bac` (`id`, `IdLot`, `idTypeBac`) VALUES (?, ?, ?);");
					// ENVOIR DE LA REQ SQL ......
					  st6.setInt(1, idBac);
					  st6.setString(2, id);
					  st6.setString(3, (tyba[CB_TB.getSelectedIndex()][1]+""));
					  
					  int rs6 = st6.executeUpdate();
					   
					  if (rs6>0) {
					      JOptionPane.showMessageDialog(null, "L'Ajout a été effectué.", "Ajout", JOptionPane.INFORMATION_MESSAGE);
					      ListBac.updateTable();
					      dispose();
					  } else {
					      JOptionPane.showMessageDialog(null, "Une erreur s'est produite eX01.", "Erreur", JOptionPane.ERROR_MESSAGE);
					  }
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "Une erreur s'est produite eX02.", "Erreur", JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}
				  
			}
        }else {
        	JOptionPane.showMessageDialog(null, "Vous annulez l'envoi du nouveau bac.", "annulation", JOptionPane.INFORMATION_MESSAGE);
        }
	}
}
