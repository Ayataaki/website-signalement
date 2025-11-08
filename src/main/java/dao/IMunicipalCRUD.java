package dao;

import metier.Municipal;

public interface IMunicipalCRUD {

	void createMunicipal(Municipal municipal);
	
	void deleteMunicipal(int id);
	
	Municipal updateMunicipal(Municipal municipal);
	
	Municipal getById(int id);

}
