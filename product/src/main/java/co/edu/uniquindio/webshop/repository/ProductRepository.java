package co.edu.uniquindio.webshop.repository;

import co.edu.uniquindio.webshop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByIdAndIsActive(Long id, Boolean isActive);
    List<Product> findAllByIsActive(Boolean isActive);
}
