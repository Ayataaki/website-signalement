<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>UrbAlert – Rendez votre ville meilleure</title>
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

        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, sans-serif;
            color: var(--text-dark);
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
            cursor: pointer;
            text-decoration: none;
            color: var(--primary-color);
            font-weight: 600;
            font-size: 1.125rem;
        }

        .logo i {
            font-size: 1.75rem;
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
            color: white;
        }
 
        .hero-section {
            position: relative;
            height: 500px;
            display: flex;
            align-items: center;
            justify-content: center;
            color: white;
            overflow: hidden;
        }

        .hero-bg {
            position: absolute;
            inset: 0;
            width: 100%;
            height: 100%;
            object-fit: cover;
        }

        .hero-overlay {
            position: absolute;
            inset: 0;
            background: rgba(0, 0, 0, 0.5);
        }

        .hero-content {
            position: relative;
            z-index: 10;
            text-align: center;
            padding: 0 1.5rem;
        }

        .hero-title {
            font-size: 3rem;
            font-weight: 700;
            margin-bottom: 1.5rem;
            text-shadow: 2px 2px 4px rgba(0,0,0,0.3);
        }

        .hero-subtitle {
            font-size: 1.25rem;
            max-width: 700px;
            margin: 0 auto 2rem;
            line-height: 1.6;
        }

        .btn-report {
            background-color: var(--secondary-color);
            color: white;
            padding: 1rem 2rem;
            font-size: 1.125rem;
            border: none;
            border-radius: 50px;
            font-weight: 600;
            transition: all 0.3s ease;
            box-shadow: 0 4px 6px rgba(0,0,0,0.1);
            cursor: pointer;
        }

        .btn-report:hover {
            background-color: var(--secondary-hover);
            transform: translateY(-2px);
            box-shadow: 0 6px 12px rgba(0,0,0,0.15);
        }
 
        .features-section {
            padding: 4rem 0;
            background-color: #f8f9fa;
        }

        .section-title {
            text-align: center;
            font-size: 2.5rem;
            font-weight: 700;
            margin-bottom: 1rem;
            color: var(--text-dark);
        }

        .section-subtitle {
            text-align: center;
            font-size: 1.125rem;
            color: var(--text-gray);
            margin-bottom: 3rem;
        }

        .feature-card {
            background: white;
            padding: 2rem;
            border-radius: 12px;
            text-align: center;
            transition: all 0.3s ease;
            border: 1px solid #e0e0e0;
            height: 100%;
            cursor: pointer;
        }

        .feature-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 10px 25px rgba(0,0,0,0.1);
        }

        .feature-icon {
            width: 64px;
            height: 64px;
            background-color: rgba(25, 118, 210, 0.1);
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            margin: 0 auto 1.5rem;
            transition: all 0.3s ease;
        }

        .feature-card:hover .feature-icon {
            background-color: rgba(25, 118, 210, 0.2);
        }

        .feature-icon i {
            font-size: 2rem;
            color: var(--primary-color);
        }

        .feature-card h4 {
            font-size: 1.25rem;
            font-weight: 600;
            margin-bottom: 0.75rem;
            color: var(--text-dark);
        }

        .feature-card p {
            color: var(--text-gray);
            font-size: 0.875rem;
            line-height: 1.5;
            margin: 0;
        }
 
        .how-it-works {
            padding: 4rem 0;
            background-color: white;
        }

        .steps-content {
            padding-right: 2rem;
        }

        .step-item {
            display: flex;
            gap: 1.5rem;
            margin-bottom: 2rem;
        }

        .step-number {
            width: 40px;
            height: 40px;
            background-color: var(--primary-color);
            color: white;
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            font-weight: 700;
            flex-shrink: 0;
        }

        .step-item:last-child .step-number {
            background-color: var(--secondary-color);
        }

        .step-item h4 {
            font-size: 1.125rem;
            font-weight: 600;
            margin-bottom: 0.5rem;
        }

        .step-item p {
            color: var(--text-gray);
            margin: 0;
        }

        .how-it-works-image {
            position: relative;
            height: 400px;
            border-radius: 12px;
            overflow: hidden;
            box-shadow: 0 10px 30px rgba(0,0,0,0.1);
        }

        .how-it-works-image img {
            width: 100%;
            height: 100%;
            object-fit: cover;
        }
 
        .contact-section {
            padding: 4rem 0;
            background-color: #f8f9fa;
        }

        .contact-info {
            text-align: center;
            padding: 2rem;
            background: white;
            border-radius: 12px;
            transition: all 0.3s ease;
            height: 100%;
        }

        .contact-info:hover {
            transform: translateY(-5px);
            box-shadow: 0 10px 25px rgba(0,0,0,0.1);
        }

        .contact-icon {
            width: 60px;
            height: 60px;
            background-color: rgba(25, 118, 210, 0.1);
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            margin: 0 auto 1rem;
        }

        .contact-icon i {
            font-size: 1.5rem;
            color: var(--primary-color);
        }

        .contact-info h5 {
            font-weight: 600;
            margin-bottom: 0.5rem;
            color: var(--text-dark);
        }

        .contact-info p {
            color: var(--text-gray);
            margin: 0;
        }
 
        .footer {
            background-color: var(--text-dark);
            color: white;
            padding: 3rem 0 1rem;
        }

        .footer h5 {
            font-weight: 600;
            margin-bottom: 1rem;
        }

        .footer ul li {
            margin-bottom: 0.5rem;
        }

        .footer a {
            color: rgba(255,255,255,0.8);
            transition: color 0.3s;
            text-decoration: none;
        }

        .footer a:hover {
            color: white;
        }

        .social-icons a {
            display: inline-block;
            width: 40px;
            height: 40px;
            background-color: rgba(255,255,255,0.1);
            border-radius: 50%;
            text-align: center;
            line-height: 40px;
            margin-right: 0.5rem;
            transition: all 0.3s;
        }

        .social-icons a:hover {
            background-color: var(--primary-color);
            transform: translateY(-3px);
        }
 
        .mobile-menu-toggle {
            display: none;
            background: none;
            border: none;
            font-size: 1.5rem;
            color: var(--text-dark);
            cursor: pointer;
        }

        @media (max-width: 768px) {
            .nav-menu {
                display: none;
            }

            .mobile-menu-toggle {
                display: block;
            }

            .hero-title {
                font-size: 2rem;
            }
            
            .hero-subtitle {
                font-size: 1rem;
            }

            .steps-content {
                padding-right: 0;
                margin-bottom: 2rem;
            }

            .section-title {
                font-size: 1.875rem;
            }
        }
    </style>
