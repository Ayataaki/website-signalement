package metier;

import java.util.Date;

public class Citoyen {

	private Long idCitoyen,
		idRegion; 
	
	private String nom,
		prenom,
		nomUtilisateur,
		cin,
		lieuNaissance,
		telephone,
		email,
		emailAuth,
		motDePasse;
	
	private Date dateNaissance,
		dateCreation;
	
	public Citoyen(){}
	
	public Citoyen(Long idRegion, String nom, String prenom, String cin, String lieuNaissance, String telephone,
			String email, String motDePasse, Date dateNaissance, Date dateCreation) {
		super();
		this.idRegion = idRegion;
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

	public Long getIdCitoyen() {
		return idCitoyen;
	}

	public void setIdCitoyen(Long idCitoyen) {
		this.idCitoyen = idCitoyen;
	}

	public Long getIdRegion() {
		return idRegion;
	}

	public void setIdRegion(Long idRegion) {
		this.idRegion = idRegion;
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

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
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
	
	
}


//CREATE TABLE IF NOT EXISTS CITOYEN (
//	    ID_CITOYEN BIGINT PRIMARY KEY AUTO_INCREMENT,
//	    NOM VARCHAR(255) NOT NULL,
//	    PRENOM VARCHAR(255) NOT NULL,
//	    CIN VARCHAR(15) NOT NULL,
//	    LIEU_NAISSANCE VARCHAR(255) NOT NULL,
//	    TELEPHONE VARCHAR(15) NOT NULL,
//	    EMAIL VARCHAR(255) NOT NULL,
//	    DATE_NAISSANCE DATE NOT NULL,
//	    MOT_DE_PASSE VARCHAR(255) NOT NULL,
//	    ID_REGION BIGINT,
//	    DATE_CREATION TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
//	    FOREIGN KEY (ID_REGION) REFERENCES REGION(ID_REGION)
//	        ON DELETE CASCADE
//	        ON UPDATE CASCADE
//	);
