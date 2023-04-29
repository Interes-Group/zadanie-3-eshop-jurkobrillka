package sk.stuba.fei.uim.oop.assignment3.product.logic;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import sk.stuba.fei.uim.oop.assignment3.product.data.IProductRepository;
import sk.stuba.fei.uim.oop.assignment3.product.data.Product;
import sk.stuba.fei.uim.oop.assignment3.product.web.bodies.ProductEditRequest;
import sk.stuba.fei.uim.oop.assignment3.product.web.bodies.ProductRequest;

import java.util.List;

public class ProductService implements IProductService{

    @Autowired
    private IProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product createProduct(ProductRequest productRequest) {
        return productRepository.save(new Product(productRequest));
    }

    @Override
    public Product getProductById(long id) throws NotFoundException {
        if (productRepository.findProductById(id)==null){
            //throw new NotFoundException();
            System.out.println("NotFoundException();");
            //TODO
            return null;
        }
        else {
            return productRepository.findProductById(id);
        }

    }

    @Override
    public Product updateProduct(long id, ProductEditRequest productEditRequest) throws NotFoundException {
        Product product = getProductById(id);
        String nameProduct = productEditRequest.getName();
        String descProduct = productEditRequest.getDescription();

        if (nameProduct!=null){
            product.setName(nameProduct);
        }
        if(descProduct!=null){
            product.setDescription(descProduct);
        }


        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(long id) throws NotFoundException {
        productRepository.delete(getProductById(id));
    }

    @Override
    public int getProductAmount(long id) throws NotFoundException {
        //TODO
        //tu si skoncil
        here
        return 0;
    }

    @Override
    public int addProductAmount(long id, int amountPar) throws NotFoundException {
        return 0;
    }
}
