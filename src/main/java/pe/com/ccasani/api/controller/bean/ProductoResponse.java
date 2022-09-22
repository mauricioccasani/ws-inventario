package pe.com.ccasani.api.controller.bean;

import lombok.Builder;
import lombok.Data;
import pe.com.ccasani.api.dto.ProductoDto;

import java.util.List;

@Data
@Builder
public class ProductoResponse {

    private List<ProductoDto>  productos;
    private Double total;
}
