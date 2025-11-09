<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Rendez votre ville meilleure</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">     <!-- rel="stylesheet" : précise que ce fichier est une feuille de style CSS.-->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <link rel="stylesheet" type="text/css" href="webapp/css/style.css">

</head>
<body>

<!-- Navigation -->
    <nav class="navbar navbar-expand-lg navbar-dark" style="background-color: var(--primary-color);">
         <div class="container">
            <a class="navbar-brand fw-bold" href="#">
                <i class="fas fa-city me-2"></i>Rendez votre ville meilleure
            </a>
        </div>
    </nav>

     
     <!-- Hero Section -->
    <section class="hero-section">
        <div class="container">
            <h1 class="hero-title">Rendez votre ville meilleure</h1>
            <p class="hero-subtitle">
                Signalez instantanément les problèmes urbains et suivez leur résolution. Ensemble, nous pouvons améliorer notre communauté.</p>
            <button class="btn btn-report">
                <i class="fas fa-bullhorn me-2"></i>Signaler un problème
            </button>
        </div>
    </section>


    <!-- How It Works Section -->
    <section class="how-it-works">
        <div class="container">
            <h2 class="section-title">Comment ça marche</h2>
            <div class="row">
                <div class="col-md-4">
                    <div class="step-card">
                        <div class="step-number">1</div>
                        <h4>Signaler un problème</h4>
                        <p>Prenez une photo et décrivez le problème dans votre région.</p>
                    </div>
                </div>
                
                <div class="col-md-4">
                    <div class="step-card">
                        <div class="step-number">2</div>
                        <h4>Suivre les progrès</h4>
                        <p> Surveillez l'état de votre rapport en temps réel.</p>
                    </div>
                </div>
                
                <div class="col-md-4">
                    <div class="step-card">
                        <div class="step-number">3</div>
                        <h4>Voir les résultats</h4>
                        <p>Recevez les résultats lorsque le problème est résolu.</p>
                    </div>
                </div>
            </div>
        </div>
    </section>

  <!-- Contact Section -->
    <section class="contact-section">
        <div class="container">
            <h2 class="section-title"> Contactez-nous </h2>
            
            <div class="row">
                <div class="col-md-4">
                    <div class="contact-info">
                        <div class="contact-icon">
                            <i class="fas fa-phone"></i>
                        </div>
                        <h5>Téléphone</h5>
                        <p>+1234 567 8900</p>
                    </div>
                </div>
                
                <div class="col-md-4">
                    <div class="contact-info">
                        <div class="contact-icon">
                            <i class="fas fa-envelope"></i>
                        </div>
                        <h5>Email</h5>
                        <p>contact@muncipal.ma</p>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="contact-info">
                        <div class="contact-icon">
                            <i class="fas fa-map-marker-alt"></i>
                        </div>
                        <h5>Address</h5>
                        <p>123 City Hall, Metro City</p>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <!-- Footer -->
    <footer class="footer">
        <div class="container">
            <div class="row">
                <div class="col-lg-4 mb-4">
                    <h5>Liens rapides</h5>
                    <ul class="list-unstyled">
                        <li><a href="#" class="text-light text-decoration-none">Politique de confidentialité</a></li>
                        <li><a href="#" class="text-light text-decoration-none">Conditions d'utilisation</a></li>
                        <li><a href="#" class="text-light text-decoration-none">FAQ</a></li>
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
                    <h5>Rendez votre ville meilleure</h5>
                    <p>Travailler ensemble pour créer une communauté plus propre, plus sûre et plus belle pour tous.</p>
                </div>
            </div>
            
            <hr class="bg-light">
            
            <div class="text-center pt-3">
                <p>&copy; 2025 Rendez votre ville meilleure. Tous droits réservés.</p>
            </div>
        </div>
    </footer>






