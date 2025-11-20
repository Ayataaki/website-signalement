package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import metier.Administrateur;
import metier.Citoyen;
import metier.Employe;
import metier.Region;
import metier.Technicien;

import java.io.IOException;
import java.util.List;

import dao.AdminCRUDImpl;
import dao.CitoyenCRUDImpl;
import dao.EmployeCRUDImpl;
import dao.TechnicienCRUDImpl;

import dao.IAdminCRUD;
import dao.ICitoyenCRUD;
import dao.IEmployeCRUD;
import dao.IRegionCRUD;
import dao.ITechnicienCRUD;
import dao.RegionCRUDImpl;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ICitoyenCRUD citoyenDao = new CitoyenCRUDImpl();
    private IAdminCRUD adminDao = new AdminCRUDImpl();
    private IEmployeCRUD employeDAO = new EmployeCRUDImpl();	
    private ITechnicienCRUD technicienDAO = new TechnicienCRUDImpl();	       
    private IRegionCRUD regionDao = new RegionCRUDImpl();    

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

		try {
			List<Region> regions = regionDao.getAll();
			System.out.println("Nombre de régions: " + (regions != null ? regions.size() : 0));

			request.getSession().setAttribute("regions", regions);

			request.getRequestDispatcher("/views/Auth/Inscription.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur: " + e.getMessage());
		}
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {		
		
		String type = request.getParameter("typeCompte"); 
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String email = request.getParameter("email");
        String cin = request.getParameter("cin") ;
        String telephone = request.getParameter("telephone") ; 
        String lieuNaissance = request.getParameter("lieu_naissance") ;        
        String dateStr = request.getParameter("date_naissance");
        java.util.Date dateNaissance = java.sql.Date.valueOf(dateStr); 
        String password = request.getParameter("mot_de_passe");

        switch (type) {
            case "citoyen":
            	Long idRegion = trouverIdRegion(request,response);
            	
            	Citoyen newCitoyen = new Citoyen(nom,prenom,cin,lieuNaissance,
            			telephone, email, password, dateNaissance,idRegion);
            	
            	citoyenDao.createCitoyen(newCitoyen);          	
            	request.getRequestDispatcher("/views/Auth/Connexion.jsp").forward(request, response);
                break;
                
            case "employe":

            	Long idMunicipal = trouverIdMunicipal(request,response);
            	
            	employeDAO.createEmploye(new Employe(nom,prenom,cin,lieuNaissance,
            			telephone, email, password, dateNaissance,idMunicipal));

                List<Employe> employes = employeDAO.getAll();
                request.getSession().setAttribute("employes", employes); 
                response.sendRedirect(request.getContextPath() + "/views/admin/GererUtilisateur.jsp?onglet=employes");

                break;
                
            case "technicien":
            	String specialite = request.getParameter("specialite");
            	String competence = request.getParameter("competence");
            	technicienDAO.createTechnicien(new Technicien(nom, prenom, cin, lieuNaissance,
            			telephone, email, password, dateNaissance, specialite, competence));
                break;
                
            case "admin":
            	adminDao.createAdmin(new Administrateur(nom,prenom,cin,lieuNaissance,
            			telephone, email, password, dateNaissance));
            	response.sendRedirect(request.getContextPath() + "/views/admin/ProfilAdmin.jsp");
            	break;
        }

	}

	private Long trouverIdMunicipal(HttpServletRequest request, HttpServletResponse response) {
		String idMunicipal = request.getParameter("idMunicipal");
		Long idMunicipalM = Long.parseLong(idMunicipal.trim());
		return idMunicipalM;
	}

	private Long trouverIdRegion(HttpServletRequest request, HttpServletResponse response) {
        String idRegion = request.getParameter("idRegion");
		Long idRegionM = Long.parseLong(idRegion.trim());
		return idRegionM;		
	}
	
	

}
