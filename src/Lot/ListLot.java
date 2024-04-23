package Lot;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Properties;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import java.awt.Color;

public class ListLot extends JFrame {

	public static final long serialVersionUID = 1L;
	public static JPanel contentPane;
	public static JScrollPane JScroll;
	public static JTable table;
	public static DefaultTableModel model;
	public static JDatePickerImpl datePicker;
	static Connection con = main.connexion.connexion();;



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
	public ListLot() {
		setTitle("Application de gestion de la criée");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 959, 311);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitre = new JLabel("Gestion des lots");
		lblTitre.setFont(new Font("Dialog", Font.BOLD, 15));
		lblTitre.setBounds(24, 11, 217, 21);
		contentPane.add(lblTitre);
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.setForeground(Color.WHITE);
		btnSupprimer.setEnabled(false);
		btnSupprimer.setBackground(new Color(204, 0, 51));
		btnSupprimer.setBounds(754, 214, 168, 26);
		contentPane.add(btnSupprimer);
		
		JButton btnVoirBacs = new JButton("Voir les bacs associés");
		btnVoirBacs.setForeground(Color.WHITE);
		btnVoirBacs.setEnabled(false);
		btnVoirBacs.setBackground(new Color(0, 0, 153));
		btnVoirBacs.setBounds(754, 66, 168, 26);
		btnVoirBacs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				String id = (String) table.getModel().getValueAt(row, 0);
				Bac.ListBac frame = new Bac.ListBac(id);
				System.out.print("");
				frame.setLocationRelativeTo(null); // Permet d'avoir le frame au milieu de l'écran
				frame.setVisible(true);
			}
		});
		contentPane.add(btnVoirBacs);
		
		JButton btnPDF = new JButton("Convertion en PDF");
		btnPDF.setForeground(Color.WHITE);
		btnPDF.setEnabled(false);
		btnPDF.setBackground(new Color(0, 0, 153));
		btnPDF.setBounds(754, 140, 168, 26);
		contentPane.add(btnPDF);
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.setForeground(Color.WHITE);
		btnAjouter.setBackground(new Color(0, 51, 204));
		btnAjouter.setBounds(754, 103, 168, 26);
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				java.sql.Date selectedDate = (java.sql.Date) datePicker.getModel().getValue();
				LotAdd frame = new LotAdd(selectedDate);
				frame.setLocationRelativeTo(null); // Permet d'avoir le frame au milieu de l'écran
				frame.setVisible(true);
			}
		});
		contentPane.add(btnAjouter);
		
		JButton btnLotModif = new JButton("Modifier");
		btnLotModif.setForeground(Color.WHITE);
		btnLotModif.setEnabled(false);
		btnLotModif.setEnabled(false);
		btnLotModif.setBackground(new Color(0, 0, 153));
		btnLotModif.setBounds(754, 177, 168, 26);
		btnLotModif.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				java.sql.Date selectedDate = (java.sql.Date) datePicker.getModel().getValue();
				int row = table.getSelectedRow();
				String idLot = (String) table.getModel().getValueAt(row, 0);
				LotModif frame = new LotModif(selectedDate, idLot);
				frame.setLocationRelativeTo(null); // Permet d'avoir le frame au milieu de l'écran
				frame.setVisible(true);
			}
		});
		contentPane.add(btnLotModif);
		
		final SqlDateModel model = new SqlDateModel();
		Calendar now = Calendar.getInstance();
		model.setDate(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH));
		model.setSelected(true);
		Properties p = new Properties();
		p.put("text.today", "Aujourd'hui");
		p.put("text.month", "Mois");
		p.put("text.year", "Année");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		datePicker = new JDatePickerImpl(datePanel, new main.DateLabelFormatter()); 
		contentPane.add(datePicker);
		datePicker.setBounds(561, 29, 139, 26);
		model.addPropertyChangeListener(new PropertyChangeListener() {
		    @Override
		    public void propertyChange(PropertyChangeEvent evt) {
		    	controller.Lot_Controller.updateListLotTable();
		    }
		});
		
		controller.Lot_Controller.ListLotTableAdd();
		controller.Lot_Controller.updateListLotTable();
		
		btnPDF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				String id = (String) table.getModel().getValueAt(row, 0);
				controller.Lot_Controller.PDFConvert(id);
			}
		});
		
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				String id = (String) table.getModel().getValueAt(row, 0);
				int result = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment supprimer le lot n° "+id+" ?", "Confirmer l'écrasement", JOptionPane.YES_NO_OPTION);
	            if (result == JOptionPane.YES_OPTION) {
	            	PreparedStatement st;
					try {
						st = con.prepareStatement("DELETE FROM `lots` WHERE `id` = ? ;");
						st.setString(1, id);
						st.execute();
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "Une erreur lors de la suppression.", "Erreur", JOptionPane.ERROR_MESSAGE);
					}
					controller.Lot_Controller.updateListLotTable();
	            }
			}
		});
		
		ListSelectionModel selectionModel = table.getSelectionModel();
		selectionModel.addListSelectionListener(new ListSelectionListener() {
 	    	public void valueChanged(ListSelectionEvent e) {
 	    		if (table.getSelectedRow() >= 0) {
 	    			btnSupprimer.setEnabled(true);
 	    			btnSupprimer.setBackground(new Color(255, 0, 51));
 	    			
 	    			btnVoirBacs.setEnabled(true);
 	    			btnVoirBacs.setBackground(new Color(0, 51, 204));

 	    			btnLotModif.setEnabled(true);
 	    			btnLotModif.setBackground(new Color(0, 51, 204));

 	    			btnPDF.setEnabled(true);
 	    			btnPDF.setBackground(new Color(0, 51, 204));
 	    		}else {
 	    			btnSupprimer.setEnabled(false);
 	    			btnSupprimer.setBackground(new Color(204, 0, 51));
 	    			
 	    			btnVoirBacs.setEnabled(false);
 	    			btnVoirBacs.setBackground(new Color(0, 0, 153));
 	    			
 	    			btnLotModif.setEnabled(false);
 	    			btnLotModif.setBackground(new Color(0, 0, 153));
 	    			
 	    			btnPDF.setEnabled(false);
 	    			btnPDF.setBackground(new Color(0, 0, 153));
 	    		}
 	    	}
 	    });
	}
}
