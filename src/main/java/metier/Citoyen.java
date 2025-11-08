package metier;

import java.util.Date;

public class Citoyen {

	private Long idCitoyen,
		idRegion; 
	
	private String nom,
		prenom,
		cin,
		lieuNaissance,
		telephone,
		email,
		motDePasse;
	
	private Date dateNaissance,
		dateCreation;

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
