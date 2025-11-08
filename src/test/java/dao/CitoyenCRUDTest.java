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

	@Test
	public void testCreateAndGetById() {
		Citoyen c = new Citoyen();
        c.setNom("Dupont");
        c.setPrenom("Jean");
        c.setCin("C123456");
        c.setLieuNaissance("Paris");
        c.setTelephone("0123456789");
        c.setEmail("jean.dupont@example.com");
        c.setMotDePasse("secret");
        c.setDateNaissance(new java.sql.Date(System.currentTimeMillis()));
        c.setDateCreation(new java.sql.Date(System.currentTimeMillis()));
        c.setIdRegion(1L);

        // Création
        crud.createCitoyen(c);

        assertNotNull("L'ID ne doit pas être null après création", c.getIdCitoyen());

        // Récupération
        Citoyen fetched = crud.getById(c.getIdCitoyen().intValue());
        assertEquals("Dupont", fetched.getNom());
        assertEquals("Jean", fetched.getPrenom());
    
	}

	@Test
	public void testDeleteCitoyen() {
        Citoyen c = new Citoyen();
        c.setNom("Delete");
        c.setPrenom("Me");
        c.setCin("D12345");
        c.setLieuNaissance("Marseille");
        c.setTelephone("0123987654");
        c.setEmail("delete.me@example.com");
        c.setMotDePasse("pass");
        c.setDateNaissance(new java.sql.Date(System.currentTimeMillis()));
        c.setDateCreation(new java.sql.Date(System.currentTimeMillis()));
        c.setIdRegion(1L);

        crud.createCitoyen(c);
        Long id = c.getIdCitoyen();
        assertNotNull(id);

        // Suppression
        crud.deleteCitoyen(id.intValue());
        Citoyen deleted = crud.getById(id.intValue());
        assertNull("Le citoyen doit être supprimé", deleted);
	}

	@Test
	public void testUpdateCitoyen() {
		// Créer un citoyen pour test
        Citoyen c = new Citoyen();
        c.setNom("Test");
        c.setPrenom("User");
        c.setCin("T12345");
        c.setLieuNaissance("Lyon");
        c.setTelephone("0987654321");
        c.setEmail("test.user@example.com");
        c.setMotDePasse("1234");
        c.setDateNaissance(new java.sql.Date(System.currentTimeMillis()));
        c.setDateCreation(new java.sql.Date(System.currentTimeMillis()));
        c.setIdRegion(1L);

        crud.createCitoyen(c);

        // Modification
        c.setNom("TestModifié");
        crud.updateCitoyen(c);

        Citoyen updated = crud.getById(c.getIdCitoyen().intValue());
        assertEquals("TestModifié", updated.getNom());
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
