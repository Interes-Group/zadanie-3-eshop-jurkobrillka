package sk.stuba.fei.uim.oop.assignment3.product.data;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.assignment3.product.web.bodies.ProductRequest;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private Long amount;
    private String unit;
    private double price;


    public Product(ProductRequest productRequest) {
        this.name = productRequest.getName();
        this.description = productRequest.getDescription();
        this.amount = productRequest.getAmount();
        this.unit = productRequest.getUnit();
        this.price = productRequest.getPrice();
    }
}
