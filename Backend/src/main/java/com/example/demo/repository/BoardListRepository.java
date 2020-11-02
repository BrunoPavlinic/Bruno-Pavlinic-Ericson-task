package com.example.demo.repository;

import com.example.demo.entity.Board;
import com.example.demo.entity.BoardList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BoardListRepository extends JpaRepository<BoardList, Integer> {
    List<BoardList> findAllByBoard(int boardId);
}
