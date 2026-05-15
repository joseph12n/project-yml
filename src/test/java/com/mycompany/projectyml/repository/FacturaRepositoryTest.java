package com.mycompany.projectyml.repository;

import com.mycompany.projectyml.domain.Cliente;
import com.mycompany.projectyml.domain.Cuenta;
import com.mycompany.projectyml.domain.Factura;
import com.mycompany.projectyml.domain.TipoDocumento;
import com.mycompany.projectyml.domain.enumeration.Estado;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.mongodb.test.autoconfigure.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
class FacturaRepositoryTest {

    @Autowired
    private FacturaRepository facturaRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private TipoDocumentoRepository tipoDocumentoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Test
    void insert() {
        tipoDocumentoRepository.deleteAll();
        clienteRepository.deleteAll();
        facturaRepository.deleteAll();
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

        Factura factura01 = new Factura(null,1L, 2026, new Date(), 23800.0,3800,20000);
        Factura factura02 = new Factura(null,2L, 2026, new Date(), 23800.0,3800,20000);
        Factura factura03 = new Factura(null,3L, 2026, new Date(), 23800.0,3800,20000);
        Factura factura04 = new Factura(null,4L, 2026, new Date(), 23800.0,3800,20000);

        facturaRepository.insert(factura01);
        facturaRepository.insert(factura02);
        facturaRepository.insert(factura03);
        facturaRepository.insert(factura04);

        clienteGuardado.getFacturas().add(factura01);
        clienteGuardado.getFacturas().add(factura02);
        clienteGuardado.getFacturas().add(factura03);
        clienteGuardado.getFacturas().add(factura04);

        clienteRepository.save(clienteGuardado);

    }

}