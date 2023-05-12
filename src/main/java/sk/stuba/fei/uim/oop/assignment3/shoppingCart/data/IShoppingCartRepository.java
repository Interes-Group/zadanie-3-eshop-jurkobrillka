package sk.stuba.fei.uim.oop.assignment3.shoppingCart.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IShoppingCartRepository extends JpaRepository<ShoppingCart,Long> {
    ShoppingCart findShoppingCartById(Long id);

    @Override
    List<ShoppingCart> findAll();

}
