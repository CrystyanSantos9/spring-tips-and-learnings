package com.cryss.tipsandlearnings.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "ITEMS_INVERSE_SIDE")
public class ItemInverseSide implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "cart_id", insertable = false, updatable = false)
    private CartOwnerSide cart;


    public ItemInverseSide() {
    }

    public ItemInverseSide(CartOwnerSide c) {
        this.cart = c;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public CartOwnerSide getCart() {
        return cart;
    }

    public void setCart(CartOwnerSide cart) {
        this.cart = cart;
    }
}
