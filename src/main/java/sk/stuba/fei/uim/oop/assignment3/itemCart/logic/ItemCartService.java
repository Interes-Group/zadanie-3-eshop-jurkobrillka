package sk.stuba.fei.uim.oop.assignment3.itemCart.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.itemCart.data.IItemCartRepository;
import sk.stuba.fei.uim.oop.assignment3.itemCart.data.ItemCart;

@Service
public class ItemCartService implements IItemCartService{

    @Autowired
    IItemCartRepository itemCartRepository;

    @Override
    public ItemCart createItemCart() {
        return itemCartRepository.save(new ItemCart());
    }



    //TODO pouzi SAVE
    @Override
    public ItemCart saveItemCart(ItemCart itemCart) {
        return itemCartRepository.save(itemCart);
    }
}
