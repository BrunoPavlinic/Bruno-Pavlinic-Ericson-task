import { Component, Input, OnInit } from '@angular/core';
import { Boardlist } from '../boardlist.model';
import { BoardlistService } from '../boardlist.service';
import { CardService } from '../card.service';
import { Card } from '../card.model';
import { Error } from '../error.model';
import { Response } from '../response.model';

@Component({
  selector: 'app-boardlist',
  templateUrl: './boardlist.component.html',
  styleUrls: ['./boardlist.component.css']
})
export class BoardlistComponent implements OnInit {

  @Input() boardList: Boardlist;
  response = new Response();
  allCards: Card[];
  newCard =  new Card(0, 0, "");

  constructor(public boardListService: BoardlistService, public cardService: CardService) { }

  ngOnInit() {
    this.setCards(this.boardList.id.toString());
  }

  setCards(listId: String) {
    this.cardService.getCardList(this.boardList.id.toString()).subscribe((data) => {
      this.allCards = Object.assign(new Array<Card>(), data);
    });
  }

  updateBoardList(boardListName: string) {
    let boardList = new Boardlist(this.boardList.id, this.boardList.board, boardListName);
    this.boardListService.updateBoardList(boardList).subscribe((data) => {
      this.response = Object.assign(new Response(), data);
    });
  }

  deleteCard(cardId: number) {
    this.cardService.deleteCardById(cardId).subscribe((data) => {
      this.setCards(this.boardList.id.toString());
    });
  }

  addCard(card: Card) {
    card.list = this.boardList.id;
    this.cardService.createCard(card).subscribe((data) => {
      this.response = Object.assign(new Response(), data);
      if(this.response.isValid) {
        this.setCards(this.boardList.id.toString());
      }
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
