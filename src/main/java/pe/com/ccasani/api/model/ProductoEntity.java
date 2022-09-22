package pe.com.ccasani.api.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data

@Table(name = "PRODUCTOS")
@Entity
public class ProductoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private int id;
    private String codigoProduco;
    @Column(unique = true)
    private String nombreProducto;
    private Integer unidadTotal;
    private Double precioUnidad;
    private Double precioTotal;
    private String estado;
}
