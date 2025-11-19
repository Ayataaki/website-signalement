<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Mes Signalements - Espace Citoyen</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
<style>
        :root {
            --primary-color: #1976D2;
            --primary-hover: #1565C0;
            --danger-color: #F44336;
            --warning-color: #FF9800;
            --success-color: #43A047;
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

        .stat-card {
            background: white;
            border-radius: 12px;
            padding: 1.5rem;
            box-shadow: 0 2px 8px rgba(0,0,0,0.1);
            transition: transform 0.3s ease;
        }

        .stat-card:hover {
            transform: translateY(-2px);
        }

        .stat-icon {
            width: 48px;
            height: 48px;
            border-radius: 12px;
            display: flex;
            align-items: center;
            justify-content: center;
            margin-bottom: 1rem;
        }

        .stat-icon.primary { background-color: rgba(25, 118, 210, 0.1); color: var(--primary-color); }
        .stat-icon.danger { background-color: rgba(244, 67, 54, 0.1); color: var(--danger-color); }
        .stat-icon.warning { background-color: rgba(255, 152, 0, 0.1); color: var(--warning-color); }
        .stat-icon.success { background-color: rgba(67, 160, 71, 0.1); color: var(--success-color); }

        .activity-item {
            padding: 1rem;
            border-radius: 8px;
            background-color: #f8f9fa;
            margin-bottom: 0.5rem;
            border-left: 4px solid transparent;
        }

        .activity-item.new { border-left-color: var(--primary-color); }
        .activity-item.in-progress { border-left-color: var(--warning-color); }
        .activity-item.resolved { border-left-color: var(--success-color); }

        .status-dot {
            width: 8px;
            height: 8px;
            border-radius: 50%;
            display: inline-block;
            margin-right: 0.5rem;
        }

        .status-new { background-color: var(--primary-color); }
        .status-pending { background-color: var(--warning-color); }
        .status-resolved { background-color: var(--success-color); }

        .user-info {
            background: white;
            padding: 1rem;
            border-radius: 12px;
            margin-bottom: 1rem;
        }
        .report-card {
		    border-radius: 12px;
		    padding: 1.5rem;
		    box-shadow: 0 2px 6px rgba(0,0,0,0.05);
		    transition: transform 0.3s ease;
		    background-color: #ffffff; /* fallback */
		}
		
		/* Couleurs par statut */
		.report-card.new {
		    background-color: rgba(25, 118, 210, 0.1); /* bleu clair */
		    border-left: 6px solid var(--primary-color);
		}
		
		.report-card.processing {
		    background-color: rgba(255, 152, 0, 0.1); /* orange clair */
		    border-left: 6px solid var(--warning-color);
		}
		
		.report-card.final {
		    background-color: rgba(67, 160, 71, 0.1); /* vert clair */
		    border-left: 6px solid var(--success-color);
		}
        .report-card:hover {
		    transform: translateY(-2px);
		    box-shadow: 0 4px 12px rgba(0,0,0,0.1);
		}
        .badge-status {
    font-size: 0.85rem;
    padding: 0.4rem 0.75rem;
    border-radius: 20px;
    font-weight: 500;
    color: #212121; /* texte noir */
    background-color: #e0e0e0; /* gris clair par défaut */
}

/* Couleurs spécifiques par statut */
.badge-new {
    background-color: #e3f2fd; /* bleu très clair */
}

.badge-processing {
    background-color: #fff3e0; /* orange très clair */
}

.badge-final {
    background-color: #e8f5e9; /* vert très clair */
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
        }
</style>

</head>
<body>
<jsp:include page="sidebar.jsp">
    <jsp:param name="activePage" value="reports"/>
</jsp:include>

<div class="main-content">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h1 class="h3 mb-0">Mes Signalements</h1>
        <div class="d-flex gap-2">
            <a href="FormSingnalement.jsp" class="btn btn-primary" style="border-radius:50px; padding:0.75rem 1.5rem;">
                <i class="fas fa-plus me-2"></i>Nouveau signalement
            </a>
        </div>
    </div>

    <div class="row">
    <c:forEach var="s" items="${sessionScope.signalements}">
        <div class="col-md-6 col-lg-4 mb-4">
            <div class="report-card ${s.statut.label} fade-in">
                <div class="d-flex justify-content-between align-items-start mb-3">
                    <span class="badge badge-status 
                        <c:choose>
                            <c:when test="${s.statut.label eq 'new'}">badge-new</c:when>
                            <c:when test="${s.statut.label eq 'processing'}">badge-processing</c:when>
                            <c:when test="${s.statut.label eq 'final'}">badge-final</c:when>
                        </c:choose>">
<%--                         ${s.statut.label} --%>
						<c:choose>
						    <c:when test="${s.statut.label eq 'new'}">Nouveau</c:when>
						    <c:when test="${s.statut.label eq 'processing'}">En cours</c:when>
						    <c:when test="${s.statut.label eq 'final'}">Résolu</c:when>
						    <c:otherwise>Inconnu</c:otherwise>
						</c:choose>
                    </span>
                    <small class="text-muted"><fmt:formatDate value="${s.dateCreation}" pattern="dd MMM yyyy"/></small>
                </div>
                <h6 class="fw-semibold mb-2">${s.designation}</h6>
                <p class="text-muted small mb-3">${s.description}</p>
                <div class="d-flex justify-content-between align-items-center">
                	<h7 class="fw-semibold mb-2">${s.localisation}</h7>
<%--                     <span class="badge bg-light text-dark" style="border:1px solid #e0e0e0;">${s.localisation}</span> --%>
                    <button class="btn btn-sm btn-outline-primary" data-bs-toggle="modal" data-bs-target="#reportModal${s.idSignalement}" style="border-radius:20px; padding:0.25rem 1rem;">
                        Détails
                    </button>
                </div>
                <c:if test="${not empty s.imagePath}">
                    <img src="${pageContext.request.contextPath}/${s.imagePath}" class="report-image mt-2" alt="Signalement">
                </c:if>
            </div>
        </div>

        <!-- Modal dynamique -->
        <div class="modal fade" id="reportModal${s.idSignalement}" tabindex="-1">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">${s.designation}</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                    </div>
                    <div class="modal-body">
                        <div class="row mb-4">
                            <div class="col-md-8">
                                <c:if test="${not empty s.imagePath}">
                                    <img src="${pageContext.request.contextPath}/${s.imagePath}" class="report-image" alt="Signalement">
                                </c:if>
                            </div>
                            <div class="col-md-4">
                                <div class="mb-3"><strong>Statut:</strong>
                                    <span class="badge 
                                        <c:choose>
                                            <c:when test="${s.statut.label eq 'new'}">badge-new</c:when>
                                            <c:when test="${s.statut.label eq 'processing'}">badge-processing</c:when>
                                            <c:when test="${s.statut.label eq 'final'}">badge-final</c:when>
                                        </c:choose> ms-2">
                                        ${s.statut.label}
                                    </span>
                                </div>
                                <div class="mb-3"><strong>Localisation:</strong> <span class="ms-2">${s.localisation}</span></div>
                                <div class="mb-3"><strong>Date:</strong> <span class="ms-2"><fmt:formatDate value="${s.dateCreation}" pattern="dd MMM yyyy"/></span></div>
                                <div class="mb-3"><strong>Commentaire:</strong> <span class="ms-2">${s.commentaire}</span></div>
                            </div>
                        </div>
                        <h6 class="fw-semibold mb-3">Description détaillée</h6>
                        <p class="mb-4">${s.description}</p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Fermer</button>
                    </div>
                </div>
            </div>
        </div>
    </c:forEach>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<script>
    // Animation fade-in des cartes
    document.addEventListener('DOMContentLoaded', function() {
        const cards = document.querySelectorAll('.report-card');
        cards.forEach((card, index) => {
            card.style.animationDelay = `${index * 0.1}s`;
            card.classList.add('fade-in');
        });
    });
</script>
</body>
</html>
