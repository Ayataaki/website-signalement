package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import metier.Signalement;
import metier.Statut;

public class SignalementCRUDImpl implements ISignalementCRUD{
	
	private Connection connection;

	public SignalementCRUDImpl() {
		this.connection = SingletonConnection.getConnection();
	}
	
	@Override
	public void createSignalement(Signalement signalement) {
		//DESIGNATION
		String sql = "INSERT INTO SIGNALEMENT (DESCRIPTION, LOCALISATION, DATE_CREATION,"
				+ " IMAGE_PATH, STATUT,"
				+ "COMMENTAIRE, ID_CITOYEN,DESIGNATION) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, signalement.getDescription());
			ps.setString(2, signalement.getLocalisation());
			//ps.setDate(3, new java.sql.Date(signalement.getDateCreation().getTime()));
			
			if (signalement.getDateCreation() == null) {
                ps.setDate(3, new java.sql.Date(System.currentTimeMillis()));
            } else {
                ps.setDate(3, new java.sql.Date(signalement.getDateCreation().getTime()));
            }
			
			ps.setString(4, signalement.getImagePath());
			ps.setString(5, signalement.getStatut().getLabel());
			ps.setString(6, signalement.getCommentaire());
			if (signalement.getIdCitoyen() != null) {
				ps.setLong(7, signalement.getIdCitoyen());
			} else {
				ps.setNull(7, Types.BIGINT);
			}
			ps.setString(8, signalement.getDesignation());
			ps.executeUpdate();

			// Get generated ID
			try (ResultSet rs = ps.getGeneratedKeys()) {
				if (rs.next()) {
					signalement.setIdSignalement(rs.getLong(1));
				}
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("Erreur lors de la création du signalement", ex);
		}
		
	}

