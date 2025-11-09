-- =========================
-- Ajout des champs d'authentification et d'administration
-- =========================

-- Ajout du champ d'administration pour les employés
ALTER TABLE EMPLOYE 
    ADD COLUMN ADMIN_PRIVILEGE BOOLEAN DEFAULT FALSE;

-- Ajout des champs d'authentification pour EMPLOYE
ALTER TABLE EMPLOYE
    ADD COLUMN NOM_UTILISATEUR VARCHAR(255) AFTER PRENOM,
    ADD COLUMN EMAIL_AUTH VARCHAR(255) UNIQUE AFTER EMAIL,
    ADD COLUMN MOT_DE_PASSE VARCHAR(255) NOT NULL AFTER EMAIL_AUTH;

-- Ajout des champs d'authentification pour CITOYEN
ALTER TABLE CITOYEN
    ADD COLUMN NOM_UTILISATEUR VARCHAR(255) AFTER PRENOM,
    ADD COLUMN EMAIL_AUTH VARCHAR(255) UNIQUE AFTER EMAIL;

-- Ajout des champs d'authentification pour TECHNICIEN
ALTER TABLE TECHNICIEN
    ADD COLUMN NOM_UTILISATEUR VARCHAR(255) AFTER PRENOM,
    ADD COLUMN EMAIL_AUTH VARCHAR(255) UNIQUE AFTER EMAIL,
    ADD COLUMN MOT_DE_PASSE VARCHAR(255) NOT NULL AFTER EMAIL_AUTH;

-- =========================
-- Fin de migration
-- =========================
