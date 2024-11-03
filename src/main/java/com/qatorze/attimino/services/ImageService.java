package com.qatorze.attimino.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qatorze.attimino.enums.ImageType;
import com.qatorze.attimino.models.Image;
import com.qatorze.attimino.repositories.ImageRepository;

@Service
public class ImageService {
	
	@Autowired
    private ImageRepository imageRepository;

    // Methode pour récupérer toutes les images associées à une Ville
    public List<Image> getImagesForCity(String cityId) {
        return imageRepository.findByEntityIdAndEntityType(cityId, ImageType.CITY);
    }

    // Methode pour récupérer toutes les images associées à un Théatre
    public List<Image> getImagesForTeatro(String teatroId) {
        return imageRepository.findByEntityIdAndEntityType(teatroId, ImageType.THEATER);
    }

    // Methode pour récupérer toutes les images associées à un Show
    public List<Image> getImagesForSpettacolo(String spettacoloId) {
        return imageRepository.findByEntityIdAndEntityType(spettacoloId, ImageType.SHOW);
    }
}
