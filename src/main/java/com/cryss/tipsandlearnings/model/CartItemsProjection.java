package com.cryss.tipsandlearnings.model;


public interface CartItemsProjection {
    Long getCartId();
    Long getItemId();
    Long  getQuantity();
}
