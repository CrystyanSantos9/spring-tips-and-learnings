package com.cryss.tipsandlearnings.controller;

import com.cryss.tipsandlearnings.model.Cart;
import com.cryss.tipsandlearnings.repository.CartRepository;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/carts")
@Log4j2
@RequiredArgsConstructor
public class CartController {

    private final CartRepository cartRepository;

    @GetMapping
    public ResponseEntity<List<Cart>> getCarts(
            @RequestParam(required = false) String id,
            ServletRequest request,
            ServletResponse response){

        List<Cart> result = new ArrayList<> ();

        log.info ("Controller={}, operation={}, method={}", "CartController", "getCarts", "GET /carts");

        if(id != null && !id.isEmpty ()){
            var cart = cartRepository.findById (Long.valueOf (id))
                    .orElseThrow (()-> new RuntimeException ("CART_ID NOT FOUND"));

            result.add (cart);
        }else{
            result = cartRepository.findAll ();
        }


        log.info ("Controller={}, operation={}, method={}, response={}", "CartController", "getCarts", "GET /carts", result);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
