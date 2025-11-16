package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import metier.Citoyen;
import metier.Employe;
import utils.PasswordHashUtil;

import java.io.IOException;

import dao.AdminCRUDImpl;
import dao.CitoyenCRUDImpl;
import dao.EmployeCRUDImpl;
import dao.IMunicipalCRUD;
import dao.ISignalementCRUD;
import dao.MunicipalCRUDImpl;
import dao.RegionCRUDImpl;
import dao.SignalementCRUDImpl;
import dao.TechnicienCRUDImpl;

/**
 * Servlet implementation class EmployeServlet
 */
@WebServlet("/EmployeServlet")
public class EmployeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private EmployeCRUDImpl employeDao = new EmployeCRUDImpl();
    private RegionCRUDImpl regionDao = new RegionCRUDImpl();
    private ISignalementCRUD signalementDao = new SignalementCRUDImpl();
    private IMunicipalCRUD municipalDao = new MunicipalCRUDImpl();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		System.out.println("Action reçue : " + action);

		switch (action) {
		
		case "update":
			// insertion des données après leurs modifications
			updateEmploye(request, response);
			request.getRequestDispatcher("/views/employe/ProfilEmploye.jsp").forward(request, response);
			break;
		case "changerMdp":
			updatePassword(request, response);
			break;
		default:
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "Action inconnue : " + action);
			break;
		}
	}
	private void updatePassword(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		// Récupération de l’utilisateur en session
		Long idEmploye = Long.parseLong(request.getParameter("idEmploye").trim());
		Employe employe = employeDao.getById(idEmploye);

		// Récupération des champs
		String currentPassword = request.getParameter("currentPassword");
		String newPassword = request.getParameter("newPassword");
		String confirmPassword = request.getParameter("confirmPassword");

		// Vérification du mot de passe actuel
		if (!PasswordHashUtil.verifyPassword(currentPassword, employe.getMotDePasse())) {
			request.setAttribute("error", "Mot de passe actuel incorrect !");
			request.getRequestDispatcher("/views/employe/ProfilEmploye.jsp").forward(request, response);
			return;
		}

		// Vérification de la correspondance des nouveaux mots de passe
		if (!newPassword.equals(confirmPassword)) {
			request.setAttribute("error", "Les mots de passe ne correspondent pas !");
			request.getRequestDispatcher("/views/employe/ProfilEmploye.jsp").forward(request, response);
			return;
		}

		// Hash du nouveau mot de passe
		String hashedPassword = PasswordHashUtil.hashPassword(newPassword);

		// Mise à jour du mot de passe dans la base
		employe.setMotDePasse(hashedPassword);
		employeDao.updatePwd(hashedPassword, employe.getIdEmploye());

		// Actualisation de la session
		request.getSession().setAttribute("employe", employe);

		// Message de succès
		request.setAttribute("success", "Votre mot de passe a été mis à jour avec succès !");
		request.getRequestDispatcher("/views/employe/ProfilEmploye.jsp").forward(request, response);
	}

	protected void updateEmploye(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		Long idEmploye = Long.parseLong(request.getParameter("idEmploye").trim());
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
		
		request.getSession().setAttribute("updatedEmploye", updatedEmploye);
		
	}
}
