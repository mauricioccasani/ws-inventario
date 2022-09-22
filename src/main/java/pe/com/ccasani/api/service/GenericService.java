package pe.com.ccasani.api.service;





import pe.com.ccasani.api.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface GenericService<T> {
    List<T> list(T t) throws ServiceException;



    T save(T t) throws ServiceException;
}
