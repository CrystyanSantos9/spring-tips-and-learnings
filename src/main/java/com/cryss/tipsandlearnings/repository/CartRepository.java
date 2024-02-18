package com.cryss.tipsandlearnings.repository;

import com.cryss.tipsandlearnings.model.Cart;
import com.cryss.tipsandlearnings.model.CartItemsDTO;
import com.cryss.tipsandlearnings.model.CartItemsProjection;
import com.cryss.tipsandlearnings.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {

/*    @Query(value = """
            select c.id, i.cart_id, i.quantity from items i 
            right join cart c on c.id = i.cart_id
            """, nativeQuery = true)
    List<Item> findAllItems();*/


    @Query(value = """
            select c.id as cartId, i.id as itemId, i.quantity from items i 
            right join cart c on c.id = i.cart_id
            """, nativeQuery = true)
    List<CartItemsProjection> findAllItems();

    @Query(value = """
            select c.id as cartId, i.cart_id as itemId, i.quantity from items i 
            left join cart c on c.id = i.cart_id
            where c.id = :cartId
            """, nativeQuery = true)
    List<CartItemsProjection> findItemsByCartId(Long cartId);
}
