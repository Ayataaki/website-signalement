package dao;

import metier.Employe;

public interface IEmployeCRUD {

	void createEmploye(Employe employe);
	
	void deleteEmploye(int id);
	
	Employe updateEmploye(Employe employe);
	
	Employe getById(int id);
}
