package com.pos.catalog.repository;

import com.pos.catalog.entity.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatalogRepository extends JpaRepository<Catalog, String> {
}
