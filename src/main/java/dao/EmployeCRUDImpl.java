package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import metier.Citoyen;
import metier.Employe;

public class EmployeCRUDImpl implements IEmployeCRUD{

	private Connection connection;

	public EmployeCRUDImpl() {
		this.connection = SingletonConnection.getConnection();
	}
//	CREATE TABLE IF NOT EXISTS EMPLOYE (
//		    ID_EMPLOYE BIGINT PRIMARY KEY AUTO_INCREMENT,
//		    NOM VARCHAR(255) NOT NULL,
//		    PRENOM VARCHAR(255) NOT NULL,
//		    CIN VARCHAR(20) NOT NULL,
//		    LIEU_NAISSANCE VARCHAR(255) NOT NULL,
//		    TELEPHONE VARCHAR(10) NOT NULL,
//		    EMAIL VARCHAR(255) NOT NULL,
//		    DATE_NAISSANCE DATE NOT NULL,
//		    DATE_CREATION TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
//		    ID_MUNICIPAL BIGINT,  -- ajout de la colonne pour la clé étrangère
//		    CONSTRAINT fk_employe_municipal
//		        FOREIGN KEY (ID_MUNICIPAL)
//		        REFERENCES MUNICIPAL(ID_MUNICIPAL)
//		        ON DELETE CASCADE
//		        ON UPDATE CASCADE
//		);

	
	@Override
	public void createEmploye(Employe employe) {

		String sql = "INSERT INTO EMPLOYE (NOM, PRENOM, CIN, LIEU_NAISSANCE, TELEPHONE,"
				+ "EMAIL, DATE_NAISSANCE, DATE_CREATION, ID_MUNICIPAL) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, employe.getNom());
			ps.setString(2, employe.getPrenom());
			ps.setString(3, employe.getCin());
			ps.setString(4, employe.getLieuNaissance());
			ps.setString(5, employe.getTelephone());
			ps.setString(6, employe.getEmail());
			ps.setDate(7, new java.sql.Date(employe.getDateNaissance().getTime()));
			ps.setDate(8, new java.sql.Date(employe.getDateCreation().getTime()));
			if (employe.getIdMunicipal() != null) {
				ps.setLong(9, employe.getIdMunicipal());
			} else {
				ps.setNull(9, Types.BIGINT);
			}

			ps.executeUpdate();

			// Get generated ID
			try (ResultSet rs = ps.getGeneratedKeys()) {
				if (rs.next()) {
					employe.setIdEmploye(rs.getLong(1));
				}
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("Erreur lors de la création de l'employé", ex);
		}
		
	}

	@Override
	public void deleteEmploye(int id) {

		String sql = "DELETE FROM EMPLOYE WHERE ID_EMPLOYE = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Erreur lors de la suppression de l'employé", ex);
        }

	}

	@Override
	public Employe updateEmploye(Employe employe) {

//		NOM, PRENOM, CIN, LIEU_NAISSANCE, TELEPHONE,"
//				+ "EMAIL, DATE_NAISSANCE, DATE_CREATION, ID_MUNICIPAL
		
		String sql = "UPDATE EMPLOYE SET NOM = ?, PRENOM = ?, CIN = ?, LIEU_NAISSANCE = ?, "
				+ "TELEPHONE = ?, EMAIL = ?, DATE_NAISSANCE = ?, DATE_CREATION = ?, ID_MUNICIPAL = ?"
				+ " WHERE ID_EMPLOYE = ?";

		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setString(1, employe.getNom());
			ps.setString(2, employe.getPrenom());
			ps.setString(3, employe.getCin());
			ps.setString(4, employe.getLieuNaissance());
			ps.setString(5, employe.getTelephone());
			ps.setString(6, employe.getEmail());
			ps.setDate(7, new java.sql.Date(employe.getDateNaissance().getTime()));
			ps.setDate(8, new java.sql.Date(employe.getDateCreation().getTime()));
			if (employe.getIdMunicipal() != null) {
				ps.setLong(9, employe.getIdMunicipal());
			} else {
				ps.setNull(9, Types.BIGINT);
			}

         ps.setLong(10, employe.getIdEmploye());

         ps.executeUpdate();
         return employe;

     } catch (SQLException ex) {
         ex.printStackTrace();
         throw new RuntimeException("Erreur lors de la mise à jour de l'employé", ex);
     }
		
	}

	@Override
	public Employe getById(int id) {
		
		String sql = "SELECT * FROM EMPLOYE WHERE ID_EMPLOYE = ?";
		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setLong(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					Employe e = new Employe();
					e.setIdEmploye(rs.getLong("ID_EMPLOYE"));
					e.setNom(rs.getString("NOM"));
					e.setPrenom(rs.getString("PRENOM"));
					e.setCin(rs.getString("CIN"));
					e.setLieuNaissance(rs.getString("LIEU_NAISSANCE"));
					e.setTelephone(rs.getString("TELEPHONE"));
					e.setEmail(rs.getString("EMAIL"));
					e.setDateNaissance(rs.getDate("DATE_NAISSANCE"));
					e.setDateCreation(rs.getDate("DATE_CREATION"));
					e.setIdMunicipal(rs.getLong("ID_MUNICIPAL"));
					return e;
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("Erreur lors de la récupération de l'employé", ex);
		}
		return null;
	}

}
