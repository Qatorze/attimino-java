package com.qatorze.attimino.models;

import com.qatorze.attimino.enums.ImageType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "images")
public class Image {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "url", length = 1200, nullable = false)
	private String url;
	
	@Column(name = "is_main", nullable = false)
	private boolean isMain;
	
	// ID du model associé (ad es. ID di City, Teatro o Spettacolo)
	@Column(name = "entity_id", nullable = false)
	private String entityId;
	
	// Type du model associé (CITY, TEATRO, SPETTACOLO)
	@Enumerated(EnumType.STRING)
	@Column(name = "entity_type", nullable = false)
	private ImageType entityType;

	public Image() {
		super();
	}

	public Image(String url, boolean isMain, String entityId, ImageType entityType) {
		super();
		this.url = url;
		this.isMain = isMain;
		this.entityId = entityId;
		this.entityType = entityType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isMain() {
		return isMain;
	}

	public void setMain(boolean isMain) {
		this.isMain = isMain;
	}

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	public ImageType getEntityType() {
		return entityType;
	}

	public void setEntityType(ImageType entityType) {
		this.entityType = entityType;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Image [id=");
		builder.append(id);
		builder.append(", url=");
		builder.append(url);
		builder.append(", isMain=");
		builder.append(isMain);
		builder.append(", entityId=");
		builder.append(entityId);
		builder.append(", entityType=");
		builder.append(entityType);
		builder.append("]");
		return builder.toString();
	}

}
