package com.mycompany.projectyml.web.rest;

import com.mycompany.projectyml.domain.TipoDocumento;
import com.mycompany.projectyml.repository.TipoDocumentoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TipoDocumentoResource{

    private final TipoDocumentoRepository tipoDocumentoRepository;

    public TipoDocumentoResource(TipoDocumentoRepository tipoDocumentoRepository) {
        this.tipoDocumentoRepository = tipoDocumentoRepository;

    }
    //POST /api/tipo-documentos
    @PostMapping("/tipo-documentos") //Endpoint para crear nuevo documento
    public String createTipoDocumento(@RequestBody TipoDocumento documentType) {
        System.out.println("se crea: "+documentType.toString());
        return "CREATE DOCUMENT TYPE";
    }

    @PutMapping("/tipo-documentos") //Endpoint para actualizar un documento existente
    public String updateTipoDocumento(@RequestBody TipoDocumento documentType) {
        System.out.println("se actualiza: "+documentType.toString());
        return "UPDATE DOCUMENT TYPE";
    }

    @GetMapping("/tipo-documentos")
    public ResponseEntity<List<TipoDocumento>>getTipoDocumentos(){
        List<TipoDocumento> tipoDocumentos = tipoDocumentoRepository.findAll();
        return ResponseEntity.ok().body(tipoDocumentos);
    }

    @GetMapping("/tipo-documentos/{id}") //Endpoint para obtener un documento por su ID
    public String getTipoDocumentoById(@PathVariable("id")Long id){
        return "GET DOCUMENT TYPE BY ID: "+id;
    }

    @DeleteMapping("/tipo-documentos/{id}") //Endpoint para eliminar un documento por su ID
    public String deleteTipoDocumento(@PathVariable("id")Long id){
        return "DELETE DOCUMENT TYPE BY ID: "+id;
    }
}
