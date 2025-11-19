package servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import dao.AdminCRUDImpl;
import dao.CitoyenCRUDImpl;
import dao.EmployeCRUDImpl;
import dao.ICitoyenCRUD;
import dao.ISignalementCRUD;
import dao.MunicipalCRUDImpl;
import dao.RegionCRUDImpl;
import dao.SignalementCRUDImpl;
import dao.TechnicienCRUDImpl;
import metier.Citoyen;
import metier.Employe;
import metier.Municipal;
import metier.Region;
import metier.Signalement;
import metier.Statut;

public class Deboggue {

	public static void main(String[] args) {

		CitoyenCRUDImpl citoyenDao = new CitoyenCRUDImpl();
	     AdminCRUDImpl adminDao = new AdminCRUDImpl();

	     MunicipalCRUDImpl muniDao = new MunicipalCRUDImpl();
	    EmployeCRUDImpl employeDAO = new EmployeCRUDImpl();	

	    RegionCRUDImpl regionDao = new RegionCRUDImpl();	
	     ISignalementCRUD signalementDao = new SignalementCRUDImpl();

//		Citoyen citoyen = citoyenDao.findByEmailAuth("MA123456@municipal.ma");
//			
//    	Long idCitoyen = citoyen.getIdCitoyen();
//    	
//    	List<Signalement> signalementCitoyen = signalementDao.getByIdCitoyen(idCitoyen);
//    	
//    	for (Signalement s : signalementCitoyen) {
//    	    // Faites quelque chose avec l'élément
//    	    System.out.println("designation"+s.getDesignation()+"localisation"+s.getLocalisation());
//    	}
//
//		List<Signalement> list = signalementDao.getByIdCitoyen(idCitoyen);
//
//		System.out.println(signalementDao.getCountNewSignalementByCitoyen(idCitoyen));
//
//		ICitoyenCRUD citoyenDAO = new CitoyenCRUDImpl();
//		Citoyen c = citoyenDao.getById(42L);
//		
//		Region region = regionDao.getById(c.getIdRegion());
//		System.out.println("region citoyen : "+region.getNom());
//    	
//		System.out.println("id region du citoyen :"+c.getIdRegion());
//		
//		List<Region> regions = regionDao.getAll();
//    	//I might not need the following line, but we'll see,leave it here for the moment 
//    	//Region regionCitoyen = regionDao.getRegionByCitoyen((long) 44);
//
//    	
//		for (Region r : regions) {
//		    System.out.println(r.getIdRegion() + " - " + r.getNom());
//		}
//		
//    	List<Signalement> signalements = signalementDao.getAll();
//    	System.out.println("taille des signalements "+signalements.size());
//    	for (Signalement r : signalements) {
//		    System.out.println(r.getDescription() + " - " + r.getLocalisation());
//		}
//
//    	System.out.println(citoyenDao.countCitoyen());
//    	System.out.println("count empl : "+employeDAO.countEmploye());
//    	System.out.println(signalementDao.countSignalement());
//    	System.out.println("pourcentage :"+signalementDao.getResolutionRate());
//    	System.out.println(signalementDao.getRecentReports(5));
//    	for (Signalement r : signalementDao.getRecentReports(5)) {
//		    System.out.println(r.getDescription() + " - " + r.getLocalisation());
//		}
//    	
//    	Map<String, Integer> monthlyData = signalementDao.getMonthlyReportStats();
//    	for (Map.Entry<String, Integer> entry : monthlyData.entrySet()) {
//    	    System.out.println(entry.getKey() + " => " + entry.getValue());
//    	}
//    	
//    	List<Employe> employes = employeDAO.getAll();
//        List<Citoyen> citoyens = citoyenDao.getAll();

        System.out.println("getResolutionRateByMunicipal :"+signalementDao.getResolutionRateByMunicipal(1L));
        
        System.out.println("getSignalementByMunicipal :"+signalementDao.getSignalementByMunicipal(2L));
        
        System.out.println("countSignalementByMunicipal :"+signalementDao.countSignalementByMunicipal(1L));
        
        System.out.println("getCountNewSignalementByMunicipal :"+signalementDao.getCountNewSignalementByMunicipal(1L));
        
        System.out.println("getCountProcessingSignalementByMunicipal :"+signalementDao.getCountProcessingSignalementByMunicipal(2L));
        
        System.out.println("getCountFinishedSignalementByMunicipal :"+signalementDao.getCountFinishedSignalementByMunicipal(2L));
        
        System.out.println("getRecentReportsByMunicipal :"+signalementDao.getRecentReportsByMunicipal(2L,5));
        
        System.out.println("getMonthlyReportStatsByMunicipal :"+signalementDao.getMonthlyReportStatsByMunicipal(1L));
        
        
        
	}
	
	

}
