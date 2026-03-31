package com.pos.catalog.service;

import com.pos.catalog.common.dto.CreateCatalogRequest;
import com.pos.catalog.common.dto.CreateCatalogResponse;
import com.pos.catalog.common.exceptions.ResourceNotFoundException;
import com.pos.catalog.entity.Catalog;
import com.pos.catalog.event.CatalogPublisher;
import com.pos.catalog.repository.CatalogRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CatalogService {
    private final CatalogRepository catalogRepository;
    private CatalogPictureService catalogPictureService;
    private final ModelMapper modelMapper;
    private final CatalogPublisher catalogPublisher;

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

    @Transactional
    public CreateCatalogResponse createCatalog(CreateCatalogRequest request){
        Catalog savedCatalog = catalogRepository.save(toCatalog(request));

        if(request.getImageUrls() != null && !request.getImageUrls().isEmpty())
            catalogPictureService.uploadCatalogPictures(savedCatalog.getId(), request.getImageUrls());
        catalogPublisher.publishProductCreatedEvent(savedCatalog.getId(), request);

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
