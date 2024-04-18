package Bac;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import java.awt.Color;

public class ListBac extends JFrame {

	private static final long serialVersionUID = 1L;
	public static JPanel contentPane;
	public static JScrollPane JScroll;
	public static JTable table;
	public static Connection con;
	public static DefaultTableModel model;
	public static String idGlo;

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
	public ListBac(String id) {
		setTitle("Gestion des bacs pour le lot n°"+id);
		idGlo = id;
		con = main.connexion.connexion();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 635, 261);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBacs = new JLabel("Gestion des bacs pour le lot n° "+id);
		lblBacs.setFont(new Font("Dialog", Font.BOLD, 15));
		lblBacs.setBounds(27, 25, 353, 21);
		contentPane.add(lblBacs);
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.setForeground(Color.WHITE);
		btnSupprimer.setEnabled(false);
		btnSupprimer.setBackground(new Color(204, 0, 51));
		btnSupprimer.setBounds(436, 177, 168, 26);
		contentPane.add(btnSupprimer);
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.setForeground(Color.WHITE);
		btnAjouter.setBackground(new Color(0, 51, 204));
		btnAjouter.setBounds(436, 79, 168, 26);
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BacAdd frame = new BacAdd(id);
				frame.setLocationRelativeTo(null); // Permet d'avoir le frame au milieu de l'écran
				frame.setVisible(true);
			}
		});
		contentPane.add(btnAjouter);
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.setForeground(Color.WHITE);
		btnRetour.setBackground(new Color(255, 0, 51));
		btnRetour.setBounds(473, 24, 131, 26);
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		contentPane.add(btnRetour);
		
		JButton btnModif = new JButton("Modifier");
		btnModif.setForeground(Color.WHITE);
		btnModif.setEnabled(false);
		btnModif.setBackground(new Color(0, 51, 204));
		btnModif.setBounds(436, 116, 168, 26);
		btnModif.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				String idBac = (String) table.getModel().getValueAt(row, 0);
				BacModif frame = new BacModif(id, idBac );
				frame.setLocationRelativeTo(null); // Permet d'avoir le frame au milieu de l'écran
				frame.setVisible(true);
			}
		});
		contentPane.add(btnModif);
		
		controller.Bac_Controller.ListBacTableAdd();
		controller.Bac_Controller.updateListBacTable();
		
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				String idB = (String) table.getModel().getValueAt(row, 0);
				int result = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment supprimer le lot n° "+id+" ?", "Confirmer l'écrasement", JOptionPane.YES_NO_OPTION);
	            if (result == JOptionPane.YES_OPTION) {
	            	PreparedStatement st;
					try {
						st = con.prepareStatement("DELETE FROM `bac` WHERE `id` = ? AND idLot = ?;");
						st.setString(1, idB);
						st.setString(2, id);
						st.execute();
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "Une erreur lors de la suppression.", "Erreur", JOptionPane.ERROR_MESSAGE);
					}
					controller.Bac_Controller.updateListBacTable();
	            }
			}
		});
		
		ListSelectionModel selectionModel = table.getSelectionModel();
		selectionModel.addListSelectionListener(new ListSelectionListener() {
 	    	public void valueChanged(ListSelectionEvent e) {
 	    		if (table.getSelectedRow() >= 0) {
 	    			btnSupprimer.setEnabled(true);
 	    			btnSupprimer.setBackground(new Color(255, 0, 51));
 	    			
 	    			btnModif.setEnabled(true);
 	    			btnModif.setBackground(new Color(0, 51, 204));
 	    			
 	    		}else {
 	    			btnSupprimer.setEnabled(false);
 	    			btnSupprimer.setBackground(new Color(204, 0, 51));
 	    			
 	    			btnModif.setEnabled(false);
 	    			btnModif.setBackground(new Color(0, 0, 153));
 	    		}
 	    	}
 	    });
	}
}
