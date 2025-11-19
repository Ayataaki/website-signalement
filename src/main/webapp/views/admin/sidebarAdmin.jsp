<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

        <div class="sidebar">
            <div class="logo">
                <h4 class="mb-0">
                    <i class="fas fa-shield-halved me-2"></i>
                    Espace Administrateur
                </h4>
                <small>Plateforme de gestion</small>
            </div>

            <nav class="nav flex-column">
                <a href="DashboardAdmin.jsp" class="nav-link active">
                    <i class="fas fa-chart-line"></i>
                    <span>Dashboard</span>
                </a>
                <a href="${pageContext.request.contextPath}/views/admin/GererUtilisateur.jsp" class="nav-link">
                    <i class="fas fa-users"></i>
                    <span>Gérer Utilisateurs</span>
                </a>
                <a href="${pageContext.request.contextPath}/views/admin/GererSignalement.jsp" class="nav-link">
                    <i class="fas fa-clipboard-list"></i>
                    <span>Tous les Signalements</span>
                </a>
                <a href="${pageContext.request.contextPath}/views/admin/GererMunicipaux.jsp" class="nav-link">
                    <i class="fas fa-building"></i>
                    <span>Services Municipaux</span>
                </a>
                <a href="${pageContext.request.contextPath}/views/admin/GererRegion.jsp" class="nav-link">
                    <i class="fas fa-map-marked-alt"></i>
                    <span>Régions</span>
                </a>
               <a href="${pageContext.request.contextPath}/AdminServlet" class="nav-link">
               <i class="fas fa-user-cog"></i>
               <span>Profil</span>
</a>
            </nav>
</body>
</html>