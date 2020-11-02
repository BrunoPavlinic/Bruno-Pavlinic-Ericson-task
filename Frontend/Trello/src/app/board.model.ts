export class Board {
    id: number;
    user: number;
    name: string;

    constructor(id?: number, user?: number, name?: string) {
        this.id = id;
        this.user = user;
        this.name = name;
    }
}
