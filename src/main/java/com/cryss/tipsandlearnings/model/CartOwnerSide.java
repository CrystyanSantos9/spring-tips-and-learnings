package com.cryss.tipsandlearnings.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

//@Entity
@Table(name = "CART_OWNER_SIDE")
public class CartOwnerSide implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany
    @JoinColumn(name = "cart_id") // we need to duplicate the physical information
    private Set<ItemInverseSide> items = new HashSet<> ();

    public CartOwnerSide() {
    }

    public CartOwnerSide(long id, Set<ItemInverseSide> items) {
        this.id = id;
        this.items = items;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<ItemInverseSide> getItems() {
        return items;
    }

    public void setItems(Set<ItemInverseSide> items) {
        this.items = items;
    }
}
