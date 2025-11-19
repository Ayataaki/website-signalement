<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tableau de Bord Municipal</title>
    
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    
    <!-- Bootstrap Icons -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.0/font/bootstrap-icons.css">
    
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    
    <!-- Chart.js pour les graphiques -->
    <script src="https://cdn.jsdelivr.net/npm/chart.js@4.4.0/dist/chart.umd.min.js"></script>
    
    <style>
        :root {
            --primary-color: #1976D2;
            --primary-hover: #1565C0;
            --sidebar-width: 280px;
        }
        
        body {
            background-color: #f8f9fa;
            margin: 0;
            padding: 0;
            overflow-x: hidden;
        }
        
        /* Layout avec Sidebar */
        .layout-container {
            display: flex;
            min-height: 100vh;
        }
        
        .sidebar {
/*             width: var(--sidebar-width); */
/*             background: white; */
/*             box-shadow: 2px 0 10px rgba(0,0,0,0.1); */
/*             position: fixed; */
/*             left: 0; */
/*             top: 0; */
/*             height: 100vh; */
/*             overflow-y: auto; */
/*             display: flex; */
/*             flex-direction: column; */
/*             z-index: 1000; */

		    background: white;
		    min-height: 100vh;
		    box-shadow: 2px 0 5px rgba(0,0,0,0.1);
		    position: fixed;
		    width: 280px;
		    display: flex;
		    flex-direction: column;


        }
        
        .main-content {
            flex: 1;
            margin-left: var(--sidebar-width);
            width: calc(100% - var(--sidebar-width));
        }
        
        /* Sidebar Styles */
        .logo {
            padding: 1.5rem;
            border-bottom: 1px solid #e9ecef;
            background: linear-gradient(135deg, var(--primary-color) 0%, var(--primary-hover) 100%);
            color: white;
        }
        
        .logo h4 {
            color: white !important;
            margin: 0;
        }
        
        .logo small {
            color: rgba(255,255,255,0.9) !important;
        }
        
        .sidebar .nav-link {
            padding: 1rem 1.5rem;
            color: #495057;
            text-decoration: none;
            display: flex;
            align-items: center;
            transition: all 0.3s;
            border-left: 3px solid transparent;
        }
        
        .sidebar .nav-link i {
            margin-right: 0.75rem;
            width: 20px;
            text-align: center;
        }
        
        .sidebar .nav-link:hover {
            background-color: #f8f9fa;
            color: var(--primary-color);
            border-left-color: var(--primary-color);
        }
        
        .sidebar .nav-link.active {
            background-color: #e3f2fd;
            color: var(--primary-color);
            font-weight: 600;
            border-left-color: var(--primary-color);
        }
        
        .user-info {
/*             background: #f8f9fa; */
/*             border-radius: 8px; */
/*             padding: 0.75rem; */
			background: white;
            padding: 1rem;
            border-radius: 12px;
            margin-bottom: 1rem;
        }
        
        .avatar-sm {
            width: 36px;
            height: 36px;
            border-radius: 50%;
            background: linear-gradient(135deg, var(--primary-color), var(--primary-hover));
            display: flex;
            align-items: center;
            justify-content: center;
            color: white;
            font-size: 1rem;
        }
        
        /* Content Styles */
        .content-wrapper {
            padding: 2rem;
        }
        
        .stat-card {
            border-radius: 12px;
            border: none;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
            transition: transform 0.2s;
        }
        
        .stat-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 4px 8px rgba(0,0,0,0.15);
        }
        
        .stat-icon {
            width: 50px;
            height: 50px;
            border-radius: 10px;
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 24px;
        }
        
        .chart-container {
            position: relative;
            height: 300px;
        }
        
        /* Responsive */
        @media (max-width: 768px) {
/*             .sidebar { */
/*                 width: 70px; */
/*             } */
            
/*             .sidebar .nav-link span { */
/*                 display: none; */
/*             } */
            
/*             .sidebar .logo h4, */
/*             .sidebar .logo small, */
/*             .user-info div:not(.avatar-sm) { */
/*                 display: none; */
/*             } */
            
/*             .main-content { */
/*                 margin-left: 70px; */
/*                 width: calc(100% - 70px); */
/*             } */
            
/*             :root { */
/*                 --sidebar-width: 70px; */
/*             } */
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


  


    <div class="layout-container">
        <!-- Sidebar -->
        <jsp:include page="sidebarMunicipal.jsp">
            <jsp:param name="activePage" value="dashboard"/>
        </jsp:include>
        
        <!-- Main Content -->
        <div class="main-content">
            <div class="content-wrapper">
                <h2 class="mb-4 text-dark">
                    <i class="bi bi-speedometer2 me-2"></i>
                    Vue d'ensemble
                </h2>
                
               <!-- Cartes de statistiques -->
                <div class="row g-4 mb-4">
                    <!-- Total Reports -->
                    <div class="col-md-6 col-lg-3">
                        <div class="card stat-card">
                            <div class="card-body">
                                <div class="d-flex justify-content-between align-items-center">
                                    <div>
                                        <p class="text-muted mb-1 small">Total Signalements</p>
                                        <h3 class="mb-0 fw-bold">${totalReports}</h3>
                                        <small class="text-success">
                                            <i class="bi bi-arrow-up"></i> +5 aujourd'hui
                                        </small>
                                    </div>
                                    <div class="stat-icon bg-primary bg-opacity-10 text-primary">
                                        <i class="bi bi-file-text"></i>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                     <!-- Nouveaux -->
                    <div class="col-md-6 col-lg-3">
                        <div class="card stat-card">
                            <div class="card-body">
                                <div class="d-flex justify-content-between align-items-center">
                                    <div>
                                        <p class="text-muted mb-1 small">Nouveaux</p>
                                        <h3 class="mb-0 fw-bold text-danger">${nouveaux}</h3>
                                        <small class="text-muted">À traiter</small>
                                    </div>
                                    <div class="stat-icon bg-danger bg-opacity-10 text-danger">
                                        <i class="bi bi-exclamation-circle"></i>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    
                    <!-- En Cours -->
                    <div class="col-md-6 col-lg-3">
                        <div class="card stat-card">
                            <div class="card-body">
                                <div class="d-flex justify-content-between align-items-center">
                                    <div>
                                        <p class="text-muted mb-1 small">En Cours</p>
                                        <h3 class="mb-0 fw-bold text-warning">${enCours}</h3>
                                        <small class="text-muted">En traitement</small>
                                    </div>
                                    <div class="stat-icon bg-warning bg-opacity-10 text-warning">
                                        <i class="bi bi-clock-history"></i>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    <!-- Résolus -->
                    <div class="col-md-6 col-lg-3">
                        <div class="card stat-card">
                            <div class="card-body">
                                <div class="d-flex justify-content-between align-items-center">
                                    <div>
                                        <p class="text-muted mb-1 small">Résolus</p>
                                        <h3 class="mb-0 fw-bold text-success">${resolus}</h3>
                                        <small class="text-success">
                                            <i class="bi bi-arrow-up"></i> +12%
                                        </small>
                                    </div>
                                    <div class="stat-icon bg-success bg-opacity-10 text-success">
                                        <i class="bi bi-check-circle"></i>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                
               
               
                    
                    <!-- Graphique par Statut -->
                    <div class="col-lg-6">
                        <div class="card stat-card">
                            <div class="card-body">
                                <h5 class="card-title mb-4">
                                    <i class="bi bi-bar-chart text-success"></i> 
                                    Répartition par Statut
                                </h5>
                                <div class="chart-container">
                                    <canvas id="statutChart"></canvas>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                
                <!-- Signalements récents -->
                <div class="row px-4">
                    <div class="col-12">
                        <div class="card stat-card">
                            <div class="card-body">
                                <h5 class="card-title mb-4">
                                    <i class="bi bi-clock-history text-primary"></i> 
                                    Signalements Récents
                                </h5>
                                <div class="table-responsive">
                                    <table class="table table-hover align-middle">
                                        <thead class="table-light">
                                            <tr>
                                                
                                                <th>Type</th>
                                                <th>Description</th>
                                                <th>Statut</th>
                                                <th>Date</th>
                                                
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:choose>
                                                <c:when test="${empty sessionScope.recentReports}">
                                                    <tr>
                                                        <td colspan="6" class="text-center text-muted py-4">
                                                            <i class="bi bi-inbox fs-1 d-block mb-2"></i>
                                                            Aucun signalement récent
                                                        </td>
                                                    </tr>
                                                </c:when>
                                                <c:otherwise>
                                                    <c:forEach var="sig" items="${sessionScope.recentReports}">
                                                        <tr>                                                            
                                                            <td>
                                                                <span class="badge bg-info">
                                                                    <c:choose>
                                                                        <c:when test="${not empty sig.designation}">
                                                                            ${sig.designation}
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            Non spécifié
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </span>
                                                            </td>
                                                            <td>
                                                                <c:choose>
                                                                    <c:when test="${sig.description.length() > 50}">
                                                                        ${sig.description.substring(0, 50)}...
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        ${sig.description}
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </td>
                                                            <td>
                                                                <c:choose>
                                                                    <c:when test="${sig.statut.label eq 'new'}">
                                                                        <span class="badge bg-danger">
                                                                            <i class="bi bi-exclamation-circle"></i> Nouveau
                                                                        </span>
                                                                    </c:when>
                                                                    <c:when test="${sig.statut.label eq 'processing'}">
                                                                        <span class="badge bg-warning text-dark">
                                                                            <i class="bi bi-clock-history"></i> En Cours
                                                                        </span>
                                                                    </c:when>
                                                                    <c:when test="${sig.statut.label eq 'final'}">
                                                                        <span class="badge bg-success">
                                                                            <i class="bi bi-check-circle"></i> Résolu
                                                                        </span>
                                                                    </c:when>                                                                    
                                                                </c:choose>
                                                            </td>
                                                            <td>
                                                                <small class="text-muted">
                                                                    <i class="bi bi-calendar3"></i>
                                                                    ${sig.dateCreation}
                                                                </small>
                                                            </td>
                                                            
                                                        </tr>
                                                    </c:forEach>
                                                </c:otherwise>
                                            </c:choose>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="text-center mt-3">
                                    <a href="GererSignalements.jsp" class="btn btn-primary">
                                        Voir tous les signalements 
                                        <i class="bi bi-arrow-right"></i>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    
    <!-- Scripts pour les graphiques -->
   <script>
 
    

        
        
        // Graphique par Statut
        const statutCtx = document.getElementById('statutChart').getContext('2d');
        new Chart(statutCtx, {
            type: 'bar',
            data: {
                labels: ['Nouveaux', 'En Cours', 'Résolus'],
                datasets: [{
                    label: 'Nombre de signalements',
                    data: [${nouveaux}, ${enCours}, ${resolus}],
                    backgroundColor: [
                        '#F44336',
                        '#FF9800',
                        '#43A047'
                    ],
                    borderRadius: 8
                }]
            },
            options: {
                responsive: true,
                maintainAspectRatio: false,
                plugins: {
                    legend: {
                        display: false
                    }
                },
                scales: {
                    y: {
                        beginAtZero: true,
                        ticks: {
                            stepSize: 1
                        }
                    }
              
                }
            }
        
        });
    
    </script> 
</body>
</html>