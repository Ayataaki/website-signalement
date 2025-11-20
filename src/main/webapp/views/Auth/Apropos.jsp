<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>À propos – UrbAlert</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">

    <style>
        :root {
            --primary-color: #1976D2;
            --primary-hover: #1565C0;
            --secondary-color: #43A047;
            --secondary-hover: #388E3C;
            --text-dark: #212121;
            --text-gray: #666;
        }

        body {
            font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, sans-serif;
            color: var(--text-dark);
            margin: 0;
        }

        .header {
            background: white;
            box-shadow: 0 1px 3px rgba(0,0,0,0.1);
            position: sticky;
            top: 0;
            z-index: 1000;
        }

        .header-container {
            max-width: 1280px;
            margin: 0 auto;
            padding: 0 1.5rem;
            display: flex;
            justify-content: space-between;
            align-items: center;
            height: 64px;
        }

        .logo {
            display: flex;
            align-items: center;
            gap: 0.5rem;
            text-decoration: none;
            color: var(--primary-color);
            font-weight: 600;
            font-size: 1.125rem;
        }

        .nav-menu {
            display: flex;
            align-items: center;
            gap: 1.5rem;
        }

        .nav-link {
            color: var(--text-dark);
            text-decoration: none;
            transition: color 0.3s;
            font-weight: 500;
        }

        .nav-link:hover {
            color: var(--primary-color);
        }

        .btn-outline {
            padding: 0.5rem 1.25rem;
            border: 2px solid var(--primary-color);
            color: var(--primary-color);
            background: white;
            border-radius: 6px;
            font-weight: 600;
            text-decoration: none;
            transition: all 0.3s;
        }

        .btn-outline:hover {
            background: var(--primary-color);
            color: white;
        }

        .btn-primary {
            padding: 0.5rem 1.25rem;
            background: var(--primary-color);
            color: white;
            border: none;
            border-radius: 6px;
            font-weight: 600;
            text-decoration: none;
            transition: all 0.3s;
        }

        .btn-primary:hover {
            background: var(--primary-hover);
        }

        .about-section {
            padding: 4rem 2rem;
            background-color: #f8f9fa;
            text-align: center;
        }

        .about-section h1 {
            font-size: 2.5rem;
            color: var(--primary-color);
            font-weight: 700;
            margin-bottom: 1rem;
        }

        .about-section p {
            max-width: 800px;
            margin: 0 auto;
            font-size: 1.125rem;
            color: var(--text-gray);
            line-height: 1.8;
        }

        .about-mission {
            background: white;
            padding: 3rem 2rem;
            margin: 3rem auto;
            max-width: 900px;
            border-radius: 12px;
            box-shadow: 0 5px 20px rgba(0,0,0,0.1);
        }

        .about-mission h2 {
            color: var(--text-dark); 
            font-size: 2rem;
            margin-bottom: 1rem;
            font-weight: 700;
        }

        .about-mission p {
            font-size: 1.1rem;
            color: var(--text-dark);
            line-height: 1.7;
        }

        .footer {
            background-color: var(--text-dark);
            color: white;
            padding: 2rem 0 1rem;
            text-align: center;
        }

        .footer a {
            color: rgba(255,255,255,0.8);
            text-decoration: none;
        }

        .footer a:hover {
            color: white;
        }
    </style>
</head>
<body>
 
    <header class="header">
        <div class="header-container">
            <a href="index.jsp" class="logo">
                <i class="fas fa-building"></i>
                <span>UrbAlert – Signalez, Suivez, Améliorez Votre Ville</span>
            </a>
            <nav class="nav-menu">
                <a href="Accueil.jsp" class="nav-link">Accueil</a>
                <a href="Connexion.jsp" class="btn-outline">Connexion</a>
                <a href="Inscription.jsp" class="btn-primary">Inscription</a>
            </nav>
        </div>
    </header>
 
    <section class="about-section">
        <h1>À propos d'UrbAlert</h1>
        <p><br><br><br>
            UrbAlert est une plateforme moderne conçue pour combler le fossé entre les citoyens et les services municipaux.
            Notre mission est de rendre les villes plus réactives et d’améliorer la qualité de vie urbaine grâce à la technologie
            et à l’engagement communautaire.
        </p>
    </section>
 
    <section class="about-mission">
        <h2>                 Notre Mission</h2>
        <p>
        
        
        
        
            En donnant aux citoyens la possibilité de signaler des problèmes et en permettant aux services municipaux 
            de répondre efficacement, nous créons un écosystème collaboratif pour l’amélioration urbaine.
            <br><br>
            Grâce à UrbAlert, chaque citoyen devient acteur du changement, contribuant à une ville plus propre, plus sûre et plus agréable à vivre.
        </p>
    </section>
 
    <footer class="footer">
        <div class="container">
            <div class="row">
                <div class="col-lg-4 mb-4">
                    <h5>Liens rapides</h5>
                    <ul class="list-unstyled">
                        <li><a href="#">Politique de confidentialité</a></li>
                        <li><a href="#">Conditions d'utilisation</a></li>
                        <li><a href="#">FAQ</a></li>
                    </ul>
                </div>
                
                <div class="col-lg-4 mb-4">
                    <h5>Suivez-nous</h5>
                    <div class="social-icons">
                        <a href="#"><i class="fab fa-facebook"></i></a>
                        <a href="#"><i class="fab fa-twitter"></i></a>
                        <a href="#"><i class="fab fa-instagram"></i></a>
                    </div>
                </div>
                
                <div class="col-lg-4 mb-4">
                    <h5>UrbAlert</h5>
                    <p>Travailler ensemble pour créer une communauté plus propre, plus sûre et plus belle pour tous.</p>
                </div>
            </div>
            
            <hr class="bg-light">
            
            <div class="text-center pt-3">
                <p>&copy; 2025 UrbAlert. Tous droits réservés.</p>
            </div>
        </div>
    </footer>


</body>
</html>
