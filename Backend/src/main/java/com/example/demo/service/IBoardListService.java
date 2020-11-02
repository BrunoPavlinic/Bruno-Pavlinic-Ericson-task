package com.example.demo.service;

import com.example.demo.entity.Board;
import com.example.demo.entity.BoardList;
import com.example.demo.response.Response;

import java.util.List;

public interface IBoardListService {
    List<BoardList> getAllBoardListByBoardId(int boardId);
    Response create(BoardList boardList);
    Response update(BoardList boardList);
    void delete(int id);
}
