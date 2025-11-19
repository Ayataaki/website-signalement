<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gérer les Signalements</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <style>
        :root {
            --primary-color: #1976D2;
            --primary-hover: #1565C0;
            --danger-color: #F44336;
            --warning-color: #FF9800;
            --success-color: #43A047;
            --info-color: #00ACC1;
            --text-dark: #212121;
            --text-gray: #666;
        }

        body {
            font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, sans-serif;
            background-color: #f8f9fa;
            color: var(--text-dark);
        }

        .sidebar {
            background: white;
            min-height: 100vh;
            box-shadow: 2px 0 5px rgba(0,0,0,0.1);
            position: fixed;
            width: 280px;
            display: flex;
            flex-direction: column;
        }

        .main-content {
            margin-left: 280px;
            padding: 2rem;
        }

        .logo {
            padding: 2rem 1.5rem 1rem;
            border-bottom: 1px solid #e0e0e0;
            margin-bottom: 1rem;
        }

        .nav-link {
            color: var(--text-dark);
            padding: 0.75rem 1.5rem;
            margin: 0.25rem 0.5rem;
            border-radius: 8px;
            transition: all 0.3s ease;
        }

        .nav-link:hover, .nav-link.active {
            background-color: rgba(25, 118, 210, 0.1);
            color: var(--primary-color);
        }

        .nav-link i {
            width: 20px;
            margin-right: 0.75rem;
        }

        .page-header {
            margin-bottom: 2rem;
        }

        .page-title {
            font-size: 1.75rem;
            font-weight: 600;
            color: var(--text-dark);
            margin-bottom: 0.5rem;
        }

        .search-section {
            background: white;
            border-radius: 12px;
            padding: 1.5rem;
            box-shadow: 0 2px 8px rgba(0,0,0,0.1);
            margin-bottom: 1.5rem;
        }

        .form-control, .form-select {
            border-radius: 8px;
            border: 1px solid #e0e0e0;
            padding: 0.625rem 0.875rem;
            transition: all 0.3s ease;
        }

        .form-control:focus, .form-select:focus {
            border-color: var(--primary-color);
            box-shadow: 0 0 0 0.2rem rgba(25, 118, 210, 0.1);
        }

        .btn-primary {
            background-color: var(--primary-color);
            border: none;
            border-radius: 8px;
            padding: 0.625rem 1.5rem;
            font-weight: 500;
            transition: all 0.3s ease;
        }

        .btn-primary:hover {
            background-color: var(--primary-hover);
            transform: translateY(-1px);
            box-shadow: 0 4px 12px rgba(25, 118, 210, 0.3);
        }

        .table-card {
            background: white;
            border-radius: 12px;
            box-shadow: 0 2px 8px rgba(0,0,0,0.1);
            overflow: hidden;
        }

        .table {
            margin-bottom: 0;
        }

        .table thead th {
            background-color: #f8f9fa;
            border-bottom: 2px solid #e0e0e0;
            font-weight: 600;
            color: var(--text-dark);
            padding: 1rem;
            font-size: 0.875rem;
            text-transform: uppercase;
            letter-spacing: 0.5px;
        }

        .table tbody td {
            padding: 1rem;
            vertical-align: middle;
            color: var(--text-dark);
        }

        .table tbody tr {
            transition: background-color 0.2s ease;
        }

        .table tbody tr:hover {
            background-color: #f8f9fa;
        }

        .badge {
            padding: 0.4rem 0.85rem;
            border-radius: 50px;
            font-weight: 500;
            font-size: 0.75rem;
        }

        .badge-new {
            background-color: var(--danger-color);
            color: white;
        }

        .badge-processing {
            background-color: var(--warning-color);
            color: white;
        }

        .badge-final {
            background-color: var(--success-color);
            color: white;
        }

        .btn-sm {
            border-radius: 6px;
            padding: 0.375rem 0.75rem;
            font-size: 0.813rem;
            font-weight: 500;
        }

        .btn-outline-info {
            border-color: var(--info-color);
            color: var(--info-color);
        }

        .btn-outline-info:hover {
            background-color: var(--info-color);
            color: white;
        }

        .btn-outline-warning {
            border-color: var(--warning-color);
            color: var(--warning-color);
        }

        .btn-outline-warning:hover {
            background-color: var(--warning-color);
            color: white;
        }

        .btn-outline-danger {
            border-color: var(--danger-color);
            color: var(--danger-color);
        }

        .btn-outline-danger:hover {
            background-color: var(--danger-color);
            color: white;
        }

        .empty-state {
            text-align: center;
            padding: 3rem 1rem;
            color: var(--text-gray);
        }

        .empty-state i {
            font-size: 4rem;
            margin-bottom: 1rem;
            opacity: 0.3;
        }

        .user-info {
            background: white;
            padding: 1rem;
            border-radius: 12px;
            margin-bottom: 1rem;
        }

        /* Styles pour la modal de détails */
        .modal-backdrop.show {
            backdrop-filter: blur(8px);
            background-color: rgba(0, 0, 0, 0.5);
        }

        .modal-content {
            border-radius: 1.5rem;
            border: none;
            box-shadow: 0 20px 60px rgba(0, 0, 0, 0.3);
            overflow: hidden;
        }

        .modal-header {
            background: linear-gradient(135deg, var(--primary-color), var(--primary-hover));
            color: white;
            border-bottom: none;
            padding: 1.5rem 2rem;
        }

        .modal-header .btn-close {
            filter: invert(1);
            opacity: 0.8;
        }

        .modal-header .btn-close:hover {
            opacity: 1;
        }

        .modal-title {
            font-weight: 600;
            font-size: 1.5rem;
        }

        .modal-body {
            padding: 2rem;
        }

        .detail-section {
            margin-bottom: 1.5rem;
        }

        .detail-label {
            font-weight: 600;
            color: var(--text-dark);
            margin-bottom: 0.5rem;
            display: flex;
            align-items: center;
            gap: 0.5rem;
        }

        .detail-value {
            color: var(--text-gray);
            background: #f8f9fa;
            padding: 0.75rem 1rem;
            border-radius: 0.75rem;
            border-left: 4px solid var(--primary-color);
        }

        .detail-image {
            max-width: 100%;
            border-radius: 0.75rem;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
            transition: transform 0.3s ease;
        }

        .detail-image:hover {
            transform: scale(1.02);
        }

        .image-placeholder {
            background: #f8f9fa;
            border: 2px dashed #dee2e6;
            border-radius: 0.75rem;
            padding: 2rem;
            text-align: center;
            color: var(--text-gray);
        }

        .image-placeholder i {
            font-size: 3rem;
            margin-bottom: 1rem;
            opacity: 0.5;
        }

        @media (max-width: 768px) {
            .sidebar {
                width: 100%;
                position: relative;
                min-height: auto;
            }
            .main-content {
                margin-left: 0;
            }
            .modal-body {
                padding: 1.5rem;
            }
        }
    </style>
