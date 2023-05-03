package sk.stuba.fei.uim.oop.assignment3.shoppingCart.data;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.assignment3.itemCart.data.ItemCart;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(orphanRemoval = true)
    private List<ItemCart> shoppingList;

    private boolean paid;


    public ShoppingCart(){
        shoppingList = new ArrayList<>();
        paid = false;
    }
}
