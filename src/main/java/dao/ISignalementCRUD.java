package dao;

import metier.Signalement;

public interface ISignalementCRUD {

	void createSignalement (Signalement signalement);
	
	void deleteSignalement (int id);
	
	Signalement  updateSignalement (Signalement signalement);
	
	Signalement  getById(int id);
}
