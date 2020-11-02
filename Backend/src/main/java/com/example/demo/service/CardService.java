package com.example.demo.service;

import com.example.demo.entity.Card;
import com.example.demo.repository.CardRepository;
import com.example.demo.response.Error;
import com.example.demo.response.Response;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService implements ICardService{

    public final CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public List<Card> getAllCardByListId(int listId) {
        return cardRepository.findAllByList(listId);
    }

    @Override
    public Response create(Card card) {
        Response response = new Response();
        if(isValid(response, card, false)) {
            cardRepository.save(card);
        }
        return response;
    }

    @Override
    public Response update(Card card) {
        Response response = new Response();
        if(isValid(response, card, true)) {
            cardRepository.save(card);
        }
        return response;
    }

    @Override
    public void delete(int id) {
        Card card = new Card();
        card.setId(id);
        cardRepository.delete(card);
    }

    private boolean isValid(Response response, Card card, boolean isUpdate) {
        boolean isValid = true;
        String errorField = "text";
        if(isUpdate) {
            errorField = "general";
        }
        if(card.getText().isEmpty()) {
            response.addError(new Error("Text is mandatory", errorField));
            response.setValid(false);
            isValid = false;
        }

        return isValid;
    }


}

