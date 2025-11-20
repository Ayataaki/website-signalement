package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import metier.Administrateur;
import metier.Citoyen;
import metier.Employe;
import metier.Municipal;
import metier.Region;
import metier.Signalement;
import metier.Technicien;
import utils.PasswordHashUtil;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import dao.AdminCRUDImpl;
import dao.CitoyenCRUDImpl;
import dao.EmployeCRUDImpl;
import dao.IMunicipalCRUD;
import dao.ISignalementCRUD;
import dao.MunicipalCRUDImpl;
import dao.RegionCRUDImpl;
import dao.SignalementCRUDImpl;
import dao.TechnicienCRUDImpl;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private CitoyenCRUDImpl citoyenDao = new CitoyenCRUDImpl();
    private AdminCRUDImpl adminDao = new AdminCRUDImpl();
    private EmployeCRUDImpl employeDAO = new EmployeCRUDImpl();	
    private TechnicienCRUDImpl technicienDAO = new TechnicienCRUDImpl();	
    private RegionCRUDImpl regionDao = new RegionCRUDImpl();
    private ISignalementCRUD signalementDao = new SignalementCRUDImpl();
    private IMunicipalCRUD municipalDao = new MunicipalCRUDImpl();
       
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

		try {
			List<Region> regions = regionDao.getAll();
			System.out.println("Nombre de régions: " + (regions != null ? regions.size() : 0));

			request.getSession().setAttribute("regions", regions);

			request.getRequestDispatcher("/views/Auth/Connexion.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur: " + e.getMessage());
		}
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String email = request.getParameter("email");
		String motDePasse = request.getParameter("password");

		Citoyen citoyen = citoyenDao.findByEmailAuth(email);
		Administrateur admin = adminDao.findByEmailAuth(email);
		Technicien technicien = technicienDAO.findByEmailAuth(email);
		Employe employe = employeDAO.findByEmailAuth(email);

		if (citoyen != null && PasswordHashUtil.verifyPassword(motDePasse, citoyen.getMotDePasse())) {

			Long idCitoyen = citoyen.getIdCitoyen();
			
        	dataSendCitoyen(citoyen,request,response);
			
			response.sendRedirect(request.getContextPath() + "/views/Citoyen/DashboardCitoyen.jsp");
			
			return;

		} else if (admin != null && PasswordHashUtil.verifyPassword(motDePasse, admin.getMotDePasse())) {


        	dataSendAdmin(admin,request,response);
        	
			response.sendRedirect(request.getContextPath() + "/views/admin/DashboardAdmin.jsp");

		} else if (technicien != null && PasswordHashUtil.verifyPassword(motDePasse, technicien.getMotDePasse())) {
			
			request.getSession().setAttribute("technicien", technicien);
			request.getSession().setAttribute("userType", "technicien");
			response.sendRedirect("technicien/dashboard.jsp");
			
		} else if (employe != null && PasswordHashUtil.verifyPassword(motDePasse, employe.getMotDePasse())) {
			
		 	dataSendEmploye(employe,request,response);
			
			
			response.sendRedirect(request.getContextPath() + "/views/Employe/DashboardEmploye.jsp");
			
		} else {
			
			request.setAttribute("error", "Email ou mot de passe incorrect");
			request.getRequestDispatcher("/views/Auth/Connexion.jsp")
				.forward(request, response);
			
		}
	}

	private void dataSendEmploye(Employe employe, HttpServletRequest request, HttpServletResponse response) {
		
		Long idMunicipal = employe.getIdMunicipal();
		int totalUsers = citoyenDao.countCitoyenByMunicipal(idMunicipal);
        int totalReports = signalementDao.countSignalementByMunicipal(idMunicipal);
        
        double resolutionRate = signalementDao.getResolutionRateByMunicipal(idMunicipal); 
        
        List<Signalement> recentReports = signalementDao.getRecentReportsByMunicipal(idMunicipal,5); // les 5 derniers

        Map<String, Integer> monthlyData = signalementDao.getMonthlyReportStatsByMunicipal(idMunicipal);
        
        int nouveaux = signalementDao.getCountNewSignalementByMunicipal(idMunicipal);
        int enCours = signalementDao.getCountProcessingSignalementByMunicipal(idMunicipal);
        int resolus = signalementDao.getCountFinishedSignalementByMunicipal(idMunicipal);
        
        List<Region> regions = regionDao.getAll();
        List<Citoyen> citoyens = citoyenDao.getAll();
        List<Municipal> municipaux = municipalDao.getAll();
        
        
        List<Signalement> signalements = signalementDao.getSignalementByMunicipal(idMunicipal);
        
        request.getSession().setAttribute("totalUsers", totalUsers);
        request.getSession().setAttribute("totalReports", totalReports);

        request.getSession().setAttribute("nouveaux", nouveaux);
        request.getSession().setAttribute("enCours", enCours);
        request.getSession().setAttribute("resolus", resolus);
        
        request.getSession().setAttribute("recentReports", recentReports);
        request.getSession().setAttribute("resolutionRate", resolutionRate);
        request.getSession().setAttribute("monthlyData", monthlyData);

        request.getSession().setAttribute("signalements", signalements); 
        request.getSession().setAttribute("citoyens", citoyens);   	
        request.getSession().setAttribute("regions", regions);
    	request.getSession().setAttribute("municipaux", municipaux);
    	request.getSession().setAttribute("employe", employe);
		request.getSession().setAttribute("userType", "employe");
	}

	private void dataSendAdmin(Administrateur admin, HttpServletRequest request, HttpServletResponse response) {
						
		int totalUsers = citoyenDao.countCitoyen();
        int totalReports = signalementDao.countSignalement();
        int municipalStaff = employeDAO.countEmploye();
        double resolutionRate = signalementDao.getResolutionRate(); 
        List<Signalement> recentReports = signalementDao.getRecentReports(5); 

        Map<String, Integer> monthlyData = signalementDao.getMonthlyReportStats();
        List<Region> regions = regionDao.getAll();
        List<Employe> employes = employeDAO.getAll();
        List<Citoyen> citoyens = citoyenDao.getAll();
        List<Municipal> municipaux = municipalDao.getAll();
        System.out.println("Municipaux récupérés : " + municipaux.size());
        List<Signalement> signalements = signalementDao.getAll();
        
        request.getSession().setAttribute("totalUsers", totalUsers);
        request.getSession().setAttribute("totalReports", totalReports);
        request.getSession().setAttribute("municipalStaff", municipalStaff);

        request.getSession().setAttribute("recentReports", recentReports);
        request.getSession().setAttribute("resolutionRate", resolutionRate);
        request.getSession().setAttribute("monthlyData", monthlyData);

        request.getSession().setAttribute("signalements", signalements); 
        request.getSession().setAttribute("citoyens", citoyens); 
        request.getSession().setAttribute("employes", employes);    	
        request.getSession().setAttribute("regions", regions);
    	request.getSession().setAttribute("municipaux", municipaux);
		request.getSession().setAttribute("admin", admin);
		request.getSession().setAttribute("userType", "admin");
		
	}

	private void dataSendCitoyen(Citoyen citoyen,HttpServletRequest request, HttpServletResponse response) {
    	
		
		Long idCitoyen = citoyen.getIdCitoyen();

		request.getSession().setAttribute("user", citoyen);
		List<Region> regions = regionDao.getAll();
    	
		Region regionCitoyen = regionDao.getRegionByCitoyen(idCitoyen);
    	List<Signalement> signalements = signalementDao.getByIdCitoyen(idCitoyen);
    	
    	int countNewSignalement = signalementDao.getCountNewSignalementByCitoyen(idCitoyen);
    	int countProcessingSignalement = signalementDao.getCountProcessingSignalementByCitoyen(idCitoyen);
    	int countFinishedSignalement = signalementDao.getCountFinishedSignalementByCitoyen(idCitoyen);
    	int countTotalSignalement = countNewSignalement +countProcessingSignalement+countFinishedSignalement;
    	
    	
		request.getSession().setAttribute("countNewSignalement", countNewSignalement);
		request.getSession().setAttribute("countProcessingSignalement", countProcessingSignalement);		
    	request.getSession().setAttribute("countFinishedSignalement", countFinishedSignalement);		
    	request.getSession().setAttribute("countTotalSignalement", countTotalSignalement);		

    	request.getSession().setAttribute("signalements", signalements);
    	request.getSession().setAttribute("regions", regions);
    	request.getSession().setAttribute("regionCitoyen", regionCitoyen);        	
    	request.getSession().setAttribute("signalements", signalements);
		request.getSession().setAttribute("userType", "citoyen");

	}

}
