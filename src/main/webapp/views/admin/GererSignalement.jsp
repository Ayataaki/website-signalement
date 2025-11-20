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

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.0/font/bootstrap-icons.css">

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
		font-size: 0.85rem;  
	}
	
	.table td, .table th {
		padding: 0.5rem 0.75rem;  
		white-space: nowrap; 
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
	
		<jsp:include page="sidebarAdmin.jsp">
			<jsp:param name="activePage" value="reports" />
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
	
	<div class="main-content">
		<div class="content-wrapper">
			<div class="d-flex justify-content-between align-items-center mb-4">
				<div>
					<h1 class="text-dark mb-1">
						<i class="fas fa-clipboard-list"></i> Gestion des signalements
					</h1>
					<p class="text-muted">Gérer les signalements</p>
				</div>
			</div>

			<div class="tab-content">

				<div class="tab-pane fade show active" id="citoyens" role="tabpanel">
					<div class="table-responsive">
						<table class="table table-hover table-sm" id="table-citoyens">
							<thead>
								<tr>
									<th>Designation</th>
									<th>Description</th>
									<th>Adresse</th>
									<th>Statut</th>
									<th>Date création</th>
									<th>Actions</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="s" items="${sessionScope.signalements}">
									<tr>
										<td>${s.designation}</td>
										<td>${s.description}</td>
										<td>${s.localisation}</td>
										<td>${s.statut.label}</td>
										<td>${s.dateCreation}</td>
										<td>			 
											<button class="btn btn-sm btn-outline-primary btn-action"
												data-bs-toggle="modal" data-bs-target="#editUserModal"
												data-type="signalement" data-id="${s.idSignalement}"
												data-designation="${s.designation}"
												data-description="${s.description}"
												data-localisation="${s.localisation}"
												data-commentaire="${s.commentaire}"
												data-idCitoyen="${s.idCitoyen}"
												data-statut="${s.statut.label}" title="Modifier">
												<i class="bi bi-pencil"></i>
											</button> 
											<button class="btn btn-sm btn-outline-danger btn-action"
												data-type="signalement" data-id="${s.idSignalement}"
												data-bs-toggle="modal" data-bs-target="#deleteModal"
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

	<div class="modal fade" id="editUserModal" tabindex="-1"
		aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">

				<div class="modal-header">
					<h5 class="modal-title">
						<i class="bi bi-pencil-square me-2"></i> Modifier le signalement
					</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
				</div>

				<form action="${pageContext.request.contextPath}/SignalementServlet" method="post">

					<div class="modal-body">

						<input type="hidden" name="id" id="edit-id"> 
						<input type="hidden" name="idCitoyen" id="edit-idCitoyen">						
						<input type="hidden" name="action" value="updateAdmin"> 
						<input type="hidden" name="type" id="edit-type" value="signalement">

						<div class="mb-3">
							<label class="form-label">Désignation</label> <input type="text"
								class="form-control" id="edit-designation" name="designation"
								required>
						</div>

						<div class="mb-3">
							<label class="form-label">Description</label>
							<textarea class="form-control" id="edit-description"
								name="description" rows="3" required></textarea>
						</div>

						<div class="mb-3">
							<label class="form-label">Localisation</label> <input type="text"
								class="form-control" id="edit-localisation" name="localisation"
								required>
						</div>

						<div class="mb-3">
							<label class="form-label">Commentaire</label>
							<textarea class="form-control" id="edit-commentaire"
								name="commentaire" rows="2"></textarea>
						</div>

						<div class="mb-3">
							<label class="form-label">Statut</label> <select
								class="form-select" id="edit-statut" name="statut" required>
								<option value="new">Nouveau</option>
								<option value="processing">En cours</option>
								<option value="final">Résolu</option>
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
					<h5 class="modal-title">
						<i class="bi bi-exclamation-triangle me-2"></i> Confirmation de
						suppression
					</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
				</div>

				<div class="modal-body">
					<p class="mb-0">
						Voulez-vous vraiment supprimer <strong id="deleteUserName"></strong>
						?
					</p>
					<small class="text-danger"> <i
						class="bi bi-info-circle me-1"></i> Cette action est irréversible.
					</small>
				</div>

				<div class="modal-footer">
					<form id="deleteForm" action="${pageContext.request.contextPath}/SignalementServlet" method="post">
						
						<input type="hidden" name="action" value="deleteAdmin"> 
						<input type="hidden" name="deleteType" value="signalement"> 
						<input type="hidden" name="id" id="deleteId">

						<button type="button" class="btn btn-secondary"
							data-bs-dismiss="modal">
							<i class="bi bi-x-circle me-1"></i>Annuler
						</button>
						<button type="submit" class="btn btn-danger">
							<i class="bi bi-trash me-1"></i>Supprimer
						</button>
					</form>
				</div>

			</div>
		</div>
	</div>


	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>


	<script>
document.addEventListener('DOMContentLoaded', function () {
    
    const editModal = document.getElementById("editUserModal");

    if (editModal) {
        editModal.addEventListener("show.bs.modal", function (event) {
            const button = event.relatedTarget;
 
            document.getElementById("edit-type").value = button.getAttribute("data-type");
            document.getElementById("edit-id").value = button.getAttribute("data-id");
            document.getElementById("edit-designation").value = button.getAttribute("data-designation");
            document.getElementById("edit-description").value = button.getAttribute("data-description");
            document.getElementById("edit-localisation").value = button.getAttribute("data-localisation");
            document.getElementById("edit-commentaire").value = button.getAttribute("data-commentaire") || '';
            document.getElementById("edit-statut").value = button.getAttribute("data-statut");
            document.getElementById("edit-idCitoyen").value = button.getAttribute("data-idCitoyen");
 
        });
    }
     
    const deleteModal = document.getElementById('deleteModal');

    if (deleteModal) {
        deleteModal.addEventListener('show.bs.modal', function(event) {
            const button = event.relatedTarget;

            const id = button.getAttribute("data-id");
            const type = button.getAttribute("data-type");
             
            document.getElementById("deleteId").value = id;
             
            document.getElementById("deleteUserName").textContent = "ce signalement";
 
        });
    }
 
    function setupPagination(tableId, paginationId, rowsPerPage = 10) {
        const table = document.querySelector(`#${tableId} tbody`);
        if (!table) return;
        
        const rows = Array.from(table.querySelectorAll('tr'));
        const pagination = document.getElementById(paginationId);
        if (!pagination) return;
        
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
 
    setupPagination('table-citoyens', 'pagination', 10);
});
</script>
</body>
</html>
