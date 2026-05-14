package com.mycompany.projectyml.repository;

import com.mycompany.projectyml.domain.TipoDocumento;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TipoDocumentoRepository extends MongoRepository<TipoDocumento, String> {
    Optional<TipoDocumento> findBySigla(String sigla);
}
