package com.mycompany.projectyml.repository;

import com.mycompany.projectyml.domain.Cliente;
import com.mycompany.projectyml.domain.TipoDocumento;
import com.mycompany.projectyml.domain.enumeration.Estado;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.mongodb.test.autoconfigure.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataMongoTest
class ClienteRepositoryTest {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private TipoDocumentoRepository tipoDocumentoRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    void Insert() {
        mongoTemplate.dropCollection(Cliente.class);
        tipoDocumentoRepository.deleteAll();

        TipoDocumento tipoDocumentoCedula = tipoDocumentoRepository.insert(new TipoDocumento(
                null, "CC", "Cedula de Ciudadania", Estado.ACTIVO)
        );

        Cliente cliente = new Cliente(null, "1025142222", "juan", "arturo", "lopez", "gonzales");
        Cliente cliente2 = new Cliente(null, "1025142245", "jonh", "luis", "lopez", "gonzales");

        assertNotNull(tipoDocumentoCedula);
        cliente.setTipoDocumento(tipoDocumentoCedula);
        cliente2.setTipoDocumento(tipoDocumentoCedula);

        Cliente clienteGuardado = clienteRepository.insert(cliente);
        Cliente clienteGuardado2 = clienteRepository.insert(cliente2);

        assertNotNull(clienteGuardado.getId());
        assertNotNull(clienteGuardado2.getId());

        Cliente clienteRecuperado = clienteRepository.findById(clienteGuardado.getId()).orElseThrow();
        Cliente clienteRecuperado2 = clienteRepository.findById(clienteGuardado2.getId()).orElseThrow();

        assertNotNull(clienteRecuperado.getTipoDocumento());
        assertNotNull(clienteRecuperado2.getTipoDocumento());
        assertEquals("CC", clienteRecuperado.getTipoDocumento().getSigla());
        assertEquals("CC", clienteRecuperado2.getTipoDocumento().getSigla());
        assertEquals(2, clienteRepository.count());

    }
}