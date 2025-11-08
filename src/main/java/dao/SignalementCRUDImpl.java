package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import metier.Employe;
import metier.Signalement;
import metier.Statut;

public class SignalementCRUDImpl implements ISignalementCRUD{
	
	private Connection connection;

	public SignalementCRUDImpl() {
		this.connection = SingletonConnection.getConnection();
	}

//	CREATE TABLE IF NOT EXISTS SIGNALEMENT (
//		    ID_SIGNALEMENT BIGINT PRIMARY KEY AUTO_INCREMENT,
//		    DESCRIPTION VARCHAR(255) NOT NULL,
//		    LOCALISATION VARCHAR(255) NOT NULL,
//		    DATE_CREATION TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
//		    IMAGE_PATH VARCHAR(255) NOT NULL,
//		    STATUT ENUM('new', 'processing', 'final') NOT NULL,
//		    COMMENTAIRE VARCHAR(255),
//		    ID_CITOYEN BIGINT,
//		    FOREIGN KEY (ID_CITOYEN) REFERENCES CITOYEN(ID_CITOYEN)
//		        ON DELETE CASCADE
//		        ON UPDATE CASCADE
//		);

	
	@Override
	public void createSignalement(Signalement signalement) {

		String sql = "INSERT INTO SIGNALEMENT (DESCRIPTION, LOCALISATION, DATE_CREATION,"
				+ " IMAGE_PATH, STATUT,"
				+ "COMMENTAIRE, ID_CITOYEN) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
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
	public void deleteSignalement(int id) {

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

//		DESCRIPTION, LOCALISATION, DATE_CREATION,"
//				+ " IMAGE_PATH, STATUT,"
//				+ "COMMENTAIRE, ID_CITOYEN

		String sql = "UPDATE SIGNALEMENT SET DESCRIPTION = ?, LOCALISATION = ?, DATE_CREATION = ?, IMAGE_PATH = ?, "
		           + "STATUT = ?, COMMENTAIRE = ?, ID_CITOYEN = ? WHERE ID_SIGNALEMENT = ?";

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

			ps.setLong(8, signalement.getIdSignalement());

			ps.executeUpdate();

			return signalement;

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("Erreur lors de la mise à jour du signalement", ex);
		}
	}

	@Override
	public Signalement getById(int id) {
		
//		DESCRIPTION, LOCALISATION, DATE_CREATION,"
//		+ " IMAGE_PATH, STATUT,"
//		+ "COMMENTAIRE, ID_CITOYEN

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

}
