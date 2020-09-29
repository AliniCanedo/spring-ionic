package com.alinicanedo.ecommerce.repositories;

import org.springframework.stereotype.Repository;

import com.alinicanedo.ecommerce.domain.ItemPedido;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer> {

}
