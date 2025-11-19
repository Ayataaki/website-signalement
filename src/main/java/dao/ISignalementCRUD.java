package dao;

import java.util.List;
import java.util.Map;

import metier.Signalement;
import metier.Statut;

public interface ISignalementCRUD {

	void createSignalement (Signalement signalement);
	
	void deleteSignalement (Long id);
	
	Signalement  updateSignalement (Signalement signalement);
	
	Signalement  getById(Long id);
	
	List<Signalement> getByIdCitoyen(Long idCitoyen);
	
	List<Signalement> getAll();
	
	double getResolutionRate();

	double getResolutionRateByMunicipal(Long idMunicipal);
	
	List<Signalement> getRecentReports(int limit);
	
	List<Signalement> getSignalementByMunicipal(Long idMunicipal);
		
	int countSignalement();
	
	int countSignalementByMunicipal(Long idMunicipal);
	
	int getCountNewSignalementByMunicipal(Long idMunicipal);
	
	int getCountProcessingSignalementByMunicipal(Long idMunicipal);
	
	int getCountFinishedSignalementByMunicipal(Long idMunicipal);
	
	int getCountNewSignalementByCitoyen(Long idCitoyen);
	
	int getCountProcessingSignalementByCitoyen(Long idCitoyen);
	
	int getCountFinishedSignalementByCitoyen(Long idCitoyen);

	Map<String, Integer> getMonthlyReportStats();

	void updateStatut(Long idSignalement, Statut statut);

	List<Signalement> rechercherSignalements(String keyword);
	
	List<Signalement> getRecentReportsByMunicipal(Long idMunicipal, int limit);

	Map<String, Integer> getMonthlyReportStatsByMunicipal(Long idMunicipal);

}
