<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inscription - UrbAlert</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
   <style>
        :root {
            --primary-color: #1976D2;
            --primary-hover: #1565C0;
            --secondary-color: #43A047;
            --text-dark: #212121;
            --text-gray: #666;
        }

        body {
            font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, sans-serif;
            background-color: #f8f9fa;
            color: var(--text-dark);
        }

        .header {
            background: white;
            box-shadow: 0 1px 3px rgba(0,0,0,0.1);
            padding: 1rem 0;
            margin-bottom: 2rem;
        }

        .logo {
            display: flex;
            align-items: center;
            gap: 0.5rem;
            color: var(--primary-color);
            font-weight: 600;
            font-size: 1.125rem;
            text-decoration: none;
        }

        .logo i {
            font-size: 1.75rem;
        }

        .register-container {
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: calc(100vh - 100px);
        }

        .register-card {
            background: white;
            border-radius: 12px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.1);
            padding: 2.5rem;
            width: 100%;
            max-width: 900px;
        }

        .register-card h2 {
            font-weight: 700;
            margin-bottom: 0.5rem;
            text-align: center;
        }

        .register-card p {
            color: var(--text-gray);
            margin-bottom: 2rem;
            text-align: center;
        }

        .form-label {
            font-weight: 600;
            color: var(--text-dark);
        }

        .form-control {
            border: 2px solid #e0e0e0;
            border-radius: 8px;
            padding: 0.75rem;
            margin-bottom: 1rem;
            transition: all 0.3s ease;
        }

        .form-control:focus {
            border-color: var(--primary-color);
            box-shadow: 0 0 0 0.2rem rgba(25, 118, 210, 0.1);
        }

        .btn-register {
         background-color: var(--primary-color);
            color: white;
            padding: 0.75rem;
            border: none;
            border-radius: 8px;
            font-weight: 600;
            width: 100%;
            transition: background-color 0.3s ease;
        }

        .btn-register:hover {
            background-color: var(--primary-hover);
        }

        .login-link {
            margin-top: 1rem;
            color: var(--text-gray);
            text-align: center;
        }

        .login-link a {
            color: var(--primary-color);
            text-decoration: none;
            font-weight: 600;
        }

        .login-link a:hover {
            text-decoration: underline;
        }

        @media (max-width: 768px) {
            .register-card {
                margin: 1rem;
                padding: 2rem;
            }
        }
    </style>
        
</head>
<body>
 
    <header class="header">
        <div class="container d-flex justify-content-between align-items-center">
            <a href="AccueilServlet" class="logo">
                <i class="fas fa-building"></i>
                <span>UrbAlert – Signalez, Suivez, Améliorez Votre Ville</span>
            </a>
        </div>
    </header>
 
    <div class="register-container">
        <div class="register-card">
            <h2>Créer un compte</h2>
            <p>Veuillez remplir les informations ci-dessous</p>

            <form action="RegisterServlet" method="post" onsubmit="return validateForm()">
                <div class="row">
                    <div class="col-12">
                    
                    <input type="hidden" name="typeCompte" value="citoyen" class="form-control" >
                    
                    
                        <label for="nom" class="form-label">Nom</label>
                        <input type="text" id="nom" name="nom" class="form-control" required>

                        <label for="prenom" class="form-label">Prénom</label>
                        <input type="text" id="prenom" name="prenom" class="form-control" required>

                        <label for="cin" class="form-label">CIN</label>
                        <input type="text" id="cin" name="cin" class="form-control" required>

                        <label for="lieu_naissance" class="form-label">Lieu de naissance</label>
                        <input type="text" id="lieu_naissance" name="lieu_naissance" class="form-control" required>
                    
                        <label for="telephone" class="form-label">Téléphone</label>
                        <input type="tel" id="telephone" name="telephone" class="form-control" required pattern="^0[5-7][0-9]{8}$" placeholder="ex: 0612345678">

                        <label for="email" class="form-label">Email</label>
                        <input type="email" id="email" name="email" class="form-control" required>

                        <label for="date_naissance" class="form-label">Date de naissance</label>
                        <input type="date" id="date_naissance" name="date_naissance" class="form-control" required>

                        <label for="mot_de_passe" class="form-label">Mot de passe</label>
                        <input type="password" id="mot_de_passe" name="mot_de_passe" class="form-control" placeholder="••••••••" required>

 
						
							<label for="idRegion" class="form-label"> Région <span
								style="color: red">*</span>
							</label>
							<div class="input-icon">
								<i class="fas fa-map-marked-alt"></i> <select
									class="form-control" id="idRegion" name="idRegion" required>
									<option value="">Sélectionnez une région</option>

									<c:forEach var="region" items="${sessionScope.regions}">
										<option value="${region.idRegion}">${region.nom}</option>
									</c:forEach>
								</select>
							</div>
							<small class="text-muted">Votre région de résidence</small>
						

						<small class="text-muted">Région d'appartenance du
								municipal</small>
						</div>

					</div>
                

                <button type="submit" class="btn-register mt-3">Créer un compte</button>
            

            <div class="login-link">
                Vous avez déjà un compte ? <a href="${pageContext.request.contextPath}/views/Auth/Connexion.jsp">Connectez-vous</a>
            </div>
            </form>

</body>
</html>