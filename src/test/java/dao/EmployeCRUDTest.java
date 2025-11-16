package dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.Date;

import org.junit.Before;
import org.junit.Test;

import metier.Citoyen;
import metier.Employe;

public class EmployeCRUDTest {
	
	private EmployeCRUDImpl employeDAO;

	@Before
	public void setUp() throws Exception {
		employeDAO = new EmployeCRUDImpl();

	    Connection conn = SingletonConnection.getConnection();
	    conn.createStatement().executeUpdate("DELETE FROM EMPLOYE");
	}
	
	
	private Employe createTestEmploye(String nom, String prenom, String cin, String email) {
		Employe c = new Employe();
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
        c.setIdMunicipal(null);
        return c;
    }

	@Test
    public void testCreateAndGetByIdEmploye() {
		Employe c = createTestEmploye("Dupont", "Jean", "C123456", "jean.dupont@example.com");
        employeDAO.createEmploye(c);

        assertNotNull("L'ID ne doit pas être null après création", c.getIdEmploye());

        Employe fetched = employeDAO.getById(c.getIdEmploye().longValue());
        assertNotNull(fetched);
        assertEquals(c.getNom(), fetched.getNom());
        assertEquals(c.getPrenom(), fetched.getPrenom());
        assertEquals(c.getNomUtilisateur(), fetched.getNomUtilisateur());
        assertEquals(c.getEmailAuth(), fetched.getEmailAuth());
        assertNotEquals("newpassword", fetched.getMotDePasse());

        assertNotNull("L'ID de l'employé doit être généré",c.getIdEmploye());
    }
	
    @Test
    public void testUpdateEmploye() {
    	
    	Employe c = createTestEmploye("Dupont", "Jean", "C123456", "jean.dupont@example.com");
        employeDAO.createEmploye(c);

        // Modification
        c.setNom("TestModifie");
        c.setNomUtilisateur(c.getNom() + "." + c.getPrenom());
        c.setEmailAuth(c.getCin() + "@municipal.ma");
        c.setMotDePasse("newpassword");
        employeDAO.updateEmploye(c);

        Employe updated = employeDAO.getById(c.getIdEmploye().longValue());
        assertEquals("TestModifie", updated.getNom());
        assertEquals(c.getNomUtilisateur(), updated.getNomUtilisateur());
        assertEquals(c.getEmailAuth(), updated.getEmailAuth());
        assertNotEquals("newpassword", updated.getMotDePasse());
        assertTrue(updated.getMotDePasse().length() > 20);
    	
    }

    @Test
    public void testDeleteEmploye() {

    	Employe c = createTestEmploye("Dupont", "Jean", "C123456", "jean.dupont@example.com");
        employeDAO.createEmploye(c);

        Long id = c.getIdEmploye();
        assertNotNull(id);

        employeDAO.deleteEmploye(id.longValue());
        Employe deleted = employeDAO.getById(id.longValue());
        assertNull("L'employé doit etre supprimé", deleted);
    	
    }

}
