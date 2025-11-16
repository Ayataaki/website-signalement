package dao;

import java.util.List;

import metier.Municipal;

public interface IMunicipalCRUD {

	void createMunicipal(Municipal municipal);
	
	void deleteMunicipal(Long id);
	
	Municipal updateMunicipal(Municipal municipal);
	
	Municipal getById(Long id);
	
	List<Municipal> getAll();

}
