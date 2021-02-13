package com.svirtual.api.controllers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.svirtual.api.classes.Producto;

@Repository
interface ProductoRepository extends JpaRepository<Producto, Long> {

}