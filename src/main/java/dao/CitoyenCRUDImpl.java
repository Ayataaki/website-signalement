package dao;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import metier.Citoyen;
import utils.PasswordHashUtil;

public class CitoyenCRUDImpl implements ICitoyenCRUD{
	
	private Connection connection;

	public CitoyenCRUDImpl() {
		this.connection = SingletonConnection.getConnection();
	}

	@Override
	public void createCitoyen(Citoyen citoyen) {

		String sql = "INSERT INTO CITOYEN (NOM, PRENOM, NOM_UTILISATEUR, CIN, LIEU_NAISSANCE, TELEPHONE,"
				+ "EMAIL, EMAIL_AUTH, DATE_NAISSANCE, MOT_DE_PASSE, DATE_CREATION, ID_REGION) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,? ,?)";
		try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, citoyen.getNom());
			ps.setString(2, citoyen.getPrenom());
			citoyen.setNomUtilisateur(citoyen.getNom() + "." + citoyen.getPrenom());
			ps.setString(3, citoyen.getNomUtilisateur());
			ps.setString(4, citoyen.getCin());
			ps.setString(5, citoyen.getLieuNaissance());
			ps.setString(6, citoyen.getTelephone());
			ps.setString(7, citoyen.getEmail());
			citoyen.setEmailAuth(citoyen.getCin() + "@municipal.ma");
			ps.setString(8, citoyen.getEmailAuth());
			ps.setDate(9, new java.sql.Date(citoyen.getDateNaissance().getTime()));
			
			String hashedPassword = PasswordHashUtil.hashPassword(citoyen.getMotDePasse());
            ps.setString(10, hashedPassword);
            
			//ps.setDate(11, new java.sql.Date(citoyen.getDateCreation().getTime()));
			
			if (citoyen.getDateCreation() == null) {
                ps.setDate(11, new java.sql.Date(System.currentTimeMillis()));
            } else {
                ps.setDate(11, new java.sql.Date(citoyen.getDateCreation().getTime()));
            }
			
			if (citoyen.getIdRegion() != null) {
				ps.setLong(12, citoyen.getIdRegion());
			} else {
				ps.setNull(12, Types.BIGINT);
			}

			ps.executeUpdate();

