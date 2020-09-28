package com.alinicanedo.ecommerce.repositories;

import org.springframework.stereotype.Repository;

import com.alinicanedo.ecommerce.domain.Categoria;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

}
