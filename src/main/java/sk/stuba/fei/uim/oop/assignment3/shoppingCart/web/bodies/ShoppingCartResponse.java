package sk.stuba.fei.uim.oop.assignment3.shoppingCart.web.bodies;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.assignment3.itemCart.data.ItemCart;
import sk.stuba.fei.uim.oop.assignment3.itemCart.web.bodies.ItemCartAddRequest;
import sk.stuba.fei.uim.oop.assignment3.shoppingCart.data.ShoppingCart;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ShoppingCartResponse {

    private long id;

    private List<ItemCartAddRequest> shoppingList;

    private boolean payed;

    public ShoppingCartResponse(ShoppingCart shoppingCart) {
        this.id = shoppingCart.getId();
        //this.shoppingList = shoppingCart.getShoppingList().stream().map(ItemCartAddRequest::new).collect(Collectors.toList());
        this.shoppingList = shoppingCart.getShoppingList().stream().map(ItemCartAddRequest::new).collect(Collectors.toList());
        this.payed = shoppingCart.isPaid();
    }


}
