import { Error } from './error.model';

export class Response {
    isValid: boolean;
    responseObject: Object;
    errors: Error[];
}
