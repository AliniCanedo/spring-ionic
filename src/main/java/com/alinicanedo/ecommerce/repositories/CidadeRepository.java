package com.alinicanedo.ecommerce.repositories;

import org.springframework.stereotype.Repository;

import com.alinicanedo.ecommerce.domain.Cidade;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer> {

}
