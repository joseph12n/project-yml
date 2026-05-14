package com.mycompany.projectyml.repository;

import com.mycompany.projectyml.domain.Cuenta;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CuentaRepository extends MongoRepository<Cuenta, String> {
}
