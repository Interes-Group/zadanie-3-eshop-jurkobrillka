package sk.stuba.fei.uim.oop.assignment3.itemCart.data;


import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//TODO opytat sa na cviku: Moze byt tak v inom packagi? je pooode IItemCartRepo nazov dva Icka za sebou?
public interface IItemCartRepository extends JpaRepository<ItemCart,Long> {

    ItemCart findItemCartById(Long id);
}
