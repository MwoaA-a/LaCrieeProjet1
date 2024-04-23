package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class Bac_Controller {
	
	static Connection con = main.connexion.connexion();
	
	public static void ListBacTableAdd() {
		Bac.ListBac.model = new DefaultTableModel();
		initListBacTable();
		Bac.ListBac.contentPane.setLayout(null);
		Bac.ListBac.table.setModel(Bac.ListBac.model);
		Bac.ListBac.contentPane.add(Bac.ListBac.JScroll);
		
		
		// Modifie le titre des colonnes
		Bac.ListBac.model.addColumn("N° Bac");
		Bac.ListBac.model.addColumn("N° Lot");
		Bac.ListBac.model.addColumn("Date Pêche");
		Bac.ListBac.model.addColumn("Bateau");
		Bac.ListBac.model.addColumn("Type Bac");
		
		// Modifie la taille des colonnes
		Bac.ListBac.table.getColumnModel().getColumn(0).setPreferredWidth(30);
		Bac.ListBac.table.getColumnModel().getColumn(1).setPreferredWidth(30);
		Bac.ListBac.table.getColumnModel().getColumn(2).setPreferredWidth(80);
		Bac.ListBac.table.getColumnModel().getColumn(3).setPreferredWidth(80);
		Bac.ListBac.table.getColumnModel().getColumn(4).setPreferredWidth(50);
		
	}
	
	public static void initListBacTable() {
		Bac.ListBac.table = new JTable();
		Bac.ListBac.table.getTableHeader().setReorderingAllowed(false);
		Bac.ListBac.table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		Bac.ListBac.table.setDefaultEditor(Object.class, null);
		Bac.ListBac.JScroll = new JScrollPane();
		Bac.ListBac.JScroll.setViewportView(Bac.ListBac.table);
		Bac.ListBac.JScroll.setBounds(30, 79, 394, 123);
		
		
	}
	
	public static void updateListBacTable() {
		Bac.ListBac.model.setRowCount(0);
	// Ajouter les données au modèle à partir de la base de données
		PreparedStatement st;
		try {
			st = con.prepareStatement("SELECT bac.`id`, bateau.nom as 'nomBat', lots.date_peche, bac.id_lot, type_bac.tare FROM `bac` INNER JOIN type_bac ON type_bac.id = bac.id_type_bac INNER JOIN lots ON lots.id = bac.id_lot INNER JOIN bateau ON lots.num_bateau = bateau.id WHERE bac.id_lot = ? ORDER BY id ASC;");
			st.setString(1, Bac.ListBac.idGlo);
			ResultSet rs = st.executeQuery();
			while (rs.next()){
				Bac.ListBac.model.addRow(new Object[]{rs.getString("id"),rs.getString("id_lot"),rs.getString("date_peche"),rs.getString("nomBat"),rs.getString("tare")});
				
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Une erreur lors de l'up du tab.", "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static void updateBacAdd() {
		// Création du tableau "pres" pour les presentations...
				try{
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery("SELECT COUNT(*) as total FROM `type_bac`;");
					while (rs.next()){
						int total = rs.getInt("total");
					    Bac.BacAdd.tyba = new Object[total][2];
					}
				}catch (SQLException ex){
					JOptionPane.showMessageDialog(null, "Une erreur lors de l'up du tab pres.", "Erreur", JOptionPane.INFORMATION_MESSAGE);
				}
						
				// Ajouts des "nom" et "id" pour les bateaux...
				try{
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery("SELECT tare, id FROM `type_bac`;");
					while (rs.next()){
						Bac.BacAdd.tyba[(rs.getRow()-1)][0] = rs.getString("tare");
						Bac.BacAdd.tyba[(rs.getRow()-1)][1] = rs.getString("id");
						Bac.BacAdd.CB_TB.addItem(rs.getString("id")+" / "+rs.getString("tare"));
					}
				}catch (SQLException ex){
					JOptionPane.showMessageDialog(null, "Une erreur lors de l'up de la liste tyba.", "Erreur", JOptionPane.INFORMATION_MESSAGE);
				}
	}
	
	public static void sendBacAdd(String id) {
		int idBac = 0;
		
		int result = JOptionPane.showConfirmDialog(null, "Voulez-vous envoyer le nouveau bac ?", "Confirmer l'envoi", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
			if((Bac.BacAdd.CB_TB.getSelectedItem()=="...")){
				JOptionPane.showMessageDialog(null, "Veuillez séléctionnez une valeurs.", "Erreur", JOptionPane.INFORMATION_MESSAGE);
			}else {
				try {
					PreparedStatement st3;
					st3 = con.prepareStatement("SELECT id FROM `bac` WHERE id_lot = ? ORDER BY id ASC;");
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
					st6 = con.prepareStatement("INSERT INTO `bac` (`id`, `id_lot`, `id_type_bac`) VALUES (?, ?, ?);");
					// ENVOIR DE LA REQ SQL ......
					  st6.setInt(1, idBac);
					  st6.setString(2, id);
					  st6.setString(3, (Bac.BacAdd.tyba[Bac.BacAdd.CB_TB.getSelectedIndex()][1]+""));
					  
					  int rs6 = st6.executeUpdate();
					   
					  if (rs6>0) {
					      JOptionPane.showMessageDialog(null, "L'Ajout a été effectué.", "Ajout", JOptionPane.INFORMATION_MESSAGE);
					      updateListBacTable();
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
	
	public static void updateBacModif() {
		// Création du tableau "pres" pour les presentations...
				try{
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery("SELECT COUNT(*) as total FROM `type_bac`;");
					while (rs.next()){
						int total = rs.getInt("total");
					    Bac.BacModif.tyba = new Object[total][2];
					}
				}catch (SQLException ex){
					JOptionPane.showMessageDialog(null, "Une erreur lors de l'up du tab pres.", "Erreur", JOptionPane.INFORMATION_MESSAGE);
				}
						
				// Ajouts des "nom" et "id" pour les bateaux...
				try{
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery("SELECT tare, id FROM `type_bac`;");
					while (rs.next()){
						Bac.BacModif.tyba[(rs.getRow()-1)][0] = rs.getString("tare");
						Bac.BacModif.tyba[(rs.getRow()-1)][1] = rs.getString("id");
						Bac.BacModif.CB_TB.addItem(rs.getString("id")+" / "+rs.getString("tare"));
					}
				}catch (SQLException ex){
					JOptionPane.showMessageDialog(null, "Une erreur lors de l'up de la liste tyba.", "Erreur", JOptionPane.INFORMATION_MESSAGE);
				}
	}
	
	public static void sendBacModif(String idLot, String idBac) {		
		int result = JOptionPane.showConfirmDialog(null, "Voulez-vous modifier le bac ?", "Confirmer l'envoi", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
			if((Bac.BacModif.CB_TB.getSelectedItem()=="...")){
				JOptionPane.showMessageDialog(null, "Veuillez séléctionnez une valeurs.", "Erreur", JOptionPane.INFORMATION_MESSAGE);
			}else {
				
				try {
					PreparedStatement st6;
					st6 = con.prepareStatement("UPDATE `bac` SET `id_type_bac`= ? WHERE `id` =  ? AND `id_lot` = ? ;");
					// ENVOIR DE LA REQ SQL ......
					  st6.setString(1, (Bac.BacModif.tyba[Bac.BacModif.CB_TB.getSelectedIndex()][1]+""));
					  st6.setString(2, idBac);
					  st6.setString(3, idLot);
					  
					  int rs6 = st6.executeUpdate();
					   
					  if (rs6>0) {
					      JOptionPane.showMessageDialog(null, "La modification a été enregistrer.", "modification", JOptionPane.INFORMATION_MESSAGE);
					      updateListBacTable();
					  } else {
					      JOptionPane.showMessageDialog(null, "Une erreur s'est produite eX01.", "Erreur", JOptionPane.ERROR_MESSAGE);
					  }
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "Une erreur s'est produite eX02.", "Erreur", JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}
				  
			}
        }else {
        	JOptionPane.showMessageDialog(null, "Vous annulez la modification du bac.", "annulation", JOptionPane.INFORMATION_MESSAGE);
        }
	}

}