			// Get generated ID
			try (ResultSet rs = ps.getGeneratedKeys()) {
				if (rs.next()) {
					citoyen.setIdCitoyen(rs.getLong(1));
				}
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("Erreur lors de la création du citoyen", ex);
		}
		
	}

	@Override
	public void deleteCitoyen(Long id) {
		
		String sql = "DELETE FROM CITOYEN WHERE ID_CITOYEN = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Erreur lors de la suppression du client", ex);
        }
		
	}
	

	@Override
	public Citoyen getById(Long id) {
		String sql = "SELECT * FROM CITOYEN WHERE ID_CITOYEN = ?";
		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setLong(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					Citoyen c = new Citoyen();
					c.setIdCitoyen(rs.getLong("ID_CITOYEN"));
					c.setNom(rs.getString("NOM"));
					c.setPrenom(rs.getString("PRENOM"));
					c.setCin(rs.getString("CIN"));
					c.setLieuNaissance(rs.getString("LIEU_NAISSANCE"));
					c.setTelephone(rs.getString("TELEPHONE"));
					c.setEmail(rs.getString("EMAIL"));
					c.setEmailAuth(rs.getString("EMAIL_AUTH"));
					c.setNomUtilisateur(rs.getString("NOM_UTILISATEUR"));
					c.setMotDePasse(rs.getString("MOT_DE_PASSE"));
					c.setDateNaissance(rs.getDate("DATE_NAISSANCE"));
					c.setDateCreation(rs.getDate("DATE_CREATION"));
					c.setIdRegion(rs.getLong("ID_REGION"));
					return c;
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("Erreur lors de la récupération du citoyen", ex);
		}
		return null;
		}

	@Override
	public Citoyen updateCitoyen(Citoyen citoyen) {
			
     String sql = "UPDATE CITOYEN SET NOM = ?, PRENOM = ?, CIN = ?, LIEU_NAISSANCE = ?, "
        		+ "TELEPHONE = ?, EMAIL = ?, EMAIL_AUTH = ?, NOM_UTILISATEUR= ?, DATE_NAISSANCE = ?,"
        		+ " MOT_DE_PASSE = ?, ID_REGION = ? WHERE ID_CITOYEN = ?";
        
     try (PreparedStatement ps = connection.prepareStatement(sql)) {
    	 	ps.setString(1, citoyen.getNom());
			ps.setString(2, citoyen.getPrenom());
			ps.setString(3, citoyen.getCin());
			ps.setString(4, citoyen.getLieuNaissance());
			ps.setString(5, citoyen.getTelephone());
			ps.setString(6, citoyen.getEmail());
			ps.setString(7, citoyen.getEmailAuth());
			ps.setString(8, citoyen.getNomUtilisateur());
			ps.setDate(9, new java.sql.Date(citoyen.getDateNaissance().getTime()));
//			String hashedPassword = PasswordHashUtil.hashPassword(citoyen.getMotDePasse());
//            ps.setString(10, hashedPassword);
			
			String motDePasse = citoyen.getMotDePasse();
	        
	        // Vérifier si le mot de passe est déjà haché (Base64 = 64 caractères pour notre algo)
	        if (motDePasse != null && motDePasse.length() == 64) {
	            // Déjà haché, utiliser tel quel
	            ps.setString(10, motDePasse);
	            System.out.println("⚠️ Mot de passe déjà haché, pas de re-hachage");
	        } else if (motDePasse != null && !motDePasse.isEmpty()) {
	            // Nouveau mot de passe en clair, le hacher
	            String hashedPassword = PasswordHashUtil.hashPassword(motDePasse);
	            ps.setString(10, hashedPassword);
	        } else {
	            // Pas de changement de mot de passe, récupérer l'ancien
	            Citoyen existing = getById((Long) citoyen.getIdCitoyen());
	            ps.setString(10, existing.getMotDePasse());
	        }
			
         if (citoyen.getIdRegion() != null) {
             ps.setLong(11, citoyen.getIdRegion());
         } else {
             ps.setNull(11, Types.BIGINT);
         }
         ps.setLong(12, citoyen.getIdCitoyen());

         ps.executeUpdate();
         return citoyen;

     } catch (SQLException ex) {
         ex.printStackTrace();
         throw new RuntimeException("Erreur lors de la mise à jour du citoyen", ex);
     }

	}


	
	@Override
	public List<Citoyen> getAll(){
		String sql = "SELECT * FROM CITOYEN";
        List<Citoyen> list = new ArrayList<>();
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Citoyen c = new Citoyen();
                c.setIdCitoyen(rs.getLong("ID_CITOYEN"));
                c.setNom(rs.getString("NOM"));
                c.setPrenom(rs.getString("PRENOM"));
                c.setCin(rs.getString("CIN"));
                c.setLieuNaissance(rs.getString("LIEU_NAISSANCE"));
                c.setTelephone(rs.getString("TELEPHONE"));
                c.setEmail(rs.getString("EMAIL"));
				c.setEmailAuth(rs.getString("EMAIL_AUTH"));
				c.setNomUtilisateur(rs.getString("NOM_UTILISATEUR"));
                c.setMotDePasse(rs.getString("MOT_DE_PASSE"));
                c.setDateNaissance(rs.getDate("DATE_NAISSANCE"));
                c.setDateCreation(rs.getDate("DATE_CREATION"));
                c.setIdRegion(rs.getLong("ID_REGION"));                
                list.add(c);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Erreur lors de la récupération des citoyens", ex);
        }
        return list;
	}

	@Override
	public List<Citoyen> getCitoyenByRegion(Long idRegion) {
	    String sql = "SELECT * FROM CITOYEN WHERE ID_REGION = ?";
	    List<Citoyen> list = new ArrayList<>();

	    try (PreparedStatement ps = connection.prepareStatement(sql)) {
	        ps.setLong(1, idRegion);
	        try (ResultSet rs = ps.executeQuery()) {
	            while (rs.next()) {
	                Citoyen c = new Citoyen();
	                c.setIdCitoyen(rs.getLong("ID_CITOYEN"));
	                c.setNom(rs.getString("NOM"));
	                c.setPrenom(rs.getString("PRENOM"));
	                c.setCin(rs.getString("CIN"));
	                c.setLieuNaissance(rs.getString("LIEU_NAISSANCE"));
	                c.setTelephone(rs.getString("TELEPHONE"));
	                c.setEmail(rs.getString("EMAIL"));
					c.setEmailAuth(rs.getString("EMAIL_AUTH"));
					c.setNomUtilisateur(rs.getString("NOM_UTILISATEUR"));
	                c.setMotDePasse(rs.getString("MOT_DE_PASSE"));
	                c.setDateNaissance(rs.getDate("DATE_NAISSANCE"));
	                c.setDateCreation(rs.getDate("DATE_CREATION"));
	                c.setIdRegion(rs.getLong("ID_REGION"));                
	                list.add(c);
	            }
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	        throw new RuntimeException("Erreur lors de la récupération des citoyens", ex);
	    }
	    return list;
	}

	@Override
	public Citoyen findByEmailAuth(String emailAuth) {

		String sql = "SELECT * FROM CITOYEN WHERE EMAIL_AUTH = ?";
		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setString(1, emailAuth);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					Citoyen c = new Citoyen();
					c.setIdCitoyen(rs.getLong("ID_CITOYEN"));
					c.setNom(rs.getString("NOM"));
					c.setPrenom(rs.getString("PRENOM"));
					c.setCin(rs.getString("CIN"));
					c.setLieuNaissance(rs.getString("LIEU_NAISSANCE"));
					c.setTelephone(rs.getString("TELEPHONE"));
					c.setEmail(rs.getString("EMAIL"));
					c.setEmailAuth(rs.getString("EMAIL_AUTH"));
					c.setNomUtilisateur(rs.getString("NOM_UTILISATEUR"));
					c.setMotDePasse(rs.getString("MOT_DE_PASSE"));
					c.setDateNaissance(rs.getDate("DATE_NAISSANCE"));
					c.setDateCreation(rs.getDate("DATE_CREATION"));
					c.setIdRegion(rs.getLong("ID_REGION"));
					return c;
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("Erreur lors de la récupération du citoyen", ex);
		}
		return null;
		
	}

	@Override
	public void updatePwd(String pwd,Long idCitoyen) {
		String sql = "UPDATE CITOYEN SET MOT_DE_PASSE = ? WHERE ID_CITOYEN = ?";
	    try (PreparedStatement ps = connection.prepareStatement(sql)) {

	    	// le mdp est hashé au niveau de la servlet, no need to stress
	        ps.setString(1, pwd);
	        ps.setLong(2, idCitoyen);
	        ps.executeUpdate();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	@Override
	public int countCitoyen() {
		String sql = "SELECT COUNT(*) FROM CITOYEN";
		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next())
					return rs.getInt(1);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("Erreur lors du calcul de total des citoyens", ex);
		}
		return 0;
	}

	@Override
	public List<Citoyen> getCitoyenByMunicipal(Long idMunicipal) {
		
		List<Citoyen> liste = new ArrayList<>();
		
		String sql = "SELECT c.ID_CITOYEN "
				+ "FROM MUNICIPAL m "
				+ "JOIN CITOYEN c "
				+ "ON m.ID_REGION = c.ID_REGION "
				+ "WHERE m.ID_MUNICIPAL = ?";
		
		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setLong(1, idMunicipal);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					liste.add(getById(rs.getLong(1)));
				}
				return liste;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("Erreur lors de la récupération du citoyen", ex);
		}		
	}

	@Override
	public int countCitoyenByMunicipal(Long idMunicipal) {
		List<Citoyen> liste = getCitoyenByMunicipal(idMunicipal);
		return liste.size();
	}
	
	

}
