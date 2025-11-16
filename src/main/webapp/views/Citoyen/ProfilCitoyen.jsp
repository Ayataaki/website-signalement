<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mon Profil - Espace Citoyen</title>
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

        .profile-card {
            background: white;
            border-radius: 12px;
            padding: 2rem;
            box-shadow: 0 2px 8px rgba(0,0,0,0.1);
            transition: transform 0.3s ease;
        }

        .profile-card:hover {
            transform: translateY(-2px);
        }

        .avatar {
            width: 120px;
            height: 120px;
            border-radius: 50%;
            background: linear-gradient(135deg, var(--primary-color), var(--primary-hover));
            display: flex;
            align-items: center;
            justify-content: center;
            margin: 0 auto 1rem;
            box-shadow: 0 4px 12px rgba(25, 118, 210, 0.3);
        }

        .avatar i {
            font-size: 3rem;
            color: white;
        }

        .stat-item {
            padding: 0.75rem 0;
            border-bottom: 1px solid #f0f0f0;
        }

        .stat-item:last-child {
            border-bottom: none;
        }

        .form-label {
            font-weight: 500;
            color: var(--text-dark);
            margin-bottom: 0.5rem;
        }

        .form-control {
            border: 1px solid #e0e0e0;
            border-radius: 8px;
            padding: 0.75rem;
            transition: all 0.3s ease;
        }

        .form-control:focus {
            border-color: var(--primary-color);
            box-shadow: 0 0 0 0.2rem rgba(25, 118, 210, 0.1);
        }

        .btn-primary {
            background-color: var(--primary-color);
            border: none;
            border-radius: 8px;
            padding: 0.75rem 1.5rem;
            font-weight: 500;
            transition: all 0.3s ease;
        }

        .btn-primary:hover {
            background-color: var(--primary-hover);
            transform: translateY(-1px);
            box-shadow: 0 4px 12px rgba(25, 118, 210, 0.3);
        }

        .btn-outline-secondary {
            border: 1px solid #e0e0e0;
            border-radius: 8px;
            padding: 0.75rem 1.5rem;
            font-weight: 500;
            color: var(--text-gray);
            transition: all 0.3s ease;
        }

        .btn-outline-secondary:hover {
            background-color: #f5f5f5;
            border-color: #d0d0d0;
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
        <jsp:param name="activePage" value="profil"/>
    </jsp:include>

    <div class="main-content">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h1 class="h3 mb-0">Mon Profil</h1>
            <span class="text-muted">Espace personnel</span>
        </div>

        <div class="row">
            <!-- Carte Profil et Statistiques -->
            <div class="col-lg-4 mb-4">
                <!-- Carte Profil -->
                <div class="profile-card text-center mb-4">
                    <div class="avatar">
                        <i class="fas fa-user"></i>
                    </div>
                    <h4 class="mb-1">${sessionScope.user.nom} ${sessionScope.user.prenom}</h4>
                    <p class="text-muted mb-3">Citoyen</p>
                    <span class="badge bg-primary">Compte actif</span>
                </div>

                <!-- Carte Statistiques -->
                <div class="profile-card">
                    <h6 class="fw-semibold mb-3">
                        <i class="fas fa-chart-bar me-2 text-primary"></i>
                        Réclamations
                    </h6>
                    <div class="stat-item d-flex justify-content-between">
                        <span class="text-muted">Signalements totaux</span>
                        <strong class="text-primary">${sessionScope.countTotalSignalement}</strong>
                    </div>
                    <div class="stat-item d-flex justify-content-between">
                        <span class="text-muted">En attente</span>
                        <strong class="text-danger">${sessionScope.countNewSignalement}</strong>
                    </div>
                    <div class="stat-item d-flex justify-content-between">
                        <span class="text-muted">En cours</span>
                        <strong class="text-warning">${sessionScope.countProcessingSignalement}</strong>
                    </div>
                    <div class="stat-item d-flex justify-content-between">
                        <span class="text-muted">Résolus</span>
                        <strong class="text-success">${sessionScope.countFinishedSignalement}</strong>
                    </div>
                </div>
            </div>

            <!-- Informations et Formulaires -->
            <div class="col-lg-8">
                <!-- Informations Personnelles -->
                <div class="profile-card mb-4">
                    <h5 class="fw-semibold mb-4">
                        <i class="fas fa-user-edit me-2 text-primary"></i>
                        Informations personnelles
                    </h5>
                    <form action="${pageContext.request.contextPath}/CitoyenServlet" method="post">
						<input type="hidden" name="idCitoyen" value="${sessionScope.user.idCitoyen}" > 
						<input type="hidden" name="action" value="insertion">
						<div class="row">
							<div class="col-md-6 mb-3">
								<label class="form-label">Nom</label> <input type="text"
									class="form-control" name="nom"
									value="${sessionScope.user.nom}" required>
							</div>
							<div class="col-md-6 mb-3">
								<label class="form-label">Prénom</label> <input type="text"
									class="form-control" name="prenom"
									value="${sessionScope.user.prenom}" required>
							</div>
						</div>

						<div class="mb-3">
                            <label class="form-label">CIN</label>
                            <input type="text" class="form-control" name="cin" value="${sessionScope.user.cin}" required>
                        </div>
                        
                        <div class="mb-3">
                            <label class="form-label">Email</label>
                            <input type="email" class="form-control" name="email" value="${sessionScope.user.email}" required>
                        </div>

						<div class="mb-3">
							<div class="input-icon">
							  <label class="form-label">Région de résidence</label>
							 <select class="form-control" id="idRegion" name="idRegion" required>
<%-- 									<option value="${sessionScope.user.idRegion}">${sessionScope.user.idRegion}</option> --%>
									<option value="${sessionScope.user.idRegion}">
										<c:forEach var="r" items="${sessionScope.regions}">
											<c:if
												test="${r.idRegion == sessionScope.user.idRegion}">
									            ${r.nom}
									        </c:if>
										</c:forEach>
									</option>
									<c:forEach var="region" items="${sessionScope.regions}">
										<option value="${region.idRegion}"
											<c:if test="${region.idRegion == sessionScope.user.idRegion}">selected</c:if>>
											${region.nom}</option>
									</c:forEach>
								</select>
							</div>
						</div>

						<div class="mb-3">
                            <label class="form-label">Téléphone</label>
                            <input type="tel" class="form-control" name="telephone" value="${sessionScope.user.telephone}">
                        </div>
                        
                        <div class="mb-3">
                            <label class="form-label">Lieu de naissance</label>
                            <input type="text" class="form-control" name="lieuNaissance" value="${sessionScope.user.lieuNaissance}">
                        </div>
                        
                        <div class="mb-3">
                            <label class="form-label">Date de naissance</label>
                            <input type="date" class="form-control" name="dateNaissance" value="${sessionScope.user.dateNaissance}">
                        </div>

                        <div class="d-flex justify-content-end gap-3">
                            <button type="button" class="btn btn-outline-secondary">
                                <i class="fas fa-times me-2"></i>Annuler
                            </button>
                            <button type="submit" class="btn btn-primary">
                                <i class="fas fa-save me-2"></i>Enregistrer les modifications
                            </button>
                        </div>
                    </form>
                </div>

                <!-- Changer Mot de Passe -->
                <div class="profile-card">
                    <h5 class="fw-semibold mb-4">
                        <i class="fas fa-lock me-2 text-primary"></i>
                        Changer le mot de passe
                    </h5>

					<c:if test="${not empty error}">
						<div class="alert alert-danger alert-dismissible fade show"
							role="alert">
							<i class="fas fa-exclamation-triangle me-2"></i>${error}
							<button type="button" class="btn-close" data-bs-dismiss="alert"
								aria-label="Close"></button>
						</div>
					</c:if>

					<c:if test="${not empty success}">
						<div class="alert alert-success alert-dismissible fade show"
							role="alert">
							<i class="fas fa-check-circle me-2"></i>${success}
							<button type="button" class="btn-close" data-bs-dismiss="alert"
								aria-label="Close"></button>
						</div>
					</c:if>
					
					<form action="${pageContext.request.contextPath}/CitoyenServlet" method="post">
						<input type="hidden" name="idCitoyen" value="${sessionScope.user.idCitoyen}" > 
						<input type="hidden" name="action" value="changerMdp">
                        <div class="mb-3">
                            <label class="form-label">Mot de passe actuel</label>
                            <input type="password" class="form-control" name="currentPassword" required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Nouveau mot de passe</label>
                            <input type="password" class="form-control" name="newPassword" required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Confirmer le nouveau mot de passe</label>
                            <input type="password" class="form-control" name="confirmPassword" required>
                        </div>
                        <div class="d-flex justify-content-end">
                            <button type="submit" class="btn btn-primary">
                                <i class="fas fa-key me-2"></i>Changer le mot de passe
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>