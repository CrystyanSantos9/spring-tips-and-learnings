package com.cryss.tipsandlearnings.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;

import java.util.Objects;

@Entity
@Table(name = "ITEMS")
@Builder
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name="cart_id", nullable=false)
    private Cart cart;


    public Item() {
    }

    public Item(Cart c) {
        this.cart = c;
    }

    public Item(Long id, Cart c) {
        this.id = id;
        this.cart = c;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Item item)) return false;
        return id == item.id && Objects.equals (cart, item.cart);
    }

    @Override
    public int hashCode() {
        return Objects.hash (id);
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", cart=" + cart +
                '}';
    }
}
