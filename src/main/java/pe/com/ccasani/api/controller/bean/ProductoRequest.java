package pe.com.ccasani.api.controller.bean;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class ProductoRequest {
    @NotBlank
    private String nombreProducto;
    @NotNull
    private Integer unidadTotal;
    @NotNull
    private Double precioUnidad;
}
