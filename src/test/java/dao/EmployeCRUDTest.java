package dao;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.Before;
import org.junit.Test;

import metier.Employe;

public class EmployeCRUDTest {
	
	private EmployeCRUDImpl employeDAO;

	@Before
	public void setUp() throws Exception {
		employeDAO = new EmployeCRUDImpl();
	}

	@Test
    public void testCreateEmploye() {
        Employe e = new Employe();
        e.setNom("Karim");
        e.setPrenom("Ali");
        e.setCin("AA123456");
        e.setLieuNaissance("Rabat");
        e.setTelephone("0612345678");
        e.setEmail("ali.karim@example.com");
        e.setDateNaissance(Date.valueOf("1995-06-10"));
        e.setDateCreation(new Date(System.currentTimeMillis()));
        e.setIdMunicipal(null); // pas encore de municipalité

        employeDAO.createEmploye(e);

        assertNotNull("L'ID de l'employé doit être généré",e.getIdEmploye());
    }
	
	@Test
	public void testGetById() {
		Employe e = new Employe();
		e.setNom("Sara");
		e.setPrenom("Ben");
		e.setCin("BB987654");
		e.setLieuNaissance("Casablanca");
		e.setTelephone("0699988776");
		e.setEmail("sara.ben@example.com");
		e.setDateNaissance(Date.valueOf("1990-12-01"));
		e.setDateCreation(new Date(System.currentTimeMillis()));
		e.setIdMunicipal(null);

		employeDAO.createEmploye(e);

		Employe fetched = employeDAO.getById(e.getIdEmploye().intValue());
		assertNotNull("L'employé doit être trouvé en base", fetched);
		assertEquals("Le nom doit correspondre", "Sara", fetched.getNom());
		}


    @Test
    public void testUpdateEmploye() {
        Employe e = new Employe();
        e.setNom("Youssef");
        e.setPrenom("El Amrani");
        e.setCin("CC555888");
        e.setLieuNaissance("Tanger");
        e.setTelephone("0622334455");
        e.setEmail("youssef@example.com");
        e.setDateNaissance(Date.valueOf("1988-03-22"));
        e.setDateCreation(new Date(System.currentTimeMillis()));
        e.setIdMunicipal(null);

        employeDAO.createEmploye(e);

        // Modifier le téléphone
        e.setTelephone("0700000000");
        employeDAO.updateEmploye(e);

        Employe updated = employeDAO.getById(e.getIdEmploye().intValue());
        assertEquals("Le téléphone doit être mis à jour", "0700000000", updated.getTelephone());

    }

    @Test
    public void testDeleteEmploye() {
        Employe e = new Employe();
        e.setNom("Fatima");
        e.setPrenom("Zahra");
        e.setCin("DD444111");
        e.setLieuNaissance("Agadir");
        e.setTelephone("0611223344");
        e.setEmail("fatima@example.com");
        e.setDateNaissance(Date.valueOf("1992-08-15"));
        e.setDateCreation(new Date(System.currentTimeMillis()));
        e.setIdMunicipal(null);

        employeDAO.createEmploye(e);
        Long id = e.getIdEmploye();

        employeDAO.deleteEmploye(id.intValue());
        Employe deleted = employeDAO.getById(id.intValue());
        assertNull("L'employé supprimé ne doit plus exister",deleted);
    }

}