</head>
<body>

<jsp:include page="sidebarMunicipal.jsp">
    <jsp:param name="activePage" value="reports"/>
</jsp:include>

<div class="main-content">

    <div class="page-header">
        <h1 class="page-title">
            <i class="fas fa-clipboard-list me-2" style="color: var(--primary-color);"></i>
            Gérer les Signalements
        </h1>
        <p class="text-muted mb-0">Recherchez et gérez tous les signalements</p>
    </div>

    <!-- Barre de recherche -->
		<div class="search-section">
			<form action="${pageContext.request.contextPath}/SignalementServlet"
				method="post" class="row g-2 align-items-end">
				<input type="hidden" name="action" value="recherche">
				
				<div class="col-md-8">
					<label class="form-label fw-semibold mb-2">Rechercher un
						signalement</label> 
					<input type="text" class="form-control"
						placeholder="Entrez une désignation ou description..."
						name="search" value="${param.search}">
				</div>
				<div class="col-md-4 d-flex align-items-end">
					<button type="submit" class="btn btn-primary w-100">
						<i class="fas fa-search me-2"></i> Rechercher
					</button>
				</div>
			</form>
		</div>

		<!-- Tableau des signalements -->
    <div class="table-card">
        <div class="table-responsive">
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>Désignation</th>
                        <th>Localisation</th>
                        <th>Statut</th>
                        <th>Date</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="s" items="${sessionScope.signalements}">
                        <tr>                    
                            <td>${s.designation}</td>
                            <td>
                                <i class="fas fa-map-marker-alt me-1 text-muted"></i>
                                ${s.localisation}
                            </td>
                            <td>
                                <span class="badge badge-${s.statut.label eq 'new' ? 'new' : (s.statut.label eq 'processing' ? 'processing' : 'final')}">
                                    ${s.statut.label eq  'new' ? 'Nouveau' : (s.statut.label eq 'processing' ? 'En cours' : 'Résolu')}
                                </span>
                            </td>
                            <td>
                                <i class="fas fa-calendar-alt me-1 text-muted"></i>
                                ${s.dateCreation}
                            </td>
                            <td>
                                <div class="btn-group" role="group">
                                    <button type="button" 
                                            class="btn btn-sm btn-outline-info"
                                            data-bs-toggle="modal"
                                            data-bs-target="#detailModal"
                                            data-id="${s.idSignalement}"
                                            data-designation="${s.designation}"
                                            data-description="${s.description}"
                                            data-localisation="${s.localisation}"
                                            data-statut="${s.statut.label}"
                                            data-commentaire="${s.commentaire}"
                                            data-imagepath="${s.imagePath}"
                                            data-datecreation="${s.dateCreation}"
                                            title="Voir les détails">
                                        <i class="fas fa-eye"></i>
                                    </button>
                                    <button type="button" 
                                       class="btn btn-sm btn-outline-warning"
                                       data-bs-toggle="modal"
                                       data-bs-target="#editModal"
                                       data-id="${s.idSignalement}"
                                       data-statut="${s.statut.label}">
                                       <i class="fas fa-edit"></i>
                                     </button>

                                    <a href="${pageContext.request.contextPath}/SignalementServlet?action=delete&idSignalement=${s.idSignalement}" 
                                       class="btn btn-sm btn-outline-danger" 
                                       onclick="return confirm('Êtes-vous sûr de vouloir supprimer ce signalement ?')"
                                       title="Supprimer">
                                        <i class="fas fa-trash"></i>
                                    </a>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <c:if test="${empty sessionScope.signalements}">
                <div class="empty-state">
                    <i class="fas fa-inbox"></i>
                    <p class="mb-0">Aucun signalement trouvé.</p>
                    <small class="text-muted">Essayez de modifier vos critères de recherche</small>
                </div>
            </c:if>
        </div>
    </div>
