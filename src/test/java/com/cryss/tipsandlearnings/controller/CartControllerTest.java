package com.cryss.tipsandlearnings.controller;

import com.cryss.tipsandlearnings.model.Cart;
import com.cryss.tipsandlearnings.model.Item;
import com.cryss.tipsandlearnings.repository.CartRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(CartController.class)
@Log4j2
@Disabled("Disbale until fix")
class CartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private CartRepository cartRepository;

    private Cart cart;

    private Item item;

    private MockHttpServletRequest request;

    @BeforeEach
    @Disabled("Disbale until fix")
    public void setup() {

        cart = Cart.builder ()
                .id (1L)
                .build ();

        item = Item.builder ()
                .id (2L)
                .cart (cart)
                .build ();



        log.info ("Creating cart ={} and Item={}...", cart, item);

        this.request = new MockHttpServletRequest ();
        this.request.setScheme("http");
        this.request.setServerName("localhost");
        this.request.setServerPort(-1);
        this.request.setRequestURI("/carts");
        this.request.setContextPath("/carts");

    }

    @Test
    void getCarts() throws Exception {

        //Given // Arrange
       Cart cart2 = Cart.builder ()
                .id (2L)
                .build ();

        List<Cart> carts = new ArrayList<> ();
        carts.add (cart);
        carts.add (cart2);




        given(cartRepository.findAll ()).willReturn (carts);

        // When /Act
        ResultActions response = mockMvc.perform (get ("/carts"));

        // Then /Assert
        response.
                andExpect (
                        status ().isOk ())
                .andDo (print ())
                .andExpect (jsonPath ("$.size()", is (carts.size ())))
                .andExpect (jsonPath ("$.[0].id", is (1)))
        ;


    }
}