import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;

public class PoidsBac extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;

	// Établir une connexion à la base de données
    Connection conn = MySQLConnection.getConnection();
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PoidsBac frame = new PoidsBac();
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
	public PoidsBac() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 251);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Lot x - Bateau x - Bac x");
		lblNewLabel.setBounds(10, 10, 162, 13);
		contentPane.add(lblNewLabel);
		
		JLabel lblPoidsDuBac = new JLabel("Poids du bac : xx Kg");
		lblPoidsDuBac.setBounds(291, 10, 135, 13);
		contentPane.add(lblPoidsDuBac);
		
		JLabel lblModificationDuPoids = new JLabel("Modification du poids du bac :");
		lblModificationDuPoids.setBounds(128, 50, 174, 13);
		contentPane.add(lblModificationDuPoids);
		
		JLabel lblPoidsDuBac_1 = new JLabel("Poids du bac* :");
		lblPoidsDuBac_1.setBounds(115, 87, 92, 13);
		contentPane.add(lblPoidsDuBac_1);
		
		textField = new JTextField();
		textField.setBounds(217, 84, 68, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblPoidsDuBac_1_1 = new JLabel("* Tout champs est obligatoire");
		lblPoidsDuBac_1_1.setBounds(245, 191, 229, 13);
		contentPane.add(lblPoidsDuBac_1_1);
		
		 JButton btnNewButton = new JButton("Valider");
	        btnNewButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                String poidsBacText = textField.getText();
	                
	                if (poidsBacText.matches("-?\\d+(\\.\\d+)?")) {
	                    JOptionPane.showMessageDialog(null, "Validation du poids du bac confirmée !");
	                } else {
	                    JOptionPane.showMessageDialog(null, "Veuillez entrer un nombre valide dans le champ Poids du bac.");
	                }
	            }
	        });
		btnNewButton.setBounds(167, 140, 102, 21);
		contentPane.add(btnNewButton);
	}
}
