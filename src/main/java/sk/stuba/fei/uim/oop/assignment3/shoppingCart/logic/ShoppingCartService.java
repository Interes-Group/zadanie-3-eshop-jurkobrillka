package sk.stuba.fei.uim.oop.assignment3.shoppingCart.logic;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.exception.IllegalOperationException;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.itemCart.data.ItemCart;
import sk.stuba.fei.uim.oop.assignment3.product.data.IProductRepository;
import sk.stuba.fei.uim.oop.assignment3.product.data.Product;
import sk.stuba.fei.uim.oop.assignment3.product.logic.IProductService;
import sk.stuba.fei.uim.oop.assignment3.product.logic.ProductService;
import sk.stuba.fei.uim.oop.assignment3.shoppingCart.data.IShoppingCartRepository;
import sk.stuba.fei.uim.oop.assignment3.shoppingCart.data.ShoppingCart;

import java.util.List;

@Service
public class ShoppingCartService implements IShoppingCartService{

    @Autowired
    private IShoppingCartRepository shoppingCartRepository;

    @Autowired
    private IProductService productService;

    @Override
    public ShoppingCart createShoppingCart() {
        return shoppingCartRepository.save(new ShoppingCart());
    }

    @Override
    public ShoppingCart getShoppingCartById(Long id) throws NotFoundException {
        ShoppingCart retShoppingCCart = shoppingCartRepository.findShoppingCartById(id);
        if (retShoppingCCart!=null){
            return retShoppingCCart;
        }
        else {
            throw new NotFoundException();
        }
    }

    @Override
    public void deleteShoppingCartById(Long id) throws NotFoundException {
        if (id!=null){
            //shoppingCartRepository.deleteById(id);
            //TODO TU BUDE CHYBA ASI
            shoppingCartRepository.delete(getShoppingCartById(id));
        }
    }


    //TODO toto riesis!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    @Override
    public ShoppingCart addProductToSHoppingCartById(Long id, ItemCart itemCart) throws NotFoundException, IllegalOperationException {
        ShoppingCart retShoppingCart = shoppingCartRepository.findShoppingCartById(id);
        Product product = itemCart.getProduct();
        if (retShoppingCart!=null){
            //kosikExistuje
            if (isSHoppingCartPayed(retShoppingCart)){
                //kosik je zaplateny - vyhod 400
                throw new IllegalOperationException();
            }
            else {
                if (productService.isSufficientAmount(id,itemCart.getAmount())){
                    //na sklade je dost produktov
                    //zistujem ci shopping cart obsahuje dany produkt,
                    if (product!=null){
                        addProductToList(id, itemCart, retShoppingCart.getShoppingList());
                    }else {
                        throw new NotFoundException();
                    }
                }
                else {
                    //na sklade neni dost produktov
                    throw new IllegalOperationException();
                }
            }
        }
        else {
            //kosikNeexistuje
            throw new NotFoundException();
        }
        return null;
    }

    @Override
    public int payForShoppingCart(Long id) throws NotFoundException, IllegalOperationException {
        ShoppingCart shoppingCart = getShoppingCartById(id);
        if (isSHoppingCartPayed(shoppingCart)){
            throw new IllegalOperationException();
        }
        else {
            //vrati cenu kosika :)
            //TODO returnPriceOfShoppingCart();
            shoppingCart.setPaid(true);
        }
        return 0;
    }

    @Override
    public boolean isSHoppingCartPayed(ShoppingCart shoppingCart) {
        if (shoppingCart.isPaid()){
            return true;
        }
        else return false;

    }

    @Override
    public void addProductToList(Long id, ItemCart itemCart, List<ItemCart> shoppingList) {
        boolean containsProd = false;
        int indexProduct = 0;
        for (ItemCart itemCartLoop: shoppingList){
            if (itemCartLoop.getProduct().equals(itemCart.getProduct())){
                containsProd = true;
                break;
            }
            indexProduct++;
        }

        int decrementedProductAmount = 0;
        if (containsProd){
             decrementedProductAmount = shoppingList.get(indexProduct).getProduct().getAmount()-itemCart.getAmount();
            shoppingList.get(indexProduct).setAmount(itemCart.getAmount());
            shoppingList.get(indexProduct).getProduct().setAmount(decrementedProductAmount);
            //kosik obsahuje produkt
            //nastavim sa na id kde je ten itemCart rovny produktu v liste shoppingList
            //dam ++ jaky je amount itemCart
        }
        else {
            decrementedProductAmount = shoppingList.get(indexProduct).getProduct().getAmount()-itemCart.getAmount();
            shoppingList.add(itemCart);
            itemCart.getProduct().setAmount(decrementedProductAmount);
            //TODO TU MOZNO CHYBY

            //kosik neobsahuje produkt,
            //pridam do listu cely itemCart a fuck off

        }
    }
}
