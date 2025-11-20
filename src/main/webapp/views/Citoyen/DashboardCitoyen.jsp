<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Espace Citoyen - Dashboard</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
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
	font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto,
		sans-serif;
	background-color: #f8f9fa;
	color: var(--text-dark);
}

.sidebar {
	background: white;
	min-height: 100vh;
	box-shadow: 2px 0 5px rgba(0, 0, 0, 0.1);
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
	box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
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

.stat-icon.primary {
	background-color: rgba(25, 118, 210, 0.1);
	color: var(--primary-color);
}

.stat-icon.danger {
	background-color: rgba(244, 67, 54, 0.1);
	color: var(--danger-color);
}

.stat-icon.warning {
	background-color: rgba(255, 152, 0, 0.1);
	color: var(--warning-color);
}

.stat-icon.success {
	background-color: rgba(67, 160, 71, 0.1);
	color: var(--success-color);
}

.activity-item {
	padding: 1rem;
	border-radius: 8px;
	background-color: #f8f9fa;
	margin-bottom: 0.5rem;
	border-left: 4px solid transparent;
}

.activity-item.new {
	border-left-color: var(--primary-color);
}

.activity-item.in-progress {
	border-left-color: var(--warning-color);
}

.activity-item.resolved {
	border-left-color: var(--success-color);
}

.status-dot {
	width: 8px;
	height: 8px;
	border-radius: 50%;
	display: inline-block;
	margin-right: 0.5rem;
}

.status-new {
	background-color: var(--primary-color);
}

.status-pending {
	background-color: var(--warning-color);
}

.status-resolved {
	background-color: var(--success-color);
}

.signalement-card {
    font-family: 'Segoe UI', 'Roboto', sans-serif;
    background-color: #f9f9f9;
    border: 1px solid #ddd;
    border-radius: 8px;
    padding: 16px;
    box-shadow: 0 2px 6px rgba(0,0,0,0.05);
    transition: box-shadow 0.3s ease;
}

.signalement-card:hover {
    box-shadow: 0 4px 12px rgba(0,0,0,0.1);
}

.signalement-card p {
    margin-bottom: 10px;
    font-size: 15px;
    color: #333;
}

.signalement-card strong {
    color: #555;
    font-weight: 500;
}

.signalement-card .text-muted {
    color: #777;
    font-style: italic;
}


.user-info {
	background: white;
	padding: 1rem;
	border-radius: 12px;
	margin-bottom: 1rem;
}

.activity-item {
	background: white;
	padding: 10px 15px;
	border-radius: 8px;
	box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
}
.status-dot {
    display: inline-block;
    width: 10px;
    height: 10px;
    border-radius: 50%;
}
.bg-success { background-color: #28a745; }
.bg-warning { background-color: #ffc107; }
.bg-secondary { background-color: #6c757d; }


@media ( max-width : 768px) {
	.sidebar {
		width: 100%;
		position: relative;
		min-height: auto;
	}
	.main-content {
		margin-left: 0;
	}
}
a</style>
</head>
<body>
	<jsp:include page="sidebar.jsp">
		<jsp:param name="activePage" value="dashboard" />
	</jsp:include>

	<div class="main-content">
		<div class="page-header">
			<h1>Tableau de bord</h1>
			<span class="text-muted">Bienvenue,
				${sessionScope.user.nomUtilisateur}</span>
		</div>

		<!-- Cartes de statistiques -->
		<div class="row mb-4">
			<div class="col-md-3 mb-4">
				<div class="stat-card primary">
					<div class="stat-icon primary">
						<i class="fas fa-file-alt fa-2x"></i>
					</div>
					<h3>${sessionScope.countTotalSignalement}</h3>
					<p>Signalements totaux</p>
				</div>
			</div>

			<div class="col-md-3 mb-4">
				<div class="stat-card danger">
					<div class="stat-icon danger">
						<i class="fas fa-exclamation-triangle fa-2x"></i>
					</div>
					<h3>${sessionScope.countNewSignalement}</h3>
					<p>En attente</p>
				</div>
			</div>

			<div class="col-md-3 mb-4">
				<div class="stat-card warning">
					<div class="stat-icon warning">
						<i class="fas fa-clock fa-2x"></i>
					</div>
					<h3>${sessionScope.countProcessingSignalement}</h3>
					<p>En cours</p>
				</div>
			</div>
			<div class="col-md-3 mb-4">
				<div class="stat-card success">
					<div class="stat-icon success">
						<i class="fas fa-check-circle fa-2x"></i>
					</div>
					<h3>${sessionScope.countFinishedSignalement}</h3>
					<p>Résolus</p>
				</div>
			</div>
		</div>

		<div class="row">
			<!-- Activité récente -->
			<div class="col-12 mb-4">
				<div class="activity-card bg-white shadow-sm rounded p-3">
<!-- 					<div class="card-header"> -->
<!-- 						<h5> -->
<!-- 							<i class="fas fa-bell me-2"></i> Activité récente -->
<!-- 						</h5> -->
<!-- 					</div> -->

					<h5>
						<i class="fas fa-bell me-2"></i> Activité récente
					</h5>

					<div class="card-body">
						<c:choose>
							<c:when test="${empty sessionScope.signalements}">
								<div class="empty-state">
									<i class="fas fa-inbox"></i>
									<p>Aucun signalement pour le moment</p>
								</div>
							</c:when>


							<c:otherwise>

								<div class="d-flex flex-column gap-3">

									<c:forEach var="s" items="${sessionScope.signalements}">

										<c:out value="${s.designation}" />

										<div
											class="activity-item signalement-card d-flex flex-column p-3 border rounded bg-light 
										<c:choose>
											<c:when test="${s.statut.label eq 'final'}">resolved</c:when>
											<c:when test="${s.statut.label eq 'processing'}">in-progress</c:when>
											<c:otherwise>new</c:otherwise>
										</c:choose>">

											<div class="mb-2">
												<span
													class="
								                <c:choose>
								                    <c:when test="${s.statut == 'FINAL'}">status-resolved</c:when>
								                    <c:when test="${s.statut == 'PROCESSING'}">status-pending</c:when>
								                    <c:otherwise>status-new</c:otherwise>
								                </c:choose>">
												</span>

												<div class="flex-grow-1">
													<p class="mb-1 fw-bold">${s.designation}</p>
												</div>
											</div>

											

											<div class="mb-2">
												<span
													class="status-dot 
												<c:choose>
													<c:when test="${s.statut.label eq 'final'}">status-resolved</c:when>
													<c:when test="${s.statut.label eq 'processing'}">status-pending</c:when>
													<c:otherwise>status-new</c:otherwise>
												</c:choose>">
												</span>
												


												<!-- Statut -->
												<p class="mb-1">
													<strong>Statut :</strong> <span> <c:choose>
															<c:when test="${s.statut == 'FINAL'}">Résolu</c:when>
															<c:when test="${s.statut == 'PROCESSING'}">En cours</c:when>
															<c:otherwise>Nouveau</c:otherwise>
														</c:choose>
													</span>
												</p>
												
												<!-- Commentaire -->
												<p class="mb-0">
													<strong>Commentaire :</strong><br> <span
														class="text-muted"> <c:out value="${s.commentaire}" />
													</span>
												</p>
																								
<!-- 												<div class="flex-grow-1"> -->
<!-- 													<p class="mb-1"> -->
<!-- 														Signalement #${s.idSignalement} -->
<%-- 														<c:choose> --%>
<%-- 															<c:when test="${s.statut.label eq 'final'}">a été résolu</c:when> --%>
<%-- 															<c:when test="${s.statut.label eq 'processing'}">est en cours</c:when> --%>
<%-- 															<c:otherwise>a été créé</c:otherwise> --%>
<%-- 														</c:choose> --%>
<!-- 													</p> -->
<!-- 												</div> -->

											</div>
										</div>
									</c:forEach>

								</div>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>