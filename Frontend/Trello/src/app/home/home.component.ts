import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Board } from '../board.model';
import { BoardService } from '../board.service';
import { Error } from '../error.model';
import { Response } from '../response.model';
import { User } from '../user.model';
import { UserService } from '../user.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  username: string;
  user: User;
  newBoard = new Board(0, 0, "");
  updatedBoard = new Board(0, 0, "");
  response = new Response();
  allBoards: Board[];

  constructor(public userService: UserService, public boardService: BoardService, private route: ActivatedRoute, private router: Router) {
  }

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      this.username = decodeURI(params.get('username'));
    });
    this.userService.getUserByUsername(this.username).subscribe((data) => {
      this.user = Object.assign(new User(), data);
      if(this.user.id) {
        this.setBoards(this.user.id);
      } else {
        this.router.navigate(['../../login/'], { relativeTo: this.route });
      }
    });
  }

  getHomeLink() {
    return "/home/" + this.username;
  }

  setBoards(userId: number) {
    this.boardService.getUserBoards(userId.toString()).subscribe((data) => {
      this.allBoards = Object.assign(new Array<Board>(), data);
    });
  }

  createBoard(board: Board) {
    board.user = this.user.id;
    this.boardService.createBoard(board).subscribe((data) => {
      this.response = Object.assign(new Response(), data);
      if(this.response.isValid) {
        this.setBoards(this.user.id);
      }
    });
  }

  updateBoard(boardId: number, boardName: string) {
    if(boardName == "") {
      document.getElementById(boardId.toString()).setAttribute("style", "display:block;");
    } else {
      let board = new Board(boardId, this.user.id, boardName);
      this.boardService.updateBoard(board).subscribe((data) => {
        this.response = Object.assign(new Response(), data);
        this.setBoards(this.user.id);
      });
    }
  }

  deleteBoard(boardId: number) {
    this.boardService.deleteBoardById(boardId).subscribe((data) => {
      this.setBoards(this.user.id);
    });
  }

  viewBoard(boardId: number) {
    this.router.navigate([decodeURI(this.router.url) + '/board/' + boardId], { relativeTo: this.route });
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
