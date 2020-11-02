export class Card {
    id: number;
    list: number;
    text: string;

    constructor(id?: number, list?: number, text?: string) {
        this.id = id;
        this.list = list;
        this.text = text;
    }
}
