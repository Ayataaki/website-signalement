package dao;

import java.util.List;

import metier.Administrateur;


public interface IAdminCRUD {

void createAdmin(Administrateur admin);
	
	void deleteAdmin(int id);
	
	Administrateur updateAdmin(Administrateur admin);
	
	Administrateur getById(Long id);
	
	List<Administrateur> getAll();

	Administrateur findByEmailAuth(String email);

	
}
