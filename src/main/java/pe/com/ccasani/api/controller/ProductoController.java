package pe.com.ccasani.api.controller;

import lombok.extern.apachecommons.CommonsLog;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pe.com.ccasani.api.controller.bean.ProductoRequest;
import pe.com.ccasani.api.controller.bean.ProductoResponse;
import pe.com.ccasani.api.dto.ProductoDto;
import pe.com.ccasani.api.exception.ServiceException;
import pe.com.ccasani.api.service.ProductoServiceInf;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController

@RequestMapping("/api/v1/productos")
public class ProductoController {
    private static final String ESTADO = "1";
    private static final String ERROR = "Id no existe";
    private static final String VERSION = "version";
    private static final String VALOR = "1.0.0";

    private ProductoServiceInf productoService;

    public ProductoController(ProductoServiceInf productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public ResponseEntity<ProductoResponse> getAllProduct() throws ServiceException {
        List<ProductoDto> productoDtos = this.productoService.list(null);
        if (productoDtos.isEmpty()) {
            return ResponseEntity.noContent().header("version", "1.0.0").build();
        }
        ProductoResponse response = ProductoResponse
                .builder()
                .productos(productoDtos)
                .total(productoDtos.stream()
                        .mapToDouble(p -> p.getPrecioTotal())
                        .sum()).build();
        return ResponseEntity.ok().header(VERSION, VALOR).body(response);
    }

    @PostMapping
    public ResponseEntity<ProductoDto> save(@RequestBody @Validated ProductoRequest request) throws ServiceException {
        ProductoDto productoDto = mapProducto(request);
        return new ResponseEntity<>(this.productoService.save(productoDto), HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProductoDto> buscarXid(@PathVariable Integer id) throws ServiceException {
        Optional<ProductoDto> productoDto = this.productoService.findById(id);
        if (!productoDto.isPresent()) {
            throw new ServiceException();
        }
        return ResponseEntity.ok().body(productoDto.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) throws ServiceException {
        Optional<ProductoDto> productoDto = this.productoService.findById(id);
        if (productoDto.isPresent()) {
            this.productoService.delete(id);
        } else {
            throw new ServiceException(ERROR);
        }
        return ResponseEntity.noContent().build();
    }

    private ProductoDto mapProducto(ProductoRequest request) {
        return ProductoDto.builder()
                .codigoProduco(String.valueOf(UUID.randomUUID().getMostSignificantBits()))
                .nombreProducto(request.getNombreProducto())
                .precioUnidad(request.getPrecioUnidad())
                .unidadTotal(request.getUnidadTotal())
                .precioTotal(request.getPrecioUnidad() * request.getUnidadTotal())
                .estado(ESTADO)
                .build();
    }


}
