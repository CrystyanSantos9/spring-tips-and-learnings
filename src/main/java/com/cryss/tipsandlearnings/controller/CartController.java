//package com.cryss.tipsandlearnings.controller;
//
//import com.cryss.tipsandlearnings.model.CartItemsDTO;
//import com.cryss.tipsandlearnings.model.CartItemsProjection;
//import com.cryss.tipsandlearnings.repository.CartRepository;
//import com.cryss.tipsandlearnings.service.CartService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.log4j.Log4j2;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@RestController
//@RequestMapping(value = "/carts")
//@Log4j2
//@RequiredArgsConstructor
//public class CartController {
//
//    private final CartService cartService;
//
//    @GetMapping
//    public ResponseEntity<List<CartItemsDTO>> getCarts(@RequestParam(required = false) String id) {
//
//        log.info ("Controller={} operation={} method={}", "CartController", "getCarts", "GET /carts");
//
//        List<CartItemsDTO> res;
//
//        res = cartService.getCarts (id);
//
//
//        log.info ("Controller={}, " + "operation={}, " + "method={}", "response={}", "CartController", "getCarts", "GET /carts", res.toString ());
//
//
//        return new ResponseEntity<> (res, HttpStatus.OK);
//    }
//}
