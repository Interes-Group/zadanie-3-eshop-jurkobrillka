package sk.stuba.fei.uim.oop.assignment3.itemCart.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IItemCartRepository extends JpaRepository<ItemCart, Long> {

    ItemCart findItemCartById(Long id);
}
