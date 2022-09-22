package pe.com.ccasani.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.ccasani.api.model.ProductoEntity;

public interface ProductoRepository extends JpaRepository<ProductoEntity,Integer> {
}
