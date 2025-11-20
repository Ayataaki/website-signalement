<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Signaler un problème - UrbAlert</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <link rel="stylesheet" href="https://unpkg.com/leaflet@1.9.4/dist/leaflet.css" />
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
        .form-container {
            max-width: 800px;
            margin: 0 auto;
            padding: 2rem 1rem;
        }

        .form-card {
            background: white;
            border-radius: 12px;
            box-shadow: 0 2px 8px rgba(0,0,0,0.1);
            padding: 2.5rem;
            transition: box-shadow 0.3s ease;
        }

        .form-card:hover {
            box-shadow: 0 4px 16px rgba(0,0,0,0.15);
        }

        .form-title {
            font-size: 2rem;
            font-weight: 700;
            color: var(--text-dark);
            margin-bottom: 0.5rem;
        }

        .form-subtitle {
            color: var(--text-gray);
            margin-bottom: 2rem;
        } 
        .form-label {
            font-weight: 600;
            color: var(--text-dark);
            margin-bottom: 0.5rem;
        }

        .form-control, .form-select {
            border: 2px solid #e0e0e0;
            border-radius: 8px;
            padding: 0.75rem;
            transition: all 0.3s ease;
        }

        .form-control:focus, .form-select:focus {
            border-color: var(--primary-color);
            box-shadow: 0 0 0 0.2rem rgba(25, 118, 210, 0.1);
            outline: none;
        } 
        .image-upload-area {
            border: 2px dashed #e0e0e0;
            border-radius: 12px;
            padding: 2rem;
            text-align: center;
            transition: all 0.3s ease;
            cursor: pointer;
            background-color: #fafafa;
        }

        .image-upload-area:hover {
            border-color: var(--primary-color);
            background-color: rgba(25, 118, 210, 0.02);
        }

        .image-upload-area.drag-over {
            border-color: var(--primary-color);
            background-color: rgba(25, 118, 210, 0.05);
        }

        .upload-icon {
            font-size: 3rem;
            color: var(--primary-color);
            margin-bottom: 1rem;
        }

        .image-preview {
            max-width: 100%;
            max-height: 300px;
            border-radius: 8px;
            margin-top: 1rem;
            object-fit: cover;
        } 
        .input-with-icon {
            position: relative;
        }

        .input-with-icon i {
            position: absolute;
            left: 12px;
            top: 50%;
            transform: translateY(-50%);
            color: var(--text-gray);
            pointer-events: none;
        }

        .input-with-icon .form-control {
            padding-left: 40px;
        } 
        #map {
            height: 400px;
            border-radius: 8px;
            margin-top: 1rem;
            border: 2px solid #e0e0e0;
            z-index: 1;
        }

        .leaflet-container {
            border-radius: 8px;
        } 
        .btn-submit {
            background-color: var(--secondary-color);
            color: white;
            padding: 0.875rem 2rem;
            border: none;
            border-radius: 50px;
            font-weight: 600;
            font-size: 1rem;
            transition: all 0.3s ease;
            box-shadow: 0 4px 6px rgba(0,0,0,0.1);
        }

        .btn-submit:hover {
            background-color: #388E3C;
            transform: translateY(-2px);
            box-shadow: 0 6px 12px rgba(0,0,0,0.15);
            color: white;
        }

        .btn-cancel {
            background-color: white;
            color: var(--text-gray);
            padding: 0.875rem 2rem;
            border: 2px solid #e0e0e0;
            border-radius: 50px;
            font-weight: 600;
            font-size: 1rem;
            transition: all 0.3s ease;
        }

        .btn-cancel:hover {
            border-color: var(--text-gray);
            color: var(--text-dark);
        } 
        .char-counter {
            font-size: 0.875rem;
            color: var(--text-gray);
            text-align: right;
            margin-top: 0.25rem;
        } 
        .loading-text {
            color: var(--primary-color);
            font-size: 0.875rem;
        } 
        .file-name {
            color: var(--secondary-color);
            font-size: 0.875rem;
            margin-top: 0.5rem;
            display: flex;
            align-items: center;
            justify-content: center;
            gap: 0.5rem;
        }

        @media (max-width: 768px) {
            .form-card {
                padding: 1.5rem;
            }

            .form-title {
                font-size: 1.5rem;
            }

            #map {
                height: 300px;
            }

            .logo span {
                font-size: 0.875rem;
            }
        }
    </style>
