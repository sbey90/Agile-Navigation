import { UserService } from './../../services/user.service';
import { User } from './../../models/user';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  user : User = new User(0,"", "", "", "", "", "", "", "");
  myImage: string = "assets/img/index1.jpg";
  status : any;

  constructor(private userService: UserService) { }

  ngOnInit(): void {
  }


  onSubmit(){
    console.log(this.user);
    this.addUser();
  }
  
    addUser(): void {
     
      this.userService.addUser(this.user)
        .subscribe(data => {
          console.log(data)
          // console.log(data.status);
          // console.log(data.hi)
        })
    }


}
