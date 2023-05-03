package sk.stuba.fei.uim.oop.assignment3.itemCart.logic;
import sk.stuba.fei.uim.oop.assignment3.itemCart.data.ItemCart;

public interface IItemCartService {

    ItemCart createItemCart();

    //TODO POUYI SAVE
    ItemCart saveItemCart(ItemCart itemCart);

}
