package com.example.demo.service;

import com.example.demo.entity.Board;
import com.example.demo.entity.User;
import com.example.demo.response.Response;

import java.util.List;

public interface IBoardService {
    List<Board> getAllBoardsByUserId(int userId);
    Response create(Board board);
    Response update(Board board);
    Board getBoardById(int boardId);
    void delete(int id);
}
