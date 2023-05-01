package sk.stuba.fei.uim.oop.assignment3.product.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.itemCart.data.ItemCart;
import sk.stuba.fei.uim.oop.assignment3.product.data.IProductRepository;
import sk.stuba.fei.uim.oop.assignment3.product.data.Product;
import sk.stuba.fei.uim.oop.assignment3.product.web.bodies.ProductEditRequest;
import sk.stuba.fei.uim.oop.assignment3.product.web.bodies.ProductRequest;

import java.util.List;

@Service
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
    public Product getProductById(long id) throws sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException {
        System.out.println("PRED NULNULNUKNUL");
        Product product = productRepository.findProductById(id);
        System.out.println(product);
        if (product==null){
            System.out.println("NULNULNULLNULLNULLNULLNULLNULL");
            throw new NotFoundException();
        }
        else {
            return product;
        }

    }

    @Override
    public Product updateProduct(long id, ProductEditRequest productEditRequest) throws sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException {
        Product product = /*returnExistingPrduct(id);*/
        getProductById(id);
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
    public void deleteProduct(long id) throws sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException {
        productRepository.delete(getProductById(id));
    }

    @Override
    public long getProductAmount(Long id) throws NotFoundException {
        Product product = getProductById(id);
        //4
        System.out.println("BRUH???"+product);
        System.out.println("PO BRUHH je product amount"+product.getAmount());
        return product.getAmount();
    }

    @Override
    public Long addProductAmount(long id, Long amountPar) throws sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException {
        Product oldProd = getProductById(id);
        Long newAmount = oldProd.getAmount()+amountPar;
        oldProd.setAmount(newAmount);
        productRepository.save(oldProd);
        return newAmount;
    }

    @Override
    public boolean isSufficientAmount(Long id, Long decrementAmount) throws NotFoundException {
        Product product = returnExistingPrduct(id);
        if(product.getAmount() - decrementAmount<0){
            System.out.println("NEMOZEM DAT DO KOSIKA, AMOUNT POZIADAVKY JE VACSI AKO POCET NA SKLADW");
            return false;
        }
        else {
            System.out.println("MOZEM BRAT ZO SKLADU");
            product.setAmount(product.getAmount()-decrementAmount);
            return true;
        }
    }

    @Override
    public ItemCart isProductInList(Long id, List<ItemCart> shoppingList) throws NotFoundException {
        Product product = getProductById(id);
        boolean productIsInList = false;
        ItemCart retItemCart = null;
        for (ItemCart itemCart:shoppingList){
            if (product.equals(itemCart.getProduct())){
                productIsInList = true;
                retItemCart = itemCart;
            }
        }
        return retItemCart;
    }

    @Override
    public Product returnExistingPrduct(Long id) throws NotFoundException {
        Product product = getProductById(id);
        if (product!=null){
            return product;
        }
        else {
            throw new NotFoundException();
        }
    }


}
