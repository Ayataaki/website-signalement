package dao;

import metier.Employe;
import metier.Technicien;

public interface ITechnicienCRUD {
	
	void createTechnicien (Technicien  technicien);
	
	void deleteTechnicien (int  id);
	
	Technicien  updateTechnicien (Technicien  technicien);
	
	Technicien  getById(int id);
}
