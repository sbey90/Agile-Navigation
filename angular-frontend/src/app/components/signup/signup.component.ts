import { UserService } from './../../services/user.service';
import { User } from './../../models/user';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  user : User = new User(0,"", "", "", "", "", "", "", "",0);
  myImage: string = "assets/img/index1.jpg";

  isFormInvalid = false;
  passwordNotMatch = false;
  emailNotAvailable = false;
  usernameNotAvailable = false;
  accountCreated = false;
  accountNotCreated = false;

  constructor(private userService: UserService) { }

  ngOnInit(): void {
  }

 
  onSubmit(signInForm: NgForm){


    if (!signInForm.valid) {
      this.isFormInvalid = true;
      this.passwordNotMatch= false;
      this.usernameNotAvailable = false;
      this.emailNotAvailable = false;          
      this.accountNotCreated = false;
      this.accountCreated = false;
      return;
    }

    if(signInForm.value.password != signInForm.value.confirmPassword){      
      this.isFormInvalid = false;
      this.passwordNotMatch= true;
      this.usernameNotAvailable = false;
      this.emailNotAvailable = false;          
      this.accountNotCreated = false;
      this.accountCreated = false;
      return;
    }
    console.log(signInForm.value)
    console.log(this.user);
    this.addUser();

  }


  addUser(): void {
    
    this.userService.addUser(this.user)
      .subscribe(data => {
        console.log(data)
        
        if(data.status == 'username not available'){
          this.isFormInvalid = false;
          this.passwordNotMatch= false;
          this.usernameNotAvailable = true;
          this.emailNotAvailable = false;          
          this.accountNotCreated = false;
          this.accountCreated = false;

        } else if(data.status == 'email not available'){
          this.isFormInvalid = false;
          this.passwordNotMatch= false;
          this.usernameNotAvailable = false;
          this.emailNotAvailable = true;          
          this.accountNotCreated = false;
          this.accountCreated = false;

        } else if(data.status == 'account not created'){
          this.isFormInvalid = false;
          this.passwordNotMatch= false;
          this.usernameNotAvailable = false;
          this.emailNotAvailable = false;          
          this.accountNotCreated = true;
          this.accountCreated = false;


        } else if(data.status == 'account created'){
          this.isFormInvalid = false;
          this.passwordNotMatch= false;
          this.usernameNotAvailable = false;
          this.emailNotAvailable = false;          
          this.accountNotCreated = false;
          this.accountCreated = true;
        }


      })
  }


}
