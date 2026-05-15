package com.mycompany.projectyml.repository;

import com.mycompany.projectyml.domain.Cliente;
import com.mycompany.projectyml.domain.Factura;
import com.mycompany.projectyml.domain.Producto;
import com.mycompany.projectyml.domain.ProductoEmbedded;
import com.mycompany.projectyml.domain.TipoDocumento;
import com.mycompany.projectyml.domain.enumeration.Estado;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.mongodb.test.autoconfigure.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
class ProductoRepositoryTest {

    @Autowired
    private FacturaRepository facturaRepository;

    @Autowired
    private TipoDocumentoRepository tipoDocumentoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Test
    void insert() {
        tipoDocumentoRepository.deleteAll();
        clienteRepository.deleteAll();
        facturaRepository.deleteAll();
        productoRepository.deleteAll();

        TipoDocumento tipoDocumentoCedula = tipoDocumentoRepository.insert(new TipoDocumento(
                null, "CC", "Cedula de Ciudadania", Estado.ACTIVO)
        );

        Cliente cliente = new Cliente(null, "1025142222", "juan", "arturo", "lopez", "gonzales");
        Cliente cliente2 = new Cliente(null, "1025142245", "jonh", "luis", "lopez", "gonzales");

        assertNotNull(tipoDocumentoCedula);

        cliente.setTipoDocumento(tipoDocumentoCedula);
        cliente2.setTipoDocumento(tipoDocumentoCedula);

        Cliente clienteGuardado = clienteRepository.insert(cliente);
        clienteRepository.insert(cliente2);


        Factura factura01 = new Factura(null,1L, 2026, new Date(), 23800.0,3800,20000);
        Factura factura02 = new Factura(null,2L, 2026, new Date(), 23800.0,3800,20000);
        Factura factura03 = new Factura(null,3L, 2026, new Date(), 23800.0,3800,20000);
        Factura factura04 = new Factura(null,4L, 2026, new Date(), 23800.0,3800,20000);

        Producto producto01 = new Producto(null, "tuleperaconlapapaya","minion",3000,1,"http://minion.com/minion.jpg","minion con suenio");
        Producto producto02 = new Producto(null, "pepe","unpepe",3000,1,"http://pepe.com/pepe.jpg","un pepillo");
        Producto producto03 = new Producto(null, "nosexd","nose._.XD",3000,1,"http://alfajor.com/alfajor.jpg","jisjasjus");

        productoRepository.insert(producto01);
        productoRepository.insert(producto02);
        productoRepository.insert(producto03);

        factura01.getProductos().add(new ProductoEmbedded(producto01.getSerial(), producto01.getNombreProducto(), producto01.getPrecio(), 10));
        factura01.getProductos().add(new ProductoEmbedded(producto02.getSerial(), producto02.getNombreProducto(), producto02.getPrecio(), 50));
        factura01.getProductos().add(new ProductoEmbedded(producto03.getSerial(), producto03.getNombreProducto(), producto03.getPrecio(), 30));
        factura01.getProductos().add(new ProductoEmbedded(producto03.getSerial(), producto03.getNombreProducto(), producto03.getPrecio(), 30));

        producto01.setCantidadStock(producto01.getCantidadStock() - 10);
        producto02.setCantidadStock(producto02.getCantidadStock() - 50);
        producto03.setCantidadStock(producto03.getCantidadStock() - 30);

        productoRepository.save(producto01);
        productoRepository.save(producto02);
        productoRepository.save(producto03);

        facturaRepository.insert(factura01);
        facturaRepository.insert(factura02);
        facturaRepository.insert(factura03);
        facturaRepository.insert(factura04);


        clienteGuardado.getFacturaSet().add(factura01);
        clienteGuardado.getFacturaSet().add(factura02);
        clienteGuardado.getFacturaSet().add(factura03);
        clienteGuardado.getFacturaSet().add(factura04);



        clienteRepository.save(clienteGuardado);

    }
}