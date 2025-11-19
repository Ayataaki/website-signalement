<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Gestion des Utilisateurs</title>
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
			<jsp:param name="activePage" value="users" />
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
						<i class="bi bi-people me-2"></i> Gestion des Utilisateurs
					</h1>
					<p class="text-muted">Gérer les citoyens et le personnel
						municipal</p>
				</div>
			</div>

			<!-- Tabs -->
			<ul class="nav nav-tabs tab-custom mb-4" id="userTabs" role="tablist">
				<li class="nav-item" role="presentation">
					<button class="nav-link active" id="citoyens-tab"
						data-bs-toggle="tab" data-bs-target="#citoyens" type="button"
						role="tab">
						<i class="bi bi-person me-2"></i> Citoyens
					</button>
				</li>
				<li class="nav-item" role="presentation">
					<button class="nav-link" id="employes-tab" data-bs-toggle="tab"
						data-bs-target="#employes" type="button" role="tab">
						<i class="bi bi-briefcase me-2"></i> Employés Municipaux
					</button>
				</li>
			</ul>


			<div class="tab-content">
				<!-- Citoyens -->
				<div class="tab-pane fade show active" id="citoyens" role="tabpanel">					
					<div class="table-responsive">
						<table class="table table-hover table-sm" id="table-citoyens">
							<thead>
								<tr>
									<th>Nom</th>
									<th>Prénom</th>
									<th>CIN</th>
									<th>Lieu Naissance</th>
									<th>Téléphone</th>
									<th>Email</th>
									<th>Date Naissance</th>
									<th>Actions</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="citoyen" items="${sessionScope.citoyens}">
									<tr>
										<td>${citoyen.nom}</td>
										<td>${citoyen.prenom}</td>
										<td>${citoyen.cin}</td>
										<td>${citoyen.lieuNaissance}</td>
										<td>${citoyen.telephone}</td>
										<td>${citoyen.email}</td>
										<td>${citoyen.dateNaissance}</td>
										<td>
											<button class="btn btn-sm btn-outline-primary btn-action"
												data-bs-toggle="modal" data-bs-target="#editUserModal"
												data-type="citoyen" data-id="${citoyen.idCitoyen}"
												data-nom="${citoyen.nom}" data-prenom="${citoyen.prenom}"
												data-cin="${citoyen.cin}"
												data-telephone="${citoyen.telephone}"
												data-email="${citoyen.email}"
												data-lieu="${citoyen.lieuNaissance}"
												data-idRegion="${citoyen.idRegion}"
												data-date="${citoyen.dateNaissance}" title="Modifier">
												<i class="bi bi-pencil"></i>
											</button>

											<button class="btn btn-sm btn-outline-danger btn-action"
												data-type="citoyen" data-id="${citoyen.idCitoyen}"
												data-name="${citoyen.nom} ${citoyen.prenom}"
												data-bs-toggle="modal" data-bs-target="#deleteModal">
												<i class="bi bi-trash"></i>
											</button>

										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<ul class="pagination justify-content-center mt-3"
							id="pagination-citoyens"></ul>
					</div>
				</div>
				<!-- Employés -->
				<div class="tab-pane fade" id="employes" role="tabpanel">
				
					<!-- Bouton ajouter employé -->
					<div class="d-flex justify-content-end mb-3">
						<button class="btn btn-primary" data-bs-toggle="modal"
							data-bs-target="#addEmployeModal">
							<i class="bi bi-person-plus me-2"></i> Ajouter un employé
						</button>
					</div>
					
					<div class="table-responsive">
						<table class="table table-hover table-sm" id="table-employes">
							<thead>
								<tr>
									<th>Nom</th>
									<th>Prénom</th>
									<th>CIN</th>
									<th>Téléphone</th>
									<th>Email</th>
									<th>Date Naissance</th>
									<th>Actions</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="employe" items="${sessionScope.employes}">
									<tr>
										<td>${employe.nom}</td>
										<td>${employe.prenom}</td>
										<td>${employe.cin}</td>
										<td>${employe.telephone}</td>
										<td>${employe.email}</td>
										<td>${employe.dateNaissance}</td>
										<td>
											<button class="btn btn-sm btn-outline-primary btn-action"
												data-bs-toggle="modal" data-bs-target="#editUserModal"
												data-type="employe" data-id="${employe.idEmploye}"
												data-nom="${employe.nom}" data-prenom="${employe.prenom}"
												data-cin="${employe.cin}"
												data-telephone="${employe.telephone}"
												data-email="${employe.email}"
												data-lieu="${employe.lieuNaissance}"
												data-idMunicipal = "${employe.idMunicipal}"
												data-date="${employe.dateNaissance}" title="Modifier">
												<i class="bi bi-pencil"></i>
											</button>

											<button class="btn btn-sm btn-outline-danger btn-action"
												data-type="employe" data-id="${employe.idEmploye}"
												data-name="${employe.nom} ${employe.prenom}"
												data-bs-toggle="modal" data-bs-target="#deleteModal">
												<i class="bi bi-trash"></i>
											</button>

										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<ul class="pagination justify-content-center mt-3"
							id="pagination-employes"></ul>
					</div>
				</div>

			</div>
		</div>
	</div>


	<!-- Bootstrap JS -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>



	<!-- Modal Ajouter Employé -->
	<div class="modal fade" id="addEmployeModal" tabindex="-1"
		aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">

				<div class="modal-header">
					<h5 class="modal-title">
						<i class="bi bi-person-plus me-2"></i> Ajouter un employé
						municipal
					</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
				</div>

				<form action="${pageContext.request.contextPath}/RegisterServlet" method="post"
					onsubmit="return validateForm()">

					<div class="modal-body">

						<input type="hidden" name="typeCompte" value="employe"
							class="form-control">

						<div class="mb-3">
							<label for="nom" class="form-label">Nom</label> <input
								type="text" id="nom" name="nom" class="form-control" required>
						</div>

						<div class="mb-3">
							<label for="prenom" class="form-label">Prénom</label> <input
								type="text" id="prenom" name="prenom" class="form-control"
								required>
						</div>

						<div class="mb-3">
							<label for="cin" class="form-label">CIN</label> <input
								type="text" id="cin" name="cin" class="form-control" required>
						</div>

						<div class="mb-3">
							<label for="telephone" class="form-label">Téléphone</label> <input
								type="tel" id="telephone" name="telephone" class="form-control"
								required pattern="^0[5-7][0-9]{8}$" placeholder="ex: 0612345678">
						</div>

						<div class="mb-3">
							<label for="email" class="form-label">Email</label> <input
								type="email" id="email" name="email" class="form-control"
								required>
						</div>

						<div class="mb-3">
							<label for="lieu_naissance" class="form-label">Lieu de
								naissance</label> <input type="text" id="lieu_naissance"
								name="lieu_naissance" class="form-control" required>
						</div>

						<div class="mb-3">
							<label for="date_naissance" class="form-label">Date de
								naissance</label> <input type="date" id="date_naissance"
								name="date_naissance" class="form-control" required>
						</div>

						<div class="mb-3">
							<label for="mot_de_passe" class="form-label">Mot de passe</label>
							<input type="password" id="mot_de_passe" name="mot_de_passe"
								class="form-control" placeholder="••••••••" required>
						</div>

						<!-- Région -->
						<div class="mb-4">
							<label for="idRegion" class="form-label"> Municipal <span
								style="color: red">*</span>
							</label>
							<div class="input-icon">
								<i class="fas fa-map-marked-alt"></i> <select
									class="form-control" id="idMunicipal" name="idMunicipal"
									required>
									<option value="">Sélectionnez un municipal</option>

									<c:forEach var="m" items="${sessionScope.municipaux}">
										<option value="${m.idMunicipal}">${m.nom}</option>
									</c:forEach>
								</select>
							</div>
							<small class="text-muted">Votre région de résidence</small>
						</div>

					</div>

					<div class="modal-footer">
						<button type="button" class="btn btn-outline-secondary"
							data-bs-dismiss="modal">Annuler</button>
						<button type="submit" class="btn btn-primary">Ajouter</button>
					</div>

				</form>

			</div>
		</div>
	</div>

	<!-- Modal Modification Utilisateur -->
	<div class="modal fade" id="editUserModal" tabindex="-1"
		aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">

				<div class="modal-header">
					<h5 class="modal-title">
						<i class="bi bi-pencil-square me-2"></i> Modifier l’utilisateur
					</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
				</div>

				<form action="${pageContext.request.contextPath}/UpdateServlet"
					method="post">

					<div class="modal-body">

						<input type="hidden" name="id" id="edit-id"> 
						<input type="hidden" name="type" id="edit-type">

						<div class="mb-3">
							<label class="form-label">Nom</label> <input type="text"
								class="form-control" id="edit-nom" name="nom" required>
						</div>

						<div class="mb-3">
							<label class="form-label">Prénom</label> <input type="text"
								class="form-control" id="edit-prenom" name="prenom" required>
						</div>

						<div class="mb-3">
							<label class="form-label">CIN</label> <input type="text"
								class="form-control" id="edit-cin" name="cin" required>
						</div>

						<div class="mb-3">
							<label class="form-label">Téléphone</label> <input type="text"
								class="form-control" id="edit-telephone" name="telephone"
								required>
						</div>

						<div class="mb-3">
							<label class="form-label">Email</label> <input type="email"
								class="form-control" id="edit-email" name="email" required>
						</div>

						<div class="mb-3">
							<label class="form-label">Lieu Naissance</label> <input
								type="text" class="form-control" id="edit-lieu"
								name="lieuNaissance" required>
						</div>

						<div class="mb-3">
							<label class="form-label">Date Naissance</label> <input
								type="date" class="form-control" id="edit-date"
								name="dateNaissance" required>
						</div>

						<div id="divMunicipal" style="display: none;">
							<label class="form-label">Municipalité</label> 
							<select id="idMunicipal" name="idMunicipal" class="form-control">
								<c:forEach var="m" items="${sessionScope.municipaux}">
									<option value="${m.idMunicipal}">${m.nom}</option>
								</c:forEach>
							</select>
						</div>


						<div id="divRegion" style="display: none;">
							<label class="form-label">Région</label> 
							<select id="idRegion" name="idRegion" class="form-control">
								<c:forEach var="r" items="${sessionScope.regions}">
									<option value="${r.idRegion}">${r.nom}</option>
								</c:forEach>
							</select>
						</div>

					</div>

					<div class="modal-footer">
						<button type="button" class="btn btn-outline-secondary"
							data-bs-dismiss="modal">Annuler</button>
						<button type="submit" class="btn btn-primary">Enregistrer</button>
					</div>

				</form>

			</div>
		</div>
	</div>

	<div class="modal fade" id="deleteModal" tabindex="-1"
		aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">

				<div class="modal-header">
					<h5 class="modal-title">Confirmation de suppression</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
				</div>

				<div class="modal-body">
					<p>
						Voulez-vous vraiment supprimer <strong id="deleteUserName"></strong>
						?
					</p>
				</div>

				<div class="modal-footer">
					<form id="deleteForm" action="${pageContext.request.contextPath}/UpdateServlet" method="post">
						<input type="hidden" name="action" value="delete"> 
						<input type="hidden" name="deleteType" id="deleteType"> 
						<input type="hidden" name="id" id="deleteId">
						<button type="button" class="btn btn-secondary"
							data-bs-dismiss="modal">Annuler</button>
						<button type="submit" class="btn btn-danger">Supprimer</button>
					</form>
				</div>

			</div>
		</div>
	</div>



	<script>
