package com.cryss.tipsandlearnings.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.proxy.HibernateProxy;

import java.io.Serializable;
import java.util.Objects;

//@Entity
@Table(name = "ITEMS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Item implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cart_id", nullable = false)
    @OnDelete (action = OnDeleteAction.CASCADE)
    private Cart cart;

    private Long quantity;


    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Item item)) return false;

        if (!Objects.equals (id, item.id)) return false;
        return Objects.equals (cart, item.cart);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode ();
        result = 31 * result + cart.hashCode ();
        return result;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", cart=" + cart +
                '}';
    }
}
