package pe.com.ccasani.api.service;

import pe.com.ccasani.api.dto.ProductoDto;
import pe.com.ccasani.api.exception.ServiceException;

import java.util.Optional;

public interface ProductoServiceInf extends GenericService<ProductoDto>{
    Optional<ProductoDto> findById(Integer id) throws ServiceException;

    void delete(Integer id)throws ServiceException;
}
