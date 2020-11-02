import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Error } from '../error.model';
import { Response } from '../response.model';
import { User } from '../user.model';
import { UserService } from '../user.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
  user = new User(0, "", "");
  response = new Response();

  constructor(public userService: UserService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit() {
  }

  signUp(user: User) {
    this.userService.signUp(user).subscribe((data) => {
      this.response = Object.assign(new Response(), data);
      if(this.response.isValid) {
        this.router.navigate(['../home/' + this.user.username], { relativeTo: this.route });
      }
    });
  }

  getError(errorField: String) {
    if(this.response.errors) {
      let error: Error = this.response.errors.find(error => {return error.errorField === errorField});
      if(error) {
        return error.errorMessage;
      }
    }
  }

}
