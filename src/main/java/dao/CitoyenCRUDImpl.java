package dao;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import metier.Citoyen;

public class CitoyenCRUDImpl implements ICitoyenCRUD{
	
	private Connection connection;

	public CitoyenCRUDImpl() {
		this.connection = SingletonConnection.getConnection();
	}

	@Override
	public void createCitoyen(Citoyen citoyen) {

		String sql = "INSERT INTO CITOYEN (NOM, PRENOM, CIN, LIEU_NAISSANCE, TELEPHONE,"
				+ "EMAIL, DATE_NAISSANCE, MOT_DE_PASSE, DATE_CREATION, ID_REGION) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, citoyen.getNom());
			ps.setString(2, citoyen.getPrenom());
			ps.setString(3, citoyen.getCin());
			ps.setString(4, citoyen.getLieuNaissance());
			ps.setString(5, citoyen.getTelephone());
			ps.setString(6, citoyen.getEmail());
			ps.setDate(7, new java.sql.Date(citoyen.getDateNaissance().getTime()));
			ps.setString(8, citoyen.getMotDePasse());
			ps.setDate(9, new java.sql.Date(citoyen.getDateCreation().getTime()));
			if (citoyen.getIdRegion() != null) {
				ps.setLong(10, citoyen.getIdRegion());
			} else {
				ps.setNull(10, Types.BIGINT);
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
	public void deleteCitoyen(int id) {
		
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
	public Citoyen updateCitoyen(Citoyen citoyen) {
			
     String sql = "UPDATE CITOYEN SET NOM = ?, PRENOM = ?, CIN = ?, LIEU_NAISSANCE = ?, "
        		+ "TELEPHONE = ?, EMAIL = ?, DATE_NAISSANCE = ?, MOT_DE_PASSE = ?, DATE_CREATION = ?,"
        		+ " ID_REGION = ? WHERE ID_CITOYEN = ?";
        
     try (PreparedStatement ps = connection.prepareStatement(sql)) {
    	 	ps.setString(1, citoyen.getNom());
			ps.setString(2, citoyen.getPrenom());
			ps.setString(3, citoyen.getCin());
			ps.setString(4, citoyen.getLieuNaissance());
			ps.setString(5, citoyen.getTelephone());
			ps.setString(6, citoyen.getEmail());
			ps.setDate(7, new java.sql.Date(citoyen.getDateNaissance().getTime()));
			ps.setString(8, citoyen.getMotDePasse());
			ps.setDate(9, new java.sql.Date(citoyen.getDateCreation().getTime()));
         if (citoyen.getIdRegion() != null) {
             ps.setLong(10, citoyen.getIdRegion());
         } else {
             ps.setNull(10, Types.BIGINT);
         }
         ps.setLong(11, citoyen.getIdCitoyen());

         ps.executeUpdate();
         return citoyen;

     } catch (SQLException ex) {
         ex.printStackTrace();
         throw new RuntimeException("Erreur lors de la mise à jour du citoyen", ex);
     }

	}

	@Override
	public Citoyen getById(int id) {
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

	
}
