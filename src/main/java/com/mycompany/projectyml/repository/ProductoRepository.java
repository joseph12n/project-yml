package com.mycompany.projectyml.repository;

import com.mycompany.projectyml.domain.Producto;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductoRepository extends MongoRepository<Producto, String> {
}