	@Override
	public void deleteSignalement(Long id) {

		String sql = "DELETE FROM SIGNALEMENT WHERE ID_SIGNALEMENT = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Erreur lors de la suppression du signalement", ex);
        }
		
	}

	@Override
	public Signalement updateSignalement(Signalement signalement) {

		String sql = "UPDATE SIGNALEMENT SET DESCRIPTION = ?, LOCALISATION = ?, DATE_CREATION = ?, IMAGE_PATH = ?, "
		           + "STATUT = ?, COMMENTAIRE = ?, ID_CITOYEN = ?, DESIGNATION = ? WHERE ID_SIGNALEMENT = ?";

		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setString(1, signalement.getDescription());
			ps.setString(2, signalement.getLocalisation());
			ps.setDate(3, new java.sql.Date(signalement.getDateCreation().getTime()));
			ps.setString(4, signalement.getImagePath());
			ps.setString(5, signalement.getStatut().getLabel());
			ps.setString(6, signalement.getCommentaire());
			if (signalement.getIdCitoyen() != null) {
				ps.setLong(7, signalement.getIdCitoyen());
			} else {
				ps.setNull(7, Types.BIGINT);
			}			

			ps.setString(8, signalement.getDesignation());

			ps.setLong(9, signalement.getIdSignalement());

			ps.executeUpdate();

			return signalement;

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("Erreur lors de la mise à jour du signalement", ex);
		}
	}

	@Override
	public Signalement getById(Long id) {
		
		String sql = "SELECT * FROM SIGNALEMENT WHERE ID_SIGNALEMENT = ?";
		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setLong(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					Signalement s = new Signalement();
					s.setIdSignalement(rs.getLong("ID_SIGNALEMENT"));
					s.setDescription(rs.getString("DESCRIPTION"));
					s.setLocalisation(rs.getString("LOCALISATION"));
					s.setDateCreation(rs.getDate("DATE_CREATION"));
					s.setImagePath(rs.getString("IMAGE_PATH"));		
					s.setStatut(Statut.fromLabel(rs.getString("STATUT")));
					s.setCommentaire(rs.getString("COMMENTAIRE"));
					s.setDesignation(rs.getString("DESIGNATION"));
					long idCitoyen = rs.getLong("ID_CITOYEN");
					s.setIdCitoyen(rs.wasNull() ? null : idCitoyen);
					return s;
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("Erreur lors de la récupération du signalement", ex);
		}
		return null;
	}

	@Override
	public List<Signalement> getByIdCitoyen(Long idCitoyen) {
		
		String sql = "SELECT * FROM SIGNALEMENT WHERE ID_CITOYEN = ?";
        List<Signalement> list = new ArrayList<>();
		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setLong(1, idCitoyen);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					Signalement s = new Signalement();
					s.setIdSignalement(rs.getLong("ID_SIGNALEMENT"));
					s.setDescription(rs.getString("DESCRIPTION"));
					s.setLocalisation(rs.getString("LOCALISATION"));
					s.setDateCreation(rs.getDate("DATE_CREATION"));
					s.setImagePath(rs.getString("IMAGE_PATH"));		
					s.setStatut(Statut.fromLabel(rs.getString("STATUT")));
					s.setCommentaire(rs.getString("COMMENTAIRE"));
					s.setDesignation(rs.getString("DESIGNATION"));
					s.setIdCitoyen(rs.getLong("ID_CITOYEN"));
					list.add(s);
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("Erreur lors de la récupération du signalement", ex);
		}
		return list;
	}

	@Override
	public int getCountNewSignalementByCitoyen(Long idCitoyen) {

        List<Signalement> list =getByIdCitoyen(idCitoyen);
        int cpt = 0;
        for (Signalement s : list) {
			if(s.getStatut().getLabel().equals("new")) {
				cpt++;
			}
		}
		return cpt;
	}

	@Override
	public int getCountProcessingSignalementByCitoyen(Long idCitoyen) {
		List<Signalement> list =getByIdCitoyen(idCitoyen);
        int cpt = 0;
        for (Signalement s : list) {
			if(s.getStatut().getLabel().equals("processing")) {
				cpt++;
			}
		}
		return cpt;
	}

	@Override
	public int getCountFinishedSignalementByCitoyen(Long idCitoyen) {
		List<Signalement> list =getByIdCitoyen(idCitoyen);
        int cpt = 0;
        for (Signalement s : list) {
			if(s.getStatut().getLabel().equals("final")) {
				cpt++;
			}
		}
		return cpt;
	}

	@Override
	public List<Signalement> getAll() {
		String sql = "SELECT * FROM SIGNALEMENT";
        List<Signalement> list = new ArrayList<>();
		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					Signalement s = new Signalement();
					s.setIdSignalement(rs.getLong("ID_SIGNALEMENT"));
					s.setDescription(rs.getString("DESCRIPTION"));
					s.setLocalisation(rs.getString("LOCALISATION"));
					s.setDateCreation(rs.getDate("DATE_CREATION"));
					s.setImagePath(rs.getString("IMAGE_PATH"));		
					s.setStatut(Statut.fromLabel(rs.getString("STATUT")));
					s.setCommentaire(rs.getString("COMMENTAIRE"));
					s.setDesignation(rs.getString("DESIGNATION"));
					s.setIdCitoyen(rs.getLong("ID_CITOYEN"));
					list.add(s);
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("Erreur lors de la récupération du signalement", ex);
		}
		return list;
	}

	@Override
	public int countSignalement() {
		String sql = "SELECT COUNT(*) FROM SIGNALEMENT";
		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next())
					return rs.getInt(1);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("Erreur lors du calcul de total des signalements", ex);
		}
		return 0;
	}
	
	public double getResolutionRate() {
		String sql = "SELECT (SUM(CASE WHEN statut='FINAL' THEN 1 ELSE 0 END) * 100.0 / COUNT(*)) AS taux FROM SIGNALEMENT";
		try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
			if (rs.next()) {
				double taux = rs.getDouble("taux");
				return Math.round(taux * 100.0) / 100.0; // Arrondi à 2 chiffres
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<Signalement> getRecentReports(int limit) {
		List<Signalement> list = new ArrayList<>();
	    String sql = "SELECT * FROM SIGNALEMENT ORDER BY date_creation DESC LIMIT ?";
	    try (PreparedStatement ps = connection.prepareStatement(sql)) {
	        ps.setInt(1, limit);
	        ResultSet rs = ps.executeQuery();
	        while (rs.next()) {
	            Signalement s = new Signalement();
				s.setIdSignalement(rs.getLong("ID_SIGNALEMENT"));
				s.setDescription(rs.getString("DESCRIPTION"));
				s.setLocalisation(rs.getString("LOCALISATION"));
				s.setDateCreation(rs.getDate("DATE_CREATION"));
				s.setImagePath(rs.getString("IMAGE_PATH"));		
				s.setStatut(Statut.fromLabel(rs.getString("STATUT")));
				s.setCommentaire(rs.getString("COMMENTAIRE"));
				s.setDesignation(rs.getString("DESIGNATION"));
				s.setIdCitoyen(rs.getLong("ID_CITOYEN"));

	            list.add(s);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return list;
	}
	
	public Map<String, Integer> getMonthlyReportStats() {

		String sql = "SELECT MONTHNAME(MIN(DATE_CREATION)) AS mois, COUNT(*) AS total " 
	               + "FROM SIGNALEMENT "
	               + "GROUP BY MONTH(DATE_CREATION) "
	               + "ORDER BY MONTH(DATE_CREATION)";


		Map<String, Integer> result = new LinkedHashMap<>();

		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				String month = rs.getString("mois");
				int total = rs.getInt("total");
				result.put(month, total);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public int getCountNewSignalementByMunicipal(Long idMunicipal) {
		List<Signalement> liste = new ArrayList<>();
		liste = getSignalementByMunicipal(idMunicipal);
		int count = 0;
		for(Signalement s: liste) {
			if(s.getStatut().getLabel().equals("new")) {
				count++;
			}
		}
		return count;
	}

	@Override
	public int getCountProcessingSignalementByMunicipal(Long idMunicipal) {
		List<Signalement> liste = new ArrayList<>();
		liste = getSignalementByMunicipal(idMunicipal);
		int count = 0;
		for(Signalement s: liste) {
			if(s.getStatut().getLabel().equals("processing")) {
				count++;
			}
		}
		return count;
	}

	@Override
	public int getCountFinishedSignalementByMunicipal(Long idMunicipal) {
		List<Signalement> liste = new ArrayList<>();
		liste = getSignalementByMunicipal(idMunicipal);
		int count = 0;
		for(Signalement s: liste) {
			if(s.getStatut().getLabel().equals("final")) {
				count++;
			}
		}
		return count;
	}

	@Override
	public void updateStatut(Long idSignalement, Statut statut) {
		String sql = "UPDATE SIGNALEMENT SET STATUT = ? WHERE ID_SIGNALEMENT = ?";

		try (PreparedStatement ps = connection.prepareStatement(sql)) {

			ps.setString(1, statut.getLabel()); // "new", "processing", "final"
			ps.setLong(2, idSignalement);

			ps.executeUpdate(); 
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<Signalement> rechercherSignalements(String keyword) {
	    List<Signalement> liste = new ArrayList<>();
	    
	    String sql = "SELECT * "+
	    		"FROM SIGNALEMENT "+
	    		"WHERE DESIGNATION LIKE ? OR DESCRIPTION LIKE ? "
	    		+ "OR LOCALISATION LIKE ?";
	    try (PreparedStatement ps = connection.prepareStatement(sql)) {
	        ps.setString(1, "%" + keyword + "%");
	        ps.setString(2, "%" + keyword + "%");
	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	            Signalement s = new Signalement();
	            s.setIdSignalement(rs.getLong("ID_SIGNALEMENT"));
                s.setDesignation(rs.getString("DESIGNATION"));
                s.setDescription(rs.getString("DESCRIPTION"));
                s.setLocalisation(rs.getString("LOCALISATION"));
                s.setCommentaire(rs.getString("COMMENTAIRE"));
                s.setImagePath(rs.getString("IMAGE_PATH"));	
                s.setIdCitoyen(rs.getLong("ID_CITOYEN"));
                s.setDateCreation(rs.getTimestamp("DATE_CREATION"));
                liste.add(s);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return liste;
	}

	@Override
	public List<Signalement> getSignalementByMunicipal(Long idMunicipal) {
		
		List<Signalement> liste = new ArrayList<>();
	    
	    String sql = "SELECT s.ID_SIGNALEMENT "
	    		+ "FROM MUNICIPAL m "
	    		+ "JOIN EMPLOYE e ON m.ID_MUNICIPAL = e.ID_MUNICIPAL "
	    		+ "JOIN CITOYEN c ON m.ID_REGION = c.ID_REGION "
	    		+ "JOIN SIGNALEMENT s ON s.ID_CITOYEN = c.ID_CITOYEN "
	    		+ "WHERE m.ID_MUNICIPAL = ?";
	    
	    try (PreparedStatement ps = connection.prepareStatement(sql)) {
	    	
	        ps.setLong(1,idMunicipal);
	        
	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {	            
	        	liste.add(getById(rs.getLong("ID_SIGNALEMENT")));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return liste;
	}

	@Override
	public int countSignalementByMunicipal(Long idMunicipal) {
		
		String sql = "SELECT count(s.ID_SIGNALEMENT) "
	    		+ "FROM MUNICIPAL m "
	    		+ "JOIN EMPLOYE e ON m.ID_MUNICIPAL = e.ID_MUNICIPAL "
	    		+ "JOIN CITOYEN c ON m.ID_REGION = c.ID_REGION "
	    		+ "JOIN SIGNALEMENT s ON s.ID_CITOYEN = c.ID_CITOYEN "
	    		+ "WHERE m.ID_MUNICIPAL = ?";
		
		try (PreparedStatement ps = connection.prepareStatement(sql)) {
	        ps.setLong(1,idMunicipal);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next())
					return rs.getInt(1);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("Erreur lors du calcul de total des signalements", ex);
		}
		return 0;
	}

	@Override
	public List<Signalement> getRecentReportsByMunicipal(Long idMunicipal,int limit) {
		// TODO Auto-generated method stub
		return null;
	}

}