document.addEventListener('DOMContentLoaded', function () {
	
	const editModal = document.getElementById("editUserModal");

    editModal.addEventListener("show.bs.modal", function (event) {
        const button = event.relatedTarget;

        // Récupération des attributs data-*
        const type = button.getAttribute("data-type");
        document.getElementById("edit-type").value = button.getAttribute("data-type");
        document.getElementById("edit-id").value = button.getAttribute("data-id");
        document.getElementById("edit-nom").value = button.getAttribute("data-nom");
        document.getElementById("edit-prenom").value = button.getAttribute("data-prenom");
        document.getElementById("edit-cin").value = button.getAttribute("data-cin");
        document.getElementById("edit-telephone").value = button.getAttribute("data-telephone");
        document.getElementById("edit-email").value = button.getAttribute("data-email");
        document.getElementById("edit-lieu").value = button.getAttribute("data-lieu");
        document.getElementById("edit-date").value = button.getAttribute("data-date");
        if (type === "employe") {

            // Afficher champs municipal
            document.getElementById('divMunicipal').style.display = 'block';
            document.getElementById('divRegion').style.display = 'none';

            // Remplir les données
            document.getElementById('idMunicipal').value = button.getAttribute('data-idMunicipal');
            
         	// Remplir les données
            const idMunicipal = button.getAttribute('data-idMunicipal');
            const selectMunicipal = document.getElementById('idMunicipal');
            
            // Définir la valeur sélectionnée
            if (idMunicipal) {
                selectMunicipal.value = idMunicipal;
            }

        } else if (type === "citoyen") {

            // Afficher champs région
            document.getElementById('divMunicipal').style.display = 'none';
            document.getElementById('divRegion').style.display = 'block';

            // idRegion provenant du bouton
            document.getElementById('idRegion').value = button.getAttribute('data-idRegion');
            
            const idRegion = button.getAttribute('data-idRegion');
            const selectRegion = document.getElementById('idRegion');
            
            if (idRegion) {
                selectRegion.value = idRegion;
            }
            
        }
    });
	      
    const deleteModal = document.getElementById('deleteModal');

    deleteModal.addEventListener('show.bs.modal', function(event) {
        const button = event.relatedTarget;

        const id = button.getAttribute("data-id");
        const type = button.getAttribute("data-type");
        const name = button.getAttribute("data-name");

        // Remplir les champs cachés du formulaire
        document.getElementById("deleteId").value = id;
        document.getElementById("deleteType").value = type;
        
        // Afficher le nom dans le message de confirmation
        document.getElementById("deleteUserName").textContent = name;

        console.log('🗑️ Préparation suppression:', { id, type, name });
    });

    // ========== PAGINATION ==========
    function setupPagination(tableId, paginationId, rowsPerPage = 5) {
        const table = document.querySelector(`#${tableId} tbody`);
        const rows = Array.from(table.querySelectorAll('tr'));
        const pagination = document.getElementById(paginationId);
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
            const prevLink = document.createElement('a');
            prevLink.classList.add('page-link');
            prevLink.href = '#';
            prevLink.textContent = 'Précédent';
            prevLink.addEventListener('click', e => {
                e.preventDefault();
                if (currentPage > 1) {
                    currentPage--;
                    displayPage(currentPage);
                }
            });
            prevLi.appendChild(prevLink);
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
            const nextLink = document.createElement('a');
            nextLink.classList.add('page-link');
            nextLink.href = '#';
            nextLink.textContent = 'Suivant';
            nextLink.addEventListener('click', e => {
                e.preventDefault();
                if (currentPage < totalPages) {
                    currentPage++;
                    displayPage(currentPage);
                }
            });
            nextLi.appendChild(nextLink);
            pagination.appendChild(nextLi);
        }

        displayPage(currentPage);
    }

    // Initialiser la pagination
    setupPagination('table-citoyens', 'pagination-citoyens');
    setupPagination('table-employes', 'pagination-employes');
});
</script>

</body>
</html>
