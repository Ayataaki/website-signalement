<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Profil Administrateur</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">

    <style>
        :root {
            --primary-color: #1976D2;
            --primary-hover: #1565C0;
            --sidebar-width: 280px;
        }

        body {
            background-color: #f8f9fa;
            margin: 0;
            font-family: Arial, sans-serif;
        }

        /* ---------- SIDEBAR ---------- */
        .sidebar {
            width: var(--sidebar-width);
            background: white;
            box-shadow: 2px 0 10px rgba(0,0,0,0.1);
            position: fixed;
            top: 0;
            left: 0;
            height: 100vh;
            z-index: 1000;
        }

        .logo {
            padding: 1.5rem;
            background: linear-gradient(135deg, var(--primary-color), var(--primary-hover));
            color: white;
            border-bottom: 1px solid #e9ecef;
        }

        .logo h4 {
            margin: 0;
        }

        .sidebar .nav-link {
            padding: 1rem 1.5rem;
            color: #495057;
            display: flex;
            align-items: center;
            border-left: 3px solid transparent;
        }

        .sidebar .nav-link i {
            margin-right: 10px;
        }
        .layout-container {
	        display: flex;
	        min-height: 100vh;
          }

        .sidebar .nav-link:hover {
            background: #f1f1f1;
            color: var(--primary-color);
            border-left-color: var(--primary-color);
        }

        .sidebar .active {
            background: #e3f2fd;
            color: var(--primary-color);
            font-weight: 600;
            border-left-color: var(--primary-color);
        }

        /* ---------- MAIN CONTENT ---------- */
        .main-content {
            margin-left: var(--sidebar-width);
            padding: 2rem;
        }

        .profile-card {
            background: white;
            border-radius: 12px;
            padding: 2rem;
            box-shadow: 0 2px 8px rgba(0,0,0,0.1);
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
        }

        .avatar i {
            color: white;
            font-size: 3rem;
        }
        
 /* ---------- FORMULAIRE MODAL AGRANDI ---------- */
.modal-dialog {
    max-width: 900px; /* plus large que la taille par défaut */
}

.modal-content {
    border-radius: 12px;
    overflow: hidden;
    box-shadow: 0 4px 25px rgba(0,0,0,0.15);
}

.modal-header {
    background: linear-gradient(135deg, var(--primary-color), var(--primary-hover));
    color: white;
    border-bottom: none;
    padding: 2rem; /* plus d'espace */
}

.modal-header .btn-close {
    filter: invert(1); /* rend le bouton blanc */
}

.modal-title {
    margin: 0;
    font-weight: 600;
    font-size: 1.5rem;
}

.modal-body {
    padding: 2.5rem; /* plus de padding pour le corps du formulaire */
}

.form-label {
    font-weight: 500;
    margin-top: 1rem;
    display: block;
    font-size: 1rem;
}

.form-control {
    border: 1px solid #e0e0e0;
    border-radius: 8px;
    padding: 0.85rem; /* champ plus grand */
    width: 100%;
    margin-top: 0.25rem;
    font-size: 1rem;
    transition: all 0.3s ease;
}

.form-control:focus {
    border-color: var(--primary-color);
    box-shadow: 0 0 0 0.2rem rgba(25,118,210,0.15);
    outline: none;
}

.btn-register {
    background-color: var(--primary-color);
    color: white;
    border: none;
    border-radius: 8px;
    padding: 0.85rem 2rem; /* bouton plus grand */
    font-weight: 500;
    margin-top: 1.5rem;
    font-size: 1rem;
    transition: all 0.3s ease;
}
.btn-action {
	padding: 0.375rem 0.75rem;
	margin: 0 0.25rem;
}

.btn-register:hover {
    background-color: var(--primary-hover);
    transform: translateY(-1px);
    box-shadow: 0 4px 15px rgba(25,118,210,0.3);
}

.row .col-md-6 {
    padding-right: 1rem;
    padding-left: 1rem;
}
    </style>
</head>

<body>

<!-- ============ SIDEBAR ============ -->
<div class="layout-container">
<div class="sidebar">
    <div class="logo">
        <h4 class="mb-0">
            <i class="fas fa-shield-halved me-2"></i>Espace Administrateur
        </h4>
        <small>Plateforme de gestion</small>
    </div>

    <nav class="nav flex-column">
        <a href="views/admin/DashboardAdmin.jsp"  class="nav-link">
            <i class="fas fa-chart-line"></i> Dashboard
        </a>
        <a href="views/admin/GererUtilisateur.jsp" class="nav-link">
            <i class="fas fa-users"></i> Gérer Utilisateurs
        </a>
        <a href="views/admin/GererSignalement.jsp" class="nav-link">
            <i class="fas fa-clipboard-list"></i> Tous les Signalements
        </a>
        <a href="views/admin/GererMunicipaux.jsp" class="nav-link">
            <i class="fas fa-building"></i> Services Municipaux
        </a>
        <a href="views/admin/GererRegion.jsp" class="nav-link">
            <i class="fas fa-chart-pie"></i> Régions
        </a>
        <a href="views/admin/AdminServlet" class="nav-link active">
            <i class="fas fa-user-shield"></i> Profil 
        </a>
    </nav>
    
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
</div>


<!-- ============ MAIN CONTENT ============ -->
<div class="main-content">

    <div class="d-flex justify-content-between align-items-center mb-4">
        <h1 class="h3 mb-0">Profil Administrateur</h1>

        <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#modalAddAdmin">
    <i class="fas fa-user-plus me-2"></i>Créer un administrateur
