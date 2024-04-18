package controller;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
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

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;

import main.connexion;



public class Lot_Controller {
	
	static Connection con = main.connexion.connexion();;
	
	public static void ListLotTableAdd() {
		Lot.ListLot.model = new DefaultTableModel();
		initListLotTable();
		Lot.ListLot.contentPane.setLayout(null);
		Lot.ListLot.table.setModel(Lot.ListLot.model);
		Lot.ListLot.contentPane.add(Lot.ListLot.JScroll);
		
		// Modifie le titre des colonnes
		Lot.ListLot.model.addColumn("N° lot");
		Lot.ListLot.model.addColumn("Date Pêche");
		Lot.ListLot.model.addColumn("Bateau");
		Lot.ListLot.model.addColumn("Espèce");
		Lot.ListLot.model.addColumn("Taille");
		Lot.ListLot.model.addColumn("Qualité");
		Lot.ListLot.model.addColumn("Présentation");
		
		// Modifie la taille des colonnes
		Lot.ListLot.table.getColumnModel().getColumn(0).setPreferredWidth(20);
		Lot.ListLot.table.getColumnModel().getColumn(1).setPreferredWidth(60);
		Lot.ListLot.table.getColumnModel().getColumn(2).setPreferredWidth(80);
		Lot.ListLot.table.getColumnModel().getColumn(3).setPreferredWidth(120);
		Lot.ListLot.table.getColumnModel().getColumn(4).setPreferredWidth(60);
		Lot.ListLot.table.getColumnModel().getColumn(5).setPreferredWidth(50);
		Lot.ListLot.table.getColumnModel().getColumn(6).setPreferredWidth(40);
		
	}
	
	public static void initListLotTable() {
		Lot.ListLot.table = new JTable();
		Lot.ListLot.table.getTableHeader().setReorderingAllowed(false);
		Lot.ListLot.table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		Lot.ListLot.table.setDefaultEditor(Object.class, null);
		Lot.ListLot.JScroll = new JScrollPane();
		Lot.ListLot.JScroll.setViewportView(Lot.ListLot.table);
		Lot.ListLot.JScroll.setBounds(30, 66, 670, 175);
		
		
	}
	
