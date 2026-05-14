package com.mycompany.projectyml.domain;

import com.mycompany.projectyml.domain.enumeration.Estado;
import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Document(collection = "tipo_documento") // Esta anotacion indica que clase va a ser un documento
public class TipoDocumento {

    @Id // Indica que este campo es el identificador unico del proyecto
    private String id;

    @Nonnull
    @Indexed(unique = true, name = "idx_unique_sigla") // índice único para 'sigla'
    @Field("sigla")
    @Size(max = 10)
    private String sigla;

    @Nonnull
    @Field("nombre_documento")
    @Size(max = 100)
    @Indexed(unique = true, name = "idx_unique_nombre_documento") // índice único para 'nombre_documento'
    private String nombreDocumento;

    @Nonnull
    @Field("estado")
    private Estado estado;

    @DBRef // indica que este campo es una referencia a otro documento en mongo
    @Field("clientes")
    private Set<Cliente> clientes = new HashSet<>();

    public TipoDocumento(String id, @Nonnull String sigla, @Nonnull String nombreDocumento, @Nonnull Estado estado) {
        this.id = id;
        this.sigla = sigla;
        this.nombreDocumento = nombreDocumento;
        this.estado = estado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Nonnull
    public String getSigla() {
        return sigla;
    }

    public void setSigla(@Nonnull String sigla) {
        this.sigla = sigla;
    }

    @Nonnull
    public String getNombreDocumento() {
        return nombreDocumento;
    }

    public void setNombreDocumento(@Nonnull String nombreDocumento) {
        this.nombreDocumento = nombreDocumento;
    }

    @Nonnull
    public Estado getEstado() {
        return estado;
    }

    public void setEstado(@Nonnull Estado estado) {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TipoDocumento that = (TipoDocumento) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
