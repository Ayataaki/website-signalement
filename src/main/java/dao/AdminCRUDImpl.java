package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import metier.Administrateur;
import utils.PasswordHashUtil;

public class AdminCRUDImpl implements IAdminCRUD {

	private Connection connection;

	public AdminCRUDImpl() {
		this.connection = SingletonConnection.getConnection();
	}
	
	@Override
	public void createAdmin(Administrateur admin) {
		
		String sql = "INSERT INTO ADMINISTRATEUR (NOM, PRENOM, NOM_UTILISATEUR, CIN, LIEU_NAISSANCE, TELEPHONE,"
				+ "EMAIL, EMAIL_AUTH, DATE_NAISSANCE, MOT_DE_PASSE, DATE_CREATION) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, admin.getNom());
			ps.setString(2, admin.getPrenom());
			admin.setNomUtilisateur(admin.getNom() + "." + admin.getPrenom());
			ps.setString(3, admin.getNomUtilisateur());
			ps.setString(4, admin.getCin());
			ps.setString(5, admin.getLieuNaissance());
			ps.setString(6, admin.getTelephone());
			ps.setString(7, admin.getEmail());
			admin.setEmailAuth(admin.getCin() + "@service-municipal.ma");
			ps.setString(8, admin.getEmailAuth());
			ps.setDate(9, new java.sql.Date(admin.getDateNaissance().getTime()));
			String hashedPassword = PasswordHashUtil.hashPassword(admin.getMotDePasse());
            ps.setString(10, hashedPassword);
			//ps.setDate(11, new java.sql.Date(admin.getDateCreation().getTime()));
			
			if (admin.getDateCreation() == null) {
                ps.setDate(11, new java.sql.Date(System.currentTimeMillis()));
            } else {
                ps.setDate(11, new java.sql.Date(admin.getDateCreation().getTime()));
            }

			ps.executeUpdate();

			// Get generated ID
			try (ResultSet rs = ps.getGeneratedKeys()) {
				if (rs.next()) {
					admin.setIdAdmin(rs.getLong(1));
				}
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("Erreur lors de la création de l'admin", ex);
		}

		
	}

	@Override
	public void deleteAdmin(int id) {

		String sql = "DELETE FROM ADMINISTRATEUR WHERE ID_ADMIN = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Erreur lors de la suppression de l'admin", ex);
        }

	}

	@Override
	public Administrateur updateAdmin(Administrateur admin) {

		String sql = "UPDATE ADMINISTRATEUR SET NOM = ?, PRENOM = ?, CIN = ?, LIEU_NAISSANCE = ?, "
        		+ "TELEPHONE = ?, EMAIL = ?, EMAIL_AUTH = ?, NOM_UTILISATEUR= ?, DATE_NAISSANCE = ?,"
        		+ " DATE_CREATION = ? WHERE ID_ADMIN = ?";
        
     try (PreparedStatement ps = connection.prepareStatement(sql)) {
    	 	ps.setString(1, admin.getNom());
			ps.setString(2, admin.getPrenom());
			ps.setString(3, admin.getCin());
			ps.setString(4, admin.getLieuNaissance());
			ps.setString(5, admin.getTelephone());
			ps.setString(6, admin.getEmail());
			ps.setString(7, admin.getEmailAuth());
			ps.setString(8, admin.getNomUtilisateur());
			ps.setDate(9, new java.sql.Date(admin.getDateNaissance().getTime()));
			ps.setDate(10, new java.sql.Date(admin.getDateCreation().getTime()));
         
         ps.setLong(11, admin.getIdAdmin());

         ps.executeUpdate();
         return admin;

     } catch (SQLException ex) {
         ex.printStackTrace();
         throw new RuntimeException("Erreur lors de la mise à jour de l'admin", ex);
     }
		
	}

	@Override
	public Administrateur getById(Long id) {

		String sql = "SELECT * FROM ADMINISTRATEUR WHERE ID_ADMIN = ?";
		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setLong(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					Administrateur a = new Administrateur();
					a.setIdAdmin(rs.getLong("ID_ADMIN"));
					a.setNom(rs.getString("NOM"));
					a.setPrenom(rs.getString("PRENOM"));
					a.setCin(rs.getString("CIN"));
					a.setLieuNaissance(rs.getString("LIEU_NAISSANCE"));
					a.setTelephone(rs.getString("TELEPHONE"));
					a.setEmail(rs.getString("EMAIL"));
					a.setEmailAuth(rs.getString("EMAIL_AUTH"));
					a.setNomUtilisateur(rs.getString("NOM_UTILISATEUR"));
					a.setMotDePasse(rs.getString("MOT_DE_PASSE"));
					a.setDateNaissance(rs.getDate("DATE_NAISSANCE"));
					a.setDateCreation(rs.getDate("DATE_CREATION"));
					return a;
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("Erreur lors de la récupération de l'admin", ex);
		}
		return null;

	}

	@Override
	public List<Administrateur> getAll() {
		String sql = "SELECT * FROM ADMINISTRATEUR";
        List<Administrateur> list = new ArrayList<>();
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
        	while (rs.next()) {
                Administrateur a = new Administrateur();
                a.setIdAdmin(rs.getLong("ID_ADMIN"));
                a.setNom(rs.getString("NOM"));
                a.setPrenom(rs.getString("PRENOM"));
                a.setCin(rs.getString("CIN"));
                a.setLieuNaissance(rs.getString("LIEU_NAISSANCE"));
                a.setTelephone(rs.getString("TELEPHONE"));
                a.setEmail(rs.getString("EMAIL"));
				a.setEmailAuth(rs.getString("EMAIL_AUTH"));
				a.setNomUtilisateur(rs.getString("NOM_UTILISATEUR"));
                a.setMotDePasse(rs.getString("MOT_DE_PASSE"));
                a.setDateNaissance(rs.getDate("DATE_NAISSANCE"));
                a.setDateCreation(rs.getDate("DATE_CREATION"));     
                list.add(a);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Erreur lors de la récupération des administrateurs", ex);
        }
        return list;
	}

	@Override
	public Administrateur findByEmailAuth(String emailAuth) {

		String sql = "SELECT * FROM ADMINISTRATEUR WHERE EMAIL_AUTH = ?";
		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setString(1, emailAuth);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					Administrateur a = new Administrateur();
					a.setIdAdmin(rs.getLong("ID_ADMIN"));
					a.setNom(rs.getString("NOM"));
					a.setPrenom(rs.getString("PRENOM"));
					a.setCin(rs.getString("CIN"));
					a.setLieuNaissance(rs.getString("LIEU_NAISSANCE"));
					a.setTelephone(rs.getString("TELEPHONE"));
					a.setEmail(rs.getString("EMAIL"));
					a.setEmailAuth(rs.getString("EMAIL_AUTH"));
					a.setNomUtilisateur(rs.getString("NOM_UTILISATEUR"));
					a.setMotDePasse(rs.getString("MOT_DE_PASSE"));
					a.setDateNaissance(rs.getDate("DATE_NAISSANCE"));
					a.setDateCreation(rs.getDate("DATE_CREATION"));
					return a;
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("Erreur lors de la récupération de l'admin", ex);
		}
		return null;

	}

	public void updatePwd(String pwd, Long idAdmin) {
	    String sql = "UPDATE ADMINISTRATEUR SET MOT_DE_PASSE = ? WHERE ID_ADMIN = ?";
	    try (PreparedStatement ps = connection.prepareStatement(sql)) {
	        ps.setString(1, pwd);
	        ps.setLong(2, idAdmin);
	        ps.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}


	

}
