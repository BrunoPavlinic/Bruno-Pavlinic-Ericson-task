import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Board } from '../board.model';
import { BoardService } from '../board.service';
import { Boardlist } from '../boardlist.model';
import { BoardlistService } from '../boardlist.service';
import { Error } from '../error.model';
import { Response } from '../response.model';

@Component({
  selector: 'app-board',
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.css']
})
export class BoardComponent implements OnInit {
  boardId: String;
  board: Board;
  newBoardList =  new Boardlist(0, 0, "");
  response = new Response();
  allBoardLists: Boardlist[];

  constructor(public boardService: BoardService, public boardListService: BoardlistService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      this.boardId = params.get('boardId');
    });
    this.boardService.getBoard(this.boardId).subscribe((data) => {
      this.board = Object.assign(new Board(), data);
    });
    this.setBoardList(this.boardId);
  }

  getBoardName() {
    if(this.board) {
      return this.board.name;
    }
  }

  setBoardList(boardId: String) {
    this.boardListService.getBoardList(boardId).subscribe((data) => {
      this.allBoardLists = Object.assign(new Array<Boardlist>(), data);
    });
  }

  addBoardList(boardList: Boardlist) {
    boardList.board = this.board.id;
    this.boardListService.createBoardList(boardList).subscribe((data) => {
      this.response = Object.assign(new Response(), data);
      if(this.response.isValid) {
        this.setBoardList(this.boardId);
      }
    });
  }

  deleteBoardList(boardListId: number) {
    this.boardListService.deleteBoardListById(boardListId).subscribe((data) => {
      this.setBoardList(this.boardId);
    });
  }

  getError(errorField: String, id?: String) {
    if(this.response.errors) {
      let error: Error = this.response.errors.find(error => {return error.errorField === errorField});
      if(error) {
        return error.errorMessage;
      }
    }
  }

}
