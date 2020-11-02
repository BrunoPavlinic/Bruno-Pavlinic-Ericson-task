export class Error {
    errorMessage: String;
    errorField: String;

    constructor(errorMessage: String, errorField: String) {
        this.errorMessage = errorMessage;
        this.errorField = errorField;
    }
}
