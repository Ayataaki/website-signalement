package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import metier.Citoyen;
import metier.Employe;
import metier.Municipal;
import metier.Region;
import metier.Signalement;
import metier.Statut;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import dao.CitoyenCRUDImpl;
import dao.EmployeCRUDImpl;
import dao.IMunicipalCRUD;
import dao.ISignalementCRUD;
import dao.MunicipalCRUDImpl;
import dao.RegionCRUDImpl;
import dao.SignalementCRUDImpl;

/**
 * Servlet implementation class SignalementServlet
 */
@WebServlet("/SignalementServlet")
@MultipartConfig(maxFileSize = 1024 * 1024 * 5) 
public class SignalementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CitoyenCRUDImpl citoyenDao = new CitoyenCRUDImpl();
    private EmployeCRUDImpl employeDAO = new EmployeCRUDImpl();		
    private RegionCRUDImpl regionDao = new RegionCRUDImpl();
    private ISignalementCRUD signalementDao = new SignalementCRUDImpl();
    private IMunicipalCRUD municipalDao = new MunicipalCRUDImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");
		
		switch (action) {

		case "create":
			
			storeSignalement(request, response);

			Citoyen citoyen = (Citoyen) request.getSession().getAttribute("user");
			Long idCitoyen = citoyen.getIdCitoyen();
			getAllSignByCitoyen(idCitoyen,request, response);
			updatedDataCitoyen(citoyen,request,response);

			request.getRequestDispatcher("/views/Citoyen/MesSignalements.jsp").forward(request, response);

			break;
			
		case "update":
			Employe employe1 = (Employe) request.getSession().getAttribute("employe");
			Long idMunicipal1 = employe1.getIdMunicipal();
			
			updateStatut(request, response);

			getAllSign(idMunicipal1,request, response);
			
			updatedDataEmploye(employe1,request,response);

			response.sendRedirect(request.getContextPath() + "/views/Employe/GererSignalements.jsp");

			break;
			
		case "delete":
			Employe employe2 = (Employe) request.getSession().getAttribute("employe");
			Long idMunicipal2 = employe2.getIdMunicipal();

			deleteSign(request, response);

			getAllSign(idMunicipal2,request, response);
			
			updatedDataEmploye(employe2,request,response);

			response.sendRedirect(request.getContextPath() + "/views/Employe/GererSignalements.jsp");

			break;
			
		case "recherche":

			rechercheSign(request, response);

			response.sendRedirect(request.getContextPath() + "/views/Employe/GererSignalements.jsp");

			break;
			
		case "updateEmploye":

			Employe employe3 = (Employe) request.getSession().getAttribute("employe");
			Long idMunicipal3 = employe3.getIdMunicipal();

			
			updateSignEmp(request, response);
			
			getAllSign(idMunicipal3,request, response);
			
			updatedDataEmploye(employe3,request,response);

			response.sendRedirect(request.getContextPath() + "/views/Employe/GererSignalements.jsp");

			break;
			
		case "updateAdmin":

			updateSignAdmin(request, response);
			
			updatedDataSendAdmin(request,response);

			response.sendRedirect(request.getContextPath() + "/views/admin/GererSignalement.jsp");

			break;
			
		case "deleteEmp":

			Employe employe4 = (Employe) request.getSession().getAttribute("employe");
			Long idMunicipal4 = employe4.getIdMunicipal();

			deleteSignEmp(request, response);
			
			getAllSign(idMunicipal4,request, response);
			
			updatedDataEmploye(employe4,request,response);
					
			response.sendRedirect(request.getContextPath() + "/views/Employe/GererSignalements.jsp");

			break;
			
		case "deleteAdmin":

			deleteSignAdmin(request, response);
			
			updatedDataSendAdmin(request,response);

			response.sendRedirect(request.getContextPath() + "/views/admin/GererSignalement.jsp");

			break;

		}
		
	}
	
	private void updatedDataCitoyen(Citoyen citoyen,HttpServletRequest request, HttpServletResponse response) {
    			
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
	
	private void updatedDataSendAdmin(HttpServletRequest request, HttpServletResponse response) {
			
		//we don't need the object admin here, cause he has a wild visibility 
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
		
	}
	
	private void updatedDataEmploye(Employe employe, HttpServletRequest request, HttpServletResponse response) {
		
		Long idMunicipal = employe.getIdMunicipal();
        int totalReports = signalementDao.countSignalementByMunicipal(idMunicipal);
        
        double resolutionRate = signalementDao.getResolutionRateByMunicipal(idMunicipal); 
        
        List<Signalement> recentReports = signalementDao.getRecentReportsByMunicipal(idMunicipal,5); // les 5 derniers

        Map<String, Integer> monthlyData = signalementDao.getMonthlyReportStatsByMunicipal(idMunicipal);
        
        int nouveaux = signalementDao.getCountNewSignalementByMunicipal(idMunicipal);
        int enCours = signalementDao.getCountProcessingSignalementByMunicipal(idMunicipal);
        int resolus = signalementDao.getCountFinishedSignalementByMunicipal(idMunicipal);
        
        
        
        List<Signalement> signalements = signalementDao.getSignalementByMunicipal(idMunicipal);
        
        request.getSession().setAttribute("totalReports", totalReports);

        request.getSession().setAttribute("nouveaux", nouveaux);
        request.getSession().setAttribute("enCours", enCours);
        request.getSession().setAttribute("resolus", resolus);
        
        request.getSession().setAttribute("recentReports", recentReports);
        request.getSession().setAttribute("resolutionRate", resolutionRate);
        request.getSession().setAttribute("monthlyData", monthlyData);

        request.getSession().setAttribute("signalements", signalements); 
    	request.getSession().setAttribute("employe", employe);
		request.getSession().setAttribute("userType", "employe");
	}
	
	
	private void deleteSignEmp(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("idSignalement");
    	Long idSig = Long.parseLong(id.trim());
		
    	signalementDao.deleteSignalement(idSig);
	}

	private void getAllSignByCitoyen(Long idCitoyen, HttpServletRequest request, HttpServletResponse response) {
		List<Signalement> signalements = signalementDao.getByIdCitoyen(idCitoyen);
		
		request.getSession().setAttribute("signalements", signalements);
	}

	private void deleteSignAdmin(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
    	Long idSig = Long.parseLong(id.trim());
		
    	signalementDao.deleteSignalement(idSig);
    	getAllSign(request,response);
	}

	private void getAllSign(HttpServletRequest request, HttpServletResponse response) {
		List<Signalement> signalements = signalementDao.getAll();
		
		request.getSession().setAttribute("signalements", signalements);
	}
	
	private void updateSignEmp(HttpServletRequest request, HttpServletResponse response) 
			throws UnsupportedEncodingException {
		
		request.setCharacterEncoding("UTF-8");

		String id = request.getParameter("idSignalement");
    	Long idSig = Long.parseLong(id.trim());
        String statut = request.getParameter("statut");

        Signalement s = signalementDao.getById(idSig);
        s.setStatut(Statut.fromLabel(statut));

        signalementDao.updateSignalement(s);
        
        getAllSign(request,response);

	}


	private void updateSignAdmin(HttpServletRequest request, HttpServletResponse response) 
			throws UnsupportedEncodingException {
		
		request.setCharacterEncoding("UTF-8");

		String id = request.getParameter("id");
    	Long idSig = Long.parseLong(id.trim());
		Long idCitoyen = Long.parseLong(request.getParameter("idCitoyen").trim());
        String designation = request.getParameter("designation");
        String description = request.getParameter("description");
        String localisation = request.getParameter("localisation");
        String commentaire = request.getParameter("commentaire");
        String statut = request.getParameter("statut");

        Signalement s = signalementDao.getById(idSig);
        s.setDesignation(designation);
        s.setDescription(description);
        s.setCommentaire(commentaire);
        s.setLocalisation(localisation);
        s.setIdCitoyen(idCitoyen);
        s.setStatut(Statut.fromLabel(statut));

        signalementDao.updateSignalement(s);
        
        getAllSign(request,response);

	}

	private void rechercheSign(HttpServletRequest request, HttpServletResponse response) {
		
		String search = request.getParameter("search");
		
		List<Signalement> signalements = signalementDao.rechercherSignalements(search);
		
		request.getSession().setAttribute("signalements", signalements);
	}

	private void deleteSign(HttpServletRequest request, HttpServletResponse response) {

		String id = request.getParameter("idSignalement");
    	Long idSig = Long.parseLong(id.trim());
		
    	signalementDao.deleteSignalement(idSig);
    	
	}

	private void updateStatut(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("idSignalement");
    	Long idSig = Long.parseLong(id.trim());
    	
    	String statutLabel = request.getParameter("statut");
    	Statut statut = Statut.fromLabel(statutLabel);
    	
    	signalementDao.updateStatut(idSig, statut);
	}

	private void getAllSign(Long idMunicipal, HttpServletRequest request, HttpServletResponse response) {
		
		List<Signalement> signalements = signalementDao.getSignalementByMunicipal(idMunicipal);
		
		request.getSession().setAttribute("signalements", signalements);
		
	}

	protected void storeSignalement(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");

		Long idCitoyen = Long.parseLong(request.getParameter("idCitoyen").trim());
        String designation = request.getParameter("designation");
        String description = request.getParameter("description");
        String localisation = request.getParameter("adresse");
        String commentaire = request.getParameter("commentaire");

        Part imagePart = request.getPart("image");
        String originalFileName = Path.of(imagePart.getSubmittedFileName()).getFileName().toString();
        String uniqueFileName = UUID.randomUUID().toString() + "_" + originalFileName;

        String uploadDirPath = getServletContext().getRealPath("/uploads/signalements");
        File uploadDir = new File(uploadDirPath);
        if (!uploadDir.exists()) {
            boolean created = uploadDir.mkdirs();
            System.out.println("Dossier créé ? " + created);
        }

        File destinationFile = new File(uploadDir, uniqueFileName);

        try (InputStream in = imagePart.getInputStream();
             FileOutputStream out = new FileOutputStream(destinationFile)) {

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }

            System.out.println("Image copiée avec succès dans : " + destinationFile.getAbsolutePath());

        } catch (IOException e) {
            e.printStackTrace();
        }

        String imagePath = "uploads/signalements/" + uniqueFileName;

        String statutLabel = "new";
        Signalement signalement = new Signalement(
            idCitoyen,
            Statut.fromLabel(statutLabel),
            designation,
            description,
            localisation,
            commentaire,
            imagePath
        );

        // l'image se trouve dans le chemin suivant : 
//        avant uploads enlever le slash, ça m'a causé du pb: C:\Users\Admin\Desktop\J2EE-Workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\project-j2ee\\uploads\signalements\50c8f25d-3f17-41b2-b7b5-fc3cd71bd41a_WhatsApp Image 2025-11-04 at 15.14.48.jpeg
        signalementDao.createSignalement(signalement);

	}

}
