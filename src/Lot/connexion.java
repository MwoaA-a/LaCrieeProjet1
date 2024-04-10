package Lot;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class connexion {
	static Connection con;
	
	public static Connection connexion(){
		try {
			con = DriverManager.getConnection("jdbc:mysql:///bddcriee2","root","");
			System.out.println("La connexion à la base de données à reussi.");
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, "Problème de connexion à la base de donnée.", "Erreur", JOptionPane.INFORMATION_MESSAGE);
		}
		return con;
	}
}
