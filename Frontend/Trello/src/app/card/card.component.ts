import { Component, Input, OnInit } from '@angular/core';
import { Card } from '../card.model';
import { CardService } from '../card.service';
import { Error } from '../error.model';
import { Response } from '../response.model';

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})
export class CardComponent implements OnInit {

  @Input() card: Card;
  response = new Response();

  constructor(public cardService: CardService) { }

  ngOnInit() {
  }

  updateCard(cardText: string) {
    let card = new Card(this.card.id, this.card.list, cardText);
    this.cardService.updateCard(card).subscribe((data) => {
      this.response = Object.assign(new Response(), data);
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
