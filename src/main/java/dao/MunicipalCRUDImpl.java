package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import metier.Municipal;
import metier.Region;

public class MunicipalCRUDImpl implements IMunicipalCRUD {

    private Connection connection;

    public MunicipalCRUDImpl() {
        this.connection = SingletonConnection.getConnection();
    }

    @Override
    public void createMunicipal(Municipal municipal) {
        String sql = "INSERT INTO MUNICIPAL (NOM, TYPE_MUNICIPAL, ID_REGION, DATE_CREATION) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, municipal.getNom());
            ps.setString(2, municipal.getTypeMunicipal());
            if (municipal.getIdRegion() != null) {
                ps.setLong(3, municipal.getIdRegion());
            } else {
                ps.setNull(3, Types.BIGINT);
            }
            
            //ps.setDate(4, new java.sql.Date(municipal.getDateCreation().getTime()));
            
            if (municipal.getDateCreation() == null) {
                ps.setDate(4, new java.sql.Date(System.currentTimeMillis()));
            } else {
                ps.setDate(4, new java.sql.Date(municipal.getDateCreation().getTime()));
            }

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    municipal.setIdMunicipal(rs.getLong(1));
                }
            }

        } catch (SQLException ex) {
            throw new RuntimeException("Erreur lors de la création de la municipalité", ex);
        }
    }

    @Override
    public Municipal getById(Long id) {
        String sql = "SELECT * FROM MUNICIPAL WHERE ID_MUNICIPAL = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Municipal m = new Municipal();
                    m.setIdMunicipal(rs.getLong("ID_MUNICIPAL"));
                    m.setNom(rs.getString("NOM"));
                    m.setTypeMunicipal(rs.getString("TYPE_MUNICIPAL"));
                    long idRegion = rs.getLong("ID_REGION");
                    m.setIdRegion(rs.wasNull() ? null : idRegion);
                    m.setDateCreation(rs.getDate("DATE_CREATION"));
                    return m;
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erreur lors de la récupération de la municipalité", ex);
        }
        return null;
    }

    @Override
    public Municipal updateMunicipal(Municipal municipal) {
        String sql = "UPDATE MUNICIPAL SET NOM = ?, TYPE_MUNICIPAL = ?, ID_REGION = ?, DATE_CREATION = ? WHERE ID_MUNICIPAL = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, municipal.getNom());
            ps.setString(2, municipal.getTypeMunicipal());
            if (municipal.getIdRegion() != null) {
                ps.setLong(3, municipal.getIdRegion());
            } else {
                ps.setNull(3, Types.BIGINT);
            }
            ps.setDate(4, new java.sql.Date(municipal.getDateCreation().getTime()));
            ps.setLong(5, municipal.getIdMunicipal());

            ps.executeUpdate();
            return municipal;
        } catch (SQLException ex) {
            throw new RuntimeException("Erreur lors de la mise à jour de la municipalité", ex);
        }
    }

    @Override
    public void deleteMunicipal(Long id) {
        String sql = "DELETE FROM MUNICIPAL WHERE ID_MUNICIPAL = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Erreur lors de la suppression de la municipalité", ex);
        }
    }

	@Override
	public List<Municipal> getAll() {
		String sql = "SELECT * FROM MUNICIPAL";
        List<Municipal> list = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Municipal m = new Municipal();
                    m.setIdMunicipal(rs.getLong("ID_MUNICIPAL"));
                    m.setNom(rs.getString("NOM"));
                    m.setTypeMunicipal(rs.getString("TYPE_MUNICIPAL"));
                    long idRegion = rs.getLong("ID_REGION");
                    m.setIdRegion(rs.wasNull() ? null : idRegion);
                    m.setDateCreation(rs.getDate("DATE_CREATION"));
                    list.add(m);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erreur lors de la récupération de la municipalité", ex);
        }
        return list;
	}
}