	public static void updateListLotTable() {
		Lot.ListLot.model.setRowCount(0);
	// Ajouter les données au modèle à partir de la base de données
		java.sql.Date selectedDate = (java.sql.Date) Lot.ListLot.datePicker.getModel().getValue();
		PreparedStatement st;
		try {
			st = con.prepareStatement("SELECT lot.id, lot.`datePeche`, espece.nom as nomEsp, qualite.libelle as qualLibelle, taille.specification, presentation.libelle as presLibelle , bateau.nom as batNom FROM lot INNER JOIN bateau ON lot.idBateau = bateau.id INNER JOIN espece ON lot.idEspece = espece.id INNER JOIN taille ON lot.idTaille = taille.id INNER JOIN qualite ON lot.idQualite = qualite.id INNER JOIN presentation ON lot.idPresentation = presentation.id WHERE datePeche = ? ORDER BY lot.id DESC ;");
			st.setString(1, selectedDate+"");
			ResultSet rs = st.executeQuery();
			while (rs.next()){
				Lot.ListLot.model.addRow(new Object[]{rs.getString("id"), rs.getString("datePeche") ,rs.getString("batNom"),rs.getString("nomEsp"), rs.getString("specification"), rs.getString("qualLibelle"), rs.getString("presLibelle")});
				
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Une erreur lors de l'up du tab.", "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}
	public static void UpdateLotAdd() {

		
		// Création du tableau "bat" pour les bateaux...
		try{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT COUNT(*) as total FROM `bateau`;");
			while (rs.next()){
				int total = rs.getInt("total");
			    Lot.LotAdd.bat = new Object[total][2];
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
				Lot.LotAdd.esp = new Object[total][2];
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
				Lot.LotAdd.qual = new Object[total][2];
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
				Lot.LotAdd.tail = new Object[total][2];
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
				Lot.LotAdd.pres = new Object[total][2];
			}
		}catch (SQLException ex){
			JOptionPane.showMessageDialog(null, "Une erreur lors de l'up du tab pres.", "Erreur", JOptionPane.INFORMATION_MESSAGE);
		}
				
		// Ajouts des "nom" et "id" pour les bateaux...
		try{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT nom, id FROM `bateau`;");
			while (rs.next()){
				Lot.LotAdd.bat[(rs.getRow()-1)][0] = rs.getString("nom");
				Lot.LotAdd.bat[(rs.getRow()-1)][1] = rs.getString("id");
				Lot.LotAdd.CB_Bateau.addItem(rs.getString("nom"));
			}
		}catch (SQLException ex){
			JOptionPane.showMessageDialog(null, "Une erreur lors de l'up de la liste bateau.", "Erreur", JOptionPane.INFORMATION_MESSAGE);
		}
		
		// Ajouts des "nom" et "id" pour les especes...
		try{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT nom, id FROM `espece`;");
			while (rs.next()){
				Lot.LotAdd.esp[(rs.getRow()-1)][0] = rs.getString("nom");
				Lot.LotAdd.esp[(rs.getRow()-1)][1] = rs.getString("id");
				Lot.LotAdd.CB_Espe.addItem(rs.getString("nom"));
			}
		}catch (SQLException ex){
			JOptionPane.showMessageDialog(null, "Une erreur lors de l'up de la liste especes.", "Erreur", JOptionPane.INFORMATION_MESSAGE);
		}
		
		// Ajouts des "nom" et "id" pour les Qualitées...
		try{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT libelle, id FROM `qualite`;");
			while (rs.next()){
				Lot.LotAdd.qual[(rs.getRow()-1)][0] = rs.getString("libelle");
				Lot.LotAdd.qual[(rs.getRow()-1)][1] = rs.getString("id");
				Lot.LotAdd.CB_Qual.addItem(rs.getString("libelle"));
			}
		}catch (SQLException ex){
			JOptionPane.showMessageDialog(null, "Une erreur lors de l'up de la liste Qualitées.", "Erreur", JOptionPane.INFORMATION_MESSAGE);
		}
		
		// Ajouts des "specification" et "id" pour les Tailles...
		try{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT specification, id FROM `taille`;");
			while (rs.next()){
				Lot.LotAdd.tail[(rs.getRow()-1)][0] = rs.getString("specification");
				Lot.LotAdd.tail[(rs.getRow()-1)][1] = rs.getString("id");
				Lot.LotAdd.CB_taille.addItem(rs.getString("specification"));
			}
		}catch (SQLException ex){
			JOptionPane.showMessageDialog(null, "Une erreur lors de l'up de la liste tail.", "Erreur", JOptionPane.INFORMATION_MESSAGE);
		}
		
		// Ajouts des "libelle" et "id" pour les presentations...
		try{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT libelle, id FROM `presentation`;");
			while (rs.next()){
				Lot.LotAdd.pres[(rs.getRow()-1)][0] = rs.getString("libelle");
				Lot.LotAdd.pres[(rs.getRow()-1)][1] = rs.getString("id");
				Lot.LotAdd.CB_Prése.addItem(rs.getString("libelle"));
			}
		}catch (SQLException ex){
			JOptionPane.showMessageDialog(null, "Une erreur lors de l'up de la liste pres.", "Erreur", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	public static void sendLotAdd(java.util.Date Date) {
		
		int result = JOptionPane.showConfirmDialog(null, "Voulez-vous envoyer le nouveau lot ?", "Confirmer l'envoi", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
			if((Lot.LotAdd.CB_Prése.getSelectedItem()=="...")||(Lot.LotAdd.CB_Bateau.getSelectedItem()=="...")||(Lot.LotAdd.CB_Qual.getSelectedItem()=="...")||(Lot.LotAdd.CB_taille.getSelectedItem()=="...")||(Lot.LotAdd.CB_Espe.getSelectedItem()=="...")) {
				JOptionPane.showMessageDialog(null, "Veuillez séléctionnez une valeurs.", "Erreur", JOptionPane.INFORMATION_MESSAGE);
			}else {
				try {
					   
					   PreparedStatement st6 = con.prepareStatement("INSERT INTO `lot` (`datePeche`, `idBateau`, `idEspece`, `idTaille`, `idPresentation`, `idQualite`) VALUES (?, ?, ?, ?, ?, ?);");
					   
					   // ENVOIR DE LA REQ SQL ......
					   st6.setString(1, Date+"");
					   st6.setString(2, (Lot.LotAdd.bat[Lot.LotAdd.CB_Bateau.getSelectedIndex()-1][1]+""));
					   st6.setString(3, (Lot.LotAdd.esp[Lot.LotAdd.CB_Espe.getSelectedIndex()-1][1]+""));
					   st6.setString(4, (Lot.LotAdd.tail[Lot.LotAdd.CB_taille.getSelectedIndex()-1][1]+""));
					   st6.setString(5, (Lot.LotAdd.pres[Lot.LotAdd.CB_Prése.getSelectedIndex()-1][1]+""));
					   st6.setString(6, (Lot.LotAdd.qual[Lot.LotAdd.CB_Qual.getSelectedIndex()-1][1]+""));
					   
					   int rs6 = st6.executeUpdate();
					   
					   Lot.LotAdd.CB_Prése.setSelectedIndex(0);
					   Lot.LotAdd.CB_Qual.setSelectedIndex(0);
					   Lot.LotAdd.CB_taille.setSelectedIndex(0);
					   Lot.LotAdd.CB_Espe.setSelectedIndex(0);
					   Lot.LotAdd.CB_Bateau.setSelectedIndex(0);
					   
					   if (rs6>0) {
					       JOptionPane.showMessageDialog(null, "L'Ajout a été effectué.", "Ajout", JOptionPane.INFORMATION_MESSAGE);
					       updateListLotTable();
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
	
    public static void UpdateLotModif(String idLot) {

		
		// Création du tableau "bat" pour les bateaux...
		try{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT COUNT(*) as total FROM `bateau`;");
			while (rs.next()){
				int total = rs.getInt("total");
			    Lot.LotModif.bat = new Object[total][2];
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
				Lot.LotModif.esp = new Object[total][2];
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
				Lot.LotModif.qual = new Object[total][2];
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
				Lot.LotModif.tail = new Object[total][2];
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
				Lot.LotModif.pres = new Object[total][2];
			}
		}catch (SQLException ex){
			JOptionPane.showMessageDialog(null, "Une erreur lors de l'up du tab pres.", "Erreur", JOptionPane.INFORMATION_MESSAGE);
		}
				
		// Ajouts des "nom" et "id" pour les bateaux...
		try{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT nom, id FROM `bateau`;");
			while (rs.next()){
				Lot.LotModif.bat[(rs.getRow()-1)][0] = rs.getString("nom");
				Lot.LotModif.bat[(rs.getRow()-1)][1] = rs.getString("id");
				Lot.LotModif.CB_Bateau.addItem(rs.getString("nom"));
			}
		}catch (SQLException ex){
			JOptionPane.showMessageDialog(null, "Une erreur lors de l'up de la liste bateau.", "Erreur", JOptionPane.INFORMATION_MESSAGE);
		}
		
		// Ajouts des "nom" et "id" pour les especes...
		try{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT nom, id FROM `espece`;");
			while (rs.next()){
				Lot.LotModif.esp[(rs.getRow()-1)][0] = rs.getString("nom");
				Lot.LotModif.esp[(rs.getRow()-1)][1] = rs.getString("id");
				Lot.LotModif.CB_Espe.addItem(rs.getString("nom"));
			}
		}catch (SQLException ex){
			JOptionPane.showMessageDialog(null, "Une erreur lors de l'up de la liste especes.", "Erreur", JOptionPane.INFORMATION_MESSAGE);
		}
		
		// Ajouts des "nom" et "id" pour les Qualitées...
		try{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT libelle, id FROM `qualite`;");
			while (rs.next()){
				Lot.LotModif.qual[(rs.getRow()-1)][0] = rs.getString("libelle");
				Lot.LotModif.qual[(rs.getRow()-1)][1] = rs.getString("id");
				Lot.LotModif.CB_Qual.addItem(rs.getString("libelle"));
			}
		}catch (SQLException ex){
			JOptionPane.showMessageDialog(null, "Une erreur lors de l'up de la liste Qualitées.", "Erreur", JOptionPane.INFORMATION_MESSAGE);
		}
		
		// Ajouts des "specification" et "id" pour les Tailles...
		try{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT specification, id FROM `taille`;");
			while (rs.next()){
				Lot.LotModif.tail[(rs.getRow()-1)][0] = rs.getString("specification");
				Lot.LotModif.tail[(rs.getRow()-1)][1] = rs.getString("id");
				Lot.LotModif.CB_taille.addItem(rs.getString("specification"));
			}
		}catch (SQLException ex){
			JOptionPane.showMessageDialog(null, "Une erreur lors de l'up de la liste tail.", "Erreur", JOptionPane.INFORMATION_MESSAGE);
		}
		
		// Ajouts des "libelle" et "id" pour les presentations...
		try{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT libelle, id FROM `presentation`;");
			while (rs.next()){
				Lot.LotModif.pres[(rs.getRow()-1)][0] = rs.getString("libelle");
				Lot.LotModif.pres[(rs.getRow()-1)][1] = rs.getString("id");
				Lot.LotModif.CB_Prése.addItem(rs.getString("libelle"));
			}
		}catch (SQLException ex){
			JOptionPane.showMessageDialog(null, "Une erreur lors de l'up de la liste pres.", "Erreur", JOptionPane.INFORMATION_MESSAGE);
		}
		
		// Selectionne les bon items des ComboBoxs...
				try{
					PreparedStatement st = con.prepareStatement("SELECT lot.id, lot.idBateau, espece.nom as nomEsp, qualite.libelle as qualLibelle, taille.specification, presentation.libelle as presLibelle FROM lot INNER JOIN espece ON lot.idEspece = espece.id INNER JOIN taille ON lot.idTaille = taille.id INNER JOIN qualite ON lot.idQualite = qualite.id INNER JOIN presentation ON lot.idPresentation = presentation.id WHERE lot.id = ? ;");
					st.setString(1, idLot);
					ResultSet rs = st.executeQuery();
					
					while (rs.next()){
						Lot.LotModif.CB_Bateau.setSelectedIndex(rs.getInt("idBateau"));
						Lot.LotModif.CB_taille.setSelectedItem(rs.getString("specification"));
						Lot.LotModif.CB_Prése.setSelectedItem(rs.getString("presLibelle"));
						Lot.LotModif.CB_Espe.setSelectedItem(rs.getString("nomEsp"));
						Lot.LotModif.CB_Qual.setSelectedItem(rs.getString("qualLibelle"));
					}
				}catch (SQLException ex){
					JOptionPane.showMessageDialog(null, "Une erreur lors de la selection.", "Erreur", JOptionPane.INFORMATION_MESSAGE);
				}
	}

	public static void sendLotModif(java.util.Date Date, String idLot) {
	
	int result = JOptionPane.showConfirmDialog(null, "Voulez-vous modifier le lot ?", "Confirmer l'envoi", JOptionPane.YES_NO_OPTION);
    if (result == JOptionPane.YES_OPTION) {
		if((Lot.LotModif.CB_Prése.getSelectedItem()=="...")||(Lot.LotModif.CB_Bateau.getSelectedItem()=="...")||(Lot.LotModif.CB_Qual.getSelectedItem()=="...")||(Lot.LotModif.CB_taille.getSelectedItem()=="...")||(Lot.LotModif.CB_Espe.getSelectedItem()=="...")) {
			JOptionPane.showMessageDialog(null, "Veuillez séléctionnez une valeurs.", "Erreur", JOptionPane.INFORMATION_MESSAGE);
		}else {
			try {
				   
				   PreparedStatement st6 = con.prepareStatement("UPDATE `lot` SET `datePeche`= ? ,`idBateau`= ? ,`idEspece`= ? ,`idTaille`= ? ,`idPresentation`= ? ,`idQualite`= ? WHERE id = ? ;");
				   
				   // ENVOIR DE LA REQ SQL ......
				   st6.setString(1, Date+"");
				   st6.setString(2, (Lot.LotModif.bat[Lot.LotModif.CB_Bateau.getSelectedIndex()-1][1]+""));
				   st6.setString(3, (Lot.LotModif.esp[Lot.LotModif.CB_Espe.getSelectedIndex()-1][1]+""));
				   st6.setString(4, (Lot.LotModif.tail[Lot.LotModif.CB_taille.getSelectedIndex()-1][1]+""));
				   st6.setString(5, (Lot.LotModif.pres[Lot.LotModif.CB_Prése.getSelectedIndex()-1][1]+""));
				   st6.setString(6, (Lot.LotModif.qual[Lot.LotModif.CB_Qual.getSelectedIndex()-1][1]+""));
				   st6.setString(7, idLot);
				   
				   int rs6 = st6.executeUpdate();
				   
				   if (rs6>0) {
				       JOptionPane.showMessageDialog(null, "La modification a été effectué.", "Ajout", JOptionPane.INFORMATION_MESSAGE);
				       updateListLotTable();
				   } else {
				       JOptionPane.showMessageDialog(null, "Une erreur s'est produite eX01.", "Erreur", JOptionPane.INFORMATION_MESSAGE);
				   }
				} catch(SQLException ex) {
				   JOptionPane.showMessageDialog(null, "Une erreur lors de l'envoie de La modification. Error: " +"\n"+ ex.getMessage() + ". SQL State: " + ex.getSQLState() + ". Error Code: " + ex.getErrorCode(), "Erreur", JOptionPane.INFORMATION_MESSAGE);
				}
		}
    }else {
    	JOptionPane.showMessageDialog(null, "Vous annulez l'envoi de La modification.", "annulation", JOptionPane.INFORMATION_MESSAGE);
    }
	}
	public static void PDFConvert(String id) {
		con = connexion.connexion();
		PDFcreator(id);	
		
	}
	

	static void PDFcreator(String id) {
        		try {
        	        PDDocument document = new PDDocument();
        	        PDPage page = new PDPage();
        	        document.addPage(page);
        	        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        	        // Utilisation d'une autre police et définition de la taille du texte
        	        contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 14);

        	        // Dimensions du tableau
        	        float margin = 50;
        	        float yStart = page.getMediaBox().getHeight() - margin;
        	        float tableWidth = page.getMediaBox().getWidth() - (2 * Math.abs(margin)) + 10;
        	        float nbRow = 2;
        	        float rowHeight = 25;
        	        float tableHeight = rowHeight * nbRow;
        	        float cellMargin = -5;

        	        // Données du tableau (récupérées depuis la base de données)
        	        List<String[]> data = new ArrayList<>();
        	        
        	        // Récupérer les données de la base de données et les stocker dans data
        	        try {
        	            PreparedStatement st = con.prepareStatement("SELECT * FROM lot WHERE lot.id = ?;");
        	            	st.setString(1, id);
        	            	ResultSet rs = st.executeQuery();
        	            	while (rs.next()) {
        	            		String[] row1 = new String[3];
        	            		row1[0] = "ID : " + rs.getString("id");
        	            		row1[1] = "Date pêche : " + rs.getString("datePeche");
        	            		row1[2] = "ID Espèce : " + rs.getString("idEspece");
        	            		data.add(row1);
        	                
        	            		String[] row2 = new String[3];
        	            		row2[0] = "ID Taille : " + rs.getString("idTaille");
        	            		row2[1] = "ID Présentation : " + rs.getString("idPresentation");
        	                	row2[2] = "ID Qualité : " + rs.getString("idQualite");
        	                	data.add(row2);
        	                	}
        	            } catch (SQLException ex) {
        	            	JOptionPane.showMessageDialog(null, "Une erreur lors de la récupération des données.", "Erreur", JOptionPane.INFORMATION_MESSAGE);
        	            }

        	        // Dessiner les lignes du tableau
        	        float yPosition = yStart;
        	        for (int i = 0; i < data.size() + 1; i++) {
        	            contentStream.moveTo(margin, yPosition);
        	            contentStream.lineTo(margin + tableWidth, yPosition);
        	            yPosition -= rowHeight;
        	        }

        	        // Dessiner les colonnes du tableau
        	        float columnWidth = tableWidth / 3;
        	        float xPosition = margin;
        	        for (int i = 0; i < 4; i++) {
        	            contentStream.moveTo(xPosition, yStart);
        	            contentStream.lineTo(xPosition, yStart - tableHeight);
        	            xPosition += columnWidth;
        	        }

        	        contentStream.stroke();

        	        // Remplir les cellules avec les données
        	        float textX, textY;
        	        for (int i = 0; i < data.size(); i++) {
        	            String[] row = data.get(i);
        	            textY = yStart - (i * rowHeight) - rowHeight - cellMargin;
        	            for (int j = 0; j < row.length; j++) {
        	                textX = margin + (j * columnWidth) + 5;
        	                contentStream.beginText();
        	                contentStream.newLineAtOffset(textX, textY);
        	                contentStream.showText(row[j]);
        	                contentStream.endText();
        	            }
        	        }

        	        contentStream.close();

        	        // Sauvegarde du document PDF
        	        	//récupération de l'id et du nom du bateau pour le nom du fichier
        	        String idName = "lot_n_"+id;
        	        String fileName = "D:/Documents/Java/PDF/" + idName + ".pdf";
        	        File file = new File(fileName);

        	        if (file.exists()) {
        	            int result = JOptionPane.showConfirmDialog(null, "Le fichier existe déjà. Voulez-vous l'écraser ?", "Confirmer l'écrasement", JOptionPane.YES_NO_OPTION);
        	            if (result == JOptionPane.YES_OPTION) {
        	                document.save(fileName);
        	                JOptionPane.showMessageDialog(null, "Le fichier PDF a bien été créé.", "PDF", JOptionPane.INFORMATION_MESSAGE);

        	                //Vérifier si le système prend en charge la classe Desktop ou non
        	                if(!Desktop.isDesktopSupported()){
        	                    System.out.println("Desktop n'est pas prise en charge");
        	                    return;
        	                }
        	                
        	                Desktop d = Desktop.getDesktop();
        	                if(file.exists()) 
        	                    d.open(file);
        	            }
        	        } else {
        	            document.save(fileName);
        	            JOptionPane.showMessageDialog(null, "Le fichier PDF a bien été créé.", "PDF", JOptionPane.INFORMATION_MESSAGE);

    	                //Vérifier si le système prend en charge la classe Desktop ou non
    	                if(!Desktop.isDesktopSupported()){
    	                    System.out.println("Desktop n'est pas prise en charge");
    	                    return;
    	                }
    	                
    	                Desktop d = Desktop.getDesktop();
    	                if(file.exists()) 
    	                    d.open(file);
        	        }

        	        document.close();
        	    } catch (IOException e) {
        	        e.printStackTrace();
        	    }
        	}
}