</head>
<body>
 
    <header class="header">
        <div class="header-container">
            <a href="#" class="logo">
                <i class="fas fa-building"></i>
                <span>UrbAlert – Signalez, Suivez, Améliorez Votre Ville</span>
            </a>
            <nav class="nav-menu">
                
                
				<a href="${pageContext.request.contextPath}/RegisterServlet" class="btn-primary">Inscription</a>
                <a href="${pageContext.request.contextPath}/views/Auth/Apropos.jsp" class="nav-link">À propos</a>
                <a href="${pageContext.request.contextPath}/views/Auth/Connexion.jsp" class="btn-outline">Connexion</a> 
            
            </nav>
            <button class="mobile-menu-toggle">
                <i class="fas fa-bars"></i>
            </button>
        </div>
    </header>
 
    <section class="hero-section">
        <img src="https://www.bladi.net/img/logo/ville-rabat-plus-chere-maroc.jpg" 
             alt="Ville de Rabat" 
             class="hero-bg">
        <div class="hero-overlay"></div>
        <div class="hero-content">
            <h1 class="hero-title">Rendez votre ville meilleure</h1>
            <p class="hero-subtitle">
                Signalez instantanément les problèmes urbains et suivez leur résolution. 
                Ensemble, nous pouvons améliorer notre communauté.
            </p>
            <button class="btn-report" onclick="window.location.href='Connexion.jsp' ">
                Signaler un problème
            </button>
        </div>
    </section>
 
    <section class="features-section">
        <div class="container">
            <h2 class="section-title">Signaler un problème en quelques clics</h2>
            <p class="section-subtitle">Sélectionnez le type de problème que vous souhaitez signaler</p>
            
            <div class="row g-4">
                <div class="col-md-6 col-lg-3">
                    <div class="feature-card">
                        <div class="feature-icon">
                            <i class="fas fa-trash"></i>
                        </div>
                        <h4>Déchets</h4>
                        <p>Signaler les poubelles débordantes, les décharges illégales et les problèmes de gestion des déchets</p>
                    </div>
                </div>
                
                <div class="col-md-6 col-lg-3">
                    <div class="feature-card">
                        <div class="feature-icon">
                            <i class="fas fa-lightbulb"></i>
                        </div>
                        <h4>Éclairage</h4>
                        <p>Signaler les lampadaires cassés, les zones sombres et les problèmes d'éclairage</p>
                    </div>
                </div>
                
                <div class="col-md-6 col-lg-3">
                    <div class="feature-card">
                        <div class="feature-icon">
                            <i class="fas fa-road"></i>
                        </div>
                        <h4>Routes</h4>
                        <p>Signaler les nids-de-poule, les routes endommagées et les problèmes d'infrastructure</p>
                    </div>
                </div>
                
                <div class="col-md-6 col-lg-3">
                    <div class="feature-card">
                        <div class="feature-icon">
                            <i class="fas fa-tree"></i>
                        </div>
                        <h4>Espaces verts</h4>
                        <p>Signaler l'entretien des parcs, les problèmes d'arbres et les préoccupations concernant les espaces verts</p>
                    </div>
                </div>
            </div>
        </div>
    </section>
 
    <section class="how-it-works">
        <div class="container">
            <div class="row align-items-center">
                <div class="col-lg-6">
                    <div class="steps-content">
                        <h2 class="section-title text-start">Comment ça marche</h2>
                        
                        <div class="step-item">
                            <div class="step-number">1</div>
                            <div>
                                <h4>Signaler le problème</h4>
                                <p>Prenez une photo et décrivez le problème dans votre région</p>
                            </div>
                        </div>
                        
                        <div class="step-item">
                            <div class="step-number">2</div>
                            <div>
                                <h4>Suivre les progrès</h4>
                                <p>Surveillez l'état de votre rapport en temps réel</p>
                            </div>
                        </div>
                        
                        <div class="step-item">
                            <div class="step-number">3</div>
                            <div>
                                <h4>Voir les résultats</h4>
                                <p>Recevez une notification lorsque le problème est résolu</p>
                            </div>
                        </div>
                    </div>
                </div>
                
                <div class="col-lg-6">
                    <div class="how-it-works-image">
                        <img src="https://sofiontour.com/wp-content/uploads/2021/08/20210827163615_8.jpg" 
                             alt="Infrastructure urbaine">
                    </div>
                </div>
            </div>
        </div>
    </section> 
    
    <section class="contact-section">
        <div class="container">
            <h2 class="section-title">Contactez-nous</h2>
            
            <div class="row g-4">
                <div class="col-md-4">
                    <div class="contact-info">
                        <div class="contact-icon">
                            <i class="fas fa-phone"></i>
                        </div>
                        <h5>Téléphone</h5>
                        <p>+212 5XX XX XX XX</p>
                    </div>
                </div>
                
                <div class="col-md-4">
                    <div class="contact-info">
                        <div class="contact-icon">
                            <i class="fas fa-envelope"></i>
                        </div>
                        <h5>Email</h5>
                        <p>contact@urbalert.ma</p>
                    </div>
                </div>
                
                <div class="col-md-4">
                    <div class="contact-info">
                        <div class="contact-icon">
                            <i class="fas fa-map-marker-alt"></i>
                        </div>
                        <h5>Adresse</h5>
                        <p>Rabat, Maroc</p>
                    </div>
                </div>
            </div>
        </div>
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

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>