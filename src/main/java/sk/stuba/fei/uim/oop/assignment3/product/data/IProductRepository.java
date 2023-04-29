package sk.stuba.fei.uim.oop.assignment3.product.data;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IProductRepository extends JpaRepository<Product,Long> {

    @Override
    Optional<Product> findById(Long aLong);

    List<Product> findAll();

    Product findProductById(Long id);
}
