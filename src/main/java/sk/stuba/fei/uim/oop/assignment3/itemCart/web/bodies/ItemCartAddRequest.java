package sk.stuba.fei.uim.oop.assignment3.itemCart.web.bodies;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sk.stuba.fei.uim.oop.assignment3.itemCart.data.ItemCart;

@Getter
@Setter
@NoArgsConstructor
public class ItemCartAddRequest {
    private Long productId;
    private int amount;
    public ItemCartAddRequest(ItemCart itemCart) {
        this.productId = itemCart.getProduct().getId();
        this.amount = itemCart.getAmount();
    }
}
