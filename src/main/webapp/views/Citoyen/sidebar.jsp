<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- Sidebar Menu -->
<div class="sidebar">
    <div class="logo">
            <h4 class=" text-primary">
                <i class="fas fa-building "></i>
                UrbAlert
            </h4>
            <small class="text-muted">Espace Citoyen</small>
        </div>
        
    <!-- Informations utilisateur -->
    <div class="user-info">
        <div class="d-flex align-items-center">
            <div class="user-avatar">
                <i class="fas fa-user"></i>
            </div>
            <div class="ms-3">
                <h6 class="mb-0">${sessionScope.user.nom} ${sessionScope.user.prenom}</h6> <!-- affiche la valeur enregistrée dans la session par session.setAttribute("userNom", ...) dans la servlet -->
                <small class="text-muted">Citoyen</small>
            </div>
        </div>
    </div>

    <!-- Navigation -->
    <nav class="nav flex-column">
        <a class="nav-link ${param.activePage == 'dashboard' ? 'active' : ''}" 
           href="DashboardCitoyen.jsp">
            <i class="fas fa-tachometer-alt"></i><!-- ${param.activePage == 'dashboard' ? 'active' : ''} indiquer visuellement à l’utilisateur quelle page est actuellement active dans la barre de navigation. -->
            Tableau de bord
        </a>
        <a class="nav-link ${param.activePage == 'reports' ? 'active' : ''}" 
           href="MesSignalements.jsp">
            <i class="fas fa-list"></i>
            Mes signalements
        </a>
        <a class="nav-link ${param.activePage == 'report' ? 'active' : ''}" 
           href="SignalerProblème.jsp">
            <i class="fas fa-plus-circle"></i>
            Signaler un problème
        </a>
        <a class="nav-link ${param.activePage == 'profile' ? 'active' : ''}" 
           href="ProfilCitoyen.jsp">
            <i class="fas fa-user-cog"></i>
            Mon profil
        </a>
        <a class="nav-link" href="${pageContext.request.contextPath}/LogoutServlet">
            <i class="fas fa-sign-out-alt"></i>
            Déconnexion
        </a>
    </nav>
</div>