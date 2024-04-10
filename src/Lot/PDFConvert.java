package Lot;
import java.awt.Color;

import java.awt.EventQueue;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.Document;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.awt.Desktop;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

public class PDFConvert extends JFrame {

	private static final long serialVersionUID = 1L;
	static Connection con;
	public  Object[][] idPDF;
	public String idGlo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListLot frame = new ListLot();
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
	public PDFConvert(String id) {
		con = connexion.connexion();
		Update(id);
		PDFcreator(id);	
		
	}
	
void Update(String id) {
		
		String date = java.time.LocalDate.now()+"";
		
		// Création du tableau "idPDF" pour la ComboBox...
		try{
			PreparedStatement st = con.prepareStatement("SELECT COUNT(*) as total FROM `lot`WHERE `datePeche` = ?;");
			st.setString(1, date);
			ResultSet rs = st.executeQuery();
			while (rs.next()){
				int total = rs.getInt("total");
			    idPDF = new Object[total][2];
			}
		}catch (SQLException ex){
			JOptionPane.showMessageDialog(null, "Une erreur lors de l'up du tab bat.", "Erreur", JOptionPane.INFORMATION_MESSAGE);
		}
		
		// Ajouts des "nom" et "id" pour le tableau idPDF...
		try{
			PreparedStatement st = con.prepareStatement("SELECT lot.id, bateau.nom FROM lot INNER JOIN bateau ON lot.idBateau = bateau.id WHERE `datePeche` = ?;");
			st.setString(1, date);
			ResultSet rs = st.executeQuery();
			while (rs.next()){
				idPDF[(rs.getRow()-1)][0] = rs.getString("nom");
				idPDF[(rs.getRow()-1)][1] = rs.getString("id");
			}
		}catch (SQLException ex){
			JOptionPane.showMessageDialog(null, "Une erreur lors de l'up de la liste bateau.", "Erreur", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	void PDFcreator(String id) {
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
