import { Board } from './board.model';

export class Boardlist {
    id: number;
    board: number;
    name: string;

    constructor(id?: number, board?: number, name?: string) {
        this.id = id;
        this.board = board;
        this.name = name;
    }
}
