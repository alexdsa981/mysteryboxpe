package com.mb.mysteryboxpe.repository;

import com.mb.mysteryboxpe.model.Plantilla;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlantillaRepository extends JpaRepository<Plantilla, Long> {
}
