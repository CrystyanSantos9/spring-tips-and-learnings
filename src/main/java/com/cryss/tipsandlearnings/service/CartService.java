package com.cryss.tipsandlearnings.service;


import com.cryss.tipsandlearnings.model.CartItemsDTO;

import com.cryss.tipsandlearnings.repository.CartRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@EnableCaching
public class CartService {

    private final CartRepository cartRepository;


    @Cacheable(value = "carts_items", unless="#result.size()==0")
    public List<CartItemsDTO> getCarts(String id){


        if (id != null && !id.isEmpty ()) {
           return cartRepository.findItemsByCartId (Long.parseLong (id)).stream ().map (item -> {
                return CartItemsDTO
                        .builder ()
                        .cartId (item.getCartId ())
                        .itemId (item.getItemId ())
                        .quantity (item.getQuantity ())
                        .build ();
            }).toList ();

        } else {
           return cartRepository.findAllItems ().stream ().map (item -> {
                return CartItemsDTO.builder ().
                        cartId (item.getCartId ()).
                        itemId (item.getItemId ()).
                        quantity (item.getQuantity ()).build ();
            }).toList ();

        }
    }

}
