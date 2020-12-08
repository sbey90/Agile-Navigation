import { AuthorizationService } from './../../services/authorization.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.css']
})
export class SigninComponent implements OnInit {

  myImage: string = "assets/img/index1.jpg";
  username: string = "";
  password: string = "";
  message: any;

  constructor(private service: AuthorizationService, private router: Router) { }

  ngOnInit(): void {
  }

  onSubmit(){
    console.log(this.username + " " + this.password);
    this.signin();
  }

  signin(){
    let data = this.service.signin(this.username, this.password);
    console.log(data);
  }

}
