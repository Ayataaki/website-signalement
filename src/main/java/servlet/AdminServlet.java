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

    	HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("admin") == null) {
            response.sendRedirect(request.getContextPath() + "/loginAdmin.jsp");
            return;
        }

        Administrateur admin = (Administrateur) session.getAttribute("admin");

        request.setAttribute("admin", admin);

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

        if (!PasswordHashUtil.verifyPassword(currentPassword, admin.getMotDePasse())) {
            request.setAttribute("error", "Mot de passe actuel incorrect !");
            request.setAttribute("admin", admin);
            request.getRequestDispatcher("/views/admin/ProfilAdmin.jsp").forward(request, response);
            return;
        }

        if (!newPassword.equals(confirmPassword)) {
            request.setAttribute("error", "Les mots de passe ne correspondent pas !");
            request.setAttribute("admin", admin);
            request.getRequestDispatcher("/views/admin/ProfilAdmin.jsp").forward(request, response);
            return;
        }

        String hashedPassword = PasswordHashUtil.hashPassword(newPassword);

        adminDao.updatePwd(hashedPassword, admin.getIdAdmin());

        admin = adminDao.getById(admin.getIdAdmin());
        request.getSession().setAttribute("admin", admin);

        request.setAttribute("success", "Votre mot de passe a été mis à jour avec succès !");
        request.setAttribute("admin", admin);
        request.getRequestDispatcher("/views/admin/ProfilAdmin.jsp").forward(request, response);
    }
}
