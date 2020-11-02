import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { Boardlist } from './boardlist.model';

@Injectable({
  providedIn: 'root'
})
export class BoardlistService {

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      'Authorization': 'my-auth-token'
    })
  };

  constructor(public http: HttpClient) { }

  getBoardList(boardId: String) {
    return this.http.get('http://localhost:8080/boardList/getAll/' + boardId);
  }

  createBoardList(boardList: Boardlist) {
    return this.http.post('http://localhost:8080/boardList/create', boardList, this.httpOptions);
  }

  updateBoardList(boardList: Boardlist) {
    return this.http.put('http://localhost:8080/boardList/update', boardList, this.httpOptions);
  }

  deleteBoardListById(boardListId: number) {
    return this.http.delete('http://localhost:8080/boardList/' + boardListId);
  }

}
