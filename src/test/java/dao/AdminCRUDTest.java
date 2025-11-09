package dao;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.junit.Before;
import org.junit.Test;

import metier.Administrateur;

public class AdminCRUDTest {
	
	private AdminCRUDImpl adminDAO;

	@Before
	public void setUp() throws Exception {
		adminDAO = new AdminCRUDImpl();

	    Connection conn = SingletonConnection.getConnection();
	    conn.createStatement().executeUpdate("DELETE FROM ADMINISTRATEUR");
	}

	private Administrateur createTestAdmin(String nom, String prenom, String cin, String email) {
		Administrateur c = new Administrateur();
        c.setNom(nom);
        c.setPrenom(prenom);
        c.setCin(cin);
        c.setLieuNaissance("TestVille");
        c.setTelephone("0123456789");
        c.setEmail(email);
        c.setDateNaissance(new java.util.Date());
        c.setDateCreation(new java.util.Date()); 
        c.setMotDePasse("password123"); 
        c.setNomUtilisateur(nom + "." + prenom);
        c.setEmailAuth(cin + "@municipal.ma");
        return c;
    }
	

	@Test
    public void testCreateAndGetByIdAdmin() {
		Administrateur c = createTestAdmin("Dupont", "Jean", "C123456", "jean.dupont@example.com");
        adminDAO.createAdmin(c);

        assertNotNull("L'ID ne doit pas être null après création", c.getIdAdmin());

        Administrateur fetched = adminDAO.getById(c.getIdAdmin().intValue());
        assertNotNull(fetched);
        assertEquals(c.getNom(), fetched.getNom());
        assertEquals(c.getPrenom(), fetched.getPrenom());
        assertEquals(c.getNomUtilisateur(), fetched.getNomUtilisateur());
        assertEquals(c.getEmailAuth(), fetched.getEmailAuth());
        assertEquals(c.getMotDePasse(), fetched.getMotDePasse());

        assertNotNull("L'ID de l'admin doit être généré",c.getIdAdmin());
    }
	
    @Test
    public void testUpdateAdmin() {
    	
    	Administrateur c = createTestAdmin("Dupont", "Jean", "C123456", "jean.dupont@example.com");
        adminDAO.createAdmin(c);

        // Modification
        c.setNom("TestModifie");
        c.setNomUtilisateur(c.getNom() + "." + c.getPrenom());
        c.setEmailAuth(c.getCin() + "@municipal.ma");
        c.setMotDePasse("newpassword");
        adminDAO.updateAdmin(c);

        Administrateur updated = adminDAO.getById(c.getIdAdmin().intValue());
        assertEquals("TestModifie", updated.getNom());
        assertEquals(c.getNomUtilisateur(), updated.getNomUtilisateur());
        assertEquals(c.getEmailAuth(), updated.getEmailAuth());
        assertEquals(c.getMotDePasse(), updated.getMotDePasse());
    	
    }

    @Test
    public void testDeleteAdmin() {

    	Administrateur c = createTestAdmin("Dupont", "Jean", "C123456", "jean.dupont@example.com");
        adminDAO.createAdmin(c);

        Long id = c.getIdAdmin();
        assertNotNull(id);

        adminDAO.deleteAdmin(id.intValue());
        Administrateur deleted = adminDAO.getById(id.intValue());
        assertNull("L'administrateur doit etre supprimé", deleted);
    	
    }
    

}
