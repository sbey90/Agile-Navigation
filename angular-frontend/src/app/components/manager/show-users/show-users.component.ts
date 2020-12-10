import { UserService } from './../../../services/user.service';
import { User } from './../../../models/user';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-show-users',
  templateUrl: './show-users.component.html',
  styleUrls: ['./show-users.component.css']
})
export class ShowUsersComponent implements OnInit {
  

  users : User[] = [];

  constructor(private userService: UserService ) { }

  ngOnInit(): void {
    this.userService.getAllUsers().subscribe((data: User[]) => {
      console.log(data);
      this.users = data;
    });


  }

}
