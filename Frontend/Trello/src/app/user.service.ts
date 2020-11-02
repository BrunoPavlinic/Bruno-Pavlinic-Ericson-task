import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import {User} from './user.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      'Authorization': 'my-auth-token'
    })
  };

  constructor(public http: HttpClient) { }

  getUserByUsername(username: String) {
    return this.http.get('http://localhost:8080/user/' + username);
  }

  login(user: User) {
    return this.http.post('http://localhost:8080/user/login', user, this.httpOptions);
  }

  signUp(user: User) {
    return this.http.post('http://localhost:8080/user/signUp', user, this.httpOptions);
  }

}
