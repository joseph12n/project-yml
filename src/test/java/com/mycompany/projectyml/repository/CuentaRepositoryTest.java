package com.mycompany.projectyml.repository;

import com.mycompany.projectyml.domain.Cliente;
import com.mycompany.projectyml.domain.Cuenta;
import com.mycompany.projectyml.domain.TipoDocumento;
import com.mycompany.projectyml.domain.enumeration.Estado;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.mongodb.test.autoconfigure.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
class CuentaRepositoryTest {

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private TipoDocumentoRepository tipoDocumentoRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    void Insert() {
        mongoTemplate.dropCollection(Cliente.class);
        mongoTemplate.dropCollection(Cuenta.class);
        tipoDocumentoRepository.deleteAll();

        TipoDocumento tipoDocumentoCedula = tipoDocumentoRepository.insert(new TipoDocumento(
                null, "CC", "Cedula de Ciudadania", Estado.ACTIVO)
        );

        assertNotNull(tipoDocumentoCedula);

        Cliente cliente = new Cliente(null, "1025142222", "juan", "arturo", "lopez", "gonzales");
        Cliente cliente2 = new Cliente(null, "1025142245", "jonh", "luis", "lopez", "gonzales");

        cliente.setTipoDocumento(tipoDocumentoCedula);
        cliente2.setTipoDocumento(tipoDocumentoCedula);

        Cliente clienteGuardado = clienteRepository.insert(cliente);
        Cliente clienteGuardado2 = clienteRepository.insert(cliente2);

        Cuenta cuenta01 = new Cuenta(null,"Cuenta facebook", Estado.ACTIVO);
        Cuenta cuenta02 = new Cuenta(null,"Cuenta twitter", Estado.ACTIVO);

        cuenta01.setCliente(clienteGuardado);
        cuenta02.setCliente(clienteGuardado2);

        cuentaRepository.insert(cuenta01);
        cuentaRepository.insert(cuenta02);
    }
}