package Lot;
import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.text.DateFormat;
import java.util.Locale;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Color;

public class LotAdd extends JFrame {

	private static final long serialVersionUID = 1L;
	static Connection con;
	public JPanel contentPane;
	public static JComboBox<String> CB_Bateau;
	public static JComboBox<String> CB_Qual;
	public static JComboBox<String> CB_taille;
	public static JComboBox<String> CB_Espe;
	public static JComboBox<String> CB_Prése;
	public JLabel lbl_Titre;
	public JButton btn_retour;
	public JButton btn_send;
	public static  Object[][] bat;
	public static Object[][] esp;
	public static Object[][] qual;
	public static Object[][] tail;
	public static Object[][] pres;

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
	public LotAdd(java.util.Date Date) {
		setTitle("Création d'un lot");
		
		con = main.connexion.connexion();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(50, 50, 544, 392);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Locale locale = new Locale("fr", "FR");
		DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT, locale);
		String date = dateFormat.format(Date);
		lbl_Titre = new JLabel("Création d'un nouveau lot pour le " + date);;
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
		
		CB_Bateau = new JComboBox<String>();
		CB_Bateau.setBounds(215, 107, 190, 22);
		contentPane.add(CB_Bateau);
		CB_Bateau.addItem("...");
		
		CB_Qual = new JComboBox<String>();
		CB_Qual.setBounds(215, 132, 190, 22);
		contentPane.add(CB_Qual);
		CB_Qual.addItem("...");
		
		CB_taille = new JComboBox<String>();
		CB_taille.setBounds(215, 157, 190, 22);
		contentPane.add(CB_taille);
		CB_taille.addItem("...");
		
		CB_Espe = new JComboBox<String>();
		CB_Espe.setBounds(215, 182, 190, 22);
		contentPane.add(CB_Espe);
		CB_Espe.addItem("...");
		
		CB_Prése = new JComboBox<String>();
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
				controller.Lot_Controller.sendLotAdd(Date);
				dispose();
			}
		});
		contentPane.add(btn_send);
		
		controller.Lot_Controller.UpdateLotAdd();
	}
}
