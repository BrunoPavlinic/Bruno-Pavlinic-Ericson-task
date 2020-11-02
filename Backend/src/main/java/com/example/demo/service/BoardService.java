package com.example.demo.service;

import com.example.demo.entity.Board;
import com.example.demo.entity.User;
import com.example.demo.repository.BoardRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.response.Error;
import com.example.demo.response.Response;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class BoardService implements IBoardService{

    public final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Override
    public Board getBoardById(int boardId) {
        return boardRepository.getBoardById(boardId);
    }

    @Override
    public List<Board> getAllBoardsByUserId(int userId) {
        return boardRepository.findAllByUser(userId);
    }

    @Override
    public Response create(Board board) {
        Response response = new Response();
        if(isValid(response, board, false)) {
            boardRepository.save(board);
        }
        return response;
    }

    @Override
    public Response update(Board board) {
        Response response = new Response();
        if(isValid(response, board, true)) {
            boardRepository.save(board);
        }
        return response;
    }

    @Override
    public void delete(int id) {
        Board board = new Board();
        board.setId(id);
        boardRepository.delete(board);
    }

    private boolean isValid(Response response, Board board, boolean isUpdate) {
        boolean isValid = true;
        String errorField = "name";
        if(isUpdate) {
            errorField = "general";
        }
        if(board.getName().isEmpty()) {
            response.addError(new Error("Name is mandatory", errorField));
            response.setValid(false);
            isValid = false;
        }

        return isValid;
    }


}

