package Bac;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Lot.LotAdd;

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
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import java.awt.Color;

public class ListBac extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JScrollPane JScroll;
	private JTable table;
	static Connection con;
	private static DefaultTableModel model;
	private static String idGlo;

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
		setTitle("Gestion des bacs");
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
		TableAdd();
		updateTable();
		
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
					updateTable();
	            }
			}
		});
		
		ListSelectionModel selectionModel = table.getSelectionModel();
		selectionModel.addListSelectionListener(new ListSelectionListener() {
 	    	public void valueChanged(ListSelectionEvent e) {
 	    		if (table.getSelectedRow() >= 0) {
 	    			btnSupprimer.setEnabled(true);
 	    			btnSupprimer.setBackground(new Color(255, 0, 51));
 	    			
 	    		}else {
 	    			btnSupprimer.setEnabled(false);
 	    			btnSupprimer.setBackground(new Color(204, 0, 51));
 	    		}
 	    	}
 	    });
	}
	
	
	private void TableAdd() {
		model = new DefaultTableModel();
		initTable();
		contentPane.setLayout(null);
		table.setModel(model);
		contentPane.add(JScroll);
		
		// Modifie le titre des colonnes
		model.addColumn("N° Bac");
		model.addColumn("N° Lot");
		model.addColumn("Date Pêche");
		model.addColumn("Bateau");
		model.addColumn("Type Bac");
		
		// Modifie la taille des colonnes
		table.getColumnModel().getColumn(0).setPreferredWidth(30);
		table.getColumnModel().getColumn(1).setPreferredWidth(30);
		table.getColumnModel().getColumn(2).setPreferredWidth(80);
		table.getColumnModel().getColumn(3).setPreferredWidth(80);
		table.getColumnModel().getColumn(4).setPreferredWidth(50);
		
	}
	
	private void initTable() {
		table = new JTable();
		table.getTableHeader().setReorderingAllowed(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setDefaultEditor(Object.class, null);
		JScroll = new JScrollPane();
		JScroll.setViewportView(table);
		JScroll.setBounds(30, 79, 394, 123);
		
		
	}
	
	static void updateTable() {
		model.setRowCount(0);
	// Ajouter les données au modèle à partir de la base de données
		String date = java.time.LocalDate.now()+"";
		PreparedStatement st;
		try {
			st = con.prepareStatement("SELECT bac.`id`, bateau.nom as 'nomBat', datePeche, bac.IdLot, typebac.tare  FROM `bac` INNER JOIN bateau ON bac.Idbateau = bateau.id INNER JOIN typebac ON typebac.id = bac.idTypeBac WHERE bac.idLot = ? ORDER BY id ASC;");
			st.setString(1, idGlo);
			ResultSet rs = st.executeQuery();
			while (rs.next()){
				model.addRow(new Object[]{rs.getString("id"),rs.getString("idLot"),rs.getString("datePeche"),rs.getString("nomBat"),rs.getString("tare")});
				
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Une erreur lors de l'up du tab.", "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}
}