</div>

<!-- Modal de détails du signalement -->
<div class="modal fade" id="detailModal" tabindex="-1" aria-labelledby="detailModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">
                    <i class="fas fa-clipboard-list me-2"></i>
                    Détails du Signalement
                </h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Fermer"></button>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-6">
                        <div class="detail-section">
                            <div class="detail-label">
                                <i class="fas fa-hashtag"></i>
                                ID du Signalement
                            </div>
                            <div class="detail-value" id="detail-id">
                                <!-- ID dynamique -->
                            </div>
                        </div>

                        <div class="detail-section">
                            <div class="detail-label">
                                <i class="fas fa-tag"></i>
                                Désignation
                            </div>
                            <div class="detail-value" id="detail-designation">
                                <!-- Désignation dynamique -->
                            </div>
                        </div>

                        <div class="detail-section">
                            <div class="detail-label">
                                <i class="fas fa-align-left"></i>
                                Description
                            </div>
                            <div class="detail-value" id="detail-description">
                                <!-- Description dynamique -->
                            </div>
                        </div>

                        <div class="detail-section">
                            <div class="detail-label">
                                <i class="fas fa-map-marker-alt"></i>
                                Localisation
                            </div>
                            <div class="detail-value" id="detail-localisation">
                                <!-- Localisation dynamique -->
                            </div>
                        </div>
                    </div>

                    <div class="col-md-6">
                        <div class="detail-section">
                            <div class="detail-label">
                                <i class="fas fa-flag"></i>
                                Statut
                            </div>
                            <div class="detail-value">
                                <span class="badge" id="detail-statut">
                                    <!-- Statut dynamique -->
                                </span>
                            </div>
                        </div>

                        <div class="detail-section">
                            <div class="detail-label">
                                <i class="fas fa-comment"></i>
                                Commentaire
                            </div>
                            <div class="detail-value" id="detail-commentaire">
                                <!-- Commentaire dynamique -->
                            </div>
                        </div>

                        <div class="detail-section">
                            <div class="detail-label">
                                <i class="fas fa-calendar-alt"></i>
                                Date de Création
                            </div>
                            <div class="detail-value" id="detail-date">
                                <!-- Date dynamique -->
                            </div>
                        </div>

                        <div class="detail-section">
                            <div class="detail-label">
                                <i class="fas fa-image"></i>
                                Image
                            </div>
                            <div id="detail-image-container">
                                <!-- Image dynamique -->
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
                    <i class="fas fa-times me-2"></i>
                    Fermer
                </button>
            </div>
        </div>
    </div>
