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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;

public class Etiquettes_ETQP extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	static Connection con;
	private JComboBox<String> CB_lot;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Etiquettes_ETQP frame = new Etiquettes_ETQP();
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
	public Etiquettes_ETQP() {
		con = connexion.connexion();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 569, 220);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		CB_lot = new JComboBox();
		CB_lot.setBounds(140, 80, 278, 22);
		contentPane.add(CB_lot);
		CB_lot.addItem("...");
		
		JLabel lblNewLabel = new JLabel("Séléction du lot pour impression étiquette EQTP");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel.setBounds(31, 31, 533, 27);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Imprimer");
		btnNewButton.setBounds(238, 147, 89, 23);
		btnNewButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				PDFcreator();
			}
		});
		contentPane.add(btnNewButton);
		
		Update();
	}
	
	void Update() {
		try{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT lot.id, lot.datePeche, bateau.nom FROM lot INNER JOIN bateau ON lot.idBateau = bateau.id;");
			while (rs.next()){
				int i = 0;
				CB_lot.addItem(rs.getString("id")+" _ "+rs.getString("datePeche")+" _ "+rs.getString("nom"));
			}
			}catch (SQLException ex){
			JOptionPane.showMessageDialog(null, "Une erreur lors de l'up de la liste.", "Erreur", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	void PDFcreator() {
		try {
	           PDDocument document = new PDDocument();
	           PDPage page = new PDPage();
	           document.addPage(page);

	           PDPageContentStream contentStream = new PDPageContentStream(document, page);
	           
	           java.util.logging.Logger.getLogger("org.apache.fontbox").setLevel(java.util.logging.Level.SEVERE);
	           contentStream.beginText();
	           contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 14);
	           contentStream.setLeading(14.5f);
	           contentStream.newLineAtOffset(25, 700);
	           String line1 = "Étiquette pour le lot ID :";
	           contentStream.showText(line1);
	           contentStream.endText();

	           contentStream.close();
	           
	           File file = new File("C:/Users/titib/Documents/Java/PDF/"+CB_lot.getSelectedItem()+".pdf");
	           if (file.exists()) {
	        	   int result = JOptionPane.showConfirmDialog(null, "The file already exists. Do you want to overwrite it?", "Confirm Overwrite", JOptionPane.YES_NO_OPTION);
	               if (result == JOptionPane.YES_OPTION) {
	                   document.save("C:/Users/titib/Documents/Java/PDF/"+CB_lot.getSelectedItem()+".pdf");
	               }
	           } else {
	        	   document.save("C:/Users/titib/Documents/Java/PDF/"+CB_lot.getSelectedItem()+".pdf");
	           }
	           document.close();
	       } catch (IOException e) {
	           e.printStackTrace();
	       }
		CB_lot.setSelectedIndex(0);
	}
}
