package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import metier.Employe;
import utils.PasswordHashUtil;

import java.io.IOException;

import dao.EmployeCRUDImpl;
import dao.IMunicipalCRUD;
import dao.ISignalementCRUD;
import dao.MunicipalCRUDImpl;
import dao.RegionCRUDImpl;
import dao.SignalementCRUDImpl;

@WebServlet("/EmployeServlet")
public class EmployeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private EmployeCRUDImpl employeDao = new EmployeCRUDImpl();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		System.out.println("Action reçue : " + action);

		switch (action) {
		
		case "update":
			updateEmploye(request, response);
			request.getRequestDispatcher("/views/Employe/ProfilEmploye.jsp").forward(request, response);
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

		Long idEmploye = Long.parseLong(request.getParameter("idEmploye").trim());
		Employe employe = employeDao.getById(idEmploye);

		String currentPassword = request.getParameter("currentPassword");
		String newPassword = request.getParameter("newPassword");
		String confirmPassword = request.getParameter("confirmPassword");

		if (!PasswordHashUtil.verifyPassword(currentPassword, employe.getMotDePasse())) {
			request.setAttribute("error", "Mot de passe actuel incorrect !");
			request.getRequestDispatcher("/views/Employe/ProfilEmploye.jsp").forward(request, response);
			return;
		}

		if (!newPassword.equals(confirmPassword)) {
			request.setAttribute("error", "Les mots de passe ne correspondent pas !");
			request.getRequestDispatcher("/views/Employe/ProfilEmploye.jsp").forward(request, response);
			return;
		}

		String hashedPassword = PasswordHashUtil.hashPassword(newPassword);

		employe.setMotDePasse(hashedPassword);
		employeDao.updatePwd(hashedPassword, employe.getIdEmploye());

		request.getSession().setAttribute("employe", employe);

		request.setAttribute("success", "Votre mot de passe a été mis à jour avec succès !");
		request.getRequestDispatcher("/views/Employe/ProfilEmploye.jsp").forward(request, response);
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

		Employe oldEmploye = employeDao.getById(idEmploye);
		String password = oldEmploye.getMotDePasse();

		Employe updatedEmploye = new Employe(idEmploye,nom,prenom,cin,lieuNaissance,
    			telephone, email, password, dateNaissance,idMunicipalM);
		
		employeDao.updateEmploye(updatedEmploye);
		
		request.getSession().setAttribute("employe", updatedEmploye);
		
	}
}
