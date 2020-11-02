import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { Card } from './card.model';

@Injectable({
  providedIn: 'root'
})
export class CardService {

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      'Authorization': 'my-auth-token'
    })
  };

  constructor(public http: HttpClient) { }

  getCardList(listId: String) {
    return this.http.get('http://localhost:8080/card/getAll/' + listId);
  }

  createCard(card: Card) {
    return this.http.post('http://localhost:8080/card/create', card, this.httpOptions);
  }

  updateCard(card: Card) {
    return this.http.put('http://localhost:8080/card/update', card, this.httpOptions);
  }

  deleteCardById(cardId: number) {
    return this.http.delete('http://localhost:8080/card/' + cardId);
  }

}
