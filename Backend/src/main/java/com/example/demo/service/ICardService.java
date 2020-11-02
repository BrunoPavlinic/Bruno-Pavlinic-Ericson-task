package com.example.demo.service;

import com.example.demo.entity.Board;
import com.example.demo.entity.BoardList;
import com.example.demo.entity.Card;
import com.example.demo.response.Response;

import java.util.List;

public interface ICardService {
    List<Card> getAllCardByListId(int listId);
    Response create(Card card);
    Response update(Card card);
    void delete(int id);
}
