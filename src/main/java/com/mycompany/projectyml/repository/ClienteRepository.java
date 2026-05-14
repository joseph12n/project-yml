package com.mycompany.projectyml.repository;

import com.mycompany.projectyml.domain.Cliente;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClienteRepository extends MongoRepository<Cliente, String> {
}
