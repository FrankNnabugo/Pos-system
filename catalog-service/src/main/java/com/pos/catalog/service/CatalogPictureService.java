package com.pos.catalog.service;

import com.pos.catalog.common.dto.CatalogPictureResponse;
import com.pos.catalog.common.exceptions.ResourceNotFoundException;
import com.pos.catalog.entity.CatalogPicture;
import com.pos.catalog.repository.CatalogPictureRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CatalogPictureService {
    private final CatalogPictureRepository catalogPictureRepository;
    private final ModelMapper modelMapper;

    public void uploadCatalogPictures(String catalogId, List<String> imageUrl){
        List<CatalogPicture> catalogPictures = new ArrayList<>();
        imageUrl.forEach(url->{
            CatalogPicture pictures = new CatalogPicture();
            pictures.setImageUrls(url);
            pictures.setCatalogId(catalogId);
            catalogPictures.add(pictures);
        });
        catalogPictureRepository.saveAll(catalogPictures);

    }

    public String uploadCatalogPicture(String catalogId, String imageUrl){
        CatalogPicture picture = new CatalogPicture();
        picture.setImageUrls(imageUrl);
        picture.setCatalogId(catalogId);
        catalogPictureRepository.save(picture);

                return "Picture uploaded";
    }

    public CatalogPictureResponse updateCatalogPicture(String id, String newImageUrl){
        CatalogPicture picture = catalogPictureRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("picture not found"));

        picture.setImageUrls(newImageUrl);
        return modelMapper.map(catalogPictureRepository.save(picture), CatalogPictureResponse.class);
    }

    public String deleteCatalogPicture(String id){
        CatalogPicture picture = catalogPictureRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("picture not found"));
        catalogPictureRepository.delete(picture);
        return "picture deleted";
    }

    public List<CatalogPictureResponse> getCatalogPictures(String catalogId){
        List<CatalogPicture> picture = catalogPictureRepository.findByCatalogId(catalogId);
        if(picture.isEmpty()) throw new ResourceNotFoundException("pictures with catalogId" + catalogId + "not found");
        return picture
                .stream()
                .map(urls -> modelMapper.map(urls, CatalogPictureResponse.class))
                .collect(Collectors.toList());
    }
}
