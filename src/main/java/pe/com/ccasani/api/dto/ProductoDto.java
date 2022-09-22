package pe.com.ccasani.api.dto;



import lombok.Builder;
import lombok.Data;





@Data
@Builder
public class ProductoDto {
    private int id;
    private String codigoProduco;
    private String nombreProducto;
    private Integer unidadTotal;
    private Double precioUnidad;
    private Double precioTotal;
    private String estado;
}
