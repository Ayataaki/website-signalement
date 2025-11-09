package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import metier.Technicien;

public class TechnicienCRUDImpl implements ITechnicienCRUD {

    private Connection connection;

    public TechnicienCRUDImpl() {
        this.connection = SingletonConnection.getConnection();
    }
    


    @Override
    public void createTechnicien(Technicien t) {
        String sql = "INSERT INTO TECHNICIEN (NOM, PRENOM, CIN, LIEU_NAISSANCE, TELEPHONE, EMAIL, DATE_NAISSANCE,"
                   + " SPECIALITE, COMPETENCE, DISPONIBILITE, DATE_CREATION, NOM_UTILISATEUR, EMAIL_AUTH, MOT_DE_PASSE )"
                   + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, t.getNom());
            ps.setString(2, t.getPrenom());
            ps.setString(3, t.getCin());
            ps.setString(4, t.getLieuNaissance());
            ps.setString(5, t.getTelephone());
            ps.setString(6, t.getEmail());
            ps.setDate(7, new java.sql.Date(t.getDateNaissance().getTime()));
            ps.setString(8, t.getSpecialite());
            ps.setString(9, t.getCompetence());
            ps.setBoolean(10, t.getDisponibilite() != null ? t.getDisponibilite() : true);
            ps.setDate(11, new java.sql.Date(t.getDateCreation().getTime()));

			t.setNomUtilisateur(t.getNom() + "." + t.getPrenom());
			ps.setString(12, t.getNomUtilisateur());
			t.setEmailAuth(t.getCin() + "@service-municipal.ma");
			ps.setString(13, t.getEmailAuth());
			ps.setString(14, t.getMotDePasse());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    t.setIdTechnicien(rs.getLong(1));
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Erreur lors de la création du technicien", ex);
        }
    }

    @Override
    public Technicien getById(int id) {
        String sql = "SELECT * FROM TECHNICIEN WHERE ID_TECHNICIEN = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Technicien t = new Technicien();
                    t.setIdTechnicien(rs.getLong("ID_TECHNICIEN"));
                    t.setNom(rs.getString("NOM"));
                    t.setPrenom(rs.getString("PRENOM"));
                    t.setCin(rs.getString("CIN"));
                    t.setLieuNaissance(rs.getString("LIEU_NAISSANCE"));
                    t.setTelephone(rs.getString("TELEPHONE"));
                    t.setEmail(rs.getString("EMAIL"));
                    t.setDateNaissance(rs.getDate("DATE_NAISSANCE"));
                    t.setSpecialite(rs.getString("SPECIALITE"));
                    t.setCompetence(rs.getString("COMPETENCE"));
                    t.setDisponibilite(rs.getBoolean("DISPONIBILITE"));
                    t.setDateCreation(rs.getDate("DATE_CREATION"));
                    t.setNomUtilisateur(rs.getString("NOM_UTILISATEUR"));
                    t.setEmailAuth(rs.getString("EMAIL_AUTH"));
                    t.setMotDePasse(rs.getString("MOT_DE_PASSE"));
                    return t;
                }
                
                

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Erreur lors de la récupération du technicien", ex);
        }
        return null;
    }

	@Override
	public Technicien updateTechnicien(Technicien t) {
		String sql = "UPDATE TECHNICIEN SET NOM = ?, PRENOM = ?, CIN = ?, LIEU_NAISSANCE = ?, TELEPHONE = ?,"
				+ " EMAIL = ?, DATE_NAISSANCE = ?, SPECIALITE = ?, COMPETENCE = ?,"
				+ " DISPONIBILITE = ?, DATE_CREATION = ?, MOT_DE_PASSE= ?,EMAIL_AUTH=?,NOM_UTILISATEUR= ?"
				+ " WHERE ID_TECHNICIEN = ?";
		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setString(1, t.getNom());
			ps.setString(2, t.getPrenom());
			ps.setString(3, t.getCin());
			ps.setString(4, t.getLieuNaissance());
			ps.setString(5, t.getTelephone());
			ps.setString(6, t.getEmail());
			ps.setDate(7, new java.sql.Date(t.getDateNaissance().getTime()));
			ps.setString(8, t.getSpecialite());
			ps.setString(9, t.getCompetence());
			ps.setBoolean(10, t.getDisponibilite() != null ? t.getDisponibilite() : true);
			ps.setDate(11, new java.sql.Date(t.getDateCreation().getTime()));
			t.setNomUtilisateur(t.getNom() + "." + t.getPrenom());
			ps.setString(12, t.getNomUtilisateur());
			t.setEmailAuth(t.getCin() + "@service-municipal.ma");
			ps.setString(13, t.getEmailAuth());
			ps.setString(14, t.getMotDePasse());
			ps.setLong(15, t.getIdTechnicien());

			ps.executeUpdate();
			return t;
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("Erreur lors de la mise à jour du technicien", ex);
		}
	}

	@Override
	public void deleteTechnicien(int id) {
		String sql = "DELETE FROM TECHNICIEN WHERE ID_TECHNICIEN = ?";
		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setLong(1, id);
			ps.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
            throw new RuntimeException("Erreur lors de la suppression du technicien", ex);
        }
    }
}
