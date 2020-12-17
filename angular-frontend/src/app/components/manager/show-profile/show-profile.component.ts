import { User } from './../../../models/user';
import { AuthenticationService } from './../../../services/authentication.service';
import { UserService } from './../../../services/user.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-show-profile',
  templateUrl: './show-profile.component.html',
  styleUrls: ['./show-profile.component.css']
})
export class ShowProfileComponent implements OnInit {

  user: User = new User(0, "", "", "", "", "", "", "", "",0);
  selectedUser : User = new User(0, "", "", "", "", "", "", "", "",0);

  constructor(private userService: UserService, private authenticationService: AuthenticationService) { }

  ngOnInit(): void {
    console.log(this.authenticationService.userId);
    this.getUser(this.authenticationService.userId);
  }


  
  onSelect(user: User): void {
    this.selectedUser = user;
  }


  getUser(userId: number) : void{
    console.log(userId);
    this.userService.getUser(userId)
    .subscribe(data =>{
      console.log(data)
      this.user = data;
    })
  }

}
