import { User } from './../../../models/user';
import { Observable, Subject } from 'rxjs';
import { UserService } from './../../../services/user.service';
import { AuthenticationService } from './../../../services/authentication.service';
import { TaskService } from './../../../services/task.service';
import { Task } from './../../../models/task';
import { FormControl, NgForm } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { debounceTime, distinctUntilChanged, switchMap } from 'rxjs/operators';
import { startWith, map } from 'rxjs/operators';

@Component({
  selector: 'app-create-task',
  templateUrl: './create-task.component.html',
  styleUrls: ['./create-task.component.css']
})
export class CreateTaskComponent implements OnInit {

  task: Task = new Task(0, "", "", "", "", "", "", 0, "", "", 0, "", "");
  myImage: string = "assets/img/task.png";


  filteredOptions: Observable<User[]>;
  myControl = new FormControl();
  options: User[];

  isFormInvalid = false;
  taskCreated = false;
  taskNotCreated = false;

  constructor(private taskService: TaskService,
    private userService: UserService,
    private authenticationService: AuthenticationService) { }

  ngOnInit(): void {

    this.userService.getAllUsers().subscribe((data: User[]) => {
      this.getEmployeeName(data)
    });

  }


  getEmployeeName(data: any) {
    this.options = data;
    this.filteredOptions = this.myControl.valueChanges
      .pipe(
        startWith(''),
        map(value => value.length >= 1 ? this._filter(value) : [])
      );
  }
  private _filter(value: string) {
    const filterValue = value.toLowerCase();

    return this.options.filter(option => (option.firstName + ' ' + option.lastName)
      .toLowerCase().includes(filterValue));
  }



  onSubmit(signInForm: NgForm) {


    if (!signInForm.valid) {
      this.isFormInvalid = true;
      this.taskCreated = false;
      this.taskNotCreated = false;
      return;
    }


    console.log(signInForm)
    console.log(this.myControl.value)
    this.task.employee = this.myControl.value;



    console.log(signInForm.value)
    this.task.managerId = this.authenticationService.userId;
    console.log(this.task)
    this.taskService.addTask(this.task)
      .subscribe(data => {
        console.log(data);

        if (data.status === 'Task created successfully') {
          this.isFormInvalid = false;
          this.taskCreated = true;
          this.taskNotCreated = false;
        } else if (data.status === 'Failed to save task'){
          this.isFormInvalid = false;
          this.taskCreated = false;
          this.taskNotCreated = true;
        }

      });

  }

}
