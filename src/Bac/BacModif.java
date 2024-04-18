package Bac;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Color;

public class BacModif extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	static Connection con;
	public static JComboBox<String> CB_TB;
	public static  Object[][] tyba;

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
	public BacModif(String idlot, String idBac) {
		con = main.connexion.connexion();
		setTitle("Modification du bac n°"+idBac+" pour le lot n°"+idlot);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 557, 204);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_Titre = new JLabel("Modification du bac n°"+idBac+" pour le lot n° "+idlot+" :");
		lbl_Titre.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lbl_Titre.setBounds(33, 11, 498, 39);
		contentPane.add(lbl_Titre);
		
		JLabel lbl_typeBac = new JLabel("Type de bac");
		lbl_typeBac.setBounds(118, 75, 101, 14);
		contentPane.add(lbl_typeBac);
		
		CB_TB = new JComboBox<String>();
		CB_TB.setBounds(229, 71, 190, 22);
		contentPane.add(CB_TB);
		
		JButton btn_retour = new JButton("Retour");
		btn_retour.setForeground(Color.WHITE);
		btn_retour.setBackground(new Color(255, 0, 51));
		btn_retour.setBounds(80, 131, 123, 23);
		contentPane.add(btn_retour);
		
		JButton btn_send = new JButton("Enregistrer les modification");
		btn_send.setForeground(Color.WHITE);
		btn_send.setBackground(new Color(0, 51, 204));
		btn_send.setBounds(312, 131, 132, 23);
		btn_send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.Bac_Controller.sendBacModif(idlot, idBac);
				dispose();
			}
		});
		contentPane.add(btn_send);
		
		controller.Bac_Controller.updateBacModif();
	}
}
