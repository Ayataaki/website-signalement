package metier;

import java.util.Date;

public class Signalement {
	
	private Long idSignalement,
		idCitoyen;

	private String description,
		localisation,
		imagePath,
		commentaire,
		designation;

	private Statut statut;
	
	private Date dateCreation;

	
	
	public Signalement(Long idCitoyen, Statut statut,String designation, String description, String localisation, 
			String commentaire, String imagePath) {
		super();
		this.idCitoyen = idCitoyen;
		this.description = description;
		this.localisation = localisation;
		this.imagePath = imagePath;
		this.commentaire = commentaire;
		this.designation = designation;
		this.statut = statut;
	}

	public Signalement() {
		// TODO Auto-generated constructor stub
	}

	public Signalement(Long idCitoyen, Statut fromLabel, String designation, String description,
			String localisation, String commentaire) {
		super();
		this.idCitoyen = idCitoyen;
		this.description = description;
		this.localisation = localisation;
		this.commentaire = commentaire;
		this.designation = designation;
		this.statut = fromLabel;
	}

	public Long getIdSignalement() {
		return idSignalement;
	}

	public void setIdSignalement(Long idSignalement) {
		this.idSignalement = idSignalement;
	}

	public Long getIdCitoyen() {
		return idCitoyen;
	}

	public void setIdCitoyen(Long idCitoyen) {
		this.idCitoyen = idCitoyen;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocalisation() {
		return localisation;
	}

	public void setLocalisation(String localisation) {
		this.localisation = localisation;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public Statut getStatut() {
		return statut;
	}

	public void setStatut(Statut statut) {
		this.statut = statut;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	
	
}

//
//CREATE TABLE IF NOT EXISTS SIGNALEMENT (
//	    ID_SIGNALEMENT BIGINT PRIMARY KEY AUTO_INCREMENT,
//	    DESCRIPTION VARCHAR(255) NOT NULL,
//	    LOCALISATION VARCHAR(255) NOT NULL,
//	    DATE_CREATION TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
//	    IMAGE_PATH VARCHAR(255) NOT NULL,
//	    STATUT ENUM('new', 'processing', 'final') NOT NULL,
//	    COMMENTAIRE VARCHAR(255),
//	    ID_CITOYEN BIGINT,
//	    FOREIGN KEY (ID_CITOYEN) REFERENCES CITOYEN(ID_CITOYEN)
//	        ON DELETE CASCADE
//	        ON UPDATE CASCADE
//	);
