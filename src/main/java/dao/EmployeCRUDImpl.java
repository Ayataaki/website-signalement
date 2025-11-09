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

	
	@Override
	public void createEmploye(Employe employe) {

		String sql = "INSERT INTO EMPLOYE (NOM, PRENOM, NOM_UTILISATEUR, CIN, LIEU_NAISSANCE, TELEPHONE,"
				+ "EMAIL, EMAIL_AUTH, MOT_DE_PASSE, DATE_NAISSANCE, DATE_CREATION, ID_MUNICIPAL, ADMIN_PRIVILEGE) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, employe.getNom());
			ps.setString(2, employe.getPrenom());
			employe.setNomUtilisateur(employe.getNom() + "." + employe.getPrenom());
			ps.setString(3, employe.getNomUtilisateur());
			ps.setString(4, employe.getCin());
			ps.setString(5, employe.getLieuNaissance());
			ps.setString(6, employe.getTelephone());
			ps.setString(7, employe.getEmail());
			employe.setEmailAuth(employe.getCin() + "@service-municipal.ma");
			ps.setString(8, employe.getEmailAuth());
			ps.setString(9, employe.getMotDePasse());
			ps.setDate(10, new java.sql.Date(employe.getDateNaissance().getTime()));
			ps.setDate(11, new java.sql.Date(employe.getDateCreation().getTime()));
			if (employe.getIdMunicipal() != null) {
				ps.setLong(12, employe.getIdMunicipal());
			} else {
				ps.setNull(12, Types.BIGINT);
			}
			ps.setBoolean(13, employe.isAdminPriv());
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
		
		String sql = "UPDATE EMPLOYE SET NOM = ?, PRENOM = ?, NOM_UTILISATEUR = ?, CIN = ?, LIEU_NAISSANCE = ?, "
				+ "TELEPHONE = ?, EMAIL = ?, EMAIL_AUTH = ?, MOT_DE_PASSE = ?, DATE_NAISSANCE = ?, DATE_CREATION = ?,"
				+ " ID_MUNICIPAL = ?, ADMIN_PRIVILEGE = ? "
				+ " WHERE ID_EMPLOYE = ?";

		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			
			
			ps.setString(1, employe.getNom());
			ps.setString(2, employe.getPrenom());
			employe.setNomUtilisateur(employe.getNom() + "." + employe.getPrenom());
			ps.setString(3, employe.getNomUtilisateur());
			ps.setString(4, employe.getCin());
			ps.setString(5, employe.getLieuNaissance());
			ps.setString(6, employe.getTelephone());
			ps.setString(7, employe.getEmail());
			employe.setEmailAuth(employe.getCin() + "@service-municipal.ma");
			ps.setString(8, employe.getEmailAuth());
			ps.setString(9, employe.getMotDePasse());
			ps.setDate(10, new java.sql.Date(employe.getDateNaissance().getTime()));
			ps.setDate(11, new java.sql.Date(employe.getDateCreation().getTime()));
			if (employe.getIdMunicipal() != null) {
				ps.setLong(12, employe.getIdMunicipal());
			} else {
				ps.setNull(12, Types.BIGINT);
			}
			ps.setBoolean(13, employe.isAdminPriv());
			
         ps.setLong(14, employe.getIdEmploye());

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
					e.setNomUtilisateur(rs.getString("NOM_UTILISATEUR"));
					e.setAdminPriv(rs.getBoolean("ADMIN_PRIVILEGE"));
					e.setEmailAuth(rs.getString("EMAIL_AUTH"));
					e.setMotDePasse(rs.getString("MOT_DE_PASSE"));
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
