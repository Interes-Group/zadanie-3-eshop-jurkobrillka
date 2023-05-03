package sk.stuba.fei.uim.oop.assignment3.shoppingCart.logic;

import sk.stuba.fei.uim.oop.assignment3.exception.IllegalOperationException;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.itemCart.data.ItemCart;
import sk.stuba.fei.uim.oop.assignment3.itemCart.web.bodies.ItemCartAddRequest;
import sk.stuba.fei.uim.oop.assignment3.shoppingCart.data.ShoppingCart;

import java.util.List;

public interface IShoppingCartService {

    ShoppingCart createShoppingCart();

    ShoppingCart getShoppingCartById(Long id) throws NotFoundException;

    void deleteShoppingCartById(Long id) throws NotFoundException;

    ShoppingCart addProductToSHoppingCartById(Long id, ItemCartAddRequest itemCart) throws NotFoundException, IllegalOperationException;

    boolean isSHoppingCartPayed(ShoppingCart shoppingCart);

    double payForCart(Long id) throws NotFoundException, IllegalOperationException;


}
