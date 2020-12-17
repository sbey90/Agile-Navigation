import { AuthenticationService } from './../../../services/authentication.service';
import { UserService } from './../../../services/user.service';
import { ActivatedRoute } from '@angular/router';
import { NgForm } from '@angular/forms';
import { User } from './../../../models/user';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-promote-user',
  templateUrl: './promote-user.component.html',
  styleUrls: ['./promote-user.component.css']
})
export class PromoteUserComponent implements OnInit {


  user : User = new User(0,"", "", "", "", "", "", "", "",0);
  myImage: string = "assets/img/career2.jpg";
  currentRole: string;

  isFormInvalid = false;
  promotionSuccess = false;
  promotionFailed = false;


  constructor(private route: ActivatedRoute,
    private userService: UserService,
    private authenticationService: AuthenticationService) { }

  ngOnInit(): void {

    this.getUser();
  }

  
  getUser(): void {

    const id = +this.route.snapshot.paramMap.get('id')!.valueOf();

  
   this.userService.getUser(id)
   .subscribe((data:User)=>{
     console.log(data);
     this.user = data;
     this.currentRole = this.user.role;
     this.user.role = "";
   })
 }

  onSubmit(signInForm: NgForm){


    if (!signInForm.valid) {
      this.isFormInvalid = true;
      this.promotionSuccess = false;
      this.promotionFailed = false;
      return;
    }

    console.log(signInForm.value)
    console.log(this.user);   

    
    console.log(signInForm.value)

    this.userService.updateCareer(this.user)
      .subscribe(data => {
        console.log(data);


        if (data.status === 'Career updated successfully') {
               
          this.isFormInvalid = false;
          this.promotionSuccess = true;
          this.promotionFailed = false;
        } else if (data.status === 'Career not updated'){
          this.isFormInvalid = false;
          this.promotionSuccess = false;
          this.promotionFailed = true;
        }

      });


  }


}
