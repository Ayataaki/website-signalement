package dao;

import java.sql.*;
import metier.Region;

public class RegionCRUDImpl implements IRegionCRUD {

    private Connection connection;

    public RegionCRUDImpl() {
        this.connection = SingletonConnection.getConnection();
    }

    @Override
    public void createRegion(Region region) {
        String sql = "INSERT INTO REGION (NOM, CAPITALE_REGIONALE, SUPERFICIE, POPULATION, DATE_CREATION) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, region.getNom());
            ps.setString(2, region.getCapitaleRegionale());
            ps.setFloat(3, region.getSuperficie());
            ps.setInt(4, region.getPopulation());
            ps.setDate(5, new java.sql.Date(region.getDateCreation().getTime()));

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    region.setIdRegion(rs.getLong(1));
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erreur lors de la création de la région", ex);
        }
    }

    @Override
    public void deleteRegion(int id) {
        String sql = "DELETE FROM REGION WHERE ID_REGION = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Erreur lors de la suppression de la région", ex);
        }
    }

    @Override
    public Region updateRegion(Region region) {
        String sql = "UPDATE REGION SET NOM = ?, CAPITALE_REGIONALE = ?, SUPERFICIE = ?, POPULATION = ?, DATE_CREATION = ? WHERE ID_REGION = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, region.getNom());
            ps.setString(2, region.getCapitaleRegionale());
            ps.setFloat(3, region.getSuperficie());
            ps.setInt(4, region.getPopulation());
            ps.setDate(5, new java.sql.Date(region.getDateCreation().getTime()));
            ps.setLong(6, region.getIdRegion());

            ps.executeUpdate();
            return region;
        } catch (SQLException ex) {
            throw new RuntimeException("Erreur lors de la mise à jour de la région", ex);
        }
    }

    @Override
    public Region getById(int id) {
        String sql = "SELECT * FROM REGION WHERE ID_REGION = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Region r = new Region();
                    r.setIdRegion(rs.getLong("ID_REGION"));
                    r.setNom(rs.getString("NOM"));
                    r.setCapitaleRegionale(rs.getString("CAPITALE_REGIONALE"));
                    r.setSuperficie(rs.getFloat("SUPERFICIE"));
                    r.setPopulation(rs.getInt("POPULATION"));
                    r.setDateCreation(rs.getDate("DATE_CREATION"));
                    return r;
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erreur lors de la récupération de la région", ex);
        }
        return null;
    }
}
