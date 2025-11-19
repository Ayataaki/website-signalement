package servlet;

import java.io.IOException;
import java.util.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import dao.SignalementCRUDImpl;
import metier.Administrateur;
import metier.Employe;
import metier.Signalement;
import metier.Statut;

@WebServlet("/EmployeDashboardServlet")
public class CorbeilleEmployeDashboardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private SignalementCRUDImpl signalementDao;

    @Override
    public void init() throws ServletException {
        signalementDao = new SignalementCRUDImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    	
    	HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("employe") == null) {
            // Rediriger vers la page de login si pas d'admin en session
            response.sendRedirect(request.getContextPath() + "/loginAdmin.jsp");
            return;
        }

        // Récupérer l'admin depuis la session
        Employe employe = (Employe) session.getAttribute("employe");
        
        
        
        
        
        // ID du service municipal connecté (à adapter plus tard avec session)
        //Long idMunicipal = 1L;

        // 🔹 Récupération de tous les signalements de cette municipalité
        List<Signalement> signalements = signalementDao.getAllSignalementsByServiceMunicipal(idEmploye);

        // --- 📊 Calcul des statistiques ---
        int totalReports = signalements.size();
        int nouveaux = 0;
        int enCours = 0;
        int resolus = 0;

        for (Signalement s : signalements) {
            if (s.getStatut() == Statut.NEW) {
                nouveaux++;
            } else if (s.getStatut() == Statut.PROCESSING) {
                enCours++;
            } else if (s.getStatut() == Statut.FINAL) {
                resolus++;
            }
        }

        // 🔹 Récupération des 5 signalements les plus récents
        List<Signalement> signalementsRecents = new ArrayList<>(signalements);
        signalementsRecents.sort((s1, s2) -> s2.getDateCreation().compareTo(s1.getDateCreation()));
        if (signalementsRecents.size() > 5) {
            signalementsRecents = signalementsRecents.subList(0, 5);
        }

        // 🔹 Statistiques par type (designation)
        Map<String, Integer> parType = new HashMap<>();
        for (Signalement s : signalements) {
            String type = (s.getDesignation() != null && !s.getDesignation().isEmpty()) 
                    ? s.getDesignation() 
                    : "Non spécifié";
            parType.put(type, parType.getOrDefault(type, 0) + 1);
        }

        // --- 📤 Envoi des données vers la JSP ---
        request.setAttribute("totalReports", totalReports);
        request.setAttribute("nouveaux", nouveaux);
        request.setAttribute("enCours", enCours);
        request.setAttribute("resolus", resolus);
        request.setAttribute("signalementsRecents", signalementsRecents);
        request.setAttribute("parType", parType);

        // 🔹 Redirection vers la page JSP
        request.getRequestDispatcher("municipale/Dashboard.jsp").forward(request, response);
    }
}
