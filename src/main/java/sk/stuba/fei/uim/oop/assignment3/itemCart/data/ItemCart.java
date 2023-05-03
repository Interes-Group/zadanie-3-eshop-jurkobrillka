package sk.stuba.fei.uim.oop.assignment3.itemCart.data;


import lombok.*;
import sk.stuba.fei.uim.oop.assignment3.product.data.Product;
import javax.persistence.*;
@Getter
@Setter
@Entity
@NoArgsConstructor
public class ItemCart {

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Product product;
    private Long amount;

}


