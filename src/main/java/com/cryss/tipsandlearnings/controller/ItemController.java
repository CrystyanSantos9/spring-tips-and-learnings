package com.cryss.tipsandlearnings.controller;


import com.cryss.tipsandlearnings.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/items")
@Log4j2
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepository itemRepository;

    @GetMapping
    public ResponseEntity<Void> getItems(){

        log.info ("Controller={}, operation={}, method={}", "ItemController", "getItems", "GET /items");

        itemRepository.findAll ().forEach (System.out::println);

        return new ResponseEntity<> (HttpStatus.OK);

    }
}
