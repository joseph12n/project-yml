package com.mycompany.projectyml.repository;

import com.mycompany.projectyml.domain.Factura;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FacturaRepository extends MongoRepository<Factura, String> {
}
