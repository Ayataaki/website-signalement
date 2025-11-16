package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import metier.Signalement;
import metier.Statut;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;

import dao.ISignalementCRUD;
import dao.SignalementCRUDImpl;

/**
 * Servlet implementation class SignalementServlet
 */
@WebServlet("/SignalementServlet")
@MultipartConfig(maxFileSize = 1024 * 1024 * 5) 
public class SignalementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ISignalementCRUD signalementDao = new SignalementCRUDImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");

		switch (action) {

		case "create":

			storeSignalement(request, response);

			getAllSign(request, response);

			request.getRequestDispatcher("/views/Citoyen/DashboardCitoyen.jsp").forward(request, response);

			break;

		case "update":
			
			System.out.println("ACTION = " + request.getParameter("action"));
			System.out.println("ID = " + request.getParameter("idSignalement"));


			updateStatut(request, response);

			getAllSign(request, response);

			response.sendRedirect(request.getContextPath() + "/views/employe/GererSignalements.jsp");

			break;
			
		case "delete":

			deleteSign(request, response);

			getAllSign(request, response);

			response.sendRedirect(request.getContextPath() + "/views/employe/GererSignalements.jsp");

			break;
			
		case "recherche":

			rechercheSign(request, response);

			response.sendRedirect(request.getContextPath() + "/views/employe/GererSignalements.jsp");

			break;
			
		case "updateAdmin":

			updateSign(request, response);

			response.sendRedirect(request.getContextPath() + "/views/admin/GererSignalement.jsp");

			break;
			
		case "deleteAdmin":

			deleteSignAdmin(request, response);

			response.sendRedirect(request.getContextPath() + "/views/admin/GererSignalement.jsp");

			break;

		}
		
	}
	
	private void deleteSignAdmin(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
    	Long idSig = Long.parseLong(id.trim());
		
    	signalementDao.deleteSignalement(idSig);
    	getAllSign(request,response);
	}

	private void updateSign(HttpServletRequest request, HttpServletResponse response) 
			throws UnsupportedEncodingException {
		
		request.setCharacterEncoding("UTF-8");

        // Récupérer les champs du formulaire
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

	private void getAllSign(HttpServletRequest request, HttpServletResponse response) {
		
		List<Signalement> signalements = signalementDao.getAll();
		
		request.getSession().setAttribute("signalements", signalements);
		
	}

	protected void storeSignalement(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");

        // Récupérer les champs du formulaire
		Long idCitoyen = Long.parseLong(request.getParameter("idCitoyen").trim());
        String designation = request.getParameter("designation");
        String description = request.getParameter("description");
        String localisation = request.getParameter("adresse");
        String commentaire = request.getParameter("commentaire");

        // Récupérer le fichier image
        Part imagePart = request.getPart("image");
        String originalFileName = Path.of(imagePart.getSubmittedFileName()).getFileName().toString();
        String uniqueFileName = UUID.randomUUID().toString() + "_" + originalFileName;

        // Définir le chemin de sauvegarde dans webapp/uploads/signalements
        String uploadDirPath = getServletContext().getRealPath("/uploads/signalements");
        File uploadDir = new File(uploadDirPath);
        if (!uploadDir.exists()) {
            boolean created = uploadDir.mkdirs();
            System.out.println("Dossier créé ? " + created);
        }

        // Créer le fichier destination
        File destinationFile = new File(uploadDir, uniqueFileName);

        // Copier le contenu du fichier
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

        // Chemin relatif pour la base de données
        String imagePath = "uploads/signalements/" + uniqueFileName;

        // Créer l'objet Signalement
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

        // où trouver l'image ? 
        // veuillez se réferer au path suivant : 
//        avant uploads enlever le slash, ça m'a causé du pb: C:\Users\Admin\Desktop\J2EE-Workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\project-j2ee\\uploads\signalements\50c8f25d-3f17-41b2-b7b5-fc3cd71bd41a_WhatsApp Image 2025-11-04 at 15.14.48.jpeg
//        // Enregistrer dans la base
        signalementDao.createSignalement(signalement);

	}

}
