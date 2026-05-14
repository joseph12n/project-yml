package com.mycompany.projectyml.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serial;
import java.io.Serializable;

@Document(collection = "clientes")
public class Cliente implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Nonnull
    @Size(max=50)
    @Field("numero_documento")
    private String numeroDocumento;

    @Nonnull
    @Size(max=50)
    @Field("primer_nombre")
    private String primerNombre;

    @Size(max=50)
    @Field ("segundo_nombre")
    private String segundoNombre;

    @Nonnull
    @Size(max=50)
    @Field("primer_apellido")
    private String primerApellido;

    @Size(max=50)
    @Field("segundo_apellido")
    private String segundoApellido;

    @DBRef
    @JsonIgnoreProperties(value = { "clientes" }, allowSetters = true)
    @Field ("tipo_documento")
    private TipoDocumento tipoDocumento;

    @DocumentReference
    @Field("cuenta")
    private Cuenta cuenta;

    public Cliente(String id, @Nonnull String numeroDocumento, @Nonnull String primerNombre, String segundoNombre, @Nonnull String primerApellido, String segundoApellido) {
        this.id = id;
        this.numeroDocumento = numeroDocumento;
        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Nonnull
    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(@Nonnull String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    @Nonnull
    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(@Nonnull String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    @Nonnull
    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(@Nonnull String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }
}
