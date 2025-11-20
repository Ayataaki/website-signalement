package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import metier.Citoyen;
import metier.Employe;

import java.io.IOException;
import java.util.List;

import dao.CitoyenCRUDImpl;
import dao.EmployeCRUDImpl;
import dao.ICitoyenCRUD;
import dao.IEmployeCRUD;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ICitoyenCRUD citoyenDao = new CitoyenCRUDImpl();
	private IEmployeCRUD employeDao = new EmployeCRUDImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String type = request.getParameter("type"); 
		String deleteType = request.getParameter("deleteType"); 
		
		String action = request.getParameter("action");
	    
	    if ("delete".equals(action) && deleteType != null) {
	        switch (deleteType) {
	            case "citoyen":
	                supprimerCitoyen(request, response);  
	                List<Citoyen> citoyens = citoyenDao.getAll();
	                request.getSession().setAttribute("citoyens", citoyens); 
	                request.getRequestDispatcher("/views/admin/GererUtilisateur.jsp").forward(request, response);
	                break;
	                
	            case "employe":
	                supprimerEmploye(request, response);
	                List<Employe> employes = employeDao.getAll();
	                request.getSession().setAttribute("employes", employes); 
	                request.getRequestDispatcher("/views/admin/GererUtilisateur.jsp").forward(request, response);
	                break;
	                
	            default:
	                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Type invalide");
	                break;
	        }
	    } 
	    // Logique de modification
	    else if (type != null) {
	        switch (type) {
	            case "citoyen":
	                updateCitoyen(request, response);  
	                List<Citoyen> citoyens = citoyenDao.getAll();
	                request.getSession().setAttribute("citoyens", citoyens); 
	                request.getRequestDispatcher("/views/admin/GererUtilisateur.jsp").forward(request, response);
	                break;
	                
	            case "employe":
	                updateEmploye(request, response);
	                List<Employe> employes = employeDao.getAll();
	                request.getSession().setAttribute("employes", employes); 
	                request.getRequestDispatcher("/views/admin/GererUtilisateur.jsp").forward(request, response);
	                break;
	                
	            default:
	                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Type invalide");
	                break;
	        }
	    } else {
	        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Paramètres manquants");
	    }
	}
	
	private void supprimerEmploye(HttpServletRequest request, HttpServletResponse response) {
		Long idEmploye = Long.parseLong(request.getParameter("id").trim());
		employeDao.deleteEmploye(idEmploye);
	}

	private void supprimerCitoyen(HttpServletRequest request, HttpServletResponse response) {
		Long idCitoyen = Long.parseLong(request.getParameter("id").trim());
		citoyenDao.deleteCitoyen(idCitoyen);
	}

	private void updateEmploye(HttpServletRequest request, HttpServletResponse response) {
		
		Long idEmploye = Long.parseLong(request.getParameter("id").trim());
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String email = request.getParameter("email");
        String cin = request.getParameter("cin") ;
        String telephone = request.getParameter("telephone") ; 
        String lieuNaissance = request.getParameter("lieuNaissance") ;        
        String dateStr = request.getParameter("dateNaissance");
        java.util.Date dateNaissance = java.sql.Date.valueOf(dateStr);
        String idMunicipal = request.getParameter("idMunicipal");
		Long idMunicipalM = Long.parseLong(idMunicipal.trim());
        
		//on a chargé les anciens données, donc il faut comparer 
		//les anciens avec les nouvelles et si elles changent, on change
		Employe oldEmploye = employeDao.getById(idEmploye);
		String password = oldEmploye.getMotDePasse();//ce mdp est hashé , donc ça ne va pas etre changé
		
		Employe updatedEmploye = new Employe(idEmploye,nom,prenom,cin,lieuNaissance,
    			telephone, email, password, dateNaissance,idMunicipalM);
		
		employeDao.updateEmploye(updatedEmploye);

	}

	protected void updateCitoyen(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Long idCitoyen = Long.parseLong(request.getParameter("id").trim());
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String email = request.getParameter("email");
        String cin = request.getParameter("cin") ;
        String telephone = request.getParameter("telephone") ; 
        String lieuNaissance = request.getParameter("lieuNaissance") ;        
        String dateStr = request.getParameter("dateNaissance");
        java.util.Date dateNaissance = java.sql.Date.valueOf(dateStr);
        String idRegion = request.getParameter("idRegion");
		Long idRegionM = Long.parseLong(idRegion.trim());
        
		//on a chargé les anciens données, donc il faut comparer 
		//les anciens avec les nouvelles et si elles changent, on change
		Citoyen oldCitoyen = citoyenDao.getById(idCitoyen);
		String password = oldCitoyen.getMotDePasse();//ce mdp est hashé , donc ça ne va pas etre changé
		
		Citoyen updatedCitoyen = new Citoyen(idCitoyen,nom,prenom,cin,lieuNaissance,
    			telephone, email, password, dateNaissance,idRegionM);
		
		citoyenDao.updateCitoyen(updatedCitoyen);
		
	}

}