</button>

    </div>

    <div class="row">

        <!-- COLONNE GAUCHE (profil) -->
        <div class="col-lg-4">
            <div class="profile-card text-center mb-4">
                <div class="avatar"><i class="fas fa-user-shield"></i></div>

                <h4>${admin.nom} ${admin.prenom}</h4>
                <p class="text-muted">${admin.nomUtilisateur}</p>
                <span class="badge bg-primary">Compte Administrateur</span>
            </div>

            <div class="profile-card">
                <h5><i class="fas fa-info-circle me-2 text-primary"></i> Informations système</h5>

                <div class="d-flex justify-content-between mt-3">
                    <span>ID Admin</span> <strong>${admin.idAdmin}</strong>
                </div>

                <div class="d-flex justify-content-between mt-3">
                    <span>Création du compte</span> <strong>${admin.dateCreation}</strong>
                </div>
            </div>
        </div>

        <!-- FORMULAIRE -->
        <div class="col-lg-8">
            <div class="profile-card mb-4">
                <h5><i class="fas fa-user-edit me-2 text-primary"></i> Modifier les informations</h5>

                <form action="AdminServlet" method="post">
                    <input type="hidden" name="action" value="insertion">
                    <input type="hidden" name="idAdmin" value="${admin.idAdmin}">

                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label>Nom</label>
                            <input type="text" class="form-control" name="nom" value="${admin.nom}" required>
                        </div>

                        <div class="col-md-6 mb-3">
                            <label>Prénom</label>
                            <input type="text" class="form-control" name="prenom" value="${admin.prenom}" required>
                        </div>
                    </div>

                    <div class="mb-3">
                        <label>CIN</label>
                        <input type="text" class="form-control" name="cin" value="${admin.cin}">
                    </div>

                    <div class="mb-3">
                        <label>Lieu de naissance</label>
                        <input type="text" class="form-control" name="lieuNaissance" value="${admin.lieuNaissance}">
                    </div>

                    <div class="mb-3">
                        <label>Téléphone</label>
                        <input type="tel" class="form-control" name="telephone" value="${admin.telephone}">
                    </div>

                    <div class="mb-3">
                        <label>Email</label>
                        <input type="email" class="form-control" name="email" value="${admin.email}">
                    </div>

                    <div class="text-end">
                        <button type="reset" class="btn btn-outline-secondary">Annuler</button>
                        <button type="submit" class="btn btn-primary">Enregistrer</button>
                    </div>
                </form>

            </div>
        </div>

        <!-- CHANGE PASSWORD -->
        <div class="col-lg-12">
            <div class="profile-card">
                <h5><i class="fas fa-lock me-2 text-primary"></i>Changer le mot de passe</h5>

                <form action="AdminServlet" method="post" class="mt-3">
                    <input type="hidden" name="action" value="changerMdp">
                    <input type="hidden" name="idAdmin" value="${admin.idAdmin}">

                    <label>Mot de passe actuel</label>
                    <input type="password" class="form-control mb-3" name="currentPassword">

                    <label>Nouveau mot de passe</label>
                    <input type="password" class="form-control mb-3" name="newPassword">

                    <label>Confirmer le mot de passe</label>
                    <input type="password" class="form-control mb-3" name="confirmPassword">

                    <button class="btn btn-primary"><i class="fas fa-key me-2"></i>Changer le mot de passe</button>
                </form>
            </div>
        </div>

    </div>

</div>


<!-- ======== MODAL AJOUT ADMIN ======== -->
<div class="modal fade" id="modalAddAdmin" tabindex="-1">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">

      <div class="modal-header">
        <h5 class="modal-title">Ajouter un administrateur</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
      </div>

      <form action="${pageContext.request.contextPath}/RegisterServlet" method="post" onsubmit="return validateForm()">
                <div class="row">
                    <div class="col-md-6">
                    
                    <input type="hidden" name="typeCompte" value="admin" class="form-control" >
                    
                    
                        <label for="nom" class="form-label">Nom</label>
                        <input type="text" id="nom" name="nom" class="form-control" required>

                        <label for="prenom" class="form-label">Prénom</label>
                        <input type="text" id="prenom" name="prenom" class="form-control" required>

                        <label for="cin" class="form-label">CIN</label>
                        <input type="text" id="cin" name="cin" class="form-control" required>

                        <label for="lieu_naissance" class="form-label">Lieu de naissance</label>
                        <input type="text" id="lieu_naissance" name="lieu_naissance" class="form-control" required>
                        </div>

                        <div class="col-md-6">
                        <label for="telephone" class="form-label">Téléphone</label>
                        <input type="tel" id="telephone" name="telephone" class="form-control" required pattern="^0[5-7][0-9]{8}$" placeholder="ex: 0612345678">

                        <label for="email" class="form-label">Email</label>
                        <input type="email" id="email" name="email" class="form-control" placeholder="ex: user@municipal.com" required>

                        <label for="date_naissance" class="form-label">Date de naissance</label>
                        <input type="date" id="date_naissance" name="date_naissance" class="form-control" required>

                        <label for="mot_de_passe" class="form-label">Mot de passe</label>
                        <input type="password" id="mot_de_passe" name="mot_de_passe" class="form-control" placeholder="••••••••" required>

					</div>
                </div>

                <button type="submit" class="btn-register mt-3">Créer un compte</button>
            </form>
    </div>
  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>