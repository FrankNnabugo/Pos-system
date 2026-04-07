package com.pos.catalog.repository;

import com.pos.catalog.entity.CatalogPicture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatalogPictureRepository extends JpaRepository<CatalogPicture, String> {
    List<CatalogPicture> findByCatalogId(String catalogId);
}
