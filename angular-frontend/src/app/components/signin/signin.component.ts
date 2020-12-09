import { SignInData } from './../../models/signindata';
import { AuthenticationService } from './../../services/authentication.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.css']
})
export class SigninComponent implements OnInit {

  myImage: string = "assets/img/index1.jpg";

  isFormInvalid = false;
  areCredentialsInvalid = false;

  constructor(private authenticationService: AuthenticationService, private router: Router) { }

  ngOnInit(): void {
  }

  onSubmit(signInForm: NgForm) {

    if (!signInForm.valid) {
      this.isFormInvalid = true;
      this.areCredentialsInvalid = false;
      return;
    }

    console.log(signInForm.value)
    const signInData = new SignInData(signInForm.value.username, signInForm.value.password);

    this.authenticationService.authenticate(signInData)
      .subscribe(data => {
        console.log(data);

        if (data.status == "signin failed") {
          this.isFormInvalid = false;
          this.areCredentialsInvalid = true;
        }

      });

  }


}
