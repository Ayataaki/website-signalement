package dao;

import metier.Region;

public interface IRegionCRUD {

	void createRegion (Region  region);
	
	void deleteRegion (int id);
	
	Region updateRegion (Region  region);
	
	Region getById(int id);
}
