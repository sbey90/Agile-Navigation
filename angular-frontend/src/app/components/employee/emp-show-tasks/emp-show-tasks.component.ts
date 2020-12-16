import { Task } from './../../../models/task';
import { TaskService } from './../../../services/task.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-emp-show-tasks',
  templateUrl: './emp-show-tasks.component.html',
  styleUrls: ['./emp-show-tasks.component.css']
})
export class EmpShowTasksComponent implements OnInit {

  tasks : Task[] = [];

  constructor(private taskService: TaskService) { }

  ngOnInit(): void {
    this.getTasks();
  }


  getTasks(){
    this.taskService.getAllTasks()
    .subscribe(data => this.tasks = data);
  }

}
