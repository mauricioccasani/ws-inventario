package pe.com.ccasani.api.service;

import com.fasterxml.jackson.databind.ObjectMapper;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.ccasani.api.dto.ProductoDto;
import pe.com.ccasani.api.exception.ServiceException;
import pe.com.ccasani.api.model.ProductoEntity;
import pe.com.ccasani.api.repository.ProductoRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class ProductoServiceImpl implements ProductoServiceInf{

    private ProductoRepository productoRepository;
    @Autowired
    private ObjectMapper objectMapper;

    public ProductoServiceImpl( ProductoRepository productoRepository) {
        this.productoRepository=productoRepository;
    }

    @Override
    public List<ProductoDto> list(ProductoDto productoDto) throws ServiceException {
       try {
        return this.productoRepository.findAll()
                .stream()
                .map(p->this.getProductoDto(p)).collect(Collectors.toList());
       }catch (Exception e){
           throw new ServiceException(e.toString());
       }

    }

    @Override
    public Optional<ProductoDto> findById(Integer id) throws ServiceException {
       Optional<ProductoEntity>  productoEntity= this.productoRepository.findById(id);
        if (!productoEntity.isPresent()){
            throw new ServiceException();
        }
        return Optional.of(this.getProductoDto(productoEntity.get()));
    }

    @Override
    public void delete(Integer id) throws ServiceException {
        this.productoRepository.deleteById(id);

    }

    @Override
    public ProductoDto save(ProductoDto productoDto) throws ServiceException {

        ProductoEntity productoEntity= this.getProductoEntity(productoDto);

        return this.getProductoDto(this.productoRepository.save(productoEntity));
    }
    private ProductoEntity getProductoEntity(ProductoDto productoDto) {
        return objectMapper.convertValue(productoDto, ProductoEntity.class);
    }

    private ProductoDto getProductoDto(ProductoEntity productoEntity) {
        return objectMapper.convertValue(productoEntity, ProductoDto.class);
    }
}
