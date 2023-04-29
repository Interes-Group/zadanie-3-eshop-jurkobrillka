package sk.stuba.fei.uim.oop.assignment3.product.logic;

import javassist.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.product.data.Product;
import sk.stuba.fei.uim.oop.assignment3.product.web.bodies.ProductEditRequest;
import sk.stuba.fei.uim.oop.assignment3.product.web.bodies.ProductRequest;

import java.util.List;

public interface IProductService {

    List<Product> getAllProducts();

    Product createProduct(ProductRequest productRequest);

    Product getProductById(long id) throws NotFoundException;

    Product updateProduct(long id, ProductEditRequest productEditRequest) throws NotFoundException;

    void deleteProduct(long id) throws NotFoundException;

    int getProductAmount(long id) throws NotFoundException;

    int addProductAmount(long id, int amountPar) throws NotFoundException;

}
