package dao;

import java.util.List;

import metier.Employe;

public interface IEmployeCRUD {

	void createEmploye(Employe employe);
	
	void deleteEmploye(Long id);
	
	Employe updateEmploye(Employe employe);

	Employe findByEmailAuth(String email);
	
	int countEmploye();
	
	List<Employe> getAll();

	Employe getById(Long idEmploye);
}
