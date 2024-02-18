package com.cryss.tipsandlearnings.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartItemsDTO implements CartItemsProjection{

    private Long cartId;
    private Long itemId;

    private Long quantity;

    @Override
    public Long getCartId() {
        return cartId;
    }

    @Override
    public Long getItemId() {
        return itemId;
    }

    @Override
    public Long getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "CartItemsDTO{" +
                "cartId=" + cartId +
                ", itemId=" + itemId +
                ", quantity=" + quantity +
                '}';
    }
}
