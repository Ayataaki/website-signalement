package dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.Date;

import org.junit.Before;
import org.junit.Test;

import metier.Citoyen;
import metier.Employe;
import metier.Technicien;

public class TechnicienCRUDTest {

    private TechnicienCRUDImpl technicienDAO;

    @Before
    public void setUp() throws Exception {
        technicienDAO = new TechnicienCRUDImpl();
        Connection conn = SingletonConnection.getConnection();
	    conn.createStatement().executeUpdate("DELETE FROM TECHNICIEN");
    }
    
    private Technicien createTestTechnicien(String nom, String prenom, String cin, String email) {
        Technicien t = new Technicien();
        t.setNom(nom);
        t.setPrenom(prenom);
        t.setCin(cin);
        t.setLieuNaissance("Rabat");
        t.setTelephone("0612345678");
        t.setEmail(email);
        t.setDateNaissance(Date.valueOf("1990-01-10"));
        t.setSpecialite("Électricité");
        t.setCompetence("Installation");
        t.setMotDePasse("secret");
        t.setDisponibilite(true);
        t.setDateCreation(new Date(System.currentTimeMillis()));
        return t;
    }

    @Test
    public void testCreateAndGetByIdTechnicien() {
    	Technicien t = createTestTechnicien("Dupont", "Jean", "C123456", "jean.dupont@example.com");
        technicienDAO.createTechnicien(t);

        assertNotNull("L'ID ne doit pas être null après création", t.getIdTechnicien());

        Technicien fetched = technicienDAO.getById(t.getIdTechnicien().intValue());
        assertNotNull(fetched);
        assertEquals(t.getNom(), fetched.getNom());
        assertEquals(t.getPrenom(), fetched.getPrenom());
        assertEquals(t.getNomUtilisateur(), fetched.getNomUtilisateur());
        assertEquals(t.getEmailAuth(), fetched.getEmailAuth());
        assertEquals(t.getMotDePasse(), fetched.getMotDePasse());

        assertNotNull("L'ID du technicien doit être généré", t.getIdTechnicien());
    }

    @Test
    public void testUpdateTechnicien() {
        Technicien t =createTestTechnicien("Hassan","Khalid","CC334455","hassan.khalid@example.com");
        
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
		Technicien t = createTestTechnicien("Hassan", "Khalid", "CC334455", "hassan.khalid@example.com");

		technicienDAO.createTechnicien(t);

		Long id = t.getIdTechnicien();

		technicienDAO.deleteTechnicien(id.intValue());

		Technicien deleted = technicienDAO.getById(id.intValue());
		assertNull("Le technicien supprimé ne doit plus exister", deleted);
	}
}
