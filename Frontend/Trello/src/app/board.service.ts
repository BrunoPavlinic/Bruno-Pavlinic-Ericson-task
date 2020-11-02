import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { Board } from './board.model';

@Injectable({
  providedIn: 'root'
})
export class BoardService {

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      'Authorization': 'my-auth-token'
    })
  };

  constructor(public http: HttpClient) { }

  getUserBoards(userId: String) {
    return this.http.get('http://localhost:8080/board/getAll/' + userId);
  }

  getBoard(boardId: String) {
    return this.http.get('http://localhost:8080/board/' + boardId);
  }

  createBoard(board: Board) {
    return this.http.post('http://localhost:8080/board/create', board, this.httpOptions);
  }

  updateBoard(board: Board) {
    return this.http.put('http://localhost:8080/board/update', board, this.httpOptions);
  }

  deleteBoardById(boardId: number) {
    return this.http.delete('http://localhost:8080/board/' + boardId);
  }
}
