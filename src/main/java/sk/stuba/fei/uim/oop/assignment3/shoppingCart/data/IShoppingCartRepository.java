package sk.stuba.fei.uim.oop.assignment3.shoppingCart.data;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IShoppingCartRepository extends JpaRepository<ShoppingCart,Long> {
    ShoppingCart findShoppingCartById(Long id);

    @Override
    List<ShoppingCart> findAll();

}
