package metier;

import java.util.Date;

public class Employe {

	private Long idEmploye,idMunicipal;

	private String nom,
		prenom,
		nomUtilisateur,
		cin,
		lieuNaissance,
		telephone,
		email,
		emailAuth,
		motDePasse;
	

	//ADMIN_PRIVILEGE
	private boolean adminPriv;
	
	public Employe () {
		
	}

	public Employe(Long idMunicipal, String nom, String prenom, String cin, String lieuNaissance, String telephone,
			String email, String motDePasse, Date dateNaissance, Date dateCreation) {
		super();
		this.idMunicipal = idMunicipal;
		this.nom = nom;
		this.prenom = prenom;
		this.cin = cin;
		this.lieuNaissance = lieuNaissance;
		this.telephone = telephone;
		this.email = email;
		this.motDePasse = motDePasse;
		this.nomUtilisateur = nom + "." + prenom;
		this.emailAuth = cin + "@municipal.ma";
		this.dateNaissance = dateNaissance;
		this.dateCreation = dateCreation;
	}
	
	private Date dateNaissance,
		dateCreation;

	public Long getIdEmploye() {
		return idEmploye;
	}

	public void setIdEmploye(Long idEmploye) {
		this.idEmploye = idEmploye;
	}

	public Long getIdMunicipal() {
		return idMunicipal;
	}

	public void setIdMunicipal(Long idMunicipal) {
		this.idMunicipal = idMunicipal;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	public String getLieuNaissance() {
		return lieuNaissance;
	}

	public void setLieuNaissance(String lieuNaissance) {
		this.lieuNaissance = lieuNaissance;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public String getNomUtilisateur() {
		return nomUtilisateur;
	}

	public void setNomUtilisateur(String nomUtilisateur) {
		this.nomUtilisateur = nomUtilisateur;
	}

	public String getEmailAuth() {
		return emailAuth;
	}

	public void setEmailAuth(String emailAuth) {
		this.emailAuth = emailAuth;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public boolean isAdminPriv() {
		return adminPriv;
	}

	public void setAdminPriv(boolean adminPriv) {
		this.adminPriv = adminPriv;
	}
	
	
	
}
//CREATE TABLE IF NOT EXISTS EMPLOYE (
//	    ID_EMPLOYE BIGINT PRIMARY KEY AUTO_INCREMENT,
//	    NOM VARCHAR(255) NOT NULL,
//	    PRENOM VARCHAR(255) NOT NULL,
//	    CIN VARCHAR(20) NOT NULL,
//	    LIEU_NAISSANCE VARCHAR(255) NOT NULL,
//	    TELEPHONE VARCHAR(10) NOT NULL,
//	    EMAIL VARCHAR(255) NOT NULL,
//	    DATE_NAISSANCE DATE NOT NULL,
//	    DATE_CREATION TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
//	    ID_MUNICIPAL BIGINT,  -- ajout de la colonne pour la clé étrangère
//	    CONSTRAINT fk_employe_municipal
//	        FOREIGN KEY (ID_MUNICIPAL)
//	        REFERENCES MUNICIPAL(ID_MUNICIPAL)
//	        ON DELETE CASCADE
//	        ON UPDATE CASCADE
//	);
