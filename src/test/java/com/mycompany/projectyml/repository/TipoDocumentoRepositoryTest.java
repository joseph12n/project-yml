package com.mycompany.projectyml.repository;

import com.mycompany.projectyml.domain.TipoDocumento;
import com.mycompany.projectyml.domain.enumeration.Estado;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.mongodb.test.autoconfigure.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
class TipoDocumentoRepositoryTest {

    @Autowired
    private TipoDocumentoRepository tipoDocumentoRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    void Insert(){
        tipoDocumentoRepository.deleteAll();
        TipoDocumento tipoDocumento = new TipoDocumento(null, "CC", "Cedula de Ciudadania", Estado.ACTIVO);
        tipoDocumentoRepository.save(tipoDocumento);
    }
}