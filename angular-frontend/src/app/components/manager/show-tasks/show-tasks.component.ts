import { TaskService } from './../../../services/task.service';
import { Task } from './../../../models/task';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-show-tasks',
  templateUrl: './show-tasks.component.html',
  styleUrls: ['./show-tasks.component.css']
})
export class ShowTasksComponent implements OnInit {

  tasks : Task[] = [];

  constructor(private taskService: TaskService) { }

  ngOnInit(): void {

    this.taskService.getAllTasks()
    .subscribe((data:Task[])=>{
      console.log(data);
      this.tasks = data;
    })
  }

}
