package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import metier.Region;

import java.io.IOException;
import java.util.List;

import dao.IRegionCRUD;
import dao.RegionCRUDImpl;

/**
 * Servlet implementation class RegionServlet
 */
@WebServlet("/RegionServlet")
public class RegionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private IRegionCRUD regionDao = new RegionCRUDImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");

		switch (action) {

		case "update":

			updateRegion(request, response);

			getAll(request, response);

			request.getRequestDispatcher("/views/admin/GererRegion.jsp").forward(request, response);

			break;
		
		case "delete":
			
			deleteRegion(request, response);
			
			getAll(request, response);

			request.getRequestDispatcher("/views/admin/GererRegion.jsp").forward(request, response);

			break;
			
		case "create":
			
			createRegion(request, response);
			
			getAll(request, response);

			request.getRequestDispatcher("/views/admin/GererRegion.jsp").forward(request, response);

			break;
			
		}

	}

	private void getAll(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        List<Region> regions = regionDao.getAll();
        request.getSession().setAttribute("regions", regions);
    }	

	private void createRegion(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		String nom = request.getParameter("nom");
		String superficie = request.getParameter("superficie");
		String population = request.getParameter("population");
		String capitalRegional = request.getParameter("capitaleRegionale");
		float s = 0;
		int p = 0;
		try {
			s = Float.parseFloat(superficie);
			p = Integer.parseInt(population);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		regionDao.createRegion(new Region(s, p, nom, capitalRegional));
		
	}
	
	private void deleteRegion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String idStr = request.getParameter("idRegion");        
        long id = Long.parseLong(idStr);
        regionDao.deleteRegion(id);

    }
	
	private void updateRegion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Récupérer les paramètres
        String idStr = request.getParameter("idRegion");        
        long id = Long.parseLong(idStr);
        String nom = request.getParameter("nom");
        String superficieStr = request.getParameter("superficie");
        String populationStr = request.getParameter("population");
        String capitaleRegionale = request.getParameter("capitaleRegionale");
        
        
            float superficie = Float.parseFloat(superficieStr);
            int population = Integer.parseInt(populationStr);
            
            // Créer l'objet région avec l'ID
            Region region = regionDao.getById(id);
            region.setCapitaleRegionale(capitaleRegionale);
            region.setNom(nom);
            region.setPopulation(population);
            region.setSuperficie(superficie);
            
            // Mettre à jour
            regionDao.updateRegion(region);
            
    }
		
}
