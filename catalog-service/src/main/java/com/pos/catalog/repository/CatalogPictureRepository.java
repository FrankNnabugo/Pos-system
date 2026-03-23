package com.pos.catalog.repository;

import com.pos.catalog.entity.CatalogPicture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CatalogPictureRepository extends JpaRepository<CatalogPicture, String> {
    List<CatalogPicture> findByCatalogId(String catalogId);
}
