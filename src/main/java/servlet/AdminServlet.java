package servlet;

import dao.AdminCRUDImpl;
import metier.Administrateur;
import utils.PasswordHashUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {

    private AdminCRUDImpl adminDao = new AdminCRUDImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	  // Récupérer la session sans en créer une nouvelle
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("admin") == null) {
            // Rediriger vers la page de login si pas d'admin en session
            response.sendRedirect(request.getContextPath() + "/loginAdmin.jsp");
            return;
        }

        // Récupérer l'admin depuis la session
        Administrateur admin = (Administrateur) session.getAttribute("admin");

        // Envoyer l'admin à la JSP
        request.setAttribute("admin", admin);

        // Forward vers la page ProfilAdmin.jsp
        request.getRequestDispatcher("/views/admin/ProfilAdmin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        Long idAdmin = Long.parseLong(request.getParameter("idAdmin").trim());
        Administrateur admin = adminDao.getById(idAdmin);

        switch (action) {

            case "insertion":
                admin.setNom(request.getParameter("nom"));
                admin.setPrenom(request.getParameter("prenom"));
                admin.setCin(request.getParameter("cin"));
                admin.setLieuNaissance(request.getParameter("lieuNaissance"));
                admin.setTelephone(request.getParameter("telephone"));
                admin.setEmail(request.getParameter("email"));

                adminDao.updateAdmin(admin);

                request.getSession().setAttribute("admin", admin);
                response.sendRedirect("AdminServlet");
                break;

            case "changerMdp":
                updatePassword(request, response, admin);
                break;
        }
    }

    private void updatePassword(HttpServletRequest request, HttpServletResponse response, Administrateur admin)
            throws ServletException, IOException {

        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");

        // Vérification du mot de passe actuel
        if (!PasswordHashUtil.verifyPassword(currentPassword, admin.getMotDePasse())) {
            request.setAttribute("error", "Mot de passe actuel incorrect !");
            request.setAttribute("admin", admin);
            request.getRequestDispatcher("/views/admin/ProfilAdmin.jsp").forward(request, response);
            return;
        }

        // Vérification de la correspondance des nouveaux mots de passe
        if (!newPassword.equals(confirmPassword)) {
            request.setAttribute("error", "Les mots de passe ne correspondent pas !");
            request.setAttribute("admin", admin);
            request.getRequestDispatcher("/views/admin/ProfilAdmin.jsp").forward(request, response);
            return;
        }

        // Hash du nouveau mot de passe
        String hashedPassword = PasswordHashUtil.hashPassword(newPassword);

        // Mise à jour dans la DB
        adminDao.updatePwd(hashedPassword, admin.getIdAdmin());

        // Recharger l'admin et mettre à jour la session
        admin = adminDao.getById(admin.getIdAdmin());
        request.getSession().setAttribute("admin", admin);

        // Message de succès
        request.setAttribute("success", "Votre mot de passe a été mis à jour avec succès !");
        request.setAttribute("admin", admin);
        request.getRequestDispatcher("/views/admin/ProfilAdmin.jsp").forward(request, response);
    }
}
