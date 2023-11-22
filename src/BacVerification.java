import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BacVerification {
    public static boolean verifyBacExists(Connection conn, String bacId) {
        boolean exists = false;
        String query = "SELECT id FROM TYPEBAC WHERE id = ?";
        
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, bacId);
            ResultSet resultSet = statement.executeQuery();
            
            exists = resultSet.next(); // Vérifie si un enregistrement correspondant a été trouvé
            
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return exists;
    }
    public static boolean verifyBacExistsByBateauLot(Connection conn, String bateau, String lotDuJour) {
        try {
            // Étape 1 : Vérifier si le bateau existe en fonction de son nom
            String bateauQuery = "SELECT id FROM bateau WHERE nom = ?";
            PreparedStatement bateauStatement = conn.prepareStatement(bateauQuery);
            bateauStatement.setString(1, bateau);
            ResultSet bateauResultSet = bateauStatement.executeQuery();

            if (bateauResultSet.next()) {
                // Le bateau existe, vous pouvez continuer
                int bateauId = bateauResultSet.getInt("id");
                System.out.println("Étape 1 réussie. Bateau trouvé avec l'ID : " + bateauId);

                // Étape 2 : Vérifier si le lot existe en fonction du bateau et d'autres conditions (par exemple, la date)
                String lotQuery = "SELECT id FROM lot WHERE idBateau = ? AND datePeche = ?";
                PreparedStatement lotStatement = conn.prepareStatement(lotQuery);
                lotStatement.setInt(1, bateauId);
                lotStatement.setDate(2, Date.valueOf(lotDuJour)); // Supposons que lotDuJour est une chaîne de date au format "yyyy-MM-dd"

                ResultSet lotResultSet = lotStatement.executeQuery();

                if (lotResultSet.next()) {
                    // Le lot existe, vous pouvez continuer
                    int lotId = lotResultSet.getInt("id");
                    System.out.println("Étape 2 réussie. Lot trouvé avec l'ID : " + lotId);

                    // Étape 3 : Vérifier si le bac existe en fonction du lot et d'autres conditions
                    String bacQuery = "SELECT id FROM bac WHERE IdLot = ?";
                    PreparedStatement bacStatement = conn.prepareStatement(bacQuery);
                    bacStatement.setInt(1, lotId);
                    ResultSet bacResultSet = bacStatement.executeQuery();

                    if (bacResultSet.next()) {
                        // Le bac existe
                        System.out.println("Étape 3 réussie. Bac trouvé.");
                        return true;
                    } else {
                        System.out.println("Étape 3 échouée. Bac non trouvé.");
                    }
                } else {
                    System.out.println("Étape 2 échouée. Lot non trouvé.");
                }
            } else {
                System.out.println("Étape 1 échouée. Bateau non trouvé.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.out.println("Erreur de conversion de date : " + e.getMessage());
        }
        return false;
    }




}
