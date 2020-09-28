package com.alinicanedo.ecommerce.repositories;

import org.springframework.stereotype.Repository;

import com.alinicanedo.ecommerce.domain.Produto;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}
