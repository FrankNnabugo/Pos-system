package com.pos.catalog.service;

import com.pos.catalog.common.dto.CreateCatalogRequest;
import com.pos.catalog.common.dto.CreateCatalogResponse;
import com.pos.catalog.common.exceptions.ResourceNotFoundException;
import com.pos.catalog.entity.Catalog;
import com.pos.catalog.repository.CatalogRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CatalogService {
    private final CatalogRepository catalogRepository;
    private CatalogPictureService catalogPictureService;
    private final ModelMapper modelMapper;

    private Catalog toCatalog(CreateCatalogRequest request) {
        Catalog catalog = new Catalog();
        catalog.setName(request.getName());
        catalog.setDescription(request.getDescription());
        catalog.setPrice(request.getPrice());
        catalog.setCurrency(request.getCurrency());
        catalog.setAvailability(request.getAvailability());
        catalog.setCategory(request.getCategory());
        return catalog;
    }

    public CreateCatalogResponse createCatalog(CreateCatalogRequest request){
        Catalog savedCatalog = catalogRepository.save(toCatalog(request));

        if(request.getImageUrls() != null && !request.getImageUrls().isEmpty())
          catalogPictureService.uploadCatalogPictures(savedCatalog.getId(), request.getImageUrls());

        return modelMapper.map(savedCatalog, CreateCatalogResponse.class);

    }

    public CreateCatalogResponse updateCatalog(CreateCatalogRequest request, String id){
        catalogRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Catalog does not exist"));
        return modelMapper.map(catalogRepository.save(toCatalog(request)), CreateCatalogResponse.class);
    }


    public String deleteCatalog(String id){
        Catalog catalog = catalogRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Catalog does not exist"));
        catalogRepository.delete(catalog);
        return "Catalog deleted";
    }
}
