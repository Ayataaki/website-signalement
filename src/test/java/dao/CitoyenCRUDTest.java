package dao;


import static org.junit.Assert.*;
//import static org.junit.Assertion.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import metier.Citoyen;

public class CitoyenCRUDTest {
	
	private CitoyenCRUDImpl crud = new CitoyenCRUDImpl();

	@Before
	public void setUp() throws Exception {
	}
	
	private Citoyen createTestCitoyen(String nom, String prenom, String cin, String email) {
        Citoyen c = new Citoyen();
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
        c.setIdRegion(null);
        return c;
    }
	
	@Test
    public void testCreateAndGetById() {
        Citoyen c = createTestCitoyen("Dupont", "Jean", "C123456", "jean.dupont@example.com");
        crud.createCitoyen(c);

        assertNotNull("L'ID ne doit pas être null après création", c.getIdCitoyen());

        Citoyen fetched = crud.getById(c.getIdCitoyen().intValue());
        assertNotNull(fetched);
        assertEquals(c.getNom(), fetched.getNom());
        assertEquals(c.getPrenom(), fetched.getPrenom());
        assertEquals(c.getNomUtilisateur(), fetched.getNomUtilisateur());
        assertEquals(c.getEmailAuth(), fetched.getEmailAuth());
        assertEquals(c.getMotDePasse(), fetched.getMotDePasse());
    }

	@Test
    public void testDeleteCitoyen() {
        Citoyen c = createTestCitoyen("Delete", "Me", "D12345", "delete.me@example.com");
        crud.createCitoyen(c);
        Long id = c.getIdCitoyen();
        assertNotNull(id);

        crud.deleteCitoyen(id.intValue());
        Citoyen deleted = crud.getById(id.intValue());
        assertNull("Le citoyen doit être supprimé", deleted);
    }


	@Test
    public void testUpdateCitoyen() {
        Citoyen c = createTestCitoyen("Test", "User", "T12345", "test.user@example.com");
        crud.createCitoyen(c);

        // Modification
        c.setNom("TestModifie");
        c.setNomUtilisateur(c.getNom() + "." + c.getPrenom());
        c.setEmailAuth(c.getCin() + "@municipal.ma");
        c.setMotDePasse("newpassword");
        crud.updateCitoyen(c);

        Citoyen updated = crud.getById(c.getIdCitoyen().intValue());
        assertEquals("TestModifie", updated.getNom());
        assertEquals(c.getNomUtilisateur(), updated.getNomUtilisateur());
        assertEquals(c.getEmailAuth(), updated.getEmailAuth());
        assertEquals(c.getMotDePasse(), updated.getMotDePasse());
    }

	@Test
	public void testGetAll() {
		 List<Citoyen> list = crud.getAll();
	     assertNotNull("La liste ne doit pas être null", list);
	}

	@Test
	public void testGetCitoyenByRegion() {
		List<Citoyen> list = crud.getCitoyenByRegion(1L);
        assertNotNull("La liste des citoyens par région ne doit pas être null", list);
	}

}