</head>
<body>
 
    <header class="header">
        <div class="container">
            <a href="${pageContext.request.contextPath}/Accueil.jsp" class="logo">
                <i class="fas fa-building"></i>
                <span>UrbAlert – Signalez, Suivez, Améliorez Votre Ville</span>
            </a>
        </div>
    </header>
 
    <div class="form-container">
        <div class="form-card">
            <h1 class="form-title">Signaler un problème</h1>
            <p class="form-subtitle">Aidez-nous à améliorer votre ville en signalant les problèmes que vous rencontrez</p>

            <form action="${pageContext.request.contextPath}/SignalementServlet" method="post" enctype="multipart/form-data" id="reportForm">
                
                <input type="hidden" name="idCitoyen" value="${sessionScope.user.idCitoyen}">
                <input type="hidden" name="action" value="create">
                 
                <div class="mb-4">
                    <label for="designation" class="form-label">
                        Objet de la réclamation <span class="text-danger">*</span>
                    </label>
                    <input 
                        type="text" 
                        class="form-control" 
                        id="designation" 
                        name="designation" 
                        placeholder="Mettez un titre à votre signalement"
                        required>
                </div> 
                <div class="mb-4">
                    <label for="description" class="form-label">
                        Description du problème <span class="text-danger">*</span>
                    </label>
                    <textarea 
                        class="form-control" 
                        id="description" 
                        name="description" 
                        rows="4" 
                        placeholder="Décrivez le problème en détail..."
                        maxlength="500"
                        required></textarea>
                    <div class="char-counter">
                        <span id="charCount">0</span>/500 caractères
                    </div>
                </div>
                 
                <div class="mb-4">
                    <label for="adresse" class="form-label">
                        Adresse où se trouve le problème <span class="text-danger">*</span>
                    </label>
                    <div class="input-with-icon">
                        <i class="fas fa-map-marker-alt"></i>
                        <input 
                            type="text" 
                            class="form-control" 
                            id="adresse" 
                            name="adresse" 
                            placeholder="Entrez l'adresse où se trouve le problème"
                            required>
                    </div>
                </div> 
                <div class="mb-4">
                    <label class="form-label">
                        Photo du problème <span class="text-danger">*</span>
                    </label>
                    <div class="image-upload-area" id="uploadArea">
                        <i class="fas fa-cloud-upload-alt upload-icon"></i>
                        <p class="mb-2"><strong>Cliquez pour télécharger</strong> ou glissez-déposez une image</p>
                        <p class="text-muted small">PNG, JPG jusqu'à 5MB</p>
                        <input 
                            type="file" 
                            class="d-none" 
                            id="imageInput" 
                            name="image" 
                            accept="image/jpeg,image/jpg,image/png"
                            required>
                    </div>
                    <div id="fileName" class="file-name d-none">
                        <i class="fas fa-check-circle"></i>
                        <span id="fileNameText"></span>
                    </div>
                    <img id="imagePreview" class="image-preview d-none" alt="Aperçu">
                </div> 
                <div class="mb-4">
                    <label for="commentaire" class="form-label">Commentaire additionnel</label>
                    <textarea 
                        class="form-control" 
                        id="commentaire" 
                        name="commentaire" 
                        rows="3" 
                        placeholder="Ajoutez des informations supplémentaires (optionnel)..."></textarea>
                </div> 
                <div class="d-flex gap-3 justify-content-end">
                    <button type="button" class="btn btn-cancel" onclick="window.history.back()">
                        <i class="fas fa-times me-2"></i>Annuler
                    </button>
                    <button type="submit" class="btn btn-submit">
                        <i class="fas fa-paper-plane me-2"></i>Soumettre le signalement
                    </button>
                </div>
            </form>
        </div>
    </div>
 
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
     
    <script> 
    const descriptionTextarea = document.getElementById('description');
    const charCount = document.getElementById('charCount');

    descriptionTextarea.addEventListener('input', function() {
        const length = this.value.length;
        charCount.textContent = length;
        charCount.style.color = (length > 450) ? '#d32f2f' : 'var(--text-gray)';
    });
 
    const uploadArea = document.getElementById('uploadArea');
    const imageInput = document.getElementById('imageInput');
    const imagePreview = document.getElementById('imagePreview');
    const fileName = document.getElementById('fileName');
    const fileNameText = document.getElementById('fileNameText');

    uploadArea.addEventListener('click', () => imageInput.click());
    imageInput.addEventListener('change', () => handleFile(imageInput.files[0]));

    uploadArea.addEventListener('dragover', e => {
        e.preventDefault();
        uploadArea.classList.add('drag-over');
    });

    uploadArea.addEventListener('dragleave', () => {
        uploadArea.classList.remove('drag-over');
    });

    uploadArea.addEventListener('drop', e => {
        e.preventDefault();
        uploadArea.classList.remove('drag-over');

        const files = e.dataTransfer.files;
        if (files.length > 0) {
            imageInput.files = files;
            handleFile(files[0]);
        }
    });

    function handleFile(file) {
        if (!file) return;

        const validTypes = ['image/jpeg', 'image/jpg', 'image/png'];
        if (!validTypes.includes(file.type)) {
            alert('Veuillez sélectionner une image JPG ou PNG');
            return;
        }

        if (file.size > 5 * 1024 * 1024) {
            alert('L\'image ne doit pas dépasser 5MB');
            return;
        }

        fileNameText.textContent = file.name;
        fileName.classList.remove('d-none');

        const reader = new FileReader();
        reader.onload = e => {
            imagePreview.src = e.target.result;
            imagePreview.classList.remove('d-none');
            uploadArea.style.display = 'none';
        };
        reader.readAsDataURL(file);
    }
 
    const form = document.getElementById('reportForm');
    const submitBtn = form.querySelector('.btn-submit');

    form.addEventListener('submit', function (e) {
        const designation = document.getElementById('designation').value.trim();
        const description = document.getElementById('description').value.trim();
        const adresse = document.getElementById('adresse').value.trim();

        if (!designation || !description || !adresse || !imageInput.files[0]) {
            e.preventDefault();
            alert("Veuillez remplir tous les champs obligatoires.");
            return;
        }
 
        submitBtn.disabled = true;
        submitBtn.innerHTML =
            '<i class="fas fa-spinner fa-spin me-2"></i> Envoi en cours...';

    });
</script>

</body>
</html>