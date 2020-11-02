package com.example.demo.controller;

import com.example.demo.entity.Board;
import com.example.demo.entity.User;
import com.example.demo.response.Response;
import com.example.demo.service.BoardService;
import com.example.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
public class BoardController {

    public final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping(value = "getAll/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<Board>> getBoardsByUserId(@PathVariable int id) {
        return ResponseEntity.ok(boardService.getAllBoardsByUserId(id));
    }

    @PostMapping(value = "/create")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Response> createBoard(@RequestBody Board board) {
        return ResponseEntity.ok(boardService.create(board));
    }

    @PutMapping(value = "/update")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Response> updateBoard(@RequestBody Board board) {
        return ResponseEntity.ok(boardService.update(board));
    }

    @DeleteMapping(value = "/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Void> deleteBoardById(@PathVariable int id) {
        boardService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Board> getBoardById(@PathVariable int id) {
        return ResponseEntity.ok(boardService.getBoardById(id));
    }

}
