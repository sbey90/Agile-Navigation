import { NgForm } from '@angular/forms';
import { UserService } from './../../../services/user.service';
import { User } from './../../../models/user';
import { Component, Input, OnInit } from '@angular/core';
import {Location} from '@angular/common';

@Component({
  selector: 'app-update-profile',
  templateUrl: './update-profile.component.html',
  styleUrls: ['./update-profile.component.css']
})
export class UpdateProfileComponent implements OnInit {

  @Input() user: User = new User(0, "", "", "", "", "", "", "", "",0);

  isFormInvalid = false;
  passwordNotMatch = false;
  emailNotAvailable = false;
  usernameNotAvailable = false;
  accountNotUpdated = false;
  accountUpdated = false;
  
  constructor(
    private userService: UserService,
    private location: Location
    ) { }

  ngOnInit(): void {
  }


  goBack(): void {
    this.location.back();
  }

   save(): void {

    if(this.user.password != this.user.confirmPassword){      
      this.isFormInvalid = false;
      this.passwordNotMatch= true;
      this.usernameNotAvailable = false;
      this.emailNotAvailable = false;          
      this.accountNotUpdated = false;
      this.accountUpdated = false;
      return;
    }


    console.log(this.user);
    this.userService.updateUser(this.user)
      .subscribe(data => {
        console.log(data)
        
        if(data.status == 'username not available'){
          this.isFormInvalid = false;
          this.passwordNotMatch= false;
          this.usernameNotAvailable = true;
          this.emailNotAvailable = false;          
          this.accountNotUpdated = false;
          this.accountUpdated = false;

        } else if(data.status == 'email not available'){
          this.isFormInvalid = false;
          this.passwordNotMatch= false;
          this.usernameNotAvailable = false;
          this.emailNotAvailable = true;          
          this.accountNotUpdated = false;
          this.accountUpdated = false;

        } else if(data.status == 'profile not updated'){
          this.isFormInvalid = false;
          this.passwordNotMatch= false;
          this.usernameNotAvailable = false;
          this.emailNotAvailable = false;          
          this.accountNotUpdated = true;
          this.accountUpdated = false;

        } else if(data.status == 'profile updated successfully'){
          this.isFormInvalid = false;
          this.passwordNotMatch= false;
          this.usernameNotAvailable = false;
          this.emailNotAvailable = false;          
          this.accountNotUpdated = false;
          this.accountUpdated = true;
        }
      })
    }

      onSubmit(signInForm: NgForm){

        console.log(signInForm)
     if (!signInForm.valid) {
      this.isFormInvalid = true;
      this.passwordNotMatch= false;
      this.usernameNotAvailable = false;
      this.emailNotAvailable = false;          
      this.accountNotUpdated = false;
      this.accountUpdated = false;
      return;
    }

    
    if(signInForm.value.password != signInForm.value.confirmPassword){      
      this.isFormInvalid = false;
      this.passwordNotMatch= true;
      this.usernameNotAvailable = false;
      this.emailNotAvailable = false;          
      this.accountNotUpdated = false;
      this.accountUpdated = false;
      return;
    }


    console.log(this.user);
    this.userService.updateUser(this.user)
      .subscribe(data => {
        console.log(data)
        
        if(data.status == 'username not available'){
          this.isFormInvalid = false;
          this.passwordNotMatch= false;
          this.usernameNotAvailable = true;
          this.emailNotAvailable = false;          
          this.accountNotUpdated = false;
          this.accountUpdated = false;

        } else if(data.status == 'email not available'){
          this.isFormInvalid = false;
          this.passwordNotMatch= false;
          this.usernameNotAvailable = false;
          this.emailNotAvailable = true;          
          this.accountNotUpdated = false;
          this.accountUpdated = false;

        } else if(data.status == 'profile not updated'){
          this.isFormInvalid = false;
          this.passwordNotMatch= false;
          this.usernameNotAvailable = false;
          this.emailNotAvailable = false;          
          this.accountNotUpdated = true;
          this.accountUpdated = false;

        } else if(data.status == 'profile updated successfully'){
          this.isFormInvalid = false;
          this.passwordNotMatch= false;
          this.usernameNotAvailable = false;
          this.emailNotAvailable = false;          
          this.accountNotUpdated = false;
          this.accountUpdated = true;
        }
      })
  }



}
