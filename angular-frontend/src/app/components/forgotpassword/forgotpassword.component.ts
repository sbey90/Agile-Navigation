import { UserService } from './../../services/user.service';
import { NgForm } from '@angular/forms';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-forgotpassword',
  templateUrl: './forgotpassword.component.html',
  styleUrls: ['./forgotpassword.component.css']
})
export class ForgotpasswordComponent implements OnInit {

  myImage: string = "assets/img/index1.jpg";

  isFormInvalid = false;
  isInvalidEmail = false;
  emailNotSent = false;
  emailSent= false;

  constructor(private userService: UserService) { }

  ngOnInit(): void {
  }


  onSubmit(signInForm: NgForm) {

    if (!signInForm.valid) {
      this.isFormInvalid = true;
      this.isInvalidEmail = false;
      this.emailNotSent = false;
      this.emailSent= false;
      return;
    }

    console.log(signInForm.value)

    
    let email : string = "";

    const emailData = {
       email : signInForm.value.email
    }

    this.userService.forgotPass(emailData)
      .subscribe(data => {
        console.log(data);

        if(data.status == 'invalid email'){

          this.isFormInvalid = false;
          this.isInvalidEmail = true;
          this.emailNotSent = false;
          this.emailSent= false;

        } else if(data.status == 'email not sent'){
          this.isFormInvalid = false;
          this.isInvalidEmail = false;
          this.emailNotSent = true;
          this.emailSent= false;

        } else if(data.status == 'email sent successfully'){
          this.isFormInvalid = false;
          this.isInvalidEmail = false;
          this.emailNotSent = false;
          this.emailSent= true;
        } 

      });

  }

}
