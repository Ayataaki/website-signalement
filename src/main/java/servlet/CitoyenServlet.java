package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import metier.Citoyen;
import utils.PasswordHashUtil;

import java.io.IOException;

import dao.CitoyenCRUDImpl;
import dao.ICitoyenCRUD;

@WebServlet("/CitoyenServlet")
public class CitoyenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ICitoyenCRUD citoyenDao = new CitoyenCRUDImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		
		String action = request.getParameter("action");

		switch (action) {
		case "changerMdp":
			updatePassword(request, response);
			break;
		case "insertion":
			updateCitoyen(request, response);
			break;

		default:
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "Action inconnue : " + action);
			break;
		}

	}
	
	private void updatePassword(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		Long idCitoyen = Long.parseLong(request.getParameter("idCitoyen").trim());
		Citoyen citoyen = citoyenDao.getById(idCitoyen);

		String currentPassword = request.getParameter("currentPassword");
		String newPassword = request.getParameter("newPassword");
		String confirmPassword = request.getParameter("confirmPassword");

		if (!PasswordHashUtil.verifyPassword(currentPassword, citoyen.getMotDePasse())) {
			request.setAttribute("error", "Mot de passe actuel incorrect !");
			request.getRequestDispatcher("/views/Citoyen/ProfilCitoyen.jsp").forward(request, response);
			return;
		}

		if (!newPassword.equals(confirmPassword)) {
			request.setAttribute("error", "Les mots de passe ne correspondent pas !");
			request.getRequestDispatcher("/views/Citoyen/ProfilCitoyen.jsp").forward(request, response);
			return;
		}

		String hashedPassword = PasswordHashUtil.hashPassword(newPassword);

		citoyen.setMotDePasse(hashedPassword);
		citoyenDao.updatePwd(hashedPassword, citoyen.getIdCitoyen());

		request.getSession().setAttribute("user", citoyen);

		request.setAttribute("success", "Votre mot de passe a été mis à jour avec succès !");
		request.getRequestDispatcher("/views/Citoyen/ProfilCitoyen.jsp").forward(request, response);
	}

	protected void updateCitoyen(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		Long idCitoyen = Long.parseLong(request.getParameter("idCitoyen").trim());
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
		//ce mdp est hashé , donc ça ne va pas etre changé
		String password = oldCitoyen.getMotDePasse();
		
		Citoyen updatedCitoyen = new Citoyen(idCitoyen,nom,prenom,cin,lieuNaissance,
    			telephone, email, password, dateNaissance,idRegionM);
		
		citoyenDao.updateCitoyen(updatedCitoyen);
		request.getSession().setAttribute("user", updatedCitoyen);
		request.getRequestDispatcher("/views/Citoyen/ProfilCitoyen.jsp").forward(request, response);

	}

}
