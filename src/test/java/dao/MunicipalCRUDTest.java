package dao;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.Before;
import org.junit.Test;

import metier.Municipal;

public class MunicipalCRUDTest {

    private MunicipalCRUDImpl municipalDAO;

    @Before
    public void setUp() throws Exception {
        municipalDAO = new MunicipalCRUDImpl();
    }

    @Test
    public void testCreateMunicipal() {
        Municipal m = new Municipal();
        m.setNom("Rabat Ville");
        m.setTypeMunicipal("Urbain");
        m.setIdRegion(null); // pas encore assignée à une région
        m.setDateCreation(new Date(System.currentTimeMillis()));

        municipalDAO.createMunicipal(m);

        assertNotNull("L'ID de la municipalité doit être généré", m.getIdMunicipal());
    }

    @Test
    public void testGetById() {
        Municipal m = new Municipal();
        m.setNom("Casablanca Ville");
        m.setTypeMunicipal("Urbain");
        m.setIdRegion(null);
        m.setDateCreation(new Date(System.currentTimeMillis()));

        municipalDAO.createMunicipal(m);

        Municipal fetched = municipalDAO.getById(m.getIdMunicipal().longValue());
        assertNotNull("La municipalité doit être trouvée en base", fetched);
        assertEquals("Le nom doit correspondre", "Casablanca Ville", fetched.getNom());
        assertEquals("Le type doit correspondre", "Urbain", fetched.getTypeMunicipal());
    }

    @Test
    public void testUpdateMunicipal() {
        Municipal m = new Municipal();
        m.setNom("Marrakech Ville");
        m.setTypeMunicipal("Urbain");
        m.setIdRegion(null);
        m.setDateCreation(new Date(System.currentTimeMillis()));

        municipalDAO.createMunicipal(m);

        // Mise à jour du type et du nom
        m.setNom("Marrakech Nouvelle Ville");
        m.setTypeMunicipal("Mixte");
        municipalDAO.updateMunicipal(m);

        Municipal updated = municipalDAO.getById(m.getIdMunicipal().longValue());
        assertEquals("Le nom doit être mis à jour", "Marrakech Nouvelle Ville", updated.getNom());
        assertEquals("Le type doit être mis à jour", "Mixte", updated.getTypeMunicipal());
    }

    @Test
    public void testDeleteMunicipal() {
        Municipal m = new Municipal();
        m.setNom("Fès Ville");
        m.setTypeMunicipal("Urbain");
        m.setIdRegion(null);
        m.setDateCreation(new Date(System.currentTimeMillis()));

        municipalDAO.createMunicipal(m);
        Long id = m.getIdMunicipal();

        municipalDAO.deleteMunicipal(id.longValue());

        Municipal deleted = municipalDAO.getById(id.longValue());
        assertNull("La municipalité supprimée ne doit plus exister", deleted);
    }
}
