package com.cryss.tipsandlearnings.controller;

import com.cryss.tipsandlearnings.model.Cart;
import com.cryss.tipsandlearnings.repository.CartRepository;
import jakarta.servlet.ServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/carts")
@Log4j2
@RequiredArgsConstructor
public class CartController {

    private final CartRepository cartRepository;

    @GetMapping
    public ResponseEntity<List<Cart>> getCarts(){

        log.info ("Controller={}, operation={}, method={}", "CartController", "getCarts", "GET /carts");


        return new ResponseEntity<>(cartRepository.findAll (), HttpStatus.OK);
    }
}
