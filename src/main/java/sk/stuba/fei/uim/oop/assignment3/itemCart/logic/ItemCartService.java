package sk.stuba.fei.uim.oop.assignment3.itemCart.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.itemCart.data.IItemCartRepository;
import sk.stuba.fei.uim.oop.assignment3.itemCart.data.ItemCart;

@Service
public class ItemCartService implements IItemCartService{

    @Autowired
    IItemCartRepository itemCartRepository;

    @Override
    public ItemCart createItemCart() {
        ItemCart itemCart = itemCartRepository.save(new ItemCart());
        return itemCart;
    }

    @Override
    public ItemCart getItemCartById(long id) throws NotFoundException {
        ItemCart itemCart = itemCartRepository.findItemCartById(id);
        if(itemCart!=null){
            return itemCart;
        }
        else {
            throw new NotFoundException();
        }
    }


    @Override
    public ItemCart saveItemCart(ItemCart itemCart) {
        ItemCart retItemCart = itemCartRepository.save(itemCart);
        return retItemCart;
    }
}
