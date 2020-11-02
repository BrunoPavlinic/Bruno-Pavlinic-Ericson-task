package com.example.demo.controller;

import com.example.demo.entity.Board;
import com.example.demo.entity.BoardList;
import com.example.demo.response.Response;
import com.example.demo.service.BoardListService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/boardList")
public class BoardListController {

    public final BoardListService boardListService;

    public BoardListController(BoardListService boardListService) {
        this.boardListService = boardListService;
    }

    @GetMapping(value = "getAll/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<BoardList>> getBoardListByBoardId(@PathVariable int id) {
        return ResponseEntity.ok(boardListService.getAllBoardListByBoardId(id));
    }

    @PostMapping(value = "/create")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Response> createBoardList(@RequestBody BoardList boardList) {
        return ResponseEntity.ok(boardListService.create(boardList));
    }

    @PutMapping(value = "/update")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Response> updateBoardList(@RequestBody BoardList boardList) {
        return ResponseEntity.ok(boardListService.update(boardList));
    }

    @DeleteMapping(value = "/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Void> deleteBoardListById(@PathVariable int id) {
        boardListService.delete(id);
        return ResponseEntity.ok().build();
    }

}
