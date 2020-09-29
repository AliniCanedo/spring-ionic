package com.alinicanedo.ecommerce.repositories;

import org.springframework.stereotype.Repository;

import com.alinicanedo.ecommerce.domain.Pedido;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

}
