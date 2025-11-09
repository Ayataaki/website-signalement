package dao;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.sql.Connection;
import java.sql.DriverManager;
import org.flywaydb.core.Flyway;

public class SingletonConnection {
	
	private static Connection connection;
    
	
	//On met la connection dans un bloc static. 
	//En effet, ceci garantit d'avoir une seule connection, lors de chargement de la classe
    static {
    try {   	

        // Charge le driver JDBC MySQL pour établir la connexion
    	// Ce driver doit etre charger avant le travail avec flyway
        Class.forName("com.mysql.cj.jdbc.Driver");
        
        // Crée un objet Properties pour lire les paramètres de configuration
        Properties props = new Properties();

        // Charge le fichier db.properties depuis le classpath (webapp/WEB-INF/config)
        InputStream in = SingletonConnection.class.getClassLoader().
                            getResourceAsStream("db.properties");
        
        // Remplit l'objet Properties avec les valeurs du fichier
        props.load(in);

        // Récupère les paramètres de connexion depuis le fichier
        String url = props.getProperty("db.url");       
        String user = props.getProperty("db.user");     
        String password = props.getProperty("db.password"); 
        
        // Migré la BDD
        migrateDatabase(url,user,password);


        // Crée la connexion à la base de données avec les paramètres récupérés
        connection = DriverManager.getConnection(url, user, password);

    } catch (Exception e) {
        e.printStackTrace();
        throw new RuntimeException("Erreur de connexion à la base de données", e);
    }
}

//    private static void migrateDatabase(String URL,String USER,String PASSWORD) {
//        System.out.println("Migration Flyway en cours");
//        Flyway flyway = Flyway.configure()
//            .dataSource(URL, USER, PASSWORD)
//            .locations("classpath:db/migration")
//            .load();
//     
//        // Repair failed migrations
//        // Si une migration est faite avec erreur et après modif dans les fichiers 
//        // principaux Vy__yy, tu dois exécuter cette ligne
//        flyway.repair();
//
//        //flyway.clean();
//        
//        flyway.migrate();
//        System.out.println("Migration Flyway terminée !");
//    }

    
    private static void migrateDatabase(String URL,String USER,String PASSWORD) {
        try {
            System.out.println("Migration Flyway en cours");
            Flyway flyway = Flyway.configure()
                .dataSource(URL, USER, PASSWORD)
                .cleanDisabled(false)
                .outOfOrder(true)  // ← AJOUTEZ CETTE LIGNE
                .load();
            
            flyway.repair();
            //flyway.clean();
            flyway.migrate();
            
            System.out.println("Migration réussie");
        } catch (Exception e) {
            throw new RuntimeException("Erreur de connexion à la base de données", e);
        }
    }
    
	public static Connection getConnection() {
		return connection;
	}
    
}