</div>

<!-- Modal de modification du statut -->
<div class="modal fade" id="editModal" tabindex="-1" aria-labelledby="editModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content" style="border-radius: 1rem; backdrop-filter: blur(5px);">
      <div class="modal-header ">
        <h5 class="modal-title " id="editModalLabel">
          <i class="fas fa-clipboard-list me-2"></i>Modifier le statut du signalement
        </h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Fermer"></button>
      </div>

      <form action="${pageContext.request.contextPath}/SignalementServlet" method="post">
        <input type="hidden" name="action" value="update">
        <input type="hidden" id="idSignalementInput" name="idSignalement">

        <div class="modal-body">
          <div class="mb-3">
            <label for="statutSelect" class="form-label fw-semibold">
              <i class="fas fa-flag me-2 text-secondary"></i>Statut
            </label>
            <select name="statut" id="statutSelect" class="form-select shadow-sm">
              <option value="new">Nouveau</option>
              <option value="processing">En cours</option>
              <option value="final">Résolu</option>
            </select>
          </div>
        </div>

        <div class="modal-footer">
          <button type="submit" class="btn btn-success">
            <i class="fas fa-check me-2"></i>Mettre à jour
          </button>
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
            <i class="fas fa-times me-2"></i>Annuler
          </button>
        </div>
      </form>
    </div>
  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>

<script>
document.addEventListener('DOMContentLoaded', function () {
    // Gestion de la modal d'édition
    var editModal = document.getElementById('editModal');
    editModal.addEventListener('show.bs.modal', function (event) {
        var button = event.relatedTarget;
        var id = button.getAttribute('data-id');
        var statut = button.getAttribute('data-statut');
        
        var inputId = editModal.querySelector('#idSignalementInput');
        inputId.value = id;
        
        var selectStatut = editModal.querySelector('#statutSelect');
        selectStatut.value = statut;
    });

    // Gestion de la modal de détails
    var detailModal = document.getElementById('detailModal');
    detailModal.addEventListener('show.bs.modal', function (event) {
        var button = event.relatedTarget;
        
        // Récupération des données
        var id = button.getAttribute('data-id');
        var designation = button.getAttribute('data-designation');
        var description = button.getAttribute('data-description');
        var localisation = button.getAttribute('data-localisation');
        var statut = button.getAttribute('data-statut');
        var commentaire = button.getAttribute('data-commentaire');
        var imagePath = button.getAttribute('data-imagepath');
        var dateCreation = button.getAttribute('data-datecreation');

        // Mise à jour du contenu
        document.getElementById('detail-id').textContent = '#' + id;
        document.getElementById('detail-designation').textContent = designation || 'Non spécifié';
        document.getElementById('detail-description').textContent = description || 'Aucune description';
        document.getElementById('detail-localisation').textContent = localisation || 'Non spécifié';
        document.getElementById('detail-commentaire').textContent = commentaire || 'Aucun commentaire';
        document.getElementById('detail-date').textContent = dateCreation || 'Date non disponible';

        // Gestion du statut avec badge coloré
        var statutElement = document.getElementById('detail-statut');
        statutElement.textContent = getStatutLabel(statut);
        statutElement.className = 'badge badge-' + statut;

        // Gestion de l'image
        var imageContainer = document.getElementById('detail-image-container');
        if (imagePath && imagePath !== 'null') {
            imageContainer.innerHTML = '<img src="' + imagePath + '" alt="Image du signalement" class="detail-image">';
        } else {
            imageContainer.innerHTML = '<div class="image-placeholder"><i class="fas fa-image"></i><p>Aucune image disponible</p></div>';
        }
    });

    function getStatutLabel(statut) {
        switch(statut) {
            case 'new': return 'Nouveau';
            case 'processing': return 'En cours';
            case 'final': return 'Résolu';
            default: return statut;
        }
    }
});
</script>

</body>
</html>