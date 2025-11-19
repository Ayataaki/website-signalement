package dao;

import metier.Citoyen;
import java.util.List;

public interface ICitoyenCRUD {

	void createCitoyen(Citoyen citoyen);
	
	void deleteCitoyen(Long id);
	
	Citoyen updateCitoyen(Citoyen citoyen);
	
	void updatePwd(String pwd,Long idCitoyen);
	
	Citoyen getById(Long id);
	
	Citoyen findByEmailAuth(String email);
	
	List<Citoyen> getAll();
	
	List<Citoyen> getCitoyenByRegion(Long idRegion);
	
	List<Citoyen> getCitoyenByMunicipal(Long idMunicipal);
	
	int countCitoyenByMunicipal(Long idMunicipal);
	
	int countCitoyen();
}
