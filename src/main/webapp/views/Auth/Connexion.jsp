<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Connexion - UrbAlert</title>
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

        /* Header */
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

        /* Form Card */
        .login-container {
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: calc(100vh - 100px);
        }

        .login-card {
            background: white;
            border-radius: 12px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.1);
            padding: 2.5rem;
            width: 100%;
            max-width: 420px;
            text-align: center;
        }

        .login-card h2 {
            font-weight: 700;
            margin-bottom: 0.5rem;
        }

        .login-card p {
            color: var(--text-gray);
            margin-bottom: 2rem;
        }

        .form-label {
            font-weight: 600;
            color: var(--text-dark);
            text-align: left;
            display: block;
        }

        .form-control, .form-select {
            border: 2px solid #e0e0e0;
            border-radius: 8px;
            padding: 0.75rem;
            margin-bottom: 1rem;
            transition: all 0.3s ease;
        }

        .form-control:focus, .form-select:focus {
            border-color: var(--primary-color);
            box-shadow: 0 0 0 0.2rem rgba(25, 118, 210, 0.1);
        }

        .btn-login {
            background-color: var(--primary-color);
            color: white;
            padding: 0.75rem;
            border: none;
            border-radius: 8px;
            font-weight: 600;
            width: 100%;
            transition: background-color 0.3s ease;
        }

        .btn-login:hover {
            background-color: var(--primary-hover);
        }

        .register-link {
            margin-top: 1rem;
            color: var(--text-gray);
        }

        .register-link a {
            color: var(--primary-color);
            text-decoration: none;
            font-weight: 600;
        }

        .register-link a:hover {
            text-decoration: underline;
        }

        @media (max-width: 768px) {
            .login-card {
                margin: 1rem;
                padding: 2rem;
            }
        }
    </style>
</head>
<body>

    <!-- Header -->
    <header class="header">
        <div class="container d-flex justify-content-between align-items-center">
            <a href="Accueil.jsp" class="logo">
                <i class="fas fa-building"></i>
                <span>UrbAlert – Signalez, Suivez, Améliorez Votre Ville</span>
            </a>
            
        </div>
    </header>

    <!-- Login Form -->
    <div class="login-container">
        <div class="login-card">
            <h2>Bienvenue</h2>
            <p>Connectez-vous à votre compte</p>

			<form action="${pageContext.request.contextPath}/LoginServlet" method="post">
				<div class="text-start">
					
					<label for="email" class="form-label">Email</label> <input
						type="email" id="email" name="email" class="form-control"
						placeholder="CIN@municipal.com" required> 
						
					<label for="password" class="form-label">Password</label> <input
						type="password" id="password" name="password" class="form-control"
						placeholder="••••••••" required>

				</div>

				<button type="submit" class="btn-login mt-3">Se connecter</button>
			</form>

			<div class="register-link">
                Vous n'avez pas de compte ? 
                <a href="${pageContext.request.contextPath}/RegisterServlet" class="text-primary">Inscrivez-vous</a>
                
            </div>
        </div>
    </div>

    <script>
        function validateEmail() {
            const email = document.getElementById('email').value;
            if (!email.endsWith('@municipal.com')) {
                alert("Veuillez utiliser une adresse e-mail se terminant par '@municipal.com'");
                return false;
            }
            return true;
        }
    </script>

</body>
</html>
