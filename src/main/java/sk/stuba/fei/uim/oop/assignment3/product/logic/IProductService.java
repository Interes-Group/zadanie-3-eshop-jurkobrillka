package sk.stuba.fei.uim.oop.assignment3.product.logic;

import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.itemCart.data.ItemCart;
import sk.stuba.fei.uim.oop.assignment3.product.data.Product;
import sk.stuba.fei.uim.oop.assignment3.product.web.bodies.ProductEditRequest;
import sk.stuba.fei.uim.oop.assignment3.product.web.bodies.ProductRequest;

import java.util.List;

public interface IProductService {

    List<Product> getAllProducts();

    Product createProduct(ProductRequest productRequest);

    Product getProductById(long id) throws sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;

    Product updateProduct(long id, ProductEditRequest productEditRequest) throws sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;

    void deleteProduct(long id) throws sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;

    long getProductAmount(Long id) throws sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;

    Long addProductAmount(long id, Long amountPar) throws NotFoundException;

    boolean isSufficientAmount(Long id, Long decrementAmount)throws NotFoundException;

    ItemCart isProductInList(Long id, List<ItemCart> shoppingList) throws NotFoundException;

    Product returnExistingPrduct(Long id)throws NotFoundException;

}
