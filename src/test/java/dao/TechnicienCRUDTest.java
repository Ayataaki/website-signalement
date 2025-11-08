package dao;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.Before;
import org.junit.Test;

import metier.Technicien;

public class TechnicienCRUDTest {

    private TechnicienCRUDImpl technicienDAO;

    @Before
    public void setUp() throws Exception {
        technicienDAO = new TechnicienCRUDImpl();
    }

    @Test
    public void testCreateTechnicien() {
        Technicien t = new Technicien();
        t.setNom("Ahmed");
        t.setPrenom("Salah");
        t.setCin("AA112233");
        t.setLieuNaissance("Rabat");
        t.setTelephone("0612345678");
        t.setEmail("ahmed.salah@example.com");
        t.setDateNaissance(Date.valueOf("1990-01-10"));
        t.setSpecialite("Électricité");
        t.setCompetence("Installation");
        t.setDisponibilite(true);
        t.setDateCreation(new Date(System.currentTimeMillis()));

        technicienDAO.createTechnicien(t);

        assertNotNull("L'ID du technicien doit être généré", t.getIdTechnicien());
    }

    @Test
    public void testGetById() {
        Technicien t = new Technicien();
        t.setNom("Mouna");
        t.setPrenom("El Idrissi");
        t.setCin("BB223344");
        t.setLieuNaissance("Casablanca");
        t.setTelephone("0699988776");
        t.setEmail("mouna.elidrissi@example.com");
        t.setDateNaissance(Date.valueOf("1988-05-15"));
        t.setSpecialite("Informatique");
        t.setCompetence("Maintenance");
        t.setDisponibilite(true);
        t.setDateCreation(new Date(System.currentTimeMillis()));

        technicienDAO.createTechnicien(t);

        Technicien fetched = technicienDAO.getById(t.getIdTechnicien().intValue());
        assertNotNull("Le technicien doit être trouvé en base", fetched);
        assertEquals("Le nom doit correspondre", "Mouna", fetched.getNom());
    }

    @Test
    public void testUpdateTechnicien() {
        Technicien t = new Technicien();
        t.setNom("Hassan");
        t.setPrenom("Khalid");
        t.setCin("CC334455");
        t.setLieuNaissance("Fès");
        t.setTelephone("0622334455");
        t.setEmail("hassan.khalid@example.com");
        t.setDateNaissance(Date.valueOf("1992-03-20"));
        t.setSpecialite("Plomberie");
        t.setCompetence("Réparation");
        t.setDisponibilite(true);
        t.setDateCreation(new Date(System.currentTimeMillis()));

        technicienDAO.createTechnicien(t);

        // Mise à jour téléphone et spécialité
        t.setTelephone("0700000000");
        t.setSpecialite("Plomberie Avancée");

        technicienDAO.updateTechnicien(t);

        Technicien updated = technicienDAO.getById(t.getIdTechnicien().intValue());
        assertEquals("Le téléphone doit être mis à jour", "0700000000", updated.getTelephone());
        assertEquals("La spécialité doit être mise à jour", "Plomberie Avancée", updated.getSpecialite());
    }

    @Test
    public void testDeleteTechnicien() {
        Technicien t = new Technicien();
        t.setNom("Fatima");
        t.setPrenom("Zahra");
        t.setCin("DD445566");
        t.setLieuNaissance("Agadir");
        t.setTelephone("0611223344");
        t.setEmail("fatima.zahra@example.com");
        t.setDateNaissance(Date.valueOf("1991-08-12"));
        t.setSpecialite("Soudure");
        t.setCompetence("Assemblage");
        t.setDisponibilite(true);
        t.setDateCreation(new Date(System.currentTimeMillis()));

        technicienDAO.createTechnicien(t);
        Long id = t.getIdTechnicien();

        technicienDAO.deleteTechnicien(id.intValue());

        Technicien deleted = technicienDAO.getById(id.intValue());
        assertNull("Le technicien supprimé ne doit plus exister", deleted);
    }
}
