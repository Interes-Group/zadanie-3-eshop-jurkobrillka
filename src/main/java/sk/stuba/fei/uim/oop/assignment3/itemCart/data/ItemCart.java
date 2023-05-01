package sk.stuba.fei.uim.oop.assignment3.itemCart.data;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.assignment3.product.data.Product;

import javax.persistence.*;

//TODO OTAZKA: Je problem ak si neukladam id ale beriem z Product Objektu?
@Getter
@Setter
@Entity
public class ItemCart {

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Product product;
    private int amount;


}