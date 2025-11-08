package metier;

import java.util.Date;

public class Municipal {
	
	private Long idMunicipal,
		idRegion;

	private String nom,
		typeMunicipal;

	private Date dateCreation;

	public Long getIdMunicipal() {
		return idMunicipal;
	}

	public void setIdMunicipal(Long idMunicipal) {
		this.idMunicipal = idMunicipal;
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

	public String getTypeMunicipal() {
		return typeMunicipal;
	}

	public void setTypeMunicipal(String typeMunicipal) {
		this.typeMunicipal = typeMunicipal;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	
}

//
//CREATE TABLE IF NOT EXISTS MUNICIPAL (
//	    ID_MUNICIPAL BIGINT PRIMARY KEY AUTO_INCREMENT,
//	    NOM VARCHAR(255) NOT NULL,
//	    TYPE_MUNICIPAL VARCHAR(255) NOT NULL,
//	    ID_REGION BIGINT,
//	    DATE_CREATION TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
//	    FOREIGN KEY (ID_REGION) REFERENCES REGION(ID_REGION)
//	        ON DELETE CASCADE
//	        ON UPDATE CASCADE
//	);
