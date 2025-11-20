package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import metier.Municipal;
import metier.Region;

import java.io.IOException;
import java.util.List;


import dao.IMunicipalCRUD;
import dao.IRegionCRUD;
import dao.MunicipalCRUDImpl;
import dao.RegionCRUDImpl;

@WebServlet("/MunicipalServlet")
public class MunicipalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private IRegionCRUD regionDao = new RegionCRUDImpl();
	private IMunicipalCRUD municipalDao = new MunicipalCRUDImpl();
	

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		doPost(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
		String action = request.getParameter("action");

		switch (action) {

		case "update":

			updateMunicipal(request, response);

			getAllMun(request, response);

			request.getRequestDispatcher("/views/admin/GererMunicipaux.jsp").forward(request, response);

			break;
		
		case "delete":
			
			deleteMunicipal(request, response);

			getAllMun(request, response);

			request.getRequestDispatcher("/views/admin/GererMunicipaux.jsp").forward(request, response);

			break;
			
		case "create":
			
			createMunicipal(request, response);

			getAllMun(request, response);

			request.getRequestDispatcher("/views/admin/GererMunicipaux.jsp").forward(request, response);

			break;
			
		}

    
    }

	private void deleteMunicipal(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("idMunicipal");
    	Long idMun = Long.parseLong(id.trim());
    
    	municipalDao.deleteMunicipal(idMun);
	}

	private void updateMunicipal(HttpServletRequest request, HttpServletResponse response) {
		
		String id = request.getParameter("idMunicipal");
    	Long idMun = Long.parseLong(id.trim());
		String nom = request.getParameter("nom");
        String typeMunicipal = request.getParameter("type");
        String idR = request.getParameter("idRegion");
    	Long idRegion = Long.parseLong(idR.trim());
        
        Municipal m = municipalDao.getById(idMun);
        m.setNom(nom);
        m.setTypeMunicipal(typeMunicipal);
        m.setIdRegion(idRegion);
        
        municipalDao.updateMunicipal(m);
        
        
	}

	private void getAllMun(HttpServletRequest request, HttpServletResponse response) {

		List<Municipal> municipaux = municipalDao.getAll();

		request.getSession().setAttribute("municipaux", municipaux);
	}

	private void createMunicipal(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nom = request.getParameter("nom");
        String typeMunicipal = request.getParameter("typeMunicipal");
        String idRegion = request.getParameter("idRegion");
        
        if (idRegion == null || idRegion.isEmpty()) {
            request.setAttribute("messageErreur", "Veuillez sélectionner une région valide.");
        } else {
            try {
            	Long idRegionM = Long.parseLong(idRegion.trim());
                Municipal municipal = new Municipal(nom, typeMunicipal, idRegionM);
                municipalDao.createMunicipal(municipal);
                request.setAttribute("messageSucces", "Municipal ajouté avec succès !");
            } catch (NumberFormatException e) {
                request.setAttribute("messageErreur", "Erreur : ID région invalide (non numérique)");
            } catch (Exception e) {
                request.setAttribute("messageErreur", "Erreur lors de l'ajout : " + e.getMessage());
            }
        }
                

        List<Region> regions = regionDao.getAll();
        request.setAttribute("regions", regions);
        
        
	}
    
    

}
