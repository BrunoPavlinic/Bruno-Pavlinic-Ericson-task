package com.example.demo.service;

import com.example.demo.entity.Board;
import com.example.demo.entity.BoardList;
import com.example.demo.repository.BoardListRepository;
import com.example.demo.response.Error;
import com.example.demo.response.Response;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardListService implements IBoardListService{

    public final BoardListRepository boardListRepository;

    public BoardListService(BoardListRepository boardListRepository) {
        this.boardListRepository = boardListRepository;
    }

    @Override
    public List<BoardList> getAllBoardListByBoardId(int boardId) {
        return boardListRepository.findAllByBoard(boardId);
    }

    @Override
    public Response create(BoardList boardList) {
        Response response = new Response();
        if(isValid(response, boardList, false)) {
            boardListRepository.save(boardList);
        }
        return response;
    }

    @Override
    public Response update(BoardList boardList) {
        Response response = new Response();
        if(isValid(response, boardList, true)) {
            boardListRepository.save(boardList);
        }
        return response;
    }

    @Override
    public void delete(int id) {
        BoardList boardList = new BoardList();
        boardList.setId(id);
        boardListRepository.delete(boardList);
    }

    private boolean isValid(Response response, BoardList boardList, boolean isUpdate) {
        boolean isValid = true;
        String errorField = "name";
        if(isUpdate) {
            errorField = "general";
        }
        if(boardList.getName().isEmpty()) {
            response.addError(new Error("Name is mandatory", errorField));
            response.setValid(false);
            isValid = false;
        }

        return isValid;
    }


}

