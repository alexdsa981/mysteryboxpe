package com.mb.mysteryboxpe.repository;

import com.mb.mysteryboxpe.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
