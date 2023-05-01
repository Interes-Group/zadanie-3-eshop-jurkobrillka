package sk.stuba.fei.uim.oop.assignment3.shoppingCart.logic;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.exception.IllegalOperationException;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.itemCart.data.ItemCart;
import sk.stuba.fei.uim.oop.assignment3.itemCart.web.bodies.ItemCartAddRequest;
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



    @Override
    public ShoppingCart addProductToSHoppingCartById(Long id, ItemCartAddRequest itemCart) throws NotFoundException, IllegalOperationException {
        ShoppingCart retShoppingCart = shoppingCartRepository.findShoppingCartById(id);
        //TODO JAK TO ZE TOTO HORE NIKDY NENI NULL????
        //ItemCart itemCartInRetShoppingCart = retShoppingCart.
        System.out.println("Pracujem s"+retShoppingCart.getId()+" shoppingCartom");
        Long idOfProductInCart = itemCart.getProductId();
        Product product = productService.getProductById(idOfProductInCart);
        System.out.println("Product ktory bol v itemCarte "+product);


        if (retShoppingCart!=null){
            System.out.println("KOSIK EXISTUJE "+retShoppingCart);
            //kosikExistuje
            if (isSHoppingCartPayed(retShoppingCart)){
                //kosik je zaplateny - vyhod 400
                throw new IllegalOperationException();
            }
            else {
                System.out.println("KOSIK NENI ZAPLATENY - MOZEM PRIDAVAT");
                //potrebuje prve zistit ci produkt vobec existuje - produkt proste exituje bo kebyze neexistuje ta vyjebe exception skorej hore
                //potrebujem zistit ci mozem brat zo skladu alebo ne a jebat na ukladanie...
                if (productService.isSufficientAmount(idOfProductInCart,itemCart.getAmount())){
                    System.out.println("JE DOST NA SKLADE MOZEM DALEJ");
                    ////potrebujem zistit ci je uz produkt v kosiku
                    //ak ano, pridam len kosikAmount a productAmount odpocita
                    //ak nie, vytvorit novy ItemCart s produktom a amountom z parametru,,,,z produktu odpocitam
                    ItemCart itemCartInCurrentShoppingCart = productService.isProductInList(idOfProductInCart,retShoppingCart.getShoppingList());
                    if (itemCartInCurrentShoppingCart != null){
                        //ano, je v kosiku pridam len kosikAmount a productAmount odpocita
                        Long productAmountBefore = product.getAmount();
                        Long productAmountInCartBefore = itemCartInCurrentShoppingCart.getAmount();
                        product.setAmount(productAmountBefore- itemCartInCurrentShoppingCart.getAmount());
                        itemCartInCurrentShoppingCart.setAmount(productAmountInCartBefore+productAmountBefore);
                    }
                    else {
                        System.out.println("TOTO BY SOM MAL RIESIT?!?!!??!?!?");
                        //nie, vytvorit novy ItemCart s produktom a amountom z parametru,,,,z produktu odpocitam
                        /*Long productAmountBefore = product.getAmount();
                        product.setAmount(productAmountBefore- itemCart.getAmount());
                        ItemCart newItemCartToAdd = new ItemCart();
                        newItemCartToAdd.setAmount(itemCart.getAmount());
                        newItemCartToAdd.setProduct(product);
                        retShoppingCart.getShoppingList().add(newItemCartToAdd);*/
                    }
                }
                else {
                    System.out.println("NENI DOST NA SKLADE");
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
    public void addProductToList(Long id, ItemCartAddRequest itemCart, List<ItemCart> shoppingList) throws NotFoundException {
        boolean containsProd = false;
        Long itemCartProductId = itemCart.getProductId();
        System.out.println("ID PRODUKTU "+itemCartProductId);
        Product p = productService.getProductById(itemCartProductId);


        for (int i = 0; i <shoppingList.size() ; i++) {
            if (shoppingList.get(i).getProduct().getId().equals(itemCart.getProductId())){
                System.out.println("O ANO UZ EXISTUJE PRODUKT V KOSIKU");
                containsProd = true;
                break;
            }
        }

        int decrementedProductAmount = 0;
        if (shoppingList.size()==0){
            System.out.println("V SHOPPING LISTE NENI NIC");
            System.out.println("KOLKO JE DANEHO PRODUKTU: (product amount):"+ p.getAmount());
            System.out.println("KOLKO CHCU Z PRODUKTU UJEBAT ZO SKLADU: "+itemCart.getAmount());
            System.out.println("KOLKO OSTANE V SKLADE PRODUKTU "+decrementedProductAmount);
            //decrementedProductAmount = productService.getProductAmount(itemCartProductId.longValue()) - itemCart.getAmount();
        }
        else {
            System.out.println("V SHOPPUING LISTE JE NIECO");
            //decrementedProductAmount = p.getAmount()-itemCart.getAmount();
        }

        if (containsProd){
            System.out.println("PRODUKT JE V KOSIKU");
            //p.setAmount(itemCart.getAmount());
            //p.setAmount(decrementedProductAmount);
            //kosik obsahuje produkt
            //nastavim sa na id kde je ten itemCart rovny produktu v liste shoppingList
            //dam ++ jaky je amount itemCart
        }
        else {
            System.out.println("PRODUKT ESTE NENI V KOSIKU");
            //decrementedProductAmount = p.getAmount()-itemCart.getAmount();
            ItemCart itemCartAdd = new ItemCart();

            //Product product = productService.getProductById(itemCart.getProductId());

            itemCartAdd.setProduct(p);
            itemCartAdd.setAmount(itemCart.getAmount());
            shoppingList.add(itemCartAdd);
            System.out.println("PRIDALI SME DO KOSIKA "+itemCartAdd); //5

            //p.setAmount(decrementedProductAmount);
            //itemCart.getProduct().setAmount();
            //TODO TU MOZNO CHYBY

            //kosik neobsahuje produkt,
            //pridam do listu cely itemCart a fuck off

        }
    }

    @Override
    public double payForCart(Long id) throws NotFoundException, IllegalOperationException {
        ShoppingCart cart = getShoppingCartById(id);
        double payment = 0;
        if (isSHoppingCartPayed(cart)){
            System.out.println("IS PAID");
            throw new IllegalOperationException();
        }
        else {
            System.out.println("IDEM ZAPLATIT");
            for (ItemCart itemCart:cart.getShoppingList()){
                payment+=itemCart.getAmount()*itemCart.getProduct().getPrice();
            }
            cart.setPaid(true);
            shoppingCartRepository.save(cart);
            return payment;
        }

    }
}
