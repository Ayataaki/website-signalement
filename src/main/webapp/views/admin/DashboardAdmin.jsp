<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard Administrateur</title>
    
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    
    <!-- Bootstrap Icons -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.0/font/bootstrap-icons.css">
    
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    
    <!-- Chart.js -->
    <script src="https://cdn.jsdelivr.net/npm/chart.js@4.4.0/dist/chart.umd.min.js"></script>
    
    <style>
        :root {
            --primary-color: #1976D2;
            --primary-hover: #1565C0;
            --success-color: #43A047;
            --warning-color: #FF9800;
            --danger-color: #F44336;
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
            width: var(--sidebar-width);
            background: white;
            box-shadow: 2px 0 10px rgba(0,0,0,0.1);
            position: fixed;
            left: 0;
            top: 0;
            height: 100vh;
            overflow-y: auto;
            display: flex;
            flex-direction: column;
            z-index: 1000;
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
            background: #f8f9fa;
            border-radius: 8px;
            padding: 0.75rem;
        }
        
        .avatar-sm {
            width: 36px;
            height: 36px;
            border-radius: 50%;
            background: linear-gradient(135deg, var(--danger-color), #E91E63);
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
            box-shadow: 0 2px 8px rgba(0,0,0,0.08);
            transition: all 0.3s;
            overflow: hidden;
        }
        
        .stat-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 4px 16px rgba(0,0,0,0.12);
        }
        
        .stat-card-header {
            padding: 1.5rem;
            background: linear-gradient(135deg, var(--primary-color) 0%, var(--primary-hover) 100%);
            color: white;
        }
        
        .stat-icon {
            width: 60px;
            height: 60px;
            border-radius: 12px;
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 28px;
        }
        
        .stat-value {
            font-size: 2.5rem;
            font-weight: 700;
            margin: 0;
        }
        
        .stat-label {
            font-size: 0.875rem;
            opacity: 0.9;
            margin-bottom: 0.5rem;
        }
        
        .trend-badge {
            display: inline-flex;
            align-items: center;
            gap: 0.25rem;
            padding: 0.25rem 0.75rem;
            border-radius: 20px;
            font-size: 0.75rem;
            font-weight: 600;
        }
        
        .chart-container {
            position: relative;
            height: 320px;
        }
        
        .tech-stat-box {
            background: linear-gradient(135deg, #f8f9fa 0%, #e9ecef 100%);
            border-radius: 12px;
            padding: 1.5rem;
            text-align: center;
            transition: all 0.3s;
        }
        
        .tech-stat-box:hover {
            transform: scale(1.05);
            box-shadow: 0 4px 12px rgba(0,0,0,0.1);
        }
        
        .tech-stat-value {
            font-size: 2rem;
            font-weight: 700;
            margin: 0.5rem 0;
        }
        
        .tech-stat-label {
            color: #6c757d;
            font-size: 0.875rem;
        }
    </style>
</head>
<body>
    <div class="layout-container">
        <!-- Sidebar -->
		<jsp:include page="sidebarAdmin.jsp">
			<jsp:param name="activePage" value="dashboard" />
		</jsp:include>

		<div class="mt-auto p-3">
                <div class="user-info">
                    <div class="d-flex align-items-center mb-2">
                        <div class="avatar-sm me-2">
                            <i class="fas fa-user-shield"></i>
                        </div>
                        <div>
                            <small class="d-block fw-semibold">
                                ${sessionScope.admin.nom} ${sessionScope.admin.prenom}
                            </small>
                            <small class="text-muted">Administrateur</small>
                        </div>
                    </div>
                    <a href="${pageContext.request.contextPath}/LogoutServlet" class="btn btn-outline-danger btn-sm w-100">
                        <i class="fas fa-sign-out-alt me-1"></i>
                        Déconnexion
                    </a>
                </div>
            </div>
        </div>
        
        <!-- Main Content -->
        <div class="main-content">
            <div class="content-wrapper">
                <div class="d-flex justify-content-between align-items-center mb-4">
                    <div>
                        <h1 class="text-dark mb-1">
                            <i class="bi bi-speedometer2 me-2"></i>
                            Tableau de Bord Administrateur
                        </h1>
                        <p class="text-muted">Vue d'ensemble de la plateforme</p>
                    </div>
                    <button class="btn btn-primary" onclick="location.reload()">
                        <i class="bi bi-arrow-clockwise me-2"></i>
                        Actualiser
                    </button>
                </div>
                
                <!-- Cartes de statistiques principales -->
                <div class="row g-4 mb-4">
                    <!-- Total Users -->
                    <div class="col-md-6 col-lg-3">
                        <div class="card stat-card h-100">
                            <div class="card-body">
                                <div class="d-flex justify-content-between align-items-start mb-3">
                                    <div class="stat-icon bg-primary bg-opacity-10 text-primary">
                                        <i class="bi bi-people"></i>
                                    </div>
<!--                                     <span class="trend-badge bg-success text-white"> -->
<!--                                         <i class="bi bi-arrow-up"></i> +12% -->
<!--                                     </span> -->
                                </div>
                                <div>
                                    <p class="stat-label text-muted mb-2">Total Utilisateurs</p>
                                    <h2 class="stat-value text-primary">${sessionScope.totalUsers}</h2>
                                    <small class="text-muted">Citoyens enregistrés</small>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    <!-- Total Reports -->
                    <div class="col-md-6 col-lg-3">
                        <div class="card stat-card h-100">
                            <div class="card-body">
                                <div class="d-flex justify-content-between align-items-start mb-3">
                                    <div class="stat-icon bg-success bg-opacity-10 text-success">
                                        <i class="bi bi-file-text"></i>
                                    </div>
									<!-- A verifier, ça veut dire quoi déjà -->
<!--                                     <span class="trend-badge bg-success text-white"> -->
<!--                                         <i class="bi bi-arrow-up"></i> +8.2% -->
<!--                                     </span> -->
                                </div>
                                <div>
                                    <p class="stat-label text-muted mb-2">Total Signalements</p>
                                    <h2 class="stat-value text-success">${sessionScope.totalReports}</h2>
                                    <small class="text-muted">Signalements créés</small>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    <!-- Municipal Staff -->
                    <div class="col-md-6 col-lg-3">
                        <div class="card stat-card h-100">
                            <div class="card-body">
                                <div class="d-flex justify-content-between align-items-start mb-3">
                                    <div class="stat-icon bg-warning bg-opacity-10 text-warning">
                                        <i class="bi bi-building"></i>
                                    </div>
                                </div>
                                <div>
                                    <p class="stat-label text-muted mb-2">Personnel Municipal</p>
                                    <h2 class="stat-value text-warning">${sessionScope.municipalStaff}</h2>
                                    <small class="text-muted">Agents actifs</small>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    <!-- Resolution Rate -->
                    <div class="col-md-6 col-lg-3">
                        <div class="card stat-card h-100">
                            <div class="card-body">
                                <div class="d-flex justify-content-between align-items-start mb-3">
                                    <div class="stat-icon bg-danger bg-opacity-10 text-danger">
                                        <i class="bi bi-trophy"></i>
                                    </div>
<!--                                     <span class="trend-badge bg-success text-white"> -->
<!--                                         <i class="bi bi-arrow-up"></i> +2.1% -->
<!--                                     </span> -->
                                </div>
                                <div>
                                    <p class="stat-label text-muted mb-2">Taux de Résolution</p>
                                    <h2 class="stat-value text-danger">${sessionScope.resolutionRate}%</h2>
                                    <small class="text-muted">Signalements résolus</small>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                
                <!-- Graphiques -->
                <div class="row g-4 mb-4">
                    <!-- Monthly Trends -->
                    <div class="col-lg-8">
                        <div class="card stat-card">
                            <div class="card-body">
                                <h5 class="card-title mb-4">
                                    <i class="bi bi-graph-up text-primary me-2"></i>
                                    Tendances Mensuelles des Signalements
                                </h5>
                                <div class="chart-container">
                                    <canvas id="monthlyChart"></canvas>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    <!-- Reports by Type -->
<!--                     <div class="col-lg-4"> -->
<!--                         <div class="card stat-card"> -->
<!--                             <div class="card-body"> -->
<!--                                 <h5 class="card-title mb-4"> -->
<!--                                     <i class="bi bi-pie-chart text-success me-2"></i> -->
<!--                                     Par Type - where are not going to use it -->
<!--                                 </h5> -->
<!--                                 <div class="chart-container"> -->
<%--                                     <canvas id="typeChart"></canvas> --%>
<!--                                 </div> -->
<!--                             </div> -->
<!--                         </div> -->
<!--                     </div> -->
                </div>
                
                <!-- Recent Reports -->
                <div class="card stat-card">
                    <div class="card-body">
                        <h5 class="card-title mb-4">
                            <i class="bi bi-clock-history text-primary me-2"></i>
                            Signalements Récents
                        </h5>
                        <div class="table-responsive">
                            <table class="table table-hover align-middle">
                                <thead class="table-light">
                                    <tr>
<!--                                         <th>ID</th> -->
                                        <th>Objet</th>
                                        <th>Description</th>
                                        <th>Localisation</th>
                                        <th>Statut</th>
                                        <th>Date</th>
                                       
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:choose>
                                        <c:when test="${empty sessionScope.recentReports}">
                                            <tr>
                                                <td colspan="7" class="text-center text-muted py-4">
                                                    <i class="bi bi-inbox fs-1 d-block mb-2"></i>
                                                    Aucun signalement récent
                                                </td>
                                            </tr>
                                        </c:when>
                                        <c:otherwise>
                                            <c:forEach var="sig" items="${sessionScope.recentReports}">
                                                <tr>
<!--                                                     <td><strong>#${sig.idSignalement}</strong></td> -->
                                                    <td>
                                                        <span class="badge bg-info">
                                                            ${sig.designation != null ? sig.designation : 'N/A'}
                                                        </span>
                                                    </td>
                                                    <td>
                                                        ${sig.description.length() > 40 
                                                            ? sig.description.substring(0, 40).concat('...') 
                                                            : sig.description}
                                                    </td>
                                                    <td>
                                                        <small class="text-muted">
                                                            <i class="bi bi-geo-alt"></i>
                                                            ${sig.localisation}
                                                        </small>
                                                    </td>
                                                    <td>
                                                        <c:choose>
                                                            <c:when test="${sig.statut.label eq 'new'}">
                                                                <span class="badge bg-danger">Nouveau</span>
                                                            </c:when>
                                                            <c:when test="${sig.statut.label eq 'processing'}">
                                                                <span class="badge bg-warning text-dark">En Cours</span>
                                                            </c:when>
                                                            <c:when test="${sig.statut.label eq 'final'}">
                                                                <span class="badge bg-success">Résolu</span>
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
                            <a href="${pageContext.request.contextPath}/views/admin/GererSignalement.jsp"
                            class="btn btn-primary">							
                                Voir tous les signalements
                                <i class="bi bi-arrow-right ms-2"></i>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    
    <!-- Charts -->
    <script>
        // Monthly Trends Chart
        const monthlyCtx = document.getElementById('monthlyChart').getContext('2d');
        const monthlyData = {
            labels: [
                <c:forEach var="entry" items="${monthlyData}" varStatus="status">
                    '${entry.key}'<c:if test="${!status.last}">,</c:if>
                </c:forEach>
            ],
            datasets: [{
                label: 'Signalements',
                data: [
                    <c:forEach var="entry" items="${monthlyData}" varStatus="status">
                        ${entry.value}<c:if test="${!status.last}">,</c:if>
                    </c:forEach>
                ],
                borderColor: '#1976D2',
                backgroundColor: 'rgba(25, 118, 210, 0.1)',
                borderWidth: 3,
                fill: true,
                tension: 0.4,
                pointRadius: 5,
                pointHoverRadius: 7,
                pointBackgroundColor: '#1976D2'
            }]
        };
        
        new Chart(monthlyCtx, {
            type: 'line',
            data: monthlyData,
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
                            stepSize: 10
                        }
                    }
                }
            }
        });
        
        // Type Distribution Chart
        const typeCtx = document.getElementById('typeChart').getContext('2d');
//I commented the following lines of code, bcs the typeData is not defined in the servlet
        //         const typeData = {
//             <c:forEach var="entry" items="${typeData}" varStatus="status">
//                 '${entry.key}': ${entry.value}<c:if test="${!status.last}">,</c:if>
//             </c:forEach>
//         };
        
        //this shouldn't be executed, due to the lack of typeData ...
//         if (Object.keys(typeData).length > 0) {
//             new Chart(typeCtx, {
//                 type: 'doughnut',
//                 data: {
//                     labels: Object.keys(typeData),
//                     datasets: [{
//                         data: Object.values(typeData),
//                         backgroundColor: [
//                             '#1976D2',
//                             '#43A047',
//                             '#FF9800',
//                             '#F44336',
//                             '#9C27B0'
//                         ],
//                         borderWidth: 2,
//                         borderColor: '#fff'
//                     }]
//                 },
//                 options: {
//                     responsive: true,
//                     maintainAspectRatio: false,
//                     plugins: {
//                         legend: {
//                             position: 'bottom',
//                             labels: {
//                                 padding: 15,
//                                 font: {
//                                     size: 11
//                                 }
//                             }
//                         }
//                     }
//                 }
//             });
//         }
    </script>
</body>
</html>