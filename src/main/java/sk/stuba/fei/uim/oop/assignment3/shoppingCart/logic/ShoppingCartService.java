package sk.stuba.fei.uim.oop.assignment3.shoppingCart.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.exception.IllegalOperationException;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.itemCart.data.ItemCart;
import sk.stuba.fei.uim.oop.assignment3.itemCart.logic.IItemCartService;
import sk.stuba.fei.uim.oop.assignment3.itemCart.web.bodies.ItemCartAddRequest;
import sk.stuba.fei.uim.oop.assignment3.product.data.Product;
import sk.stuba.fei.uim.oop.assignment3.product.logic.IProductService;
import sk.stuba.fei.uim.oop.assignment3.shoppingCart.data.IShoppingCartRepository;
import sk.stuba.fei.uim.oop.assignment3.shoppingCart.data.ShoppingCart;

import java.util.List;

@Service
public class ShoppingCartService implements IShoppingCartService{

    @Autowired
    private IShoppingCartRepository shoppingCartRepository;

    @Autowired
    private IItemCartService itemCartService;



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



    @Override
    public ShoppingCart addProductToSHoppingCartById(Long id, ItemCartAddRequest itemCart) throws NotFoundException, IllegalOperationException {
        ShoppingCart retShoppingCart = shoppingCartRepository.findShoppingCartById(id);
        Long idOfProductInCart = itemCart.getProductId();
        Product product = productService.getProductById(idOfProductInCart);


        if (retShoppingCart!=null){
            if (isSHoppingCartPayed(retShoppingCart)){
                throw new IllegalOperationException();
            }
            else {
                if (productService.isSufficientAmount(idOfProductInCart,itemCart.getAmount())){
                    ItemCart itemCartInCurrentShoppingCart = productService.isProductInList(idOfProductInCart,retShoppingCart.getShoppingList());
                    if (itemCartInCurrentShoppingCart != null){
                        Long productAmountBefore = product.getAmount();
                        Long productAmountInCartBefore = itemCartInCurrentShoppingCart.getAmount();
                        product.setAmount(productAmountBefore- itemCartInCurrentShoppingCart.getAmount());
                        itemCartInCurrentShoppingCart.setAmount(productAmountInCartBefore+productAmountBefore);
                    }
                    else {
                        Long productAmountBefore = product.getAmount();
                        product.setAmount(productAmountBefore- itemCart.getAmount());
                        ItemCart newItemCartToAdd = itemCartService.createItemCart();
                        newItemCartToAdd.setProduct(product);
                        newItemCartToAdd.setAmount(itemCart.getAmount());
                        retShoppingCart.getShoppingList().add(newItemCartToAdd);
                    }
                }
                else {
                    throw new IllegalOperationException();
                }
                return shoppingCartRepository.save(retShoppingCart);
            }
        }
        else {
            throw new NotFoundException();
        }

    }


    @Override
    public boolean isSHoppingCartPayed(ShoppingCart shoppingCart) {
        if (shoppingCart.isPaid()){
            return true;
        }
        else return false;

    }


    @Override
    public double payForCart(Long id) throws NotFoundException, IllegalOperationException {
        ShoppingCart cart = getShoppingCartById(id);
        double payment = 0;
        if (isSHoppingCartPayed(cart)){
            throw new IllegalOperationException();
        }
        else {
            for (ItemCart itemCart:cart.getShoppingList()){
                payment+=itemCart.getAmount()*itemCart.getProduct().getPrice();
            }
            cart.setPaid(true);
            shoppingCartRepository.save(cart);
            return payment;
        }

    }
}
