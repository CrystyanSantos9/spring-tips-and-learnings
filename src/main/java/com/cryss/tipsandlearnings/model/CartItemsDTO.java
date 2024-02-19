package com.cryss.tipsandlearnings.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Objects;

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
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof CartItemsDTO that)) return false;

        if (!Objects.equals (cartId, that.cartId)) return false;
        if (!Objects.equals (itemId, that.itemId)) return false;
        return Objects.equals (quantity, that.quantity);
    }

    @Override
    public int hashCode() {
        int result = cartId != null ? cartId.hashCode () : 0;
        result = 31 * result + (itemId != null ? itemId.hashCode () : 0);
        result = 31 * result + (quantity != null ? quantity.hashCode () : 0);
        return result;
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
