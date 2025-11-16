<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Gestion des Signalements</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Bootstrap Icons -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.0/font/bootstrap-icons.css">

<!-- Font Awesome -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">

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

.layout-container {
	display: flex;
	min-height: 100vh;
}

.sidebar {
	width: var(--sidebar-width);
	background: white;
	box-shadow: 2px 0 10px rgba(0, 0, 0, 0.1);
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

.logo {
	padding: 1.5rem;
	border-bottom: 1px solid #e9ecef;
	background: linear-gradient(135deg, var(--primary-color) 0%,
		var(--primary-hover) 100%);
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

.content-wrapper {
	padding: 2rem;
}

.stat-card {
	border-radius: 12px;
	border: none;
	box-shadow: 0 2px 8px rgba(0, 0, 0, 0.08);
	transition: all 0.3s;
	overflow: hidden;
}

.stat-card:hover {
	box-shadow: 0 4px 16px rgba(0, 0, 0, 0.12);
}

.tab-custom {
	border: none;
	border-bottom: 2px solid #e9ecef;
}

.tab-custom .nav-link {
	border: none;
	color: #6c757d;
	padding: 1rem 1.5rem;
	font-weight: 500;
	transition: all 0.3s;
}

.tab-custom .nav-link:hover {
	border: none;
	color: var(--primary-color);
	background-color: transparent;
}

.tab-custom .nav-link.active {
	border: none;
	border-bottom: 3px solid var(--primary-color);
	color: var(--primary-color);
	background-color: transparent;
}

.table-actions {
	display: flex;
	gap: 0.5rem;
}

.badge-role {
	padding: 0.5rem 1rem;
	font-size: 0.75rem;
	font-weight: 600;
	border-radius: 20px;
}

.search-box {
	position: relative;
}

.search-box input {
	padding-left: 2.5rem;
	border-radius: 8px;
	border: 1px solid #e9ecef;
}

.search-box i {
	position: absolute;
	left: 1rem;
	top: 50%;
	transform: translateY(-50%);
	color: #6c757d;
}

.modal-header {
	background: linear-gradient(135deg, var(--primary-color) 0%,
		var(--primary-hover) 100%);
	color: white;
}

.modal-header .btn-close {
	filter: brightness(0) invert(1);
}

.avatar-lg {
	width: 48px;
	height: 48px;
	border-radius: 50%;
	display: flex;
	align-items: center;
	justify-content: center;
	font-weight: 600;
	font-size: 1.2rem;
}

.table-card {
	background: white;
	border-radius: 12px;
	box-shadow: 0 2px 8px rgba(0, 0, 0, 0.08);
	padding: 1.5rem;
}

.table thead {
	background-color: #f8f9fa;
}

.table thead th {
	border-bottom: 2px solid var(--primary-color);
	color: #495057;
	font-weight: 600;
	padding: 1rem;
}

.table tbody td {
	padding: 1rem;
	vertical-align: middle;
}

.btn-action {
	padding: 0.375rem 0.75rem;
	margin: 0 0.25rem;
}

.table {
	font-size: 0.85rem; /* Police plus petite */
}

.table td, .table th {
	padding: 0.5rem 0.75rem; /* Réduit l’espace vertical et horizontal */
	white-space: nowrap;
	/* Empêche le texte de se couper sur plusieurs lignes */
}
.pagination {
  display: flex;
  list-style: none;
  padding-left: 0;
}

.page-item {
  margin: 0 0.25rem;
}

.page-link {
  display: block;
  padding: 0.5rem 0.75rem;
  color: var(--primary-color);
  background-color: white;
  border: 1px solid #dee2e6;
  border-radius: 0.25rem;
  text-decoration: none;
  font-weight: 500;
}

.page-item.active .page-link {
  background-color: var(--primary-color);
  color: white;
  border-color: var(--primary-color);
}

.page-item.disabled .page-link {
  color: #6c757d;
  pointer-events: none;
  background-color: #e9ecef;
  border-color: #dee2e6;
}

</style>
</head>
<body>
	<div class="layout-container">
		<!-- Sidebar -->
		<jsp:include page="sidebarAdmin.jsp">
			<jsp:param name="activePage" value="signalements" />
		</jsp:include>

		<div class="mt-auto p-3">
			<div class="user-info">
				<div class="d-flex align-items-center mb-2">
					<div class="avatar-sm me-2">
						<i class="fas fa-user-shield"></i>
					</div>
					<div>
						<small class="d-block fw-semibold">
							${sessionScope.admin.nom} ${sessionScope.admin.prenom} </small> <small
							class="text-muted">Administrateur</small>
					</div>
				</div>
				<a href="${pageContext.request.contextPath}/LogoutServlet"
					class="btn btn-outline-danger btn-sm w-100"> <i
					class="fas fa-sign-out-alt me-1"></i> Déconnexion
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
						<i class="fas fa-building"></i> Gestion des municipaux
					</h1>
					<p class="text-muted">Gérer les municipaux</p>
				</div>
			</div>
			
			<div class="tab-content">
				<!-- Citoyens -->
				<div class="tab-pane fade show active">
					<div class="table-responsive">
						<table class="table table-hover table-sm">
							<thead>
								<tr>
									<th>Nom</th>
									<th>Type de la municipal</th>
									<th>Date création</th>
									<th>Actions</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="m" items="${sessionScope.municipaux}">
									<tr>
										<td>${m.nom}</td>
										<td>${m.typeMunicipal}</td>
										<td>${m.dateCreation}</td>
										<td>
											<button class="btn btn-sm btn-outline-primary btn-action"
												data-bs-toggle="modal" data-bs-target="#editMunicipalModal"
												data-id="${m.idMunicipal}" data-nom="${m.nom}"
												data-type="${m.typeMunicipal}" data-date="${m.dateCreation}"
												data-idRegion="${m.idRegion}"
												title="Modifier">
												<i class="bi bi-pencil"></i>
											</button>

											<button class="btn btn-sm btn-outline-danger btn-action"
												data-bs-toggle="modal"
												data-bs-target="#deleteMunicipalModal"
												data-id="${m.idMunicipal}" data-nom="${m.nom}"
												title="Supprimer">
												<i class="bi bi-trash"></i>
											</button>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div class="d-flex justify-content-center mt-3">
				<ul class="pagination" id="pagination"></ul>
			</div>
		</div>
	</div>

	<div class="modal fade" id="editMunicipalModal" tabindex="-1">
		<div class="modal-dialog">
			<form action="${pageContext.request.contextPath}/MunicipalServlet" method="post">
			
				<input type="hidden" name="action" value="update"> 
				<input type="hidden" name="idMunicipal" id="edit-idMunicipal">
				<input type="hidden" name="idRegion" id="edit-idRegion">
				

				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title">Modifier une municipalité</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
					</div>

					<div class="modal-body">
						<label class="form-label">Nom</label> <input
							id="edit-nomMunicipal" name="nom" class="form-control"> 
						<label
							class="form-label mt-2">Type</label> <input
							id="edit-typeMunicipal" name="type" class="form-control"> 
						<label
							class="form-label mt-2">Région</label> 
						<select id="edit-selectRegion" name="idRegion" class="form-control">
							<c:forEach var="r" items="${sessionScope.regions}">
								<option value="${r.idRegion}">${r.nom}</option>
							</c:forEach>
						</select> 
						<label class="form-label mt-2">Date de création</label> 
						<input id="edit-dateMunicipal" name="date" class="form-control" readonly>
					</div>

					<div class="modal-footer">
						<button class="btn btn-primary">Modifier</button>
					</div>
				</div>
			</form>
		</div>
	</div>

	<div class="modal fade" id="deleteMunicipalModal" tabindex="-1">
		<div class="modal-dialog">
			<form action="${pageContext.request.contextPath}/MunicipalServlet" method="post">
			
				<input type="hidden" name="action" value="delete"> 
				<input type="hidden" name="idMunicipal" id="delete-idMunicipal">

				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title">Supprimer</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
					</div>

					<div class="modal-body">
						<p id="delete-message"></p>
					</div>

					<div class="modal-footer">
						<button class="btn btn-secondary" data-bs-dismiss="modal">Annuler</button>
						<button class="btn btn-danger">Supprimer</button>
					</div>
				</div>
			</form>
		</div>
	</div>




	<!-- Bootstrap JS -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>



<script>
	function confirmDelete(type, name) {
	    if (confirm(`Êtes-vous sûr de vouloir supprimer ${type} "${name}" ?\n\nCette action est irréversible.`)) {
	        alert(`${type} "${name}" supprimé avec succès.`);
	        // Ici vous ajouteriez la logique de suppression réelle
	    }
	}
		
	document.addEventListener('DOMContentLoaded', function() {
		
// 		document.addEventListener("click", function(e) {

// 		    const button = e.target.closest("button[data-bs-target]");

// 		    if (!button) return;

// 		    const modalId = button.getAttribute("data-bs-target");

// 		    // ---- MODAL MODIFICATION ----
// 		    if (modalId === "#editMunicipalModal") {
		    	
		    	
// 		        document.getElementById("edit-idMunicipal").value = button.dataset.id;
// 		        document.getElementById("edit-nomMunicipal").value = button.dataset.nom;
// 		        document.getElementById("edit-typeMunicipal").value = button.dataset.type;
// 		        document.getElementById("edit-dateMunicipal").value = button.dataset.date;
// 		        document.getElementById("edit-selectRegion").value = button.dataset.idRegion;

		        
		        
// 		        const idRegion = button.dataset.idRegion;

// 		        // Sélectionner l'option correcte dans le select
// 		        const selectRegion = document.getElementById("edit-selectRegion");
// 		        selectRegion.value = idRegion;
// 		    }

// 		    // ---- MODAL SUPPRESSION ----
// 		    if (modalId === "#deleteMunicipalModal") {
// 		        document.getElementById("delete-idMunicipal").value = button.dataset.id;
// 		        document.getElementById("delete-message").innerHTML =
// 		            `Voulez-vous vraiment supprimer la municipalité : <strong>${button.dataset.nom}</strong> ?`;
// 		    }
// 		});
		
		// ---- MODAL MODIF ----
		const editModal = document.getElementById("editMunicipalModal");

	    if (editModal) {
	        editModal.addEventListener("show.bs.modal", function (event) {
	            const button = event.relatedTarget;

	            // Récupération des attributs data-*
	            document.getElementById("edit-idMunicipal").value = button.getAttribute("data-id");
	            document.getElementById("edit-nomMunicipal").value = button.getAttribute("data-nomMunicipal");
	            document.getElementById("edit-typeMunicipal").value = button.getAttribute("data-typeMunicipal");
	            document.getElementById("edit-dateMunicipal").value = button.getAttribute("data-dateMunicipal");
	            document.getElementById("edit-selectRegion").value = button.getAttribute("data-idRegion");

	            const idRegion = button.getAttribute("data-idRegion");

		        // Sélectionner l'option correcte dans le select
		        const selectRegion = document.getElementById("edit-selectRegion");
		        selectRegion.value = idRegion;
	        });
	    }
	    
	 // ========== MODAL SUPPRESSION SIGNALEMENT ==========
	    const deleteModal = document.getElementById('deleteModal');

	    if (deleteModal) {
	        deleteModal.addEventListener('show.bs.modal', function(event) {
	            const button = event.relatedTarget;
	            

	            document.getElementById("delete-idMunicipal").value = button.getAttribute("data-id");

	            const id = button.getAttribute("data-id");
	            //const type = button.getAttribute("data-type");
	            
	            // Remplir les champs cachés du formulaire
	            document.getElementById("deleteId").value = id;
	            
	        });
	    }


		
		
	// Animation
	const tables = document.querySelectorAll('.table tbody tr');
	tables.forEach((row, index) => {
	row.style.opacity = '0';
	row.style.transform = 'translateY(20px)';
	setTimeout(() => {
	row.style.transition = 'all 0.3s ease';
	row.style.opacity = '1';
	row.style.transform = 'translateY(0)';
	}, index * 50);
	});
	
	// Pagination
	const rowsPerPage = 5;
	const table = document.querySelector('.table tbody');
	const rows = Array.from(table.querySelectorAll('tr'));
	const pagination = document.getElementById('pagination');
	const totalPages = Math.ceil(rows.length / rowsPerPage);
	let currentPage = 1;
	
	function displayPage(page) {
	rows.forEach((row, index) => {
	row.style.display = (index >= (page - 1) * rowsPerPage && index < page * rowsPerPage) ? '' : 'none';
	});
	renderPagination();
	}
	
	function renderPagination() {
	pagination.innerHTML = '';
	
	const prevLi = document.createElement('li');
	prevLi.classList.add('page-item');
	if (currentPage === 1) prevLi.classList.add('disabled');
	prevLi.innerHTML = `<a class="page-link" href="#">Précédent</a>`;
	prevLi.addEventListener('click', e => {
	e.preventDefault();
	if (currentPage > 1) {
	currentPage--;
	displayPage(currentPage);
	}
	});
	pagination.appendChild(prevLi);
	
	for (let i = 1; i <= totalPages; i++) {
	const li = document.createElement('li');
	li.classList.add('page-item');
	if (i === currentPage) li.classList.add('active');
	const a = document.createElement('a');
	a.classList.add('page-link');
	a.href = '#';
	a.textContent = i;
	a.addEventListener('click', e => {
	  e.preventDefault();
	  currentPage = i;
	  displayPage(currentPage);
	});
	li.appendChild(a);
	pagination.appendChild(li);
	}
	
	const nextLi = document.createElement('li');
	nextLi.classList.add('page-item');
	if (currentPage === totalPages) nextLi.classList.add('disabled');
	nextLi.innerHTML = `<a class="page-link" href="#">Suivant</a>`;
	nextLi.addEventListener('click', e => {
	e.preventDefault();
	if (currentPage < totalPages) {
	currentPage++;
	displayPage(currentPage);
	}
	});
	pagination.appendChild(nextLi);
	}
	
	displayPage(currentPage);
	});
</script>

</body>
</html>
