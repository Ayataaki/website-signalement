package metier;

import java.util.Date;

public class Employe {

	private Long idEmploye,idMunicipal;

	private String nom,
		prenom,
		cin,
		lieuNaissance,
		telephone,
		email;

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
