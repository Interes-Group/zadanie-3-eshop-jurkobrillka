package sk.stuba.fei.uim.oop.assignment3.product.web;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.oop.assignment3.product.logic.IProductService;
import sk.stuba.fei.uim.oop.assignment3.product.web.bodies.ProductEditRequest;
import sk.stuba.fei.uim.oop.assignment3.product.web.bodies.ProductRequest;
import sk.stuba.fei.uim.oop.assignment3.product.web.bodies.ProductResponse;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductResponse> getAllProducts(){
        return productService.getAllProducts().stream().map(ProductResponse::new).collect(Collectors.toList());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest productRequestBody){
        ProductResponse newProductResponse = new ProductResponse(productService.createProduct(productRequestBody));
        return new ResponseEntity<>(newProductResponse,HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductResponse getProduct(@PathVariable("id") Long id) throws NotFoundException {
        ProductResponse retProdResponse = new ProductResponse(productService.getProductById(id));
        return retProdResponse;
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductResponse updateProduct(@PathVariable("id") Long id, @RequestBody ProductEditRequest productEditRequestBody) throws NotFoundException {
        ProductResponse retProductResponse = new ProductResponse(productService.updateProduct(id,productEditRequestBody));
        return retProductResponse;
    }

    //TODO tu si skoncil



}
