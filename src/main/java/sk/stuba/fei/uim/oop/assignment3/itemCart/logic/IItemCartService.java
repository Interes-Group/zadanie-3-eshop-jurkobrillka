package sk.stuba.fei.uim.oop.assignment3.itemCart.logic;

import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.itemCart.data.ItemCart;

public interface IItemCartService {

    ItemCart createItemCart();

    ItemCart getItemCartById(long id)throws NotFoundException;

    ItemCart saveItemCart(ItemCart itemCart);

}
