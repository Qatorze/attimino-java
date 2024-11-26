package com.qatorze.attimino.models;

import com.qatorze.attimino.enums.ImageType;
import com.qatorze.attimino.repositories.ImageRepository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import java.util.List;

@Entity
@Table(name = "cities")
public class City {

	@Id
	@Column(name = "id", length = 4, nullable = false)
	private String id;
	
	@Column(name = "name", length = 20, nullable = false)
	private String name;
	
	@Column(name = "description", length = 255, nullable = false)
	private String description;
	
	
	public City() {
		super();
	}

	public City(String id) {
		super();
		this.id = id;
	}

	public City(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/*Function pour obtenir les immages associées à une ville en particulier. L'annotation @Trasient
	 * sur la methode getImages indique à JPA de ne pas persister
	 * ce champ dans le database, car il s'agit simplement d'une methode d'accès dynamique
	 * et non pas un attribut physique de la classe*/
    @Transient
    public List<Image> getImages(ImageRepository imageRepository) {
        return imageRepository.findByEntityIdAndEntityType(this.id, ImageType.CITY);
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("City [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", description=");
		builder.append(description);
		builder.append("]");
		return builder.toString();
	}
    
}
