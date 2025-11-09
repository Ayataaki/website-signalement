package dao;

import java.util.List;

import metier.Administrateur;


public interface IAdminCRUD {

void createAdmin(Administrateur admin);
	
	void deleteAdmin(int id);
	
	Administrateur updateAdmin(Administrateur admin);
	
	Administrateur getById(int id);
	
	List<Administrateur> getAll();
}
