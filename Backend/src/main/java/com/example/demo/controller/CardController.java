package com.example.demo.controller;

import com.example.demo.entity.Board;
import com.example.demo.entity.BoardList;
import com.example.demo.entity.Card;
import com.example.demo.response.Response;
import com.example.demo.service.BoardListService;
import com.example.demo.service.CardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/card")
public class CardController {

    public final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping(value = "getAll/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<Card>> getCardByBoardId(@PathVariable int id) {
        return ResponseEntity.ok(cardService.getAllCardByListId(id));
    }

    @PostMapping(value = "/create")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Response> createCard(@RequestBody Card card) {
        return ResponseEntity.ok(cardService.create(card));
    }

    @PutMapping(value = "/update")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Response> updateCard(@RequestBody Card card) {
        return ResponseEntity.ok(cardService.update(card));
    }

    @DeleteMapping(value = "/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Void> deleteCardById(@PathVariable int id) {
        cardService.delete(id);
        return ResponseEntity.ok().build();
    }

}
