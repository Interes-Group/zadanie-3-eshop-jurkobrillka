package sk.stuba.fei.uim.oop.assignment3.product.logic;

import sk.stuba.fei.uim.oop.assignment3.exception.IllegalOperationException;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;
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

    int getProductAmount(long id) throws sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;

    int addProductAmount(long id, int amountPar) throws NotFoundException;

    boolean isSufficientAmount(Long id, int decrementAmount)throws NotFoundException;

    Product returnExistingPrduct(Long id)throws NotFoundException;

}
