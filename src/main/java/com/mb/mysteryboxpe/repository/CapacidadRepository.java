package com.mb.mysteryboxpe.repository;

import com.mb.mysteryboxpe.model.Capacidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CapacidadRepository extends JpaRepository<Capacidad, Long> {
}
