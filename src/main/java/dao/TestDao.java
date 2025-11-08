package dao;

public class TestDao {

    public static void main(String[] args) {
        System.out.println("Démarrage du test...");
        
        // Cela va charger SingletonConnection et déclencher Flyway
        java.sql.Connection conn = SingletonConnection.getConnection();
        
        System.out.println("Connexion : " + (conn != null ? "OK ✅" : "ÉCHEC ❌"));
    }
}