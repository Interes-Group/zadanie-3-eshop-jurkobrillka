package sk.stuba.fei.uim.oop.assignment3.shoppingCart.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.oop.assignment3.exception.IllegalOperationException;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.itemCart.data.ItemCart;
import sk.stuba.fei.uim.oop.assignment3.itemCart.logic.IItemCartService;
import sk.stuba.fei.uim.oop.assignment3.itemCart.web.bodies.ItemCartAddRequest;
import sk.stuba.fei.uim.oop.assignment3.shoppingCart.logic.IShoppingCartService;
import sk.stuba.fei.uim.oop.assignment3.shoppingCart.web.bodies.ShoppingCartResponse;

@RestController
@RequestMapping("/cart")
public class ShoppingCartController {

    @Autowired
    private IShoppingCartService shoppingCartService;

    @Autowired
    private IItemCartService itemCartService;


    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ShoppingCartResponse> createShoppingCart(){
        return new ResponseEntity<>(new ShoppingCartResponse(shoppingCartService.createShoppingCart()), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ShoppingCartResponse getShoppingCart(@PathVariable("id") Long id) throws NotFoundException{
        return new ShoppingCartResponse(shoppingCartService.getShoppingCartById(id));
    }

    @DeleteMapping(value = "/{id}")
    public void deleteShoppingCart(@PathVariable("id") Long id)throws NotFoundException{
        shoppingCartService.deleteShoppingCartById(id);
    }

    @PostMapping(value = "/{id}/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ShoppingCartResponse addToShoppingCart(@PathVariable("id") Long id, @RequestBody ItemCartAddRequest itemCartAddRequest)throws NotFoundException, IllegalOperationException{
        return new ShoppingCartResponse(shoppingCartService.addProductToSHoppingCartById(id,itemCartAddRequest));
    }

    @GetMapping(value = "/{id}/pay", produces = MediaType.TEXT_PLAIN_VALUE)
    public String payForCart(@PathVariable("id") Long id) throws NotFoundException, IllegalOperationException {

        return "" + (double)shoppingCartService.payForCart(id);
    }


}
