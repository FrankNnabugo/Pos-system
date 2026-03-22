package com.pos.catalog.controller;

import com.pos.catalog.common.dto.ApiResponse;
import com.pos.catalog.common.dto.CatalogPictureResponse;
import com.pos.catalog.common.dto.CreateCatalogRequest;
import com.pos.catalog.common.dto.CreateCatalogResponse;
import com.pos.catalog.service.CatalogPictureService;
import com.pos.catalog.service.CatalogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/catalog")
@RequiredArgsConstructor
public class CatalogController {
    private final CatalogService catalogService;
    private final CatalogPictureService catalogPictureService;

    @GetMapping("/user")
    public String userOnly(){
        return "user";
    }

    @GetMapping("/admin")
    public String adminOnly(){
        return "admin";
    }


    @PostMapping("/user/create-catalog")
    public ApiResponse<CreateCatalogResponse> createCatalog(@RequestBody CreateCatalogRequest request){
        CreateCatalogResponse response = catalogService.createCatalog(request);
        return new ApiResponse<>(HttpStatus.OK.value(),HttpStatus.OK.name(), response);
    }


    @PutMapping("/{catalogId}")
    public ApiResponse<CreateCatalogResponse> updateCatalog(@RequestBody CreateCatalogRequest request,
                                               @PathVariable("id") String id){
        CreateCatalogResponse response = catalogService.updateCatalog(request, id);
        return new ApiResponse<>(HttpStatus.OK.value(),HttpStatus.OK.name(), response);
    }


    @DeleteMapping("/user/{catalogId}")
    public ApiResponse<String> deleteCatalog(@PathVariable("id") String id){
        String response = catalogService.deleteCatalog(id);
        return new ApiResponse<>(HttpStatus.OK.value(),HttpStatus.OK.name(), response);
    }


    @PostMapping("/user/{catalogId}/catalog-picture")
    public ApiResponse<String> uploadCatalogPicture(@PathVariable String catalogId,
                                                     @RequestBody String imageUrl){
         String response = catalogPictureService.uploadCatalogPicture(catalogId, imageUrl);
        return new ApiResponse<>(HttpStatus.OK.value(),HttpStatus.OK.name(), response);
    }


    @PutMapping("/user/{id}/catalog-picture")
    public ApiResponse<CatalogPictureResponse> updateCatalogPicture(
            @PathVariable("id") String id, @RequestBody String newImageUrl){
        CatalogPictureResponse response =  catalogPictureService.updateCatalogPicture(id, newImageUrl);
        return new ApiResponse<>(HttpStatus.OK.value(),HttpStatus.OK.name(), response);
    }


    @DeleteMapping("/user/{id}/catalog-picture")
    public ApiResponse<String> deleteCatalogPicture(@PathVariable("id") String id){
        String response = catalogPictureService.deleteCatalogPicture(id);
        return new ApiResponse<>(HttpStatus.OK.value(),HttpStatus.OK.name(), response);
    }


    @GetMapping("/{catalogId}/catalog-pictures")
    public ApiResponse<List<CatalogPictureResponse>> getCatalogPictures(
            @PathVariable("catalogId")String catalogId){
        List<CatalogPictureResponse> responses = catalogPictureService.getCatalogPictures(catalogId);
        return new ApiResponse<>(HttpStatus.OK.value(),HttpStatus.OK.name(), responses);
    }
}
